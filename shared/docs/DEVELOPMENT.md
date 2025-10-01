# Guide de Développement - Daylio SAC

## Configuration de l'environnement

### Prérequis généraux
- Git installé
- Éditeur de code (VS Code, IntelliJ IDEA, etc.)
- Connaissances en développement mobile

### Environnement Android

#### Installation
1. **Android Studio** :
   - Télécharger depuis [developer.android.com](https://developer.android.com)
   - Installer Android Studio Arctic Fox ou supérieur
   
2. **JDK** :
   ```bash
   # Vérifier l'installation
   java -version
   # Doit être JDK 11 ou supérieur
   ```

3. **Android SDK** :
   - Ouvrir Android Studio → SDK Manager
   - Installer Android SDK 24 (minimum) à 33 (cible)
   - Installer Android SDK Build-Tools
   - Installer Google Play Services

#### Configuration du projet
```bash
cd android
./gradlew wrapper --gradle-version 7.5
./gradlew build
```

### Environnement iOS

#### Installation (macOS uniquement)
1. **Xcode** :
   - Installer depuis l'App Store
   - Version 13 ou supérieure requise
   
2. **Command Line Tools** :
   ```bash
   xcode-select --install
   ```

3. **CocoaPods** (si utilisé) :
   ```bash
   sudo gem install cocoapods
   cd ios
   pod install
   ```

#### Configuration du projet
```bash
cd ios
open DaylioSAC.xcodeproj
```

## Structure du code

### Android (Kotlin)

#### Structure des packages
```
com.dayliosac/
├── data/
│   ├── database/          # Room database, DAOs
│   ├── model/             # Entités de données
│   ├── repository/        # Repositories
│   └── security/          # Chiffrement, PIN
├── ui/
│   ├── consumption/       # Écran de consommation
│   ├── statistics/        # Écran de statistiques
│   ├── guides/            # Écran des guides
│   ├── settings/          # Écran des paramètres
│   └── common/            # Composants réutilisables
├── viewmodel/             # ViewModels
├── utils/                 # Utilitaires
└── DaylioSACApplication.kt
```

#### Exemple de ViewModel
```kotlin
class ConsumptionViewModel(
    private val repository: ConsumptionRepository
) : ViewModel() {
    
    private val _consumptions = MutableLiveData<List<Consumption>>()
    val consumptions: LiveData<List<Consumption>> = _consumptions
    
    fun loadConsumptions() {
        viewModelScope.launch {
            _consumptions.value = repository.getAllConsumptions()
        }
    }
    
    fun addConsumption(consumption: Consumption) {
        viewModelScope.launch {
            repository.insertConsumption(consumption)
            loadConsumptions()
        }
    }
}
```

### iOS (Swift)

#### Structure des dossiers
```
DaylioSAC/
├── Models/               # Modèles de données
├── Views/                # Views et UI Components
├── Controllers/          # ViewControllers
├── Services/             # Services (Database, Security)
├── Utils/                # Utilitaires
└── Resources/            # Assets, Localizations
```

#### Exemple de ViewController
```swift
class ConsumptionViewController: UIViewController {
    
    private let viewModel = ConsumptionViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupUI()
        loadData()
    }
    
    private func loadData() {
        viewModel.fetchConsumptions { [weak self] result in
            switch result {
            case .success(let consumptions):
                self?.updateUI(with: consumptions)
            case .failure(let error):
                self?.showError(error)
            }
        }
    }
}
```

## Conventions de code

### Kotlin
- Utiliser camelCase pour les variables et fonctions
- Utiliser PascalCase pour les classes
- Indentation : 4 espaces
- Longueur maximale de ligne : 120 caractères
- Préférer les expressions aux instructions

```kotlin
// ✅ Bon
val userName: String = user.name
fun calculateStatistics(): Statistics { ... }

// ❌ Éviter
val user_name: String = user.name
fun Calculate_Statistics(): Statistics { ... }
```

### Swift
- Utiliser camelCase pour les variables et fonctions
- Utiliser PascalCase pour les types
- Indentation : 4 espaces
- Utiliser guard pour la validation

```swift
// ✅ Bon
let userName: String = user.name
func calculateStatistics() -> Statistics { ... }

// ❌ Éviter
let user_name: String = user.name
func CalculateStatistics() -> Statistics { ... }
```

## Base de données

### Android (Room + SQLCipher)

#### Définition d'entité
```kotlin
@Entity(tableName = "consumptions")
data class Consumption(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    @ColumnInfo(name = "product_id")
    val productId: Long,
    
    val quantity: Double,
    val unit: String,
    val datetime: String,
    
    @ColumnInfo(name = "mood_id")
    val moodId: Long?,
    
    val note: String?
)
```

#### DAO
```kotlin
@Dao
interface ConsumptionDao {
    @Query("SELECT * FROM consumptions ORDER BY datetime DESC")
    suspend fun getAllConsumptions(): List<Consumption>
    
    @Insert
    suspend fun insertConsumption(consumption: Consumption): Long
    
    @Query("SELECT * FROM consumptions WHERE datetime >= :startDate AND datetime <= :endDate")
    suspend fun getConsumptionsByDateRange(startDate: String, endDate: String): List<Consumption>
}
```

#### Database
```kotlin
@Database(
    entities = [Consumption::class, Product::class, Mood::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun consumptionDao(): ConsumptionDao
    abstract fun productDao(): ProductDao
    abstract fun moodDao(): MoodDao
}
```

### iOS (SQLite.swift)

```swift
import SQLite

class DatabaseManager {
    private var db: Connection?
    
    private let consumptions = Table("consumptions")
    private let id = Expression<Int64>("id")
    private let productId = Expression<Int64>("product_id")
    private let quantity = Expression<Double>("quantity")
    private let datetime = Expression<String>("datetime")
    
    func insertConsumption(_ consumption: Consumption) throws {
        try db?.run(consumptions.insert(
            productId <- consumption.productId,
            quantity <- consumption.quantity,
            datetime <- consumption.datetime
        ))
    }
}
```

## Sécurité

### Implémentation du PIN (Android)

```kotlin
class SecurityManager(private val context: Context) {
    
    private val prefs = context.getSharedPreferences("security", Context.MODE_PRIVATE)
    
    fun setPIN(pin: String): Boolean {
        val salt = generateSalt()
        val hashedPin = hashPIN(pin, salt)
        
        return prefs.edit()
            .putString("pin_hash", hashedPin)
            .putString("pin_salt", salt)
            .commit()
    }
    
    fun verifyPIN(pin: String): Boolean {
        val storedHash = prefs.getString("pin_hash", null) ?: return false
        val salt = prefs.getString("pin_salt", null) ?: return false
        
        val hashedPin = hashPIN(pin, salt)
        return hashedPin == storedHash
    }
    
    private fun hashPIN(pin: String, salt: String): String {
        val spec = PBEKeySpec(pin.toCharArray(), salt.toByteArray(), 10000, 256)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val hash = factory.generateSecret(spec).encoded
        return Base64.encodeToString(hash, Base64.NO_WRAP)
    }
    
    private fun generateSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return Base64.encodeToString(salt, Base64.NO_WRAP)
    }
}
```

### Chiffrement de la base de données (Android)

```kotlin
val passphrase = SQLiteDatabase.getBytes(pin.toCharArray())
val factory = SupportFactory(passphrase)

val database = Room.databaseBuilder(
    context,
    AppDatabase::class.java,
    "dayliosac.db"
).openHelperFactory(factory)
 .build()
```

## Tests

### Tests unitaires (Android)

```kotlin
@Test
fun testAddConsumption() = runBlocking {
    val consumption = Consumption(
        productId = 1,
        quantity = 5.0,
        unit = "ml",
        datetime = "2024-01-01T12:00:00"
    )
    
    val id = dao.insertConsumption(consumption)
    assertTrue(id > 0)
    
    val retrieved = dao.getConsumptionById(id)
    assertEquals(consumption.quantity, retrieved.quantity)
}
```

### Tests UI (Android - Espresso)

```kotlin
@Test
fun testAddConsumptionFlow() {
    onView(withId(R.id.fab_add_consumption))
        .perform(click())
    
    onView(withId(R.id.edit_quantity))
        .perform(typeText("5.0"))
    
    onView(withId(R.id.button_save))
        .perform(click())
    
    onView(withText("Consommation ajoutée"))
        .check(matches(isDisplayed()))
}
```

## Génération de graphiques

### Android (MPAndroidChart)

```kotlin
fun setupChart(chart: LineChart, data: List<StatisticPoint>) {
    val entries = data.map { Entry(it.timestamp.toFloat(), it.value.toFloat()) }
    val dataSet = LineDataSet(entries, "Consommation")
    
    dataSet.color = Color.BLUE
    dataSet.setDrawCircles(false)
    dataSet.lineWidth = 2f
    
    chart.data = LineData(dataSet)
    chart.invalidate()
}
```

### iOS (Charts)

```swift
func setupChart(_ chartView: LineChartView, data: [StatisticPoint]) {
    let entries = data.map { ChartDataEntry(x: $0.timestamp, y: $0.value) }
    let dataSet = LineChartDataSet(entries: entries, label: "Consommation")
    
    dataSet.setColor(.blue)
    dataSet.drawCirclesEnabled = false
    dataSet.lineWidth = 2.0
    
    chartView.data = LineChartData(dataSet: dataSet)
}
```

## Export de données

### Export CSV

```kotlin
fun exportToCSV(consumptions: List<Consumption>): String {
    val csv = StringBuilder()
    csv.append("Date,Produit,Quantité,Unité,Humeur,Note\n")
    
    consumptions.forEach { consumption ->
        csv.append("${consumption.datetime},")
        csv.append("${consumption.productName},")
        csv.append("${consumption.quantity},")
        csv.append("${consumption.unit},")
        csv.append("${consumption.mood ?: ""},")
        csv.append("\"${consumption.note ?: ""}\"\n")
    }
    
    return csv.toString()
}
```

### Export JSON

```kotlin
fun exportToJSON(consumptions: List<Consumption>): String {
    return Json.encodeToString(consumptions)
}
```

## Bonnes pratiques

### Performance
1. Utiliser des requêtes asynchrones pour la base de données
2. Paginer les listes longues
3. Mettre en cache les statistiques calculées
4. Optimiser les images et assets

### Sécurité
1. Ne jamais stocker le PIN en clair
2. Utiliser HTTPS pour les flux RSS
3. Valider toutes les entrées utilisateur
4. Effacer les données sensibles de la mémoire

### UX
1. Fournir un feedback immédiat
2. Gérer les états de chargement
3. Messages d'erreur clairs
4. Navigation intuitive

## Débogage

### Android
```bash
# Logs
adb logcat | grep DaylioSAC

# Base de données
adb shell
run-as com.dayliosac
cd databases
```

### iOS
```bash
# Console Xcode
# View → Debug Area → Activate Console

# Base de données
# Simulator → File → Show Finder
```

## Déploiement

### Android
```bash
# Build release
./gradlew assembleRelease

# Signer l'APK
jarsigner -keystore release.keystore app-release-unsigned.apk alias_name
```

### iOS
1. Ouvrir Xcode
2. Product → Archive
3. Distribute App → App Store Connect

## Ressources

### Documentation officielle
- [Android Developers](https://developer.android.com)
- [iOS Developer](https://developer.apple.com)
- [Kotlin Documentation](https://kotlinlang.org/docs)
- [Swift Documentation](https://swift.org/documentation)

### Bibliothèques
- [Room Persistence Library](https://developer.android.com/training/data-storage/room)
- [SQLCipher](https://www.zetetic.net/sqlcipher)
- [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
- [Charts (iOS)](https://github.com/danielgindi/Charts)

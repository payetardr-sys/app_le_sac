# Structure du Projet - Daylio SAC

## Vue d'Ensemble

```
app_le_sac/
├── .github/                           # Configuration GitHub
│   ├── ISSUE_TEMPLATE/
│   │   ├── bug_report.md             # Template pour les bugs
│   │   └── feature_request.md        # Template pour les features
│   └── pull_request_template.md      # Template pour les PRs
│
├── android/                           # Application Android
│   ├── app/
│   │   ├── build.gradle              # Configuration Gradle de l'app
│   │   └── src/
│   │       ├── main/
│   │       │   ├── AndroidManifest.xml
│   │       │   └── java/com/dayliosac/
│   │       │       ├── data/
│   │       │       │   ├── database/
│   │       │       │   │   ├── AppDatabase.kt        # Room Database + SQLCipher
│   │       │       │   │   ├── ConsumptionDao.kt     # DAO pour consommations
│   │       │       │   │   ├── ProductDao.kt         # DAO pour produits
│   │       │       │   │   └── MoodDao.kt            # DAO pour humeurs
│   │       │       │   ├── model/
│   │       │       │   │   ├── Consumption.kt        # Entité Consommation
│   │       │       │   │   ├── Product.kt            # Entité Produit
│   │       │       │   │   └── Mood.kt               # Entité Humeur
│   │       │       │   ├── repository/
│   │       │       │   │   └── ConsumptionRepository.kt  # Repository pattern
│   │       │       │   └── security/
│   │       │       │       └── SecurityManager.kt    # Gestion PIN et chiffrement
│   │       │       ├── ui/                           # Interface utilisateur
│   │       │       ├── viewmodel/                    # ViewModels
│   │       │       └── utils/                        # Utilitaires
│   │       └── test/
│   │           └── java/com/dayliosac/
│   │               └── data/repository/
│   │                   └── ConsumptionRepositoryTest.kt  # Tests unitaires
│   ├── gradle/
│   │   └── wrapper/
│   │       └── gradle-wrapper.properties
│   ├── build.gradle                  # Configuration Gradle racine
│   └── settings.gradle               # Paramètres Gradle
│
├── ios/                               # Application iOS
│   └── DaylioSAC/
│       ├── Models/
│       │   ├── Consumption.swift     # Modèle Consommation
│       │   ├── Product.swift         # Modèle Produit
│       │   └── Mood.swift            # Modèle Humeur
│       ├── Views/                    # Vues SwiftUI/UIKit
│       ├── Controllers/              # ViewControllers
│       ├── Services/                 # Services (Database, etc.)
│       ├── Utils/                    # Utilitaires
│       └── Resources/                # Assets, Localizations
│
├── shared/                            # Ressources partagées
│   ├── docs/                         # Documentation
│   │   ├── ARCHITECTURE.md           # Architecture technique
│   │   ├── DEVELOPMENT.md            # Guide de développement
│   │   └── USER_GUIDE.md             # Guide utilisateur
│   ├── guides/                       # Guides de RdR
│   │   ├── 01-principes-generaux.md  # Principes généraux
│   │   └── 02-situations-urgence.md  # Gestion des urgences
│   ├── data-models/                  # Schémas et configs
│   │   ├── database-schema.md        # Schéma de BDD
│   │   ├── app-config.json           # Configuration app
│   │   └── guides-export.json        # Export des guides
│   └── assets/                       # Assets communs
│
├── admin/                             # Outils d'administration
│   └── tools/
│       └── admin_guides.py           # CLI pour gérer les guides
│
├── .gitignore                        # Fichiers à ignorer
├── CHANGELOG.md                      # Journal des changements
├── CONTRIBUTING.md                   # Guide de contribution
├── LICENSE                           # Licence MIT
├── PROJECT_STRUCTURE.md              # Ce fichier
├── QUICKSTART.md                     # Démarrage rapide
├── README.md                         # Vue d'ensemble
├── ROADMAP.md                        # Feuille de route
└── SECURITY.md                       # Politique de sécurité
```

## Statistiques

- **Total de fichiers** : 34+
- **Lignes de code** : ~15,000+ (incluant documentation)
- **Documentation** : 8 fichiers majeurs
- **Guides RdR** : 2 guides complets
- **Tests** : Tests unitaires configurés

## Technologies

### Android
- **Langage** : Kotlin
- **Architecture** : MVVM avec Repository pattern
- **Base de données** : Room + SQLCipher
- **Sécurité** : EncryptedSharedPreferences, PBKDF2
- **Tests** : JUnit, Mockito

### iOS
- **Langage** : Swift
- **Architecture** : MVC/MVVM
- **Base de données** : SQLite chiffré
- **Tests** : XCTest

### Outils Admin
- **Langage** : Python 3
- **Fonctions** : Gestion des guides, export JSON

## Composants Principaux

### 1. Couche de Données
```
data/
├── database/      # DAOs et Database
├── model/         # Entités
├── repository/    # Repositories
└── security/      # Sécurité
```

### 2. Couche Métier
```
viewmodel/         # Logique de présentation
utils/             # Fonctions utilitaires
```

### 3. Couche Présentation
```
ui/
├── consumption/   # Écrans de consommation
├── statistics/    # Écrans de statistiques
├── guides/        # Écrans des guides
├── settings/      # Écrans des paramètres
└── common/        # Composants réutilisables
```

### 4. Documentation
```
shared/docs/
├── ARCHITECTURE.md    # Architecture technique
├── DEVELOPMENT.md     # Guide de développement
└── USER_GUIDE.md      # Guide utilisateur
```

### 5. Guides RdR
```
shared/guides/
├── 01-principes-generaux.md
├── 02-situations-urgence.md
└── [futurs guides...]
```

## Flux de Données

```
UI Layer (Activities/Fragments)
    ↓
ViewModel (LiveData/StateFlow)
    ↓
Repository (abstraction)
    ↓
DAO (Room/SQLite)
    ↓
Database (SQLCipher chiffrée)
```

## Sécurité

### Stockage
```
[PIN de l'utilisateur]
    ↓ PBKDF2 (10000 iterations)
[Hash + Salt] → EncryptedSharedPreferences
    ↓
[Clé de chiffrement] → SQLCipher
    ↓
[Base de données chiffrée AES-256]
```

### Authentification
```
1. Utilisateur entre PIN
2. Hash avec PBKDF2
3. Comparaison avec hash stocké
4. Si valide → déverrouillage
5. Si invalide → incrémenter tentatives
6. Si 3 échecs → verrouillage 5 min
```

## Patterns de Design

### Repository Pattern
```kotlin
ViewModel → Repository → DAO → Database
```
Avantages :
- Séparation des préoccupations
- Testabilité
- Abstraction de la source de données

### Observer Pattern
```kotlin
DAO → Flow/LiveData → ViewModel → UI
```
Avantages :
- Mises à jour réactives
- Pas de gestion manuelle des callbacks
- Thread-safe

### Singleton Pattern
```kotlin
AppDatabase (via Room)
SecurityManager
```
Avantages :
- Instance unique
- Économie de ressources
- État partagé

## Conventions de Nommage

### Fichiers Kotlin
- **Classes** : PascalCase (ConsumptionDao.kt)
- **Interfaces** : PascalCase + Suffix (Repository)
- **Tests** : NomClasseTest.kt

### Fichiers Swift
- **Classes** : PascalCase (Consumption.swift)
- **Protocols** : PascalCase + Protocol suffix
- **Tests** : NomClasseTests.swift

### Fichiers Documentation
- **Majuscules** : README.md, CONTRIBUTING.md
- **Guides** : NN-nom-du-guide.md

### Base de Données
- **Tables** : snake_case pluriel (consumptions)
- **Colonnes** : snake_case (product_id)
- **Index** : idx_table_column

## Dépendances Principales

### Android
```gradle
// Base de données
androidx.room:room-runtime:2.5.2
net.zetetic:android-database-sqlcipher:4.5.4

// Sécurité
androidx.security:security-crypto:1.1.0-alpha06

// Graphiques
com.github.PhilJay:MPAndroidChart:v3.1.0

// Export
com.itextpdf:itext7-core:7.2.5
```

### iOS
```swift
// Base de données
SQLite.swift ~> 0.14.0

// Graphiques
Charts ~> 4.0.0

// Sécurité
CryptoSwift ~> 1.7.0
```

## Configuration Requise

### Développement
- **Android** : API 24+ (Android 7.0+)
- **iOS** : iOS 14.0+
- **JDK** : 11+
- **Xcode** : 13+

### Production
- **Android** : API 24-33
- **iOS** : iOS 14-16
- **Taille APK** : ~15-20 MB (estimé)
- **Taille IPA** : ~10-15 MB (estimé)

## Prochaines Étapes

1. ✅ Architecture définie
2. ✅ Documentation créée
3. ✅ Modèles de données implémentés
4. ✅ DAOs et Repositories créés
5. 🔄 Interface utilisateur (en cours)
6. 🔄 Tests complets
7. ⏳ Release alpha

---

**Légende** :
- ✅ Complété
- 🔄 En cours
- ⏳ Planifié

**Version** : 1.0.0-alpha
**Dernière mise à jour** : 2024-10-01

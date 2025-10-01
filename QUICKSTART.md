# Démarrage Rapide - Daylio SAC

Ce guide vous aide à démarrer rapidement avec le développement de Daylio SAC.

## Prérequis

### Pour Android
- **JDK 11+** : `java -version`
- **Android Studio** : Arctic Fox ou supérieur
- **Android SDK** : API 24 minimum, API 33 recommandé
- **Git** : Pour cloner le repository

### Pour iOS
- **macOS** : Version 11 ou supérieure
- **Xcode** : Version 13 ou supérieure
- **CocoaPods** : `sudo gem install cocoapods`
- **Git** : Pour cloner le repository

## Installation

### 1. Cloner le repository
```bash
git clone https://github.com/payetardr-sys/app_le_sac.git
cd app_le_sac
```

### 2. Configuration Android

```bash
# Aller dans le dossier Android
cd android

# Vérifier l'installation (optionnel)
./gradlew --version

# Synchroniser et construire
./gradlew build

# Lancer les tests
./gradlew test

# Installer sur un appareil/émulateur
./gradlew installDebug
```

**Première fois avec Gradle Wrapper ?**
```bash
# Si gradlew n'existe pas, créez-le
gradle wrapper --gradle-version 8.0
```

**Problèmes courants :**
- **Erreur de permission** : `chmod +x gradlew`
- **JDK invalide** : Vérifiez `JAVA_HOME`
- **SDK manquant** : Ouvrez Android Studio et installez les SDKs nécessaires

### 3. Configuration iOS

```bash
# Aller dans le dossier iOS
cd ios

# Installer les dépendances (si CocoaPods utilisé)
pod install

# Ouvrir le projet
open DaylioSAC.xcodeproj
# ou si CocoaPods
open DaylioSAC.xcworkspace
```

**Dans Xcode :**
1. Sélectionner un simulateur ou appareil
2. Product → Build
3. Product → Run

**Problèmes courants :**
- **Certificat manquant** : Configurez votre équipe dans Signing & Capabilities
- **Simulator introuvable** : Xcode → Preferences → Components

## Structure du Projet

```
app_le_sac/
├── android/              # Application Android
├── ios/                  # Application iOS
├── shared/               # Ressources partagées
│   ├── docs/            # Documentation
│   ├── guides/          # Guides RdR
│   ├── data-models/     # Schémas de données
│   └── assets/          # Assets communs
└── admin/               # Outils d'administration
```

## Premiers Pas

### Voir les guides disponibles
```bash
python3 admin/tools/admin_guides.py list
```

### Créer un nouveau guide
```bash
python3 admin/tools/admin_guides.py create
```

### Exporter les guides
```bash
python3 admin/tools/admin_guides.py export
```

### Voir les statistiques
```bash
python3 admin/tools/admin_guides.py stats
```

## Développement

### Android

**Ouvrir le projet :**
1. Lancer Android Studio
2. File → Open → Sélectionner le dossier `android`
3. Attendre la synchronisation Gradle

**Structure du code :**
```
android/app/src/main/java/com/dayliosac/
├── data/
│   ├── database/        # Room DAOs et Database
│   ├── model/           # Entités de données
│   ├── repository/      # Repositories
│   └── security/        # Sécurité (PIN, chiffrement)
├── ui/                  # Interface utilisateur
├── viewmodel/           # ViewModels
└── utils/               # Utilitaires
```

**Lancer les tests :**
```bash
./gradlew test
./gradlew connectedAndroidTest  # Tests instrumentés
```

**Générer un APK :**
```bash
./gradlew assembleDebug
# APK dans: app/build/outputs/apk/debug/
```

### iOS

**Structure du code :**
```
ios/DaylioSAC/
├── Models/              # Modèles de données
├── Views/               # Vues SwiftUI ou UIKit
├── Controllers/         # ViewControllers
├── Services/            # Services (Database, Network)
├── Utils/               # Utilitaires
└── Resources/           # Assets, Storyboards
```

**Lancer les tests :**
```bash
xcodebuild test -scheme DaylioSAC -destination 'platform=iOS Simulator,name=iPhone 14'
```

**Générer une build :**
- Product → Archive
- Organizer → Distribute App

## Documentation

### Lire la documentation
- **Architecture** : `shared/docs/ARCHITECTURE.md`
- **Développement** : `shared/docs/DEVELOPMENT.md`
- **Guide utilisateur** : `shared/docs/USER_GUIDE.md`
- **Sécurité** : `SECURITY.md`
- **Contribution** : `CONTRIBUTING.md`

### Documentation en ligne du code

**Android (Dokka) :**
```bash
./gradlew dokkaHtml
# Documentation dans: app/build/dokka/html/
```

**iOS (jazzy) :**
```bash
jazzy --clean --output docs/ios
# Documentation dans: docs/ios/
```

## Workflows Courants

### Ajouter une nouvelle fonctionnalité

1. **Créer une branche**
   ```bash
   git checkout -b feature/ma-fonctionnalite
   ```

2. **Développer et tester**
   ```bash
   # Android
   ./gradlew test
   
   # iOS
   xcodebuild test -scheme DaylioSAC
   ```

3. **Committer**
   ```bash
   git add .
   git commit -m "feat: description de la fonctionnalité"
   ```

4. **Pousser et créer une PR**
   ```bash
   git push origin feature/ma-fonctionnalite
   ```

### Corriger un bug

1. **Créer une branche**
   ```bash
   git checkout -b fix/description-du-bug
   ```

2. **Corriger et tester**
   ```bash
   # Vérifier que le bug est corrigé
   ./gradlew test
   ```

3. **Committer et pousser**
   ```bash
   git commit -m "fix: correction du bug"
   git push origin fix/description-du-bug
   ```

### Mettre à jour la documentation

1. **Éditer les fichiers Markdown**
   - `shared/docs/` pour la documentation technique
   - `shared/guides/` pour les guides RdR

2. **Valider un guide**
   ```bash
   python3 admin/tools/admin_guides.py validate nom-du-guide.md
   ```

3. **Committer**
   ```bash
   git commit -m "docs: mise à jour de la documentation"
   ```

## Ressources Utiles

### Android
- [Documentation Android](https://developer.android.com)
- [Kotlin](https://kotlinlang.org)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [SQLCipher](https://www.zetetic.net/sqlcipher/sqlcipher-for-android/)

### iOS
- [Documentation iOS](https://developer.apple.com/documentation/)
- [Swift](https://swift.org)
- [SQLite.swift](https://github.com/stephencelis/SQLite.swift)

### Bibliothèques
- [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
- [Charts (iOS)](https://github.com/danielgindi/Charts)

## Support

### Questions ?
- **Issues GitHub** : Pour les bugs et features
- **Discussions GitHub** : Pour les questions générales
- **Documentation** : `shared/docs/`

### Besoin d'aide ?
1. Consulter la documentation
2. Chercher dans les issues existantes
3. Créer une nouvelle issue avec le template approprié

## Prochaines Étapes

1. ✅ Explorer la structure du projet
2. ✅ Lire la documentation d'architecture
3. ✅ Comprendre le modèle de données
4. 📝 Créer votre première fonctionnalité
5. 🧪 Écrire des tests
6. 📤 Soumettre une Pull Request

**Bonne contribution ! 💚**

---

**Dernière mise à jour** : 2024
**Version** : 1.0.0

# Daylio SAC - Application de Suivi des Consommations

Une application mobile native pour le suivi et l'analyse des consommations de substances.

## 📱 Description

Daylio SAC est une application mobile de suivi de consommation qui permet aux utilisateurs d'enregistrer leurs consommations de substances, de suivre leur humeur et leur bien-être, et d'analyser leurs habitudes pour favoriser la réduction des risques.

## ✨ Fonctionnalités

### Pour les utilisateurs
- **Enregistrement des consommations** : Produits, quantités, date et heure
- **Suivi de l'humeur** : Enregistrement de l'état émotionnel associé
- **Notes personnelles** : Contexte et observations
- **Analyses et statistiques** :
  - Progression dans le temps
  - Corrélations entre consommation et humeur
  - Périodes d'abstinence et rechutes
- **Graphiques visuels** : Représentations claires des données
- **Guides de réduction des risques** : Informations et conseils
- **Actualités** : Flux RSS d'informations pertinentes
- **Export des données** : PDF, CSV, JSON
- **Sécurité** : 
  - Données 100% locales (pas de cloud)
  - Protection par code PIN
  - Chiffrement des données sensibles

### Pour les administrateurs
- Gestion des guides de réduction des risques
- Configuration des flux RSS
- Gestion des catégories de produits

## 🏗️ Architecture

### Plateformes
- **Android** : Application native (Kotlin/Java)
- **iOS** : Application native (Swift)

### Stockage
- Base de données locale sécurisée (SQLite avec chiffrement)
- Aucune connexion serveur requise
- Export local uniquement

### Sécurité
- Chiffrement de la base de données
- Protection par code PIN
- Pas de collecte de données
- Pas de tracking

## 📂 Structure du Projet

```
app_le_sac/
├── android/                 # Application Android
│   ├── app/
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/
│   │   │   │   ├── res/
│   │   │   │   └── AndroidManifest.xml
│   │   │   └── test/
│   │   └── build.gradle
│   └── gradle/
├── ios/                     # Application iOS
│   ├── DaylioSAC/
│   │   ├── Models/
│   │   ├── Views/
│   │   ├── Controllers/
│   │   └── Resources/
│   └── DaylioSAC.xcodeproj/
├── shared/                  # Ressources partagées
│   ├── docs/               # Documentation
│   ├── guides/             # Guides de réduction des risques
│   ├── data-models/        # Schémas de données
│   └── assets/             # Assets communs
└── admin/                   # Outils d'administration
    └── tools/              # Scripts et utilitaires
```

## 🚀 Démarrage Rapide

### Prérequis Android
- Android Studio Arctic Fox ou supérieur
- JDK 11 ou supérieur
- Android SDK 24 (Android 7.0) minimum

### Prérequis iOS
- Xcode 13 ou supérieur
- macOS 11 ou supérieur
- iOS 14.0 minimum

### Installation

#### Android
```bash
cd android
./gradlew build
./gradlew installDebug
```

#### iOS
```bash
cd ios
open DaylioSAC.xcodeproj
# Puis build et run depuis Xcode
```

## 📊 Modèle de Données

### Entités Principales
- **Consommation** : Enregistrement d'une prise
- **Produit** : Type de substance
- **Humeur** : État émotionnel
- **Note** : Observations personnelles
- **Guide** : Document de réduction des risques
- **Statistique** : Données calculées

## 🔒 Confidentialité et Sécurité

- ✅ Données stockées uniquement en local
- ✅ Chiffrement AES-256 de la base de données
- ✅ Code PIN requis pour l'accès
- ✅ Aucune connexion Internet requise (sauf RSS optionnel)
- ✅ Pas de collecte de données
- ✅ Pas d'analyse comportementale
- ✅ Export contrôlé par l'utilisateur

## 📖 Documentation

Voir le dossier `docs/` pour la documentation complète :
- Architecture technique
- Guide de développement
- Guide utilisateur
- API de la base de données
- Spécifications de sécurité

## 🤝 Contribution

Ce projet vise à aider les personnes en situation de consommation. Les contributions sont les bienvenues pour améliorer l'application.

## 📄 Licence

À définir

## 👥 Équipe

Développé avec le soutien de professionnels de la santé et de la réduction des risques.

## ⚠️ Avertissement

Cette application est un outil d'aide au suivi et à la réduction des risques. Elle ne remplace pas un accompagnement médical ou psychologique professionnel. En cas de besoin, consultez un professionnel de santé.

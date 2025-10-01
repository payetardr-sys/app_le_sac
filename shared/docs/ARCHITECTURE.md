# Architecture Technique - Daylio SAC

## Vue d'ensemble

Daylio SAC est une application mobile native multi-plateforme (Android/iOS) conçue pour le suivi des consommations avec une approche axée sur la confidentialité et la réduction des risques.

## Principes de conception

1. **Confidentialité d'abord** : Toutes les données restent sur l'appareil
2. **Sécurité renforcée** : Chiffrement et protection par PIN
3. **Simplicité d'utilisation** : Interface intuitive et accessible
4. **Hors ligne d'abord** : Fonctionnement complet sans connexion Internet
5. **Données portables** : Export facile dans plusieurs formats

## Architecture en couches

### Couche de présentation
- **Android** : Activities, Fragments, ViewModels (Architecture MVVM)
- **iOS** : ViewControllers, Views (Architecture MVC/MVVM)
- Interface utilisateur native pour chaque plateforme
- Navigation intuitive et cohérente

### Couche métier
- Logique de calcul des statistiques
- Analyse des corrélations
- Génération de graphiques
- Validation des données

### Couche de données
- Base de données SQLite locale
- Chiffrement AES-256
- DAO (Data Access Objects)
- Repository pattern

### Couche de sécurité
- Gestionnaire de PIN
- Chiffrement/Déchiffrement
- Gestion des sessions
- Verrouillage automatique

## Composants principaux

### 1. Module de Suivi des Consommations
**Responsabilités :**
- Enregistrement des consommations
- Gestion des produits
- Horodatage automatique

**Technologies :**
- Base de données SQLite
- SQLCipher pour le chiffrement

### 2. Module de Suivi de l'Humeur
**Responsabilités :**
- Enregistrement de l'état émotionnel
- Association consommation/humeur
- Échelle standardisée

### 3. Module de Statistiques
**Responsabilités :**
- Calcul des métriques
- Détection de patterns
- Analyse de progression
- Calcul des périodes d'abstinence

**Métriques calculées :**
- Fréquence de consommation
- Quantités moyennes
- Tendances temporelles
- Corrélations humeur/consommation

### 4. Module de Visualisation
**Responsabilités :**
- Graphiques de progression
- Calendrier de consommation
- Diagrammes de corrélation
- Courbes de tendance

**Bibliothèques :**
- **Android** : MPAndroidChart
- **iOS** : Charts (Daniel Gindi)

### 5. Module de Guides
**Responsabilités :**
- Affichage des guides de réduction des risques
- Recherche dans les guides
- Gestion du contenu

**Format :**
- Markdown pour les guides
- Assets locaux

### 6. Module RSS
**Responsabilités :**
- Récupération des flux RSS
- Parsing XML
- Mise en cache
- Affichage des actualités

**Fonctionnement :**
- Optionnel (désactivable)
- Mise à jour manuelle ou automatique
- Stockage temporaire

### 7. Module d'Export
**Responsabilités :**
- Export PDF (rapport complet)
- Export CSV (données tabulaires)
- Export JSON (données brutes)

**Formats de sortie :**
```
PDF : Rapport formaté avec graphiques
CSV : Données de consommations
JSON : Structure complète de données
```

### 8. Module de Sécurité
**Responsabilités :**
- Gestion du code PIN
- Chiffrement/déchiffrement
- Verrouillage automatique
- Tentatives de connexion

**Algorithmes :**
- AES-256 pour le chiffrement de la base de données
- PBKDF2 pour le hachage du PIN
- Salt unique par installation

## Schéma de base de données

### Table : consumptions
```sql
CREATE TABLE consumptions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    product_id INTEGER NOT NULL,
    quantity REAL NOT NULL,
    unit TEXT NOT NULL,
    datetime TEXT NOT NULL,
    mood_id INTEGER,
    note TEXT,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (mood_id) REFERENCES moods(id)
);
```

### Table : products
```sql
CREATE TABLE products (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    category TEXT NOT NULL,
    default_unit TEXT,
    icon TEXT,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP
);
```

### Table : moods
```sql
CREATE TABLE moods (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    level INTEGER NOT NULL CHECK(level BETWEEN 1 AND 5),
    label TEXT NOT NULL,
    emoji TEXT
);
```

### Table : guides
```sql
CREATE TABLE guides (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    category TEXT NOT NULL,
    content TEXT NOT NULL,
    author TEXT,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP
);
```

### Table : rss_feeds
```sql
CREATE TABLE rss_feeds (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    url TEXT NOT NULL UNIQUE,
    title TEXT NOT NULL,
    enabled INTEGER DEFAULT 1,
    last_update TEXT
);
```

### Table : settings
```sql
CREATE TABLE settings (
    key TEXT PRIMARY KEY,
    value TEXT NOT NULL
);
```

## Flux de données

### Enregistrement d'une consommation
1. L'utilisateur saisit les informations
2. Validation des données (côté client)
3. Sélection ou création du produit
4. Ajout optionnel de l'humeur et des notes
5. Insertion dans la base de données chiffrée
6. Mise à jour des statistiques en cache
7. Notification de succès

### Consultation des statistiques
1. L'utilisateur accède à l'écran de statistiques
2. Sélection de la période d'analyse
3. Récupération des données depuis la base
4. Calcul des métriques
5. Génération des graphiques
6. Affichage des résultats

### Export des données
1. L'utilisateur choisit le format d'export
2. Sélection de la période
3. Récupération des données
4. Génération du fichier (PDF/CSV/JSON)
5. Sauvegarde dans le stockage de l'appareil
6. Partage optionnel via le système

## Sécurité

### Protection par PIN
- Code à 4-6 chiffres
- 3 tentatives maximum avant verrouillage temporaire
- Hachage PBKDF2 avec 10000 itérations
- Salt unique stocké séparément

### Chiffrement de la base de données
- SQLCipher pour Android et iOS
- Clé de chiffrement dérivée du PIN
- Chiffrement AES-256
- Pas de données en clair

### Verrouillage automatique
- Après 1 minute d'inactivité (configurable)
- À la mise en arrière-plan de l'app
- Option de verrouillage immédiat

## Performance

### Optimisations
- Index sur les colonnes fréquemment requêtées
- Pagination des listes
- Cache des statistiques calculées
- Chargement lazy des graphiques
- Requêtes asynchrones

### Limites recommandées
- Maximum 10 000 enregistrements de consommations
- Cache de 30 jours de statistiques
- 100 guides maximum

## Compatibilité

### Android
- Version minimale : Android 7.0 (API 24)
- Version cible : Android 13 (API 33)
- Taille minimale d'écran : 4.5"

### iOS
- Version minimale : iOS 14.0
- Version cible : iOS 16.0
- Appareils : iPhone 6s et supérieur

## Dépendances principales

### Android
```gradle
dependencies {
    // Base de données
    implementation "androidx.room:room-runtime:2.5.0"
    implementation "net.zetetic:android-database-sqlcipher:4.5.3"
    
    // Graphiques
    implementation "com.github.PhilJay:MPAndroidChart:3.1.0"
    
    // Export PDF
    implementation "com.itextpdf:itext7-core:7.2.5"
    
    // Architecture
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.6.0"
}
```

### iOS
```swift
dependencies: [
    .package(url: "https://github.com/danielgindi/Charts", from: "4.0.0"),
    .package(url: "https://github.com/stephencelis/SQLite.swift", from: "0.14.0"),
    .package(url: "https://github.com/krzyzanowskim/CryptoSwift", from: "1.7.0")
]
```

## Extensibilité future

### Fonctionnalités potentielles
- Rappels et notifications
- Objectifs personnalisés
- Communauté anonyme (optionnel)
- Intégration avec des services de santé
- Support multi-utilisateurs
- Synchronisation chiffrée end-to-end (optionnel)

### Points d'extension
- Plugin système pour les types de produits
- Templates de graphiques personnalisables
- Thèmes d'interface
- Langues supplémentaires

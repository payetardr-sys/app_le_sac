# Changelog

Tous les changements notables de ce projet seront documentés dans ce fichier.

Le format est basé sur [Keep a Changelog](https://keepachangelog.com/fr/1.0.0/),
et ce projet adhère au [Semantic Versioning](https://semver.org/lang/fr/).

## [Unreleased]

### En cours de développement
- Interface utilisateur de base
- Écrans de saisie de consommation
- Implémentation complète du chiffrement
- Tests d'intégration

## [1.0.0-alpha] - 2024-10-01

### Ajouté
- Architecture complète du projet documentée
- Structure du projet Android et iOS
- Modèles de données pour Consumption, Product, et Mood
- Base de données SQLite avec schéma complet
- Configuration SQLCipher pour le chiffrement (Android)
- SecurityManager avec gestion du PIN et PBKDF2
- DAOs pour toutes les entités (ConsumptionDao, ProductDao, MoodDao)
- Repository pattern pour la couche de données
- Tests unitaires pour ConsumptionRepository
- Guides de réduction des risques (2 guides initiaux)
- Outil d'administration CLI pour les guides (Python)
- Configuration des flux RSS
- Documentation complète :
  - README avec vue d'ensemble
  - ARCHITECTURE avec détails techniques
  - DEVELOPMENT guide de développement
  - USER_GUIDE guide utilisateur
  - SECURITY politique de sécurité
  - CONTRIBUTING guide de contribution
  - QUICKSTART démarrage rapide
  - ROADMAP feuille de route
- Configuration Gradle et build system
- Licence MIT avec notice de réduction des risques
- .gitignore adapté au projet
- Schéma de base de données documenté
- Configuration de l'application (app-config.json)

### Sécurité
- Chiffrement de la base de données avec SQLCipher
- Hachage du PIN avec PBKDF2 (10000 itérations)
- Génération de salt unique par installation
- Protection contre les attaques par force brute (3 tentatives max)
- Verrouillage temporaire après échecs
- EncryptedSharedPreferences pour les données sensibles

### Documentation
- 8 fichiers de documentation créés
- 2 guides de réduction des risques
- Schéma de base de données détaillé
- Architecture en couches documentée
- Exemples de code pour Android et iOS

---

## Versions Futures Prévues

### [1.1.0] - Q3 2024
- Interface utilisateur complète
- Ajout et liste des consommations
- Filtres et recherche
- Statistiques de base
- Graphiques simples

### [1.2.0] - Q4 2024
- Corrélations humeur/consommation
- Analyses avancées
- Suivi d'abstinence
- Export CSV

### [1.3.0] - Q1 2025
- 10+ guides de réduction des risques
- Recherche dans les guides
- Contenu enrichi

### [1.4.0] - Q2 2025
- Flux RSS fonctionnel
- Actualités et informations
- Mode hors-ligne

### [1.5.0] - Q3 2025
- Export PDF avec graphiques
- Export JSON complet
- Backup et restauration

### [2.0.0] - Q4 2025
- Interface admin web
- Fonctionnalités premium
- Accessibilité complète

---

## Notes de Version

### Format
Chaque version suit le format X.Y.Z où :
- X = Version majeure (changements incompatibles)
- Y = Version mineure (nouvelles fonctionnalités)
- Z = Patch (corrections de bugs)

### Catégories
Les changements sont groupés ainsi :
- **Ajouté** : Nouvelles fonctionnalités
- **Modifié** : Changements de fonctionnalités existantes
- **Déprécié** : Fonctionnalités bientôt retirées
- **Retiré** : Fonctionnalités supprimées
- **Corrigé** : Corrections de bugs
- **Sécurité** : Vulnérabilités corrigées

---

**Légende des statuts** :
- [Unreleased] : En développement
- [X.Y.Z-alpha] : Version alpha (développement)
- [X.Y.Z-beta] : Version beta (tests)
- [X.Y.Z] : Version stable

**Dernière mise à jour** : 2024-10-01

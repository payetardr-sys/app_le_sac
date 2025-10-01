# Roadmap - Daylio SAC

## Vision

Créer une application mobile de suivi des consommations qui respecte profondément la vie privée des utilisateurs, tout en leur fournissant des outils puissants pour comprendre et gérer leurs habitudes.

## Principes Directeurs

1. **Confidentialité avant tout** : Données 100% locales, aucun tracking
2. **Non-jugement** : Approche pragmatique de réduction des risques
3. **Accessibilité** : Interface simple et inclusive
4. **Open Source** : Transparence et collaboration
5. **Qualité** : Tests, documentation, sécurité

---

## Version 1.0.0 (MVP) - Q2 2024

**Objectif** : Application fonctionnelle de base avec les features essentielles

### Fonctionnalités Core ✅

- [x] Architecture du projet définie
- [x] Documentation complète créée
- [x] Schéma de base de données conçu
- [x] Modèles de données implémentés (Android & iOS)
- [ ] Base de données SQLite chiffrée
  - [x] Configuration SQLCipher (Android)
  - [ ] Configuration SQLite chiffré (iOS)
  - [ ] Tests de chiffrement
- [ ] Gestion du PIN
  - [x] SecurityManager (Android)
  - [ ] SecurityManager (iOS)
  - [ ] Écran de configuration PIN
  - [ ] Écran de déverrouillage

### Suivi des Consommations

- [x] Modèles de données
- [x] DAOs (Android)
- [x] Repository pattern (Android)
- [ ] Interface d'ajout de consommation
- [ ] Liste des consommations
- [ ] Édition/suppression
- [ ] Filtres et recherche

### Gestion des Produits

- [x] Modèle de données
- [x] DAO (Android)
- [ ] Produits par défaut
- [ ] Ajout de produits personnalisés
- [ ] Catégorisation
- [ ] Archivage

### Suivi de l'Humeur

- [x] Modèle de données
- [x] Humeurs par défaut
- [ ] Sélection d'humeur
- [ ] Visualisation

### Interface Utilisateur

- [ ] Navigation principale
- [ ] Écran d'accueil
- [ ] Design system
- [ ] Thèmes (clair/sombre)
- [ ] Localisation (FR/EN)

### Tests et Qualité

- [x] Tests unitaires (Repository)
- [ ] Tests d'intégration (Database)
- [ ] Tests UI (flows critiques)
- [ ] Couverture >70%

---

## Version 1.1.0 - Q3 2024

**Objectif** : Statistiques et analyses de base

### Statistiques

- [ ] Calcul des métriques de base
  - [ ] Fréquence de consommation
  - [ ] Quantités totales/moyennes
  - [ ] Tendances temporelles
- [ ] Filtrage par période
- [ ] Filtrage par produit
- [ ] Export CSV de base

### Visualisations

- [ ] Graphiques linéaires
  - [ ] Évolution dans le temps
  - [ ] Tendances
- [ ] Graphiques en barres
  - [ ] Comparaison entre produits
  - [ ] Comparaison entre périodes
- [ ] Calendrier de consommation
  - [ ] Vue mensuelle
  - [ ] Marqueurs visuels

### Analyses

- [ ] Détection de patterns
- [ ] Jours de la semaine
- [ ] Heures de la journée
- [ ] Périodes à risque

---

## Version 1.2.0 - Q4 2024

**Objectif** : Corrélations et analyses avancées

### Corrélations Humeur/Consommation

- [ ] Analyse des corrélations
- [ ] Graphiques de corrélation
- [ ] Insights automatiques
- [ ] Recommandations personnalisées

### Suivi d'Abstinence

- [ ] Définition d'objectifs
- [ ] Compteur de jours
- [ ] Alertes de rechute
- [ ] Célébration des étapes

### Analyses Avancées

- [ ] Comparaisons multi-périodes
- [ ] Progression par rapport aux objectifs
- [ ] Identification des déclencheurs
- [ ] Rapports personnalisés

---

## Version 1.3.0 - Q1 2025

**Objectif** : Guides et contenu éducatif

### Guides de Réduction des Risques

- [x] Structure de guides créée
- [x] 2 guides initiaux rédigés
- [x] Outil d'administration
- [ ] 10+ guides complets
  - [ ] Par substance
  - [ ] Par situation
  - [ ] Aspects légaux
  - [ ] Ressources locales
- [ ] Recherche dans les guides
- [ ] Favoris
- [ ] Partage (anonyme)

### Contenu Dynamique

- [ ] Actualisation régulière
- [ ] Contributions communautaires
- [ ] Validation par experts
- [ ] Multi-langues

---

## Version 1.4.0 - Q2 2025

**Objectif** : RSS et actualités

### Flux RSS

- [ ] Configuration des flux
- [ ] Parsing et mise en cache
- [ ] Interface de lecture
- [ ] Marquage lu/non-lu
- [ ] Notifications optionnelles
- [ ] Mode hors-ligne complet

### Sources

- [ ] Flux officiels configurés
- [ ] Sources validées
- [ ] Filtrage par thème
- [ ] Recommandations personnalisées

---

## Version 1.5.0 - Q3 2025

**Objectif** : Export avancé et portabilité

### Export PDF

- [ ] Génération de rapports
- [ ] Inclusion de graphiques
- [ ] Templates personnalisables
- [ ] Protection par mot de passe
- [ ] Annotations

### Export CSV

- [x] Structure définie
- [ ] Implémentation complète
- [ ] Sélection des colonnes
- [ ] Formatage personnalisé

### Export JSON

- [x] Structure définie
- [ ] Export complet
- [ ] Import (restauration)
- [ ] Validation du schéma

### Backup & Restore

- [ ] Backup manuel complet
- [ ] Restauration
- [ ] Migration entre appareils
- [ ] Vérification d'intégrité

---

## Version 2.0.0 - Q4 2025

**Objectif** : Fonctionnalités avancées et communauté

### Interface Admin

- [x] Outils CLI créés
- [ ] Interface web locale
- [ ] Gestion des guides
- [ ] Configuration des flux RSS
- [ ] Analytics d'utilisation (anonyme)

### Fonctionnalités Premium (optionnelles)

- [ ] Rappels intelligents
- [ ] Analyses prédictives
- [ ] Coaching personnalisé
- [ ] Communauté anonyme

### Intégrations

- [ ] Export vers apps de santé
- [ ] Import de données existantes
- [ ] API pour professionnels (opt-in)

### Accessibilité

- [ ] Support complet lecteurs d'écran
- [ ] Ajustement de contraste
- [ ] Tailles de police
- [ ] Navigation au clavier
- [ ] Mode daltonisme

---

## Backlog (Futur)

### Fonctionnalités Envisagées

- [ ] Widget pour écran d'accueil
- [ ] Apple Watch / Wear OS
- [ ] Mode multi-utilisateurs
- [ ] Synchronisation P2P chiffrée
- [ ] Partage anonyme de stats (recherche)
- [ ] Gamification positive
- [ ] Journal de gratitude intégré
- [ ] Méditation et relaxation
- [ ] Groupes de soutien anonymes

### Améliorations Techniques

- [ ] Migration vers Kotlin Multiplatform
- [ ] Refonte en Jetpack Compose
- [ ] SwiftUI pour iOS
- [ ] CI/CD complet
- [ ] Tests automatisés (E2E)
- [ ] Performance optimization
- [ ] Réduction de la taille de l'app

### Sécurité

- [ ] Audit de sécurité tiers
- [ ] Certification ISO 27001
- [ ] Bug bounty program
- [ ] Authentification biométrique
- [ ] Mode panique (effacement rapide)

---

## Critères de Succès

### Techniques
- ✅ Architecture solide et maintenable
- ✅ Documentation complète
- 🔄 Couverture de tests >80%
- 🔄 Aucun bug critique
- ✅ Performance optimale
- ✅ Sécurité renforcée

### Utilisateur
- 📊 Facilité d'utilisation (score >4/5)
- 📊 Adoption par 1000+ utilisateurs (v1.0)
- 📊 Retention >60% à 30 jours
- 📊 Satisfaction >4.5/5
- 📊 Moins de 5% de désinstallations

### Communauté
- 🔄 10+ contributeurs actifs
- 📊 100+ stars GitHub
- 🔄 50+ issues/PRs traités
- 🔄 Documentation traduite en 3 langues

### Impact
- 📊 Retours positifs d'utilisateurs
- 📊 Recommandations par professionnels
- 📊 Études de cas documentées
- 📊 Réduction des risques mesurable

---

## Comment Contribuer

### Priorités Actuelles (V1.0)

1. **Implémentation du chiffrement de la base de données**
2. **Interfaces utilisateur de base**
3. **Tests unitaires et d'intégration**
4. **Documentation technique**
5. **Guides de réduction des risques**

### Compétences Recherchées

- **Développeurs Android** (Kotlin, Room, Jetpack)
- **Développeurs iOS** (Swift, SwiftUI)
- **Designers UI/UX** (Figma, Sketch)
- **Rédacteurs** (Guides RdR, documentation)
- **Testeurs** (QA, accessibilité)
- **Traducteurs** (EN, ES, DE, AR)

### Comment S'impliquer

1. Consulter [CONTRIBUTING.md](CONTRIBUTING.md)
2. Parcourir les [issues GitHub](https://github.com/payetardr-sys/app_le_sac/issues)
3. Rejoindre les [discussions](https://github.com/payetardr-sys/app_le_sac/discussions)
4. Proposer de nouvelles idées

---

## Notes

**Ce roadmap est évolutif** et sera mis à jour régulièrement en fonction :
- Des retours utilisateurs
- Des contributions de la communauté
- Des évolutions technologiques
- Des besoins identifiés

**Légende** :
- ✅ Complété
- 🔄 En cours
- 📊 À mesurer
- [ ] À faire

---

**Dernière mise à jour** : 2024-10-01
**Version du document** : 1.0

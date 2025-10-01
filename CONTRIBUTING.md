# Guide de Contribution - Daylio SAC

Merci de votre intérêt pour contribuer à Daylio SAC ! Ce projet vise à aider les personnes en situation de consommation par un outil de suivi respectueux de leur vie privée.

## Code de Conduite

### Nos Valeurs
- **Bienveillance** : Respect de tous les contributeurs
- **Non-jugement** : Pas de moralisation sur les consommations
- **Confidentialité** : Protection des données utilisateurs prioritaire
- **Inclusivité** : Ouvert à tous, quels que soient les compétences
- **Pragmatisme** : Solutions pratiques avant tout

### Comportements Attendus
- ✅ Respecter les opinions divergentes
- ✅ Accepter les critiques constructives
- ✅ Se concentrer sur l'aide aux utilisateurs
- ✅ Faire preuve d'empathie

### Comportements Inacceptables
- ❌ Jugement sur les consommations
- ❌ Discrimination de quelque nature
- ❌ Harcèlement ou insultes
- ❌ Divulgation d'informations privées

## Comment Contribuer

### Types de Contributions

#### 1. Signalement de Bugs
**Avant de créer une issue :**
- Vérifier qu'elle n'existe pas déjà
- Tester sur la dernière version
- Rassembler les informations nécessaires

**Template d'issue :**
```markdown
**Description du bug**
Description claire et concise.

**Étapes pour reproduire**
1. Aller à '...'
2. Cliquer sur '...'
3. Faire défiler jusqu'à '...'
4. Voir l'erreur

**Comportement attendu**
Ce qui devrait se passer.

**Captures d'écran**
Si applicable.

**Environnement**
- OS : [ex: Android 12]
- Version de l'app : [ex: 1.0.0]
- Appareil : [ex: Samsung Galaxy S21]
```

#### 2. Suggestion de Fonctionnalités
**Avant de suggérer :**
- Vérifier que ça n'existe pas
- S'assurer que c'est aligné avec la vision
- Considérer l'impact sur la confidentialité

**Template :**
```markdown
**Problème à résoudre**
Description du besoin utilisateur.

**Solution proposée**
Description de la fonctionnalité.

**Alternatives considérées**
Autres approches envisagées.

**Impact sur la confidentialité**
Comment cela affecte les données.
```

#### 3. Amélioration de la Documentation
- Corriger les fautes
- Clarifier les instructions
- Ajouter des exemples
- Traduire (autres langues)

#### 4. Code
- Corriger des bugs
- Implémenter des fonctionnalités
- Améliorer les performances
- Refactoring

#### 5. Design
- Améliorer l'UX
- Créer des maquettes
- Proposer des icônes
- Tester l'accessibilité

#### 6. Guides de Réduction des Risques
- Rédiger de nouveaux guides
- Mettre à jour l'existant
- Vérifier les sources
- Traduire

## Processus de Contribution

### 1. Fork et Clone
```bash
# Fork via l'interface GitHub
git clone https://github.com/VOTRE-USERNAME/app_le_sac.git
cd app_le_sac
git remote add upstream https://github.com/payetardr-sys/app_le_sac.git
```

### 2. Créer une Branche
```bash
git checkout -b feature/ma-fonctionnalite
# ou
git checkout -b fix/mon-correctif
# ou
git checkout -b docs/ma-documentation
```

**Convention de nommage :**
- `feature/` : Nouvelle fonctionnalité
- `fix/` : Correction de bug
- `docs/` : Documentation
- `refactor/` : Refactoring
- `test/` : Tests
- `chore/` : Maintenance

### 3. Développer
- Suivre les conventions de code
- Écrire des tests si applicable
- Documenter le code
- Tester localement

### 4. Committer
```bash
git add .
git commit -m "type: description courte

Description plus détaillée si nécessaire.

Closes #123"
```

**Types de commit :**
- `feat:` Nouvelle fonctionnalité
- `fix:` Correction de bug
- `docs:` Documentation
- `style:` Formatage (pas de changement de code)
- `refactor:` Refactoring
- `test:` Ajout/modification de tests
- `chore:` Maintenance

### 5. Pousser et Pull Request
```bash
git push origin feature/ma-fonctionnalite
```

Puis créer une Pull Request via GitHub avec :
- Titre descriptif
- Description détaillée
- Référence aux issues
- Captures d'écran si UI

### 6. Revue de Code
- Répondre aux commentaires
- Faire les modifications demandées
- Être ouvert aux suggestions
- Apprendre et s'améliorer

## Standards de Code

### Android (Kotlin)

#### Style
```kotlin
// Classes : PascalCase
class ConsumptionViewModel { }

// Fonctions et variables : camelCase
fun calculateStatistics() { }
val userName: String

// Constantes : UPPER_SNAKE_CASE
const val MAX_ATTEMPTS = 3

// Indentation : 4 espaces
fun example() {
    if (condition) {
        // code
    }
}
```

#### Documentation
```kotlin
/**
 * Calcule les statistiques pour une période donnée.
 *
 * @param startDate Date de début (ISO 8601)
 * @param endDate Date de fin (ISO 8601)
 * @return Objet Statistics contenant les métriques
 */
fun calculateStatistics(startDate: String, endDate: String): Statistics {
    // implémentation
}
```

#### Tests
```kotlin
@Test
fun `calculateStatistics should return correct data for valid period`() {
    // Given
    val startDate = "2024-01-01T00:00:00"
    val endDate = "2024-01-31T23:59:59"
    
    // When
    val result = calculator.calculateStatistics(startDate, endDate)
    
    // Then
    assertEquals(expectedValue, result.totalConsumptions)
}
```

### iOS (Swift)

#### Style
```swift
// Types : PascalCase
class ConsumptionViewModel { }

// Fonctions et variables : camelCase
func calculateStatistics() { }
let userName: String

// Constantes : camelCase
let maxAttempts = 3

// Indentation : 4 espaces
func example() {
    if condition {
        // code
    }
}
```

#### Documentation
```swift
/// Calcule les statistiques pour une période donnée.
///
/// - Parameters:
///   - startDate: Date de début
///   - endDate: Date de fin
/// - Returns: Objet Statistics contenant les métriques
func calculateStatistics(startDate: Date, endDate: Date) -> Statistics {
    // implémentation
}
```

#### Tests
```swift
func testCalculateStatistics_ValidPeriod_ReturnsCorrectData() {
    // Given
    let startDate = Date()
    let endDate = Date()
    
    // When
    let result = calculator.calculateStatistics(startDate: startDate, endDate: endDate)
    
    // Then
    XCTAssertEqual(expectedValue, result.totalConsumptions)
}
```

### Documentation (Markdown)

```markdown
# Titre Principal

## Section

Description claire et concise.

### Sous-section

- Liste à puces
- Bien structurée

**Important** : Texte important.

`code inline` pour les commandes.

\`\`\`language
bloc de code
\`\`\`
```

## Sécurité

### Règles Critiques
1. **Jamais de données en clair** dans le code
2. **Jamais de clés** commitées
3. **Toujours chiffrer** les données sensibles
4. **Valider** toutes les entrées utilisateur
5. **HTTPS uniquement** pour les requêtes réseau

### Revue de Sécurité
Toute PR touchant à :
- Chiffrement
- Authentification
- Stockage de données
- Réseau

Nécessite une revue sécurité approfondie.

## Tests

### Tests Requis
- **Tests unitaires** : Logique métier
- **Tests d'intégration** : Base de données
- **Tests UI** : Flows utilisateur critiques

### Exécution
```bash
# Android
cd android
./gradlew test

# iOS
cd ios
xcodebuild test -scheme DaylioSAC
```

### Couverture
- Objectif : >80% pour le code critique
- Vérifier avant la PR
- Pas de régression de couverture

## Documentation

### À Documenter
- Nouvelles fonctionnalités
- APIs publiques
- Configurations complexes
- Guides utilisateur (si impact UX)

### Où
- `shared/docs/` : Documentation technique
- `README.md` : Vue d'ensemble
- Code : Commentaires inline
- Commits : Messages descriptifs

## Processus de Revue

### Checklist du Reviewer

**Code**
- [ ] Suit les conventions
- [ ] Tests présents et passants
- [ ] Pas de régression
- [ ] Performance acceptable
- [ ] Sécurité respectée

**Documentation**
- [ ] Code documenté
- [ ] Changelog mis à jour
- [ ] README à jour si nécessaire

**UX**
- [ ] Interface intuitive
- [ ] Messages d'erreur clairs
- [ ] Accessibilité considérée

### Critères d'Acceptation
- ✅ Au moins 1 approbation
- ✅ Tous les tests passent
- ✅ Pas de conflit
- ✅ Documentation à jour
- ✅ Revue de sécurité (si applicable)

## Communication

### Où Discuter
- **GitHub Issues** : Bugs et fonctionnalités
- **GitHub Discussions** : Questions générales
- **Pull Requests** : Revues de code
- **Email** : contact@dayliosac.app (à configurer)

### Langue
- Français : Préféré
- Anglais : Accepté

## Reconnaissance

### Hall of Fame
Les contributeurs sont crédités dans :
- `CONTRIBUTORS.md`
- Notes de version
- À propos de l'application

### Licence
En contribuant, vous acceptez que votre code soit sous la licence du projet (à définir).

## Questions ?

N'hésitez pas à :
- Ouvrir une issue
- Demander de l'aide
- Proposer des améliorations à ce guide

**Merci de contribuer à Daylio SAC !** 💚

---

**Version** : 1.0.0
**Dernière mise à jour** : 2024

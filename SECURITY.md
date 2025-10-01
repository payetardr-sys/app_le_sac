# Politique de Sécurité - Daylio SAC

## Notre Engagement

La confidentialité et la sécurité des données des utilisateurs sont au cœur de Daylio SAC. Cette application a été conçue avec une approche "confidentialité par conception" (Privacy by Design).

## Principes Fondamentaux

### 1. Données Locales Uniquement
- **Aucune synchronisation cloud** : Toutes les données restent sur l'appareil de l'utilisateur
- **Aucun serveur** : Pas de transmission de données personnelles
- **Aucun compte** : Pas d'authentification externe requise
- **Contrôle total** : L'utilisateur garde le contrôle absolu de ses données

### 2. Chiffrement Renforcé
- **Base de données chiffrée** : SQLCipher avec AES-256
- **Clé dérivée du PIN** : Protection par code personnel
- **Pas de données en clair** : Aucune information sensible non chiffrée

### 3. Protection par PIN
- **Accès sécurisé** : Code PIN à 4-6 chiffres requis
- **Hachage PBKDF2** : 10 000 itérations avec salt unique
- **Tentatives limitées** : Verrouillage après 3 échecs
- **Pas de récupération** : Sécurité maximale, pas de porte dérobée

### 4. Aucun Tracking
- **Pas d'analytics** : Aucune collecte de données d'usage
- **Pas de crash reports** : Aucun envoi automatique d'erreurs
- **Pas de publicité** : Aucun tracker publicitaire
- **Pas de fingerprinting** : Aucune identification de l'appareil

## Fonctionnalités Optionnelles

### Flux RSS
- **Optionnel** : Peut être désactivé complètement
- **Anonyme** : Aucune donnée personnelle transmise
- **Read-only** : Téléchargement d'articles publics uniquement
- **Pas de tracking** : Aucun suivi de lecture

### Export de Données
- **Contrôlé par l'utilisateur** : Export manuel uniquement
- **Local** : Fichiers sauvegardés sur l'appareil
- **Pas de cloud automatique** : Aucun upload automatique
- **Choix du format** : PDF, CSV ou JSON selon les besoins

## Mesures de Sécurité Technique

### Stockage
- **SQLCipher** : Bibliothèque de chiffrement reconnue
- **AES-256** : Standard militaire de chiffrement
- **Salt unique** : Différent pour chaque installation
- **Clé dérivée** : PBKDF2 avec nombreuses itérations

### Code
- **Auditable** : Code source disponible (open source prévu)
- **Bonnes pratiques** : Développement sécurisé
- **Dépendances vérifiées** : Bibliothèques de confiance uniquement
- **Mises à jour régulières** : Corrections de sécurité

### Réseau
- **HTTPS uniquement** : Pour les flux RSS optionnels
- **Pas de cookies** : Aucun mécanisme de session
- **Pas de requêtes cachées** : Transparence totale
- **Firewall friendly** : Fonctionne sans connexion Internet

### Appareil
- **Sandboxing** : Isolation de l'application
- **Permissions minimales** : Seulement le nécessaire
- **Pas de root/jailbreak detection** : Respect de la liberté utilisateur
- **Verrouillage auto** : Protection en cas d'oubli

## Ce Que Nous NE Faisons PAS

### ❌ Collecte de Données
- Pas de collecte d'informations personnelles
- Pas de profilage utilisateur
- Pas d'analyse comportementale
- Pas de géolocalisation

### ❌ Transmission de Données
- Pas de serveur central
- Pas de synchronisation cloud
- Pas d'envoi à des tiers
- Pas de backup automatique externe

### ❌ Tracking
- Pas de Google Analytics
- Pas de Firebase Analytics
- Pas de trackers publicitaires
- Pas de pixels de suivi

### ❌ Monétisation
- Pas de publicité
- Pas de vente de données
- Pas d'abonnement avec tracking
- Pas de micro-transactions intrusives

## Menaces et Protections

### Perte/Vol de l'Appareil
**Risque** : Accès physique aux données
**Protection** :
- Chiffrement de la base de données
- Code PIN requis
- Tentatives limitées
- Données illisibles sans PIN

### Malware/Virus
**Risque** : Accès par logiciel malveillant
**Protection** :
- Sandboxing du système d'exploitation
- Chiffrement des données
- Permissions minimales
- Pas d'API externe exposée

### Backup Cloud Automatique
**Risque** : Données dans le cloud de l'OS
**Protection** :
- Backup désactivé par configuration
- Données chiffrées même si backup
- Utilisateur averti dans la doc
- Option d'export contrôlé

### Man-in-the-Middle
**Risque** : Interception réseau
**Protection** :
- HTTPS uniquement (RSS)
- Pas de données sensibles transmises
- Fonctionnement hors ligne possible
- Certificats vérifiés

## Conformité

### RGPD (Europe)
- ✅ Données minimales : Seulement le nécessaire
- ✅ Consentement : Transparent et explicite
- ✅ Droit à l'effacement : Désinstallation = suppression
- ✅ Portabilité : Export des données facile
- ✅ Sécurité : Chiffrement et protection

### Législation Française
- ✅ Confidentialité médicale respectée
- ✅ Pas de déclaration CNIL nécessaire (données locales)
- ✅ Pas de serveur = pas de responsabilité hébergeur
- ✅ Utilisateur propriétaire de ses données

## Recommandations aux Utilisateurs

### Pour Maximiser la Sécurité

1. **Code PIN fort**
   - Au moins 6 chiffres
   - Pas de date de naissance
   - Différent des autres codes
   - Mémorisable mais pas évident

2. **Exports réguliers**
   - Backup hebdomadaire
   - Stockage sécurisé externe
   - Chiffrement des exports (optionnel)
   - Test de restauration

3. **Mise à jour de l'app**
   - Installer les mises à jour
   - Corrections de sécurité
   - Nouvelles protections
   - Améliorations

4. **Protection de l'appareil**
   - Verrouillage de l'écran
   - Antivirus à jour
   - Pas d'apps suspectes
   - Sources officielles uniquement

5. **Vigilance**
   - Ne pas prêter l'appareil déverrouillé
   - Verrouiller après usage
   - Pas de capture d'écran sensible
   - Désinstaller proprement si abandon

## Signalement de Vulnérabilités

### Processus de Divulgation Responsable

Si vous découvrez une vulnérabilité de sécurité :

1. **NE PAS** la divulguer publiquement
2. **Contacter** : [security@dayliosac.app] (à définir)
3. **Décrire** : Le problème en détail
4. **Attendre** : Notre réponse sous 48h
5. **Collaborer** : Pour la correction

### Récompenses
- Reconnaissance publique (optionnel)
- Crédit dans les notes de version
- Notre gratitude sincère

## Audits et Certifications

### Audits Prévus
- [ ] Audit de code par tiers indépendant
- [ ] Pentest de l'application
- [ ] Revue cryptographique
- [ ] Certification ISO 27001 (objectif)

### Transparence
- Code source open source (prévu)
- Documentation complète
- Architecture publique
- Process de build reproductible

## Mises à Jour de Cette Politique

Cette politique de sécurité peut être mise à jour pour refléter :
- Nouvelles fonctionnalités
- Améliorations de sécurité
- Feedback utilisateurs
- Évolution des menaces

**Version actuelle** : 1.0.0
**Dernière mise à jour** : 2024

## Contact

Pour toute question sur la sécurité :
- **Email** : security@dayliosac.app (à configurer)
- **Documentation** : [GitHub Repository]
- **Issues** : GitHub Issues pour questions non sensibles

---

**Notre promesse** : Vos données vous appartiennent. Nous ne les voyons jamais, ne pouvons jamais les voir, et ne voulons jamais les voir. Votre confiance est notre priorité absolue.

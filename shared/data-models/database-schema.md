# Schéma de Base de Données - Daylio SAC

## Vue d'ensemble

La base de données est chiffrée avec SQLCipher et utilise SQLite comme moteur sous-jacent.

## Schéma Complet

### Table : consumptions
Enregistre chaque consommation de substance.

```sql
CREATE TABLE consumptions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    product_id INTEGER NOT NULL,
    quantity REAL NOT NULL,
    unit TEXT NOT NULL,
    datetime TEXT NOT NULL,  -- Format ISO 8601: YYYY-MM-DDTHH:MM:SS
    mood_id INTEGER,
    note TEXT,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (mood_id) REFERENCES moods(id) ON DELETE SET NULL
);

CREATE INDEX idx_consumptions_datetime ON consumptions(datetime);
CREATE INDEX idx_consumptions_product_id ON consumptions(product_id);
CREATE INDEX idx_consumptions_mood_id ON consumptions(mood_id);
```

**Colonnes :**
- `id` : Identifiant unique
- `product_id` : Référence au produit consommé
- `quantity` : Quantité consommée (décimal)
- `unit` : Unité de mesure (ml, g, unité, etc.)
- `datetime` : Date et heure de la consommation
- `mood_id` : Référence à l'humeur du moment (optionnel)
- `note` : Note personnelle (optionnel)
- `created_at` : Date de création de l'enregistrement
- `updated_at` : Date de dernière modification

---

### Table : products
Catalogue des produits/substances.

```sql
CREATE TABLE products (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    category TEXT NOT NULL,  -- alcool, tabac, cannabis, autre
    default_unit TEXT,
    icon TEXT,  -- Nom de l'icône ou emoji
    color TEXT,  -- Code couleur hexadécimal
    description TEXT,
    is_active INTEGER DEFAULT 1,  -- 0 = archivé, 1 = actif
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX idx_products_name ON products(name);
CREATE INDEX idx_products_category ON products(category);
```

**Colonnes :**
- `id` : Identifiant unique
- `name` : Nom du produit
- `category` : Catégorie (pour le regroupement)
- `default_unit` : Unité par défaut
- `icon` : Icône ou emoji représentatif
- `color` : Couleur pour les graphiques
- `description` : Description ou notes
- `is_active` : Actif ou archivé
- `created_at` : Date de création
- `updated_at` : Date de modification

---

### Table : moods
Échelle d'humeur standardisée.

```sql
CREATE TABLE moods (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    level INTEGER NOT NULL CHECK(level BETWEEN 1 AND 5),
    label TEXT NOT NULL,
    emoji TEXT,
    color TEXT
);

-- Données initiales
INSERT INTO moods (level, label, emoji, color) VALUES
    (1, 'Très mauvais', '😞', '#FF0000'),
    (2, 'Mauvais', '😟', '#FF8800'),
    (3, 'Neutre', '😐', '#FFDD00'),
    (4, 'Bon', '🙂', '#88FF00'),
    (5, 'Excellent', '😊', '#00FF00');
```

**Colonnes :**
- `id` : Identifiant unique
- `level` : Niveau d'humeur (1-5)
- `label` : Libellé descriptif
- `emoji` : Emoji représentatif
- `color` : Couleur associée

---

### Table : guides
Guides de réduction des risques.

```sql
CREATE TABLE guides (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    category TEXT NOT NULL,
    content TEXT NOT NULL,  -- Contenu en Markdown
    author TEXT,
    source TEXT,
    tags TEXT,  -- Liste séparée par des virgules
    order_index INTEGER DEFAULT 0,
    is_published INTEGER DEFAULT 1,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_guides_category ON guides(category);
CREATE INDEX idx_guides_published ON guides(is_published);
```

**Colonnes :**
- `id` : Identifiant unique
- `title` : Titre du guide
- `category` : Catégorie du guide
- `content` : Contenu formaté en Markdown
- `author` : Auteur du guide
- `source` : Source d'information
- `tags` : Mots-clés pour la recherche
- `order_index` : Ordre d'affichage
- `is_published` : Publié ou brouillon
- `created_at` : Date de création
- `updated_at` : Date de modification

---

### Table : rss_feeds
Configuration des flux RSS.

```sql
CREATE TABLE rss_feeds (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    url TEXT NOT NULL UNIQUE,
    title TEXT NOT NULL,
    description TEXT,
    enabled INTEGER DEFAULT 1,
    update_frequency INTEGER DEFAULT 3600,  -- Secondes
    last_update TEXT,
    last_error TEXT,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_rss_feeds_enabled ON rss_feeds(enabled);
```

**Colonnes :**
- `id` : Identifiant unique
- `url` : URL du flux RSS
- `title` : Titre du flux
- `description` : Description
- `enabled` : Activé ou désactivé
- `update_frequency` : Fréquence de mise à jour (en secondes)
- `last_update` : Dernière mise à jour réussie
- `last_error` : Dernière erreur rencontrée
- `created_at` : Date de création

---

### Table : rss_items
Articles des flux RSS (cache local).

```sql
CREATE TABLE rss_items (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    feed_id INTEGER NOT NULL,
    title TEXT NOT NULL,
    link TEXT NOT NULL,
    description TEXT,
    pub_date TEXT,
    is_read INTEGER DEFAULT 0,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (feed_id) REFERENCES rss_feeds(id) ON DELETE CASCADE
);

CREATE INDEX idx_rss_items_feed_id ON rss_items(feed_id);
CREATE INDEX idx_rss_items_pub_date ON rss_items(pub_date);
CREATE INDEX idx_rss_items_is_read ON rss_items(is_read);
```

**Colonnes :**
- `id` : Identifiant unique
- `feed_id` : Référence au flux RSS
- `title` : Titre de l'article
- `link` : URL de l'article
- `description` : Description/résumé
- `pub_date` : Date de publication
- `is_read` : Lu ou non lu
- `created_at` : Date d'ajout en cache

---

### Table : settings
Paramètres de l'application.

```sql
CREATE TABLE settings (
    key TEXT PRIMARY KEY,
    value TEXT NOT NULL,
    type TEXT DEFAULT 'string',  -- string, integer, boolean, json
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP
);

-- Paramètres par défaut
INSERT INTO settings (key, value, type) VALUES
    ('pin_enabled', 'true', 'boolean'),
    ('auto_lock_timeout', '60', 'integer'),
    ('theme', 'light', 'string'),
    ('language', 'fr', 'string'),
    ('rss_enabled', 'true', 'boolean'),
    ('export_include_notes', 'true', 'boolean'),
    ('statistics_cache_days', '30', 'integer'),
    ('notification_enabled', 'false', 'boolean');
```

**Colonnes :**
- `key` : Clé du paramètre (unique)
- `value` : Valeur du paramètre
- `type` : Type de la valeur
- `updated_at` : Date de modification

---

### Table : statistics_cache
Cache des statistiques calculées.

```sql
CREATE TABLE statistics_cache (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cache_key TEXT NOT NULL UNIQUE,
    data TEXT NOT NULL,  -- JSON stringifié
    expires_at TEXT NOT NULL,
    created_at TEXT DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_statistics_cache_expires ON statistics_cache(expires_at);
```

**Colonnes :**
- `id` : Identifiant unique
- `cache_key` : Clé unique du cache
- `data` : Données JSON stringifiées
- `expires_at` : Date d'expiration
- `created_at` : Date de création

---

### Table : abstinence_periods
Périodes d'abstinence trackées.

```sql
CREATE TABLE abstinence_periods (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    product_id INTEGER,  -- NULL pour tous les produits
    start_date TEXT NOT NULL,
    end_date TEXT,  -- NULL si période en cours
    goal_days INTEGER,
    notes TEXT,
    status TEXT DEFAULT 'active',  -- active, completed, failed
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    updated_at TEXT DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE INDEX idx_abstinence_product_id ON abstinence_periods(product_id);
CREATE INDEX idx_abstinence_status ON abstinence_periods(status);
```

**Colonnes :**
- `id` : Identifiant unique
- `product_id` : Produit concerné (NULL = tous)
- `start_date` : Date de début
- `end_date` : Date de fin (NULL si en cours)
- `goal_days` : Objectif en jours
- `notes` : Notes personnelles
- `status` : Statut (active, completed, failed)
- `created_at` : Date de création
- `updated_at` : Date de modification

---

## Vues SQL

### Vue : consumption_details
Vue enrichie des consommations avec tous les détails.

```sql
CREATE VIEW consumption_details AS
SELECT 
    c.id,
    c.quantity,
    c.unit,
    c.datetime,
    c.note,
    p.name as product_name,
    p.category as product_category,
    p.color as product_color,
    p.icon as product_icon,
    m.level as mood_level,
    m.label as mood_label,
    m.emoji as mood_emoji
FROM consumptions c
LEFT JOIN products p ON c.product_id = p.id
LEFT JOIN moods m ON c.mood_id = m.id
ORDER BY c.datetime DESC;
```

### Vue : daily_statistics
Statistiques journalières agrégées.

```sql
CREATE VIEW daily_statistics AS
SELECT 
    date(datetime) as date,
    product_id,
    COUNT(*) as consumption_count,
    SUM(quantity) as total_quantity,
    AVG(quantity) as avg_quantity,
    MIN(datetime) as first_consumption,
    MAX(datetime) as last_consumption
FROM consumptions
GROUP BY date(datetime), product_id;
```

---

## Requêtes Utiles

### Statistiques de période
```sql
SELECT 
    p.name,
    COUNT(c.id) as count,
    SUM(c.quantity) as total,
    AVG(c.quantity) as average
FROM consumptions c
JOIN products p ON c.product_id = p.id
WHERE c.datetime >= ? AND c.datetime <= ?
GROUP BY p.id, p.name
ORDER BY count DESC;
```

### Corrélation humeur/consommation
```sql
SELECT 
    m.label as mood,
    COUNT(c.id) as consumption_count,
    AVG(c.quantity) as avg_quantity
FROM consumptions c
JOIN moods m ON c.mood_id = m.id
WHERE c.datetime >= ? AND c.datetime <= ?
GROUP BY m.id, m.label
ORDER BY m.level;
```

### Jours d'abstinence consécutifs
```sql
WITH RECURSIVE dates(date) AS (
    SELECT date('now', '-30 days')
    UNION ALL
    SELECT date(date, '+1 day')
    FROM dates
    WHERE date < date('now')
)
SELECT 
    COUNT(*) as consecutive_days
FROM dates
WHERE date NOT IN (
    SELECT DISTINCT date(datetime)
    FROM consumptions
    WHERE product_id = ?
);
```

### Progression mensuelle
```sql
SELECT 
    strftime('%Y-%m', datetime) as month,
    COUNT(*) as total_consumptions,
    SUM(quantity) as total_quantity
FROM consumptions
WHERE product_id = ?
GROUP BY month
ORDER BY month;
```

---

## Migration et Versions

### Version 1.0.0
- Schéma initial avec tables principales
- Index de base pour les performances

### Migrations futures
À implémenter selon les besoins :
- Ajouter des colonnes
- Créer de nouvelles tables
- Modifier les index

---

## Backup et Restore

### Export complet
```sql
-- Exporter toutes les données
SELECT * FROM consumptions;
SELECT * FROM products;
SELECT * FROM moods;
-- etc.
```

### Import
```sql
-- Importer des données depuis un export JSON
-- (à implémenter dans le code de l'application)
```

---

## Optimisations

### Index recommandés
Tous les index nécessaires sont déjà créés dans le schéma.

### Maintenance
```sql
-- Nettoyer le cache expiré
DELETE FROM statistics_cache WHERE expires_at < datetime('now');

-- Nettoyer les anciens articles RSS
DELETE FROM rss_items WHERE created_at < datetime('now', '-30 days');

-- Vacuum (compacter la base de données)
VACUUM;

-- Analyser (mettre à jour les statistiques)
ANALYZE;
```

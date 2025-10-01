#!/usr/bin/env python3
"""
Script d'administration pour Daylio SAC
Gestion des guides de réduction des risques
"""

import json
import os
import sys
from datetime import datetime
from pathlib import Path

GUIDES_DIR = Path(__file__).parent.parent.parent / "shared" / "guides"
DATA_MODELS_DIR = Path(__file__).parent.parent.parent / "shared" / "data-models"


def list_guides():
    """Liste tous les guides disponibles"""
    print("\n=== Guides de Réduction des Risques ===\n")
    
    guides = sorted(GUIDES_DIR.glob("*.md"))
    
    if not guides:
        print("Aucun guide trouvé.")
        return
    
    for guide in guides:
        print(f"📄 {guide.name}")
        # Lire la première ligne pour afficher le titre
        with open(guide, 'r', encoding='utf-8') as f:
            first_line = f.readline().strip()
            if first_line.startswith('#'):
                print(f"   {first_line.replace('#', '').strip()}")
        print()


def validate_guide(guide_path):
    """Valide le format d'un guide"""
    try:
        with open(guide_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # Vérifications basiques
        checks = {
            "A un titre (# )": content.startswith('#'),
            "Contient du contenu": len(content) > 100,
            "Pas de lignes trop longues": all(len(line) < 120 for line in content.split('\n')),
        }
        
        print(f"\n=== Validation de {guide_path.name} ===\n")
        
        all_valid = True
        for check, result in checks.items():
            status = "✅" if result else "❌"
            print(f"{status} {check}")
            if not result:
                all_valid = False
        
        if all_valid:
            print("\n✅ Guide valide!")
        else:
            print("\n⚠️  Certaines vérifications ont échoué.")
        
        return all_valid
        
    except Exception as e:
        print(f"❌ Erreur lors de la validation: {e}")
        return False


def create_guide_template(title, category):
    """Crée un nouveau guide à partir d'un template"""
    
    # Créer un nom de fichier à partir du titre
    filename = f"{category.lower().replace(' ', '-')}-{title.lower().replace(' ', '-')}.md"
    filepath = GUIDES_DIR / filename
    
    if filepath.exists():
        print(f"❌ Le fichier {filename} existe déjà.")
        return False
    
    template = f"""# Guide - {title}

## Introduction

[Introduction du guide]

## Objectifs

- Objectif 1
- Objectif 2
- Objectif 3

## Contenu Principal

### Section 1

[Contenu]

### Section 2

[Contenu]

## Points Clés à Retenir

- Point 1
- Point 2
- Point 3

## Ressources

- [Ressource 1](URL)
- [Ressource 2](URL)

---

**Dernière mise à jour** : {datetime.now().strftime('%Y-%m-%d')}
**Catégorie** : {category}
**Auteur** : [Nom de l'auteur]
"""

    try:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(template)
        
        print(f"✅ Guide créé: {filename}")
        print(f"📝 Éditez le fichier: {filepath}")
        return True
        
    except Exception as e:
        print(f"❌ Erreur lors de la création: {e}")
        return False


def export_guides_json():
    """Exporte tous les guides au format JSON pour l'application"""
    
    guides_data = []
    
    for guide_file in sorted(GUIDES_DIR.glob("*.md")):
        with open(guide_file, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # Extraire les métadonnées du guide
        lines = content.split('\n')
        title = lines[0].replace('#', '').strip() if lines else guide_file.stem
        
        # Chercher les métadonnées en bas du fichier
        category = "Général"
        author = ""
        
        for line in reversed(lines[-10:]):
            if "**Catégorie**" in line:
                category = line.split(':')[1].strip()
            if "**Auteur**" in line:
                author = line.split(':')[1].strip()
        
        guide_data = {
            "id": len(guides_data) + 1,
            "filename": guide_file.name,
            "title": title,
            "category": category,
            "content": content,
            "author": author,
            "created_at": datetime.now().isoformat(),
            "updated_at": datetime.now().isoformat()
        }
        
        guides_data.append(guide_data)
    
    # Exporter en JSON
    output_file = DATA_MODELS_DIR / "guides-export.json"
    
    try:
        with open(output_file, 'w', encoding='utf-8') as f:
            json.dump(guides_data, f, ensure_ascii=False, indent=2)
        
        print(f"✅ {len(guides_data)} guides exportés vers: {output_file}")
        return True
        
    except Exception as e:
        print(f"❌ Erreur lors de l'export: {e}")
        return False


def show_stats():
    """Affiche des statistiques sur les guides"""
    
    guides = list(GUIDES_DIR.glob("*.md"))
    
    print("\n=== Statistiques des Guides ===\n")
    print(f"📊 Nombre total de guides: {len(guides)}")
    
    total_words = 0
    total_lines = 0
    
    categories = {}
    
    for guide in guides:
        with open(guide, 'r', encoding='utf-8') as f:
            content = f.read()
            lines = content.split('\n')
            words = len(content.split())
            
            total_lines += len(lines)
            total_words += words
            
            # Extraire la catégorie
            for line in reversed(lines[-10:]):
                if "**Catégorie**" in line:
                    cat = line.split(':')[1].strip()
                    categories[cat] = categories.get(cat, 0) + 1
    
    print(f"📝 Total de lignes: {total_lines}")
    print(f"📖 Total de mots: {total_words}")
    print(f"📄 Moyenne de mots par guide: {total_words // len(guides) if guides else 0}")
    
    if categories:
        print("\n📑 Guides par catégorie:")
        for cat, count in sorted(categories.items()):
            print(f"   {cat}: {count}")
    
    print()


def main():
    """Fonction principale"""
    
    if len(sys.argv) < 2:
        print("""
Usage: python admin_guides.py <commande> [arguments]

Commandes disponibles:
  list              Liste tous les guides
  validate <file>   Valide un guide
  create            Crée un nouveau guide (interactif)
  export            Exporte les guides en JSON
  stats             Affiche des statistiques
        """)
        return
    
    command = sys.argv[1]
    
    if command == "list":
        list_guides()
    
    elif command == "validate":
        if len(sys.argv) < 3:
            print("Usage: admin_guides.py validate <filename>")
            return
        
        guide_path = GUIDES_DIR / sys.argv[2]
        if not guide_path.exists():
            print(f"❌ Guide non trouvé: {guide_path}")
            return
        
        validate_guide(guide_path)
    
    elif command == "create":
        print("\n=== Création d'un Nouveau Guide ===\n")
        
        title = input("Titre du guide: ")
        category = input("Catégorie (ex: Sécurité, Santé, Légal): ")
        
        if title and category:
            create_guide_template(title, category)
        else:
            print("❌ Titre et catégorie requis.")
    
    elif command == "export":
        export_guides_json()
    
    elif command == "stats":
        show_stats()
    
    else:
        print(f"❌ Commande inconnue: {command}")
        print("Utilisez 'python admin_guides.py' pour voir l'aide.")


if __name__ == "__main__":
    main()

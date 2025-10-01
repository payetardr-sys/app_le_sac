//
//  Mood.swift
//  DaylioSAC
//
//  Modèle de données pour l'humeur
//

import Foundation

struct Mood: Codable, Identifiable {
    let id: Int64
    let level: Int
    let label: String
    let emoji: String?
    let color: String?
    
    init(
        id: Int64 = 0,
        level: Int,
        label: String,
        emoji: String? = nil,
        color: String? = nil
    ) {
        self.id = id
        self.level = level
        self.label = label
        self.emoji = emoji
        self.color = color
    }
    
    // Humeurs par défaut
    static let defaultMoods: [Mood] = [
        Mood(id: 1, level: 1, label: "Très mauvais", emoji: "😞", color: "#FF0000"),
        Mood(id: 2, level: 2, label: "Mauvais", emoji: "😟", color: "#FF8800"),
        Mood(id: 3, level: 3, label: "Neutre", emoji: "😐", color: "#FFDD00"),
        Mood(id: 4, level: 4, label: "Bon", emoji: "🙂", color: "#88FF00"),
        Mood(id: 5, level: 5, label: "Excellent", emoji: "😊", color: "#00FF00")
    ]
}

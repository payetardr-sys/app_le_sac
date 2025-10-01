//
//  Consumption.swift
//  DaylioSAC
//
//  Modèle de données pour une consommation
//

import Foundation

struct Consumption: Codable, Identifiable {
    let id: Int64
    let productId: Int64
    let quantity: Double
    let unit: String
    let datetime: Date
    let moodId: Int64?
    let note: String?
    let createdAt: Date
    let updatedAt: Date
    
    enum CodingKeys: String, CodingKey {
        case id
        case productId = "product_id"
        case quantity
        case unit
        case datetime
        case moodId = "mood_id"
        case note
        case createdAt = "created_at"
        case updatedAt = "updated_at"
    }
    
    init(
        id: Int64 = 0,
        productId: Int64,
        quantity: Double,
        unit: String,
        datetime: Date = Date(),
        moodId: Int64? = nil,
        note: String? = nil,
        createdAt: Date = Date(),
        updatedAt: Date = Date()
    ) {
        self.id = id
        self.productId = productId
        self.quantity = quantity
        self.unit = unit
        self.datetime = datetime
        self.moodId = moodId
        self.note = note
        self.createdAt = createdAt
        self.updatedAt = updatedAt
    }
}

// Extension pour l'affichage
extension Consumption {
    var formattedQuantity: String {
        "\(quantity) \(unit)"
    }
    
    var formattedDateTime: String {
        let formatter = DateFormatter()
        formatter.dateStyle = .medium
        formatter.timeStyle = .short
        formatter.locale = Locale(identifier: "fr_FR")
        return formatter.string(from: datetime)
    }
}

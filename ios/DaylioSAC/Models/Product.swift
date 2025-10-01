//
//  Product.swift
//  DaylioSAC
//
//  Modèle de données pour un produit/substance
//

import Foundation

struct Product: Codable, Identifiable {
    let id: Int64
    let name: String
    let category: ProductCategory
    let defaultUnit: String?
    let icon: String?
    let color: String?
    let description: String?
    let isActive: Bool
    let createdAt: Date
    let updatedAt: Date
    
    enum CodingKeys: String, CodingKey {
        case id
        case name
        case category
        case defaultUnit = "default_unit"
        case icon
        case color
        case description
        case isActive = "is_active"
        case createdAt = "created_at"
        case updatedAt = "updated_at"
    }
    
    init(
        id: Int64 = 0,
        name: String,
        category: ProductCategory,
        defaultUnit: String? = nil,
        icon: String? = nil,
        color: String? = nil,
        description: String? = nil,
        isActive: Bool = true,
        createdAt: Date = Date(),
        updatedAt: Date = Date()
    ) {
        self.id = id
        self.name = name
        self.category = category
        self.defaultUnit = defaultUnit
        self.icon = icon
        self.color = color
        self.description = description
        self.isActive = isActive
        self.createdAt = createdAt
        self.updatedAt = updatedAt
    }
}

enum ProductCategory: String, Codable, CaseIterable {
    case alcool = "alcool"
    case tabac = "tabac"
    case cannabis = "cannabis"
    case autre = "autre"
    
    var displayName: String {
        switch self {
        case .alcool: return "Alcool"
        case .tabac: return "Tabac"
        case .cannabis: return "Cannabis"
        case .autre: return "Autre"
        }
    }
}

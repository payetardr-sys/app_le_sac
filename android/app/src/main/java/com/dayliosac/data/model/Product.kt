package com.dayliosac.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entité représentant un produit/substance
 */
@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val name: String,
    
    @ColumnInfo(index = true)
    val category: String, // alcool, tabac, cannabis, autre
    
    @ColumnInfo(name = "default_unit")
    val defaultUnit: String? = null,
    
    val icon: String? = null, // emoji ou nom d'icône
    
    val color: String? = null, // code couleur hex
    
    val description: String? = null,
    
    @ColumnInfo(name = "is_active")
    val isActive: Boolean = true,
    
    @ColumnInfo(name = "created_at")
    val createdAt: String = getCurrentTimestamp(),
    
    @ColumnInfo(name = "updated_at")
    val updatedAt: String = getCurrentTimestamp()
)

private fun getCurrentTimestamp(): String {
    return java.time.Instant.now().toString()
}

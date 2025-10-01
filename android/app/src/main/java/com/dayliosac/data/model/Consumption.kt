package com.dayliosac.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Entité représentant une consommation
 */
@Entity(
    tableName = "consumptions",
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["product_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Mood::class,
            parentColumns = ["id"],
            childColumns = ["mood_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class Consumption(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    @ColumnInfo(name = "product_id", index = true)
    val productId: Long,
    
    val quantity: Double,
    
    val unit: String,
    
    @ColumnInfo(index = true)
    val datetime: String, // ISO 8601 format
    
    @ColumnInfo(name = "mood_id", index = true)
    val moodId: Long? = null,
    
    val note: String? = null,
    
    @ColumnInfo(name = "created_at")
    val createdAt: String = getCurrentTimestamp(),
    
    @ColumnInfo(name = "updated_at")
    val updatedAt: String = getCurrentTimestamp()
)

private fun getCurrentTimestamp(): String {
    return java.time.Instant.now().toString()
}

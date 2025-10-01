package com.dayliosac.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entité représentant une humeur
 */
@Entity(tableName = "moods")
data class Mood(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val level: Int, // 1-5
    
    val label: String,
    
    val emoji: String? = null,
    
    val color: String? = null
) {
    companion object {
        /**
         * Humeurs par défaut
         */
        fun getDefaultMoods(): List<Mood> = listOf(
            Mood(1, 1, "Très mauvais", "😞", "#FF0000"),
            Mood(2, 2, "Mauvais", "😟", "#FF8800"),
            Mood(3, 3, "Neutre", "😐", "#FFDD00"),
            Mood(4, 4, "Bon", "🙂", "#88FF00"),
            Mood(5, 5, "Excellent", "😊", "#00FF00")
        )
    }
}

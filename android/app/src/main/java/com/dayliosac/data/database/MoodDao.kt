package com.dayliosac.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dayliosac.data.model.Mood
import kotlinx.coroutines.flow.Flow

/**
 * DAO pour les opérations sur les humeurs
 */
@Dao
interface MoodDao {
    
    /**
     * Récupère toutes les humeurs
     */
    @Query("SELECT * FROM moods ORDER BY level ASC")
    fun getAllMoods(): Flow<List<Mood>>
    
    /**
     * Récupère une humeur par son ID
     */
    @Query("SELECT * FROM moods WHERE id = :id")
    suspend fun getMoodById(id: Long): Mood?
    
    /**
     * Récupère une humeur par son niveau
     */
    @Query("SELECT * FROM moods WHERE level = :level")
    suspend fun getMoodByLevel(level: Int): Mood?
    
    /**
     * Insère les humeurs par défaut
     */
    @Insert
    suspend fun insertMoods(moods: List<Mood>)
    
    /**
     * Vérifie si les humeurs sont déjà initialisées
     */
    @Query("SELECT COUNT(*) FROM moods")
    suspend fun getMoodsCount(): Int
}

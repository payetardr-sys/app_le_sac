package com.dayliosac.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dayliosac.data.model.Consumption
import kotlinx.coroutines.flow.Flow

/**
 * DAO pour les opérations sur les consommations
 */
@Dao
interface ConsumptionDao {
    
    /**
     * Récupère toutes les consommations, triées par date décroissante
     */
    @Query("SELECT * FROM consumptions ORDER BY datetime DESC")
    fun getAllConsumptions(): Flow<List<Consumption>>
    
    /**
     * Récupère une consommation par son ID
     */
    @Query("SELECT * FROM consumptions WHERE id = :id")
    suspend fun getConsumptionById(id: Long): Consumption?
    
    /**
     * Récupère les consommations pour une période donnée
     */
    @Query("""
        SELECT * FROM consumptions 
        WHERE datetime >= :startDate AND datetime <= :endDate
        ORDER BY datetime DESC
    """)
    suspend fun getConsumptionsByDateRange(startDate: String, endDate: String): List<Consumption>
    
    /**
     * Récupère les consommations par produit
     */
    @Query("SELECT * FROM consumptions WHERE product_id = :productId ORDER BY datetime DESC")
    suspend fun getConsumptionsByProduct(productId: Long): List<Consumption>
    
    /**
     * Récupère les consommations par humeur
     */
    @Query("SELECT * FROM consumptions WHERE mood_id = :moodId ORDER BY datetime DESC")
    suspend fun getConsumptionsByMood(moodId: Long): List<Consumption>
    
    /**
     * Compte le nombre de consommations
     */
    @Query("SELECT COUNT(*) FROM consumptions")
    suspend fun getConsumptionsCount(): Int
    
    /**
     * Compte les consommations pour une période
     */
    @Query("""
        SELECT COUNT(*) FROM consumptions 
        WHERE datetime >= :startDate AND datetime <= :endDate
    """)
    suspend fun getConsumptionsCountByDateRange(startDate: String, endDate: String): Int
    
    /**
     * Insère une nouvelle consommation
     * @return L'ID de la consommation insérée
     */
    @Insert
    suspend fun insertConsumption(consumption: Consumption): Long
    
    /**
     * Insère plusieurs consommations
     */
    @Insert
    suspend fun insertConsumptions(consumptions: List<Consumption>)
    
    /**
     * Met à jour une consommation existante
     */
    @Update
    suspend fun updateConsumption(consumption: Consumption)
    
    /**
     * Supprime une consommation
     */
    @Delete
    suspend fun deleteConsumption(consumption: Consumption)
    
    /**
     * Supprime toutes les consommations
     */
    @Query("DELETE FROM consumptions")
    suspend fun deleteAllConsumptions()
    
    /**
     * Récupère la dernière consommation
     */
    @Query("SELECT * FROM consumptions ORDER BY datetime DESC LIMIT 1")
    suspend fun getLastConsumption(): Consumption?
    
    /**
     * Récupère les statistiques journalières
     */
    @Query("""
        SELECT 
            date(datetime) as date,
            product_id,
            COUNT(*) as count,
            SUM(quantity) as total
        FROM consumptions
        WHERE datetime >= :startDate AND datetime <= :endDate
        GROUP BY date(datetime), product_id
        ORDER BY date DESC
    """)
    suspend fun getDailyStatistics(startDate: String, endDate: String): List<DailyStatistic>
}

/**
 * Classe de données pour les statistiques journalières
 */
data class DailyStatistic(
    val date: String,
    val productId: Long,
    val count: Int,
    val total: Double
)

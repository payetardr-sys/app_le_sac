package com.dayliosac.data.repository

import com.dayliosac.data.database.ConsumptionDao
import com.dayliosac.data.model.Consumption
import kotlinx.coroutines.flow.Flow

/**
 * Repository pour gérer les opérations sur les consommations
 * Abstraction entre la couche UI et la couche de données
 */
class ConsumptionRepository(
    private val consumptionDao: ConsumptionDao
) {
    
    /**
     * Récupère toutes les consommations sous forme de Flow
     */
    fun getAllConsumptions(): Flow<List<Consumption>> {
        return consumptionDao.getAllConsumptions()
    }
    
    /**
     * Récupère une consommation par ID
     */
    suspend fun getConsumptionById(id: Long): Consumption? {
        return consumptionDao.getConsumptionById(id)
    }
    
    /**
     * Récupère les consommations pour une période
     */
    suspend fun getConsumptionsByDateRange(startDate: String, endDate: String): List<Consumption> {
        return consumptionDao.getConsumptionsByDateRange(startDate, endDate)
    }
    
    /**
     * Récupère les consommations par produit
     */
    suspend fun getConsumptionsByProduct(productId: Long): List<Consumption> {
        return consumptionDao.getConsumptionsByProduct(productId)
    }
    
    /**
     * Ajoute une nouvelle consommation
     */
    suspend fun addConsumption(consumption: Consumption): Long {
        return consumptionDao.insertConsumption(consumption)
    }
    
    /**
     * Met à jour une consommation existante
     */
    suspend fun updateConsumption(consumption: Consumption) {
        consumptionDao.updateConsumption(consumption)
    }
    
    /**
     * Supprime une consommation
     */
    suspend fun deleteConsumption(consumption: Consumption) {
        consumptionDao.deleteConsumption(consumption)
    }
    
    /**
     * Compte le nombre total de consommations
     */
    suspend fun getConsumptionsCount(): Int {
        return consumptionDao.getConsumptionsCount()
    }
    
    /**
     * Compte les consommations pour une période
     */
    suspend fun getConsumptionsCountByDateRange(startDate: String, endDate: String): Int {
        return consumptionDao.getConsumptionsCountByDateRange(startDate, endDate)
    }
    
    /**
     * Récupère la dernière consommation
     */
    suspend fun getLastConsumption(): Consumption? {
        return consumptionDao.getLastConsumption()
    }
    
    /**
     * Récupère les statistiques journalières
     */
    suspend fun getDailyStatistics(startDate: String, endDate: String): List<com.dayliosac.data.database.DailyStatistic> {
        return consumptionDao.getDailyStatistics(startDate, endDate)
    }
}

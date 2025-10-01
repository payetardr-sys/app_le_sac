package com.dayliosac.data.repository

import com.dayliosac.data.database.ConsumptionDao
import com.dayliosac.data.model.Consumption
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Tests unitaires pour ConsumptionRepository
 */
class ConsumptionRepositoryTest {
    
    @Mock
    private lateinit var consumptionDao: ConsumptionDao
    
    private lateinit var repository: ConsumptionRepository
    
    private val sampleConsumption = Consumption(
        id = 1,
        productId = 1,
        quantity = 5.0,
        unit = "ml",
        datetime = "2024-01-01T12:00:00",
        moodId = 3,
        note = "Test note"
    )
    
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = ConsumptionRepository(consumptionDao)
    }
    
    @Test
    fun `getAllConsumptions should return flow from dao`() = runBlocking {
        // Given
        val consumptions = listOf(sampleConsumption)
        `when`(consumptionDao.getAllConsumptions()).thenReturn(flowOf(consumptions))
        
        // When
        val result = repository.getAllConsumptions()
        
        // Then
        assertNotNull(result)
        verify(consumptionDao).getAllConsumptions()
    }
    
    @Test
    fun `getConsumptionById should return consumption from dao`() = runBlocking {
        // Given
        val id = 1L
        `when`(consumptionDao.getConsumptionById(id)).thenReturn(sampleConsumption)
        
        // When
        val result = repository.getConsumptionById(id)
        
        // Then
        assertEquals(sampleConsumption, result)
        verify(consumptionDao).getConsumptionById(id)
    }
    
    @Test
    fun `addConsumption should insert and return id`() = runBlocking {
        // Given
        val expectedId = 1L
        `when`(consumptionDao.insertConsumption(sampleConsumption)).thenReturn(expectedId)
        
        // When
        val result = repository.addConsumption(sampleConsumption)
        
        // Then
        assertEquals(expectedId, result)
        verify(consumptionDao).insertConsumption(sampleConsumption)
    }
    
    @Test
    fun `updateConsumption should call dao update`() = runBlocking {
        // When
        repository.updateConsumption(sampleConsumption)
        
        // Then
        verify(consumptionDao).updateConsumption(sampleConsumption)
    }
    
    @Test
    fun `deleteConsumption should call dao delete`() = runBlocking {
        // When
        repository.deleteConsumption(sampleConsumption)
        
        // Then
        verify(consumptionDao).deleteConsumption(sampleConsumption)
    }
    
    @Test
    fun `getConsumptionsCount should return count from dao`() = runBlocking {
        // Given
        val expectedCount = 42
        `when`(consumptionDao.getConsumptionsCount()).thenReturn(expectedCount)
        
        // When
        val result = repository.getConsumptionsCount()
        
        // Then
        assertEquals(expectedCount, result)
        verify(consumptionDao).getConsumptionsCount()
    }
    
    @Test
    fun `getConsumptionsByDateRange should return filtered consumptions`() = runBlocking {
        // Given
        val startDate = "2024-01-01T00:00:00"
        val endDate = "2024-01-31T23:59:59"
        val consumptions = listOf(sampleConsumption)
        `when`(consumptionDao.getConsumptionsByDateRange(startDate, endDate))
            .thenReturn(consumptions)
        
        // When
        val result = repository.getConsumptionsByDateRange(startDate, endDate)
        
        // Then
        assertEquals(consumptions, result)
        verify(consumptionDao).getConsumptionsByDateRange(startDate, endDate)
    }
    
    @Test
    fun `getLastConsumption should return latest consumption`() = runBlocking {
        // Given
        `when`(consumptionDao.getLastConsumption()).thenReturn(sampleConsumption)
        
        // When
        val result = repository.getLastConsumption()
        
        // Then
        assertEquals(sampleConsumption, result)
        verify(consumptionDao).getLastConsumption()
    }
}

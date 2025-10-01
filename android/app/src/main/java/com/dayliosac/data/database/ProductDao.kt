package com.dayliosac.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dayliosac.data.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * DAO pour les opérations sur les produits
 */
@Dao
interface ProductDao {
    
    /**
     * Récupère tous les produits actifs
     */
    @Query("SELECT * FROM products WHERE is_active = 1 ORDER BY name ASC")
    fun getAllActiveProducts(): Flow<List<Product>>
    
    /**
     * Récupère tous les produits (actifs et archivés)
     */
    @Query("SELECT * FROM products ORDER BY name ASC")
    fun getAllProducts(): Flow<List<Product>>
    
    /**
     * Récupère un produit par son ID
     */
    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductById(id: Long): Product?
    
    /**
     * Récupère les produits par catégorie
     */
    @Query("SELECT * FROM products WHERE category = :category AND is_active = 1 ORDER BY name ASC")
    suspend fun getProductsByCategory(category: String): List<Product>
    
    /**
     * Recherche des produits par nom
     */
    @Query("SELECT * FROM products WHERE name LIKE '%' || :query || '%' AND is_active = 1 ORDER BY name ASC")
    suspend fun searchProducts(query: String): List<Product>
    
    /**
     * Insère un nouveau produit
     * @return L'ID du produit inséré
     */
    @Insert
    suspend fun insertProduct(product: Product): Long
    
    /**
     * Insère plusieurs produits
     */
    @Insert
    suspend fun insertProducts(products: List<Product>)
    
    /**
     * Met à jour un produit existant
     */
    @Update
    suspend fun updateProduct(product: Product)
    
    /**
     * Supprime un produit
     */
    @Delete
    suspend fun deleteProduct(product: Product)
    
    /**
     * Archive un produit (soft delete)
     */
    @Query("UPDATE products SET is_active = 0 WHERE id = :id")
    suspend fun archiveProduct(id: Long)
    
    /**
     * Restaure un produit archivé
     */
    @Query("UPDATE products SET is_active = 1 WHERE id = :id")
    suspend fun restoreProduct(id: Long)
}

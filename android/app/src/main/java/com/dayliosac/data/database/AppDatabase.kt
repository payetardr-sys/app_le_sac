package com.dayliosac.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dayliosac.data.model.Consumption
import com.dayliosac.data.model.Mood
import com.dayliosac.data.model.Product
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

/**
 * Base de données Room principale de l'application
 * Chiffrée avec SQLCipher pour la sécurité des données
 */
@Database(
    entities = [
        Consumption::class,
        Product::class,
        Mood::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun consumptionDao(): ConsumptionDao
    abstract fun productDao(): ProductDao
    abstract fun moodDao(): MoodDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        private const val DATABASE_NAME = "dayliosac.db"
        
        /**
         * Récupère l'instance de la base de données
         * @param context Context de l'application
         * @param passphrase Phrase de passe pour le chiffrement (dérivée du PIN)
         */
        fun getInstance(context: Context, passphrase: CharArray): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = buildDatabase(context, passphrase)
                INSTANCE = instance
                instance
            }
        }
        
        /**
         * Construit la base de données avec chiffrement
         */
        private fun buildDatabase(context: Context, passphrase: CharArray): AppDatabase {
            // Créer la factory pour SQLCipher
            val factory = SupportFactory(SQLiteDatabase.getBytes(passphrase))
            
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .openHelperFactory(factory)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Initialiser les données par défaut
                        // (Les humeurs par défaut seront insérées au premier lancement)
                    }
                })
                .build()
        }
        
        /**
         * Ferme l'instance de la base de données
         * Utile pour les tests ou lors de la déconnexion
         */
        fun closeDatabase() {
            INSTANCE?.close()
            INSTANCE = null
        }
        
        /**
         * Vérifie si la base de données existe
         */
        fun databaseExists(context: Context): Boolean {
            val dbFile = context.getDatabasePath(DATABASE_NAME)
            return dbFile.exists()
        }
    }
}

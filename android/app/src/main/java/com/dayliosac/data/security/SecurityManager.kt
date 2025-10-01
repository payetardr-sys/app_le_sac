package com.dayliosac.data.security

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * Gestionnaire de sécurité pour le code PIN et le chiffrement
 */
class SecurityManager(private val context: Context) {
    
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    
    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        PREFS_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    
    companion object {
        private const val PREFS_NAME = "security_prefs"
        private const val KEY_PIN_HASH = "pin_hash"
        private const val KEY_PIN_SALT = "pin_salt"
        private const val KEY_FAILED_ATTEMPTS = "failed_attempts"
        private const val KEY_LOCK_TIMESTAMP = "lock_timestamp"
        
        private const val PBKDF2_ITERATIONS = 10000
        private const val SALT_LENGTH = 16
        private const val MAX_ATTEMPTS = 3
        private const val LOCK_DURATION_MS = 300000L // 5 minutes
    }
    
    /**
     * Vérifie si un PIN est défini
     */
    fun isPinSet(): Boolean {
        return sharedPreferences.contains(KEY_PIN_HASH)
    }
    
    /**
     * Définit un nouveau code PIN
     */
    fun setPin(pin: String): Boolean {
        return try {
            val salt = generateSalt()
            val hash = hashPin(pin, salt)
            
            sharedPreferences.edit()
                .putString(KEY_PIN_HASH, hash)
                .putString(KEY_PIN_SALT, salt)
                .putInt(KEY_FAILED_ATTEMPTS, 0)
                .apply()
            
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    /**
     * Vérifie un code PIN
     */
    fun verifyPin(pin: String): Boolean {
        // Vérifier si le compte est verrouillé
        if (isLocked()) {
            return false
        }
        
        val storedHash = sharedPreferences.getString(KEY_PIN_HASH, null) ?: return false
        val salt = sharedPreferences.getString(KEY_PIN_SALT, null) ?: return false
        
        val hash = hashPin(pin, salt)
        val isValid = hash == storedHash
        
        if (isValid) {
            // Réinitialiser le compteur de tentatives
            resetFailedAttempts()
        } else {
            // Incrémenter le compteur de tentatives
            incrementFailedAttempts()
        }
        
        return isValid
    }
    
    /**
     * Change le code PIN
     */
    fun changePin(oldPin: String, newPin: String): Boolean {
        if (!verifyPin(oldPin)) {
            return false
        }
        return setPin(newPin)
    }
    
    /**
     * Génère un salt aléatoire
     */
    private fun generateSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(SALT_LENGTH)
        random.nextBytes(salt)
        return Base64.encodeToString(salt, Base64.NO_WRAP)
    }
    
    /**
     * Hache le PIN avec PBKDF2
     */
    private fun hashPin(pin: String, salt: String): String {
        val spec = PBEKeySpec(
            pin.toCharArray(),
            Base64.decode(salt, Base64.NO_WRAP),
            PBKDF2_ITERATIONS,
            256
        )
        
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val hash = factory.generateSecret(spec).encoded
        
        return Base64.encodeToString(hash, Base64.NO_WRAP)
    }
    
    /**
     * Incrémente le compteur de tentatives échouées
     */
    private fun incrementFailedAttempts() {
        val attempts = getFailedAttempts() + 1
        sharedPreferences.edit()
            .putInt(KEY_FAILED_ATTEMPTS, attempts)
            .apply()
        
        if (attempts >= MAX_ATTEMPTS) {
            lockAccount()
        }
    }
    
    /**
     * Réinitialise le compteur de tentatives échouées
     */
    private fun resetFailedAttempts() {
        sharedPreferences.edit()
            .putInt(KEY_FAILED_ATTEMPTS, 0)
            .remove(KEY_LOCK_TIMESTAMP)
            .apply()
    }
    
    /**
     * Récupère le nombre de tentatives échouées
     */
    fun getFailedAttempts(): Int {
        return sharedPreferences.getInt(KEY_FAILED_ATTEMPTS, 0)
    }
    
    /**
     * Verrouille le compte
     */
    private fun lockAccount() {
        sharedPreferences.edit()
            .putLong(KEY_LOCK_TIMESTAMP, System.currentTimeMillis())
            .apply()
    }
    
    /**
     * Vérifie si le compte est verrouillé
     */
    fun isLocked(): Boolean {
        val lockTimestamp = sharedPreferences.getLong(KEY_LOCK_TIMESTAMP, 0L)
        
        if (lockTimestamp == 0L) {
            return false
        }
        
        val elapsedTime = System.currentTimeMillis() - lockTimestamp
        
        if (elapsedTime >= LOCK_DURATION_MS) {
            // Déverrouiller automatiquement après la durée
            resetFailedAttempts()
            return false
        }
        
        return true
    }
    
    /**
     * Récupère le temps restant de verrouillage (en secondes)
     */
    fun getLockRemainingTime(): Long {
        if (!isLocked()) {
            return 0
        }
        
        val lockTimestamp = sharedPreferences.getLong(KEY_LOCK_TIMESTAMP, 0L)
        val elapsedTime = System.currentTimeMillis() - lockTimestamp
        val remainingTime = LOCK_DURATION_MS - elapsedTime
        
        return remainingTime / 1000 // Convertir en secondes
    }
    
    /**
     * Génère la passphrase pour le chiffrement de la base de données
     * (dérivée du PIN)
     */
    fun generateDatabasePassphrase(pin: String): CharArray {
        val salt = sharedPreferences.getString(KEY_PIN_SALT, null) ?: ""
        return hashPin(pin, salt).toCharArray()
    }
    
    /**
     * Réinitialise complètement la sécurité
     * ⚠️ Attention : cela supprimera l'accès à la base de données chiffrée
     */
    fun resetSecurity() {
        sharedPreferences.edit().clear().apply()
    }
}

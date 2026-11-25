package com.example.tarotoneinviewmode.util

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.example.tarotoneinviewmode.R

object RemoteConfigHelper {

    private const val KEY_ATIVAR_SADHANA = "ativar_sadhana"
    private const val KEY_GIST_URL = "gist_url_vibracao"
    private const val KEY_DEBUG_MODE = "debug_mode"

    fun initialize() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600 // Verifica atualizações a cada 1 hora
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        
        // Valores Padrão (Caso o cel esteja offline)
        val defaults = mapOf(
            KEY_ATIVAR_SADHANA to false,
            KEY_GIST_URL to "https://gist.githubusercontent.com/blopes-oracle/283c74f588bd050c04c09ab7bbfbac7a/raw/gistfile1.txt",
            KEY_DEBUG_MODE to false
        )
        remoteConfig.setDefaultsAsync(defaults)
        
        remoteConfig.fetchAndActivate()
    }

    fun isSadhanaEnabled(): Boolean = Firebase.remoteConfig.getBoolean(KEY_ATIVAR_SADHANA)
    
    fun getGistUrl(): String = Firebase.remoteConfig.getString(KEY_GIST_URL)
    
    fun isDebugMode(): Boolean = Firebase.remoteConfig.getBoolean(KEY_DEBUG_MODE)
}

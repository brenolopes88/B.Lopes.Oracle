package com.example.tarotoneinviewmode.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// extensão no Context — cria o DataStore uma vez só
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "oraculo_prefs")

class OraculoPreferences(private val context: Context) {

    companion object {
        // notificação da manhã
        val NOTIF_MANHA_ATIVA    = booleanPreferencesKey("notif_manha_ativa")
        val NOTIF_MANHA_HORA     = intPreferencesKey("notif_manha_hora")
        val NOTIF_MANHA_MINUTO   = intPreferencesKey("notif_manha_minuto")

        // notificação da noite
        val NOTIF_NOITE_ATIVA    = booleanPreferencesKey("notif_noite_ativa")
        val NOTIF_NOITE_HORA     = intPreferencesKey("notif_noite_hora")
        val NOTIF_NOITE_MINUTO   = intPreferencesKey("notif_noite_minuto")

        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        val ANALYTICS_ENABLED    = booleanPreferencesKey("analytics_enabled")
        val PRIVACY_NOTICE_SEEN  = booleanPreferencesKey("privacy_notice_seen")

        // defaults
        const val DEFAULT_MANHA_HORA   = 7
        const val DEFAULT_MANHA_MINUTO = 0
        const val DEFAULT_NOITE_HORA   = 21
        const val DEFAULT_NOITE_MINUTO = 0
    }

    // ── leitura ───────────────────────────────────────────────────────────────

    val notifManhaAtiva: Flow<Boolean> = context.dataStore.data
        .map { it[NOTIF_MANHA_ATIVA] ?: false }

    val notifManhaHora: Flow<Int> = context.dataStore.data
        .map { it[NOTIF_MANHA_HORA] ?: DEFAULT_MANHA_HORA }

    val notifManhaMinuto: Flow<Int> = context.dataStore.data
        .map { it[NOTIF_MANHA_MINUTO] ?: DEFAULT_MANHA_MINUTO }

    val notifNoiteAtiva: Flow<Boolean> = context.dataStore.data
        .map { it[NOTIF_NOITE_ATIVA] ?: false }

    val notifNoiteHora: Flow<Int> = context.dataStore.data
        .map { it[NOTIF_NOITE_HORA] ?: DEFAULT_NOITE_HORA }

    val notifNoiteMinuto: Flow<Int> = context.dataStore.data
        .map { it[NOTIF_NOITE_MINUTO] ?: DEFAULT_NOITE_MINUTO }

    val onboardingCompleted: Flow<Boolean> = context.dataStore.data
        .map { it[ONBOARDING_COMPLETED] ?: false }

    val analyticsEnabled: Flow<Boolean> = context.dataStore.data
        .map { it[ANALYTICS_ENABLED] ?: true }

    val privacyNoticeSeen: Flow<Boolean> = context.dataStore.data
        .map { it[PRIVACY_NOTICE_SEEN] ?: false }

    // ── escrita ───────────────────────────────────────────────────────────────

    suspend fun setNotifManha(ativa: Boolean, hora: Int, minuto: Int) {
        context.dataStore.edit {
            it[NOTIF_MANHA_ATIVA]  = ativa
            it[NOTIF_MANHA_HORA]   = hora
            it[NOTIF_MANHA_MINUTO] = minuto
        }
    }

    suspend fun setNotifNoite(ativa: Boolean, hora: Int, minuto: Int) {
        context.dataStore.edit {
            it[NOTIF_NOITE_ATIVA]  = ativa
            it[NOTIF_NOITE_HORA]   = hora
            it[NOTIF_NOITE_MINUTO] = minuto
        }
    }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit {
            it[ONBOARDING_COMPLETED] = completed
        }
    }

    suspend fun setAnalyticsEnabled(enabled: Boolean) {
        context.dataStore.edit {
            it[ANALYTICS_ENABLED] = enabled
        }
    }

    suspend fun setPrivacyNoticeSeen(seen: Boolean) {
        context.dataStore.edit {
            it[PRIVACY_NOTICE_SEEN] = seen
        }
    }
}
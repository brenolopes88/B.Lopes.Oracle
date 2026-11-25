package com.example.tarotoneinviewmode.ui.settings

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.preferences.OraculoPreferences
import com.example.tarotoneinviewmode.data.repository.local.BackupRepository
import com.example.tarotoneinviewmode.data.repository.local.LeituraRepository
import com.example.tarotoneinviewmode.notifications.NotificationScheduler
import com.example.tarotoneinviewmode.util.AnalyticsHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

data class SettingsUiState(
    val notifManhaAtiva: Boolean = false,
    val notifManhaHora: Int = 7,
    val notifManhaMinuto: Int = 0,
    val notifNoiteAtiva: Boolean = false,
    val notifNoiteHora: Int = 21,
    val notifNoiteMinuto: Int = 0,
    val analyticsEnabled: Boolean = true,
    val statusBackup: String? = null
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferences: OraculoPreferences,
    private val leituraRepository: LeituraRepository,
    private val backupRepository: BackupRepository
) : ViewModel() {

    private val _statusBackup = MutableStateFlow<String?>(null)

    val uiState: StateFlow<SettingsUiState> = combine(
        preferences.notifManhaAtiva,
        preferences.notifManhaHora,
        preferences.notifManhaMinuto,
        preferences.notifNoiteAtiva,
        preferences.notifNoiteHora,
        preferences.notifNoiteMinuto,
        preferences.analyticsEnabled,
        _statusBackup
    ) { values ->
        SettingsUiState(
            notifManhaAtiva  = values[0] as Boolean,
            notifManhaHora   = values[1] as Int,
            notifManhaMinuto = values[2] as Int,
            notifNoiteAtiva  = values[3] as Boolean,
            notifNoiteHora   = values[4] as Int,
            notifNoiteMinuto = values[5] as Int,
            analyticsEnabled = values[6] as Boolean,
            statusBackup     = values[7] as String?
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SettingsUiState()
    )

    fun salvarNotifManha(context: Context, ativa: Boolean, hora: Int, minuto: Int) {
        viewModelScope.launch {
            preferences.setNotifManha(ativa, hora, minuto)
            if (ativa) {
                NotificationScheduler.agendarManha(context, hora, minuto)
                AnalyticsHelper.logReminderEnabled(AnalyticsHelper.TYPE_MANHA)
            } else {
                NotificationScheduler.cancelarManha(context)
            }
        }
    }

    fun salvarNotifNoite(context: Context, ativa: Boolean, hora: Int, minuto: Int) {
        viewModelScope.launch {
            preferences.setNotifNoite(ativa, hora, minuto)
            if (ativa) {
                NotificationScheduler.agendarNoite(context, hora, minuto)
                AnalyticsHelper.logReminderEnabled(AnalyticsHelper.TYPE_NOITE)
            } else {
                NotificationScheduler.cancelarNoite(context)
            }
        }
    }

    fun limparHistorico() {
        viewModelScope.launch {
            leituraRepository.deletarTudo()
        }
    }

    fun exportarBackup(context: Context, uri: Uri) {
        viewModelScope.launch {
            try {
                val json = backupRepository.createBackup()
                context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                    outputStream.write(json.toByteArray())
                }
                _statusBackup.value = "Backup exportado com sucesso! ✨"
                AnalyticsHelper.logBackupExported()
            } catch (e: Exception) {
                _statusBackup.value = "Erro ao exportar: ${e.message}"
            }
        }
    }

    fun importarBackup(context: Context, uri: Uri) {
        viewModelScope.launch {
            try {
                val stringBuilder = StringBuilder()
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    BufferedReader(InputStreamReader(inputStream)).use { reader ->
                        var line: String? = reader.readLine()
                        while (line != null) {
                            stringBuilder.append(line)
                            line = reader.readLine()
                        }
                    }
                }
                backupRepository.restoreBackup(stringBuilder.toString())
                _statusBackup.value = "Dados restaurados com sucesso! ✦"
                AnalyticsHelper.logBackupRestored(true)
            } catch (e: Exception) {
                _statusBackup.value = "Erro ao importar: ${e.message}"
                AnalyticsHelper.logBackupRestored(false)
            }
        }
    }

    fun limparStatus() {
        _statusBackup.value = null
    }

    fun setAnalyticsEnabled(context: Context, enabled: Boolean) {
        viewModelScope.launch {
            preferences.setAnalyticsEnabled(enabled)
            AnalyticsHelper.setCollectionEnabled(context, enabled)
            if (!enabled) {
                AnalyticsHelper.resetAnalyticsData(context)
            }
        }
    }
}

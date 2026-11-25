package com.example.tarotoneinviewmode.util

import android.content.Context
import android.os.Bundle
import com.example.tarotoneinviewmode.BuildConfig
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

object AnalyticsHelper {
    private var firebaseAnalytics: FirebaseAnalytics? = null

    // ── Constantes de Debug ──
    const val ANALYTICS_IN_DEBUG = false

    // ── Constantes de Eventos (snake_case, <= 40 chars) ──
    const val EVENT_DRAW_STARTED = "draw_started"
    const val EVENT_TAROT_DRAW_COMPLETED = "tarot_draw_completed"
    const val EVENT_ICHING_DAILY_VIEWED = "iching_daily_viewed"
    const val EVENT_SADHANA_TEASER_TAPPED = "sadhana_teaser_tapped"
    const val EVENT_READING_SAVED = "reading_saved"
    const val EVENT_BACKUP_EXPORTED = "backup_exported"
    const val EVENT_BACKUP_RESTORED = "backup_restored"
    const val EVENT_NOTIFICATION_OPENED = "notification_opened"
    const val EVENT_REMINDER_ENABLED = "reminder_enabled"
    const val EVENT_NOTIF_PERM_RESULT = "notification_permission_result"

    // ── Constantes de Parâmetros ──
    const val PARAM_MODE = "mode"
    const val PARAM_TYPE = "type"
    const val PARAM_SUCCESS = "success"
    const val PARAM_RESULT = "result"

    // ── Valores de Parâmetros (Lista Fechada) ──
    const val TYPE_TAROT = "tarot"
    const val TYPE_ICHING = "iching"
    const val TYPE_MANHA = "carta_do_dia"
    const val TYPE_NOITE = "reflexao_noite"
    const val RESULT_GRANTED = "granted"
    const val RESULT_DENIED = "denied"

    fun initialize(context: Context) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    fun setCollectionEnabled(context: Context, enabled: Boolean) {
        FirebaseAnalytics.getInstance(context).setAnalyticsCollectionEnabled(enabled)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(enabled)
    }

    fun resetAnalyticsData(context: Context) {
        FirebaseAnalytics.getInstance(context).resetAnalyticsData()
    }

    fun logEvent(name: String, params: Bundle? = null) {
        if (BuildConfig.DEBUG && !ANALYTICS_IN_DEBUG) return
        firebaseAnalytics?.logEvent(name, params)
    }

    // ── Métodos de Eventos Específicos ──
    fun logDrawStarted(mode: String) {
        val params = Bundle().apply {
            putString(PARAM_MODE, mode)
        }
        logEvent(EVENT_DRAW_STARTED, params)
    }

    fun logTarotDrawCompleted(mode: String) {
        val params = Bundle().apply {
            putString(PARAM_MODE, mode)
        }
        logEvent(EVENT_TAROT_DRAW_COMPLETED, params)
    }

    fun logIChingDailyViewed() {
        logEvent(EVENT_ICHING_DAILY_VIEWED)
    }

    fun logSadhanaTeaserTapped() {
        logEvent(EVENT_SADHANA_TEASER_TAPPED)
    }

    fun logReadingSaved(type: String) {
        val params = Bundle().apply {
            putString(PARAM_TYPE, type)
        }
        logEvent(EVENT_READING_SAVED, params)
    }

    fun logBackupExported() {
        logEvent(EVENT_BACKUP_EXPORTED)
    }

    fun logBackupRestored(success: Boolean) {
        val params = Bundle().apply {
            putString(PARAM_SUCCESS, success.toString())
        }
        logEvent(EVENT_BACKUP_RESTORED, params)
    }

    fun logNotificationOpened(type: String) {
        val params = Bundle().apply {
            putString(PARAM_TYPE, type)
        }
        logEvent(EVENT_NOTIFICATION_OPENED, params)
    }

    fun logReminderEnabled(type: String) {
        val params = Bundle().apply {
            putString(PARAM_TYPE, type)
        }
        logEvent(EVENT_REMINDER_ENABLED, params)
    }

    fun logNotificationPermissionResult(result: String) {
        val params = Bundle().apply {
            putString(PARAM_RESULT, result)
        }
        logEvent(EVENT_NOTIF_PERM_RESULT, params)
    }
}

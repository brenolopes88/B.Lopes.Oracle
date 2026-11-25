package com.example.tarotoneinviewmode.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager
import java.util.Calendar

object NotificationScheduler {

    private const val REQUEST_MANHA = 2001
    private const val REQUEST_NOITE = 2002
    private const val PREFS_NAME = "notif_backup"

    fun agendarManha(context: Context, hora: Int, minuto: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean("manha_ativa", true).putInt("manha_hora", hora).putInt("manha_minuto", minuto).apply()
        agendar(context, hora, minuto, REQUEST_MANHA, "MANHA")
    }

    fun agendarNoite(context: Context, hora: Int, minuto: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean("noite_ativa", true).putInt("noite_hora", hora).putInt("noite_minuto", minuto).apply()
        agendar(context, hora, minuto, REQUEST_NOITE, "NOITE")
    }

    fun cancelarManha(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean("manha_ativa", false).apply()
        cancelar(context, REQUEST_MANHA, "MANHA")
    }

    fun cancelarNoite(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean("noite_ativa", false).apply()
        cancelar(context, REQUEST_NOITE, "NOITE")
    }

    private fun hasNotificationPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    private fun agendar(context: Context, hora: Int, minuto: Int, requestCode: Int, tipo: String) {
        if (!hasNotificationPermission(context)) return

        val alarmManager = context.getSystemService(AlarmManager::class.java) ?: return

        val intent = Intent(context, NotifReceiver::class.java).apply {
            putExtra("tipo", tipo)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context, requestCode, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alvo = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hora)
            set(Calendar.MINUTE, minuto)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        if (alvo.timeInMillis <= System.currentTimeMillis()) {
            alvo.add(Calendar.DAY_OF_YEAR, 1)
        }

        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alvo.timeInMillis,
            pendingIntent
        )
    }

    private fun cancelar(context: Context, requestCode: Int, tipo: String) {
        val alarmManager = context.getSystemService(AlarmManager::class.java) ?: return
        val intent = Intent(context, NotifReceiver::class.java).apply {
            putExtra("tipo", tipo)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context, requestCode, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}

package com.example.tarotoneinviewmode.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build

class NotifReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == Intent.ACTION_BOOT_COMPLETED ||
            action == Intent.ACTION_TIME_CHANGED ||
            action == Intent.ACTION_TIMEZONE_CHANGED
        ) {
            reagendarTodos(context)
            return
        }

        when (intent.getStringExtra("tipo")) {
            "MANHA" -> NotificationHelper.mostrarNotificacao(
                context  = context,
                notifId  = NotificationHelper.NOTIF_ID_MANHA,
                titulo   = "✦ Oráculo",
                mensagem = "Sua carta do dia está esperando.",
                destino  = "draw/day_card"
            )
            "NOITE" -> NotificationHelper.mostrarNotificacao(
                context  = context,
                notifId  = NotificationHelper.NOTIF_ID_NOITE,
                titulo   = "✦ Oráculo",
                mensagem = "E aí, ressoou?",
                destino  = "history"
            )
        }

        // reagenda para o dia seguinte
        reagendar(context, intent)
    }

    private fun reagendar(context: Context, intent: Intent) {
        if (!hasNotificationPermission(context)) return
        val prefs = context.getSharedPreferences("notif_backup", Context.MODE_PRIVATE)
        when (intent.getStringExtra("tipo")) {
            "MANHA" -> {
                if (prefs.getBoolean("manha_ativa", false)) {
                    val hora   = prefs.getInt("manha_hora", 7)
                    val minuto = prefs.getInt("manha_minuto", 0)
                    NotificationScheduler.agendarManha(context, hora, minuto)
                }
            }
            "NOITE" -> {
                if (prefs.getBoolean("noite_ativa", false)) {
                    val hora   = prefs.getInt("noite_hora", 21)
                    val minuto = prefs.getInt("noite_minuto", 0)
                    NotificationScheduler.agendarNoite(context, hora, minuto)
                }
            }
        }
    }

    private fun reagendarTodos(context: Context) {
        if (!hasNotificationPermission(context)) return
        val prefs = context.getSharedPreferences("notif_backup", Context.MODE_PRIVATE)
        if (prefs.getBoolean("manha_ativa", false)) {
            val hora = prefs.getInt("manha_hora", 7)
            val minuto = prefs.getInt("manha_minuto", 0)
            NotificationScheduler.agendarManha(context, hora, minuto)
        }
        if (prefs.getBoolean("noite_ativa", false)) {
            val hora = prefs.getInt("noite_hora", 21)
            val minuto = prefs.getInt("noite_minuto", 0)
            NotificationScheduler.agendarNoite(context, hora, minuto)
        }
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
}

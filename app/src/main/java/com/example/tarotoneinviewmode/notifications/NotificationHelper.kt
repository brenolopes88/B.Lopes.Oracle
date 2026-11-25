package com.example.tarotoneinviewmode.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.tarotoneinviewmode.MainActivity
import com.example.tarotoneinviewmode.R

object NotificationHelper {

    const val CHANNEL_ID   = "oraculo_channel"
    const val NOTIF_ID_MANHA = 1001
    const val NOTIF_ID_NOITE = 1002

    fun criarCanal(context: Context) {
        val canal = NotificationChannel(
            CHANNEL_ID,
            "Oráculo",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Notificações diárias do Oráculo"
        }
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(canal)
    }

    fun mostrarNotificacao(
        context: Context,
        notifId: Int,
        titulo: String,
        mensagem: String,
        destino: String      // rota de navegação ao abrir
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra("destino", destino)
        }
        val pendingIntent = PendingIntent.getActivity(
            context, notifId, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notif = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(titulo)
            .setContentText(mensagem)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.notify(notifId, notif)
    }
}
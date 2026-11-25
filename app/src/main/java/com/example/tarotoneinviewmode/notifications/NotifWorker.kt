package com.example.tarotoneinviewmode.notifications

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class NotifManhaWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        NotificationHelper.mostrarNotificacao(
            context = applicationContext,
            notifId = NotificationHelper.NOTIF_ID_MANHA,
            titulo  = "✦ Oráculo",
            mensagem = "Sua carta do dia está esperando.",
            destino  = "home"
        )
        return Result.success()
    }
}

class NotifNoiteWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        NotificationHelper.mostrarNotificacao(
            context = applicationContext,
            notifId = NotificationHelper.NOTIF_ID_NOITE,
            titulo  = "✦ Oráculo",
            mensagem = "E aí, ressoou?",
            destino  = "home"
        )
        return Result.success()
    }
}
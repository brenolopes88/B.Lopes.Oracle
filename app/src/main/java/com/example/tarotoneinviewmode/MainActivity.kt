package com.example.tarotoneinviewmode

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.tarotoneinviewmode.ui.main.MainScaffold
import com.example.tarotoneinviewmode.ui.navigation.TarotNavHost
import com.example.tarotoneinviewmode.ui.theme.TarotOneInViewModeTheme
import com.example.tarotoneinviewmode.util.AnalyticsHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var destinoInicialState by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cria o canal de notificações
        com.example.tarotoneinviewmode.notifications.NotificationHelper.criarCanal(this)

        // Lê o destino da intent que abriu o aplicativo pela primeira vez
        intent.getStringExtra("destino")?.let { rota ->
            destinoInicialState = rota
            val notifType = if (rota.contains("day_card")) AnalyticsHelper.TYPE_MANHA else AnalyticsHelper.TYPE_NOITE
            AnalyticsHelper.logNotificationOpened(notifType)
        }

        setContent {
            val navController = rememberNavController()

            TarotOneInViewModeTheme {
                // Navega para a rota recebida via notificação (se houver)
                LaunchedEffect(destinoInicialState) {
                    destinoInicialState?.let { rota ->
                        if (rota.isNotEmpty()) {
                            navController.navigate(rota)
                            destinoInicialState = null // Limpa para não re-navegar em recomposições
                        }
                    }
                }

                MainScaffold(navController = navController) { modifier ->
                    Box(modifier = modifier) {
                        TarotNavHost(
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    // Chamado quando o app já está aberto e recebe clique de notificação
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        intent.getStringExtra("destino")?.let { rota ->
            destinoInicialState = rota
            val notifType = if (rota.contains("day_card")) "manha" else "noite"
            com.example.tarotoneinviewmode.util.AnalyticsHelper.logNotificationOpened(notifType)
        }
    }
}
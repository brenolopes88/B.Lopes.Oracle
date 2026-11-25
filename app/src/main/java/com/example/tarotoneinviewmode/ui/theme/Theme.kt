package com.example.tarotoneinviewmode.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary          = TarotGold,
    secondary        = EmeraldGlow,
    tertiary         = EtherBlue,
    background       = DeepIndigo,
    surface          = DarkAbyss,
    surfaceVariant   = ShadowPurple,
    onPrimary        = Color.Black,
    onSecondary      = Color.Black,
    onTertiary       = Color.Black,
    onBackground     = Color.White,
    onSurface        = Parchment,
    error            = ErrorRed
)

@Composable
fun TarotOneInViewModeTheme(
    content: @Composable () -> Unit
) {
    // 🚀 Sempre usa o DarkColorScheme, ignorando configurações de sistema ou cores dinâmicas
    val colorScheme = DarkColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            
            // 🚀 Garante que os ícones da barra de status sejam brancos (fundo escuro)
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography  = Typography,
        content     = content
    )
}
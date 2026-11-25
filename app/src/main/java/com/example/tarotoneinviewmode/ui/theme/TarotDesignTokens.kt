package com.example.tarotoneinviewmode.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object TarotDesignTokens {

    // ── Opacidades de Backgrounds / Overlays ──────────────────────────────────
    object Alpha {
        const val BgImageDefault = 0.60f // 🚀 Padronizado para telas gerais
        const val BgImageArchive = 0.60f // 🚀 Padronizado para os Arquivos
        const val BgImageInterpretation = 0.35f // 🚀 Mais escuro para facilitar a leitura do texto

        const val CardSurface = 0.65f // 🚀 Ajustado de 0.50 para 0.65 conforme auditoria
        const val MandaraSurface = 0.70f // 🚀 0.70 onde há texto secundário sobre a mandala
        const val HeaderSurface = 0.00f

        const val GlassButtonBg = 0.065f
        const val GlassButtonBorder = 0.08f

        const val TabRowBg = 0.00f
        const val TabUnselectedIcon = 0.85f
        const val TabUnselectedText = 0.65f // 🚀 Ajustado de 0.45 para 0.65

        const val TextDisabled = 0.40f
        const val TextMuted = 0.85f // 🚀 TextSecondary (85%)
        const val TextSubtle = 0.70f // 🚀 TextSupport (70% - mínimo)

        const val Divider = 0.06f
    }

    // ── Cores Centralizadas Globais ──────────────────────────────────────────
    object Colors {
        val BorderLight = Color.White.copy(alpha = Alpha.GlassButtonBorder)
        val DividerColor = Color.White.copy(alpha = Alpha.Divider)

        val TextPrimary = Parchment
        val TextSecondary = Parchment.copy(alpha = Alpha.TextMuted)
        val TextSupport = Parchment.copy(alpha = Alpha.TextSubtle)
        val LabelGold = TarotGold
        val TealLabel = LabelTeal
        val DestructiveText = ErrorText
    }

    // ── Dimensões e Arredondamentos Reutilizáveis ─────────────────────────────
    object Shapes {
        val CardCornerRadius: Dp = 10.dp
        val GlassButtonCornerRadius: Dp = 8.dp
        val CardImageCornerRadius: Dp = 8.dp
        val MiniCardCornerRadius: Dp = 4.dp
    }
}
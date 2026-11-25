package com.example.tarotoneinviewmode.ui.draw

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.ui.navigation.NavRoute
import com.example.tarotoneinviewmode.ui.theme.TarotDesignTokens

@Composable
fun SpreadSelectionScreen(
    onBack: () -> Unit,
    onNavigateToDraw: (String) -> Unit,
    onNavigateToIChing: (String) -> Unit
) {
    val haptic = LocalHapticFeedback.current
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val topHeaderHeight = 110.dp + statusBarHeight

    Box(modifier = Modifier.fillMaxSize()) {
        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.bg_default),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = TarotDesignTokens.Alpha.BgImageDefault
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 24.dp, end = 24.dp, top = topHeaderHeight + 16.dp, bottom = 140.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.hub_title),
                style = MaterialTheme.typography.headlineMedium,
                color = TarotDesignTokens.Colors.LabelGold,
                fontWeight = FontWeight.Light,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(R.string.hub_subtitle),
                style = MaterialTheme.typography.bodySmall,
                color = TarotDesignTokens.Colors.TextSupport,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- SEÇÃO TAROT ---
            SectionHeader(stringResource(R.string.section_tarot_label), TarotDesignTokens.Colors.LabelGold)

            val descPpf = stringResource(R.string.hub_tarot_desc_ppf)
            val badge3 = stringResource(R.string.badge_cards_3)
            SpreadButton(
                title = "Passado · Presente · Futuro",
                description = descPpf,
                badge = badge3,
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onNavigateToDraw(NavRoute.Draw.PPF)
                },
                color = TarotDesignTokens.Colors.LabelGold,
                schemeType = SchemeType.TAROT_3
            )

            val descSimNao = stringResource(R.string.hub_tarot_desc_simnao)
            val badge1 = stringResource(R.string.badge_cards_1)
            SpreadButton(
                title = "Sim ou Não",
                description = descSimNao,
                badge = badge1,
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onNavigateToDraw(NavRoute.Draw.SIM_NAO)
                },
                color = TarotDesignTokens.Colors.LabelGold,
                schemeType = SchemeType.TAROT_1
            )

            val descDesafio = stringResource(R.string.hub_tarot_desc_desafio)
            val badge2 = stringResource(R.string.badge_cards_2)
            SpreadButton(
                title = "Desafio e Solução",
                description = descDesafio,
                badge = badge2,
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onNavigateToDraw(NavRoute.Draw.DESAFIO)
                },
                color = TarotDesignTokens.Colors.LabelGold,
                schemeType = SchemeType.TAROT_2
            )

            val descRel = stringResource(R.string.hub_tarot_desc_relacionamento)
            SpreadButton(
                title = "Relacionamento",
                description = descRel,
                badge = badge3,
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onNavigateToDraw(NavRoute.Draw.RELATION)
                },
                color = TarotDesignTokens.Colors.LabelGold,
                schemeType = SchemeType.TAROT_3
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- SEÇÃO I CHING ---
            SectionHeader(stringResource(R.string.section_iching_label), TarotDesignTokens.Colors.TealLabel)

            val descGen = stringResource(R.string.hub_iching_desc_general)
            val badgeHex1 = stringResource(R.string.badge_hex_1)
            SpreadButton(
                title = "Consulta de Sabedoria",
                description = descGen,
                badge = badgeHex1,
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onNavigateToIChing(NavRoute.IChing.MODE_GENERAL)
                },
                color = TarotDesignTokens.Colors.TealLabel,
                schemeType = SchemeType.ICHING_1
            )

            val descMut = stringResource(R.string.hub_iching_desc_mutation)
            val badgeHex2 = stringResource(R.string.badge_hex_2)
            SpreadButton(
                title = "Mutação",
                description = descMut,
                badge = badgeHex2,
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onNavigateToIChing(NavRoute.IChing.MODE_MUTATION)
                },
                color = TarotDesignTokens.Colors.TealLabel,
                schemeType = SchemeType.ICHING_2
            )

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

enum class SchemeType {
    TAROT_1, TAROT_2, TAROT_3, ICHING_1, ICHING_2
}

@Composable
private fun SectionHeader(title: String, color: Color) {
    Text(
        text = title.uppercase(),
        style = MaterialTheme.typography.labelMedium,
        fontSize = 12.sp,
        color = color,
        letterSpacing = 2.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpreadButton(
    title: String,
    description: String,
    badge: String,
    onClick: () -> Unit,
    color: Color,
    schemeType: SchemeType
) {
    val cardDesc = "$title, $description, $badge"

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .semantics { contentDescription = cardDesc },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.75f)
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, color.copy(alpha = 0.60f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {
                SpreadCanvas(schemeType = schemeType, color = color)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        color = color,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )

                    Surface(
                        color = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, color.copy(alpha = 0.5f))
                    ) {
                        Text(
                            text = badge,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 12.sp,
                            color = TarotDesignTokens.Colors.TextSupport,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 13.sp,
                    color = TarotDesignTokens.Colors.TextSecondary
                )
            }
        }
    }
}

@Composable
private fun SpreadCanvas(schemeType: SchemeType, color: Color) {
    val strokeColor = color
    val fillColor = color.copy(alpha = 0.2f)

    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        when (schemeType) {
            SchemeType.TAROT_1 -> {
                val rectW = w * 0.45f
                val rectH = h * 0.8f
                drawRect(
                    color = fillColor,
                    topLeft = Offset((w - rectW) / 2f, (h - rectH) / 2f),
                    size = Size(rectW, rectH)
                )
                drawRect(
                    color = strokeColor,
                    topLeft = Offset((w - rectW) / 2f, (h - rectH) / 2f),
                    size = Size(rectW, rectH),
                    style = Stroke(width = 2f)
                )
            }
            SchemeType.TAROT_2 -> {
                val rectW = w * 0.35f
                val rectH = h * 0.75f
                val spacing = 4f
                val startX = (w - (rectW * 2 + spacing)) / 2f
                val startY = (h - rectH) / 2f
                repeat(2) { i ->
                    val x = startX + i * (rectW + spacing)
                    drawRect(color = fillColor, topLeft = Offset(x, startY), size = Size(rectW, rectH))
                    drawRect(color = strokeColor, topLeft = Offset(x, startY), size = Size(rectW, rectH), style = Stroke(width = 2f))
                }
            }
            SchemeType.TAROT_3 -> {
                val rectW = w * 0.25f
                val rectH = h * 0.75f
                val spacing = 3f
                val startX = (w - (rectW * 3 + spacing * 2)) / 2f
                val startY = (h - rectH) / 2f
                repeat(3) { i ->
                    val x = startX + i * (rectW + spacing)
                    drawRect(color = fillColor, topLeft = Offset(x, startY), size = Size(rectW, rectH))
                    drawRect(color = strokeColor, topLeft = Offset(x, startY), size = Size(rectW, rectH), style = Stroke(width = 2f))
                }
            }
            SchemeType.ICHING_1 -> {
                val lineH = h * 0.08f
                val lineW = w * 0.7f
                val startX = (w - lineW) / 2f
                val startY = h * 0.2f
                val gap = h * 0.09f
                repeat(6) { i ->
                    val y = startY + i * (lineH + gap)
                    drawRect(color = strokeColor, topLeft = Offset(startX, y), size = Size(lineW, lineH))
                }
            }
            SchemeType.ICHING_2 -> {
                val lineH = h * 0.06f
                val lineW = w * 0.35f
                val startY = h * 0.25f
                val gap = h * 0.07f

                repeat(6) { i ->
                    val y = startY + i * (lineH + gap)
                    drawRect(color = strokeColor, topLeft = Offset(2f, y), size = Size(lineW, lineH))
                }
                drawLine(
                    color = strokeColor,
                    start = Offset(w * 0.42f, h * 0.5f),
                    end = Offset(w * 0.58f, h * 0.5f),
                    strokeWidth = 2f
                )
                repeat(6) { i ->
                    val y = startY + i * (lineH + gap)
                    drawRect(color = strokeColor, topLeft = Offset(w * 0.62f, y), size = Size(lineW, lineH))
                }
            }
        }
    }
}

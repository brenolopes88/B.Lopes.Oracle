package com.example.tarotoneinviewmode.ui.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tarotoneinviewmode.data.iching.IChingData
import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.domain.model.TarotCard
import com.example.tarotoneinviewmode.ui.theme.TarotDesignTokens
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import com.example.tarotoneinviewmode.ui.navigation.NavRoute
import com.example.tarotoneinviewmode.util.LuaUtils
import com.example.tarotoneinviewmode.ui.components.SectionLabel
import com.example.tarotoneinviewmode.util.AnalyticsHelper
import java.util.Calendar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onDrawDayCard: () -> Unit,
    onVerCartaDoDia: () -> Unit,
    onVerIChing: (String) -> Unit,
    onVerHexagramaDoDia: () -> Unit,
    onVerHistorico: () -> Unit,
    onPPF: () -> Unit,
    onSimNao: () -> Unit,
    onDesafio: () -> Unit,
    onRelacionamento: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.carregarHome()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
            return@Box
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 🚀 Header Fixo (Dia e Fase da Lua)
            HomeHeader(faseLua = uiState.faseLua)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // 🚀 1. RESUMO ENERGÉTICO (Vibração ou Boas-vindas)
                SectionLabel(stringResource(R.string.section_energy_summary))
                CardReflexaoEvolutiva(
                    uiState = uiState,
                    onStartJourney = onDrawDayCard
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 🚀 2. CONSELHOS DO DIA (Ação principal)
                SectionLabel(stringResource(R.string.section_daily_advice))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CardRitualTarot(
                        modifier = Modifier.weight(1f),
                        carta = uiState.cartaDoDia,
                        onConsultar = onDrawDayCard,
                        onVerLeitura = onVerCartaDoDia
                    )
                    CardRitualIChing(
                        modifier = Modifier.weight(1f),
                        hexagrama = uiState.hexagramaDoDia,
                        onConsultar = { onVerIChing(NavRoute.IChing.MODE_DAILY) },
                        onVerLeitura = onVerHexagramaDoDia
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 🚀 3. HISTÓRICO RECENTE
                SectionLabel(stringResource(R.string.section_history))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = TarotDesignTokens.Alpha.CardSurface),
                            shape = RoundedCornerShape(TarotDesignTokens.Shapes.CardCornerRadius)
                        )
                        .padding(vertical = 4.dp)
                ) {
                    if (uiState.historicoRecente.isEmpty()) {
                        HistoricoItemPlaceholder()
                    } else {
                        uiState.historicoRecente.forEachIndexed { index, item ->
                            HistoricoItem(item = item)
                            if (index < uiState.historicoRecente.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    color = TarotDesignTokens.Colors.DividerColor
                                )
                            }
                        }
                    }
                }

                TextButton(
                    onClick = onVerHistorico,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 20.dp)
                ) {
                    Text(
                        text = stringResource(R.string.view_full_history),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = TarotDesignTokens.Alpha.TextMuted)
                    )
                }

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
private fun HomeHeader(faseLua: LuaUtils.FaseLua) {
    val hoje = LocalDate.now()
    val diaSemana = hoje.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("pt", "BR"))
        .replaceFirstChar { it.uppercase() }
    val dataFormatada = "%02d de %s".format(
        hoje.dayOfMonth,
        hoje.month.getDisplayName(TextStyle.FULL, Locale("pt", "BR"))
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(MaterialTheme.colorScheme.background.copy(alpha = TarotDesignTokens.Alpha.HeaderSurface))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 16.dp)
        ) {
            Text(
                text = "$diaSemana, $dataFormatada  ·  ${faseLua.emoji} ${faseLua.nome}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = TarotDesignTokens.Alpha.TextMuted),
                letterSpacing = 0.5.sp
            )
        }
    }
}

@Composable
private fun CardReflexaoEvolutiva(
    uiState: HomeUiState,
    onStartJourney: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable {
                if (!uiState.isEmpty && uiState.vibracaoDaSemana == null) {
                    AnalyticsHelper.logSadhanaTeaserTapped()
                }
            },
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            1.dp, 
            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                // 1. ESTADO VAZIO: Onboarding
                uiState.isEmpty -> {
                    Text(
                        text = stringResource(R.string.reflection_empty_title),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.reflection_empty_desc),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "✨",
                        fontSize = 24.sp,
                        modifier = Modifier.alpha(0.5f)
                    )
                }

                // 2. ESTADO COM DADOS: Vibração da Semana
                uiState.vibracaoDaSemana != null -> {
                    Text(
                        text = stringResource(R.string.reflection_vibration_title),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = uiState.vibracaoDaSemana.imageResId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp, 80.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = uiState.vibracaoDaSemana.name,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = stringResource(R.string.reflection_vibration_desc),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                                lineHeight = 16.sp
                            )
                        }
                    }
                }

                // 3. FALLBACK: Insight do Oráculo
                else -> {
                    Text(
                        text = stringResource(R.string.reflection_insight_title),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "“${uiState.insightDoDia}”",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        lineHeight = 24.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.reflection_insight_footer),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}

@Composable
private fun CardRitualTarot(
    modifier: Modifier,
    carta: TarotCard?,
    onConsultar: () -> Unit,
    onVerLeitura: () -> Unit
) {
    val isRevealed = carta != null
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(TarotDesignTokens.Shapes.CardCornerRadius))
            .clickable { if (isRevealed) onVerLeitura() else onConsultar() }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f),
                shape = RoundedCornerShape(TarotDesignTokens.Shapes.CardCornerRadius)
            )
            .background(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.75f)
            )
            .padding(vertical = 20.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.height(140.dp),
            contentAlignment = Alignment.Center
        ) {
            if (carta != null) {
                Image(
                    painter = painterResource(id = carta.imageResId),
                    contentDescription = carta.name,
                    modifier = Modifier
                        .width(90.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(TarotDesignTokens.Shapes.CardImageCornerRadius)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = "✦",
                    fontSize = 56.sp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = carta?.name ?: "Conselho de hoje",
            style = MaterialTheme.typography.bodyMedium,
            color = if (isRevealed) MaterialTheme.colorScheme.primary else TarotDesignTokens.Colors.TextSecondary,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = if (isRevealed) stringResource(R.string.action_tap_to_see_details) else stringResource(R.string.action_tap_to_reveal),
            style = MaterialTheme.typography.labelSmall,
            color = TarotDesignTokens.Colors.TextSupport,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun CardRitualIChing(
    modifier: Modifier,
    hexagrama: Int?,
    onConsultar: () -> Unit,
    onVerLeitura: () -> Unit
) {
    val isRevealed = hexagrama != null
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(TarotDesignTokens.Shapes.CardCornerRadius))
            .clickable { if (isRevealed) onVerLeitura() else onConsultar() }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.25f),
                shape = RoundedCornerShape(TarotDesignTokens.Shapes.CardCornerRadius)
            )
            .background(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.75f)
            )
            .padding(vertical = 20.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.height(140.dp),
            contentAlignment = Alignment.Center
        ) {
            if (hexagrama != null) {
                Text(
                    text = getHexagramaUnicode(hexagrama),
                    fontSize = 96.sp, 
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = "☯",
                    fontSize = 56.sp,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.25f)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = if (isRevealed) "Hexagrama $hexagrama" else "Sabedoria diária",
            style = MaterialTheme.typography.bodyMedium,
            color = if (isRevealed) MaterialTheme.colorScheme.secondary else TarotDesignTokens.Colors.TextSecondary,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = if (isRevealed) stringResource(R.string.action_tap_to_see_details) else stringResource(R.string.action_tap_to_reveal),
            style = MaterialTheme.typography.labelSmall,
            color = TarotDesignTokens.Colors.TextSupport,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun HistoricoItem(item: ItemHistoricoRecente) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // DATA
        Text(
            text = item.dataFormatada,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = TarotDesignTokens.Alpha.TextDisabled),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(45.dp)
        )

        VerticalDivider(
            modifier = Modifier.height(24.dp).padding(horizontal = 8.dp),
            color = TarotDesignTokens.Colors.DividerColor.copy(alpha = 0.5f)
        )

        // TAROT
        Box(modifier = Modifier.weight(1f)) {
            if (item.cartaTarot != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = item.cartaTarot.imageResId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.cartaTarot.name,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        maxLines = 1
                    )
                }
            } else {
                Text(
                    text = "—",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
        }

        // I CHING
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
            if (item.hexagramaIChing != null) {
                val hex = IChingData.hexagrams.firstOrNull { it.number == item.hexagramaIChing }
                val labelHex = hex?.let { "${it.number}. ${it.nameTranslated}" } ?: "Hex. ${item.hexagramaIChing}"
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = getHexagramaUnicode(item.hexagramaIChing),
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = labelHex,
                        style = MaterialTheme.typography.bodySmall,
                        color = TarotDesignTokens.Colors.TextSecondary,
                        maxLines = 1
                    )
                }
            } else {
                Text(
                    text = "—",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                )
            }
        }
    }
}

@Composable
private fun ReadingCardButton(
    onClick: () -> Unit,
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Surface(
        onClick = onClick,
        modifier = modifier
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        textColor.copy(alpha = 0.3f),
                        textColor.copy(alpha = 0.05f)
                    )
                ),
                shape = RoundedCornerShape(TarotDesignTokens.Shapes.CardCornerRadius)
            ),
        shape = RoundedCornerShape(TarotDesignTokens.Shapes.CardCornerRadius),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.15f),
        tonalElevation = 2.dp
    ) {
        Box(
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
private fun HistoricoItemPlaceholder() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .alpha(0.3f), // 🚀 Deixa o placeholder mais discreto
        verticalAlignment = Alignment.CenterVertically
    ) {
        // DATA PLACEHOLDER
        Text(
            text = "--/--",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(45.dp)
        )

        VerticalDivider(
            modifier = Modifier.height(24.dp).padding(horizontal = 8.dp),
            color = TarotDesignTokens.Colors.DividerColor
        )

        // TEXTO PLACEHOLDER
        Text(
            text = "As leituras anteriores aparecerão aqui",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.weight(1f)
        )
    }
}

private fun getHexagramaUnicode(numero: Int): String {
    val codePoint = 0x4DC0 + (numero - 1).coerceIn(0, 63)
    return String(Character.toChars(codePoint))
}

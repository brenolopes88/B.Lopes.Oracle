package com.example.tarotoneinviewmode.ui.draw

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import com.example.tarotoneinviewmode.util.ShareUtils
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.domain.model.draw.DrawCard
import com.example.tarotoneinviewmode.ui.navigation.NavRoute
import com.example.tarotoneinviewmode.domain.enums.ArcanaType
import com.example.tarotoneinviewmode.ui.components.DetailSection
import com.example.tarotoneinviewmode.util.AnalyticsHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawScreen(
    viewModel: DrawViewModel,
    onBack: () -> Unit,
    onShowGuide: (String) -> Unit
) {
    val uiState by viewModel.uiState
    val haptic = LocalHapticFeedback.current
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.nav_back),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    if (uiState.allRevealed) {
                        val shareText = buildString {
                            appendLine("─── ✧ ORÁCULO ✧ ───")
                            appendLine("🃏 LEITURA: ${uiState.mode.label}")
                            appendLine()
                            uiState.cards.forEach { 
                                appendLine("${it.position.label}: ${it.card.name}")
                                appendLine("\"${it.card.essence}\"")
                                appendLine()
                            }
                            append("✨ Sintonize sua intenção no app Oráculo.")
                        }

                        IconButton(onClick = { ShareUtils.copyToClipboard(context, shareText) }) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copiar", tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = { ShareUtils.shareText(context, shareText) }) {
                            Icon(Icons.Default.Share, contentDescription = "Compartilhar", tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                    IconButton(onClick = { onShowGuide(NavRoute.Guide.TOPIC_TAROT) }) {
                        Icon(Icons.Default.Info, contentDescription = "Sobre o Tarot", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 🚀 Fundo Padronizado para Interpretação
            Image(
                painter = painterResource(id = R.drawable.bg_default),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.6f
            )

            when {
                uiState.isCheckingDay -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }

                uiState.isShuffling -> {
                    ShufflingAnimation()
                }

                !uiState.hasCards -> PreparationPhase(
                    mode = uiState.mode,
                    onDraw = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        viewModel.drawCards()
                    }
                )

                else -> RevealPhase(
                    uiState = uiState,
                    onReveal = {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        viewModel.revealNext()
                    },
                    onReset = { viewModel.reset() }
                )
            }
        }
    }
}

@Composable
private fun ShufflingAnimation() {
    val transition = rememberInfiniteTransition(label = "shuffling")
    val offset by transition.animateFloat(
        initialValue = -20f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(150, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shuffleOffset"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            repeat(3) { index ->
                CardBack(
                    compact = true,
                    modifier = Modifier
                        .graphicsLayer {
                            translationX = offset * (index - 1)
                            rotationZ = (index - 1) * 5f
                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.state_shuffling),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 2.sp
        )
    }
}

@Composable
private fun PreparationPhase(
    mode: DrawMode,
    onDraw: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = mode.label,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = when (mode) {
                DrawMode.DAY_CARD ->
                    "Respire fundo.\nFormule sua intenção para hoje e toque para receber sua carta."
                DrawMode.PAST_PRESENT_FUTURE ->
                    "Respire fundo.\nPense na situação que deseja compreender e toque para iniciar sua leitura."
                DrawMode.SIM_NAO ->
                    "Respire fundo.\nFaça uma pergunta clara de Sim ou Não e toque para receber a resposta."
                DrawMode.DESAFIO_SOLUCAO ->
                    "Respire fundo.\nPense em um problema atual e toque para ver o desafio e a solução."
                DrawMode.RELACIONAMENTO ->
                    "Respire fundo.\nPense na conexão entre você e outra pessoa e toque para iniciar a leitura."
            },
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            lineHeight = 26.sp
        )

        Spacer(modifier = Modifier.height(56.dp))

        Button(
            onClick = onDraw,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = stringResource(R.string.action_draw_oracle),
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RevealPhase(
    uiState: DrawUiState,
    onReveal: () -> Unit,
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = uiState.mode.label,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Light,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            when (uiState.mode) {
                DrawMode.DAY_CARD, DrawMode.SIM_NAO -> {
                    val card = uiState.cards.first()
                    val isRevealed = uiState.revealedCount >= 1
                    DayCardLayout(
                        card = card,
                        isRevealed = isRevealed,
                        onReveal = if (!isRevealed) onReveal else null
                    )
                }

                DrawMode.PAST_PRESENT_FUTURE, DrawMode.DESAFIO_SOLUCAO, DrawMode.RELACIONAMENTO -> {
                    GenericSpreadLayout(
                        cards = uiState.cards,
                        revealedCount = uiState.revealedCount,
                        onReveal = if (!uiState.allRevealed) onReveal else null
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (!uiState.allRevealed) {
                val remaining = uiState.cards.size - uiState.revealedCount
                Text(
                    text = if (remaining == uiState.cards.size)
                        stringResource(R.string.action_tap_to_reveal)
                    else
                        "Toque para revelar a próxima carta",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f),
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                )
            }
        }

        if (uiState.allRevealed) {
            CompletedReading(
                cards = uiState.cards,
                mode = uiState.mode,
                cartaDoDiaJaExistia = uiState.cartaDoDiaJaExistia,
                onReset = onReset
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun DayCardLayout(
    card: DrawCard,
    isRevealed: Boolean,
    onReveal: (() -> Unit)?
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FlippableCard(
            card = card,
            isRevealed = isRevealed,
            onClick = onReveal
        )
    }
}

@Composable
private fun GenericSpreadLayout(
    cards: List<DrawCard>,
    revealedCount: Int,
    onReveal: (() -> Unit)?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
    ) {
        cards.forEachIndexed { index, drawCard ->
            val isRevealed = index < revealedCount
            val isNext = index == revealedCount
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = drawCard.position.label,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isRevealed)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f),
                    letterSpacing = 1.sp,
                    textAlign = TextAlign.Center,
                    minLines = 2
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlippableCard(
                    card = drawCard,
                    isRevealed = isRevealed,
                    onClick = if (isNext) onReveal else null,
                    compact = true
                )
                if (isRevealed) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = drawCard.card.name,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center,
                        maxLines = 2
                    )
                }
            }
        }
    }
}

@Composable
fun FlippableCard(
    card: DrawCard,
    isRevealed: Boolean,
    onClick: (() -> Unit)?,
    compact: Boolean = false
) {
    val rotation by animateFloatAsState(
        targetValue = if (isRevealed) 180f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "cardFlip"
    )
    val cardHeight = if (compact) 180.dp else 320.dp
    val cardWidth  = if (compact) 110.dp else 200.dp

    Box(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .clip(RoundedCornerShape(12.dp))
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            },
        contentAlignment = Alignment.Center
    ) {
        if (rotation <= 90f) {
            CardBack(compact = compact)
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { rotationY = 180f }
            ) {
                Image(
                    painter = painterResource(id = card.card.imageResId),
                    contentDescription = card.card.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
private fun CardBack(
    compact: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(if (compact) 110.dp else 200.dp)
            .height(if (compact) 180.dp else 320.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.colorScheme.surface
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "✦",
            fontSize = if (compact) 28.sp else 48.sp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun CompletedReading(
    cards: List<DrawCard>,
    mode: DrawMode,
    cartaDoDiaJaExistia: Boolean = false,
    onReset: () -> Unit
) {
    var expandedIndex by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(modifier = Modifier.padding(bottom = 24.dp)) {
            cards.forEachIndexed { index, drawCard ->
                CardInterpretation(
                    drawCard = drawCard,
                    showPosition = mode != DrawMode.DAY_CARD && mode != DrawMode.SIM_NAO,
                    isExpanded = expandedIndex == index,
                    onToggle = { expandedIndex = if (expandedIndex == index) -1 else index }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                if (mode == DrawMode.DAY_CARD) {
                    if (cartaDoDiaJaExistia) {
                        Text(
                            text = stringResource(R.string.msg_day_card_exists),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                } else {
                    Button(
                        onClick = onReset,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Nova Leitura", letterSpacing = 1.sp)
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun CardInterpretation(
    drawCard: DrawCard,
    showPosition: Boolean,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "arrowRotation"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(300))
            .clickable { onToggle() }
            .padding(vertical = 16.dp, horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                if (showPosition) {
                    Text(
                        text = drawCard.position.label.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }
                Text(
                    text = drawCard.card.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            }

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Recolher" else "Expandir",
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                modifier = Modifier.graphicsLayer { rotationZ = rotation }
            )
        }

        if (isExpanded) {
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = if (drawCard.card.arcana == ArcanaType.MAJOR) "Arcano Maior" else "Arcano Menor · ${drawCard.card.suit?.displayName}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            DetailSection(stringResource(R.string.label_essence), drawCard.card.essence)
            DetailSection(stringResource(R.string.label_light_path), drawCard.card.lightMeaning)
            DetailSection(stringResource(R.string.label_shadow_challenge), drawCard.card.shadowMeaning)
            DetailSection(stringResource(R.string.label_oracle_guidance), drawCard.card.guidanceWhenReversed)
            
            if (drawCard.card.keywordsLight.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                KeywordsRow(keywords = drawCard.card.keywordsLight)
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(top = 16.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
    }
}

@Composable
private fun KeywordsRow(keywords: List<String>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        keywords.take(4).forEach { keyword ->
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = keyword,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 10.sp
                )
            }
        }
    }
}

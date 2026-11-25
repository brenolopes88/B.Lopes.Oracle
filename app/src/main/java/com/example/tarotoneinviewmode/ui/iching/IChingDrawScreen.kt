package com.example.tarotoneinviewmode.ui.iching

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarotoneinviewmode.domain.model.IChingHexagram
import com.example.tarotoneinviewmode.ui.navigation.NavRoute
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.platform.LocalContext
import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.domain.model.Trigram
import com.example.tarotoneinviewmode.ui.components.DetailSection
import com.example.tarotoneinviewmode.ui.components.TrigramBadge
import com.example.tarotoneinviewmode.ui.theme.TarotDesignTokens
import com.example.tarotoneinviewmode.util.ShareUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IChingDrawScreen(
    viewModel: IChingDrawViewModel,
    onBack: () -> Unit,
    onShowGuide: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
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
                    if (uiState.drawState is DrawState.Result) {
                        val state = uiState.drawState as DrawState.Result
                        val shareText = buildString {
                            appendLine("─── ☯ I CHING ☯ ───")
                            appendLine("📜 CONSULTA: ${uiState.mode.title}")
                            appendLine()
                            appendLine("${stringResource(R.string.label_present)}: ${state.hexagram.number}. ${state.hexagram.nameTranslated}")
                            appendLine("\"${state.hexagram.summary}\"")
                            if (state.secondHexagram != null) {
                                appendLine()
                                appendLine("${stringResource(R.string.label_future)}: ${state.secondHexagram.number}. ${state.secondHexagram.nameTranslated}")
                                appendLine("\"${state.secondHexagram.summary}\"")
                            }
                            appendLine()
                            append("✨ Sintonize sua intenção no app Oráculo.")
                        }

                        IconButton(onClick = { ShareUtils.copyToClipboard(context, shareText) }) {
                            Icon(Icons.Default.ContentCopy, contentDescription = "Copiar", tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = { ShareUtils.shareText(context, shareText) }) {
                            Icon(Icons.Default.Share, contentDescription = "Compartilhar", tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                    IconButton(onClick = { onShowGuide(NavRoute.Guide.TOPIC_ICHING) }) {
                        Icon(Icons.Default.Info, contentDescription = "Sobre o I Ching", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent,
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Fundo Padronizado para Interpretação (Apenas na fase de resultado)
            if (uiState.drawState is DrawState.Result) {
                Image(
                    painter = painterResource(id = R.drawable.bg_default),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alpha = TarotDesignTokens.Alpha.BgImageInterpretation
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Título da Consulta
                Text(
                    text = uiState.mode.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center
                )

                when (val state = uiState.drawState) {
                    is DrawState.Intention -> {
                        IntentionSection(
                            pergunta = uiState.pergunta,
                            onPerguntaChanged = viewModel::onPerguntaChanged,
                            onConsultar = {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                viewModel.realizarSorteio()
                            }
                        )
                    }

                    is DrawState.FlippingCoins -> {
                        FlippingCoinsSection()
                    }

                    is DrawState.Result -> {
                        ResultSection(
                            hexagram = state.hexagram,
                            secondHexagram = state.secondHexagram,
                            mode = uiState.mode,
                            onReset = viewModel::reset
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun IntentionSection(
    pergunta: String,
    onPerguntaChanged: (String) -> Unit,
    onConsultar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        
        Text(
            text = "䷀",
            fontSize = 72.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Concentre-se em sua questão",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "O I Ching responderá com a sabedoria das Mutações.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onConsultar,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = stringResource(R.string.action_launch_coins), fontSize = 16.sp)
        }
    }
}

@Composable
private fun FlippingCoinsSection() {
    val transition = rememberInfiniteTransition(label = "coinFlip")
    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "coinRotation"
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .graphicsLayer {
                            rotationY = rotation + (index * 40f)
                        }
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                            shape = CircleShape
                        )
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (rotation > 180f) "☯" else "◯",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 24.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.state_flipping_coins),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = stringResource(R.string.state_calculating_lines),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun ResultSection(
    hexagram: IChingHexagram,
    secondHexagram: IChingHexagram? = null,
    mode: IChingMode,
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (secondHexagram != null) {
            MutationHeader(first = hexagram, second = secondHexagram)
        } else {
            Box(
                modifier = Modifier
                    .padding(vertical = 32.dp)
                    .size(160.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = hexagram.unicodeSymbol,
                    fontSize = 120.sp,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Conteúdo Estilo Pergaminho
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (secondHexagram != null) {
                    Text(
                        text = stringResource(R.string.label_mutation),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    DetailSection("${stringResource(R.string.label_present)}: ${hexagram.number}. ${hexagram.nameTranslated}", hexagram.summary)
                    DetailSection("${stringResource(R.string.label_future)}: ${secondHexagram.number}. ${secondHexagram.nameTranslated}", secondHexagram.summary)
                } else {
                    Text(
                        text = "${hexagram.number}. ${hexagram.nameTranslated}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "${hexagram.nameOriginal} (${hexagram.pinyin})",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        TrigramBadge(label = stringResource(R.string.label_upper), trigram = hexagram.trigramUpper, modifier = Modifier.weight(1f))
                        TrigramBadge(label = stringResource(R.string.label_lower), trigram = hexagram.trigramLower, modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    DetailSection(stringResource(R.string.label_summary), hexagram.summary)
                    DetailSection(stringResource(R.string.label_judgment), hexagram.judgment)
                    DetailSection(stringResource(R.string.label_image), hexagram.imageText)
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (mode != IChingMode.DAILY) {
                    Button(
                        onClick = onReset,
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Nova Consulta")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun MutationHeader(first: IChingHexagram, second: IChingHexagram) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = first.unicodeSymbol, fontSize = 80.sp, color = MaterialTheme.colorScheme.primary)
        Text(
            text = "→", 
            fontSize = 32.sp, 
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
        )
        Text(text = second.unicodeSymbol, fontSize = 80.sp, color = MaterialTheme.colorScheme.primary)
    }
}

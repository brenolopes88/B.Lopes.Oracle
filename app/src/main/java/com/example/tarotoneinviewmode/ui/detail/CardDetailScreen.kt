package com.example.tarotoneinviewmode.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.domain.enums.ArcanaType
import com.example.tarotoneinviewmode.domain.enums.Suit
import com.example.tarotoneinviewmode.domain.model.TarotCard
import com.example.tarotoneinviewmode.ui.navigation.NavRoute
import com.example.tarotoneinviewmode.util.ShareUtils
import com.example.tarotoneinviewmode.ui.components.DetailSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardDetailScreen(
    viewModel: CardDetailViewModel,
    onBack: () -> Unit,
    onShowGuide: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val card = uiState.card
    val error = uiState.error
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
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    card?.let {
                        val emoji = when (it.suit) {
                            Suit.WANDS -> "🔥"
                            Suit.CUPS -> "🌊"
                            Suit.SWORDS -> "🌬️"
                            Suit.PENTACLES -> "🌿"
                            else -> "✨"
                        }
                        val shareText = buildString {
                            appendLine("─── ✧ TAROT ✧ ───")
                            appendLine("🃏 CARTA: ${it.name}")
                            appendLine("Elemento: $emoji")
                            appendLine()
                            appendLine("\"${it.essence}\"")
                            appendLine()
                            appendLine("💡 CONSELHO:")
                            appendLine(it.lightMeaning)
                            appendLine()
                            append("✨ Sintonize sua intenção no app Oráculo.")
                        }

                        // 🚀 BOTÃO FAVORITO
                        IconButton(onClick = { viewModel.toggleFavorite() }) {
                            Icon(
                                imageVector = if (uiState.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = stringResource(R.string.cd_favorite),
                                tint = if (uiState.isFavorite) Color.Red else MaterialTheme.colorScheme.primary
                            )
                        }

                        IconButton(onClick = { ShareUtils.copyToClipboard(context, shareText) }) {
                            Icon(Icons.Default.ContentCopy, contentDescription = stringResource(R.string.cd_copy), tint = MaterialTheme.colorScheme.primary)
                        }
                        IconButton(onClick = { ShareUtils.shareText(context, shareText) }) {
                            Icon(Icons.Default.Share, contentDescription = stringResource(R.string.cd_share), tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                    IconButton(onClick = { onShowGuide(NavRoute.Guide.TOPIC_TAROT) }) {
                        Icon(Icons.Default.Info, contentDescription = stringResource(R.string.cd_info_tarot), tint = MaterialTheme.colorScheme.primary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            // 🚀 Fundo Padronizado para Interpretação
            Image(
                painter = painterResource(id = R.drawable.bg_default),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.3f
            )

            when {
                uiState.isLoading -> LoadingState()
                error != null -> ErrorState(message = error, onBack = onBack)
                card != null -> CardContent(
                    card = card,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun ErrorState(
    message: String,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onBack) {
                Text(stringResource(R.string.nav_back))
            }
        }
    }
}

@Composable
private fun CardContent(
    card: TarotCard,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagem da carta com "moldura"
        Surface(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .width(220.dp)
                .height(380.dp),
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 8.dp,
            shadowElevation = 12.dp
        ) {
            Image(
                painter = painterResource(id = card.imageResId),
                contentDescription = card.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
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
                Text(
                    text = card.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 1.sp
                )
                
                Text(
                    text = if (card.arcana == ArcanaType.MAJOR) stringResource(R.string.label_major_arcana) else "${stringResource(R.string.label_minor_arcana)} · ${card.suit?.displayName}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                DetailSection(stringResource(R.string.label_essence), card.essence)
                DetailSection(stringResource(R.string.label_light_path), card.lightMeaning)
                DetailSection(stringResource(R.string.label_shadow_challenge), card.shadowMeaning)
                DetailSection(stringResource(R.string.label_oracle_guidance), card.guidanceWhenReversed)

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

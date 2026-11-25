package com.example.tarotoneinviewmode.ui.iching

import androidx.compose.foundation.BorderStroke
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
import com.example.tarotoneinviewmode.domain.model.Trigram
import com.example.tarotoneinviewmode.ui.navigation.NavRoute
import com.example.tarotoneinviewmode.ui.theme.TarotDesignTokens
import com.example.tarotoneinviewmode.util.ShareUtils
import com.example.tarotoneinviewmode.ui.components.DetailSection
import com.example.tarotoneinviewmode.ui.components.TrigramBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HexagramDetailScreen(
    viewModel: HexagramDetailViewModel,
    onBack: () -> Unit,
    onShowGuide: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val hexagram = uiState.hexagram ?: return

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
                    val shareText = buildString {
                        appendLine("─── ☯ I CHING ☯ ───")
                        appendLine("📜 HEXAGRAMA ${hexagram.number}: ${hexagram.nameTranslated}")
                        appendLine()
                        appendLine("\"${hexagram.summary}\"")
                        appendLine()
                        appendLine("💡 JULGAMENTO:")
                        appendLine(hexagram.judgment)
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
                    IconButton(onClick = { onShowGuide(NavRoute.Guide.TOPIC_ICHING) }) {
                        Icon(Icons.Default.Info, contentDescription = stringResource(R.string.cd_info_iching), tint = MaterialTheme.colorScheme.primary)
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
                alpha = TarotDesignTokens.Alpha.BgImageInterpretation
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 32.dp)
                        .size(160.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = hexagram.unicodeSymbol,
                        fontSize = 120.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

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
                            text = "${hexagram.number}. ${hexagram.nameTranslated}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "${hexagram.nameOriginal} (${hexagram.pinyin})",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // 🚀 Adicionado Badges de Trigramas para paridade com a tela de sorteio
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

                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
            }
        }
    }
}

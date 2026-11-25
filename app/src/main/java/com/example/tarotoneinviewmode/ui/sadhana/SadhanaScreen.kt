package com.example.tarotoneinviewmode.ui.sadhana

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterVintage
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tarotoneinviewmode.ui.components.CelebrationOverlay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SadhanaScreen(
    viewModel: SadhanaViewModel,
    onShowGuide: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    var showCelebration by remember { mutableStateOf(false) }
    
    // 🚀 Estado do Paywall
    var showPaywall by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        viewModel.efeitos.collect { efeito ->
            when (efeito) {
                is SadhanaEffect.CicloCompleto -> {
                    showCelebration = true
                    snackbarHostState.showSnackbar(
                        message = "✨ Parabéns! Você completou o ciclo de: ${efeito.titulo}",
                        duration = SnackbarDuration.Long
                    )
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            containerColor = Color.Transparent
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // ── tabs de categoria ──
                ScrollableTabRow(
                    selectedTabIndex = uiState.categorias.indexOf(uiState.categoriaSelecionada),
                    containerColor = Color.Transparent,
                    modifier = Modifier.padding(4.dp),
                    divider = {},
                    edgePadding = 16.dp
                ) {
                    uiState.categorias.forEach { categoria ->
                        val isSelected = uiState.categoriaSelecionada == categoria
                        Tab(
                            selected = isSelected,
                            onClick = { viewModel.onCategoriaSelected(categoria) },
                            text = {
                                Text(
                                    text = categoria,
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                    color = if (isSelected)
                                        MaterialTheme.colorScheme.primary
                                    else
                                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                                )
                            }
                        )
                    }
                }

                when {
                    uiState.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }
                    }

                    uiState.itens.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Nenhuma prática disponível ainda.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    else -> {
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(uiState.itens, key = { it.pratica.id }) { item ->
                                PraticaItem(
                                    item = item,
                                    onToggle = { 
                                        if (item.pratica.isPremium && !uiState.isUserPremium) {
                                            showPaywall = true
                                        } else {
                                            viewModel.onPraticaToggle(item.pratica.id)
                                        }
                                    },
                                    onDiaCompleto = { viewModel.registrarDiaCompleto(item.pratica.id) }
                                )
                            }
                        }
                    }
                }
            }
        }

        // 🚀 CELEBRAÇÃO MÍSTICA
        if (showCelebration) {
            CelebrationOverlay(onFinished = { showCelebration = false })
        }

        // 💎 PAYWALL BOTTOM SHEET
        if (showPaywall) {
            ModalBottomSheet(
                onDismissRequest = { showPaywall = false },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.surface,
                dragHandle = { BottomSheetDefaults.DragHandle() }
            ) {
                PaywallContent(onSubscribe = { /* Integrar Google Play futuramente */ })
            }
        }
    }
}

@Composable
private fun PaywallContent(onSubscribe: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.FilterVintage,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(64.dp)
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Desbloqueie o Sadhana Premium",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Tenha acesso a meditações exclusivas, mantras ancestrais e ferramentas avançadas de autoconhecimento.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            lineHeight = 26.sp
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = onSubscribe,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Tornar-se Premium",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Em breve: Integração com a Google Play Store",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        )
    }
}

@Composable
private fun PraticaItem(
    item: PraticaUiItem,
    onToggle: () -> Unit,
    onDiaCompleto: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = 300))
            .background(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp)
                .clickable { onToggle() }
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.pratica.titulo,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                    if (item.pratica.isPremium) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.FilterVintage,
                            contentDescription = "Premium",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⏱ ${item.pratica.duracao}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    if (item.pratica.ciclo != null) {
                        val dias = item.progresso?.diasCompletos ?: 0
                        Text(
                            text = "•  $dias/${item.pratica.ciclo} dias",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }

            Icon(
                imageVector = if (item.expandida) Icons.Default.KeyboardArrowUp
                else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }

        if (item.expandida) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            ) {
                HorizontalDivider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = item.pratica.descricao,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                item.pratica.ciclo?.let { ciclo ->
                    CicloProgressoVisual(
                        diasCompletos = item.progresso?.diasCompletos ?: 0,
                        cicloTotal = ciclo,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                AudioPlayer(
                    url = item.pratica.audioUrl,
                    titulo = item.pratica.titulo,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                            RoundedCornerShape(8.dp)
                        )
                )

                item.pratica.ciclo?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onDiaCompleto,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Marcar Dia como Concluído",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CicloProgressoVisual(
    diasCompletos: Int,
    cicloTotal: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            repeat(cicloTotal) { index ->
                val dia = index + 1
                val isCompleto = dia <= diasCompletos
                val isProximo = dia == diasCompletos + 1

                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(
                            color = when {
                                isCompleto -> MaterialTheme.colorScheme.primary
                                isProximo -> MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                                else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                            },
                            shape = CircleShape
                        )
                        .then(
                            if (isProximo) Modifier.border(
                                1.dp,
                                MaterialTheme.colorScheme.primary,
                                CircleShape
                            ) else Modifier
                        )
                )
            }
        }
    }
}

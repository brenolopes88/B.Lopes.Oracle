package com.example.tarotoneinviewmode.ui.iching
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarotoneinviewmode.domain.model.IChingHexagram
import com.example.tarotoneinviewmode.domain.model.Trigram
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.filled.*

import coil.compose.AsyncImage
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import com.example.tarotoneinviewmode.R

import com.example.tarotoneinviewmode.ui.theme.DeepIndigo
import com.example.tarotoneinviewmode.ui.theme.TarotGold

@Composable
fun IChingArchiveScreen(
    hexagrams: List<IChingHexagram>,
    selectedTrigram: Trigram?,
    onTrigramSelected: (Trigram?) -> Unit,
    onHexagramClick: (Int) -> Unit,
    showOnlyFavorites: Boolean = false,
    onToggleFavorites: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var expandedHexagramNumber by remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current

    // 🚀 Lógica de Fundo Condicional baseada no Trigrama selecionado
    val backgroundRes = when (selectedTrigram) {
        Trigram.HEAVEN -> R.drawable.bg_ceu
        Trigram.EARTH -> R.drawable.bg_terra
        Trigram.THUNDER -> R.drawable.bg_trovao
        Trigram.WATER -> R.drawable.bg_agua
        Trigram.MOUNTAIN -> R.drawable.bg_montanha
        Trigram.WIND -> R.drawable.bg_vento
        Trigram.FIRE -> R.drawable.bg_fogo
        Trigram.LAKE -> R.drawable.bg_lago
        else -> R.drawable.bg_major // Fundo padrão quando nenhum está selecionado
    }

    Box(modifier = modifier.fillMaxSize()) {
        // 🚀 Fundo místico assíncrono e condicional com transição suave
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(backgroundRes)
                .crossfade(500)
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.6f
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Filtro por Trigramas no topo
            TrigramFilterRow(
                selectedTrigram = selectedTrigram,
                onTrigramSelected = onTrigramSelected,
                showOnlyFavorites = showOnlyFavorites,
                onToggleFavorites = onToggleFavorites
            )

            // Lista de Hexagramas
            HexagramExpandableList(
                hexagrams = hexagrams,
                expandedHexagramNumber = expandedHexagramNumber,
                onHexagramExpanded = { newNumber ->
                    expandedHexagramNumber = newNumber
                },
                onDetailClick = onHexagramClick
            )
        }
    }
}

@Composable
fun HexagramExpandableList(
    hexagrams: List<IChingHexagram>,
    expandedHexagramNumber: Int?,
    onHexagramExpanded: (Int?) -> Unit,
    onDetailClick: (Int) -> Unit, // 🚀 Adicionado
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 140.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = hexagrams,
            key = { it.number },
            contentType = { "hexagram_card_item" } // 🚀 Reciclagem rápida de itens na lista
        ) { hexagram ->
            val isExpanded = hexagram.number == expandedHexagramNumber

            HexagramCardItem(
                hexagram = hexagram,
                isExpanded = isExpanded,
                onToggle = {
                    onHexagramExpanded(if (isExpanded) null else hexagram.number)
                },
                onDetailClick = { onDetailClick(hexagram.number) } // 🚀 Passa para o item
            )
        }
    }
}

@Composable
fun HexagramCardItem(
    hexagram: IChingHexagram,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    onDetailClick: () -> Unit, // 🚀 Adicionado
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.65f),
                shape = RoundedCornerShape(10.dp)
            )
            .animateContentSize(animationSpec = tween(durationMillis = 250)) // 🚀 Suavidade de expansão fluida sem travar o layout
    ) {
        // Linha recolhida (Cabeçalho da lâmina)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp) // 🚀 Padronizado com Tarot
                .clickable { onToggle() }
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = hexagram.unicodeSymbol,
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(end = 12.dp)
                )

                Column {
                    Text(
                        text = "${hexagram.number}. ${hexagram.nameTranslated}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${hexagram.nameOriginal} (${hexagram.pinyin})",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp
                else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Recolher" else "Expandir",
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
            )
        }

        // Conteúdo expandido apenas renderizado quando necessário
        if (isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            ) {
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = hexagram.summary,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "O Julgamento",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = hexagram.judgment,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.88f),
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "A Imagem",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = hexagram.imageText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = onDetailClick, // 🚀 Agora funciona!
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = "Ver detalhes completos",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TrigramFilterRow(
    selectedTrigram: Trigram?,
    onTrigramSelected: (Trigram?) -> Unit,
    showOnlyFavorites: Boolean,
    onToggleFavorites: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 🚀 Usar .entries garante performance e zero alocação de memória a cada recomposição
    val trigrams = remember { Trigram.entries }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .background(DeepIndigo)
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item(key = "all_filter", contentType = "filter_chip") {
            FilterChip(
                selected = selectedTrigram == null && !showOnlyFavorites,
                onClick = { onTrigramSelected(null) },
                label = { Text("Todos") },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color.Transparent,
                    selectedLabelColor = TarotGold
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = selectedTrigram == null && !showOnlyFavorites,
                    borderColor = TarotGold.copy(alpha = 0.3f),
                    selectedBorderColor = TarotGold,
                    selectedBorderWidth = 1.dp
                )
            )
        }

        items(
            items = trigrams,
            key = { it.name },
            contentType = { "filter_chip" }
        ) { trigram ->
            val isSelected = selectedTrigram == trigram
            FilterChip(
                selected = isSelected,
                onClick = {
                    onTrigramSelected(if (isSelected) null else trigram)
                },
                label = { Text("${trigram.symbol} ${trigram.title}") },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color.Transparent,
                    selectedLabelColor = TarotGold
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = isSelected,
                    borderColor = TarotGold.copy(alpha = 0.3f),
                    selectedBorderColor = TarotGold,
                    selectedBorderWidth = 1.dp
                )
            )
        }

        item(key = "favorites_filter", contentType = "filter_chip") {
            FilterChip(
                selected = showOnlyFavorites,
                onClick = onToggleFavorites,
                label = { 
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (showOnlyFavorites) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = if (showOnlyFavorites) Color.Red else TarotGold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Favoritos")
                    }
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color.Transparent,
                    selectedLabelColor = TarotGold
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = showOnlyFavorites,
                    borderColor = TarotGold.copy(alpha = 0.3f),
                    selectedBorderColor = TarotGold,
                    selectedBorderWidth = 1.dp
                )
            )
        }
    }
}
package com.example.tarotoneinviewmode.ui.archive

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.ui.theme.TarotDesignTokens
import com.example.tarotoneinviewmode.ui.theme.DeepIndigo
import com.example.tarotoneinviewmode.ui.theme.TarotGold
import com.example.tarotoneinviewmode.ui.theme.MoonSilver
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchiveScreen(
    viewModel: ArchiveViewModel,
    onCardClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val suitOverlayRes = uiState.selectedTab.backgroundRes
    val tabs = ArchiveTab.tabEntries

    Column(modifier = Modifier.fillMaxSize()) {

        // 🚀 Unificado com I Ching: Usando FilterChips em uma LazyRow
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(DeepIndigo)
                .padding(vertical = 8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(tabs) { tab ->
                val isSelected = uiState.selectedTab == tab
                FilterChip(
                    selected = isSelected,
                    onClick = { viewModel.onTabSelected(tab) },
                    label = { 
                        Text(
                            text = stringResource(tab.titleRes),
                            style = MaterialTheme.typography.labelMedium
                        ) 
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color.Transparent,
                        selectedLabelColor = TarotGold,
                        labelColor = MoonSilver.copy(alpha = 0.6f)
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = isSelected,
                        borderColor = MoonSilver.copy(alpha = 0.4f),
                        selectedBorderColor = TarotGold,
                        selectedBorderWidth = 1.dp
                    )
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            // 🚀 Fundo Dinâmico Unificado (Similar ao I Ching)
            val backgroundRes = suitOverlayRes ?: R.drawable.bg_default
            
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(backgroundRes)
                    .crossfade(500) // 🚀 Transição suave de meio segundo
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = if (suitOverlayRes != null) TarotDesignTokens.Alpha.BgImageArchive else 0.6f
            )

            CardExpandableList(
                cards = uiState.cards,
                expandedCardId = uiState.expandedCardId,
                onCardExpanded = { viewModel.onCardExpanded(it) },
                onCardClick = onCardClick
            )
        }
    }
}

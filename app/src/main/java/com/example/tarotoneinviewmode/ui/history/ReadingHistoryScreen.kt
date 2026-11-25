package com.example.tarotoneinviewmode.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.domain.model.draw.DrawCard
import com.example.tarotoneinviewmode.ui.theme.TarotGold
import com.example.tarotoneinviewmode.ui.theme.MoonSilver
import com.example.tarotoneinviewmode.ui.theme.TarotDesignTokens
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingHistoryScreen(
    viewModel: ReadingHistoryViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = stringResource(R.string.nav_history),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Light,
                        letterSpacing = 2.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.nav_back),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 🚀 Filtros
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HistoryFilter.entries.forEach { filter ->
                    FilterChip(
                        selected = uiState.currentFilter == filter,
                        onClick = { viewModel.onFilterChanged(filter) },
                        label = {
                            Text(
                                text = when(filter) {
                                    HistoryFilter.ALL -> stringResource(R.string.filter_all)
                                    HistoryFilter.TAROT -> stringResource(R.string.nav_tarot)
                                    HistoryFilter.ICHING -> stringResource(R.string.nav_iching)
                                    HistoryFilter.FAVORITES -> stringResource(R.string.filter_favorites)
                                },
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
                            selected = uiState.currentFilter == filter,
                            borderColor = MoonSilver.copy(alpha = 0.4f),
                            selectedBorderColor = TarotGold,
                            selectedBorderWidth = 1.dp
                        )
                    )
                }
            }

            if (uiState.readings.isEmpty()) {
                EmptyHistory()
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 140.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = uiState.readings,
                        key = { it.id }
                    ) { item ->
                        ReadingHistoryItem(
                            item = item,
                            onNoteChange = { note ->
                                viewModel.updateNote(item.id, note)
                            },
                            onToggleFavorite = {
                                viewModel.toggleFavorite(item.id, item.isFavorite)
                            },
                            onDeleteClick = {
                                viewModel.deleteReading(
                                    readingId = item.id,
                                    timestamp = item.timestamp,
                                    tipoLabel = item.tipo
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyHistory() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "✦",
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.history_empty_title),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.45f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.history_empty_desc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
private fun ReadingHistoryItem(
    item: ReadingHistoryUiItem,
    onNoteChange: (String) -> Unit,
    onToggleFavorite: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var noteText by remember(item.id) { mutableStateOf(item.note) }
    var mostrarConfirmacaoDeletar by remember { mutableStateOf(false) }

    val isIChing = item.hexagramaNumber != null || item.tipo == "ICHING_DAY"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp)
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.modeLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isIChing) MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                    else MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.height(4.dp))

                if (isIChing) {
                    val hex = item.hexagrama
                    val nomeHex = hex?.let { "${it.number}. ${it.nameTranslated}" } ?: "Hexagrama ${item.hexagramaNumber}"
                    val subtituloHex = hex?.let { "(${it.nameOriginal} • ${it.pinyin})" }
                    val hasMutation = item.hexagramaFutureNumber != null
                    val hexFut = item.hexagramaFuture
                    val nomeFut = hexFut?.let { "${it.number}. ${it.nameTranslated}" } ?: "Hex. ${item.hexagramaFutureNumber}"
                    val subtituloFut = hexFut?.let { "(${it.nameOriginal} • ${it.pinyin})" }

                    Column {
                        Text(
                            text = buildString {
                                append(getHexagramaUnicode(item.hexagramaNumber ?: 1))
                                append(" ")
                                append(nomeHex)
                                if (hasMutation) {
                                    append(" → ")
                                    append(getHexagramaUnicode(item.hexagramaFutureNumber ?: 1))
                                    append(" ")
                                    append(nomeFut)
                                }
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                        if (subtituloHex != null) {
                            Text(
                                text = if (hasMutation && subtituloFut != null) "$subtituloHex → $subtituloFut" else subtituloHex,
                                style = MaterialTheme.typography.labelSmall,
                                color = TarotDesignTokens.Colors.TextSupport
                            )
                        }
                    }
                } else {
                    Text(
                        text = item.tarotReading?.cards?.joinToString(" · ") { it.card.name } ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formatTimestamp(item.timestamp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            }

            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = if (item.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(R.string.cd_favorite),
                    tint = if (item.isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    modifier = Modifier.size(20.dp)
                )
            }

            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp
                else Icons.Default.KeyboardArrowDown,
                contentDescription = if (expanded) stringResource(R.string.cd_collapse) else stringResource(R.string.cd_expand),
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            )
        }


        if (expanded) {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                if (isIChing) {
                    item.hexagrama?.let { hex ->
                        if (item.hexagramaFuture != null) {
                            Text(
                                text = "${stringResource(R.string.label_present)}:",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                            )
                        }
                        Text(
                            text = hex.judgment,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            lineHeight = 18.sp
                        )
                        
                        if (item.hexagramaFuture != null) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "${stringResource(R.string.label_future)}:",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                            )
                            Text(
                                text = item.hexagramaFuture.judgment,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                lineHeight = 18.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                } else {
                    item.tarotReading?.cards?.forEach { drawCard ->
                        MiniCardRow(drawCard = drawCard)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                NoteField(
                    note = noteText,
                    onNoteChange = { newNote ->
                        noteText = newNote
                        onNoteChange(newNote)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { mostrarConfirmacaoDeletar = true },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.action_delete_reading),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }

    if (mostrarConfirmacaoDeletar) {
        AlertDialog(
            onDismissRequest = { mostrarConfirmacaoDeletar = false },
            title = {
                Text(
                    stringResource(R.string.dialog_delete_title),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            text = {
                Text(
                    stringResource(R.string.dialog_delete_desc),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteClick()
                        mostrarConfirmacaoDeletar = false
                    }
                ) {
                    Text(
                        stringResource(R.string.action_delete),
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Medium
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarConfirmacaoDeletar = false }) {
                    Text(stringResource(R.string.action_cancel))
                }
            }
        )
    }
}

@Composable
private fun MiniCardRow(drawCard: DrawCard) {
    Column {
        if (drawCard.position.label != "Carta do Dia") {
            Text(
                text = drawCard.position.label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                letterSpacing = 1.5.sp,
                fontSize = 9.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
        Text(
            text = drawCard.card.name,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = drawCard.card.essence.take(100) + if (drawCard.card.essence.length > 100) "…" else "",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
            lineHeight = 18.sp,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun NoteField(
    note: String,
    onNoteChange: (String) -> Unit
) {
    Column {
        Text(
            text = stringResource(R.string.label_notes),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
            letterSpacing = 1.5.sp,
            fontSize = 9.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp)
        ) {
            if (note.isEmpty()) {
                Text(
                    text = stringResource(R.string.note_placeholder),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f),
                    fontStyle = FontStyle.Italic
                )
            }

            BasicTextField(
                value = note,
                onValueChange = onNoteChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 80.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
            )
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd 'de' MMMM · HH:mm", Locale("pt", "BR"))
    return sdf.format(Date(timestamp))
}

private fun getHexagramaUnicode(numero: Int): String {
    val codePoint = 0x4DC0 + (numero - 1).coerceIn(0, 63)
    return String(Character.toChars(codePoint))
}

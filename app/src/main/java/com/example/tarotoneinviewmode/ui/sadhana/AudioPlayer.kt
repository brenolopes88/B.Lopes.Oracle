package com.example.tarotoneinviewmode.ui.sadhana

import android.content.ComponentName
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun AudioPlayer(
    url: String,
    titulo: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val controllerFuture = remember {
        val sessionToken = SessionToken(context, ComponentName(context, SadhanaPlaybackService::class.java))
        MediaController.Builder(context, sessionToken).buildAsync()
    }
    var controller by remember { mutableStateOf<MediaController?>(null) }

    var isPlaying by remember { mutableStateOf(false) }
    var progresso by remember { mutableFloatStateOf(0f) }
    var duracaoTotal by remember { mutableLongStateOf(0L) }
    var posicaoAtual by remember { mutableLongStateOf(0L) }

    DisposableEffect(controllerFuture) {
        controllerFuture.addListener({
            controller = controllerFuture.get()
            
            // Se já tem algo tocando e é diferente dessa URL, o player decide o que fazer.
            // Aqui, se o usuário abriu este player, vamos carregar a URL se ela não estiver carregada.
            controller?.let { player ->
                if (player.currentMediaItem?.mediaId != url) {
                    val mediaItem = MediaItem.Builder()
                        .setMediaId(url)
                        .setUri(url)
                        .build()
                    player.setMediaItem(mediaItem)
                    player.prepare()
                }
                
                isPlaying = player.isPlaying
                duracaoTotal = player.duration.coerceAtLeast(0L)
            }
        }, MoreExecutors.directExecutor())

        onDispose {
            MediaController.releaseFuture(controllerFuture)
        }
    }

    // Listener de estado
    LaunchedEffect(controller) {
        val player = controller ?: return@LaunchedEffect
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(playing: Boolean) {
                isPlaying = playing
            }
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_READY) {
                    duracaoTotal = player.duration.coerceAtLeast(0L)
                }
            }
        }
        player.addListener(listener)
    }

    // atualiza progresso
    LaunchedEffect(isPlaying, controller) {
        val player = controller
        while (isActive && isPlaying && player != null) {
            posicaoAtual = player.currentPosition.coerceAtLeast(0L)
            progresso = if (duracaoTotal > 0) {
                posicaoAtual.toFloat() / duracaoTotal.toFloat()
            } else 0f
            delay(500)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // barra de progresso
        Slider(
            value = progresso,
            onValueChange = { valor ->
                controller?.let { player ->
                    val novaPosicao = (valor * duracaoTotal).toLong()
                    player.seekTo(novaPosicao)
                    progresso = valor
                }
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // tempos
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatarTempo(posicaoAtual),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                fontSize = 10.sp
            )
            Text(
                text = formatarTempo(duracaoTotal),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                fontSize = 10.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // controles
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // voltar 10s
            IconButton(onClick = {
                controller?.let { player ->
                    player.seekTo((player.currentPosition - 10_000).coerceAtLeast(0))
                }
            }) {
                Text("↺", fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            }

            // play / pause
            FilledIconButton(
                onClick = {
                    controller?.let { player ->
                        if (player.isPlaying) player.pause() else player.play()
                    }
                },
                modifier = Modifier.size(56.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = if (isPlaying) "⏸" else "▶",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            // avançar 10s
            IconButton(onClick = {
                controller?.let { player ->
                    player.seekTo((player.currentPosition + 10_000).coerceAtMost(duracaoTotal))
                }
            }) {
                Text("↻", fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            }
        }
    }
}

private fun formatarTempo(ms: Long): String {
    val totalSegundos = ms / 1000
    val minutos = totalSegundos / 60
    val segundos = totalSegundos % 60
    return "%d:%02d".format(minutos, segundos)
}

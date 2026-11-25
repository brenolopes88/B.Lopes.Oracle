package com.example.tarotoneinviewmode.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import kotlinx.coroutines.delay
import kotlin.random.Random

data class Particle(
    val x: Float,
    var y: Float,
    val size: Float,
    val speed: Float,
    val color: Color,
    val rotation: Float,
    val rotationSpeed: Float,
    val drift: Float
)

@Composable
fun CelebrationOverlay(
    onFinished: () -> Unit
) {
    val particles = remember {
        List(50) {
            Particle(
                x = Random.nextFloat(),
                y = -0.1f,
                size = Random.nextFloat() * 15f + 5f,
                speed = Random.nextFloat() * 0.01f + 0.005f,
                color = if (Random.nextBoolean()) Color(0xFFFFD700) else Color(0xFFE1BEE7),
                rotation = Random.nextFloat() * 360f,
                rotationSpeed = Random.nextFloat() * 2f - 1f,
                drift = Random.nextFloat() * 0.002f - 0.001f
            )
        }
    }

    var frame by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        delay(4000) // Duração da celebração
        onFinished()
    }

    LaunchedEffect(Unit) {
        while (true) {
            withFrameMillis {
                particles.forEach { p ->
                    p.y += p.speed
                }
                frame++
            }
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        particles.forEach { p ->
            val xPos = p.x * size.width
            val yPos = p.y * size.height

            if (p.y < 1.1f) {
                rotate(p.rotation + (frame * p.rotationSpeed), pivot = androidx.compose.ui.geometry.Offset(xPos, yPos)) {
                    drawRect(
                        color = p.color.copy(alpha = 0.7f),
                        topLeft = androidx.compose.ui.geometry.Offset(xPos, yPos),
                        size = androidx.compose.ui.geometry.Size(p.size, p.size * 0.6f)
                    )
                }
            }
        }
    }
}

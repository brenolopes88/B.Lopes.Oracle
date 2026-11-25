package com.example.tarotoneinviewmode.ui.main

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.asComposeRenderEffect

@RequiresApi(Build.VERSION_CODES.S)
fun createGooeyRenderEffect(blurRadius: Float = 35f): androidx.compose.ui.graphics.RenderEffect {
    val blur = RenderEffect.createBlurEffect(blurRadius, blurRadius, Shader.TileMode.DECAL)

    // Matriz que multiplica o canal Alpha (18f) e aplica um deslocamento (-2000f)
    // Isso transforma as bordas suaves do blur em cortes limpos e fluidos
    val alphaMatrix = ColorMatrix(
        floatArrayOf(
            1f, 0f, 0f, 0f, 0f,
            0f, 1f, 0f, 0f, 0f,
            0f, 0f, 1f, 0f, 0f,
            0f, 0f, 0f, 18f, -2000f
        )
    )
    val colorFilter = RenderEffect.createColorFilterEffect(ColorMatrixColorFilter(alphaMatrix))

    return RenderEffect.createChainEffect(colorFilter, blur).asComposeRenderEffect()
}


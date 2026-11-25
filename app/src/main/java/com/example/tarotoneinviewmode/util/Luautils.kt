package com.example.tarotoneinviewmode.util

import java.util.Calendar
import java.util.TimeZone
import kotlin.math.floor

/**
 * Calcula a fase da lua para uma data qualquer.
 * Baseado no ciclo sinódico de 29.53059 dias a partir de uma lua nova conhecida.
 * Referência: lua nova em 6 de janeiro de 2000 (Julian Day 2451550.1).
 */
object LuaUtils {

    private const val CICLO_SINODICO = 29.53059

    // Fases com emoji, nome e ângulo de início (em graus do ciclo)
    data class FaseLua(val emoji: String, val nome: String)

    fun calcularFase(ano: Int, mes: Int, dia: Int): FaseLua {
        // Converte para Julian Day Number (JDN)
        val a = floor((14.0 - mes) / 12).toInt()
        val y = ano + 4800 - a
        val m = mes + 12 * a - 3
        val jdn = dia + floor((153.0 * m + 2) / 5).toInt() +
                365 * y + floor(y / 4.0).toInt() -
                floor(y / 100.0).toInt() + floor(y / 400.0).toInt() - 32045

        // Diferença em dias desde a lua nova de referência (JDN 2451550)
        val diasDesdeReferencia = (jdn - 2451550).toDouble()
        val cicloParcial = ((diasDesdeReferencia % CICLO_SINODICO) + CICLO_SINODICO) % CICLO_SINODICO

        return when {
            cicloParcial < 1.85                         -> FaseLua("🌑", "Lua Nova")
            cicloParcial < 7.38                         -> FaseLua("🌒", "Lua Crescente")
            cicloParcial < 9.22                         -> FaseLua("🌓", "Quarto Crescente")
            cicloParcial < 14.77                        -> FaseLua("🌔", "Lua Gibosa Crescente")
            cicloParcial < 16.61                        -> FaseLua("🌕", "Lua Cheia")
            cicloParcial < 22.15                        -> FaseLua("🌖", "Lua Gibosa Minguante")
            cicloParcial < 23.99                        -> FaseLua("🌗", "Quarto Minguante")
            cicloParcial < 29.53                        -> FaseLua("🌘", "Lua Minguante")
            else                                        -> FaseLua("🌑", "Lua Nova")
        }
    }

    /** Atalho para hoje */
    fun hoje(): FaseLua {
        val cal = Calendar.getInstance(TimeZone.getDefault())
        return calcularFase(
            ano = cal.get(Calendar.YEAR),
            mes = cal.get(Calendar.MONTH) + 1, // Calendar.MONTH é 0-based
            dia = cal.get(Calendar.DAY_OF_MONTH)
        )
    }
}
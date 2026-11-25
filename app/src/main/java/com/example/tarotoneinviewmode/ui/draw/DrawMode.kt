package com.example.tarotoneinviewmode.ui.draw

enum class DrawMode(val label: String, val cardCount: Int) {
    DAY_CARD("Carta do Dia", 1),
    PAST_PRESENT_FUTURE("Passado · Presente · Futuro", 3),
    SIM_NAO("Sim ou Não", 1),
    DESAFIO_SOLUCAO("Desafio e Solução", 2),
    RELACIONAMENTO("Relacionamento", 3)
}

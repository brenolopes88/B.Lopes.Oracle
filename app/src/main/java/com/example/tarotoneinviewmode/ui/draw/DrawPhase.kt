package com.example.tarotoneinviewmode.ui.draw

enum class DrawPhase {
    PREPARATION,    // silêncio, presença
    INTENTION,      // intenção fixada
    REVEALING,      // carta sendo revelada
    CONTEMPLATION   // interpretação
}


sealed class DrawEvent {

    object StartPreparation : DrawEvent()

    object ConfirmIntention : DrawEvent()

    object RevealCard : DrawEvent()

    object Reset : DrawEvent()
}

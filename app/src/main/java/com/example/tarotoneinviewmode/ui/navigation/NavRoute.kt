package com.example.tarotoneinviewmode.ui.navigation

sealed class NavRoute(val route: String) {

    object Home     : NavRoute("home")
    object Onboarding : NavRoute("onboarding")
    object Archive  : NavRoute("archive")
    object IChing   : NavRoute("iching/{mode}") {
        fun createRoute(mode: String) = "iching/$mode"
        const val MODE_GENERAL = "general"
        const val MODE_MUTATION = "mutation"
        const val MODE_DAILY = "daily"
    }

    object IChingArchive : NavRoute("iching_archive")
    object Settings : NavRoute("settings")
    object History  : NavRoute("history")
    object SpreadSelection : NavRoute("spread_selection") // 🚀 Novo Hub Central
    object Sadhana  : NavRoute("sadhana")
    object Guide    : NavRoute("guide/{topic}") {
        fun createRoute(topic: String) = "guide/$topic"
        const val TOPIC_TAROT = "tarot"
        const val TOPIC_ICHING = "iching"
        const val TOPIC_SADHANA = "sadhana"
    }

    object CardDetail : NavRoute("card_detail/{cardId}") {
        fun createRoute(cardId: String) = "card_detail/$cardId"
    }

    object HexagramDetail : NavRoute("hexagram_detail/{number}") {
        fun createRoute(number: Int) = "hexagram_detail/$number"
    }

    object Draw : NavRoute("draw/{mode}") {
        fun createRoute(mode: String) = "draw/$mode"
        const val DAY_CARD = "day_card"
        const val PPF      = "past_present_future"
        const val SIM_NAO  = "sim_nao"
        const val DESAFIO  = "desafio_solucao"
        const val RELATION = "relacionamento"
    }
}
package com.example.tarotoneinviewmode.data.repository

import com.example.tarotoneinviewmode.domain.enums.ArcanaType
import com.example.tarotoneinviewmode.domain.model.TarotCard
import com.example.tarotoneinviewmode.domain.model.draw.TarotReading
import com.example.tarotoneinviewmode.ui.archive.ArchiveTab
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryTarotRepository @Inject constructor(
    private val cards: List<TarotCard>
) : TarotRepository {

    private val readings = mutableListOf<TarotReading>()

    // 🚀 Pré-mapeia e guarda em memória as cartas agrupadas por aba.
    // A busca por aba passa de O(N) para O(1) instantâneo sem filtrar a lista a cada clique!
    private val cardsByTabMap: Map<ArchiveTab, List<TarotCard>> by lazy {
        ArchiveTab.tabEntries.associateWith { tab ->
            when {
                tab.arcanaType == ArcanaType.MAJOR ->
                    cards.filter { it.arcana == ArcanaType.MAJOR }
                tab.suit != null ->
                    cards.filter { it.suit == tab.suit }
                else -> cards
            }
        }
    }

    override fun getAllCards() = cards

    override fun getCardById(id: String) =
        cards.find { it.id == id }

    override fun getCardsByTab(tab: ArchiveTab): List<TarotCard> {
        return cardsByTabMap[tab] ?: cards
    }

    override fun saveReading(reading: TarotReading) {
        readings.add(0, reading)
    }

    override fun getReadings(): List<TarotReading> = readings.toList()

    override fun updateReadingNote(readingId: String, note: String) {
        val index = readings.indexOfFirst { it.id == readingId }
        if (index != -1) {
            readings[index] = readings[index].copy(note = note)
        }
    }

    override fun drawRandomCard(): TarotCard = cards.random()
}
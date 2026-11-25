package com.example.tarotoneinviewmode.data.repository

import com.example.tarotoneinviewmode.domain.model.TarotCard
import com.example.tarotoneinviewmode.domain.model.draw.TarotReading
import com.example.tarotoneinviewmode.ui.archive.ArchiveTab

interface TarotRepository {
    fun getAllCards(): List<TarotCard>
    fun getCardById(id: String): TarotCard?
    fun getCardsByTab(tab: ArchiveTab): List<TarotCard>
    fun saveReading(reading: TarotReading)
    fun getReadings(): List<TarotReading>
    fun updateReadingNote(readingId: String, note: String)
    fun drawRandomCard(): TarotCard
}

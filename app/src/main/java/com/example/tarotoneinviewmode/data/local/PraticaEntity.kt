package com.example.tarotoneinviewmode.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "praticas")
data class PraticaEntity(
    @PrimaryKey
    val id: String,
    val titulo: String,
    val descricao: String,
    val duracao: String,
    val categoria: String,
    val audioUrl: String,
    val ciclo: Int? = null,
    val isPremium: Boolean = false
)

@Entity(tableName = "pratica_progresso")
data class PraticaProgressoEntity(
    @PrimaryKey
    @SerializedName("pratica_id")
    val praticaId: String,

    @SerializedName("dias_completos")
    val diasCompletos: Int = 0,

    @SerializedName("ultima_data_completa")
    val ultimaDataCompleta: Long? = null
)
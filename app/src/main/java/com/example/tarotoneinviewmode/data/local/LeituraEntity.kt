package com.example.tarotoneinviewmode.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "leituras")
data class LeituraEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("tipo")
    val tipo: String,

    @SerializedName("data_criacao")
    val dataCriacao: Long,

    @SerializedName("nota")
    val nota: String = "",

    @SerializedName("compartilhada")
    val compartilhada: Boolean = false,

    @SerializedName("hexagram_number")
    val hexagramNumber: Int? = null,

    @SerializedName("hexagram_future_number")
    val hexagramFutureNumber: Int? = null, // 🚀 Novo: Segundo hexagrama para mutações

    @SerializedName("is_favorite")
    val isFavorite: Boolean = false
)

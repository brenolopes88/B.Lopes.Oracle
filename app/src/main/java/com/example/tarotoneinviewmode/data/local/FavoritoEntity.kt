package com.example.tarotoneinviewmode.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favoritos")
data class FavoritoEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("tipo")
    val tipo: String, // "TAROT_CARD" | "ICHING_HEXAGRAM"

    @SerializedName("ref_id")
    val refId: String, // cardId ou String do hexagramNumber

    @SerializedName("data_criacao")
    val dataCriacao: Long = System.currentTimeMillis()
)

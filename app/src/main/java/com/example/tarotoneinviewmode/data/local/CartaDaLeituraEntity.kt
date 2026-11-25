package com.example.tarotoneinviewmode.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "cartas_leitura",
    foreignKeys = [ForeignKey(
        entity = LeituraEntity::class,
        parentColumns = ["id"],
        childColumns = ["leituraId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("leituraId")]
)
data class CartaDaLeituraEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("leitura_id")
    val leituraId: Long,        // FK para LeituraEntity

    @SerializedName("card_id")
    val cardId: String,         // ex: "cups_01" — mesmo id do TarotCard

    @SerializedName("posicao")
    val posicao: String,        // "DIA" | "PASSADO" | "PRESENTE" | "FUTURO"

    @SerializedName("invertida")
    val invertida: Boolean = false
)
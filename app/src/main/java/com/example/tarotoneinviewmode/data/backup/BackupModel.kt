package com.example.tarotoneinviewmode.data.backup

import com.example.tarotoneinviewmode.data.local.CartaDaLeituraEntity
import com.example.tarotoneinviewmode.data.local.FavoritoEntity
import com.example.tarotoneinviewmode.data.local.LeituraEntity
import com.example.tarotoneinviewmode.data.local.PraticaProgressoEntity
import com.google.gson.annotations.SerializedName

data class BackupModel(
    @SerializedName("leituras") val leituras: List<LeituraEntity>? = null,
    @SerializedName("cartas_leitura") val cartasLeitura: List<CartaDaLeituraEntity>? = null,
    @SerializedName("progresso_praticas") val progressoPraticas: List<PraticaProgressoEntity>? = null,
    @SerializedName("favoritos") val favoritos: List<FavoritoEntity>? = null,
    @SerializedName("export_date") val exportDate: Long? = null,
    @SerializedName("version") val version: Int? = null,
    @SerializedName("schema_version") val schemaVersion: Int? = null,
    @SerializedName("app_version_name") val appVersionName: String? = null,
    @SerializedName("app_version_code") val appVersionCode: Int? = null
)

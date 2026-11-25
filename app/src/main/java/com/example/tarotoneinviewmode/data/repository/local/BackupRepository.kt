package com.example.tarotoneinviewmode.data.repository.local

import androidx.room.withTransaction
import com.example.tarotoneinviewmode.data.backup.BackupModel
import com.example.tarotoneinviewmode.data.local.FavoritoDao
import com.example.tarotoneinviewmode.data.local.LeituraDao
import com.example.tarotoneinviewmode.data.local.OraculoDatabase
import com.example.tarotoneinviewmode.data.local.PraticaDao
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackupRepository @Inject constructor(
    private val database: OraculoDatabase,
    private val leituraDao: LeituraDao,
    private val praticaDao: PraticaDao,
    private val favoritoDao: FavoritoDao,
    private val gson: Gson
) {

    companion object {
        const val CURRENT_SCHEMA_VERSION = 1
    }

    suspend fun createBackup(): String = withContext(Dispatchers.IO) {
        val backup = BackupModel(
            leituras = leituraDao.listaTodasLeituras(),
            cartasLeitura = leituraDao.listaTodasCartas(),
            progressoPraticas = praticaDao.listaTodoProgressos(),
            favoritos = favoritoDao.listaTodosFavoritos(),
            exportDate = System.currentTimeMillis(),
            version = 1,
            schemaVersion = CURRENT_SCHEMA_VERSION,
            appVersionName = "1.0",
            appVersionCode = 1
        )
        gson.toJson(backup)
    }

    suspend fun restoreBackup(json: String) = withContext(Dispatchers.IO) {
        val backup = try {
            gson.fromJson(json, BackupModel::class.java)
        } catch (e: Exception) {
            throw IllegalArgumentException("Arquivo de backup malformado ou corrompido.", e)
        } ?: throw IllegalArgumentException("Não foi possível processar o conteúdo do backup.")

        val schemaVer = backup.schemaVersion
        if (schemaVer == null || schemaVer <= 0) {
            throw IllegalArgumentException("Arquivo de backup inválido: versão do schema ausente ou não reconhecida.")
        }

        if (schemaVer > CURRENT_SCHEMA_VERSION) {
            throw IllegalStateException("A versão do schema do backup ($schemaVer) é mais recente do que a suportada por este aplicativo (máxima suportada: $CURRENT_SCHEMA_VERSION). Atualize o app para restaurar este backup.")
        }

        val leituras = backup.leituras
        val cartasLeitura = backup.cartasLeitura
        val progressoPraticas = backup.progressoPraticas
        val favoritos = backup.favoritos

        if (leituras == null || cartasLeitura == null || progressoPraticas == null || favoritos == null) {
            throw IllegalArgumentException("Arquivo de backup incompleto: listas essenciais ausentes ou nulas.")
        }

        database.withTransaction {
            leituraDao.deletarTodasCartas()
            leituraDao.deletarTudo()
            praticaDao.deletarTodoProgresso()
            favoritoDao.deletarTodosFavoritos()

            leituraDao.restaurarLeituras(leituras)
            leituraDao.restaurarCartas(cartasLeitura)
            praticaDao.restaurarProgressos(progressoPraticas)
            favoritoDao.restaurarFavoritos(favoritos)
        }
    }
}

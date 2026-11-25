package com.example.tarotoneinviewmode.data.repository

import com.example.tarotoneinviewmode.data.local.PraticaDao
import com.example.tarotoneinviewmode.data.local.PraticaEntity
import com.example.tarotoneinviewmode.data.local.PraticaProgressoEntity
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PraticaRepository @Inject constructor(private val dao: PraticaDao) {

    fun praticasPorCategoria(categoria: String): Flow<List<PraticaEntity>> =
        dao.praticasPorCategoria(categoria)

    fun todoProgressos(): Flow<List<PraticaProgressoEntity>> =
        dao.todoProgressos()

    suspend fun inserirPraticas(praticas: List<PraticaEntity>) =
        dao.inserirPraticas(praticas)

    suspend fun registrarDiaCompleto(praticaId: String) {
        val hoje = inicioDodia()
        val progresso = dao.progressoDaPratica(praticaId)

        // evita registrar duas vezes no mesmo dia
        if (progresso?.ultimaDataCompleta == hoje) return

        val diasCompletos = (progresso?.diasCompletos ?: 0) + 1
        dao.salvarProgresso(
            PraticaProgressoEntity(
                praticaId = praticaId,
                diasCompletos = diasCompletos,
                ultimaDataCompleta = hoje
            )
        )
    }

    suspend fun progressoDaPratica(praticaId: String): PraticaProgressoEntity? =
        dao.progressoDaPratica(praticaId)

    private fun inicioDodia(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }
}
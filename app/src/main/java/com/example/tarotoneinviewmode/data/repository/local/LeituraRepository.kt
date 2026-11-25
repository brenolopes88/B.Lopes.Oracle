package com.example.tarotoneinviewmode.data.repository.local

import com.example.tarotoneinviewmode.data.local.CartaDaLeituraEntity
import com.example.tarotoneinviewmode.data.local.LeituraDao
import com.example.tarotoneinviewmode.data.local.LeituraEntity
import com.example.tarotoneinviewmode.util.AnalyticsHelper
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeituraRepository @Inject constructor(private val dao: LeituraDao) {

    // ── leituras ──────────────────────────────────────────────────────────────

    val todasLeituras: Flow<List<LeituraEntity>> = dao.todasLeituras()

    val leiturasFavoritas: Flow<List<LeituraEntity>> = dao.leiturasFavoritas()

    suspend fun setFavorito(id: Long, isFavorite: Boolean) =
        dao.setFavorito(id, isFavorite)

    // --- SALVAR TAROT ---
    suspend fun salvarLeitura(
        tipo: String,
        cartas: List<Pair<String, String>>,
        invertida: Boolean = false
    ): Long {
        val leitura = LeituraEntity(
            tipo = tipo,
            dataCriacao = System.currentTimeMillis()
        )
        val leituraId = dao.inserirLeitura(leitura)

        val cartasEntity = cartas.map { (cardId, posicao) ->
            CartaDaLeituraEntity(
                leituraId = leituraId,
                cardId = cardId,
                posicao = posicao,
                invertida = invertida
            )
        }
        dao.inserirCartas(cartasEntity)
        AnalyticsHelper.logReadingSaved(AnalyticsHelper.TYPE_TAROT)

        return leituraId
    }

    // --- SALVAR I CHING ---
    suspend fun salvarLeituraIChing(
        tipo: String = "ICHING_READING",
        hexagramNumber: Int,
        hexagramFutureNumber: Int? = null,
        nota: String = ""
    ): Long {
        // Se for leitura do dia, remove anterior para não duplicar
        if (tipo == "ICHING_DAY") {
            val existente = leituraIChingDoDia()
            existente?.let { dao.deletarLeitura(it) }
        }

        val leitura = LeituraEntity(
            tipo = tipo,
            dataCriacao = System.currentTimeMillis(),
            hexagramNumber = hexagramNumber,
            hexagramFutureNumber = hexagramFutureNumber,
            nota = nota
        )
        val id = dao.inserirLeitura(leitura)
        AnalyticsHelper.logReadingSaved(AnalyticsHelper.TYPE_ICHING)
        return id
    }

    suspend fun cartasDaLeitura(leituraId: Long) =
        dao.cartasDaLeitura(leituraId)

    suspend fun atualizarNota(leituraId: Long, nota: String) =
        dao.atualizarNota(leituraId, nota)

    suspend fun deletarLeitura(leitura: LeituraEntity) =
        dao.deletarLeitura(leitura)

    /** Retorna as [n] leituras mais recentes, ordenadas da mais nova para a mais antiga. */
    suspend fun ultimas(n: Int): List<LeituraEntity> =
        dao.ultimasLeituras(n)

    // ── carta do dia (Tarot) ──────────────────────────────────────────────────

    suspend fun leiturasDoDiaExiste(): Boolean {
        val (inicioDia, fimDia) = intervaloHoje()
        return dao.leituraDoDia(inicioDia, fimDia) != null
    }

    suspend fun leituraDoDiaCompleta(): Pair<LeituraEntity, List<CartaDaLeituraEntity>>? {
        val (inicioDia, fimDia) = intervaloHoje()
        val leitura = dao.leituraDoDia(inicioDia, fimDia) ?: return null
        val cartas  = dao.cartasDaLeitura(leitura.id)
        return Pair(leitura, cartas)
    }

    // ── hexagrama do dia (I Ching) ────────────────────────────────────────────

    suspend fun leituraIChingDoDia(): LeituraEntity? {
        val (inicioDia, fimDia) = intervaloHoje()
        val leiturasDoDia = dao.ultimasLeituras(20) // busca as últimas do banco
        return leiturasDoDia.firstOrNull {
            it.tipo == "ICHING_DAY" && it.dataCriacao in inicioDia..fimDia
        }
    }

    suspend fun deletarTudo() = dao.deletarTudo()

    suspend fun buscarCardIdsDesde(inicio: Long) = dao.buscarCardIdsDesde(inicio)

    // ── util ──────────────────────────────────────────────────────────────────

    private fun intervaloHoje(): Pair<Long, Long> {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0); cal.set(Calendar.MILLISECOND, 0)
        val inicio = cal.timeInMillis
        cal.set(Calendar.HOUR_OF_DAY, 23); cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        val fim = cal.timeInMillis
        return Pair(inicio, fim)
    }
}
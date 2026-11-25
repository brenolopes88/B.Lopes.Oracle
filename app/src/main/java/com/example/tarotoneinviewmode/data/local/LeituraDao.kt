package com.example.tarotoneinviewmode.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface LeituraDao {

    // ── INSERT ────────────────────────────────────────────────────────────────

    @Insert
    suspend fun inserirLeitura(leitura: LeituraEntity): Long

    @Insert
    suspend fun inserirCartas(cartas: List<CartaDaLeituraEntity>)

    // ── READ ──────────────────────────────────────────────────────────────────

    @Query("SELECT * FROM leituras ORDER BY dataCriacao DESC")
    fun todasLeituras(): Flow<List<LeituraEntity>>

    @Query("SELECT * FROM cartas_leitura WHERE leituraId = :leituraId")
    suspend fun cartasDaLeitura(leituraId: Long): List<CartaDaLeituraEntity>

    @Query("SELECT * FROM leituras WHERE id = :id")
    suspend fun leituraPorId(id: Long): LeituraEntity?

    @Query("SELECT * FROM leituras")
    suspend fun listaTodasLeituras(): List<LeituraEntity>

    @Query("SELECT * FROM cartas_leitura")
    suspend fun listaTodasCartas(): List<CartaDaLeituraEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun restaurarLeituras(leituras: List<LeituraEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun restaurarCartas(cartas: List<CartaDaLeituraEntity>)

    @Query("SELECT * FROM leituras ORDER BY dataCriacao DESC LIMIT :n")
    suspend fun ultimasLeituras(n: Int): List<LeituraEntity>

    @Query("""
        SELECT * FROM leituras 
        WHERE dataCriacao >= :inicioDia 
        AND dataCriacao < :fimDia 
        AND tipo = 'DAY_CARD'
        LIMIT 1
    """)
    suspend fun leituraDoDia(inicioDia: Long, fimDia: Long): LeituraEntity?

    @Query("SELECT * FROM leituras WHERE isFavorite = 1 ORDER BY dataCriacao DESC")
    fun leiturasFavoritas(): Flow<List<LeituraEntity>>

    // ── UPDATE ────────────────────────────────────────────────────────────────

    @Query("UPDATE leituras SET nota = :nota WHERE id = :id")
    suspend fun atualizarNota(id: Long, nota: String)

    @Query("UPDATE leituras SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun setFavorito(id: Long, isFavorite: Boolean)

    // ── DELETE ────────────────────────────────────────────────────────────────

    @Delete
    suspend fun deletarLeitura(leitura: LeituraEntity)

    @Query("DELETE FROM leituras")
    suspend fun deletarTudo()

    @Query("DELETE FROM cartas_leitura")
    suspend fun deletarTodasCartas()

    @Query("""
        SELECT cardId FROM cartas_leitura 
        INNER JOIN leituras ON cartas_leitura.leituraId = leituras.id
        WHERE leituras.dataCriacao >= :inicio
    """)
    suspend fun buscarCardIdsDesde(inicio: Long): List<String>
}
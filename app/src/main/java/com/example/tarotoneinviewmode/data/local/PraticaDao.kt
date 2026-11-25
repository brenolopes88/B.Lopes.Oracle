package com.example.tarotoneinviewmode.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PraticaDao {

    // ── praticas ──────────────────────────────────────────────────────────────

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirPraticas(praticas: List<PraticaEntity>)

    @Query("SELECT * FROM praticas WHERE categoria = :categoria ORDER BY titulo ASC")
    fun praticasPorCategoria(categoria: String): Flow<List<PraticaEntity>>

    @Query("SELECT * FROM praticas WHERE id = :id")
    suspend fun praticaPorId(id: String): PraticaEntity?

    // ── progresso ─────────────────────────────────────────────────────────────

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvarProgresso(progresso: PraticaProgressoEntity)

    @Query("SELECT * FROM pratica_progresso WHERE praticaId = :praticaId")
    suspend fun progressoDaPratica(praticaId: String): PraticaProgressoEntity?

    @Query("SELECT * FROM pratica_progresso")
    fun todoProgressos(): Flow<List<PraticaProgressoEntity>>

    @Query("SELECT * FROM pratica_progresso")
    suspend fun listaTodoProgressos(): List<PraticaProgressoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun restaurarProgressos(progressos: List<PraticaProgressoEntity>)

    @Query("DELETE FROM pratica_progresso")
    suspend fun deletarTodoProgresso()
}

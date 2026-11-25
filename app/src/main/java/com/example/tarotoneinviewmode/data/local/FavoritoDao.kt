package com.example.tarotoneinviewmode.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(favorito: FavoritoEntity)

    @Query("DELETE FROM favoritos WHERE tipo = :tipo AND refId = :refId")
    suspend fun deletar(tipo: String, refId: String)

    @Query("SELECT * FROM favoritos ORDER BY dataCriacao DESC")
    fun todosFavoritos(): Flow<List<FavoritoEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favoritos WHERE tipo = :tipo AND refId = :refId)")
    fun ehFavorito(tipo: String, refId: String): Flow<Boolean>

    @Query("SELECT * FROM favoritos")
    suspend fun listaTodosFavoritos(): List<FavoritoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun restaurarFavoritos(favoritos: List<FavoritoEntity>)

    @Query("DELETE FROM favoritos")
    suspend fun deletarTodosFavoritos()
}

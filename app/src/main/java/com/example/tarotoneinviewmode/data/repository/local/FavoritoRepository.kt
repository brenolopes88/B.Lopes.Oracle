package com.example.tarotoneinviewmode.data.repository.local

import com.example.tarotoneinviewmode.data.local.FavoritoDao
import com.example.tarotoneinviewmode.data.local.FavoritoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritoRepository @Inject constructor(private val dao: FavoritoDao) {

    val todosFavoritos: Flow<List<FavoritoEntity>> = dao.todosFavoritos()

    fun ehFavorito(tipo: String, refId: String): Flow<Boolean> =
        dao.ehFavorito(tipo, refId)

    suspend fun alternarFavorito(tipo: String, refId: String, isFavorite: Boolean) {
        if (isFavorite) {
            dao.inserir(FavoritoEntity(tipo = tipo, refId = refId))
        } else {
            dao.deletar(tipo, refId)
        }
    }
}

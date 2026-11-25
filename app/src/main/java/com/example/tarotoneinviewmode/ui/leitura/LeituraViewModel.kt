package com.example.tarotoneinviewmode.ui.leitura

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.local.LeituraEntity
import com.example.tarotoneinviewmode.data.repository.local.LeituraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeituraViewModel @Inject constructor(
    private val repository: LeituraRepository
) : ViewModel() {

    // ── estado ────────────────────────────────────────────────────────────────

    // lista de leituras — atualiza automaticamente via Flow
    val leituras: StateFlow<List<LeituraEntity>> = repository.todasLeituras
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    // ── ações ─────────────────────────────────────────────────────────────────

    fun salvarLeitura(
        tipo: String,
        cartas: List<Pair<String, String>> // Pair(cardId, posicao)
    ) {
        viewModelScope.launch {
            repository.salvarLeitura(tipo, cartas)
        }
    }

    fun atualizarNota(leituraId: Long, nota: String) {
        viewModelScope.launch {
            repository.atualizarNota(leituraId, nota)
        }
    }

    fun deletarLeitura(leitura: LeituraEntity) {
        viewModelScope.launch {
            repository.deletarLeitura(leitura)
        }
    }

    suspend fun cartaDoDiaJaExiste(): Boolean =
        repository.leiturasDoDiaExiste()
}

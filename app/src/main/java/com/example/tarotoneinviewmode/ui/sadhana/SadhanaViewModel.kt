package com.example.tarotoneinviewmode.ui.sadhana

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.local.PraticaEntity
import com.example.tarotoneinviewmode.data.local.PraticaProgressoEntity
import com.example.tarotoneinviewmode.data.repository.PraticaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SadhanaEffect {
    data class CicloCompleto(val titulo: String) : SadhanaEffect()
}

data class PraticaUiItem(
    val pratica: PraticaEntity,
    val progresso: PraticaProgressoEntity? = null,
    val expandida: Boolean = false
)

data class SadhanaUiState(
    val categorias: List<String> = listOf("Orações", "Afirmações", "Meditações"),
    val categoriaSelecionada: String = "Orações",
    val itens: List<PraticaUiItem> = emptyList(),
    val isLoading: Boolean = true,
    val isUserPremium: Boolean = false // 🚀 Para futura integração com faturamento
)

@HiltViewModel
class SadhanaViewModel @Inject constructor(
    private val repository: PraticaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SadhanaUiState())
    val uiState: StateFlow<SadhanaUiState> = _uiState

    private val _efeitos = MutableSharedFlow<SadhanaEffect>()
    val efeitos = _efeitos.asSharedFlow()

    private val _categoriaSelecionada = MutableStateFlow("Orações")
    private val _expandidaId = MutableStateFlow<String?>(null)

    init {
        observarDados()
        popularDadosIniciais()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observarDados() {
        viewModelScope.launch {
            combine(
                _categoriaSelecionada,
                repository.todoProgressos(),
                _expandidaId
            ) { categoria, progressos, expandidaId ->
                Triple(categoria, progressos, expandidaId)
            }.flatMapLatest { (categoria, progressos, expandidaId) ->
                val mapaProgressos = progressos.associateBy { it.praticaId }
                repository.praticasPorCategoria(categoria).combine(MutableStateFlow(Unit)) { praticas, _ ->
                    praticas.map { pratica ->
                        PraticaUiItem(
                            pratica = pratica,
                            progresso = mapaProgressos[pratica.id],
                            expandida = expandidaId == pratica.id
                        )
                    }
                }
            }.collect { itens ->
                _uiState.update { it.copy(
                    itens = itens,
                    categoriaSelecionada = _categoriaSelecionada.value,
                    isLoading = false
                ) }
            }
        }
    }

    fun onCategoriaSelected(categoria: String) {
        _expandidaId.value = null
        _categoriaSelecionada.value = categoria
    }

    fun onPraticaToggle(id: String) {
        _expandidaId.value = if (_expandidaId.value == id) null else id
    }

    fun registrarDiaCompleto(praticaId: String) {
        viewModelScope.launch {
            repository.registrarDiaCompleto(praticaId)
            
            val item = _uiState.value.itens.find { it.pratica.id == praticaId }
            val progresso = repository.progressoDaPratica(praticaId)

            if (item?.pratica?.ciclo != null && progresso != null) {
                if (progresso.diasCompletos == item.pratica.ciclo) {
                    _efeitos.emit(SadhanaEffect.CicloCompleto(item.pratica.titulo))
                }
            }
        }
    }

    private fun popularDadosIniciais() {
        viewModelScope.launch {
            // 🚀 Áudio local para teste instantâneo
            val audioTeste = "android.resource://com.example.tarotoneinviewmode/raw/teste"
            val praticas = listOf(
                PraticaEntity(
                    id = "sao_miguel_01",
                    titulo = "Oração de São Miguel Arcanjo",
                    descricao = "Limpeza e proteção espiritual. Repita por 21 dias consecutivos para completar o ciclo.",
                    duracao = "3:30",
                    categoria = "Orações",
                    audioUrl = audioTeste,
                    ciclo = 21,
                    isPremium = false
                ),
                PraticaEntity(
                    id = "afirmacao_manha_01",
                    titulo = "Afirmações da Manhã",
                    descricao = "Afirmações para iniciar o dia com intenção e clareza.",
                    duracao = "5:00",
                    categoria = "Afirmações",
                    audioUrl = audioTeste,
                    ciclo = null,
                    isPremium = false
                ),
                PraticaEntity(
                    id = "yoga_nidra_01",
                    titulo = "Yoga Nidra — Relaxamento Profundo",
                    descricao = "Meditação guiada para relaxamento profundo e regeneração do sistema nervoso.",
                    duracao = "20:00",
                    categoria = "Meditações",
                    audioUrl = audioTeste,
                    ciclo = null,
                    isPremium = true
                )
            )
            repository.inserirPraticas(praticas)
        }
    }
}

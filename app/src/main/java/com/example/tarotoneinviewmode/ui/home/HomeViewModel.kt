package com.example.tarotoneinviewmode.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarotoneinviewmode.data.local.LeituraEntity
import com.example.tarotoneinviewmode.data.repository.TarotRepository
import com.example.tarotoneinviewmode.data.repository.local.LeituraRepository
import com.example.tarotoneinviewmode.domain.model.TarotCard
import com.example.tarotoneinviewmode.util.LuaUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.util.Calendar
import javax.inject.Inject

data class ItemHistoricoRecente(
    val dataFormatada: String,
    val cartaTarot: TarotCard? = null,
    val hexagramaIChing: Int? = null,
)

data class HomeUiState(
    val isLoading: Boolean = true,
    val isEmpty: Boolean = true,
    val cartaDoDia: TarotCard? = null,
    val hexagramaDoDia: Int? = null,
    val leitura: LeituraEntity? = null,
    val faseLua: LuaUtils.FaseLua = LuaUtils.FaseLua("🌙", ""),
    val historicoRecente: List<ItemHistoricoRecente> = emptyList(),
    val videoAtemporal: String? = null,
    val vibracaoDaSemana: TarotCard? = null,
    val insightDoDia: String? = null // 🚀 Novo campo para o toque místico
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val leituraRepository: LeituraRepository,
    private val tarotRepository: TarotRepository
) : ViewModel() {

    private val GIST_URL = "https://gist.githubusercontent.com/blopes-oracle/283c74f588bd050c04c09ab7bbfbac7a/raw/gistfile1.txt"
    private val fallbackLinks = listOf("https://www.tiktok.com/@b.lopes.oraculo/video/7397734498305002758")

    private val insightsMisticos = listOf(
        "O silêncio é a linguagem onde os oráculos falam mais alto.",
        "Sua intuição é a bússola que nunca erra o norte da alma.",
        "Cada carta revelada é um espelho de uma verdade interna.",
        "O destino não é traçado, ele é tecido a cada escolha consciente.",
        "Confie no tempo do universo; as mutações ocorrem no momento certo.",
        "A sabedoria não está em saber o futuro, mas em agir no presente.",
        "Sintonize sua energia com a frequência da gratidão hoje.",
        "Onde há dúvida, busque a clareza no centro do seu ser.",
        "Seu ritual diário é o portal para sua própria maestria.",
        "O I Ching ensina que a única constante é a mudança."
    )

    private val _videoAtemporal = MutableStateFlow<String?>(null)

    val uiState: StateFlow<HomeUiState> = combine(
        leituraRepository.todasLeituras,
        _videoAtemporal
    ) { leituras, video ->
        processarEstadoHome(leituras, video)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )

    init {
        carregarLinksRemotos()
    }

    private suspend fun processarEstadoHome(todasLeituras: List<LeituraEntity>, video: String?): HomeUiState {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0); cal.set(Calendar.MILLISECOND, 0)
        val hojeInicio = cal.timeInMillis

        // 1. Tarot de hoje
        val leituraTarotHoje = todasLeituras.firstOrNull { it.tipo == "DAY_CARD" && it.dataCriacao >= hojeInicio }
        val cartaDoDia = leituraTarotHoje?.let { 
            leituraRepository.cartasDaLeitura(it.id).firstOrNull()?.let { c -> tarotRepository.getCardById(c.cardId) }
        }

        // 2. I Ching de hoje
        val leituraIChingHoje = todasLeituras.firstOrNull { it.tipo == "ICHING_DAY" && it.dataCriacao >= hojeInicio }
        val hexagramaDoDia = leituraIChingHoje?.hexagramNumber

        // 3. Histórico (Excluindo hoje, apenas últimos 3 dias)
        val historicoRecente = todasLeituras
            .filter { it.dataCriacao < hojeInicio }
            .groupBy { leitura ->
                val calItem = Calendar.getInstance().apply { timeInMillis = leitura.dataCriacao }
                "%02d/%02d".format(calItem.get(Calendar.DAY_OF_MONTH), calItem.get(Calendar.MONTH) + 1)
            }
            .map { (data, leiturasDoDia) ->
                val tarotNoDia = leiturasDoDia.firstOrNull { it.tipo == "DAY_CARD" }
                val carta = tarotNoDia?.let { 
                    leituraRepository.cartasDaLeitura(it.id).firstOrNull()?.let { c -> tarotRepository.getCardById(c.cardId) }
                }
                val ichingNoDia = leiturasDoDia.firstOrNull { it.tipo == "ICHING_DAY" }
                
                ItemHistoricoRecente(
                    dataFormatada = data,
                    cartaTarot = carta,
                    hexagramaIChing = ichingNoDia?.hexagramNumber
                )
            }
            .take(3)

        // 4. Lógica de Vibração da Semana
        val seteDiasAtras = hojeInicio - (7 * 24 * 60 * 60 * 1000)
        val cardIdsRecentes = leituraRepository.buscarCardIdsDesde(seteDiasAtras)
        val vibracao = if (cardIdsRecentes.isNotEmpty()) {
            val grupos = cardIdsRecentes.groupBy { it }
            val maxFreq = grupos.maxOfOrNull { it.value.size } ?: 0
            
            // Só mostramos como vibração se uma carta aparecer pelo menos 2 vezes
            if (maxFreq >= 2) {
                val maisFrequenteId = grupos.maxByOrNull { it.value.size }?.key
                maisFrequenteId?.let { tarotRepository.getCardById(it) }
            } else null
        } else null

        // 5. 🚀 Insight do Dia (Aleatório baseado no dia do mês para não mudar a cada clique)
        val diaDoMes = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val insight = insightsMisticos[diaDoMes % insightsMisticos.size]

        return HomeUiState(
            isLoading = false,
            isEmpty = todasLeituras.isEmpty(),
            cartaDoDia = cartaDoDia,
            hexagramaDoDia = hexagramaDoDia,
            leitura = leituraTarotHoje,
            faseLua = LuaUtils.hoje(),
            historicoRecente = historicoRecente,
            videoAtemporal = video,
            vibracaoDaSemana = vibracao,
            insightDoDia = insight
        )
    }

    private fun carregarLinksRemotos() {
        viewModelScope.launch {
            try {
                val links = withContext(Dispatchers.IO) {
                    URL(GIST_URL).readText().lines().map { it.trim() }.filter { it.startsWith("http") }
                }
                _videoAtemporal.value = if (links.isNotEmpty()) links.random() else null
            } catch (e: Exception) {
                _videoAtemporal.value = fallbackLinks.random()
            }
        }
    }

    fun carregarHome() { /* Mantido por compatibilidade */ }
}

package com.example.tarotoneinviewmode.ui.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.ui.navigation.NavRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideScreen(
    topic: String,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sabedoria do Oráculo") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bg_default),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.4f
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (topic) {
                    NavRoute.Guide.TOPIC_ICHING -> IChingGuideContent()
                    //NavRoute.Guide.TOPIC_SADHANA -> SadhanaGuideContent()
                    else -> TarotGuideContent()
                }

                Spacer(modifier = Modifier.height(24.dp))

                TextButton(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("← Voltar para a Consulta", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun IChingGuideContent() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            GuideSection(
                title = "A Sabedoria das Mutações",
                content = "O I Ching (ou Yi Jing) é a cristalização de milênios de observação da natureza. Sua premissa é simples, mas profunda: a única constante no universo é a mudança. Ele não prevê um destino fixo, mas mostra a tendência do momento e como agir em harmonia com ela."
            )

            GuideSection(
                title = "O Yin e o Yang",
                content = "As linhas quebradas (--) representam o Yin: o receptivo, a sombra, o repouso e a terra. As linhas contínuas (—) representam o Yang: o ativo, a luz, o movimento e o céu. O equilíbrio e a alternância entre essas duas forças criam toda a existência."
            )

            GuideSection(
                title = "Os Oito Trigramas (Ba Gua)",
                content = "Combinando Yin e Yang em grupos de três, surgem os 8 Trigramas primordiais, cada um representando uma força da natureza: Céu (Criativo), Terra (Receptivo), Trovão (Despertar), Água (Abismo), Montanha (Imobilidade), Vento (Suavidade), Fogo (Aderente) e Lago (Alegria)."
            )

            GuideSection(
                title = "Como Formar o Hexagrama",
                content = "Tradicionalmente, usa-se o lançamento de 3 moedas por seis vezes. Cada lançamento define uma linha, começando de baixo para cima. O resultado final de 6 linhas é o Hexagrama, que captura a complexidade da sua questão no tempo presente."
            )

            GuideSection(
                title = "A Mutação (Tendência)",
                content = "Às vezes, uma linha é 'mutante', indicando que aquela energia está no auge e prestes a se transformar no seu oposto. Isso gera um segundo hexagrama, mostrando para onde a situação está evoluindo. No Oráculo, focamos no conselho do presente e na tendência do futuro."
            )
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SadhanaGuideContent() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            GuideSection(
                title = "O Caminho do Sadhana",
                content = "Sadhana é a sua prática espiritual diária. Mais do que um exercício, é um compromisso consciente com o seu crescimento interno e com a conexão com o divino ou com o seu Eu Superior."
            )

            GuideSection(
                title = "A Força do Hábito",
                content = "A regularidade é a chave. Ao praticar todos os dias no mesmo horário, você cria um momentum energético que facilita a meditação e a introspecção profunda."
            )

            GuideSection(
                title = "O Ciclo de 21 Dias",
                content = "A neurociência e a tradição concordam: são necessários cerca de 21 dias para estabelecer uma nova rede neural e consolidar um novo hábito. O Oráculo ajuda você a trilhar esse ciclo com foco e disciplina."
            )

            GuideSection(
                title = "Práticas e Meditações",
                content = "Cada prática foi selecionada para trabalhar um aspecto diferente do seu ser. Ouça com fones de ouvido, em um local tranquilo, e permita-se mergulhar na experiência sem julgamentos."
            )
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun TarotGuideContent() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            GuideSection(
                title = "O Tarot como Espelho",
                content = "O Tarot não é sobre adivinhação, mas sobre sincronicidade. Ele utiliza a linguagem simbólica do inconsciente coletivo para projetar em cartas o que nossa mente racional muitas vezes não consegue ver. É uma ferramenta de introspecção e clareza."
            )

            GuideSection(
                title = "A Jornada do Louco",
                content = "Os 22 Arcanos Maiores narram uma história completa de evolução espiritual. Começa com o Louco (0), o passo de fé no desconhecido, e termina com o Mundo (XXI), a integração total e o sucesso. Cada carta é um estágio de maturidade que todos atravessamos."
            )

            GuideSection(
                title = "O Cotidiano nos Arcanos Menores",
                content = "Enquanto os Maiores falam de 'grandes temas', as 56 cartas dos Arcanos Menores detalham o dia a dia:\n\n• PAUS (Fogo): Criatividade, paixão e ação.\n• COPAS (Água): Emoções, amor e relacionamentos.\n• ESPADAS (Ar): Intelecto, conflitos e decisões.\n• OUROS (Terra): Trabalho, saúde e finanças."
            )

            GuideSection(
                title = "Como Perguntar ao Oráculo",
                content = "Evite perguntas de 'Sim ou Não' para questões complexas. Prefira: 'O que preciso saber sobre...', 'Como posso lidar com...', ou 'Qual a melhor postura diante de...'. O Tarot responde melhor quando você busca sabedoria em vez de uma resposta pronta."
            )
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun GuideSection(title: String, content: String) {
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f),
            lineHeight = 24.sp,
            textAlign = TextAlign.Justify
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 16.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
    }
}

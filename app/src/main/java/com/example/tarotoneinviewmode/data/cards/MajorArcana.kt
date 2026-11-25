package com.example.tarotoneinviewmode.data.cards

import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.domain.enums.ArcanaType
import com.example.tarotoneinviewmode.domain.enums.Element
import com.example.tarotoneinviewmode.domain.enums.Planet
import com.example.tarotoneinviewmode.domain.enums.ZodiacSign
import com.example.tarotoneinviewmode.domain.model.Correspondences
import com.example.tarotoneinviewmode.domain.model.TarotCard





val theFool = TarotCard(
    id = "major_00",
    name = "O Louco",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 0,
    essence = """
    O Louco representa o impulso primordial da jornada. 
    É o momento antes da forma, onde tudo é possível e nada está garantido.
    """.trimIndent(),
    lightMeaning = """
    Confiança no fluxo da vida, abertura ao novo e coragem para dar o primeiro passo.
    O Louco convida a experimentar sem medo do erro, guiado pela intuição.
    """.trimIndent(),
    shadowMeaning = """
    Imaturidade, imprudência e fuga de responsabilidades.
    Pode indicar agir sem consciência das consequências ou negar limites necessários.
    """.trimIndent(),
    guidanceWhenReversed = """
    Pergunte-se: estou evitando crescer ou apenas com medo de avançar?
    A sabedoria do Louco surge quando a liberdade é equilibrada com presença.
    """.trimIndent(),
    keywordsLight = listOf(
        "Início", "Liberdade", "Espontaneidade", "Fé", "Potencial"
    ),
    keywordsShadow = listOf(
        "Imprudência", "Ingenuidade", "Irresponsabilidade", "Fuga"
    ),
    correspondences = Correspondences(
        element = Element.AIR,
        planet = Planet.URANUS,
        zodiac = null,
        numerology = 0
    ),
    history = """
    Tradicionalmente retratado como um viajante à beira de um precipício,
    o Louco simboliza a alma antes da experiência.
    Ele não é ignorante, mas inocente — confia na jornada mesmo sem mapa.
    """.trimIndent(),
    imageResId = R.drawable.major_00
)
val theMagician = TarotCard(
    id = "major_01",
    name = "O Mago",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 1,

    essence = """
    O Mago representa o poder de transformar intenção em realidade.
    É a consciência que reconhece seus recursos e aprende a utilizá-los com propósito.
    """.trimIndent(),

    lightMeaning = """
    Habilidade, foco e comunicação clara. O Mago indica um momento favorável para agir, iniciar projetos e manifestar ideias através da vontade consciente e do uso inteligente das ferramentas disponíveis.
    """.trimIndent(),

    shadowMeaning = """
    Manipulação, engano ou uso distorcido do poder pessoal.
    Pode indicar promessas vazias, excesso de controle ou desconexão entre intenção e ética.
    """.trimIndent(),

    guidanceWhenReversed = """
    Observe onde sua energia está sendo dispersa ou usada para convencer em vez de criar.
    O verdadeiro poder do Mago surge quando intenção, palavra e ação estão alinhadas.
    """.trimIndent(),

    keywordsLight = listOf(
        "Manifestação", "Habilidade", "Foco", "Ação", "Comunicação"
    ),

    keywordsShadow = listOf(
        "Manipulação", "Ilusão", "Truques", "Controle Excessivo"
    ),

    correspondences = Correspondences(
        element = Element.AIR,
        planet = Planet.MERCURY,
        zodiac = null,
        numerology = 1
    ),

    history = """
    O Mago é tradicionalmente representado com os quatro símbolos dos naipes
    sobre sua mesa, indicando domínio sobre os elementos.
    Ele atua como ponte entre o mundo invisível da ideia e o mundo concreto da ação.
    """.trimIndent(),

    imageResId = R.drawable.major_01
)

val theHighPriestess = TarotCard(
    id = "major_02",
    name = "A Sacerdotisa",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 2,

    essence = """
    A Sacerdotisa representa o conhecimento interior, o silêncio e o mistério.
    Ela guarda verdades que não podem ser forçadas — apenas percebidas.
    """.trimIndent(),

    lightMeaning = """
    Intuição aguçada, sensibilidade e percepção profunda.
    Indica um momento de observar mais do que agir e confiar na sabedoria interna.
    """.trimIndent(),

    shadowMeaning = """
    Repressão emocional, passividade excessiva ou medo de se expressar.
    Pode indicar segredos, confusão interna ou desconexão da própria intuição.
    """.trimIndent(),

    guidanceWhenReversed = """
    O silêncio está te protegendo ou te paralisando?
    Ouvir a intuição exige presença, mas também coragem para agir quando for a hora.
    """.trimIndent(),

    keywordsLight = listOf(
        "Intuição", "Mistério", "Sabedoria Interior", "Silêncio", "Percepção"
    ),

    keywordsShadow = listOf(
        "Bloqueio Intuitivo", "Segredos", "Passividade", "Confusão"
    ),

    correspondences = Correspondences(
        element = Element.WATER,
        planet = Planet.MOON,
        zodiac = null,
        numerology = 2
    ),

    history = """
    Sentada entre dois pilares, a Sacerdotisa simboliza o limiar entre o consciente
    e o inconsciente. Ela não ensina por palavras, mas por presença.
    """.trimIndent(),

    imageResId = R.drawable.major_02
)

val theEmpress = TarotCard(
    id = "major_03",
    name = "A Imperatriz",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 3,

    essence = """
    A Imperatriz representa a criação, o cuidado e a abundância natural.
    É a força que nutre, faz crescer e dá forma ao que foi concebido.
    """.trimIndent(),

    lightMeaning = """
    Fertilidade criativa, amor, prazer e conexão com o corpo.
    Indica crescimento saudável, acolhimento e expressão da beleza.
    """.trimIndent(),

    shadowMeaning = """
    Excesso de apego, indulgência ou dependência emocional.
    Pode indicar estagnação por conforto excessivo ou falta de limites.
    """.trimIndent(),

    guidanceWhenReversed = """
    Nutrir não é controlar.
    Pergunte-se se você está cuidando por amor ou por medo de perder.
    """.trimIndent(),

    keywordsLight = listOf(
        "Criação", "Abundância", "Cuidado", "Prazer", "Fertilidade"
    ),

    keywordsShadow = listOf(
        "Apego", "Excesso", "Dependência", "Estagnação"
    ),

    correspondences = Correspondences(
        element = Element.EARTH,
        planet = Planet.VENUS,
        zodiac = null,
        numerology = 3
    ),

    history = """
    Associada à Mãe Terra, a Imperatriz simboliza o princípio feminino da criação,
    onde vida, arte e prazer coexistem em equilíbrio.
    """.trimIndent(),

    imageResId = R.drawable.major_03
)
val theEmperor = TarotCard(
    id = "major_04",
    name = "O Imperador",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 4,

    essence = """
    O Imperador representa estrutura, autoridade e estabilidade.
    Ele transforma caos em ordem através de limites claros.
    """.trimIndent(),

    lightMeaning = """
    Liderança responsável, disciplina e construção sólida.
    Indica capacidade de assumir controle e sustentar decisões no longo prazo.
    """.trimIndent(),

    shadowMeaning = """
    Rigidez, autoritarismo ou necessidade excessiva de controle.
    Pode indicar medo de vulnerabilidade ou dificuldade em ceder.
    """.trimIndent(),

    guidanceWhenReversed = """
    Estrutura deve sustentar, não sufocar.
    Reavalie onde o controle está substituindo a confiança.
    """.trimIndent(),

    keywordsLight = listOf(
        "Autoridade", "Estrutura", "Disciplina", "Estabilidade", "Responsabilidade"
    ),

    keywordsShadow = listOf(
        "Rigidez", "Controle", "Autoritarismo", "Inflexibilidade"
    ),

    correspondences = Correspondences(
        element = Element.FIRE,
        planet = Planet.MARS,
        zodiac = ZodiacSign.ARIES,
        numerology = 4
    ),

    history = """
    Sentado em um trono de pedra, o Imperador simboliza o princípio paterno:
    aquele que protege, organiza e dá forma ao mundo material.
    """.trimIndent(),

    imageResId = R.drawable.major_04
)

val theHierophant = TarotCard(
    id = "major_05",
    name = "O Hierofante",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 5,

    essence = """
    O Hierofante representa tradição, valores e transmissão de conhecimento.
    Ele conecta o indivíduo a sistemas de significado maiores que si mesmo.
    """.trimIndent(),

    lightMeaning = """
    Aprendizado, orientação espiritual e respeito aos valores compartilhados.
    Indica o momento de aprender com mestres, tradições ou estruturas consolidadas.
    """.trimIndent(),

    shadowMeaning = """
    Dogmatismo, rigidez moral ou submissão cega à autoridade.
    Pode indicar medo de questionar crenças herdadas.
    """.trimIndent(),

    guidanceWhenReversed = """
    Honrar a tradição não exige abandonar o pensamento crítico.
    Pergunte-se quais valores ainda são verdadeiros para você.
    """.trimIndent(),

    keywordsLight = listOf(
        "Tradição", "Aprendizado", "Valores", "Orientação", "Espiritualidade"
    ),

    keywordsShadow = listOf(
        "Dogma", "Rigidez", "Conformismo", "Autoridade Cega"
    ),

    correspondences = Correspondences(
        element = Element.EARTH,
        planet = Planet.VENUS,
        zodiac = ZodiacSign.TAURUS,
        numerology = 5
    ),

    history = """
    O Hierofante atua como ponte entre o sagrado e o cotidiano,
    transmitindo conhecimento espiritual por meio de rituais e ensinamentos.
    """.trimIndent(),

    imageResId = R.drawable.major_05
)
val theLovers = TarotCard(
    id = "major_06",
    name = "Os Enamorados",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 6,

    essence = """
    Os Enamorados representam escolha consciente e alinhamento de valores.
    É a união entre desejo, ética e responsabilidade.
    """.trimIndent(),

    lightMeaning = """
    Relacionamentos significativos, escolhas alinhadas e integração de opostos.
    Indica decisões tomadas com o coração e a consciência.
    """.trimIndent(),

    shadowMeaning = """
    Indecisão, dependência emocional ou conflito interno.
    Pode indicar escolhas feitas por medo ou prazer imediato.
    """.trimIndent(),

    guidanceWhenReversed = """
    Toda escolha envolve renúncia.
    Observe se você está escolhendo por verdade ou evitando consequências.
    """.trimIndent(),

    keywordsLight = listOf(
        "Escolha", "Relacionamento", "Alinhamento", "União", "Valor"
    ),

    keywordsShadow = listOf(
        "Indecisão", "Dependência", "Conflito", "Desequilíbrio"
    ),

    correspondences = Correspondences(
        element = Element.AIR,
        planet = Planet.MERCURY,
        zodiac = ZodiacSign.GEMINI,
        numerology = 6
    ),

    history = """
    Mais do que romance, os Enamorados simbolizam a necessidade
    de escolher conscientemente o caminho que se deseja seguir.
    """.trimIndent(),

    imageResId = R.drawable.major_06
)
val theChariot = TarotCard(
    id = "major_07",
    name = "O Carro",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 7,

    essence = """
    O Carro representa direção, movimento e domínio da vontade.
    Ele avança quando forças opostas são integradas.
    """.trimIndent(),

    lightMeaning = """
    Determinação, progresso e conquista por esforço consciente.
    Indica vitória através do foco e da disciplina emocional.
    """.trimIndent(),

    shadowMeaning = """
    Impulsividade, agressividade ou avanço sem propósito claro.
    Pode indicar conflito interno ou perda de controle.
    """.trimIndent(),

    guidanceWhenReversed = """
    Avançar exige mais do que força — exige direção.
    Reavalie se você está se movendo por vontade ou por fuga.
    """.trimIndent(),

    keywordsLight = listOf(
        "Direção", "Vitória", "Foco", "Determinação", "Movimento"
    ),

    keywordsShadow = listOf(
        "Impulsividade", "Conflito", "Perda de Controle", "Pressa"
    ),

    correspondences = Correspondences(
        element = Element.WATER,
        planet = Planet.MOON,
        zodiac = ZodiacSign.CANCER,
        numerology = 7
    ),

    history = """
    O condutor do Carro simboliza a mente que domina emoções opostas,
    conduzindo-as com firmeza rumo a um objetivo claro.
    """.trimIndent(),

    imageResId = R.drawable.major_07
)
val strength = TarotCard(
    id = "major_08",
    name = "A Força",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 8,

    essence = """
    A Força representa o poder que nasce da gentileza e da autoconsciência.
    É o domínio do instinto através da presença.
    """.trimIndent(),

    lightMeaning = """
    Coragem, compaixão e equilíbrio emocional.
    Indica capacidade de enfrentar desafios sem violência ou repressão.
    """.trimIndent(),

    shadowMeaning = """
    Repressão emocional, orgulho ou uso inadequado da força.
    Pode indicar perda de controle ou negação dos próprios impulsos.
    """.trimIndent(),

    guidanceWhenReversed = """
    Força verdadeira não é resistência rígida, mas adaptação consciente.
    Observe onde você está lutando contra si mesmo.
    """.trimIndent(),

    keywordsLight = listOf(
        "Coragem", "Autodomínio", "Compaixão", "Equilíbrio", "Confiança"
    ),

    keywordsShadow = listOf(
        "Repressão", "Orgulho", "Agressividade", "Desequilíbrio"
    ),

    correspondences = Correspondences(
        element = Element.FIRE,
        planet = Planet.SUN,
        zodiac = ZodiacSign.LEO,
        numerology = 8
    ),

    history = """
    A Força mostra que o verdadeiro poder não domina pela imposição,
    mas pela compreensão profunda da própria natureza.
    """.trimIndent(),

    imageResId = R.drawable.major_08
)

val theHermit = TarotCard(
    id = "major_09",
    name = "O Eremita",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 9,

    essence = """
    O Eremita representa introspecção, sabedoria e busca interior.
    Ele se afasta do ruído externo para encontrar clareza.
    """.trimIndent(),

    lightMeaning = """
    Autoconhecimento, prudência e reflexão profunda.
    Indica um momento de pausa consciente para alinhar propósito e verdade.
    """.trimIndent(),

    shadowMeaning = """
    Isolamento excessivo, solidão ou medo de se reconectar.
    Pode indicar evasão do mundo ou rigidez intelectual.
    """.trimIndent(),

    guidanceWhenReversed = """
    Retirar-se é saudável, mas esconder-se não.
    Leve a luz que encontrou de volta ao mundo.
    """.trimIndent(),

    keywordsLight = listOf(
        "Sabedoria", "Introspecção", "Silêncio", "Prudência", "Busca Interior"
    ),

    keywordsShadow = listOf(
        "Isolamento", "Solidão", "Rigidez", "Evasão"
    ),

    correspondences = Correspondences(
        element = Element.EARTH,
        planet = Planet.MERCURY,
        zodiac = ZodiacSign.VIRGO,
        numerology = 9
    ),

    history = """
    Com sua lanterna, o Eremita ilumina o caminho não para os outros,
    mas para si mesmo — confiando que outros seguirão quando estiverem prontos.
    """.trimIndent(),

    imageResId = R.drawable.major_09
)
val wheelOfFortune = TarotCard(
    id = "major_10",
    name = "A Roda da Fortuna",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 10,

    essence = """
    A Roda da Fortuna representa os ciclos inevitáveis da vida.
    Mudança, movimento e o jogo entre destino e livre-arbítrio.
    """.trimIndent(),

    lightMeaning = """
    Mudanças favoráveis, viradas de sorte e novas oportunidades.
    Indica que o fluxo da vida está em movimento e pede adaptação.
    """.trimIndent(),

    shadowMeaning = """
    Resistência à mudança ou sensação de falta de controle.
    Pode indicar repetição de padrões ou instabilidade emocional.
    """.trimIndent(),

    guidanceWhenReversed = """
    Nem tudo pode ser controlado, mas tudo pode ser aprendido.
    Observe os padrões que se repetem e ajuste sua postura.
    """.trimIndent(),

    keywordsLight = listOf(
        "Ciclos", "Mudança", "Destino", "Oportunidade", "Movimento"
    ),

    keywordsShadow = listOf(
        "Instabilidade", "Resistência", "Repetição", "Imprevisibilidade"
    ),

    correspondences = Correspondences(
        element = Element.FIRE,
        planet = Planet.JUPITER,
        zodiac = null,
        numerology = 10
    ),

    history = """
    A Roda simboliza a dança eterna entre ascensão e queda,
    lembrando que nenhuma posição é permanente.
    """.trimIndent(),

    imageResId = R.drawable.major_10
)
val justice = TarotCard(
    id = "major_11",
    name = "A Justiça",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 11,

    essence = """
    A Justiça representa equilíbrio, verdade e responsabilidade.
    Toda ação gera consequências — internas e externas.
    """.trimIndent(),

    lightMeaning = """
    Clareza, honestidade e decisões justas.
    Indica alinhamento entre valores, ações e resultados.
    """.trimIndent(),

    shadowMeaning = """
    Negação da verdade, parcialidade ou rigidez moral.
    Pode indicar autoengano ou medo de assumir responsabilidades.
    """.trimIndent(),

    guidanceWhenReversed = """
    Seja honesto consigo antes de exigir justiça do mundo.
    O equilíbrio começa no reconhecimento da própria verdade.
    """.trimIndent(),

    keywordsLight = listOf(
        "Verdade", "Equilíbrio", "Responsabilidade", "Ética", "Clareza"
    ),

    keywordsShadow = listOf(
        "Injustiça", "Rigidez", "Negação", "Autoengano"
    ),

    correspondences = Correspondences(
        element = Element.AIR,
        planet = Planet.VENUS,
        zodiac = ZodiacSign.LIBRA,
        numerology = 11
    ),

    history = """
    Com espada e balança, a Justiça corta ilusões
    e pesa ações com imparcialidade.
    """.trimIndent(),

    imageResId = R.drawable.major_11
)
val theHangedMan = TarotCard(
    id = "major_12",
    name = "O Enforcado",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 12,

    essence = """
    O Enforcado representa pausa, rendição e mudança de perspectiva.
    A sabedoria surge quando o controle é attachment.
    """.trimIndent(),

    lightMeaning = """
    Aceitação, entrega consciente e nova visão.
    Indica crescimento através da suspensão da ação imediata.
    """.trimIndent(),

    shadowMeaning = """
    Estagnação, vitimização ou sacrifício inútil.
    Pode indicar apego ao sofrimento ou medo de agir.
    """.trimIndent(),

    guidanceWhenReversed = """
    Nem toda pausa é iluminação.
    Reflita se você está escolhendo parar ou apenas evitando avançar.
    """.trimIndent(),

    keywordsLight = listOf(
        "Entrega", "Perspectiva", "Pausa", "Sacrifício Consciente", "Aceitação"
    ),

    keywordsShadow = listOf(
        "Estagnação", "Vitimização", "Apego", "Paralisia"
    ),

    correspondences = Correspondences(
        element = Element.WATER,
        planet = Planet.NEPTUNE,
        zodiac = null,
        numerology = 12
    ),

    history = """
    Suspenso de cabeça para baixo, o Enforcado vê o mundo invertido,
    revelando verdades ocultas pela pressa cotidiana.
    """.trimIndent(),

    imageResId = R.drawable.major_12
)
val death = TarotCard(
    id = "major_13",
    name = "A Morte",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 13,

    essence = """
    A Morte representa transformação profunda e encerramento de ciclos.
    Nada novo nasce sem que algo termine.
    """.trimIndent(),

    lightMeaning = """
    Renascimento, libertação e mudança inevitável.
    Indica o fim de uma fase para dar lugar a outra mais alinhada.
    """.trimIndent(),

    shadowMeaning = """
    Resistência à mudança, medo de perdas ou apego ao passado.
    Pode indicar prolongamento desnecessário do sofrimento.
    """.trimIndent(),

    guidanceWhenReversed = """
    O fim não é punição, é passagem.
    Soltar conscientemente reduz a dor da transformação.
    """.trimIndent(),

    keywordsLight = listOf(
        "Transformação", "Fim", "Renascimento", "Libertação", "Ciclo"
    ),

    keywordsShadow = listOf(
        "Apego", "Medo", "Resistência", "Estagnação"
    ),

    correspondences = Correspondences(
        element = Element.WATER,
        planet = Planet.PLUTO,
        zodiac = ZodiacSign.SCORPIO,
        numerology = 13
    ),

    history = """
    Tradicionalmente mal interpretada, a Morte não fala de destruição,
    mas da renovação que só ocorre após o fim do que já cumpriu seu papel.
    """.trimIndent(),

    imageResId = R.drawable.major_13
)
val temperance = TarotCard(
    id = "major_14",
    name = "A Temperança",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 14,

    essence = """
    A Temperança representa equilíbrio, integração e harmonia dinâmica.
    É a arte de misturar opostos com consciência.
    """.trimIndent(),

    lightMeaning = """
    Moderação, cura e adaptação inteligente.
    Indica um período de alinhamento entre mente, corpo e espírito.
    """.trimIndent(),

    shadowMeaning = """
    Desequilíbrio, excessos ou dificuldade de integração.
    Pode indicar conflitos internos não resolvidos.
    """.trimIndent(),

    guidanceWhenReversed = """
    Equilíbrio não é estagnação.
    Ajustes conscientes exigem atenção contínua.
    """.trimIndent(),

    keywordsLight = listOf(
        "Equilíbrio", "Harmonia", "Cura", "Integração", "Moderação"
    ),

    keywordsShadow = listOf(
        "Excesso", "Desequilíbrio", "Desarmonia", "Conflito Interno"
    ),

    correspondences = Correspondences(
        element = Element.FIRE,
        planet = Planet.JUPITER,
        zodiac = ZodiacSign.SAGITTARIUS,
        numerology = 14
    ),

    history = """
    A Temperança mistura águas opostas, simbolizando a alquimia interna
    que transforma conflito em sabedoria prática.
    """.trimIndent(),

    imageResId = R.drawable.major_14
)
val devil = TarotCard(
    id = "major_15",
    name = "O Diabo",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 15,

    essence = """
    O Diabo representa aprisionamentos inconscientes e desejos não integrados.
    É o poder da sombra quando não é reconhecida.
    """.trimIndent(),

    lightMeaning = """
    Consciência sobre impulsos, desejos e padrões.
    Autenticidade radical e libertação através do reconhecimento da sombra.
    """.trimIndent(),

    shadowMeaning = """
    Vícios, dependências e relações tóxicas.
    Ilusão de liberdade enquanto se permanece preso.
    """.trimIndent(),

    guidanceWhenReversed = """
    Nomear a sombra dissolve seu poder.
    Liberdade começa com responsabilidade pessoal.
    """.trimIndent(),

    keywordsLight = listOf(
        "Consciência", "Desejo", "Autenticidade", "Libertação"
    ),

    keywordsShadow = listOf(
        "Vício", "Dependência", "Aprisionamento", "Autoengano"
    ),

    correspondences = Correspondences(
        element = Element.EARTH,
        planet = Planet.SATURN,
        zodiac = ZodiacSign.CAPRICORN,
        numerology = 15
    ),

    history = """
    Tradicionalmente retrata correntes soltas, indicando que a prisão
    é mais psicológica do que real.
    """.trimIndent(),

    imageResId = R.drawable.major_15
)
val tower = TarotCard(
    id = "major_16",
    name = "A Torre",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 16,

    essence = """
    A Torre representa a queda de estruturas falsas.
    O choque necessário para restaurar a verdade.
    """.trimIndent(),

    lightMeaning = """
    Libertação súbita, despertar e revelação.
    O que cai já não sustentava a alma.
    """.trimIndent(),

    shadowMeaning = """
    Medo da mudança, colapso traumático e resistência à verdade.
    Rupturas vividas com sofrimento desnecessário.
    """.trimIndent(),

    guidanceWhenReversed = """
    Não reconstrua o que desabou por ilusão.
    Use a queda como base para algo autêntico.
    """.trimIndent(),

    keywordsLight = listOf(
        "Despertar", "Verdade", "Libertação", "Ruptura"
    ),

    keywordsShadow = listOf(
        "Caos", "Medo", "Perda", "Colapso"
    ),

    correspondences = Correspondences(
        element = Element.FIRE,
        planet = Planet.MARS,
        zodiac = null,
        numerology = 16
    ),

    history = """
    A Torre simboliza a destruição do ego inflado
    e das construções baseadas em falsidade.
    """.trimIndent(),

    imageResId = R.drawable.major_16
)
val star = TarotCard(
    id = "major_17",
    name = "A Estrela",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 17,

    essence = """
    A Estrela representa esperança, cura e fé restaurada.
    A luz suave após a tempestade.
    """.trimIndent(),

    lightMeaning = """
    Renovação espiritual, serenidade e confiança no fluxo da vida.
    Cura profunda e alinhamento interior.
    """.trimIndent(),

    shadowMeaning = """
    Desânimo, perda de fé ou dificuldade em confiar no futuro.
    Esperança fragilizada.
    """.trimIndent(),

    guidanceWhenReversed = """
    A esperança não precisa ser grandiosa.
    Basta manter a chama acesa.
    """.trimIndent(),

    keywordsLight = listOf(
        "Esperança", "Cura", "Fé", "Renovação"
    ),

    keywordsShadow = listOf(
        "Desânimo", "Descrença", "Vulnerabilidade"
    ),

    correspondences = Correspondences(
        element = Element.AIR,
        planet = Planet.URANUS,
        zodiac = ZodiacSign.AQUARIUS,
        numerology = 17
    ),

    history = """
    A Estrela simboliza a confiança no invisível
    e a restauração da fé após grandes rupturas.
    """.trimIndent(),

    imageResId = R.drawable.major_17
)
val moon = TarotCard(
    id = "major_18",
    name = "A Lua",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 18,

    essence = """
    A Lua representa o inconsciente, os mistérios e as ilusões.
    O caminho através da sombra emocional.
    """.trimIndent(),

    lightMeaning = """
    Intuição aguçada, sensibilidade e contato profundo com o inconsciente.
    Sonhos e sinais ganham relevância.
    """.trimIndent(),

    shadowMeaning = """
    Medos, confusão, ansiedade e enganos internos.
    Dificuldade em discernir realidade de ilusão.
    """.trimIndent(),

    guidanceWhenReversed = """
    Nem tudo precisa ser entendido agora.
    Confie no ritmo da revelação.
    """.trimIndent(),

    keywordsLight = listOf(
        "Intuição", "Sensibilidade", "Mistério", "Inconsciente"
    ),

    keywordsShadow = listOf(
        "Medo", "Confusão", "Ilusão", "Ansiedade"
    ),

    correspondences = Correspondences(
        element = Element.WATER,
        planet = Planet.MOON,
        zodiac = ZodiacSign.PISCES,
        numerology = 18
    ),

    history = """
    A Lua aponta para os desafios do caminho noturno da alma,
    onde a razão não é guia suficiente.
    """.trimIndent(),

    imageResId = R.drawable.major_18
)
val sun = TarotCard(
    id = "major_19",
    name = "O Sol",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 19,

    essence = """
    O Sol representa clareza, vitalidade e alegria consciente.
    A verdade iluminada sem distorções.
    """.trimIndent(),

    lightMeaning = """
    Sucesso, autenticidade e energia vital elevada.
    Momento de expansão e celebração.
    """.trimIndent(),

    shadowMeaning = """
    Excesso de ego, otimismo ingênuo ou necessidade de validação externa.
    """.trimIndent(),

    guidanceWhenReversed = """
    Brilhe sem competir.
    A luz verdadeira não ofusca, aquece.
    """.trimIndent(),

    keywordsLight = listOf(
        "Clareza", "Vitalidade", "Sucesso", "Alegria"
    ),

    keywordsShadow = listOf(
        "Ego", "Excesso", "Ingenuidade"
    ),

    correspondences = Correspondences(
        element = Element.FIRE,
        planet = Planet.SUN,
        zodiac = ZodiacSign.LEO,
        numerology = 19
    ),

    history = """
    O Sol simboliza a consciência plena
    e a integração harmoniosa do eu.
    """.trimIndent(),

    imageResId = R.drawable.major_19
)
val judgement = TarotCard(
    id = "major_20",
    name = "O Julgamento",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 20,

    essence = """
    O Julgamento representa renascimento e chamado interior.
    A resposta inevitável da alma.
    """.trimIndent(),

    lightMeaning = """
    Despertar, libertação do passado e clareza de propósito.
    Um novo ciclo se inicia.
    """.trimIndent(),

    shadowMeaning = """
    Culpa, autojulgamento severo e medo de responder ao chamado.
    """.trimIndent(),

    guidanceWhenReversed = """
    Ouça o chamado interno, não o ruído externo.
    A redenção começa com aceitação.
    """.trimIndent(),

    keywordsLight = listOf(
        "Renascimento", "Despertar", "Propósito", "Libertação"
    ),

    keywordsShadow = listOf(
        "Culpa", "Autojulgamento", "Resistência"
    ),

    correspondences = Correspondences(
        element = Element.FIRE,
        planet = Planet.PLUTO,
        zodiac = null,
        numerology = 20
    ),

    history = """
    O Julgamento marca o momento de revisão final
    antes da integração completa do ciclo.
    """.trimIndent(),

    imageResId = R.drawable.major_20
)
val world = TarotCard(
    id = "major_21",
    name = "O Mundo",
    arcana = ArcanaType.MAJOR,
    suit = null,
    number = 21,

    essence = """
    O Mundo representa conclusão, integração e plenitude.
    A união entre interno e externo.
    """.trimIndent(),

    lightMeaning = """
    Realização, encerramento bem-sucedido e senso de totalidade.
    O ciclo se completa.
    """.trimIndent(),

    shadowMeaning = """
    Sensação de incompletude ou dificuldade em encerrar ciclos.
    Apego ao passado.
    """.trimIndent(),

    guidanceWhenReversed = """
    Reconheça o quanto já foi conquistado.
    A conclusão é parte do crescimento.
    """.trimIndent(),

    keywordsLight = listOf(
        "Plenitude", "Conclusão", "Integração", "Realização"
    ),

    keywordsShadow = listOf(
        "Incompletude", "Apego", "Resistência ao fim"
    ),

    correspondences = Correspondences(
        element = Element.EARTH,
        planet = Planet.SATURN,
        zodiac = null,
        numerology = 21
    ),

    history = """
    O Mundo encerra os Arcanos Maiores,
    simbolizando a totalidade da jornada da alma.
    """.trimIndent(),

    imageResId = R.drawable.major_21
)

val majorArcanaCards = listOf(
    theFool,
    theMagician,
    theHighPriestess,
    theEmpress,
    theEmperor,
    theHierophant,
    theLovers,
    theChariot,
    strength,
    theHermit,
    wheelOfFortune,
    justice,
    theHangedMan,
    death,
    temperance,
    devil,
    tower,
    star,
    moon,
    sun,
    judgement,
    world


    )


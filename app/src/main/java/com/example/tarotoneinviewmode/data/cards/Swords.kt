package com.example.tarotoneinviewmode.data.cards

import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.domain.enums.ArcanaType
import com.example.tarotoneinviewmode.domain.enums.Element
import com.example.tarotoneinviewmode.domain.enums.Planet
import com.example.tarotoneinviewmode.domain.enums.Suit
import com.example.tarotoneinviewmode.domain.enums.ZodiacSign
import com.example.tarotoneinviewmode.domain.model.Correspondences
import com.example.tarotoneinviewmode.domain.model.TarotCard

val swordsCards = listOf(
    TarotCard(
        id = "swords_01",
        name = "Ás de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 1,
        essence = "O Ás de Espadas representa clareza, verdade e avanços. Simboliza o poder da mente e novos começos no pensamento e na comunicação.",
        lightMeaning = "Em pé, o Ás de Espadas significa clareza mental, novas ideias e a coragem de encarar a verdade. Incentiva o uso da lógica e do intelecto para superar desafios.",
        shadowMeaning = "Esta carta pode indicar confusão, má comunicação ou falta de foco. Sugere reavaliar seus pensamentos e buscar clareza antes de agir.",
        guidanceWhenReversed = "Indica confusão, falha na comunicação ou falta de foco intelectual.",
        keywordsLight = listOf("Clareza", "Avanço", "Verdade", "Intelecto", "Novas Ideias"),
        keywordsShadow = listOf("Confusão", "Má Comunicação", "Bloqueio Mental"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.MERCURY,
            zodiac = ZodiacSign.GEMINI,
            numerology = 1
        ),
        history = "O Ás de Espadas tradicionalmente retrata uma mão segurando uma espada emergindo de uma nuvem, simbolizando inspiração divina e o triunfo da razão sobre a confusão.",
        imageResId = R.drawable.swords_01
    ),
    TarotCard(
        id = "swords_02",
        name = "Dois de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 2,
        essence = "Representa tomada de decisão, equilíbrio e o desafio de pesar opções ou ideias opostas.",
        lightMeaning = "Em pé, significa a necessidade de tomar uma decisão, muitas vezes entre duas opções igualmente desafiadoras. Incentiva a busca pelo equilíbrio interno.",
        shadowMeaning = "Pode indicar indecisão, evitação ou estar paralisado por escolhas conflitantes. Aconselha enfrentar o problema de frente para avançar.",
        guidanceWhenReversed = "Indica indecisão crônica, fuga da realidade ou estar preso entre escolhas conflitantes.",
        keywordsLight = listOf("Decisões", "Equilíbrio", "Escolhas", "Conflito Interno"),
        keywordsShadow = listOf("Indecisão", "Evasão", "Confusão"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.MOON,
            zodiac = ZodiacSign.LIBRA,
            numerology = 2
        ),
        history = "Tradicionalmente, retrata uma figura vendada segurando duas espadas cruzadas, simbolizando a necessidade de equilíbrio e uma escolha difícil que exige introspecção.",
        imageResId = R.drawable.swords_02
    ),
    TarotCard(
        id = "swords_03",
        name = "Três de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 3,
        essence = "Representa decepção, tristeza e dor emocional. Frequentemente simboliza desafios em relacionamentos ou lutas pessoais.",
        lightMeaning = "Em pé, significa mágoa, perda ou agitação emocional. Incentiva o reconhecimento da dor e a busca pela cura através da introspecção.",
        shadowMeaning = "Esta carta indica recuperação, perdão ou a liberação de uma dor antiga. Aconselha deixar de lado fardos emocionais para abraçar um novo capítulo.",
        guidanceWhenReversed = "Indica recuperação, perdão e a liberação de dores passadas.",
        keywordsLight = listOf("Decepção", "Perda", "Tristeza", "Dor Emocional"),
        keywordsShadow = listOf("Recuperação", "Perdão", "Cura"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.SATURN,
            zodiac = ZodiacSign.LIBRA,
            numerology = 3
        ),
        history = "Tradicionalmente retrata um coração perfurado por três espadas sob chuva ou nuvens tempestuosas, simbolizando o luto e o sofrimento emocional.",
        imageResId = R.drawable.swords_03
    ),
    TarotCard(
        id = "swords_04",
        name = "Quatro de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 4,
        essence = "Representa descanso, recuperação e contemplação. Incentiva o recuo para restaurar o equilíbrio mental e emocional.",
        lightMeaning = "Em pé, significa repouso, recuperação e clareza mental. Incentiva o distanciamento temporário para recuperar a perspectiva e a energia.",
        shadowMeaning = "Pode indicar estafa (burnout), inquietação ou negligência com o próprio descanso. Aconselha priorizar o autocuidado.",
        guidanceWhenReversed = "Alerta para esgotamento, inquietação ou incapacidade de fazer uma pausa necessária.",
        keywordsLight = listOf("Descanso", "Recuperação", "Introspecção", "Clareza"),
        keywordsShadow = listOf("Esgotamento", "Inquietação", "Sobrecarga"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.JUPITER,
            zodiac = ZodiacSign.LIBRA,
            numerology = 4
        ),
        history = "Muitas vezes retrata uma figura deitada com as mãos em prece, como em meditação, simbolizando o repouso do guerreiro e a recuperação das energias.",
        imageResId = R.drawable.swords_04
    ),
    TarotCard(
        id = "swords_05",
        name = "Cinco de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 5,
        essence = "Representa conflito, tensão e as consequências de uma batalha. Simboliza a vitória a qualquer custo.",
        lightMeaning = "Em pé, significa conflito, tensão e potencial traição. Pode indicar uma vitória vazia, onde o dano causado supera o ganho obtido.",
        shadowMeaning = "Pode indicar a resolução de uma disputa ou o desejo de fazer as pazes. Sugere abandonar ressentimentos e seguir em frente.",
        guidanceWhenReversed = "Indica a resolução de conflitos, desejo de reconciliação ou o fim de uma disputa.",
        keywordsLight = listOf("Conflito", "Tensão", "Traição", "Vitória Vazia"),
        keywordsShadow = listOf("Resolução", "Reconciliação", "Perdão"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.VENUS,
            zodiac = ZodiacSign.AQUARIUS,
            numerology = 5
        ),
        history = "Retrata uma figura recolhendo espadas após uma batalha enquanto outros se afastam, sugerindo que certas vitórias são, na verdade, perdas morais.",
        imageResId = R.drawable.swords_05
    ),
    TarotCard(
        id = "swords_06",
        name = "Seis de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 6,
        essence = "Representa transição, mudança e o ato de seguir em frente, deixando dificuldades para trás em busca de paz.",
        lightMeaning = "Em pé, significa progredir após um período difícil. Sugere deixar para trás o peso emocional e rumar para um estado mental mais pacífico.",
        shadowMeaning = "Pode indicar estagnação ou resistência à mudança. Sugere a necessidade de aceitar o passado para poder finalmente curar-se.",
        guidanceWhenReversed = "Indica estagnação, relutância em mudar ou bagagem emocional não resolvida.",
        keywordsLight = listOf("Transição", "Mudança", "Cura", "Seguir em Frente"),
        keywordsShadow = listOf("Estagnação", "Relutância", "Resistência"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.MERCURY,
            zodiac = ZodiacSign.AQUARIUS,
            numerology = 6
        ),
        history = "Mostra um barco atravessando águas turbulentas rumo a águas calmas, simbolizando uma jornada necessária para longe de tempos difíceis.",
        imageResId = R.drawable.swords_06
    ),
    TarotCard(
        id = "swords_07",
        name = "Sete de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 7,
        essence = "Representa engano, estratégia e segredo. Sugere agir com astúcia ou evitar confrontos diretos.",
        lightMeaning = "Em pé, sugere a necessidade de uma abordagem estratégica. Pode indicar que alguém está agindo de forma oculta ou escondendo intenções reais.",
        shadowMeaning = "Pode indicar o fim de uma mentira ou a decisão de enfrentar as consequências das próprias ações. Sugere honestidade após omissões.",
        guidanceWhenReversed = "Indica honestidade, confissão ou a revelação de verdades antes ocultas.",
        keywordsLight = listOf("Engano", "Estratégia", "Segredo", "Evasão"),
        keywordsShadow = listOf("Honestidade", "Verdade", "Revelação", "Confronto"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.MOON,
            zodiac = ZodiacSign.AQUARIUS,
            numerology = 7
        ),
        history = "Retrata uma figura fugindo com espadas, sugerindo astúcia, segredo ou a necessidade de discrição e tática em uma situação específica.",
        imageResId = R.drawable.swords_07
    ),
    TarotCard(
        id = "swords_08",
        name = "Oito de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 8,
        essence = "Representa restrição e a sensação de estar preso, sugerindo limitações criadas pelos próprios pensamentos.",
        lightMeaning = "Em pé, significa sentir-se restringido pelas circunstâncias. Sugere que a clareza de pensamento é a chave para superar limitações autoimpostas.",
        shadowMeaning = "Indica a possibilidade de libertar-se. Sugere que as restrições são mentais e podem ser removidas mudando a forma de ver o problema.",
        guidanceWhenReversed = "Indica libertação de medos, quebra de barreiras mentais e nova perspectiva.",
        keywordsLight = listOf("Restrição", "Limitação", "Aprisionamento", "Bloqueio Mental"),
        keywordsShadow = listOf("Liberdade", "Liberação", "Superação"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.JUPITER,
            zodiac = ZodiacSign.GEMINI,
            numerology = 8
        ),
        history = "Mostra uma figura cercada por espadas, simbolizando o aprisionamento mental ou a ilusão de estar sem saída em uma situação.",
        imageResId = R.drawable.swords_08
    ),
    TarotCard(
        id = "swords_09",
        name = "Nove de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 9,
        essence = "Representa ansiedade, culpa e angústia mental. Reflete o tormento da mente e o medo de cenários negativos.",
        lightMeaning = "Em pé, significa ansiedade e noites mal dormidas. Sugere que o medo e a culpa estão afetando a saúde mental, mas lembra que o pior está na mente.",
        shadowMeaning = "Indica a saída de um estado de angústia. Sugere trabalhar os traumas e encontrar finalmente a paz de espírito.",
        guidanceWhenReversed = "Indica a liberação da ansiedade, o fim de pesadelos e o início da paz mental.",
        keywordsLight = listOf("Ansiedade", "Culpa", "Angústia", "Medo"),
        keywordsShadow = listOf("Liberação", "Paz de Espírito", "Cura", "Desapego"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.MARS,
            zodiac = ZodiacSign.GEMINI,
            numerology = 9
        ),
        history = "Retrata uma figura sentada na cama em desespero, refletindo a agonia de noites sem dormir preenchidas por preocupação e medo do futuro.",
        imageResId = R.drawable.swords_09
    ),
    TarotCard(
        id = "swords_10",
        name = "Dez de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 10,
        essence = "Representa traição, perda e o fim de um ciclo doloroso. Indica o fundo do poço, mas também o potencial de recomeço.",
        lightMeaning = "Em pé, significa um final doloroso ou traição. No entanto, indica que o ciclo terminou e que, a partir do fundo do poço, só se pode subir.",
        shadowMeaning = "Pode sugerir o fim do sofrimento ou alívio após uma crise. Representa a oportunidade de reconstruir-se após a tempestade.",
        guidanceWhenReversed = "Sugere a superação de uma derrota, o início da cura e a sobrevivência a um trauma.",
        keywordsLight = listOf("Traição", "Fim Doloroso", "Fundo do Poço", "Derrota"),
        keywordsShadow = listOf("Recuperação", "Cura", "Recomeço", "Alívio"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.SUN,
            zodiac = ZodiacSign.GEMINI,
            numerology = 10
        ),
        history = "Mostra uma figura prostrada com dez espadas nas costas, simbolizando a finalidade de uma situação dolorosa ou uma traição definitiva.",
        imageResId = R.drawable.swords_10
    ),
    TarotCard(
        id = "swords_page",
        name = "Valete de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 11,
        essence = "Representa curiosidade, exploração e o desejo de conhecimento. Simboliza uma abordagem jovem e fresca para novas ideias.",
        lightMeaning = "Em pé, representa sede de saber. Pode sugerir um momento ideal para estudos, pesquisas ou o início de um novo projeto intelectual.",
        shadowMeaning = "Pode indicar indiscrição ou o uso da mente para fins enganosos. Alerta para não ser precipitado em julgamentos ou palavras.",
        guidanceWhenReversed = "Alerta para fofocas, espionagem ou o uso manipulador da inteligência.",
        keywordsLight = listOf("Curiosidade", "Aprendizado", "Descoberta", "Energia Jovem"),
        keywordsShadow = listOf("Fofoca", "Espionagem", "Engano", "Insegurança"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = null,
            zodiac = ZodiacSign.GEMINI,
            numerology = 11
        ),
        history = "Retrata um jovem atento com sua espada, simbolizando uma mente afiada, a disposição para questionar e a busca incessante pela verdade.",
        imageResId = R.drawable.swords_page
    ),
    TarotCard(
        id = "swords_knight",
        name = "Cavaleiro de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 12,
        essence = "Representa ação, ambição e determinação. Significa alguém que avança com um objetivo claro e mente focada.",
        lightMeaning = "Em pé, significa um período de ação assertiva e comunicação direta. Incentiva a tomada de decisões rápidas e o avanço com confiança.",
        shadowMeaning = "Sugere pressa excessiva ou grosseria. Alerta contra agir impulsivamente sem considerar como isso afeta os outros.",
        guidanceWhenReversed = "Sugere impulsividade, comportamento imprudente ou agir sem pensar nas consequências.",
        keywordsLight = listOf("Ação", "Ambição", "Determinação", "Raciocínio Rápido"),
        keywordsShadow = listOf("Impulsividade", "Imprudência", "Conflito", "Desfocado"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.MARS,
            zodiac = ZodiacSign.AQUARIUS,
            numerology = 12
        ),
        history = "Mostra um cavaleiro cavalgando velozmente para a batalha, encarnando a determinação absoluta e o impulso de agir rapidamente.",
        imageResId = R.drawable.swords_knight
    ),
    TarotCard(
        id = "swords_queen",
        name = "Rainha de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 13,
        essence = "Representa clareza, intelecto e sabedoria. Encarna uma abordagem objetiva e racional, equilibrando emoção com razão.",
        lightMeaning = "Em pé, significa clareza de pensamento e julgamento imparcial. Incentiva a falar a sua verdade e usar o intelecto para resolver problemas.",
        shadowMeaning = "Pode indicar distanciamento emocional ou crueza nas palavras. Sugere cuidado para não se tornar amarga ou dura demais com os outros.",
        guidanceWhenReversed = "Pode indicar frieza emocional, amargura ou ser excessivamente crítica e julgadora.",
        keywordsLight = listOf("Clareza", "Sabedoria", "Independência", "Racionalidade"),
        keywordsShadow = listOf("Frieza", "Rigor", "Distanciamento", "Crítica"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.VENUS,
            zodiac = ZodiacSign.LIBRA,
            numerology = 13
        ),
        history = "Retratada como uma figura calma e perceptiva, ela usa sua espada com precisão. Representa independência mental e a habilidade de ver através das mentiras.",
        imageResId = R.drawable.swords_queen
    ),
    TarotCard(
        id = "swords_king",
        name = "Rei de Espadas",
        arcana = ArcanaType.MINOR,
        suit = Suit.SWORDS,
        number = 14,
        essence = "Representa autoridade, lógica e liderança intelectual. Usa a razão e a análise para liderar com justiça e sabedoria.",
        lightMeaning = "Em pé, significa domínio da comunicação e decisões baseadas em fatos. Incentiva a liderança pelo exemplo e o pensamento imparcial.",
        shadowMeaning = "Alerta para o uso do intelecto para manipular ou dominar. Pode indicar uma pessoa fria e desconectada das emoções humanas.",
        guidanceWhenReversed = "Sugere abuso de poder, manipulação ou um pensamento excessivamente rígido e frio.",
        keywordsLight = listOf("Autoridade", "Intelecto", "Liderança", "Justiça"),
        keywordsShadow = listOf("Manipulação", "Rigidez", "Abuso de Poder", "Frieza"),
        correspondences = Correspondences(
            element = Element.AIR,
            planet = Planet.JUPITER,
            zodiac = ZodiacSign.AQUARIUS,
            numerology = 14
        ),
        history = "O Rei de Espadas é a autoridade máxima do intelecto, simbolizando a justiça aplicada através da lógica e da diplomacia firme.",
        imageResId = R.drawable.swords_king
    )
)

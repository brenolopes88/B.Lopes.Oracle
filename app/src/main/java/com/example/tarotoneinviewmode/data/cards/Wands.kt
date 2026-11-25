package com.example.tarotoneinviewmode.data.cards

import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.domain.enums.ArcanaType
import com.example.tarotoneinviewmode.domain.enums.Element
import com.example.tarotoneinviewmode.domain.enums.Planet
import com.example.tarotoneinviewmode.domain.enums.Suit
import com.example.tarotoneinviewmode.domain.enums.ZodiacSign
import com.example.tarotoneinviewmode.domain.model.Correspondences
import com.example.tarotoneinviewmode.domain.model.TarotCard

val wandsCards = listOf(
    TarotCard(
        id = "wands_01",
        name = "Ás de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 1,
        essence = "O Ás de Paus simboliza inspiração, potencial e a centelha de novos começos.",
        lightMeaning = "Em pé, o Ás de Paus representa uma explosão de energia criativa, entusiasmo e a coragem de começar algo novo. É uma carta de oportunidade e crescimento.",
        shadowMeaning = "Esta carta indica atrasos, falta de direção ou oportunidades perdidas. Incentiva a reavaliar planos e reacender a inspiração.",
        guidanceWhenReversed = "Indica atrasos, falta de direção ou oportunidades criativas perdidas.",
        keywordsLight = listOf("Inspiração", "Novos Começos", "Potencial", "Criatividade"),
        keywordsShadow = listOf("Atrasos", "Falta de Foco", "Oportunidades Perdidas"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.SUN,
            zodiac = ZodiacSign.ARIES,
            numerology = 1
        ),
        history = "Representando a forma mais pura de energia criativa, o Ás de Paus tem sido associado à inspiração divina e ao nascimento de novos empreendimentos.",
        imageResId = R.drawable.wands_01
    ),
    TarotCard(
        id = "wands_02",
        name = "Dois de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 2,
        essence = "O Dois de Paus significa planejamento, previsão e a exploração de possibilidades futuras.",
        lightMeaning = "Em pé, o Dois de Paus representa planejamento estratégico, tomada de decisão e preparação para expansão. Incentiva a definição de metas claras.",
        shadowMeaning = "Esta carta alerta para indecisão, medo do desconhecido ou falta de progresso. Sugere superar a hesitação e abraçar o crescimento.",
        guidanceWhenReversed = "Alerta para indecisão, medo do desconhecido ou falta de progresso real.",
        keywordsLight = listOf("Planejamento", "Previsão", "Exploração", "Visão"),
        keywordsShadow = listOf("Indecisão", "Estagnação", "Medo de Mudança"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.MARS,
            zodiac = ZodiacSign.ARIES,
            numerology = 2
        ),
        history = "Frequentemente retratado como uma figura segurando um globo, esta carta simboliza uma visão ampla e a coragem de dar os primeiros passos em direção a um objetivo global.",
        imageResId = R.drawable.wands_02
    ),
    TarotCard(
        id = "wands_03",
        name = "Três de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 3,
        essence = "O Três de Paus representa expansão, progresso e a antecipação do sucesso.",
        lightMeaning = "Em pé, o Três de Paus significa crescimento, aventura e a implementação bem-sucedida de planos. Incentiva olhar para frente com otimismo.",
        shadowMeaning = "Esta carta alerta para obstáculos, atrasos ou expectativas irreais. Sugere reavaliar estratégias e ter paciência.",
        guidanceWhenReversed = "Alerta para obstáculos inesperados, atrasos ou expectativas irreais.",
        keywordsLight = listOf("Expansão", "Progresso", "Sucesso", "Visão"),
        keywordsShadow = listOf("Atrasos", "Contratempos", "Excesso de Confiança"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.SUN,
            zodiac = ZodiacSign.LEO,
            numerology = 3
        ),
        history = "Uma carta associada à espera por resultados, o Três de Paus destaca as recompensas da preparação e a coragem de expandir horizontes.",
        imageResId = R.drawable.wands_03
    ),
    TarotCard(
        id = "wands_04",
        name = "Quatro de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 4,
        essence = "O Quatro de Paus significa celebração, harmonia e um senso de comunidade ou lar.",
        lightMeaning = "Em pé, o Quatro de Paus representa o retorno ao lar, celebração e o alcance de marcos importantes. É uma carta de gratidão e harmonia.",
        shadowMeaning = "Esta carta pode indicar instabilidade, conflitos ou expectativas não atendidas. Sugere trabalhar nos relacionamentos e restaurar o equilíbrio.",
        guidanceWhenReversed = "Pode indicar instabilidade familiar, conflitos comunitários ou celebrações adiadas.",
        keywordsLight = listOf("Celebração", "Harmonia", "Unidade", "Estabilidade"),
        keywordsShadow = listOf("Instabilidade", "Conflito", "Incerteza"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.VENUS,
            zodiac = ZodiacSign.SAGITTARIUS,
            numerology = 4
        ),
        history = "Esta carta está ligada a ocasiões alegres, simbolizando unidade, estabilidade e conquistas compartilhadas.",
        imageResId = R.drawable.wands_04
    ),
    TarotCard(
        id = "wands_05",
        name = "Cinco de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 5,
        essence = "O Cinco de Paus representa conflito, competição e a luta para encontrar harmonia.",
        lightMeaning = "Em pé, o Cinco de Paus significa divergências, competição ou tensão criativa. Incentiva a busca por soluções construtivas e trabalho colaborativo.",
        shadowMeaning = "Sugere evitar conflitos, resolver tensões ou desengajar-se de batalhas desnecessárias. Alerta contra ignorar problemas que precisam ser resolvidos.",
        guidanceWhenReversed = "Sugere a resolução de conflitos, evitar disputas desnecessárias ou o fim de uma competição.",
        keywordsLight = listOf("Conflito", "Competição", "Tensão", "Desafio"),
        keywordsShadow = listOf("Resolução", "Evasão", "Harmonia"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.SATURN,
            zodiac = ZodiacSign.LEO,
            numerology = 5
        ),
        history = "Retrata indivíduos envolvidos em uma luta caótica, simbolizando os desafios do conflito e visões divergentes.",
        imageResId = R.drawable.wands_05
    ),
    TarotCard(
        id = "wands_06",
        name = "Seis de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 6,
        essence = "O Seis de Paus significa vitória, reconhecimento e sucesso público.",
        lightMeaning = "Em pé, representa conquistas, confiança e a admiração dos outros. Incentiva a celebrar as vitórias e reconhecer o próprio sucesso.",
        shadowMeaning = "Alerta para autodúvida, contratempos ou falta de reconhecimento. Sugere focar na validação interna em vez da aprovação externa.",
        guidanceWhenReversed = "Alerta para falta de confiança, queda do pedestal ou sentimento de desvalorização.",
        keywordsLight = listOf("Vitória", "Reconhecimento", "Confiança", "Sucesso"),
        keywordsShadow = listOf("Contratempos", "Autodúvida", "Decepção"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.JUPITER,
            zodiac = ZodiacSign.LEO,
            numerology = 6
        ),
        history = "Associado ao triunfo e aclamação, representa as recompensas do esforço e da perseverança perante os outros.",
        imageResId = R.drawable.wands_06
    ),
    TarotCard(
        id = "wands_07",
        name = "Sete de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 7,
        essence = "Representa manter sua posição, perseverança e defender suas convicções.",
        lightMeaning = "Em pé, o Sete de Paus significa determinação, coragem e autoafirmação. Incentiva a manter-se firme em suas crenças.",
        shadowMeaning = "Pode indicar sentir-se sobrecarregado, derrotado ou vulnerável. Sugere reavaliar prioridades e recuperar a confiança.",
        guidanceWhenReversed = "Indica sentir-se sobrecarregado, desistência ou vulnerabilidade extrema.",
        keywordsLight = listOf("Perseverança", "Coragem", "Defesa", "Resiliência"),
        keywordsShadow = listOf("Sobrecarga", "Derrota", "Vulnerabilidade"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.MARS,
            zodiac = ZodiacSign.SAGITTARIUS,
            numerology = 7
        ),
        history = "Retrata uma figura defendendo sua posição contra oponentes, simbolizando a resiliência diante da oposição.",
        imageResId = R.drawable.wands_07
    ),
    TarotCard(
        id = "wands_08",
        name = "Oito de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 8,
        essence = "Representa movimento rápido, progresso e o desenrolar veloz dos acontecimentos.",
        lightMeaning = "Em pé, o Oito de Paus significa velocidade, progresso e comunicação clara. Incentiva a abraçar oportunidades e agir decisivamente.",
        shadowMeaning = "Alerta para atrasos, má comunicação ou energia dispersa. Sugere desacelerar e recuperar o foco.",
        guidanceWhenReversed = "Alerta para atrasos frustrantes, comunicação confusa ou pressa excessiva.",
        keywordsLight = listOf("Velocidade", "Progresso", "Ímpeto", "Clareza"),
        keywordsShadow = listOf("Atrasos", "Pressa", "Confusão"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.MERCURY,
            zodiac = ZodiacSign.SAGITTARIUS,
            numerology = 8
        ),
        history = "Associado à ação rápida e progresso decisivo, simbolizando o ímpeto e a resolução de esperas.",
        imageResId = R.drawable.wands_08
    ),
    TarotCard(
        id = "wands_09",
        name = "Nove de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 9,
        essence = "Significa resiliência, persistência e a coragem para superar os últimos desafios.",
        lightMeaning = "Em pé, o Nove de Paus representa força, resistência e proteção do progresso feito. Incentiva a manter-se forte diante das adversidades.",
        shadowMeaning = "Pode indicar exaustão, esgotamento ou relutância em continuar. Sugere buscar apoio e restaurar o equilíbrio.",
        guidanceWhenReversed = "Indica exaustão, burnout ou recusa em continuar lutando.",
        keywordsLight = listOf("Resiliência", "Persistência", "Coragem", "Resistência"),
        keywordsShadow = listOf("Exaustão", "Burnout", "Resistência"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.MOON,
            zodiac = ZodiacSign.SAGITTARIUS,
            numerology = 9
        ),
        history = "Representa a determinação de perseverar apesar dos ferimentos, simbolizando o último esforço antes do sucesso.",
        imageResId = R.drawable.wands_09
    ),
    TarotCard(
        id = "wands_10",
        name = "Dez de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 10,
        essence = "Representa fardos, responsabilidade excessiva e o peso das obrigações.",
        lightMeaning = "Em pé, o Dez de Paus significa trabalho árduo, determinação e a conclusão de tarefas pesadas. Incentiva a reconhecer seus limites.",
        shadowMeaning = "Alerta para sobrecarga, fardos excessivos ou negligência com o autocuidado. Sugere aliviar a carga e priorizar.",
        guidanceWhenReversed = "Sugere colapso sob pressão, necessidade de delegar ou libertação de pesos.",
        keywordsLight = listOf("Responsabilidade", "Fardos", "Trabalho Árduo", "Conclusão"),
        keywordsShadow = listOf("Sobrecarga", "Negligência", "Estresse"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.SATURN,
            zodiac = ZodiacSign.LEO,
            numerology = 10
        ),
        history = "Retrata uma figura carregando paus pesados, simbolizando o desafio de carregar responsabilidades sozinho e a necessidade de delegar.",
        imageResId = R.drawable.wands_10
    ),
    TarotCard(
        id = "wands_page",
        name = "Valete de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 11,
        essence = "Representa entusiasmo juvenil, curiosidade e a centelha inicial de inspiração.",
        lightMeaning = "Em pé, significa inspiração, potencial e uma perspectiva fresca. Incentiva dar os primeiros passos em direção a um novo projeto.",
        shadowMeaning = "Alerta para falta de direção, hesitação ou energia desfocada. Sugere refinar ideias e criar um plano claro.",
        guidanceWhenReversed = "Alerta para falta de direção, hesitação ou energia dispersa e imatura.",
        keywordsLight = listOf("Inspiração", "Curiosidade", "Exploração", "Criatividade"),
        keywordsShadow = listOf("Hesitação", "Falta de Direção", "Imaturidade"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.MARS,
            zodiac = ZodiacSign.SAGITTARIUS,
            numerology = 11
        ),
        history = "Simboliza exploração, novas ideias e energia desenfreada, geralmente associada a notícias empolgantes ou novas paixões.",
        imageResId = R.drawable.wands_page
    ),
    TarotCard(
        id = "wands_knight",
        name = "Cavaleiro de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 12,
        essence = "Representa paixão, ação e a busca por objetivos com determinação e rapidez.",
        lightMeaning = "Em pé, o Cavaleiro de Paus significa determinação, ousadia e entusiasmo. Incentiva a perseguir objetivos com confiança.",
        shadowMeaning = "Alerta para impulsividade, temeridade ou falta de foco. Sugere equilibrar a paixão com paciência e planejamento.",
        guidanceWhenReversed = "Alerta para impulsividade, imprudência ou frustração por falta de foco.",
        keywordsLight = listOf("Ação", "Paixão", "Determinação", "Ambição"),
        keywordsShadow = listOf("Impulsividade", "Imprudência", "Frustração"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.MERCURY,
            zodiac = ZodiacSign.ARIES,
            numerology = 12
        ),
        history = "Simboliza ambição, energia e o impulso de realizar sonhos de forma dinâmica e às vezes impetuosa.",
        imageResId = R.drawable.wands_knight
    ),
    TarotCard(
        id = "wands_queen",
        name = "Rainha de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 13,
        essence = "Encarna confiança, independência e o poder do carisma e da determinação.",
        lightMeaning = "Em pé, representa calor humano, determinação e autoconfiança. Incentiva a abraçar sua força interior e inspirar os outros.",
        shadowMeaning = "Sugere insegurança, comportamento controlador ou esgotamento emocional.",
        guidanceWhenReversed = "Sugere insegurança, comportamento controlador ou esgotamento emocional.",
        keywordsLight = listOf("Confiança", "Carisma", "Criatividade", "Independência"),
        keywordsShadow = listOf("Insegurança", "Dominadora", "Autodúvida"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.SUN,
            zodiac = ZodiacSign.LEO,
            numerology = 13
        ),
        history = "Simboliza autoconfiança, criatividade e liderança magnética, frequentemente associada a alguém que ilumina o ambiente.",
        imageResId = R.drawable.wands_queen
    ),
    TarotCard(
        id = "wands_king",
        name = "Rei de Paus",
        arcana = ArcanaType.MINOR,
        suit = Suit.WANDS,
        number = 14,
        essence = "Representa liderança, visão e o domínio total da energia criativa e empreendedora.",
        lightMeaning = "Em pé, o Rei de Paus significa visão, liderança e espírito empreendedor. Incentiva ações ousadas e inspiração mútua.",
        shadowMeaning = "Alerta para arrogância, impulsividade ou falta de direção. Sugere equilibrar a ambição com humildade e paciência.",
        guidanceWhenReversed = "Alerta para arrogância, impulsividade ou liderança autoritária e sem direção.",
        keywordsLight = listOf("Liderança", "Visão", "Autoridade", "Inovação"),
        keywordsShadow = listOf("Arrogância", "Impulsividade", "Excesso de Confiança"),
        correspondences = Correspondences(
            element = Element.FIRE,
            planet = Planet.MARS,
            zodiac = ZodiacSign.ARIES,
            numerology = 14
        ),
        history = "Simboliza autoridade, inovação e a capacidade de inspirar e liderar grandes movimentos ou empresas.",
        imageResId = R.drawable.wands_king
    )
)

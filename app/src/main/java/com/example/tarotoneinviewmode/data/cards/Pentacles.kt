package com.example.tarotoneinviewmode.data.cards

import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.domain.enums.ArcanaType
import com.example.tarotoneinviewmode.domain.enums.Element
import com.example.tarotoneinviewmode.domain.enums.Planet
import com.example.tarotoneinviewmode.domain.enums.Suit
import com.example.tarotoneinviewmode.domain.enums.ZodiacSign
import com.example.tarotoneinviewmode.domain.model.Correspondences
import com.example.tarotoneinviewmode.domain.model.TarotCard

// Nota: IDs mantêm prefixo "pentacles_" para corresponder aos drawables (R.drawable.pentacles_*)
// O naipe era chamado "Ouros" no app anterior — aqui mapeado para Suit.PENTACLES

val pentaclesCards = listOf(
    TarotCard(
        id = "pentacles_01",
        name = "Ás de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 1,
        essence = "O Ás de Ouros representa novas oportunidades financeiras, prosperidade e abundância.",
        lightMeaning = "Em pé, o Ás de Ouros indica um novo começo nos aspectos financeiros ou materiais. Simboliza abundância, novos negócios e a promessa de prosperidade futura.",
        shadowMeaning = "O Ás de Ouros alerta sobre oportunidades perdidas, instabilidade financeira ou ganância. Urge cautela na gestão de seus recursos.",
        guidanceWhenReversed = "Alerta sobre oportunidades perdidas, instabilidade financeira ou ganância.",
        keywordsLight = listOf("Abundância", "Prosperidade", "Novas Oportunidades", "Sucesso"),
        keywordsShadow = listOf("Oportunidades Perdidas", "Instabilidade Financeira", "Ganância"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.SATURN,
            zodiac = ZodiacSign.TAURUS,
            numerology = 1
        ),
        history = "O Ás de Ouros simboliza o início do sucesso material e de novos empreendimentos. Frequentemente associado à riqueza e prosperidade, este arcano incentiva a aproveitar novas oportunidades.",
        imageResId = R.drawable.pentacles_01
    ),
    TarotCard(
        id = "pentacles_02",
        name = "Dois de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 2,
        essence = "O Dois de Ouros destaca equilíbrio, adaptabilidade e a gestão de prioridades.",
        lightMeaning = "Em pé, esta carta sugere encontrar equilíbrio em meio ao caos e priorizar de forma eficaz. Reflete adaptabilidade e multitarefa.",
        shadowMeaning = "O Dois de Ouros alerta sobre estar sobrecarregado, má gestão do tempo ou dificuldade em manter as coisas em ordem.",
        guidanceWhenReversed = "Alerta sobre sobrecarga, má gestão do tempo ou desorganização.",
        keywordsLight = listOf("Equilíbrio", "Adaptabilidade", "Multitarefa", "Flexibilidade"),
        keywordsShadow = listOf("Sobrecarga", "Desorganização", "Má Gestão do Tempo"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.JUPITER,
            zodiac = ZodiacSign.CAPRICORN,
            numerology = 2
        ),
        history = "O Dois de Ouros representa a necessidade de gerenciar responsabilidades e manter o equilíbrio na vida. Tem raízes no simbolismo da harmonia e da mudança.",
        imageResId = R.drawable.pentacles_02
    ),
    TarotCard(
        id = "pentacles_03",
        name = "Três de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 3,
        essence = "O Três de Ouros simboliza trabalho em equipe, colaboração e maestria artesanal.",
        lightMeaning = "Em pé, esta carta destaca o valor do trabalho em equipe e da colaboração para alcançar um objetivo. Incentiva o reconhecimento das contribuições individuais.",
        shadowMeaning = "Alerta para um trabalho em equipe deficiente, falta de colaboração ou sentimento de desvalorização em um esforço de grupo.",
        guidanceWhenReversed = "Indica falta de cooperação, desorganização ou trabalho mal valorizado.",
        keywordsLight = listOf("Trabalho em Equipe", "Maestria", "Colaboração", "Planejamento"),
        keywordsShadow = listOf("Desorganização", "Falta de Colaboração", "Subvalorização"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.MARS,
            zodiac = ZodiacSign.VIRGO,
            numerology = 3
        ),
        history = "Enraizado na tradição das guildas medievais, o Três de Ouros enfatiza a importância da cooperação e do trabalho qualificado.",
        imageResId = R.drawable.pentacles_03
    ),
    TarotCard(
        id = "pentacles_04",
        name = "Quatro de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 4,
        essence = "O Quatro de Ouros representa estabilidade, segurança e, às vezes, materialismo ou controle.",
        lightMeaning = "Em pé, o Quatro de Ouros sugere segurança financeira e estabilidade. No entanto, também pode alertar contra o apego excessivo aos recursos ou o materialismo exagerado.",
        shadowMeaning = "Esta carta alerta para insegurança financeira, má gestão ou a necessidade de abrir mão do controle e se abrir para a generosidade.",
        guidanceWhenReversed = "Sugere insegurança financeira, avareza excessiva ou necessidade de desapego.",
        keywordsLight = listOf("Estabilidade", "Controle", "Segurança", "Conservação"),
        keywordsShadow = listOf("Insegurança", "Ganância", "Má Gestão"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.SUN,
            zodiac = ZodiacSign.CAPRICORN,
            numerology = 4
        ),
        history = "O Quatro de Ouros simboliza um apego firme às posses materiais ou recursos, refletindo tanto estabilidade quanto ganância potencial.",
        imageResId = R.drawable.pentacles_04
    ),
    TarotCard(
        id = "pentacles_05",
        name = "Cinco de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 5,
        essence = "O Cinco de Ouros simboliza dificuldades, privação ou sentimento de exclusão.",
        lightMeaning = "Em pé, o Cinco de Ouros significa dificuldade financeira, isolamento ou perda. Incentiva a busca por ajuda e a descoberta de força na adversidade.",
        shadowMeaning = "Esta carta pode indicar recuperação de dificuldades, esperança renovada ou a descoberta de um caminho para a estabilidade e segurança.",
        guidanceWhenReversed = "Indica recuperação de dificuldades, renovação da esperança e auxílio vindo.",
        keywordsLight = listOf("Dificuldade", "Pobreza", "Perda", "Luta"),
        keywordsShadow = listOf("Recuperação", "Renovação", "Esperança"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.MERCURY,
            zodiac = ZodiacSign.TAURUS,
            numerology = 5
        ),
        history = "Esta carta frequentemente retrata a privação e a necessidade de apoio, enraizada na imaginação medieval sobre caridade e compaixão.",
        imageResId = R.drawable.pentacles_05
    ),
    TarotCard(
        id = "pentacles_06",
        name = "Seis de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 6,
        essence = "O Seis de Ouros representa generosidade, equilíbrio e o ato de dar e receber.",
        lightMeaning = "Em pé, o Seis de Ouros simboliza generosidade, compartilhamento e equilíbrio. Incentiva atos de bondade e trocas equitativas.",
        shadowMeaning = "Esta carta alerta para desigualdade, exploração ou segundas intenções em atos de dar e receber.",
        guidanceWhenReversed = "Alerta para desigualdade, exploração ou segundas intenções em favores.",
        keywordsLight = listOf("Generosidade", "Equilíbrio", "Bondade", "Apoio"),
        keywordsShadow = listOf("Desigualdade", "Exploração", "Egoísmo"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.MOON,
            zodiac = ZodiacSign.VIRGO,
            numerology = 6
        ),
        history = "Esta carta está enraizada em temas de caridade, refletindo a troca de recursos e a importância do equilíbrio entre dar e receber.",
        imageResId = R.drawable.pentacles_06
    ),
    TarotCard(
        id = "pentacles_07",
        name = "Sete de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 7,
        essence = "O Sete de Ouros simboliza paciência, reflexão e investimento a longo prazo.",
        lightMeaning = "Em pé, o Sete de Ouros significa que o trabalho árduo está dando frutos, destacando a paciência e o planejamento a longo prazo.",
        shadowMeaning = "Esta carta alerta para impaciência, falta de resultados ou esforço mal direcionado. Sugere reavaliar sua abordagem.",
        guidanceWhenReversed = "Indica impaciência, falta de resultados ou esforço mal direcionado.",
        keywordsLight = listOf("Paciência", "Esforço", "Planejamento", "Reflexão"),
        keywordsShadow = listOf("Impaciência", "Frustração", "Má Gestão"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.SATURN,
            zodiac = ZodiacSign.TAURUS,
            numerology = 7
        ),
        history = "O Sete de Ouros destaca o valor da persistência e a importância de avaliar o progresso e o esforço realizado.",
        imageResId = R.drawable.pentacles_07
    ),
    TarotCard(
        id = "pentacles_08",
        name = "Oito de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 8,
        essence = "O Oito de Ouros simboliza maestria, dedicação e o aperfeiçoamento de habilidades.",
        lightMeaning = "Em pé, o Oito de Ouros significa dedicação, desenvolvimento de habilidades e foco. Incentiva o trabalho árduo para alcançar a maestria.",
        shadowMeaning = "Esta carta alerta para o esgotamento, falta de foco ou trabalho de baixa qualidade. Sugere revisar sua dedicação.",
        guidanceWhenReversed = "Alerta para esgotamento (burnout), falta de foco ou trabalho medíocre.",
        keywordsLight = listOf("Dedicação", "Maestria", "Foco", "Perseverança"),
        keywordsShadow = listOf("Esgotamento", "Falta de Foco", "Baixa Qualidade"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.SUN,
            zodiac = ZodiacSign.VIRGO,
            numerology = 8
        ),
        history = "Esta carta representa o valor do trabalho focado e as recompensas de aprimorar o próprio ofício, frequentemente mostrada como um artesão trabalhando.",
        imageResId = R.drawable.pentacles_08
    ),
    TarotCard(
        id = "pentacles_09",
        name = "Nove de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 9,
        essence = "O Nove de Ouros representa independência, luxo e autossuficiência.",
        lightMeaning = "Em pé, o Nove de Ouros significa independência financeira, autossuficiência e as recompensas do esforço pessoal.",
        shadowMeaning = "Esta carta alerta para dependência financeira, falta de autodisciplina ou excesso de indulgência.",
        guidanceWhenReversed = "Indica dependência financeira, falta de autodisciplina ou excessos.",
        keywordsLight = listOf("Independência", "Luxo", "Autossuficiência", "Conquista"),
        keywordsShadow = listOf("Dependência", "Falta de Disciplina", "Excesso"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.VENUS,
            zodiac = ZodiacSign.CAPRICORN,
            numerology = 9
        ),
        history = "Enraizado em imagens de riqueza e conforto, este arcano simboliza a autorrealização e o prazer de desfrutar os frutos do próprio trabalho.",
        imageResId = R.drawable.pentacles_09
    ),
    TarotCard(
        id = "pentacles_10",
        name = "Dez de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 10,
        essence = "O Dez de Ouros simboliza riqueza, família e legado.",
        lightMeaning = "Em pé, o Dez de Ouros significa sucesso, segurança e as recompensas duradouras do legado e dos laços familiares.",
        shadowMeaning = "Alerta para instabilidade financeira, conflitos familiares ou planejamento de curto prazo.",
        guidanceWhenReversed = "Indica instabilidade financeira, conflitos familiares ou falta de visão futura.",
        keywordsLight = listOf("Riqueza", "Legado", "Segurança", "Família"),
        keywordsShadow = listOf("Instabilidade", "Conflito", "Mau Planejamento"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.MERCURY,
            zodiac = ZodiacSign.VIRGO,
            numerology = 10
        ),
        history = "Esta carta representa a riqueza geracional, herança e o legado construído através de anos de trabalho e dedicação.",
        imageResId = R.drawable.pentacles_10
    ),
    TarotCard(
        id = "pentacles_page",
        name = "Valete de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 11,
        essence = "O Valete de Ouros representa curiosidade, ambição e potencial de crescimento em assuntos práticos.",
        lightMeaning = "Em pé, o Valete de Ouros significa oportunidades de aprendizado, definição de metas e a manifestação de sonhos em realidade. Incentiva diligência e foco.",
        shadowMeaning = "Esta carta alerta para procrastinação, falta de foco ou oportunidades perdidas. Sugere reavaliar suas prioridades e manter os pés no chão.",
        guidanceWhenReversed = "Alerta para procrastinação, falta de foco ou oportunidades ignoradas.",
        keywordsLight = listOf("Oportunidade", "Aprendizado", "Ambição", "Diligência"),
        keywordsShadow = listOf("Procrastinação", "Oportunidades Perdidas", "Distração"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.SATURN,
            zodiac = ZodiacSign.CAPRICORN,
            numerology = 11
        ),
        history = "Frequentemente retratado como um jovem examinando um pentáculo, esta carta simboliza o início de uma jornada no reino material ou educacional.",
        imageResId = R.drawable.pentacles_page
    ),
    TarotCard(
        id = "pentacles_knight",
        name = "Cavaleiro de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 12,
        essence = "O Cavaleiro de Ouros simboliza trabalho árduo, confiabilidade e progresso constante em direção às metas.",
        lightMeaning = "Em pé, o Cavaleiro de Ouros representa dedicação, perseverança e uma mentalidade prática. Incentiva o foco em metas de longo prazo.",
        shadowMeaning = "Esta carta alerta para estagnação, cautela excessiva ou foco exagerado na rotina. Sugere encontrar um equilíbrio entre estabilidade e flexibilidade.",
        guidanceWhenReversed = "Sugere estagnação, cautela excessiva ou rigidez na rotina.",
        keywordsLight = listOf("Persistência", "Confiabilidade", "Progresso Constante", "Diligência"),
        keywordsShadow = listOf("Estagnação", "Rigidez", "Falta de Progresso"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.MERCURY,
            zodiac = ZodiacSign.TAURUS,
            numerology = 12
        ),
        history = "Esta carta retrata um cavaleiro firme, incorporando a persistência e uma abordagem metódica para alcançar o sucesso.",
        imageResId = R.drawable.pentacles_knight
    ),
    TarotCard(
        id = "pentacles_queen",
        name = "Rainha de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 13,
        essence = "A Rainha de Ouros representa energia nutridora, praticidade e abundância material.",
        lightMeaning = "Em pé, a Rainha de Ouros significa abundância, calor humano e uma atitude realista. Incentiva o equilíbrio entre responsabilidades pessoais e profissionais.",
        shadowMeaning = "Esta carta alerta para negligência, desequilíbrio ou dependência material. Sugere reconectar-se com o autocuidado e priorizar responsabilidades reais.",
        guidanceWhenReversed = "Alerta para negligência, desequilíbrio ou dependência material.",
        keywordsLight = listOf("Nutrição", "Abundância", "Praticidade", "Acolhimento"),
        keywordsShadow = listOf("Negligência", "Desequilíbrio", "Materialismo"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.VENUS,
            zodiac = ZodiacSign.VIRGO,
            numerology = 13
        ),
        history = "Incorpora cuidado, desenvoltura e o equilíbrio entre a nutrição emocional e a praticidade material.",
        imageResId = R.drawable.pentacles_queen
    ),
    TarotCard(
        id = "pentacles_king",
        name = "Rei de Ouros",
        arcana = ArcanaType.MINOR,
        suit = Suit.PENTACLES,
        number = 14,
        essence = "O Rei de Ouros simboliza riqueza, liderança e maestria sobre o sucesso material.",
        lightMeaning = "Em pé, o Rei de Ouros significa sucesso material, disciplina e confiabilidade. Incentiva uma abordagem estratégica e fundamentada em seus empreendimentos.",
        shadowMeaning = "Esta carta alerta para ganância, má liderança ou indulgência excessiva em questões materiais. Sugere refocar em valores e responsabilidades.",
        guidanceWhenReversed = "Alerta para ganância, liderança medíocre ou excesso de materialismo.",
        keywordsLight = listOf("Liderança", "Riqueza", "Estabilidade", "Disciplina"),
        keywordsShadow = listOf("Ganância", "Má Liderança", "Materialismo"),
        correspondences = Correspondences(
            element = Element.EARTH,
            planet = Planet.SATURN,
            zodiac = ZodiacSign.CAPRICORN,
            numerology = 14
        ),
        history = "Representa estabilidade, sucesso financeiro consolidado e uma abordagem responsável à liderança e ao poder.",
        imageResId = R.drawable.pentacles_king
    )
)
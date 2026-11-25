package com.example.tarotoneinviewmode.data.cards

import com.example.tarotoneinviewmode.R
import com.example.tarotoneinviewmode.domain.enums.ArcanaType
import com.example.tarotoneinviewmode.domain.enums.Element
import com.example.tarotoneinviewmode.domain.enums.Planet
import com.example.tarotoneinviewmode.domain.enums.Suit
import com.example.tarotoneinviewmode.domain.enums.ZodiacSign
import com.example.tarotoneinviewmode.domain.model.Correspondences
import com.example.tarotoneinviewmode.domain.model.TarotCard

val cupsCards = listOf(
    TarotCard(
        id = "cups_01", name = "Ás de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 1,
        essence = "O Ás de Copas poderia atuializar o contexto realização emocional e o fluxo de amor e compaixão. É o potencial de renovação emocional e o despertar do coração.",
        lightMeaning = "Novos relacionamentos, amor e realização emocional. Incentiva abraçar seus sentimentos e expressá-los abertamente.",
        shadowMeaning = "Bloqueios emocionais, amor não correspondido ou dificuldade em expressar emoções. Sugere trabalhar a cura emocional e o amor-próprio.",
        guidanceWhenReversed = "O coração está fechado ou apenas protegido? A diferença entre os dois define o que você precisa fazer agora.",
        keywordsLight = listOf("Amor", "Compaixão", "Renovação Emocional", "Relacionamentos", "Criatividade"),
        keywordsShadow = listOf("Emoções Bloqueadas", "Amor Não Correspondido", "Cura Emocional"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.VENUS, zodiac = ZodiacSign.CANCER, numerology = 1),
        history = "Frequentemente retratado como um cálice transbordando, o Ás de Copas simboliza o potencial de renovação emocional e o despertar do coração.",
        imageResId = R.drawable.cups_01
    ),
    TarotCard(
        id = "cups_02", name = "Dois de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 2,
        essence = "O Dois de Copas representa parceria, harmonia e respeito mútuo. É a troca genuína entre dois que se reconhecem.",
        lightMeaning = "Parcerias fortes, uniões românticas e equilíbrio emocional. Incentiva a construção de relacionamentos baseados na confiança e na compreensão mútua.",
        shadowMeaning = "Desequilíbrio, tensão ou falta de harmonia nos relacionamentos. Pode indicar falha na comunicação ou expectativas não ditas.",
        guidanceWhenReversed = "O que você não está dizendo que precisaria ser dito? O silêncio aqui não é proteção — é distância.",
        keywordsLight = listOf("Parceria", "Harmonia", "União", "Amor", "Equilíbrio"),
        keywordsShadow = listOf("Desequilíbrio", "Falha na Comunicação", "Tensão"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.VENUS, zodiac = ZodiacSign.CANCER, numerology = 2),
        history = "Esta carta frequentemente retrata dois indivíduos trocando taças, simbolizando conexão, amor e igualdade nos relacionamentos.",
        imageResId = R.drawable.cups_02
    ),
    TarotCard(
        id = "cups_03", name = "Três de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 3,
        essence = "O Três de Copas simboliza celebração, amizade e alegria compartilhada. É a força que nasce da comunidade genuína.",
        lightMeaning = "Celebração, trabalho em equipe e o apoio de amigos próximos e familiares. Incentiva a valorizar os momentos de união.",
        shadowMeaning = "Excessos, fofocas ou sentimentos de isolamento dentro de um grupo. Pode indicar superficialidade nas conexões.",
        guidanceWhenReversed = "Você está celebrando com pessoas que realmente te conhecem, ou está preenchendo espaço com presença sem profundidade?",
        keywordsLight = listOf("Celebração", "Amizade", "Unidade", "Felicidade"),
        keywordsShadow = listOf("Excessos", "Fofoca", "Solidão"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.MERCURY, zodiac = ZodiacSign.CANCER, numerology = 3),
        history = "Tradicionalmente mostrado com três figuras erguendo suas taças em um brinde, esta carta reflete comunidade, unidade e felicidade compartilhada.",
        imageResId = R.drawable.cups_03
    ),
    TarotCard(
        id = "cups_04", name = "Quatro de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 4,
        essence = "O Quatro de Copas representa introspecção e contemplação. Há uma taça sendo oferecida que você ainda não viu.",
        lightMeaning = "Reavaliar seu estado emocional e as oportunidades ao seu redor. Sugere a necessidade de gratidão e atenção plena.",
        shadowMeaning = "Estagnação emocional, indiferença ou foco excessivo no negativo enquanto oportunidades passam despercebidas.",
        guidanceWhenReversed = "O que você está ignorando porque já decidiu que não vai funcionar antes mesmo de olhar?",
        keywordsLight = listOf("Contemplação", "Introspecção", "Reavaliação", "Oportunidades"),
        keywordsShadow = listOf("Estagnação", "Indiferença", "Oportunidades Perdidas"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.MOON, zodiac = ZodiacSign.CANCER, numerology = 4),
        history = "Muitas vezes retratado como uma figura sentada sob uma árvore, ignorando uma taça sendo oferecida, esta carta simboliza a introspecção e o desafio de encontrar gratidão.",
        imageResId = R.drawable.cups_04
    ),
    TarotCard(
        id = "cups_05", name = "Cinco de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 5,
        essence = "O Cinco de Copas representa perda e decepção. Três taças derramadas. Duas ainda de pé. O que você está olhando?",
        lightMeaning = "Reconhecimento honesto da dor e da perda. Incentiva encontrar forças para seguir em frente e valorizar o que restou.",
        shadowMeaning = "Arrependimento crônico, tristeza ou fracasso que não permite ver o que ainda existe de intacto.",
        guidanceWhenReversed = "Você já está em recuperação ou ainda está contando o que derramou em vez de olhar para o que sobrou?",
        keywordsLight = listOf("Luto", "Honestidade", "Recuperação", "Perspectiva"),
        keywordsShadow = listOf("Perda", "Arrependimento", "Tristeza", "Decepção"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.MARS, zodiac = ZodiacSign.SCORPIO, numerology = 5),
        history = "Esta carta tradicionalmente mostra uma figura lamentando sobre três taças derramadas, enquanto duas permanecem de pé, simbolizando a necessidade de mudar de perspectiva.",
        imageResId = R.drawable.cups_05
    ),
    TarotCard(
        id = "cups_06", name = "Seis de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 6,
        essence = "O Seis de Copas representa nostalgia e as alegrias simples do passado. A memória que ainda aquece.",
        lightMeaning = "Abraçar memórias felizes, reconectar-se com entes queridos ou encontrar alegria em prazeres simples.",
        shadowMeaning = "Ficar preso ao passado, evitar responsabilidades ou romantizar tempos que não voltam. O presente exige presença.",
        guidanceWhenReversed = "A nostalgia é um descanso legítimo ou uma fuga do que você não quer encarar hoje?",
        keywordsLight = listOf("Nostalgia", "Inocência", "Generosidade", "Alegria"),
        keywordsShadow = listOf("Viver no Passado", "Expectativas Irreais", "Evasão"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.SUN, zodiac = ZodiacSign.SCORPIO, numerology = 6),
        history = "Tradicionalmente, esta carta mostra crianças trocando taças cheias de flores, evocando temas de inocência, generosidade e recordação alegre.",
        imageResId = R.drawable.cups_06
    ),
    TarotCard(
        id = "cups_07", name = "Sete de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 7,
        essence = "O Sete de Copas representa escolhas e o desafio de distinguir a realidade da ilusão. Nem tudo que brilha na taça é real.",
        lightMeaning = "Abundância de opções, imaginação e criatividade. Aconselha consideração cuidadosa e foco no que é realmente alcançável.",
        shadowMeaning = "Devaneio excessivo, paralisia por excesso de opções ou ilusões sedutoras que desviam do que é concreto.",
        guidanceWhenReversed = "Das opções que você está considerando agora, quantas você escolheria se não pudesse mais mudar de ideia amanhã?",
        keywordsLight = listOf("Escolhas", "Imaginação", "Criatividade", "Possibilidades"),
        keywordsShadow = listOf("Ilusões", "Devaneio", "Fuga da Realidade"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.NEPTUNE, zodiac = ZodiacSign.SCORPIO, numerology = 7),
        history = "Esta carta frequentemente retrata uma figura observando sete taças cheias de vários símbolos, destacando a complexidade das escolhas e a sedução das ilusões.",
        imageResId = R.drawable.cups_07
    ),
    TarotCard(
        id = "cups_08", name = "Oito de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 8,
        essence = "O Oito de Copas representa deixar para trás o que não serve mais. Não por raiva — por necessidade.",
        lightMeaning = "Desapego consciente, introspecção e a busca por objetivos mais elevados. Incentiva priorizar o crescimento pessoal em vez do conforto.",
        shadowMeaning = "Medo da mudança, estagnação ou dificuldade em deixar uma situação insatisfatória mesmo sabendo que ela não funciona mais.",
        guidanceWhenReversed = "Você está ficando por amor ou por medo de ter que começar de novo?",
        keywordsLight = listOf("Desapego", "Introspecção", "Crescimento Pessoal", "Coragem"),
        keywordsShadow = listOf("Estagnação", "Medo da Mudança", "Resistência"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.SATURN, zodiac = ZodiacSign.PISCES, numerology = 8),
        history = "Esta carta tradicionalmente mostra uma figura se afastando de oito taças, simbolizando a coragem de desapegar do familiar para buscar um significado mais profundo.",
        imageResId = R.drawable.cups_08
    ),
    TarotCard(
        id = "cups_09", name = "Nove de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 9,
        essence = "O Nove de Copas é a carta dos desejos. Contentamento, satisfação e realização emocional — mas conquistados, não dados.",
        lightMeaning = "Felicidade, bem-estar emocional e a realização de desejos pessoais. Incentiva a gratidão pelo que você conquistou.",
        shadowMeaning = "Insatisfação, excessos ou desejos não realizados por falta de ação. A abundância aqui exige que você tenha feito sua parte.",
        guidanceWhenReversed = "O que você quer que ainda não foi buscado de verdade, apenas desejado?",
        keywordsLight = listOf("Contentamento", "Gratidão", "Realização", "Abundância"),
        keywordsShadow = listOf("Insatisfação", "Excessos", "Vazio"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.JUPITER, zodiac = ZodiacSign.PISCES, numerology = 9),
        history = "Conhecida como a 'carta dos desejos', esta carta frequentemente retrata uma figura sentada em frente a nove taças, significando alegria, gratidão e abundância.",
        imageResId = R.drawable.cups_09
    ),
    TarotCard(
        id = "cups_10", name = "Dez de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 10,
        essence = "O Dez de Copas representa harmonia, felicidade familiar e realização emocional duradoura. O arco-íris depois da tempestade.",
        lightMeaning = "Felicidade a longo prazo, harmonia familiar e realização nos relacionamentos. Celebra a conexão emocional e o amor.",
        shadowMeaning = "Relacionamentos tensos, sonhos desfeitos ou insatisfação oculta sob uma aparência de harmonia.",
        guidanceWhenReversed = "A paz que você vê de fora também existe por dentro, ou é uma fachada que você mantém por hábito?",
        keywordsLight = listOf("Harmonia", "Felicidade", "Realização", "Conexão"),
        keywordsShadow = listOf("Tensão", "Insatisfação", "Aparências"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.MARS, zodiac = ZodiacSign.PISCES, numerology = 10),
        history = "Frequentemente retratado como uma família sob um arco-íris de taças, esta carta simboliza felicidade duradoura, paz e contentamento emocional.",
        imageResId = R.drawable.cups_10
    ),
    TarotCard(
        id = "cups_page", name = "Valete de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 11,
        essence = "O Valete de Copas representa abertura emocional e curiosidade. O peixe na taça não foi convocado — apareceu.",
        lightMeaning = "Novas experiências emocionais, empreendimentos criativos e abertura à inspiração. Incentiva a abraçar sua criança interior.",
        shadowMeaning = "Imaturidade emocional, bloqueios criativos ou relutância em expressar sentimentos. Pode indicar expectativas irreais.",
        guidanceWhenReversed = "Você está aberto ao inesperado ou está esperando apenas o que já conhece?",
        keywordsLight = listOf("Imaginação", "Inspiração", "Abertura", "Curiosidade"),
        keywordsShadow = listOf("Imaturidade", "Bloqueios", "Repressão Emocional"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.MOON, zodiac = ZodiacSign.PISCES, numerology = 11),
        history = "Tradicionalmente, o Valete de Copas retrata uma figura jovem segurando uma taça com um peixe, simbolizando imaginação e inspiração inesperada.",
        imageResId = R.drawable.cups_page
    ),
    TarotCard(
        id = "cups_knight", name = "Cavaleiro de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 12,
        essence = "O Cavaleiro de Copas representa romantismo e a busca pelos sonhos com paixão e graça. Ele cavalga suavemente — mas cavalga.",
        lightMeaning = "Buscar suas paixões, seguir seu coração e abraçar a criatividade. Incentiva a ouvir sua intuição.",
        shadowMeaning = "Romantização excessiva, melancolia ou expectativas irreais que deixam o ideal sempre fora de alcance.",
        guidanceWhenReversed = "O que você está romantizando a ponto de não conseguir ver como é de verdade?",
        keywordsLight = listOf("Romance", "Charme", "Criatividade", "Sonhos"),
        keywordsShadow = listOf("Melancolia", "Expectativas Irreais", "Desequilíbrio"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.VENUS, zodiac = ZodiacSign.SCORPIO, numerology = 12),
        history = "O Cavaleiro de Copas frequentemente retrata um cavaleiro segurando uma taça, simbolizando a busca por objetivos emocionais ou criativos com charme e sensibilidade.",
        imageResId = R.drawable.cups_knight
    ),
    TarotCard(
        id = "cups_queen", name = "Rainha de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 13,
        essence = "A Rainha de Copas representa inteligência emocional e sabedoria intuitiva. Ela sente tudo e ainda assim permanece.",
        lightMeaning = "Acolhimento, compreensão e equilíbrio emocional. Incentiva a empatia e a confiança na sua intuição.",
        shadowMeaning = "Sobrecarga emocional, insegurança ou codependência. Pode indicar cuidar dos outros a ponto de se esquecer de si.",
        guidanceWhenReversed = "Você está cuidando por amor genuíno ou porque não sabe existir sem ser necessário para alguém?",
        keywordsLight = listOf("Compaixão", "Intuição", "Empatia", "Acolhimento"),
        keywordsShadow = listOf("Insegurança", "Sobrecarga", "Codependência"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.NEPTUNE, zodiac = ZodiacSign.CANCER, numerology = 13),
        history = "A Rainha de Copas é frequentemente mostrada sentada em um trono à beira da água, simbolizando profundidade emocional e conexão com a intuição.",
        imageResId = R.drawable.cups_queen
    ),
    TarotCard(
        id = "cups_king", name = "Rei de Copas",
        arcana = ArcanaType.MINOR, suit = Suit.CUPS, number = 14,
        essence = "O Rei de Copas representa maestria emocional. Ele sente tudo — e não se afoga em nada disso.",
        lightMeaning = "Equilíbrio emocional, compaixão e liderança sábia. Incentiva a lidar com os desafios com empatia e compostura.",
        shadowMeaning = "Manipulação emocional, volatilidade ou sentimentos reprimidos que eventualmente transbordam de forma destrutiva.",
        guidanceWhenReversed = "O controle que você exerce sobre suas emoções é sabedoria ou é supressão disfarçada de força?",
        keywordsLight = listOf("Equilíbrio", "Diplomacia", "Compaixão", "Liderança"),
        keywordsShadow = listOf("Manipulação", "Volatilidade", "Repressão"),
        correspondences = Correspondences(element = Element.WATER, planet = Planet.PLUTO, zodiac = ZodiacSign.PISCES, numerology = 14),
        history = "O Rei de Copas é retratado como uma figura calma em um trono cercado por água, simbolizando estabilidade emocional e liderança.",
        imageResId = R.drawable.cups_king
    )
)
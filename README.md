# TarotOne (B.Lopes Oráculo)

Aplicativo Android nativo em **Kotlin** e **Jetpack Compose** (`Material 3 - Tema Always Dark`) que integra leituras de **Tarot** e **I Ching** com foco em experiência fluida, privacidade *offline-first* e estabilidade para publicação na Google Play Store.

## Visão Geral

O **TarotOne** reúne consultas de oráculo e registro pessoal em um ambiente noturno calibrado[cite: 7]:
- **Tarot e I Ching Dinâmico:** cálculo de hexagramas mutantes com visualização simultânea de Presente e Futuro (com textos clássicos traduzidos e adaptados de James Legge)[cite: 7].
- **Reflexão Evolutiva:** seção na Home voltada à "Vibração da Semana" ou ao "Insight do Oráculo" diário[cite: 7].
- **Histórico de Notas:** diário local com filtros, favoritos, edição e exclusão[cite: 7].
- **Hub de Leituras ("Tiragens e Consultas"):** portal com mini-esquemas desenhados via `Canvas` (proporções 2:3 de cartas e linhas de hexagramas), selos de contagem e cartões translúcidos (`alpha = 0.75f`)[cite: 7].

## Arquitetura e Stack Tecnológica

- **Linguagem:** Kotlin nativo[cite: 7].
- **Interface:** 100% Jetpack Compose com Material 3 (`Always Dark`), paleta em tom *Parchment* (`onSurface 85%`) e rótulos `LabelGold` e `TealLabel` (`12sp`)[cite: 7].
- **Navegação e Layout:** *Bottom Bar* customizada com efeito *gooey* de transição orgânica e telas de interpretação em formato de pergaminho[cite: 7].
- **Arquitetura:** MVVM + Clean Architecture com injeção de dependência via Dagger Hilt[cite: 7].
- **Persistência Local:** Room Database (`v7`) com suporte a mutações complexas e histórico detalhado[cite: 7].
- **Mídia:** imagens em WebP (*lossless*) com carregamento assíncrono e transições via Coil[cite: 7].
- **Telemetria e Configuração (Firebase):** Analytics (funil de retenção com opção de *opt-out*), Crashlytics e Remote Config[cite: 7].
- **Localização de Textos:** textos centralizados em `strings.xml`, com cabeçalhos de seção limpos (sem artigos "A/O")[cite: 7].

## Privacidade e Dados

- **Offline-first:** tiragens e anotações do diário ficam armazenadas localmente no dispositivo do usuário[cite: 7].
- **Telemetria transparente:** eventos agregados e diagnósticos técnicos seguem aviso de privacidade e podem ser desativados nas Configurações[cite: 7].

## Observação de Repositório

Antes de publicar o código no GitHub, garanta que arquivos sensíveis ou de assinatura/ambiente (como `google-services.json`, *keystores*, senhas ou tokens locais) estejam devidamente ignorados via `.gitignore`.
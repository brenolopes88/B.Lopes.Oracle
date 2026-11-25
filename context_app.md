# Contexto do Projeto: TarotOne (v1.21 - Production Release Candidate)

## 🎯 Objetivo
O **TarotOne (B.Lopes Oráculo)** é um oráculo digital de alta performance que integra as sabedorias do **Tarot** e do **I Ching**. O projeto foca em oferecer uma interface mística e fluida, priorizando a privacidade do usuário (dados offline), acessibilidade visual e estabilidade técnica para publicação na Google Play Store.

---

## 🛠️ Pilares Tecnológicos (Release Stack)
- **Linguagem:** Kotlin Nativo.
- **Interface:** 100% Jetpack Compose (Material 3 - Tema Always Dark).
- **Arquitetura:** MVVM + Clean Architecture (Otimizado com Dagger Hilt).
- **Persistência:** Room Database v7 (Suporte a mutações complexas e histórico detalhado).
- **Cloud & Telemetria (Firebase):**
  - **Analytics:** Telemetria de funil (com consentimento opt-out) para análise de retenção.
  - **Crashlytics:** Monitoramento de crashes em tempo real.
  - **Remote Config:** Controle dinâmico de recursos.
- **Assets:** Imagens em formato WebP (lossless) com carregamento assíncrono e transições suaves via Coil.

---

## 🎨 Design & UX Premium
- **Efeito Gooey 🌊:** Bottom Bar customizada com animação de "círculo líquido" que se funde organicamente entre as abas, com rótulos limpos na seleção.
- **Layout de Pergaminho:** Telas de interpretação unificadas em largura total que nascem da base da tela para foco total no texto.
- **Tipografia & Cores:** Esquema de cores "Always Dark" (Noturno) calibrado, com textos em tom **Parchment** (onSurface 85%), títulos e rótulos de seção refinados (`LabelGold`, `TealLabel` em 12sp).
- **Hub de Leituras ("Tiragens e Consultas"):** Portal centralizado com mini-esquemas desenhados via `Canvas` (proporções 2:3 de cartas e linhas de hexagramas), selos de contagem e cartões com fundo de vidro escuro translúcido (`alpha = 0.75f`).

---

## 📦 Funcionalidades Estratégicas
1. **Reflexão Evolutiva:** Seção inteligente na Home que detecta a "Vibração da Semana" ou oferece um "Insight do Oráculo" diário.
2. **I Ching Dinâmico:** Cálculo de hexagramas mutantes com exibição simultânea de Presente e Futuro (Textos clássicos traduzidos e adaptados de James Legge).
3. **Histórico de Notas:** Diário local completo com filtros, favoritos e capacidade de exclusão/edição.
4. **Strings Centralizadas:** 100% dos textos do app estão no `strings.xml`, com cabeçalhos de seção limpos (sem artigos "A/O").

---

## 🛡️ Compromisso de Privacidade
- **Diário Privado:** Notas e tiragens permanecem 100% locais (Offline-first).
- **Telemetria Transparente:** Coleta de dados limitada a diagnósticos técnicos e eventos anônimos agregados, com aviso de privacidade e opção de desativação nas Configurações.

---

**Sugestão de uso em outras IAs:**
"Utilize este contexto do TarotOne v1.21 para analisar as melhores práticas de design system Material 3 Always Dark, arquitetura offline-first em Room e otimização de apps de oráculo nativos."

# Iterações — ÁgoraHub

Registro das iterações do Projeto Integrador: o que foi planejado, o que foi entregue e o que mudou. O plano de cada iteração é apresentado na entrega da anterior.

| Iteração | Período            | Duração | Situação                      |
|----------|--------------------|---------|-------------------------------|
| 1        | 29/09 a 06/10/2026 | 7 dias  | Concluída, com replanejamento |
| 2        | 06/10 a 13/10/2026 | 7 dias  | Concluída                     |
| 3        | 13/10 a 27/10/2026 | 15 dias | A planejar                    |

## Iteração 1 — 29/09 a 06/10/2026

### Planejado

O planejamento inicial foi feito para o produto completo, com sprints para todo o semestre e uma arquitetura de ambiente de produção. Para esta iteração estavam previstas as histórias E1 (configuração do projeto Android e do Firebase), E2 (modelo de dados e regras de segurança), F01 (autenticação institucional) e F02 (primeiro acesso), nas issues #1 a #12.

### O que aconteceu

Ao começar a issue #12 (validar o README seguindo o passo a passo), a equipe percebeu que:

- a maior parte das tecnologias previstas ainda não tinha sido vista na disciplina, e o passo a passo descrevia um projeto que ainda não existia;
- o plano pedia mais do que a equipe, que ainda está aprendendo programação mobile e gestão de projetos, conseguiria entregar até dezembro;
- o planejamento estava espalhado em muitos documentos de texto, sem uma ferramenta de acompanhamento, o que dificultava saber o que fazer a seguir.

Do plano original, só o README (#8) foi concluído. As demais issues foram encerradas.

### Entregue

- Documento de requisitos refeito com foco no MVP (`requisitos.md`, versão 1.0), com as decisões D01 a D03.
- Novo projeto Android criado do zero (D03), que abre na tela do Mural mostrando o título.
- README novo, descrevendo só o que existe no repositório.

### Mudanças para as próximas iterações

- Escopo reduzido ao MVP (D01); o restante fica registrado como versão completa.
- Deixaram de fazer parte do MVP a arquitetura MVVM com ViewModels, Coroutines/Flow e injeção de dependências (AppContainer), as Cloud Functions, as notificações (Firebase Cloud Messaging), o Firebase Emulator Suite, o modo demonstração e a chave de entregas por iteração. O app passou a ser construído com os conceitos vistos na disciplina, usando do Firebase só o Authentication e o Firestore (RNF12), e o `minSdk` foi de 26 para 24 (D03).
- Documentação em Markdown, junto do código (D02).
- Acompanhamento pelo GitHub (Milestone, issues e Projects) no lugar dos documentos de planejamento.

## Iteração 2 — 06/10 a 13/10/2026

### Objetivo

Mural de Oportunidades funcionando com dados de exemplo, com a barra de navegação e a paleta de cores do app.

### Itens

| Item                                                          | Requisito ou decisão | Responsável | Issue | Situação  |
|---------------------------------------------------------------|----------------------|-------------|-------|-----------|
| Revisão completa dos requisitos (`requisitos.md`, versão 2.0) | D04 a D12            | Equipe      | —     | Concluído |
| Paleta de cores nos temas claro e escuro                      | D09, RNF06           | Equipe      | —     | Concluído |
| Registro das iterações (`iteracoes.md`)                       | D01                  | Equipe      | —     | Concluído |
| Guia de desenvolvimento para a equipe                         | RNF11, RNF13         | Fernanda    | #27   | Concluído |
| Classe `Oportunidade` e dados de exemplo                      | RF10, RF11           | Fernanda    | #28   | Concluído |
| Mural de Oportunidades                                        | RF10, RN11 a RN15    | Fernanda    | #29   | Concluído |
| Barra de navegação com as abas Mural, Projetos e Perfil       | D08                  | Jhully      | #30   | Concluído |
| Detalhes da oportunidade                                      | RF11                 | Samuel      | #31   | Concluído |

O plano previa a revisão dos pull requests por Lucas e Jousiclécia.

### Ordem de trabalho

1. A classe `Oportunidade` vem primeiro: o Mural e os Detalhes dependem dela.
2. A barra de navegação cria a navegação entre as telas, com as abas Mural, Projetos e Perfil. A tela de Detalhes entra nessa navegação depois que a barra estiver na `main`.

### Fora desta iteração

- Leitura das oportunidades no Firestore e cadastro pelo console (RN10): Iteração 3. Nesta iteração, o Mural usa dados de exemplo.
- Abrir o link da oportunidade no navegador (RF12).
- Conteúdo das abas Projetos e Perfil: nesta iteração, elas mostram só o título.

### Resultado

A iteração foi concluída em 13/10/2026, com os oito itens planejados entregues.

**Entregue fora do plano:**

| Item                                                      | Requisito ou decisão | Responsável | Issue |
|-----------------------------------------------------------|----------------------|-------------|-------|
| Ícone do app                                              | D09                  | Fernanda    | —     |
| Ícones do Material: biblioteca e tutorial no guia         | D13                  | Fernanda    | —     |
| Datas com `LocalDate` e desugaring                        | D14                  | Fernanda    | #28   |
| Cores da barra de navegação                               | D09, RNF02           | Fernanda    | #37   |
| Mesmo estilo de título nas telas Mural, Projetos e Perfil | D08                  | Fernanda    | #29   |

**Revisão e validação:**

- Os pull requests foram revisados por Jhully (#32, #33 e #34), Lucas (#36 e #39), Samuel (#35) e Fernanda (#38).
- O app foi executado em celular Android real e conferido com o checklist de acessibilidade do guia: TalkBack, fonte no tamanho máximo, Accessibility Scanner e tema escuro.
- Nenhum item foi apresentado ao cliente. O projeto não tem cliente externo, e a equipe definiu o PO, Samuel, como cliente. A partir da Iteração 3, os itens concluídos são apresentados a ele antes da entrega.
- Ainda não houve teste com uma pessoa usuária de leitor de tela.

**Métricas:**

- Entrega dos itens planejados: 100% (8 de 8).
- Aceite dos itens entregues: 100%.
- Validação pelo PO: 0%.
- Progresso do projeto: 7% (2 de 27 requisitos funcionais, RF10 e RF11, ainda com dados de exemplo).

**Lições aprendidas:**

- Sem estimativa de tempo, quase todo o código ficou para 10 a 13/10.
- A cadeia de dependências (classe, Mural, Detalhes e barra) fez itens esperarem uns pelos outros.
- Issues com critérios de aceite e "Fora desta issue" deixaram a revisão objetiva.
- Não houve reunião síncrona nem validação do PO.
- O registro das iterações ficou desatualizado em relação ao GitHub.
- Uma das revisoras não tem conta no GitHub.
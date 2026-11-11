# Iterações — ÁgoraHub

Registro das iterações do Projeto Integrador: o que foi planejado, o que foi entregue e o que mudou. O plano de cada iteração é apresentado na entrega da anterior.

| Iteração | Período            | Duração | Situação                      |
|----------|--------------------|---------|-------------------------------|
| 1        | 29/09 a 06/10/2026 | 7 dias  | Concluída, com replanejamento |
| 2        | 06/10 a 13/10/2026 | 7 dias  | Concluída                     |
| 3        | 13/10 a 27/10/2026 | 15 dias | Concluída, com itens passados para a Iteração 4 |
| 4        | 27/10 a 10/11/2026 | 15 dias | Concluída, com o RF02 passado para a Iteração 5 |

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

## Iteração 3 — 13/10 a 27/10/2026

### Objetivo

- Concluir o Mural de Oportunidades com as oportunidades reais, vindas do Firebase.
- Permitir que o estudante crie a conta, entre no app e complete o perfil.
- Testar as telas com leitor de tela e validá-las com o PO.

### Itens do backlog

- Mural de Oportunidades concluído: oportunidades reais cadastradas no Firebase, tela de detalhes e link aberto no navegador (RF10 a RF12).
- Tela de abertura e Privacidade e dados (RF06).
- Acesso do estudante: criar conta com e-mail institucional, confirmar o e-mail, entrar, recuperar a senha e sair (RF01 a RF05).
- Perfil do estudante: completar o perfil no primeiro acesso e visualizá-lo na aba Perfil (RF07 e RF08).
- Telas testadas com leitor de tela e entregas validadas pelo PO.

Se sobrar tempo: editar o perfil (RF09) e criar um projeto (RF13).

### Timeline

| Data            | Responsável            | Tarefa                                                         |
|-----------------|------------------------|----------------------------------------------------------------|
| 14/10           | Fernanda               | Recriar a branch `develop` e proteger a `main`                 |
| 14 a 15/10      | Fernanda               | Corrigir a documentação do repositório                         |
| 14 a 16/10      | Samuel                 | Abrir o link da oportunidade no navegador (RF12)               |
| 14 a 17/10      | Jhully                 | Criar o projeto Firebase e conectar o Firestore ao app (RNF12) |
| 14 a 17/10      | Fernanda               | Tela de abertura e tela Privacidade e dados (RF06)             |
| 14 a 26/10      | Lucas                  | Revisar os pull requests                                       |
| 15 a 16/10      | Jousiclécia            | Testar com leitor de tela as telas da Iteração 2               |
| 17 a 19/10      | Samuel                 | Aba Perfil com dados de exemplo (RF08)                         |
| 18 a 22/10      | Jhully                 | Mural lendo as oportunidades do Firestore (RN10)               |
| 18 a 22/10      | Fernanda               | Criar conta e Entrar (RF01 e RF03)                             |
| 20/10           | Equipe                 | Reunião online de acompanhamento                               |
| 20 a 21/10      | Samuel                 | Cadastrar as oportunidades reais no console do Firebase (RN10) |
| 22 a 24/10      | Samuel                 | Recuperar a senha (RF04)                                       |
| 23 a 25/10      | Fernanda               | Confirmação do e-mail institucional (RF02)                     |
| 23 a 26/10      | Jhully                 | Completar o perfil no primeiro acesso (RF07)                   |
| 24 a 25/10      | Samuel                 | Sair da conta (RF05)                                           |
| 26/10           | Samuel                 | Aba Perfil com os dados reais (RF08)                           |
| 26/10           | Jousiclécia            | Testar com leitor de tela as telas novas                       |
| 26/10           | Samuel                 | Validar as entregas como PO                                    |
| 27/10           | Equipe                 | Apresentar o resultado da Iteração 3 e o plano da Iteração 4   |
| Se sobrar tempo | Quem terminar primeiro | Editar o perfil (RF09) e criar um projeto (RF13)               |

### Resultado

A iteração terminou em 27/10/2026. Até essa data, estavam concluídas as issues #40 (correção da documentação), #41 (projeto Firebase), #42 (link no navegador), #43 (abertura e Privacidade e dados), #44 (aba Perfil com dados de exemplo) e #46 (Criar conta).

Ficaram para depois:

- concluídas na Iteração 4, mas ainda no milestone da Iteração 3: #45 (Mural lendo o Firestore, em 31/10), #48 (cadastro das oportunidades reais, em 01/11) e #47 (Entrar, em 02/11);
- passadas para o milestone da Iteração 4: #50 (RF02), #54 (RF04), #52 (RF05), #51 (RF07), #53 (RF08 com o Firestore) e, dos itens para quando sobrasse tempo, #49 (RF09). O RF13 não foi iniciado.

**Revisão e validação:**

- Revisaram os pull requests: Lucas (#57, #59 e #63) e Fernanda (#56, #60 e #61). Samuel aprovou, como PO, o texto da Privacidade e dados (#57). O #59 foi revertido pelo #61 e refeito no #62. O #55, só de documentação, e o #62 não têm revisão registrada.

**Métricas:**

- Progresso do projeto: 22% (6 de 27 requisitos funcionais: RF01, RF06, RF08, RF10, RF11 e RF12, com o RF08, o RF10 e o RF11 ainda com dados de exemplo).

## Iteração 4 — 27/10 a 10/11/2026

### Objetivo

- Aplicar coroutines e Retrofit, como pede a disciplina de Programação para Dispositivos Móveis (D19 e D20): cada dev entrega uma tela que lê uma coleção do Firestore e uma tela que consome uma rota diferente da API fake.
- Concluir as issues da Iteração 3 que passaram para esta iteração.

### Itens

| Item                                               | Requisito ou decisão | Responsável | Issue | Situação     |
|----------------------------------------------------|----------------------|-------------|-------|--------------|
| Coroutines, Retrofit e API fake                    | D19, D20             | Fernanda    | #65   | Concluído    |
| Hub de Projetos com projetos de teste do Firestore | RF14                 | Fernanda    | #66   | Concluído    |
| Tela Equipe ÁgoraHub                               | RF30                 | Fernanda    | #70   | Concluído    |
| Confirmação do e-mail institucional                | RF02                 | Fernanda    | #50   | Passou para a Iteração 5 |
| Mural com coroutines e mensagem de erro            | RN10, D19            | Jhully      | #67   | Concluído    |
| Tela Perguntas frequentes                          | RF28                 | Jhully      | #68   | Concluído    |
| Recuperar a senha                                  | RF04                 | Jhully      | #54   | Concluído    |
| Completar o perfil no primeiro acesso              | RF07                 | Samuel      | #51   | Concluído    |
| Sair da conta                                      | RF05                 | Samuel      | #52   | Concluído    |
| Aba Perfil com os dados do Firestore               | RF08                 | Samuel      | #53   | Concluído    |
| Tela Calendário acadêmico                          | RF29                 | Samuel      | #69   | Concluído    |
| Editar o perfil                                    | RF09                 | Samuel      | #49   | Concluído    |

Também foram concluídas nesta iteração as issues #45, #48 e #47, do milestone da Iteração 3.

### Resultado

**Mudanças durante a iteração:**

- O My JSON Server saiu do ar em 01/11, e a API fake passou a ser lida pelo endereço raw do GitHub (D20, em 03/11).
- A #50 (RF02) não foi concluída e passou para a Iteração 5.

**Revisão e validação:**

- Revisaram os pull requests: Lucas (#71 a #79, #81 e #82), Fernanda (#64, #80 e #83) e Samuel (#82).
- A Jousiclécia não testou nesta iteração, porque a equipe não preparou os testes para ela.
- Não houve validação direta pelo PO. Numa conversa informal, o Samuel disse que está gostando do que o app está se tornando e que está animado com os próximos incrementos.

**Métricas:**

- Entrega dos itens planejados: 92% (11 de 12; o RF02 passou para a Iteração 5).
- Aceite dos itens entregues: 100% (11 de 11, todos com o pull request aprovado na revisão).
- Progresso do projeto: 50% (15 de 30 requisitos funcionais, já com os RF28 a RF30 da versão 3.0 dos requisitos; na contagem anterior, 12 de 27).

**Lições aprendidas:**

- Issues que não dependem de outras pessoas andam mais rápido.
- Mandar um APK a cada issue para a Jousiclécia não é viável para os devs.

### Mudanças para as próximas iterações

- Os testes da Jousiclécia passam a ser por iteração: ao fechar uma iteração, a `develop` vai para a `main`, a Fernanda gera o APK da `main` e envia com um passo a passo, e a Jousiclécia testa o app inteiro durante a iteração seguinte (guia de desenvolvimento, seção 10). O APK da Iteração 4 vai para ela no início da Iteração 5.
- O `requisitos.md` passou para a versão 3.0, coerente com o código e com a seção de decisões mais curta: as decisões que descrevem o produto viraram requisitos, as telas de consulta viraram RF28 a RF30, a estrutura e as regras do Firestore foram para a seção 9, e a seção 8.4 mostra onde está cada decisão antiga. O README e o guia de desenvolvimento foram atualizados junto.
- As issues da Iteração 5 partem da versão 3.0 e das decisões em aberto D23 a D25.

Os pull requests são revisados pelo Lucas ou, se ele não puder, por outro dev. Quem abriu o pull request nunca revisa o próprio. A Jousiclécia testa a acessibilidade instalando o APK no celular (guia de desenvolvimento, seção 10).
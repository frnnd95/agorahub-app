# Iterações — ÁgoraHub

Registro das iterações do Projeto Integrador: o que foi planejado, o que foi entregue e o que mudou. O plano de cada iteração é apresentado na entrega da anterior.

| Iteração | Período            | Duração | Situação                      |
|----------|--------------------|---------|-------------------------------|
| 1        | 29/09 a 06/10/2026 | 7 dias  | Concluída, com replanejamento |
| 2        | 06/10 a 13/10/2026 | 7 dias  | Concluída                     |
| 3        | 13/10 a 27/10/2026 | 15 dias | Concluída, com itens passados para a Iteração 4 |
| 4        | 27/10 a 10/11/2026 | 15 dias | Concluída, com o RF02 passado para a Iteração 5 |
| 5        | 10/11 a 24/11/2026 | 15 dias | Concluída, com itens passados para a Iteração 6 |
| 6        | 24/11 a 15/12/2026 | 20 dias | Em andamento                  |

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

## Iteração 5 — 10/11 a 24/11/2026

### Objetivo

- Implementar os 15 requisitos funcionais que faltam no MVP: criar e gerenciar projetos, solicitações de participação, membros dos projetos e a confirmação do e-mail institucional.
- Deixar a Iteração 6 (24/11 a 15/12) para correções e melhorias antes da apresentação final (RNF14).
- Enviar à Jousiclécia o APK da Iteração 4 e validar as entregas com o PO.

### Itens

| Item                                                                | Requisito ou decisão | Responsável | Issue | Situação                 |
|---------------------------------------------------------------------|----------------------|-------------|-------|--------------------------|
| Regras do Firestore das decisões D23 a D25 e solicitar participação | RF17, D23 a D25      | Fernanda    | #86   | Concluída                |
| Aceitar uma solicitação                                             | RF21                 | Fernanda    | #91   | Concluída                |
| E-mail dos membros nas solicitações Aceitas                         | RF23                 | Fernanda    | #96   | Passou para a Iteração 6 |
| Remover um membro                                                   | RF24                 | Fernanda    | #98   | Passou para a Iteração 6 |
| Confirmação do e-mail institucional                                 | RF02                 | Fernanda    | #50   | Concluída                |
| Detalhes do projeto                                                 | RF16                 | Samuel      | #85   | Concluída                |
| Solicitações recebidas                                              | RF20                 | Samuel      | #89   | Concluída                |
| Recusar uma solicitação                                             | RF22                 | Samuel      | #92   | Concluída                |
| Minhas solicitações                                                 | RF18                 | Samuel      | #94   | Concluída                |
| Encerrar um projeto                                                 | RF26                 | Samuel      | #97   | Concluída                |
| Criar um projeto                                                    | RF13                 | Jhully      | #87   | Concluída                |
| Meus projetos                                                       | RF19                 | Jhully      | #88   | Concluída                |
| Filtrar o Hub de Projetos por interesse                             | RF15                 | Jhully      | #90   | Concluída                |
| Editar o título e a descrição de um projeto                         | RF25                 | Jhully      | #93   | Concluída                |
| Reabrir um projeto fechado                                          | RF27                 | Jhully      | #95   | Passou para a Iteração 6 |
| Barra inferior só nas abas e nos detalhes da oportunidade           | RNF15                | Fernanda    | #102  | Concluída                |
| Interesses em comum também em texto                                 | RF16                 | Samuel      | #103  | Concluída                |
| Nome e outro curso com até 60 caracteres                            | RF07                 | Fernanda    | #104  | Concluída                |
| Espaços, tamanho mínimo e aviso no Novo projeto                     | RF13                 | A definir   | #107  | Passou para a Iteração 6 |

As decisões D23 a D25 foram registradas em 12/11, antes das issues que dependem delas. As regras do Firestore ficam na primeira parte da #86, publicada até 13/11, para que as outras issues possam ser testadas com dados cadastrados pelo console, sem esperar o RF17 inteiro.
Em 18/11, a análise do protótipo levou a ajustes em várias issues: a tela Membros saiu, e o e-mail e o Remover ficam nas solicitações Aceitas (#96 e #98); as ações sem volta pedem confirmação; o requisitos.md passou para a versão 3.3. As issues #102 a #104 e a do RF13 foram criadas nesse dia.

### Timeline

A data final de cada tarefa é a do pull request aberto. O Lucas revisa em até 24 horas.

| Data       | Responsável | Tarefa                                                                                          |
|------------|-------------|-------------------------------------------------------------------------------------------------|
| 12/11      | Fernanda    | Registrar as decisões D23 a D25 e criar as issues da iteração                                   |
| 12 a 13/11 | Fernanda    | Levar a `develop` para a `main` e enviar à Jousiclécia o APK e o roteiro de teste da Iteração 4 |
| 12 a 13/11 | Fernanda    | Regras do Firestore das decisões D23 a D25 (#86, parte 1)                                       |
| 12 a 14/11 | Samuel      | Detalhes do projeto (RF16)                                                                      |
| 12 a 15/11 | Jhully      | Criar um projeto (RF13)                                                                         |
| 12 a 24/11 | Lucas       | Revisar os pull requests                                                                        |
| 13 a 23/11 | Jousiclécia | Testar o APK da Iteração 4 e relatar os problemas ao Lucas                                      |
| 14 a 17/11 | Fernanda    | Solicitar participação (RF17)                                                                   |
| 15 a 16/11 | Jhully      | Meus projetos (RF19)                                                                            |
| 15 a 17/11 | Samuel      | Solicitações recebidas (RF20)                                                                   |
| 17/11      | Jhully      | Filtrar o Hub de Projetos por interesse (RF15)                                                  |
| 17/11      | Equipe      | Reunião online de acompanhamento                                                                |
| 18/11      | Fernanda    | Barra inferior só nas abas e nos detalhes da oportunidade (RNF15)                               |
| 18 a 19/11 | Fernanda    | Aceitar uma solicitação (RF21)                                                                  |
| 18 a 19/11 | Samuel      | Recusar uma solicitação (RF22)                                                                  |
| 18 a 19/11 | Jhully      | Editar o título e a descrição de um projeto (RF25)                                              |
| 20/11      | Fernanda    | E-mail dos membros nas solicitações Aceitas (RF23)                                              |
| 20/11      | Samuel      | Minhas solicitações (RF18)                                                                      |
| 20/11      | Jhully      | Reabrir um projeto fechado (RF27)                                                               |
| 21/11      | Fernanda    | Remover um membro (RF24)                                                                        |
| 21/11      | Samuel      | Encerrar um projeto (RF26)                                                                      |
| 22/11      | Fernanda    | Confirmação do e-mail institucional (RF02)                                                      |
| 23/11      | Samuel      | Validar as entregas como PO                                                                     |
| 24/11      | Equipe      | Apresentar o resultado da Iteração 5 e o plano da Iteração 6                                    |

### Fora desta iteração

- Funcionalidades da versão completa (`requisitos.md`, seção 7).
- Testes da Jousiclécia nas telas novas desta iteração: entram no APK da Iteração 5, testado na Iteração 6.

Os pull requests são revisados pelo Lucas ou, se ele não puder, por outro dev. Quem abriu o pull request nunca revisa o próprio. A Jousiclécia testa a acessibilidade instalando o APK no celular (guia de desenvolvimento, seção 10).

### Resultado

A iteração terminou em 24/11/2026, com 15 dos 19 itens concluídos. O RF23, o RF24, o RF27 e os ajustes do Novo projeto (#107) passaram para a Iteração 6.

**Entregue fora do plano:**

| Item                                                      | Requisito ou decisão | Responsável | Issue ou PR |
|-----------------------------------------------------------|----------------------|-------------|-------------|
| Seta de voltar nas telas sem barra inferior               | Seção 5.3            | Samuel      | #105        |
| Cores do tema completas, sem o lilás padrão do Material 3 | RNF06                | Fernanda    | PR #117     |
| Borda no cartão de Meus projetos e filtro do Hub numa linha | RF19, RF15         | Samuel      | PR #118     |

**Mudanças durante a iteração:**

- Em 23/11, o tema passou a definir todas as cores do Material 3. O Samuel, como PO, aceitou o status Pendente com a cor secundária do tema, sem o amarelo de aviso; o Fechado continua em vermelho e o Encerrado em cinza.
- A #105, planejada para a Iteração 6, foi concluída nesta.

**Revisão e validação:**

- Revisaram os pull requests: Lucas (#84, #99 a #101, #109, #110, #112 e #115 a #117), Fernanda (#111, #113, #114 e #118 a #122) e Samuel (#123). O #108, só de documentação, não tem revisão registrada.
- Como a Jhully estava viajando, a Fernanda fez no #119 o merge da `develop` e os ajustes pedidos na revisão.
- A Jousiclécia testou o APK da Iteração 4 e não encontrou problemas no cadastro, no perfil, no Hub de Projetos, nas telas de consulta, na Privacidade e dados, no modo avião, no erro de login e na recuperação de senha. O relato não cita a fonte no máximo, o Accessibility Scanner nem o tema escuro.
- O Samuel validou as entregas como PO.
- A reunião de 17/11 não aconteceu.

**Métricas:**

- Entrega dos itens planejados: 79% (15 de 19).
- Aceite dos itens entregues: 100% (15 de 15, todos com o pull request aprovado na revisão).
- Validação pelo PO: 100%.
- Progresso do projeto: 90% (27 de 30 requisitos funcionais; faltam o RF23, o RF24 e o RF27).

**Lições aprendidas:**

- A timeline não foi seguida: 13 dos 17 pull requests de código entraram em 23 e 24/11, e as datas previstas iam de 14 a 22/11.
- Seis issues mexeram na tela de detalhes do projeto, e os merges do último dia viraram uma cadeia de conflitos. Tirar a branch do RF26 da branch do RF18 ajudou.
- Sem a reunião de 17/11, o atraso só apareceu no fim da iteração.
- Publicar as regras do Firestore antes (#86, parte 1) permitiu testar as outras issues com dados do console, sem esperar o RF17.
- O teste por iteração funcionou: a Jousiclécia testou o app inteiro. O roteiro precisa pedir cada item do checklist de acessibilidade.
- Dados editados no console passam por cima das regras e podem criar situações que o app não gera, como um projeto com vagas negativas.

### Mudanças para as próximas iterações

- Issues que mexem na mesma tela são feitas uma depois da outra.
- A reunião de acompanhamento é marcada no início da iteração.
- O roteiro de teste da Jousiclécia lista cada item do checklist de acessibilidade (guia de desenvolvimento, seção 3).
- Nos testes com dados do console, os status seguem as regras de negócio (RN24).

## Iteração 6 — 24/11 a 15/12/2026

### Objetivo

- Concluir os três requisitos funcionais que faltam no MVP: RF23, RF24 e RF27.
- Aplicar o padrão MVVM, critério de avaliação do segundo bimestre de Programação para Dispositivos Móveis (D26).
- Documentar os padrões de projeto aplicados, para a avaliação de Processos de Desenvolvimento de Software.
- Corrigir os problemas encontrados nas revisões e nos testes.
- Preparar a apresentação final (15/12) e a entrega final (17/12) (RNF14).

### Itens

| Item                                                    | Requisito ou decisão | Responsável                | Issue   | Situação  |
|---------------------------------------------------------|----------------------|----------------------------|---------|-----------|
| E-mail dos membros nas solicitações Aceitas             | RF23                 | Fernanda                   | #96     | A fazer   |
| Remover um membro                                       | RF24                 | Fernanda                   | #98     | A fazer   |
| Reabrir um projeto fechado                              | RF27                 | Jhully                     | #95     | A fazer   |
| Espaços, tamanho mínimo e aviso no Novo projeto         | RF13                 | Jhully                     | #107    | A fazer   |
| Botão Enviar e-mail nas solicitações Aceitas            | RF23                 | Samuel                     | #106    | A fazer   |
| Singleton na RetrofitHelper                             | Padrões de projeto   | Jhully                     | A criar | A fazer   |
| Padrão MVVM nas telas                                   | D26                  | Fernanda, Jhully e Samuel  | A criar | A fazer   |
| Documento dos padrões de projeto, com diagramas         | D26                  | Tanyse, Samuel e Fernanda  | A criar | A fazer   |
| Correções da tabela abaixo                              | —                    | Ver a tabela               | A criar | A fazer   |
| APK da Iteração 5 e roteiro de teste para a Jousiclécia | RNF01 a RNF07        | Fernanda                   | —       | A fazer   |
| Projetos e oportunidades de demonstração no Firestore   | RN10                 | Fernanda                   | A criar | A fazer   |
| Documentação final                                      | RNF13                | Samuel                     | A criar | A fazer   |

### O que precisa ser corrigido

| Problema                                                                                                     | Onde                                                              | Origem                  | Responsável          |
|--------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------|-------------------------|----------------------|
| Dois toques rápidos em Aceitar mostram a mensagem de falha; o botão deve ficar desligado enquanto grava      | Solicitações recebidas (RF21)                                     | Revisão do #123         | Fernanda             |
| O TalkBack conta o título e os cabeçalhos como itens da lista ("6 itens" com 3 solicitações)                 | Listas com título dentro da lista, como as Solicitações recebidas | Teste do RF21           | Samuel               |
| Se o projeto for encerrado e a releitura falhar, aparece a mensagem de falha do Encerrar                     | Detalhes do projeto (RF26)                                        | Revisão do #120         | Samuel               |
| Mensagens de tamanho mínimo diferentes entre criar e editar o projeto                                        | Novo projeto e Editar projeto (RF13 e RF25)                       | Revisão do #119         | Jhully (#107)        |
| Projetos de teste aparecem no Hub de Projetos                                                                | Firestore                                                         | Teste da Jousiclécia    | Fernanda             |
| README sem as telas novas; guia com o ícone Enviar antigo; linhas tortas na tabela de versões dos requisitos | Documentação                                                      | Revisão da documentação | Fernanda             |
| Problemas que a Jousiclécia encontrar no APK da Iteração 5                                                   | —                                                                 | Teste de acessibilidade | Lucas abre as issues |

### Timeline

A data final de cada tarefa é a do pull request aberto. Cada pull request entra na `develop` assim que é aprovado. O Lucas revisa em até 24 horas.

| Data          | Responsável              | Tarefa                                                                                 |
|---------------|--------------------------|----------------------------------------------------------------------------------------|
| 25/11         | Fernanda                 | Registrar a decisão D26 e criar as issues da iteração                                  |
| 25 a 26/11    | Fernanda                 | Levar a `develop` para a `main` e enviar à Jousiclécia o APK e o roteiro da Iteração 5 |
| 25 a 27/11    | Jhully                   | Reabrir um projeto fechado (RF27)                                                      |
| 25 a 27/11    | Samuel                   | Corrigir a contagem do TalkBack e a mensagem do Encerrar                               |
| 25 a 28/11    | Fernanda                 | E-mail dos membros nas solicitações Aceitas (RF23)                                     |
| 25/11 a 15/12 | Lucas                    | Revisar os pull requests                                                               |
| 26/11 a 08/12 | Jousiclécia              | Testar o APK da Iteração 5 e relatar os problemas ao Lucas                             |
| 28 a 30/11    | Jhully                   | Espaços, tamanho mínimo e aviso no Novo projeto (#107)                                 |
| 28 a 30/11    | Samuel                   | Piloto do MVVM numa tela, com exemplo no guia                                          |
| 29/11 a 02/12 | Fernanda                 | Remover um membro (RF24) e o Aceitar desligado enquanto grava                          |
| 29/11 a 02/12 | Samuel                   | Botão Enviar e-mail (#106), depois do merge do RF23                                    |
| 01/12         | Equipe                   | Reunião online de acompanhamento                                                       |
| 02/12         | Devs                     | Último dia para pull request de funcionalidade                                         |
| 03 a 05/12    | Jhully                   | Singleton na RetrofitHelper                                                            |
| 03 a 08/12    | Fernanda, Jhully e Samuel | MVVM nas telas de cada dev                                                             |
| 08 a 10/12    | Tanyse, Samuel e Fernanda | Documento dos padrões de projeto (`docs/padroes-de-projeto.md`), com diagramas         |
| 09 a 11/12    | Devs                     | Corrigir os problemas do teste da Jousiclécia                                          |
| 09 a 11/12    | Samuel                   | Documentação final                                                                     |
| 11/12         | Samuel                   | Validar as entregas como PO                                                            |
| 11 a 12/12    | Fernanda                 | Apagar os dados de teste do Firestore e cadastrar os de demonstração                   |
| 12/12         | Fernanda                 | Levar a `develop` para a `main` e gerar o APK final                                    |
| 13 a 14/12    | Equipe                   | Preparar e ensaiar a apresentação final                                                |
| 15/12         | Equipe                   | Apresentação final                                                                     |

### Fora desta iteração

- Funcionalidades da versão completa (`requisitos.md`, seção 7).
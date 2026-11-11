# Documento de Requisitos — ÁgoraHub

| Versão | Responsável     | Data       | Alterações                                                                                                                                                                                                                                                           |
|--------|-----------------|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1.0    | Equipe ÁgoraHub | 06/10/2026 | Primeira versão: propósito, usuários, glossário, solução proposta, requisitos do MVP e da versão completa, relação entre funcionalidades e requisitos, e decisões                                                                                                    |
| 2.0    | Equipe ÁgoraHub | 07/10/2026 | Revisão completa: requisitos organizados por funcionalidade, com novos identificadores; vocabulário das telas; decisões D04 a D12                                                                                                                                    |
| 2.1    | Equipe ÁgoraHub | 11/10/2026 | Decisão D13 (ícones das telas)                                                                                                                                                                                                                                       |
| 2.2    | Equipe ÁgoraHub | 11/10/2026 | Decisão D14: datas com `LocalDate` e desugaring                                                                                                                                                                                                                      |
| 2.3    | Jhully          | 20/10/2026 | Decisão D17: regra provisória do Firestore                                                                                                                                                                                                                           |
| 2.4    | Fernanda        | 27/10/2026 | RN01 e RN02 completadas: espaços, letras maiúsculas e e-mail sem nada antes do @; decisão D18 (recursos do cadastro)                                                                                                                                                 |
| 2.5    | Jhully          | 30/10/2026 | Decisão D15: estrutura da coleção `oportunidades` no Firestore                                                                                                                                                                                                       |
| 2.6    | Fernanda        | 30/10/2026 | Decisões D19 (Retrofit e coroutines), D20 (API fake) e D21 (coleção `projetos`); RNF12 complementada; seção 5.3 com as telas da D20                                                                                                                                  |
| 2.7    | Fernanda        | 01/11/2026 | Decisão D17, etapa 2: ler a coleção `oportunidades` exige login (RF03)                                                                                                                                                                                               |
| 2.8    | Samuel          | 03/11/2026 | Decisões D16 (coleção `estudantes`) e D22 (recursos do Completar perfil); D17, etapa 3: regras da coleção `estudantes` (RF07)                                                                                                                                        |
| 2.9    | Fernanda        | 03/11/2026 | Decisão D20: a API fake passa a ser lida pelo endereço raw do GitHub, um arquivo por rota                                                                                                                                                                            |
| 2.10   | Jhully          | 07/11/2026 | Decisão D15: a leitura das oportunidades usa `await()` (D19)                                                                                                                                                                                                         |
| 2.11   | Fernanda        | 08/11/2026 | Decisão D17, etapa 4: regras da coleção `projetos` (RF14)                                                                                                                                                                                                            |
| 2.12   | Fernanda        | 09/11/2026 | Decisão D20: `mergeDescendants` nos itens do Calendário e da Equipe                                                                                                                                                                                                  |
| 3.0    | Fernanda        | 09/11/2026 | Decisões que descrevem o produto viram requisitos (RNF06, RNF10 a RNF13, RNF15 e RNF16) ou saem; telas de consulta como RF28 a RF30; RN04 ampliada; seções 8.3, 8.4 e 9 novas; decisões em aberto D23 a D25; RNF14 com a data da apresentação final; sai a seção 5.4 |


## Sumário

1. [Introdução](#1-introdução)
2. [Propósito do sistema](#2-propósito-do-sistema)
3. [Usuários](#3-usuários)
4. [Glossário](#4-glossário)
5. [Solução proposta](#5-solução-proposta)
6. [Requisitos do MVP](#6-requisitos-do-mvp)
   - [6.1 Requisitos funcionais](#61-requisitos-funcionais)
   - [6.2 Regras de negócio](#62-regras-de-negócio)
   - [6.3 Requisitos não funcionais](#63-requisitos-não-funcionais)
7. [Requisitos da versão completa](#7-requisitos-da-versão-completa)
8. [Decisões](#8-decisões)
9. [Dados do app](#9-dados-do-app)

## 1. Introdução

Este documento apresenta os requisitos do aplicativo **ÁgoraHub**, projeto acadêmico das disciplinas de Projeto Integrador e Programação para Dispositivos Móveis (Sistemas de Informação, IFAL Arapiraca, 2026.2).

Os requisitos estão divididos em duas partes:

- **MVP do semestre:** o que a equipe vai construir até dezembro de 2026, com os conceitos vistos nas aulas e as exceções previstas na RNF11.
- **Versão completa:** o que o app precisaria ter para ser usado de verdade pelos estudantes. Fica registrado para não perder a visão do produto, mas não faz parte da entrega do semestre.

## 2. Propósito do sistema

No curso de Sistemas de Informação do IFAL Arapiraca, editais, bolsas, hackathons e eventos chegam aos estudantes espalhados por e-mails, murais e grupos de WhatsApp, e muitos só ficam sabendo depois do prazo. Formar equipe também é difícil: quem tem uma ideia não sabe quem tem interesse ou habilidade para ajudar, e muitos estudantes evitam abordar colegas que não conhecem. Além disso, boa parte da turma concilia os estudos com trabalho, família ou longos deslocamentos e passa pouco tempo no campus.

Em setembro de 2026, a equipe aplicou um questionário à comunidade acadêmica do curso e recebeu 33 respostas (31 estudantes e 2 professores ou membros da coordenação). Entre os respondentes:

- 10 já deixaram de participar de uma oportunidade por não encontrar colegas para formar equipe, e outros 9 por causa de avisos perdidos (5) ou porque souberam depois do prazo (4);
- 27 apontaram um mural com editais, vagas e desafios como uma das funcionalidades que mais os fariam usar a plataforma, e 20 escolheram a possibilidade de iniciar um projeto com outros estudantes;
- 21 preferem encontrar parceiros por interesse comum em áreas específicas, como desenvolvimento web, dados ou UX/UI;
- 17 conciliam os estudos com trabalho, família ou longos deslocamentos;
- 22 acham que a falta de ferramentas acessíveis atrapalha muito a integração dos alunos, e 10 que atrapalha um pouco; 2 respondentes usam recursos de acessibilidade e 21 conhecem colegas que precisam.

Nas respostas abertas, os participantes disseram que não usariam uma plataforma com excesso de informação ou parecida com os grupos de WhatsApp que já existem.

Como a amostra é pequena, esses números indicam tendências e não representam todo o curso.

O propósito do ÁgoraHub é reunir, num só aplicativo Android, **as oportunidades acadêmicas e a formação de equipes de projeto** entre estudantes do IFAL, organizadas por áreas de interesse, com o estudante no controle de quando o seu contato é compartilhado.

A pesquisa também mostrou que 17 respondentes têm dificuldade para tirar dúvidas sobre as disciplinas, por não saberem quem domina o assunto (9) ou por receio de perguntar em grupos grandes (8). Esse problema é real, mas fica fora do foco do MVP e está registrado na versão completa (VC17).

## 3. Usuários

### 3.1 Tipos de usuário

| Tipo de usuário | Descrição                                                                                                                                                                         | No MVP?               |
|-----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------|
| Estudante       | Aluno do IFAL com e-mail institucional (`@aluno.ifal.edu.br`). Consulta o Mural de Oportunidades, cria projetos no Hub de Projetos e pede para participar de projetos de colegas. | Sim                   |
| Equipe ÁgoraHub | Integrantes da equipe que mantêm o Mural de Oportunidades atualizado. No MVP, cadastram as oportunidades pelo console do Firebase e mantêm os dados da API fake (D20), fora do aplicativo.                            | Sim, fora do app      |
| Administrador   | Publica e remove oportunidades pelo próprio aplicativo.                                                                                                                           | Não (versão completa) |
| Professor       | Divulga desafios e oportunidades e acompanha a participação dos estudantes.                                                                                                       | Não (versão completa) |

### 3.2 Papéis do estudante

O mesmo estudante pode assumir dois papéis, dependendo da situação:

| Papel                    | Quando                                                    | O que pode fazer                                                                                                                                           |
|--------------------------|-----------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Responsável pelo projeto | Quando cria um projeto                                    | Ver as solicitações recebidas, aceitar ou recusar, ver o e-mail dos membros, editar o título e a descrição, remover membros, reabrir e encerrar o projeto. |
| Solicitante              | Quando pede para participar do projeto de outro estudante | Enviar a solicitação e acompanhar o status.                                                                                                                |

### 3.3 Protopersonas

Pessoas fictícias criadas a partir da pesquisa, usadas para orientar as decisões do produto.

| Protopersona      | Quem é                                                                  | O que espera do ÁgoraHub                                                           | Atendida no MVP?                                                                                   |
|-------------------|-------------------------------------------------------------------------|------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| Marina, 21        | Estuda IA e UX por conta própria, mas não tem com quem montar projetos. | Saber das oportunidades a tempo e encontrar colegas com interesses complementares. | Sim                                                                                                |
| Pedro, 18         | Calouro tímido, não sabe como se aproximar dos grupos.                  | Entrar em projetos sem precisar dar o primeiro passo "no escuro".                  | Sim                                                                                                |
| John, 19          | Mora longe e passa pouco tempo no campus.                               | Se conectar com colegas e projetos sem depender de estar no campus.                | Sim                                                                                                |
| Isabela, 22       | Usa leitor de tela.                                                     | Um app acessível desde o primeiro acesso.                                          | Sim, com leitor de tela; a navegação completa por teclado fica para a versão completa              |
| Camila, 20        | Tem ansiedade social e evita se expor em grupos.                        | Se conectar aos poucos, sem exposição imediata.                                    | Em parte: pede para entrar num projeto de forma discreta; pedir ajuda com dúvidas fica fora do MVP |
| Roberta, 26       | Voltou de licença-maternidade e não conhece a turma nova.               | Encontrar parceiros e colaborar a distância.                                       | Em parte: encontra projetos por interesse; grupos de estudo ficam fora do MVP                      |
| Théo, 23          | Veterano que ajuda colegas pelo WhatsApp e se sente sobrecarregado.     | Um espaço organizado para oferecer ajuda.                                          | Não (versão completa)                                                                              |
| Prof. Ricardo, 41 | Professor que quer engajar os alunos em desafios.                       | Um canal para divulgar desafios e acompanhar a participação.                       | Não (versão completa)                                                                              |

## 4. Glossário

Termos usados neste documento e nas telas do app, em ordem alfabética. As telas usam sempre estes termos, como "interesse" (não "tag") e "responsável" (não "dono"), conforme a RNF05.

| Termo                    | Significado                                                                                                                                                                                  |
|--------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| API fake | Arquivos JSON do repositório público `agorahub-api`, lidos pelas telas de consulta (RF28 a RF30, D20). |
| Data de publicação       | Data em que a Equipe ÁgoraHub cadastrou a oportunidade. Aparece nos detalhes da oportunidade, não no mural.                                                                                  |
| E-mail institucional     | Endereço do domínio `@aluno.ifal.edu.br`. É o único aceito no cadastro de estudantes.                                                                                                        |
| Equipe ÁgoraHub          | Integrantes da equipe do projeto. Cadastram as oportunidades do mural, mantêm a API fake e atendem pedidos de exclusão de conta.                                                                                |
| Hub de Projetos          | Lista dos projetos abertos que ainda têm vagas disponíveis, mostrada na aba Projetos.                                                                                                        |
| Interesse                | Área de habilidade ou interesse, como Mobile, UX/UI ou Banco de Dados, escolhida da lista de interesses. Aparece no perfil do estudante e nos projetos.                                      |
| Interesses procurados    | Interesses que o responsável procura em quem vai participar do projeto.                                                                                                                      |
| Lista de interesses      | Conjunto fixo de interesses definido pela equipe (seção 6.2.7).                                                                                                                              |
| Membro do projeto        | Estudante cuja solicitação para participar de um projeto foi aceita e que não foi removido dele.                                                                                             |
| Meus projetos            | Tela da aba Perfil com os projetos criados pelo estudante.                                                                                                                                   |
| Minhas solicitações      | Tela da aba Perfil com as solicitações enviadas pelo estudante.                                                                                                                              |
| Mural de Oportunidades   | Lista de oportunidades da aba Mural. É a primeira tela depois de entrar com o perfil completo.                                                                                                                             |
| MVP                      | Versão mínima do app, entregue no semestre 2026.2.                                                                                                                                           |
| Oportunidade             | Edital, estágio, competição, evento ou outra oportunidade cadastrada pela Equipe ÁgoraHub, com título, tipo, descrição, prazo, data de publicação, link e, se houver, imagem.                |
| Perfil                   | Nome, curso e de 3 a 5 interesses do estudante.                                                                                                                                              |
| PO                       | Integrante da equipe responsável pelas decisões sobre o produto.                                                                                                                             |
| Prazo                    | Último dia para se inscrever ou participar de uma oportunidade. Em eventos sem inscrição, é a data do evento. A oportunidade fica visível no mural até o fim desse dia.                      |
| Primeiro acesso          | Momento em que o estudante, ao entrar no app pela primeira vez, completa o perfil. Só então pode usar o app.                                                                                 |
| Projeto                  | Proposta criada por um estudante para formar equipe, com título, descrição, número de vagas e interesses procurados.                                                                         |
| Responsável pelo projeto | Estudante que criou um projeto.                                                                                                                                                              |
| Solicitação              | Pedido de um estudante para participar de um projeto.                                                                                                                                        |
| Solicitações recebidas   | Tela da aba Perfil com as solicitações enviadas aos projetos do estudante.                                                                                                                   |
| Solicitante              | Estudante que enviou uma solicitação.                                                                                                                                                        |
| Status da solicitação    | **Pendente** (aguardando resposta do responsável), **Aceita**, **Recusada** (com o motivo) ou **Removida** (o responsável removeu o membro do projeto).                                      |
| Status do projeto        | **Aberto** (recebe solicitações), **Fechado** (todas as vagas foram preenchidas; o responsável pode reabri-lo se uma vaga for liberada) ou **Encerrado** (o responsável encerrou o projeto). |
| Tipo de oportunidade     | Categoria da oportunidade (RN11).                                                                                                                                                            |
| Vagas disponíveis        | Número de vagas do projeto menos o número de membros.                                                                                                                                        |
| Vagas preenchidas        | Número de membros do projeto.                                                                                                                                                                |
| Versão completa          | Funcionalidades que fazem parte da visão do produto, mas ficam fora do MVP.                                                                                                                  |

## 5. Solução proposta

O ÁgoraHub é um aplicativo Android com duas frentes principais:

- **Mural de Oportunidades:** reúne num só lugar editais, estágios, competições e eventos, mostrando apenas os que ainda estão dentro do prazo, dos mais urgentes para os menos urgentes.
- **Hub de Projetos:** quem tem uma ideia cria um projeto com vagas e os interesses que procura; quem quer participar encontra projetos pelas suas áreas de interesse e pede para entrar.

Quando o responsável pelo projeto aceita uma solicitação, passa a ver o e-mail institucional do solicitante, e o contato continua fora do app.

### 5.1 Como o ÁgoraHub responde aos problemas

| Problema identificado                                                 | Como o ÁgoraHub responde                                                                                                                                      |
|-----------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Oportunidades espalhadas em e-mails, murais e grupos; prazos perdidos | Mural de Oportunidades com todas as oportunidades num só lugar, mostrando apenas as que ainda estão dentro do prazo, das mais urgentes para as menos urgentes |
| Dificuldade de encontrar colegas para formar equipe                   | Hub de Projetos com projetos que mostram as vagas e os interesses procurados, com filtro por interesse                                                        |
| Receio de abordar desconhecidos                                       | Pedido de participação com um toque, sem conversa pública; a recusa vem com um motivo pronto, sem exposição                                                   |
| Pouco tempo no campus                                                 | Tudo acontece pelo celular, no tempo de cada estudante                                                                                                        |
| Excesso de informação nos grupos de mensagens                         | Mural só com oportunidades cadastradas pela equipe, sem bate-papo e sem postagens livres                                                                      |
| Ferramentas pouco acessíveis                                          | Telas compatíveis com leitor de tela, com bom contraste e respeitando o tamanho de fonte do celular                                                           |
| Exposição de dados pessoais                                           | O e-mail do solicitante só é mostrado ao responsável depois do aceite, e o solicitante é avisado disso antes de enviar o pedido                               |

### 5.2 Funcionalidades do MVP

1. **Acesso e privacidade:** cadastro com e-mail institucional, confirmação do e-mail, entrada, recuperação de senha, saída e informações sobre o uso dos dados.
2. **Perfil:** nome, curso e de 3 a 5 interesses, que podem ser alterados depois.
3. **Mural de Oportunidades:** lista de oportunidades dentro do prazo, com os detalhes de cada uma.
4. **Hub de Projetos:** criação de projetos, lista com filtro por interesse e detalhes de cada projeto.
5. **Solicitações:** pedido de participação num projeto, com aviso sobre o compartilhamento do e-mail, e acompanhamento das solicitações enviadas.
6. **Gestão dos projetos:** resposta às solicitações recebidas, acesso ao e-mail dos membros, edição do título e da descrição, remoção de membros, encerramento e reabertura.
7. **Consultas:** Perguntas frequentes, Calendário acadêmico e Equipe ÁgoraHub, com dados da API fake.

### 5.3 Navegação

Antes de entrar, o app mostra a tela de abertura, com Entrar, Criar conta e Ver privacidade e dados, que levam ao cadastro, à confirmação do e-mail, à entrada e à recuperação de senha. Depois de entrar, e sempre que o app abre com alguém logado, a tela Início abre o Completar perfil (RF07), se o perfil ainda não existe, ou o Mural. Com o perfil completo, aparece a barra inferior com três abas (RNF15):

| Aba      | O que reúne |
|----------|-------------|
| Mural    | Mural de Oportunidades e os detalhes de cada oportunidade. |
| Projetos | Hub de Projetos, com o botão Novo projeto, e os detalhes de cada projeto. |
| Perfil   | Perfil do estudante e as opções Editar perfil, Calendário acadêmico, Perguntas frequentes, Equipe ÁgoraHub, Privacidade e dados e Sair. Com as solicitações, entram também Solicitações recebidas, Minhas solicitações e Meus projetos. |

A barra aparece nas três abas e nos detalhes da oportunidade; as outras telas abrem sem ela. Depois de entrar, o Voltar no Mural fecha o app.

## 6. Requisitos do MVP

### 6.1 Requisitos funcionais

Os requisitos estão agrupados pelas funcionalidades da seção 5.2. A coluna **Depende de** indica os requisitos que precisam existir antes.

#### 6.1.1 Acesso e privacidade

| Id.  | Descrição                                                                                                | Prioridade | Depende de |
|------|----------------------------------------------------------------------------------------------------------|------------|------------|
| RF01 | Cadastrar uma conta com e-mail institucional, senha e confirmação da senha.                              | Alta       | —          |
| RF02 | Confirmar o e-mail institucional pelo link enviado após o cadastro, com a opção de reenviar o link.      | Média      | RF01       |
| RF03 | Entrar no app com e-mail e senha.                                                                        | Alta       | RF01       |
| RF04 | Recuperar a senha por um link enviado ao e-mail institucional.                                           | Baixa      | RF01       |
| RF05 | Sair da conta.                                                                                           | Média      | RF03       |
| RF06 | Exibir a tela Privacidade e dados, acessível na abertura do app, no cadastro e na aba Perfil.            | Alta       | —          |

#### 6.1.2 Perfil

| Id.  | Descrição                                                                                                | Prioridade | Depende de |
|------|----------------------------------------------------------------------------------------------------------|------------|------------|
| RF07 | Completar o perfil no primeiro acesso, informando nome, curso e de 3 a 5 interesses.                     | Alta       | RF03       |
| RF08 | Visualizar o próprio perfil.                                                                             | Baixa      | RF07       |
| RF09 | Editar o nome, o curso e os interesses do próprio perfil.                                                | Baixa      | RF08       |

#### 6.1.3 Mural de Oportunidades

| Id.  | Descrição                                                                                                                                         | Prioridade | Depende de |
|------|---------------------------------------------------------------------------------------------------------------------------------------------------|------------|------------|
| RF10 | Exibir o Mural de Oportunidades, com título, tipo, prazo e, quando houver, imagem de cada oportunidade.                                           | Alta       | —          |
| RF11 | Exibir os detalhes de uma oportunidade ao tocar nela no mural: título, tipo, descrição, data de publicação, prazo, imagem (quando houver) e link. | Alta       | RF10       |
| RF12 | Abrir o link de uma oportunidade no navegador do celular.                                                                                         | Média      | RF11       |

#### 6.1.4 Hub de Projetos

| Id.  | Descrição                                                                                                            | Prioridade | Depende de |
|------|----------------------------------------------------------------------------------------------------------------------|------------|------------|
| RF13 | Criar um projeto, com título, descrição, número de vagas e interesses procurados.                                    | Alta       | RF07       |
| RF14 | Exibir o Hub de Projetos, com título, vagas disponíveis e interesses procurados de cada projeto.                     | Alta       | RF13       |
| RF15 | Filtrar o Hub de Projetos por um interesse.                                                                          | Média      | RF14       |
| RF16 | Exibir os detalhes de um projeto: título, descrição, vagas disponíveis, interesses procurados e nome do responsável. | Alta       | RF14       |

#### 6.1.5 Solicitações

| Id.  | Descrição                                                                                                                         | Prioridade | Depende de |
|------|-----------------------------------------------------------------------------------------------------------------------------------|------------|------------|
| RF17 | Solicitar participação num projeto, a partir dos detalhes dele, depois de confirmar o aviso de compartilhamento do e-mail.        | Alta       | RF16       |
| RF18 | Exibir ao estudante as solicitações que ele enviou (Minhas solicitações), com o status de cada uma e o motivo, em caso de recusa. | Média      | RF17       |

#### 6.1.6 Gestão dos projetos

| Id.  | Descrição                                                                                                                                                        | Prioridade | Depende de |
|------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|------------|
| RF19 | Exibir ao responsável os projetos que ele criou (Meus projetos), com status, vagas disponíveis e vagas preenchidas.                                              | Alta       | RF13       |
| RF20 | Exibir ao responsável as solicitações recebidas, separadas por status, com as pendentes em primeiro lugar e o nome, o curso e os interesses de cada solicitante. | Alta       | RF17       |
| RF21 | Aceitar uma solicitação pendente.                                                                                                                                | Alta       | RF20       |
| RF22 | Recusar uma solicitação pendente, informando o motivo.                                                                                                           | Alta       | RF20       |
| RF23 | Exibir ao responsável os membros de um projeto dele, com nome, curso, interesses e e-mail institucional de cada um.                                              | Alta       | RF19, RF21 |
| RF24 | Remover um membro de um projeto.                                                                                                                                 | Alta       | RF23       |
| RF25 | Editar o título e a descrição de um projeto.                                                                                                                     | Média      | RF19       |
| RF26 | Encerrar um projeto.                                                                                                                                             | Média      | RF19       |
| RF27 | Reabrir um projeto fechado.                                                                                                                                      | Média      | RF19       |

#### 6.1.7 Consultas

Telas abertas pela lista de opções da aba Perfil, com dados da API fake (D20).

| Id.  | Descrição | Prioridade | Depende de |
|------|-----------|------------|------------|
| RF28 | Exibir as Perguntas frequentes sobre o ÁgoraHub, com cada pergunta e a sua resposta. | Alta | — |
| RF29 | Exibir o Calendário acadêmico do semestre, com a data e o evento de cada item, na ordem da API fake. | Alta | — |
| RF30 | Exibir a Equipe ÁgoraHub, com o nome, o papel e, quando houver, a foto de cada integrante. | Alta | — |

#### 6.1.8 Diagrama de dependências

Cada seta vai do requisito que precisa existir antes para o requisito que depende dele.

```mermaid
flowchart LR
    RF06
    RF28
    RF29
    RF30
    RF01 --> RF02
    RF01 --> RF03 --> RF05
    RF01 --> RF04
    RF03 --> RF07 --> RF08 --> RF09
    RF10 --> RF11 --> RF12
    RF07 --> RF13 --> RF14 --> RF15
    RF14 --> RF16 --> RF17 --> RF18
    RF13 --> RF19
    RF17 --> RF20
    RF20 --> RF21 --> RF23
    RF20 --> RF22
    RF19 --> RF23 --> RF24
    RF19 --> RF25
    RF19 --> RF26
    RF19 --> RF27
```

### 6.2 Regras de negócio

As regras estão agrupadas pelas mesmas funcionalidades dos requisitos funcionais. A coluna **Relacionada a** indica os requisitos funcionais em que a regra se aplica.

#### 6.2.1 Acesso e privacidade

| Id.  | Descrição                                                                                                                                                                                                                                                                            | Prioridade | Relacionada a                |
|------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|------------------------------|
| RN01 | Só e-mails do domínio `@aluno.ifal.edu.br`, com algo antes do @, podem ser cadastrados. Antes da conferência, o app remove os espaços do início e do fim e passa o e-mail para letras minúsculas, porque o e-mail institucional não diferencia maiúsculas de minúsculas.             | Alta       | RF01                         |
| RN02 | A senha tem de 8 a 64 caracteres, com pelo menos uma letra e um número, e não pode conter a parte do e-mail antes do @, sem diferenciar maiúsculas de minúsculas. A senha e a confirmação da senha precisam ser iguais.                                                              | Alta       | RF01                         |
| RN03 | O estudante só entra no app depois de confirmar o e-mail institucional.                                                                                                                                                                                                              | Média      | RF02, RF03                   |
| RN04 | O app não revela se existe uma conta com o e-mail informado: na entrada, e-mail sem conta e senha errada mostram a mesma mensagem; na recuperação de senha, a mensagem é a mesma, exista ou não a conta. | Baixa | RF03, RF04 |
| RN05 | O nome, o curso e os interesses de um estudante são visíveis aos demais estudantes no app. O e-mail institucional só é visível ao próprio estudante e ao responsável por um projeto do qual ele é membro. Uma solicitação só é visível ao solicitante e ao responsável pelo projeto. | Alta       | RF06, RF16, RF18, RF20, RF23 |

#### 6.2.2 Perfil

| Id.  | Descrição                                                                                                                                                                                       | Prioridade | Relacionada a          |
|------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|------------------------|
| RN06 | O estudante só acessa o Mural de Oportunidades, o Hub de Projetos e as solicitações depois de completar o perfil.                                                                               | Alta       | RF07, RF10, RF14, RF17 |
| RN07 | O curso é Sistemas de Informação ou outro curso, informado pelo nome.                                                                                                                           | Média      | RF07, RF09             |
| RN08 | O estudante escolhe seus interesses na lista de interesses (seção 6.2.7) e pode alterar essa escolha depois. A lista é fixa: estudantes não podem incluir, renomear ou excluir interesses dela. | Alta       | RF07, RF09, RF13, RF15 |
| RN09 | O perfil tem de 3 a 5 interesses.                                                                                                                                                               | Alta       | RF07, RF09             |

#### 6.2.3 Mural de Oportunidades

| Id.  | Descrição                                                                                                                                                                                                                                      | Prioridade | Relacionada a |
|------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|---------------|
| RN10 | As oportunidades são cadastradas pela Equipe ÁgoraHub, com título, tipo, descrição, prazo, link e, opcionalmente, imagem. A data de publicação é a data do cadastro. Estudantes não publicam oportunidades.                                    | Média      | RF10, RF11    |
| RN11 | Os tipos de oportunidade são: Edital, Estágio, Competição, Evento e Outro. Bolsas entram como Edital ou Estágio, e Competição inclui hackathons, maratonas de programação e olimpíadas. | Média | RF10, RF11 |
| RN12 | O Mural de Oportunidades só mostra oportunidades dentro do prazo. Cada oportunidade fica visível até as 23h59 do último dia do prazo.                                                                                                          | Média      | RF10          |
| RN13 | O Mural de Oportunidades mostra as oportunidades da que tem o prazo mais próximo para a que tem o prazo mais distante. Com o mesmo prazo, aparece primeiro a publicada mais recentemente.                                                      | Média      | RF10          |
| RN14 | No mural, o prazo aparece como data ("Prazo: 20/10"). Quando faltam 3 dias ou menos, aparece em vermelho, como "Faltam 3 dias", "Faltam 2 dias", "Falta 1 dia" e, no último dia, "Encerra hoje".                                               | Média      | RF10          |
| RN15 | A imagem de uma oportunidade é opcional e indicada por um endereço na internet. Toda imagem tem uma descrição de até 200 caracteres, lida apenas pelo leitor de tela nos detalhes da oportunidade; no mural, o leitor de tela ignora a imagem. | Alta       | RF10, RF11    |

#### 6.2.4 Hub de Projetos

| Id.  | Descrição                                                                                                                                                             | Prioridade | Relacionada a    |
|------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|------------------|
| RN16 | O título do projeto tem até 80 caracteres, e a descrição, até 1000; nenhum dos dois pode ficar vazio. O projeto tem de 1 a 10 vagas e de 1 a 5 interesses procurados. | Alta       | RF13, RF25       |
| RN17 | Todo projeto é criado com status Aberto. Quem cria o projeto é o responsável por ele.                                                                                 | Alta       | RF13             |
| RN18 | Vagas disponíveis = número de vagas do projeto − número de membros.                                                                                                   | Alta       | RF14, RF16, RF19 |
| RN19 | O Hub de Projetos mostra só projetos com status Aberto e com vagas disponíveis, do criado mais recentemente para o mais antigo.                                       | Alta       | RF14             |

#### 6.2.5 Solicitações

| Id.  | Descrição                                                                                                                                                                                                                                                                | Prioridade | Relacionada a |
|------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|---------------|
| RN20 | O responsável não pode solicitar participação no próprio projeto.                                                                                                                                                                                                        | Alta       | RF17          |
| RN21 | O estudante não pode enviar uma solicitação para um projeto se já tiver uma solicitação Pendente para ele, se já for membro dele ou se tiver sido removido dele. Depois de uma recusa, pode enviar uma nova solicitação, desde que o projeto apareça no Hub de Projetos. | Alta       | RF17          |
| RN22 | A solicitação só é enviada depois que o estudante confirma o aviso: "Se sua solicitação for aceita, seu e-mail institucional será compartilhado com o responsável pelo projeto."                                                                                         | Alta       | RF17          |
| RN23 | Solicitações não são apagadas: só mudam de status.                                                                                                                                                                                                                       | Baixa      | RF18, RF20    |

#### 6.2.6 Gestão dos projetos

| Id.  | Descrição                                                                                                                                                                                                      | Prioridade | Relacionada a    |
|------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|------------------|
| RN24 | Uma solicitação Pendente pode ser aceita ou recusada. Uma solicitação Aceita pode passar a Removida. Recusada e Removida são status finais: não mudam mais.                                                    | Alta       | RF21, RF22, RF24 |
| RN25 | Uma solicitação só pode ser aceita se o projeto tiver vagas disponíveis.                                                                                                                                       | Alta       | RF21             |
| RN26 | Quando as vagas disponíveis chegam a zero, o projeto passa para o status Fechado, e as solicitações pendentes dele são recusadas automaticamente com o motivo "Vagas preenchidas".                             | Alta       | RF21             |
| RN27 | Toda recusa feita pelo responsável tem um motivo escolhido da lista: Perfil não compatível, Vagas preenchidas ou Outro. Ao escolher Outro, o responsável precisa escrever o motivo. O solicitante vê o motivo. | Média      | RF18, RF22       |
| RN28 | Ao remover um membro, a solicitação dele passa para Removida e a vaga volta a ficar disponível. O status do projeto não muda: um projeto Fechado continua Fechado até o responsável reabri-lo.                 | Média      | RF24             |
| RN29 | Só projetos com status Aberto ou Fechado podem ser editados.                                                                                                                                                   | Média      | RF25             |
| RN30 | Ao encerrar um projeto, ele passa para o status Encerrado, e as solicitações pendentes dele são recusadas automaticamente com o motivo "Projeto encerrado". Um projeto Encerrado não pode ser reaberto.        | Média      | RF26             |
| RN31 | Só um projeto com status Fechado e com vagas disponíveis pode ser reaberto. Ao ser reaberto, ele volta para o status Aberto e reaparece no Hub de Projetos.                                                    | Média      | RF27             |

#### 6.2.7 Lista de interesses

| Categoria                    | Interesses                                                                                                            |
|------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| Desenvolvimento              | Mobile, Web, Back-end, Jogos                                                                                          |
| Dados e IA                   | Ciência de Dados, Inteligência Artificial, Banco de Dados                                                             |
| Design                       | UX/UI, Design Gráfico                                                                                                 |
| Infraestrutura               | Redes, Cloud/DevOps, Segurança da Informação                                                                          |
| Hardware                     | IoT e Embarcados, Robótica                                                                                            |
| Pesquisa, gestão e sociedade | Pesquisa Acadêmica, Gestão de Projetos, Empreendedorismo, Acessibilidade, Ensino e Monitoria, Programação Competitiva |

As categorias só organizam a lista na tela: o estudante escolhe interesses, não categorias. A lista cobre as áreas citadas na pesquisa e nas protopersonas.

### 6.3 Requisitos não funcionais

A coluna **Como verificar** indica como confirmar que o requisito foi atendido.

| Id.   | Descrição                                                                                                                                                                                                                                                                                                        | Categoria                        | Prioridade | Como verificar                                                                                                                                                                   |
|-------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| RNF01 | Imagens e ícones que informam algo têm uma descrição lida pelo leitor de tela. Imagens e ícones só decorativos são ignorados por ele.                                                                                                                                                                            | Produto / Usabilidade            | Alta       | Navegar pelas telas com o TalkBack ligado: cada imagem e ícone importante é lido com um nome que faz sentido.                                                                    |
| RNF02 | Os textos têm contraste mínimo de 4,5:1 com o fundo (3:1 para textos grandes), conforme o nível AA das diretrizes WCAG.                                                                                                                                                                                          | Produto / Usabilidade            | Alta       | O app Accessibility Scanner não mostra alertas de contraste.                                                                                                                     |
| RNF03 | Os textos respeitam o tamanho de fonte escolhido nas configurações do celular, sem cortar conteúdo.                                                                                                                                                                                                              | Produto / Usabilidade            | Alta       | Com a fonte do celular no tamanho máximo, todos os textos aparecem inteiros.                                                                                                     |
| RNF04 | Botões e outros elementos tocáveis têm área de toque de pelo menos 48 dp.                                                                                                                                                                                                                                        | Produto / Usabilidade            | Média      | O app Accessibility Scanner não mostra alertas de área de toque.                                                                                                                 |
| RNF05 | Os textos usam linguagem simples e os termos do glossário (seção 4). Mensagens de erro aparecem escritas junto ao campo com problema, nunca indicadas só por cor. | Produto / Usabilidade | Média | Revisão das telas: todo erro aparece como texto, e os termos são os do glossário. |
| RNF06 | O app segue o tema claro ou escuro escolhido no celular, com a paleta de cores e o logotipo definidos pelo PO, e não adapta as cores ao papel de parede. | Produto / Usabilidade | Média | Ao trocar o tema do celular, o app passa a usar as cores do tema correspondente. |
| RNF07 | As senhas ficam ocultas durante a digitação, e cada campo de senha tem um botão para mostrá-la ou ocultá-la.                                                                                                                                                                                                     | Produto / Proteção               | Média      | Ao digitar, aparecem pontos no lugar dos caracteres; o botão alterna entre mostrar e ocultar, e o leitor de tela anuncia a ação e o campo, como "Mostrar senha".                 |
| RNF08 | As regras de segurança do Firestore só permitem acesso a usuários logados e garantem a visibilidade definida na RN05. As regras em uso estão na seção 9.4. | Produto / Proteção | Alta | No simulador de regras do console do Firebase, acessos sem login são recusados, e um estudante não consegue ler o e-mail nem as solicitações de outro, exceto nos casos da RN05. |
| RNF09 | O app coleta só nome, e-mail institucional, curso e interesses. A tela Privacidade e dados (RF06) informa para que esses dados são usados, quem vê cada um, onde ficam guardados e como pedir a correção (pelo Editar perfil) ou a exclusão (pelo e-mail da Equipe ÁgoraHub, mostrado na tela), conforme a LGPD. | Externo / Legislativo            | Alta       | Revisão da tela Privacidade e dados.                                                                                                                                             |
| RNF10 | O app é Android nativo, criado com o modelo Empty Activity do Android Studio, em Kotlin com Jetpack Compose. Funciona no Android 7.0 (`minSdk` 24) ou superior e usa o `compileSdk` 37, exigido pelas bibliotecas que o Android Studio adicionou ao projeto. | Organizacional / Desenvolvimento | Alta | Configuração do projeto no arquivo `app/build.gradle.kts`. |
| RNF11 | O desenvolvimento é incremental, em iterações, e o MVP usa os conceitos vistos na disciplina; o restante fica na versão completa (seção 7). Um recurso que não apareceu nas aulas só entra se for indispensável para um requisito do MVP e fica registrado na seção 8. | Organizacional / Desenvolvimento | Alta | Comparação entre o código, o plano de iterações e a seção 8. |
| RNF12 | Os dados do app ficam no Firebase, no plano gratuito Spark, só com o Firebase Authentication (e-mail e senha, que também envia os links do RF02 e do RF04) e o Cloud Firestore. O Room, visto nas aulas, não é usado, porque cada celular teria só os próprios dados. As telas de consulta (RF28 a RF30) leem a API fake (D20). | Organizacional / Desenvolvimento | Alta | No console do Firebase, o projeto está no plano Spark e só o Authentication e o Firestore estão ativados. |
| RNF13 | O código e a documentação ficam no GitHub, com uma branch por tarefa. A documentação fica em arquivos Markdown na pasta `docs`, e o README descreve só o que existe no repositório. | Organizacional / Desenvolvimento | Média | Revisão do repositório. |
| RNF14 | O MVP está funcionando na apresentação final (15/12/2026), com entrega final em 17/12/2026.                                                                                                                                                                                                             | Organizacional / Entrega         | Alta       | Demonstração na apresentação final.                                                                                                                                              |
| RNF15 | A navegação principal é uma barra inferior com as abas Mural, Projetos e Perfil, cada uma com ícone e texto (seção 5.3). | Produto / Usabilidade | Alta | Com o TalkBack, cada aba é lida pelo nome e pelo estado, como "Mural, selecionado". |
| RNF16 | As telas obtêm os dados por meio de uma classe para cada assunto, na pasta `data`, sem ler ou gravar direto no Firebase ou na API. Assim, a origem dos dados muda sem alterar as telas. | Organizacional / Desenvolvimento | Média | Revisão de código: nenhuma tela lê ou grava dados direto no Firebase ou na API. |

## 7. Requisitos da versão completa

Funcionalidades que fazem parte da visão do produto, mas não serão desenvolvidas no semestre. Esta lista não é um compromisso, e a ordem dos itens não indica prioridade. Se a equipe decidir trazer um item para o MVP, ele passa a ser um requisito da seção 6, com um novo identificador.

### 7.1 Acesso, perfil e pessoas

| Id.  | Descrição                                                                                                                                  | Por que fica para depois                                                                     |
|------|--------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------|
| VC01 | Exclusão da própria conta e dos dados pessoais pelo app, conforme a LGPD.                                                                  | Envolve apagar ou anonimizar dados ligados a projetos e solicitações.                        |
| VC02 | Separação de usuários e conteúdo por curso.                                                                                                | No MVP, estudantes de todos os cursos usam o mesmo Mural e o mesmo Hub.                      |
| VC03 | Disponibilidade de horários no perfil do estudante.                                                                                        | Acrescenta campos e telas que não são essenciais ao fluxo principal.                         |
| VC04 | Busca de pessoas: lista de estudantes com filtro por interesse e indicação dos interesses em comum, com opção de ocultar o próprio perfil. | Acrescenta telas e regras de privacidade; no MVP, os estudantes se encontram pelos projetos. |

### 7.2 Mural de Oportunidades

| Id.  | Descrição                                                                                                | Por que fica para depois                                        |
|------|----------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------|
| VC05 | Filtro do mural por tipo de oportunidade.                                                                | Com poucas oportunidades, a ordenação pelo prazo já basta.      |
| VC06 | Publicação de oportunidades por administradores, dentro do app, com envio de imagem a partir do celular. | Exige um novo tipo de usuário, com permissões e telas próprias. |

### 7.3 Projetos e solicitações

| Id.  | Descrição                                                                                                                                 | Por que fica para depois                                                       |
|------|-------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| VC07 | Vínculo entre um projeto e uma oportunidade do mural ("procuro equipe para esta competição").                                             | Liga as duas frentes do app, mas amplia o modelo de dados.                     |
| VC08 | Horário de encontro no projeto e o motivo de recusa "Horários incompatíveis".                                                             | Depende da VC03.                                                               |
| VC09 | Cancelamento de uma solicitação pendente pelo solicitante.                                                                                | Acrescenta um novo status de solicitação.                                      |
| VC10 | Saída de um membro do projeto por conta própria.                                                                                          | Acrescenta um novo status de solicitação.                                      |
| VC11 | Justificativa ao remover um membro.                                                                                                       | Melhoria de comunicação; a remoção já funciona sem ela.                        |
| VC12 | Edição das vagas e dos interesses procurados de um projeto.                                                                               | Exige regras para quando o número de vagas fica menor que o número de membros. |
| VC13 | Justificativa ao encerrar um projeto: não completou as vagas, desistência ou concluído, com link para o site ou repositório do resultado. | Ganha valor junto com o acompanhamento por professores (VC16).                 |
| VC14 | Reabertura de um projeto encerrado.                                                                                                       | Depende de como ficarão os status com a VC13.                                  |

### 7.4 Comunicação

| Id.  | Descrição                                                                                          | Por que fica para depois                        |
|------|----------------------------------------------------------------------------------------------------|-------------------------------------------------|
| VC15 | Notificações no celular: nova solicitação, mudança de status e lembrete de prazo de oportunidades. | Depende de um serviço de envio de notificações. |

### 7.5 Comunidade e professores

| Id.  | Descrição                                                                               | Por que fica para depois                                        |
|------|-----------------------------------------------------------------------------------------|-----------------------------------------------------------------|
| VC16 | Perfil de professor, para divulgar desafios e acompanhar a participação dos estudantes. | Exige um novo tipo de usuário, com permissões e telas próprias. |
| VC17 | Apoio para tirar dúvidas e formar grupos de estudo.                                     | É outro problema da pesquisa, fora do foco do MVP.              |

### 7.6 Acessibilidade

| Id.  | Descrição                                                             | Por que fica para depois                                                                                                 |
|------|-----------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| VC18 | Uso completo do app por teclado físico e pelo Acesso com Interruptor. | Exige testes com equipamentos e configurações específicas; o MVP cobre leitor de tela, contraste, fonte e área de toque. |

## 8. Decisões

Esta seção registra o que a RNF11 pede: as decisões que mudam o rumo do projeto (8.1 e 8.2) e os recursos que não apareceram nas aulas (8.3). Quando uma decisão em aberto é tomada, ela passa para a 8.2 com o mesmo identificador.

### 8.1 Decisões em aberto

| Id. | Questão | Requisitos afetados |
|-----|---------|---------------------|
| D23 | Onde guardar o e-mail que o responsável vê nos membros do projeto. Hoje ele fica só no Authentication (9.1), e a RN05 limita quem pode vê-lo. | RF23 |
| D24 | Como guardar as solicitações e garantir, nas regras do Firestore, que só o solicitante e o responsável as vejam (RN05). | RF17 a RF24 |
| D25 | Como atualizar juntos o projeto e as solicitações, sem as Cloud Functions retiradas na Iteração 1, ao aceitar uma solicitação (RN26), remover um membro (RN28) e encerrar o projeto (RN30). | RF21, RF24, RF26 |

### 8.2 Decisões tomadas

| Id. | Decisão | Motivo | Data |
|-----|---------|--------|------|
| D19 | O app usa coroutines e Retrofit, como nas Aulas 08 e 09. As classes da pasta `data` têm funções `suspend`, que as telas chamam num `LaunchedEffect`, para carregar dados ao abrir, ou num `rememberCoroutineScope`, para gravar ao tocar num botão (Aula 09), sem ViewModel e com `try`/`catch` nas falhas. O Retrofit usa o conversor Moshi, e o Firestore, o `await()` (8.3). O Authentication continua com os ouvintes do `ContaDados`. | A disciplina de Programação para Dispositivos Móveis exige telas que acessam o banco e uma API com coroutines. Muda o registrado na Iteração 1, quando as coroutines saíram do MVP. | 30/10/2026; 09/11/2026 (`rememberCoroutineScope`) |
| D20 | Cada dev consome uma rota diferente de uma API fake: um arquivo JSON por rota no repositório público `agorahub-api`, lido pelo endereço raw do GitHub (`https://raw.githubusercontent.com/frnnd95/agorahub-api/main/`). O app só lê os dados, com Retrofit e coroutines (D19), nas telas de consulta (RF28 a RF30). Os dados são alterados direto na branch `main` do `agorahub-api` (seção 9.5). | A disciplina exige que cada dev consuma uma rota diferente de uma API. A API fake segue o exemplo do professor, sem servidor nem conta nova, e cada dev atualiza a própria rota sem esperar um pull request no app. O My JSON Server (visto em aula), usado até 03/11, saiu do ar em 01/11. | 30/10/2026; 03/11/2026 (endereço raw) |

### 8.3 Recursos que não apareceram nas aulas

Recursos que entraram por serem indispensáveis a um requisito (RNF11). Quem usar um recurso novo acrescenta uma linha aqui e explica no pull request.

| Recurso | Onde é usado | Por que entrou | Registro |
|---------|--------------|----------------|----------|
| `NavigationBar` e `NavigationBarItem` | Barra inferior (RNF15) | É o componente do Material 3 para essa barra | Issue #30 |
| `LocalDate` e o desugaring de bibliotecas do Java, no `app/build.gradle.kts` | Prazo e data de publicação das oportunidades | O `LocalDate` compara datas e conta dias (RN12 a RN14), mas só existe a partir do Android 8; o desugaring mantém o Android 7 (RNF10) | D14 |
| `LocalUriHandler` | Botão Abrir no navegador (RF12) | Abre o link sem biblioteca nova e sem `Intent`, `Uri` e `Context` | PR #56 |
| `FlowRow`, `clearAndSetSemantics` e `clickable(role = Role.Button)` | Aba Perfil (RF08); o `FlowRow` também no cartão do projeto e no seletor de interesses | Os interesses quebram a linha sem sair da tela (RNF03); o TalkBack pula as iniciais, que repetem o nome, e anuncia as opções como botão (RNF01) | PR #60 |
| Parâmetros `placeholder`, `visualTransformation`, `keyboardOptions`, `isError` e `supportingText` do `OutlinedTextField`; `enabled` do `Button`; `imePadding()`; `popUpTo`; e as funções de texto `trim`, `lowercase`, `substringBefore`, `contains`, `any`, `isLetter` e `isDigit` | Criar conta (RF01) e, depois, as outras telas com formulário | São o mínimo para a RN01, a RN02, a RNF05 e a RNF07 | D18 |
| `RadioButton`, `FilterChip`, `selectable`, `selectableGroup` e as semânticas `heading()` e `liveRegion` | Completar e Editar perfil (RF07 e RF09), no `SeletorInteresses`; o `liveRegion` também na recuperação de senha (RF04) | O TalkBack lê se o curso e cada interesse estão marcados (RNF01), com toque de 48 dp (RNF04) | D22 |
| `await()`, da biblioteca `kotlinx-coroutines-play-services` | Classes da pasta `data` que leem e gravam no Firestore | Espera o resultado do Firestore numa coroutine (D19) | D19 |
| `semantics(mergeDescendants = true)` | Itens do Calendário acadêmico e da Equipe ÁgoraHub (RF29 e RF30) | O TalkBack lê cada item de uma vez: a data com o evento; o nome com o papel | D20 |

### 8.4 Correspondência das decisões da versão 2

Decisões que saíram desta seção na versão 3.0. Os números não são reutilizados.

| Decisão | Onde está agora | Decisão | Onde está agora |
|---------|-----------------|---------|-----------------|
| D01 | RNF11 | D11 | Seção 4 e RNF05 |
| D02 | RNF13 | D12 | RNF12 |
| D03 | RNF10 | D13 | Guia, seções 8 e 9 |
| D04 | RNF12 | D14 | Seção 8.3 |
| D05 | RN08 e seção 6.2.7 | D15 | Seção 9.2 |
| D06 | RN11 | D16 | Seção 9.1 |
| D07 | RN02 | D17 | Seção 9.4 e RNF08 |
| D08 | RNF15 | D18 | Seção 8.3 |
| D09 | RNF06 | D21 | Seção 9.3 |
| D10 | RNF16 | D22 | Seção 8.3 |

## 9. Dados do app

Estrutura usada hoje no Firestore (RNF12) e na API fake (D20). Os nomes dos campos são escritos exatamente assim, sem acento.

### 9.1 Coleção `estudantes`

Um documento por estudante, com o mesmo identificador do usuário no Firebase Authentication. O perfil está completo quando o documento existe (RN06). O e-mail fica só no Authentication, porque a RN05 limita quem pode vê-lo (D23).

| Campo | Tipo | Regra |
|-------|------|-------|
| `nome` | texto | — |
| `curso` | texto | RN07 |
| `interesses` | lista de textos | Nomes da seção 6.2.7, de 3 a 5 (RN08 e RN09) |

A lista de interesses fica uma única vez no código, em `InteresseDados`, para o RF07, o RF09 e o RF13.

### 9.2 Coleção `oportunidades`

Um documento por oportunidade, cadastrado pela Equipe ÁgoraHub no console do Firebase (RN10), como explica a seção 11 do guia de desenvolvimento. O identificador do documento é o `id` da classe `Oportunidade`. O app ignora os documentos sem algum campo obrigatório.

| Campo | Tipo | Regra |
|-------|------|-------|
| `titulo` | texto | Obrigatório |
| `tipo` | texto | Obrigatório; um dos tipos da RN11 |
| `descricao` | texto | Obrigatório |
| `prazo` | timestamp | Obrigatório; vira `LocalDate` no app (8.3) |
| `dataPublicacao` | timestamp | Obrigatório; data do cadastro (RN10) |
| `link` | texto | Obrigatório |
| `imagemUrl` | texto | Opcional (RN15) |
| `descricaoImagem` | texto | Opcional para o app; quem cadastra preenche sempre que houver imagem (RN15) |

### 9.3 Coleção `projetos`

Um documento por projeto, com o identificador gerado pelo Firestore. Até o RF13, os projetos são de teste, cadastrados pelo console.

| Campo | Tipo | Regra |
|-------|------|-------|
| `titulo` | texto | RN16 |
| `descricao` | texto | RN16 |
| `vagas` | número | De 1 a 10 (RN16) |
| `interesses` | lista de textos | Nomes da seção 6.2.7, de 1 a 5 (RN16) |
| `status` | texto | Aberto, Fechado ou Encerrado |
| `responsavel` | texto | Identificador do responsável no Authentication (RN17) |
| `membros` | lista de textos | Identificadores dos membros, sem o responsável |
| `criadoEm` | timestamp | Ordem do Hub de Projetos (RN19) |

Vagas disponíveis = `vagas` menos o número de itens de `membros` (RN18). O nome do responsável é lido em `estudantes`, para não ficar em dois lugares.

### 9.4 Regras do Firestore

As regras ficam no console do Firebase e repetem conferências do app, como a da RN09, porque o app pode ser contornado (RNF08). Todas usam a função `logado()`, que confere se há um usuário logado (`request.auth != null`).

| Coleção | Leitura | Criação e alteração | Exclusão |
|---------|---------|---------------------|----------|
| `oportunidades` | Com login | Não pelo app: o cadastro é feito no console, que não passa pelas regras (RN10) | Não pelo app |
| `estudantes` | Com login (RN05) | Só no próprio documento (`request.auth.uid == uid`), só com os campos `nome`, `curso` e `interesses` e com 3 a 5 interesses (RN09) | Não pelo app |
| `projetos` | Com login | Não pelo app, até o RF13 | Não pelo app |

### 9.5 API fake

Arquivos da branch `main` do repositório `agorahub-api` (D20). O app só lê, e os itens aparecem na ordem do arquivo.

| Arquivo | Campos | Tela |
|---------|--------|------|
| `perguntas.json` | `id`, `pergunta` e `resposta` | Perguntas frequentes (RF28) |
| `calendario.json` | `id`, `data` (texto, no formato dd/mm/aaaa) e `evento` | Calendário acadêmico (RF29) |
| `equipe.json` | `id`, `nome`, `papel` e, opcional, `foto` (endereço raw de uma imagem da pasta `fotos`) | Equipe ÁgoraHub (RF30) |

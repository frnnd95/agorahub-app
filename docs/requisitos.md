# Documento de Requisitos — ÁgoraHub

| Versão | Responsável | Data | Alterações |
|--------|---|---|---|
| 1.0    | Equipe ÁgoraHub | 06/10/2026 | Criação do documento (seções 1 e 2) |
| 1.1    | Equipe ÁgoraHub | 06/10/2026 | Seção 3: tipos de usuário, papéis e protopersonas |
| 1.2    | Equipe ÁgoraHub | 06/10/2026 | Seção 4: glossário |


## 1. Introdução

Este documento apresenta os requisitos do aplicativo **ÁgoraHub**, projeto acadêmico das disciplinas de Projeto Integrador e Programação para Dispositivos Móveis (Sistemas de Informação, IFAL Arapiraca, 2026.2).

Os requisitos estão divididos em duas partes:

- **MVP do semestre:** o que a equipe vai construir até dezembro de 2026, com o que aprende nas aulas.
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

A pesquisa também mostrou que 17 respondentes têm dificuldade para tirar dúvidas sobre as disciplinas, por não saberem quem domina o assunto (9) ou por receio de perguntar em grupos grandes (8). Esse problema é real, mas não faz parte do foco do MVP e está registrado como possível evolução.

## 3. Usuários

### 3.1 Tipos de usuário

| Tipo de usuário | Descrição | No MVP? |
|---|---|---|
| Estudante | Aluno do IFAL com e-mail institucional (`@aluno.ifal.edu.br`). Consulta o mural, cria projetos e pede para participar de projetos de colegas. | Sim |
| Equipe ÁgoraHub | Integrantes da equipe que mantêm o Mural de Oportunidades atualizado. No MVP, cadastram as oportunidades fora do aplicativo. | Sim, fora do app |
| Administrador | Publica e remove oportunidades pelo próprio aplicativo. | Não (versão completa) |
| Professor | Divulga desafios e oportunidades e acompanha a participação dos estudantes. | Não (versão completa) |

### 3.2 Papéis do estudante

O mesmo estudante pode assumir dois papéis, dependendo da situação:

| Papel | Quando | O que pode fazer |
|---|---|---|
| Dono do projeto | Quando cria um projeto | Ver as solicitações recebidas, aceitar ou recusar, ver o e-mail dos aceitos e encerrar o projeto. |
| Solicitante | Quando pede para participar do projeto de outro estudante | Enviar a solicitação e acompanhar o status. |

### 3.3 Protopersonas

Pessoas fictícias criadas a partir da pesquisa, usadas para orientar as decisões do produto.

| Protopersona | Quem é | O que espera do ÁgoraHub | Atendida no MVP? |
|---|---|---|---|
| Marina, 21 | Estuda IA e UX por conta própria, mas não tem com quem montar projetos. | Saber das oportunidades a tempo e encontrar colegas com interesses complementares. | Sim |
| Pedro, 18 | Calouro tímido, não sabe como se aproximar dos grupos. | Entrar em projetos sem precisar dar o primeiro passo "no escuro". | Sim |
| John, 19 | Mora longe e passa pouco tempo no campus. | Se conectar com colegas e projetos sem depender de estar no campus. | Sim |
| Isabela, 22 | Usa leitor de tela. | Um app acessível desde o primeiro acesso. | Sim, com leitor de tela; a navegação completa por teclado fica para a versão completa |
| Camila, 20 | Tem ansiedade social e evita se expor em grupos. | Se conectar aos poucos, sem exposição imediata. | Em parte: pede para entrar num projeto de forma discreta; pedir ajuda com dúvidas fica fora do MVP |
| Roberta, 26 | Voltou de licença-maternidade e não conhece a turma nova. | Encontrar parceiros e colaborar a distância. | Em parte: encontra projetos por interesse; grupos de estudo ficam fora do MVP |
| Théo, 23 | Veterano que ajuda colegas pelo WhatsApp e se sente sobrecarregado. | Um espaço organizado para oferecer ajuda. | Não (versão completa) |
| Prof. Ricardo, 41 | Professor que quer engajar os alunos em desafios. | Um canal para divulgar desafios e acompanhar a participação. | Não (versão completa) |

## 4. Glossário

Termos usados neste documento, em ordem alfabética.

| Termo                  | Significado                                                                                                                           |
|------------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| Dono do projeto        | Estudante que criou um projeto.                                                                                                       |
| E-mail institucional   | Endereço do domínio `@aluno.ifal.edu.br`. É o único aceito no cadastro de estudantes.                                                 |
| Hub de Projetos        | Lista dos projetos abertos que ainda têm vagas disponíveis.                                                                           |
| Lista de tags          | Conjunto fixo de tags definido pela equipe. Os estudantes escolhem tags dessa lista, mas não podem criar, editar ou apagar tags.      |
| Mural de Oportunidades | Tela inicial do app, com a lista de oportunidades.                                                                                    |
| MVP                    | Versão mínima do app, entregue no semestre 2026.2.                                                                                    |
| Oportunidade           | Edital, bolsa, hackathon, evento ou similar publicado no mural, com título, tipo, prazo, resumo, imagem e link para mais informações. |
| Perfil                 | Nome, curso e de 3 a 5 tags do estudante. O estudante pode editar o perfil depois, marcando ou desmarcando tags da lista.             |
| Primeiro acesso        | Momento em que o estudante, depois de criar a conta, completa o perfil. Só então pode usar o app.                                     |
| Projeto                | Proposta criada por um estudante para formar equipe, com título, descrição, número de vagas e tags necessárias.                       |
| Solicitação            | Pedido de um estudante para participar de um projeto.                                                                                 |
| Solicitante            | Estudante que enviou uma solicitação.                                                                                                 |
| Status da solicitação  | **Pendente** (aguardando resposta do dono), **Aceita** ou **Recusada** (com o motivo informado pelo dono).                            |
| Status do projeto      | **Aberto** (recebe solicitações), **Fechado** (todas as vagas foram preenchidas) ou **Encerrado** (o dono encerrou o projeto).        |
| Tag                    | Área de habilidade ou interesse, como Mobile, UX/UI ou Banco de Dados. Usada no perfil dos estudantes e nos projetos.                 |
| Vagas disponíveis      | Número de vagas do projeto menos o número de solicitações aceitas.                                                                    |
| Versão completa        | Funcionalidades que fazem parte da visão do produto, mas ficam fora do MVP.                                                           |

## 5. Solução proposta

*Em construção.*

## 6. Requisitos do MVP

*Em construção.*

## 7. Requisitos da versão completa

*Em construção.*

## 8. Principais funcionalidades

*Em construção.*

## 9. Decisões em aberto

*Em construção.*
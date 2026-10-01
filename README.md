# ÁgoraHub

Aplicativo Android que reúne, num só lugar, as **oportunidades acadêmicas** (editais, bolsas, hackathons e eventos) e a **formação de equipes de projeto** entre estudantes do IFAL.

> Projeto Integrador 2026.2 · Sistemas de Informação · IFAL
> Sprint atual: **Sprint 1 · Iteração 1 (29/09 a 06/10/2026)** — base técnica, autenticação institucional e onboarding.
>
> A base do app da Iteração 1 é publicada na `main` durante a Sprint 1 (issue #7). Até lá, o repositório contém apenas a documentação; os comandos abaixo valem a partir dessa publicação.

---

## Sumário

- [Sobre o projeto](#sobre-o-projeto)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Como rodar localmente](#como-rodar-localmente)
- [Testes](#testes)
- [Estrutura de pastas](#estrutura-de-pastas)
- [Fluxo de trabalho: sprints de 7 dias](#fluxo-de-trabalho-sprints-de-7-dias)
- [Convenções de contribuição](#convenções-de-contribuição)
- [Responsabilidades por função](#responsabilidades-por-função)
- [Documentação do projeto](#documentação-do-projeto)

---

## Sobre o projeto

Estudantes perdem prazos de oportunidades espalhadas por grupos e e-mails e têm dificuldade em encontrar colegas para formar equipes. O ÁgoraHub resolve isso com quatro frentes:

| Frente | O que o estudante faz |
|---|---|
| **Acesso e perfil** | Entra com o e-mail institucional (`@aluno.ifal.edu.br`) verificado e informa curso e de 3 a 5 interesses (tags). |
| **Mural de Oportunidades** | Vê editais, bolsas, hackathons e eventos publicados e curados por administradores. |
| **Vitrine de Projetos** | Cadastra projetos com vagas e tags, filtra projetos por interesse e pede para participar. O contato só é liberado depois do aceite. |
| **Notificações** | Recebe push quando alguém pede para entrar no seu projeto ou quando o seu pedido é aceito, recusado ou removido. |

**Escopo do MVP:** histórias habilitadoras E1 e E2 e as features Must Have F01–F04, F06 e F08–F18. F05 (Should) e F07 (Could) entram depois do MVP; F19–F22 estão fora do escopo.
**Meta do semestre:** MVP funcionando no Android e validado com usuários piloto do curso de Sistemas de Informação.

## Tecnologias

| Camada | Tecnologia |
|---|---|
| App | Kotlin · Jetpack Compose (Material 3) · Navigation Compose · Lifecycle ViewModel · Coroutines/Flow · Coil |
| Arquitetura do app | MVVM + Repository, injeção manual de dependências (`AppContainer`) |
| Backend (BaaS) | Firebase Authentication · Cloud Firestore + Security Rules · Cloud Functions (TypeScript, Node 20, `southamerica-east1`) · Firebase Cloud Messaging |
| Testes | JUnit · Compose UI Test · Firebase Emulator Suite · `@firebase/rules-unit-testing` |
| Acessibilidade | TalkBack · Accessibility Scanner · Acesso com Interruptor |
| Build | Gradle (Kotlin DSL) com catálogo de versões em `gradle/libs.versions.toml` |
| Plataforma | Android 8.0+ (minSdk 26) |

As versões exatas de Gradle, AGP, Kotlin, Compose BOM e Firebase BoM ficam no catálogo de versões. Atualizações de versão são uma decisão de arquitetura: atualize tudo junto e registre no pull request.

## Arquitetura

```
┌───────────── App Android ─────────────┐        ┌────────────── Firebase ──────────────┐
│ Telas (Compose)                       │        │ Authentication (e-mail verificado)   │
│   ↓ observam estado                   │        │ Cloud Firestore + Security Rules     │
│ ViewModels (estado e validação)       │ ─────► │ Cloud Functions (vagas, status, push)│
│   ↓ chamam                            │        │ Cloud Messaging (notificações)       │
│ Repositórios (único acesso a dados)   │        └──────────────────────────────────────┘
└───────────────────────────────────────┘
```

- A tela **nunca** acessa o Firebase diretamente: tela → ViewModel → repositório.
- Regras de negócio sensíveis (vagas, status, liberação de e-mail e push) rodam **no servidor**, em transação (ADR-004).
- Só contas `@aluno.ifal.edu.br` com **e-mail verificado** acessam os dados (ADR-005).
- Decisões registradas como ADRs no documento de arquitetura do projeto (ADR-001 a ADR-005).

### Chave de entregas por iteração

O código de todas as features está no repositório, mas `core/Entregas.kt` define `ITERACAO` e **liga só o que já foi entregue**. O que ainda não foi entregue fica oculto ou aparece como "Em construção". A versão aparece no app em **Perfil › Privacidade e dados** (`versionName 0.N-iteracaoN`).

## Como rodar localmente

### Pré-requisitos

- **Android Studio** (versão estável) com o SDK indicado em `app/build.gradle.kts`.
- Aparelho Android 8.0+ com depuração USB **ou** emulador com Google Play (necessário para testar push).
- **Node.js 20** e Firebase CLI (`npm install -g firebase-tools`) — só para o backend, o emulador e o seed.

### 1. Clonar e abrir

```bash
git clone https://github.com/frnnd95/agorahub-app.git
```

Abra a pasta no Android Studio, aguarde o **Gradle Sync** e rode **Build › Make Project**.

### 2. Modo demonstração (sem configurar nada)

Sem o arquivo `app/google-services.json`, o build de depuração roda com um **Firebase simulado em memória**, que segue as mesmas regras do servidor. É o jeito mais rápido de ver o app funcionando e de executar os testes funcionais.

- Contas prontas, todas com a senha `12345678`: `demo@aluno.ifal.edu.br`, `marina@`, `pedro@`, `camila@`, `isabela@` e `theo@` (todas `@aluno.ifal.edu.br`).
- Criar conta também funciona; o botão **"Já verifiquei"** simula o clique no link do e-mail.
- Os dados voltam ao estado inicial quando o app é fechado. Não há push nesse modo.

| Situação | Backend usado pelo build de depuração |
|---|---|
| `app/google-services.json` presente | Firebase de desenvolvimento |
| `USAR_EMULADOR = true` em `app/build.gradle.kts` | Firebase Emulator Suite |
| Nenhum dos dois | Modo demonstração |

O build de release nunca usa o modo demonstração.

### 3. Firebase de desenvolvimento

1. Solicite à função de Backend o `google-services.json` do projeto **agorahub-dev**.
2. Coloque o arquivo em `app/`. **Ele nunca é versionado** (já está no `.gitignore`).
3. Rode o app: login, cadastro e onboarding passam a usar o Firebase real de desenvolvimento.

### 4. Firebase Emulator Suite e carga inicial

```bash
npm --prefix functions install
npm --prefix functions run build
firebase emulators:start --only auth,firestore,functions   # interface em http://localhost:4000
```

Em outro terminal, carregue as 20 tags e os dados de exemplo:

```bash
cd seed && npm install && cd ..
# Linux/macOS
export FIRESTORE_EMULATOR_HOST=127.0.0.1:8080 FIREBASE_AUTH_EMULATOR_HOST=127.0.0.1:9099
# Windows (PowerShell)
# $env:FIRESTORE_EMULATOR_HOST="127.0.0.1:8080"; $env:FIREBASE_AUTH_EMULATOR_HOST="127.0.0.1:9099"
node seed/seed.js --projeto agorahub-dev --exemplos
```

No app, ative `USAR_EMULADOR` no bloco `debug` de `app/build.gradle.kts`. O emulador Android enxerga o computador em `10.0.2.2`; num aparelho físico, use o IP do computador na rede. No emulador do Authentication, o link de verificação de e-mail aparece no terminal e na interface.

## Testes

| O quê | Como rodar |
|---|---|
| Validações do app (e-mail institucional, senha, 3 a 5 tags) | `./gradlew test` |
| Regras de segurança do Firestore | Emulator Suite + `@firebase/rules-unit-testing` |
| Cloud Functions (vagas, status, push) | `npm --prefix functions test` |
| Acessibilidade (RN19) | TalkBack, fonte do sistema em 200%, Accessibility Scanner e navegação por teclado/Switch Access |

Os cenários de teste (CT01–CT32) e a função responsável por cada um estão na planilha **Cenários de Teste** do projeto.

## Estrutura de pastas

```
agorahub-app/
├─ app/                          app Android (pacote br.edu.ifal.agorahub)
│  └─ src/
│     ├─ main/java/br/edu/ifal/agorahub/
│     │  ├─ core/                navegação, design system, injeção (AppContainer), Entregas.kt, utilitários
│     │  ├─ data/                modelos, repositórios, acesso às Functions, modo demonstração
│     │  ├─ feature/             telas por funcionalidade: auth · onboarding · feed · conexoes · perfil
│     │  ├─ notifications/       FCM: canal, token e abertura da tela pelo push
│     │  └─ ui/theme/            cores e tipografia (contraste WCAG AA, claro/escuro)
│     ├─ main/res/               strings.xml (todos os textos do app), temas, ícones
│     └─ test/                   testes de unidade
├─ functions/                    Cloud Functions (TypeScript) e seus testes
├─ seed/                         carga inicial: 20 tags, admin e dados de exemplo
├─ firestore.rules               regras de segurança
├─ firestore.indexes.json        índices compostos
├─ firebase.json · .firebaserc   configuração do Firebase CLI e dos emuladores
└─ gradle/libs.versions.toml     catálogo de versões
```

## Fluxo de trabalho: sprints de 7 dias

O trabalho é organizado em **sprints de 7 dias, de terça a terça**, acompanhadas no quadro **ÁgoraHub — Desenvolvimento** do GitHub Projects. As iterações do cronograma do Projeto Integrador (PJSI) de 15 e 20 dias são compostas por duas ou três sprints, com revisão online nas terças intermediárias.

| Terça | Modalidade | Marco do PJSI |
|---|---|---|
| 29/09 | Presencial | Planejamento geral · início da Iteração 1 (7 dias) |
| 06/10 | Presencial | Resultados da Iteração 1 · apresentação da Iteração 2 (7 dias) |
| 13/10 | Presencial | Resultados da Iteração 2 · apresentação da Iteração 3 (15 dias) |
| 20/10 | Online | Revisão intermediária |
| 27/10 | Presencial | Resultados da Iteração 3 · apresentação da Iteração 4 (15 dias) |
| 03/11 | Online | Revisão intermediária |
| 10/11 | Presencial | Resultados da Iteração 4 · apresentação da Iteração 5 (15 dias) |
| 17/11 | Online | Revisão intermediária |
| 24/11 | Presencial | Resultados da Iteração 5 · apresentação da Iteração 6 (20 dias) |
| 01/12 | Online | Revisão intermediária |
| 08/12 e 15/12 | Presencial | Apresentação final |
| 17/12 | — | Entrega final e pendências |

### Ciclo de cada sprint

1. **Planejamento** — só começa depois do fechamento da sprint anterior, com o registro do que foi **entregue** e do que ficou **pendente**. O Product Owner reprioriza o backlog (pendências incluídas) e a equipe confirma objetivo, tarefas, critérios de aceite e capacidade. O planejamento da sprint seguinte nunca é antecipado.
2. **Execução** — as tarefas da sprint ficam no Project, com campo **Sprint** e status atualizado. Impedimentos são registrados na própria issue.
3. **Fechamento** — revisão (demonstração), retrospectiva e registro de entregue × pendente, que é a entrada do próximo planejamento.

### Quadro (GitHub Projects)

`A fazer` → `Em andamento` → `Em revisão` (pull request aberto) → `Em teste` (QA e acessibilidade) → `Concluído` (aceite do PO)

- Cada história do backlog é uma issue com a label `tipo: história`; as tarefas da sprint são **sub-issues** dela (label `tipo: tarefa`). Tarefas que atendem a várias histórias ficam como `Transversal`.
- Campos do quadro: **Sprint** (iteração de 7 dias), **Status**, **Prioridade** (Must, Should, Could) e **História**.

### Definição de Pronto

Uma história só vai para **Concluído** quando:

- [ ] o código está numa branch da história e foi integrado à `main` por pull request;
- [ ] o code review foi aprovado antes do merge;
- [ ] o build compila sem erros e os testes de unidade e de regras passam;
- [ ] os cenários de teste ligados à história foram executados e aprovados (aceite funcional);
- [ ] as telas foram verificadas com TalkBack, navegação sem toque e checklist de contraste (RN19);
- [ ] o Product Owner deu o aceite final.

## Convenções de contribuição

### Issues e tarefas

- Toda mudança nasce de uma issue ligada a uma história do backlog (`E1`, `E2`, `F01`…), com critérios de aceite.
- As tarefas são descritas por **função** (labels `função: backend`, `função: mobile`, `função: design-ui-ux`, `função: qa`, `função: acessibilidade`…) e **não são atribuídas a pessoas** no planejamento. Quem assume uma tarefa durante a sprint move o cartão para `Em andamento`.

### Branches

- `main` está sempre compilável e representa a versão da iteração.
- Uma branch por história ou tarefa, a partir da `main`:

| Tipo | Padrão | Exemplo |
|---|---|---|
| Funcionalidade | `feature/<ID>-<resumo>` | `feature/F01-autenticacao` |
| Correção | `fix/<ID>-<resumo>` | `fix/F02-contador-tags` |
| Documentação | `docs/<resumo>` | `docs/readme` |
| Infraestrutura e build | `chore/<resumo>` | `chore/emulator-seed` |

### Commits

Mensagens curtas, no imperativo, começando pelo ID da história:

```
F02: valida de 3 a 5 tags no onboarding
E2: nega leitura de solicitação a terceiros
docs: atualiza instruções do emulador
```

### Pull requests

- Pequenos e focados numa história ou tarefa; título com o ID (ex.: `F01: cadastro com e-mail institucional`).
- A descrição informa **o que muda**, **como testar** e **quais cenários de teste (CT)** são afetados, e referencia a issue (`Closes #N`).
- Todo pull request passa por **code review** antes do merge.

Checklist do revisor:

- [ ] Compila e roda em modo demonstração.
- [ ] Camadas respeitadas: nenhuma chamada ao Firebase dentro de tela.
- [ ] Nenhum segredo no diff (`google-services.json`, chaves, tokens).
- [ ] Textos novos em `strings.xml`, em linguagem simples.
- [ ] Semântica de acessibilidade nos componentes novos (rótulos, títulos, estado, erros em texto).
- [ ] Validações com testes de unidade; regra de segurança alterada com teste no emulador.

### Segurança

- `app/google-services.json`, chaves de serviço e tokens **nunca** são versionados.
- O Firestore é protegido pelas regras; o app não é a única barreira.

## Responsabilidades por função

| Função | Responde por |
|---|---|
| Produto (PO) | Visão do produto, prioridade do backlog, critérios de aceite e aceite final |
| Arquitetura de Software | ADRs, camadas, modelo de dados e regras no servidor |
| Gestão de Projeto | Cronograma, cerimônias, riscos e impedimentos |
| Backend | Authentication, Firestore, regras, Cloud Functions, FCM, emulador e seed |
| Mobile | Telas em Compose, ViewModels, navegação e integração com os repositórios |
| Design UI/UX | Fluxos, protótipos, tema, componentes e textos |
| QA | Cenários de teste, execução, defeitos e aceite funcional |
| Tech Lead | Code review, padrões de código e fluxo de branches |
| Acessibilidade | TalkBack, navegação sem toque, fonte 200%, contraste e aceite de acessibilidade |
| Requisitos | Requisitos, lista de tags, LGPD e rastreabilidade requisito → história → teste |

## Documentação do projeto

A documentação de produto e de gestão fica na pasta compartilhada do Projeto Integrador:

- **Visão Geral do Projeto** — papéis, responsabilidades por função, arquitetura e ciclo iterativo.
- **Backlog do Produto** — histórias, critérios de aceite, prioridade e aceite.
- **Requisitos** e **Épicos e Features** — RF, RNF, RN e features.
- **Arquitetura (Sprint 3)** — ADRs, modelo de dados, regras de segurança e Cloud Functions.
- **Cenários de Teste** — CT01–CT32.
- **Planilha de Riscos**.

---

Projeto acadêmico desenvolvido no Projeto Integrador 2026.2 do curso de Sistemas de Informação do IFAL.

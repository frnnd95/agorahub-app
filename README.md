# ÁgoraHub

Aplicativo Android que reúne oportunidades acadêmicas (editais, bolsas, hackathons e eventos) e a formação de equipes de projeto entre estudantes do IFAL.

Projeto acadêmico das disciplinas de Projeto Integrador e Programação para Dispositivos Móveis — Sistemas de Informação, IFAL Campus Arapiraca, 2026.2.

## Situação atual

O app abre no Mural de Oportunidades, que mostra uma lista de oportunidades de exemplo (os dados ainda não vêm do Firestore). As vencidas não aparecem, a lista é ordenada pelo prazo mais próximo e o prazo fica em vermelho quando faltam 3 dias ou menos. Tocar numa oportunidade abre os detalhes dela.

A barra de navegação tem as abas Mural, Projetos e Perfil. Projetos e Perfil mostram, por enquanto, só o título.
## Tecnologias

- Kotlin
- Jetpack Compose
- Android Studio
- Navigation Compose: navegação entre as telas (Aula 06)
- Coil: imagens das oportunidades carregadas da internet (Aula 01)
- Material Icons: ícones das telas (Aula 05, D13)
- Desugaring de bibliotecas do Java: datas com `LocalDate` no Android 7 (D14)

## Documentação

- [Requisitos](docs/requisitos.md): o que o app faz no MVP, o que fica para a versão completa e as decisões do projeto.
- [Iterações](docs/iteracoes.md): o que foi planejado e entregue em cada iteração.
- [Guia de desenvolvimento](docs/guia-desenvolvimento.md): passo a passo para trabalhar numa issue.

## Como rodar o projeto

1. Clone o repositório:
```shell
   git clone https://github.com/frnnd95/agorahub-app.git
```
2. No Android Studio, abra **File › Open** e escolha a pasta `agorahub-app`.
3. Espere a sincronização do Gradle terminar.
4. Conecte um celular com a **Depuração USB** ativada ou inicie um emulador.
5. Clique em **Run ▶**.

**Resultado esperado:** o app abre no Mural de Oportunidades, com a lista de oportunidades de exemplo e a barra de navegação embaixo. Tocar numa oportunidade abre os detalhes.

Se aparecer o erro `INSTALL_FAILED_USER_RESTRICTED`, ative **Instalar via USB** nas Opções do desenvolvedor.

### Firebase

O app usa o Firebase, no plano gratuito Spark, com o Authentication (e-mail e senha) e o Cloud Firestore. Para rodar o projeto:

- O arquivo `google-services.json` já está na pasta `app` do repositório. Não é preciso criar nem baixar outro.
- Na primeira sincronização, o Gradle baixa as bibliotecas do Firebase, então o computador precisa estar conectado à internet.
- Para ver ou alterar os dados, entre no [console do Firebase](https://console.firebase.google.com) com o email do AgoraHub.
- As regras do Firestore ficam no console, não no repositório. A regra provisória está registrada na decisão D17 do [documento de requisitos](docs/requisitos.md).
- O Mural ainda usa dados de exemplo. A leitura das oportunidades no Firestore fica para a Iteração 3.

## Estrutura de pastas

```
app/src/main/java/br/edu/ifal/agorahub/
├── MainActivity.kt   → abre o app, mostra a barra de navegação e define as rotas das telas
├── data/             → classes que entregam os dados às telas (D10) e os dados de exemplo
├── domain/           → classes que representam os dados do app, como Oportunidade
└── ui/
    ├── components/   → partes usadas dentro das telas, como o cartão da oportunidade e a barra de navegação
    ├── screen/       → telas do app (uma por arquivo)
    └── theme/        → cores, fontes e tema
```

## Equipe

Fernanda Tenório Silva · Jhully Walkyria Oliveira Santos · Jousiclécia Almeida dos Santos · Lucas Correia Costa Cardozo · Samuel Cassiano dos Santos
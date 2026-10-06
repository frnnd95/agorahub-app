# ÁgoraHub

Aplicativo Android que reúne oportunidades acadêmicas (editais, bolsas, hackathons e eventos) e a formação de equipes de projeto entre estudantes do IFAL.

Projeto acadêmico das disciplinas de Projeto Integrador e Programação para Dispositivos Móveis — Sistemas de Informação, IFAL Campus Arapiraca, 2026.2.

## Situação atual

O app abre na tela do Mural de Oportunidades, que por enquanto mostra apenas o título.

## Tecnologias

- Kotlin
- Jetpack Compose
- Android Studio

## Como rodar o projeto

1. Clone o repositório:
```shell
   git clone https://github.com/frnnd95/agorahub-app.git
```
2. No Android Studio, abra **File › Open** e escolha a pasta `agorahub-app`.
3. Espere a sincronização do Gradle terminar.
4. Conecte um celular com a **Depuração USB** ativada ou inicie um emulador.
5. Clique em **Run ▶**.

**Resultado esperado:** o app abre mostrando "Mural de Oportunidades".

Se aparecer o erro `INSTALL_FAILED_USER_RESTRICTED`, ative **Instalar via USB** nas Opções do desenvolvedor.

## Estrutura de pastas

```
app/src/main/java/br/edu/ifal/agorahub/
├── MainActivity.kt   → abre o app e mostra a tela inicial
└── ui/
    ├── screen/       → telas do app (uma por arquivo)
    └── theme/        → cores, fontes e tema
```

## Equipe

Fernanda Tenório Silva · Jhully Walkyria Oliveira Santos · Jousiclécia Almeida dos Santos · Lucas Correia Costa Cardozo · Samuel Cassiano dos Santos
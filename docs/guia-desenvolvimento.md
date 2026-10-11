# Guia de desenvolvimento — ÁgoraHub

Passo a passo para quem vai trabalhar numa issue. Siga na ordem e volte aqui sempre que tiver dúvida.

## 1. Antes de começar

1. No quadro do GitHub Projects, mova a sua issue para **Em andamento**.
2. Leia a issue inteira, incluindo os critérios de aceite e o "Fora desta issue".
3. Atualize a `main` e crie a sua branch a partir dela:

```shell
git checkout main
git pull origin main
git checkout -b feature/<requisito>-<resumo>
```

Exemplos de nome de branch: `feature/RF10-mural`, `feature/D08-barra-navegacao`. Para corrigir um erro, use `fix/` no lugar de `feature/`.

## 2. Durante o trabalho

- Faça commits pequenos, um para cada parte que já funciona.
- A mensagem do commit é curta e começa pelo requisito:

```shell
git add .
git commit -m "RF10: mostra o prazo em vermelho"
```

- Rode o app no celular antes de cada commit.
- Teste o comportamento pedido nos critérios de aceite da issue.

## 3. Antes de abrir o pull request

**Traga para a sua branch o que os colegas já colocaram na `main`:**

```shell
git checkout main
git pull origin main
git checkout feature/<sua-branch>
git merge main
```

Se o Git avisar de conflito, ele mostra os arquivos com problema. Peça ajuda no grupo antes de resolver.

**Confira a acessibilidade (RNF01 a RNF05):**

- [ ] Com o TalkBack ligado, cada imagem e ícone importante é lido com um nome que faz sentido. Imagens e ícones só decorativos não são lidos.
- [ ] Com a fonte do celular no tamanho máximo, nenhum texto é cortado.
- [ ] O app Accessibility Scanner não mostra alertas de contraste nem de área de toque.
- [ ] Mensagens de erro e avisos aparecem como texto, nunca só por cor.

## 4. Abrir o pull request

1. Envie a branch: `git push -u origin feature/<sua-branch>`
2. No GitHub, clique em **Compare & pull request**.
3. Título: o requisito e o que foi feito, por exemplo `RF10: Mural de Oportunidades`.
4. Descrição:

```markdown
**O que muda:** ...
**Como testar:** ...
Closes #N
```

O `Closes #N` (com o número da issue) fecha a issue automaticamente quando o pull request for aceito.

5. Em **Reviewers**, escolha Lucas ou Jousiclécia.
6. No quadro, mova a issue para **Em revisão**.

## 5. Revisão e merge

**Quem revisa confere:**

- [ ] O app compila e roda no celular.
- [ ] Os critérios de aceite da issue funcionam.
- [ ] O checklist de acessibilidade foi seguido.
- [ ] Não há código de outras issues misturado.

Para rodar o app da branch do colega:

```shell
git fetch origin
git checkout feature/<branch-do-colega>
```

Depois da revisão, volte com `git checkout main`.

Se precisar de mudanças, o revisor comenta no pull request. Quem fez a issue corrige na mesma branch e faz um novo commit e um novo `git push`.

Depois da aprovação, clique em **Merge pull request** e depois em **Delete branch**. A issue fecha sozinha e vai para **Concluído** no quadro; se não for, mova manualmente.

## 6. Documentação

Alterações só em arquivos da pasta `docs` ou no README podem ser feitas direto na `main`, com mensagem começando por `docs:`. Código sempre passa por branch e pull request (RNF13).

## 7. Como decidir sozinho

Durante a issue, você vai precisar tomar decisões técnicas. Antes de usar algo novo, responda:

1. Isso foi visto na aula ([AulaPRMO](https://github.com/tarsisms/AulaPRMO)) ou é exigido pelo [documento de requisitos](requisitos.md)?
2. Consigo explicar ao professor, em poucas frases, o que é e por que foi preciso?
3. Existe um jeito mais simples, com o que já sabemos?

Se a resposta à pergunta 1 for "não", só use se for indispensável para a issue. Nesse caso, registre a decisão na seção 8 do documento de requisitos (RNF11) e explique na descrição do pull request.

As decisões já tomadas estão na [seção 8 do documento de requisitos](requisitos.md#82-decisões-tomadas). Consulte antes de começar.

## 8. Combinados do código

- Todo `@Preview` fica dentro do `AgoraHubTheme`, para mostrar as cores do app.
- As cores nas telas vêm do tema (`MaterialTheme.colorScheme.primary`, `.error` e assim por diante), nunca de um `Color(0xFF...)` escrito na tela. Assim o tema escuro funciona sozinho (RNF06).
- Use as palavras do documento de requisitos: "interesse" (não "tag"), "responsável" (não "dono"), "Mural de Oportunidades", "Hub de Projetos" (D11).
- Ícones vêm do `Icons.Default`, sem importar SVG (D13). Veja a seção 9.

## 9. Como usar ícones

Os ícones do app são os Material Icons (D13). A biblioteca já está no projeto, e quem desenvolve a tela escolhe o ícone.

### Escolher o ícone

1. No Android Studio, clique com o botão direito em `app` e escolha **New › Vector Asset**.
2. Clique no ícone ao lado de **Clip art** para ver a lista. Pesquise em inglês, como `home`, `edit` ou `mail`.
3. Anote o nome e clique em **Cancel**. A janela serve só para consulta: não crie nenhum arquivo.

O nome muda de formato no código: `open_in_new` vira `OpenInNew`, e `campaign` vira `Campaign`.

Ícones já separados pela equipe:

| Ícone        | No código                              |
|--------------|----------------------------------------|
| Casa         | `Icons.Default.Home`                   |
| Megafone     | `Icons.Default.Campaign`               |
| Pasta        | `Icons.Default.Folder`                 |
| Perfil       | `Icons.Default.Person`                 |
| Pessoas      | `Icons.Default.People`                 |
| Adicionar    | `Icons.Default.Add`                    |
| Editar       | `Icons.Default.Edit`                   |
| E-mail       | `Icons.Default.Email`                  |
| Abrir link   | `Icons.Default.OpenInNew`              |
| Enviar       | `Icons.Default.Send`                   |
| Seta direita | `Icons.Default.ChevronRight`           |
| Voltar       | `Icons.AutoMirrored.Filled.ArrowBack`  |
| Sair         | `Icons.AutoMirrored.Filled.Logout`     |

Voltar e Sair usam `AutoMirrored`: são setas que se espelham sozinhas em idiomas escritos da direita para a esquerda.

### Colocar na tela

```kotlin
Icon(
    imageVector = Icons.Default.Campaign,
    contentDescription = null
)
```

Ao digitar o nome, aperte **Alt+Enter** para importar. Cada ícone tem o seu import, como `androidx.compose.material.icons.filled.Campaign`.

- **Tamanho:** 24 dp por padrão. Para mudar, use `modifier = Modifier.size(28.dp)`.
- **Cor:** por padrão segue a cor do texto ao redor. Para mudar, use uma cor do tema, como `tint = MaterialTheme.colorScheme.primary`, nunca `Color(0xFF...)` (seção 8).

### Acessibilidade (RNF01)

- Se o ícone **informa algo sozinho**, sem texto ao lado, escreva o que ele significa: `contentDescription = "Abrir link"`.
- Se o ícone está **junto de um texto** que já diz a mesma coisa, ou é **só decorativo**, use `contentDescription = null`. Assim o TalkBack não lê a mesma coisa duas vezes.
- Nunca deixe `contentDescription = ""`.

### Ícone que funciona como botão

Se tocar no ícone faz alguma coisa, coloque-o dentro de um `IconButton`. Ele garante a área de toque de 48 dp (RNF04), e o TalkBack anuncia que é um botão:

```kotlin
IconButton(onClick = { /* o que acontece ao tocar */ }) {
    Icon(
        imageVector = Icons.Default.Edit,
        contentDescription = "Editar perfil"
    )
}
```

O `IconButton` não aparece nas aulas: ele entra por causa da RNF04.

## 10. Se travar

Escreva um comentário na issue explicando onde travou e avise no grupo. Travar faz parte; ficar parado sem avisar atrasa a equipe.
package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Projeto
import com.google.firebase.Timestamp

// Dados de teste, usados só no ‘Preview’ da ProjetosScreen.
val projetosExemplo = listOf(
    Projeto(
        id = "1",
        titulo = "Aplicativo para organizar grupos de estudo das disciplinas do curso",
        descricao = "App para marcar encontros de estudo por disciplina, com participantes e lembretes.",
        vagasDisponiveis = 3,
        interesses = listOf("Mobile", "UX/UI", "Back-end", "Banco de Dados", "Gestão de Projetos"),
        status = "Aberto",
        responsavel = "uid-exemplo-1",
        criadoEm = Timestamp.now(),
    ),
    Projeto(
        id = "2",
        titulo = "Monitoria colaborativa de Banco de Dados",
        descricao = "Grupo para revisar Banco de Dados e montar listas de exercícios para os calouros.",
        vagasDisponiveis = 1,
        interesses = listOf("Banco de Dados", "Ensino e Monitoria"),
        status = "Aberto",
        responsavel = "uid-exemplo-2",
        criadoEm = Timestamp.now(),
    ),
)
package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Projeto
import com.google.firebase.Timestamp

// Dados de teste, usados só no Preview da ProjetosScreen.
val projetosExemplo = listOf(
    Projeto(
        titulo = "Aplicativo para organizar grupos de estudo das disciplinas do curso",
        vagasDisponiveis = 3,
        interesses = listOf("Mobile", "UX/UI", "Back-end", "Banco de Dados", "Gestão de Projetos"),
        status = "Aberto",
        criadoEm = Timestamp.now(),
    ),
    Projeto(
        titulo = "Monitoria colaborativa de Banco de Dados",
        vagasDisponiveis = 1,
        interesses = listOf("Banco de Dados", "Ensino e Monitoria"),
        status = "Aberto",
        criadoEm = Timestamp.now(),
    ),
)
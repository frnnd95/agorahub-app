package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Perfil

// Dados de teste. Serão substituídos pela leitura do Firestore depois do RF07.

val perfilExemplo = Perfil(
    nome = "Maria Clara Santos",
    curso = "Sistemas de Informação",
    interesses = listOf("Mobile", "UX/UI", "Acessibilidade", "Banco de Dados"),
)
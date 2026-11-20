package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Perfil
import br.edu.ifal.agorahub.domain.Solicitacao
import com.google.firebase.Timestamp

// Dados de teste, usados só no Preview da SolicitacoesRecebidasScreen.
val solicitacoesExemplo = listOf(
    Solicitacao(
        id = "1",
        projeto = "1",
        solicitante = "teste-ana",
        responsavel = "uid-exemplo-1",
        status = "Pendente",
        motivo = null,
        criadaEm = Timestamp.now(),
    ),
    Solicitacao(
        id = "2",
        projeto = "1",
        solicitante = "teste-bruno",
        responsavel = "uid-exemplo-1",
        status = "Aceita",
        motivo = null,
        criadaEm = Timestamp.now(),
    ),
    Solicitacao(
        id = "3",
        projeto = "2",
        solicitante = "teste-carla",
        responsavel = "uid-exemplo-1",
        status = "Recusada",
        motivo = "As vagas para esse perfil já foram preenchidas.",
        criadaEm = Timestamp.now(),
    ),
)
val solicitantesExemplo = mapOf(
    "teste-ana" to Perfil(
        "Ana Souza",
        "Sistemas de Informação",
        listOf("Android", "Kotlin", "UX")
    ),
    "teste-bruno" to Perfil(
        "Bruno Lima",
        "Ciência da Computação",
        listOf("Banco de Dados", "Python")
    ),
    "teste-carla" to Perfil(
        "Carla Melo",
        "Engenharia de Software",
        listOf("Testes", "Kotlin", "Git")
    ),
)

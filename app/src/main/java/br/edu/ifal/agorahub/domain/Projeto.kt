package br.edu.ifal.agorahub.domain

import com.google.firebase.Timestamp

class Projeto(
    val id: String,
    val titulo: String,
    val descricao: String,
    val vagasDisponiveis: Int,
    val interesses: List<String>,
    val status: String,
    val responsavel: String,
    val criadoEm: Timestamp,
)
package br.edu.ifal.agorahub.domain

import com.google.firebase.Timestamp

class Projeto(
    val titulo: String,
    val vagasDisponiveis: Int,
    val interesses: List<String>,
    val status: String,
    val criadoEm: Timestamp,
)
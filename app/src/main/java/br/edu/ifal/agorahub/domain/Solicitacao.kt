package br.edu.ifal.agorahub.domain

import com.google.firebase.Timestamp

class Solicitacao(
    val id: String,
    val projeto: String,
    val solicitante: String,
    val responsavel: String,
    val status: String,
    val motivo: String?,
    val criadaEm: Timestamp,
)
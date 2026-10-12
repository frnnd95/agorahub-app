package br.edu.ifal.agorahub.domain

import java.time.LocalDate

class Oportunidade(
    val id: String,
    val titulo: String,
    val tipo: String,
    val descricao: String,
    val prazo: LocalDate,
    val dataPublicacao: LocalDate,
    val link: String,
    val imagemUrl: String? = null,
    val descricaoImagem: String? = null,
)
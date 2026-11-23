package br.edu.ifal.agorahub.domain

import com.google.firebase.Timestamp

class Projeto(
    val id: String,
    val titulo: String,
    val descricao: String,
    val vagasDisponiveis: Int,
    val vagas: Int,
    val membros: List<String>, // uids dos membros, sem o responsável (seção 9.3)
    val interesses: List<String>,
    val status: String,
    val responsavel: String,
    val criadoEm: Timestamp,
)
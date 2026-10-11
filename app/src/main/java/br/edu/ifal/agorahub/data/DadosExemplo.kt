package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Oportunidade
import java.time.LocalDate

// Dados de teste. Serão substituídos pela leitura do Firestore na Iteração 3.

private val hoje = LocalDate.now()

val oportunidadesExemplo = listOf(
    // Prazo vencido: não deve aparecer no Mural (RN12)
    Oportunidade(
        id = "1",
        titulo = "Edital de Monitoria 2026.2",
        tipo = "Edital",
        descricao = "Seleção de monitores para disciplinas do curso de Sistemas de Informação.",
        prazo = hoje.minusDays(1),
        dataPublicacao = hoje.minusDays(20),
        link = "https://www.ifal.edu.br",
        imagemUrl = "https://picsum.photos/id/20/800/400",
        descricaoImagem = "Mesa de estudos com caderno, caneta e notebook.",
    ),
    // Encerra hoje (RN14)
    Oportunidade(
        id = "2",
        titulo = "Maratona de Programação do IFAL",
        tipo = "Competição",
        descricao = "Competição em equipes de três estudantes, resolvendo problemas de programação.",
        prazo = hoje,
        dataPublicacao = hoje.minusDays(10),
        link = "https://www.ifal.edu.br",
        imagemUrl = "https://picsum.photos/id/0/800/400",
        descricaoImagem = "Notebook aberto sobre uma mesa de madeira.",
    ),
    // Falta 1 dia (RN14)
    Oportunidade(
        id = "3",
        titulo = "Semana de Tecnologia do Campus Arapiraca",
        tipo = "Evento",
        descricao = "Palestras e minicursos sobre desenvolvimento, dados e carreira.",
        prazo = hoje.plusDays(1),
        dataPublicacao = hoje.minusDays(7),
        link = "https://www.ifal.edu.br",
        imagemUrl = "https://picsum.photos/id/180/800/400",
        descricaoImagem = "Notebook e caderno sobre uma mesa clara.",
    ),
    // Faltam 3 dias (RN14) e sem imagem (RN15)
    Oportunidade(
        id = "4",
        titulo = "Estágio em Desenvolvimento Mobile",
        tipo = "Estágio",
        descricao = "Vaga de estágio para estudantes com interesse em Android.",
        prazo = hoje.plusDays(3),
        dataPublicacao = hoje.minusDays(4),
        link = "https://www.ifal.edu.br",
    ),
    // Mesmo prazo da próxima, publicada mais recentemente: aparece antes (RN13)
    Oportunidade(
        id = "5",
        titulo = "Grupo de Estudos de Kotlin",
        tipo = "Outro",
        descricao = "Encontros semanais para estudar Kotlin e Jetpack Compose.",
        prazo = hoje.plusDays(10),
        dataPublicacao = hoje.minusDays(2),
        link = "https://www.ifal.edu.br",
        imagemUrl = "https://picsum.photos/id/119/800/400",
        descricaoImagem = "Notebook sobre uma mesa, visto de cima.",
    ),
    Oportunidade(
        id = "6",
        titulo = "Edital de Iniciação Científica",
        tipo = "Edital",
        descricao = "Bolsas de pesquisa para estudantes de graduação.",
        prazo = hoje.plusDays(10),
        dataPublicacao = hoje.minusDays(5),
        link = "https://www.ifal.edu.br",
        imagemUrl = "https://picsum.photos/id/24/800/400",
        descricaoImagem = "Livro aberto sobre uma superfície.",
    ),
)
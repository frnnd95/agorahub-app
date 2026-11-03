package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.CategoriaInteresse

// Lista fixa de interesses ( RN08)
class InteresseDados {
    fun listarCategorias(): List<CategoriaInteresse> {
        return listOf(
            CategoriaInteresse("Desenvolvimento", listOf("Mobile", "Web", "Back-end", "Jogos")),
            CategoriaInteresse("Dados e IA", listOf("Ciência de Dados", "Inteligência Artificial",
                "Banco de Dados")),
            CategoriaInteresse("Design", listOf("UX/UI", "Design Gráfico")),
            CategoriaInteresse("Infraestrutura", listOf("Redes", "Cloud/DevOps", "Segurança da Informação")),
                    CategoriaInteresse("Hardware", listOf("IoT e Embarcados", "Robótica")),
                CategoriaInteresse(
                    "Pesquisa, gestão e sociedade",
                    listOf(
                        "Pesquisa Acadêmica", "Gestão de Projetos", "Empreendedorismo",
                        "Acessibilidade", "Ensino e Monitoria", "Programação Competitiva",
                    ),
                ),
            )
    }
}
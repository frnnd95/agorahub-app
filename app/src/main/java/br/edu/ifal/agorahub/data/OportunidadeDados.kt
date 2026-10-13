package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Oportunidade
import java.time.LocalDate

class OportunidadeDados {
    fun listarMural(): List<Oportunidade> {
        val hoje = LocalDate.now()

        val dentroDoPrazo = oportunidadesExemplo.filter { it.prazo >= hoje }

        return dentroDoPrazo.sortedWith(
            compareBy<Oportunidade> { it.prazo }
                .thenByDescending { it.dataPublicacao }
        )
    }
}
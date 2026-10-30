package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Oportunidade
import java.time.LocalDate

class OportunidadeDados {
    fun listarMural(aoCarregar: (List<Oportunidade>) -> Unit) {
        val hoje = LocalDate.now()

        val dentroDoPrazo = oportunidadesExemplo.filter { it.prazo >= hoje }

        val ordenadas = dentroDoPrazo.sortedWith(
            compareBy<Oportunidade> { it.prazo }
                .thenByDescending { it.dataPublicacao }
        )
        // Entrega a lista para quem pediu (por enquanto, na hora)
        aoCarregar(ordenadas)
    }

    fun buscarPorId(id: String?): Oportunidade? {
        return oportunidadesExemplo.find { it.id == id }
    }
}
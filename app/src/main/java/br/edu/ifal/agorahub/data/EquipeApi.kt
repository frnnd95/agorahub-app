package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Integrante
import retrofit2.http.GET

interface EquipeApi {
    @GET("equipe.json")
    suspend fun listarIntegrantes(): List<Integrante>
}
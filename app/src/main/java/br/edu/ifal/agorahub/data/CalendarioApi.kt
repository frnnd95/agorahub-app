package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.EventoCalendario
import retrofit2.http.GET

interface CalendarioApi {
    @GET("calendario.json")
    suspend fun listarEventos(): List<EventoCalendario>
}
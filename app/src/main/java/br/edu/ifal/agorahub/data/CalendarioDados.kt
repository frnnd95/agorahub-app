package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.EventoCalendario

class CalendarioDados {
    suspend fun listarEventos(): List<EventoCalendario> {
        return RetrofitHelper().calendarioApi.listarEventos()
    }
}
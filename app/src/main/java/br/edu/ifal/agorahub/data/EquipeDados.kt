package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Integrante

class EquipeDados {
    suspend fun listarIntegrantes(): List<Integrante> {
        return RetrofitHelper().equipeApi.listarIntegrantes()
    }
}
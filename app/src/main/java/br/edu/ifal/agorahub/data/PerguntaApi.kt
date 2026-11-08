package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Pergunta
import retrofit2.http.GET

interface PerguntaApi {
    // Caminho somado ao baseUrl do RetrofitHelper
    @GET("perguntas.json")
    suspend fun listarPerguntas(): List<Pergunta>
}
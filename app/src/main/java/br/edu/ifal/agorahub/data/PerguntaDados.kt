package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Pergunta

class PerguntaDados {
    suspend fun listarPerguntas(): List<Pergunta> {
        return RetrofitHelper().perguntaApi.listarPerguntas()
    }
}
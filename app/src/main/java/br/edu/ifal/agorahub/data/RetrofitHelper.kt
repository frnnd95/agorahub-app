package br.edu.ifal.agorahub.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitHelper {

    private val client = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/frnnd95/agorahub-api/main/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val perguntaApi: PerguntaApi = client.create(PerguntaApi::class.java)

    val calendarioApi: CalendarioApi = client.create(CalendarioApi::class.java)
}
package br.edu.ifal.agorahub.data

import br.edu.ifal.agorahub.domain.Oportunidade
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.ZoneId
import kotlinx.coroutines.tasks.await

class OportunidadeDados {
    suspend fun listarMural(): List<Oportunidade> {
        // Espera o Firestore responder; se falhar, lança uma exceção
        val resultado = FirebaseFirestore.getInstance()
            .collection("oportunidades")
            .get()
            .await()

        val hoje = LocalDate.now()
        // Converte cada documento; os incompletos ficam de fora
        val todas = resultado.documents.mapNotNull { converter(it) }
        val dentroDoPrazo = todas.filter { it.prazo >= hoje }

        return dentroDoPrazo.sortedWith(
            compareBy<Oportunidade> { it.prazo }
                .thenByDescending { it.dataPublicacao }
        )
    }

    suspend fun buscarPorId(id: String?): Oportunidade? {
        if (id == null) {
            return null
        }
        // Espera o Firestore responder; se falhar, lança uma exceção
        val doc = FirebaseFirestore.getInstance()
            .collection("oportunidades")
            .document(id)
            .get()
            .await()
        // Documento inexistente ou incompleto vira null
        return converter(doc)
    }

    // Documento do Firestore -> Oportunidade (estrutura da D15)
    private fun converter(doc: DocumentSnapshot): Oportunidade? {
        return Oportunidade(
            id = doc.id,
            titulo = doc.getString("titulo") ?: return null,
            tipo = doc.getString("tipo") ?: return null,
            descricao = doc.getString("descricao") ?: return null,
            prazo = paraLocalDate(doc.getTimestamp("prazo") ?: return null),
            dataPublicacao = paraLocalDate(doc.getTimestamp("dataPublicacao") ?: return null),
            link = doc.getString("link") ?: return null,
            imagemUrl = doc.getString("imagemUrl"),
            descricaoImagem = doc.getString("descricaoImagem"),
        )
    }

    private fun paraLocalDate(data: Timestamp): LocalDate {
        return data.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    }
}
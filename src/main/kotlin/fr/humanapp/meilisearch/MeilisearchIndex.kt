package fr.humanapp.meilisearch

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileNotFoundException
import java.util.*
import fr.humanapp.meilisearch.dto.IndexDto
import fr.humanapp.meilisearch.exception.MeilisearchException

public class MeilisearchIndex(
	uid: String,
	primaryKey: String = "id",
	name: String = uid,
	createdAt: Date? = null,
	updatedAt: Date? = null,
	var config: MeilisearchConfig) : IndexDto(uid, primaryKey, name) {

	private companion object {
		private val gson = Gson()
	}

	@Throws(MeilisearchException::class)
	inline fun <reified T> getDocuments(): List<T> {
		val (_, _, result) = this.config.indexDocumentsPath(this.uid).httpGet().responseObject<List<T>>()

		if (result is Result.Failure)
			throw MeilisearchException("Unable to retrieve documents for index ${this.uid}", result.getException())

		return result.get()
	}

	@Throws(FileNotFoundException::class)
	fun <T : Any> getDocument(key: String): T {
		val genericType = object : TypeToken<T>() {}.type
		val (_, response, result) = "${this.config.indexDocumentsPath(this.uid)}/$key".httpGet().responseString()

		if (result is Result.Failure) {
			if (response.statusCode == 404) throw FileNotFoundException("Could not find document $key in index $uid")
			throw MeilisearchException("Unknown error while gathering document $key of index $uid", result.getException())
		}

		return gson.fromJson(result.get(), genericType)
	}

	@Throws(MeilisearchException::class)
	fun <T> insert(vararg documents: T) {
		val (_, _, result) = this.config.indexDocumentsPath(this.uid).httpPost()
			.body(documents.map {
				gson.toJson(it, object : TypeToken<T>() {}.type)
			}.toString())
			.responseString()

		if (result is Result.Failure) throw MeilisearchException("Unknown error while inserting documents in index $uid", result.getException())
	}

	@Throws(MeilisearchException::class, FileNotFoundException::class)
	fun deleteDocument(id: String) {
		val (_, response, result) = this.config.documentPath(this.uid, docId = id).httpDelete().response()

		if (result is Result.Failure) {
			throw when(response.statusCode) {
				404 -> FileNotFoundException("Document $id does not exist in index ${this.uid}")
				else -> MeilisearchException("Unknown error while deleting document $id from index ${this.uid}", result.getException())
			}
		}
	}

	@Throws(MeilisearchException::class, FileNotFoundException::class)
	fun deleteDocument(id: Number) {
		this.deleteDocument(id.toString())
	}

	@Throws(MeilisearchException::class, FileNotFoundException::class)
	fun deleteDocuments(vararg ids: String) = ids.forEach {
		deleteDocument(it)
	}

	@Throws(MeilisearchException::class, FileNotFoundException::class)
	fun deleteDocuments(vararg ids: Number) = ids.forEach {
		deleteDocument(it)
	}
}

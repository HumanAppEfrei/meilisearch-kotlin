package fr.humanapp.meilisearch.model

data class TestBook(
	val id: Long,
	val title: String,
	val author: String,
	val description: String = ""
)
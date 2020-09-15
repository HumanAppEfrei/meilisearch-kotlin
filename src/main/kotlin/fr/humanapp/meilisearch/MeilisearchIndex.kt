package fr.humanapp.meilisearch

import fr.humanapp.meilisearch.dto.IndexDto
import java.util.*

public class MeilisearchIndex(
	uid: String,
	primaryKey: String = "id",
	name: String = uid,
	createdAt: Date? = null,
	updatedAt: Date? = null) : IndexDto(uid, primaryKey, name) {

}
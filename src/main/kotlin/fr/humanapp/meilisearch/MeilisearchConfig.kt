package fr.humanapp.meilisearch

import fr.humanapp.meilisearch.exception.MeilisearchException
import java.net.UnknownHostException


/**
 * Wrapper class representing the configuration for a MeilisearchClient instance
 */
public class MeilisearchConfig @JvmOverloads constructor(host: String, private val apiKey: String? = null) {
	var host: String private set

	// TODO: improve httpRegex
	private val httpRegex = Regex("https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}(\\.[a-zA-Z0-9()]{1,6})?\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)")
	private fun removeTrailingSlash(host: String) = Regex("/*$").replace(host, "")

	init {
		if (!httpRegex.matches(host)) throw UnknownHostException("Invalid host \"$host\"")
		this.host = removeTrailingSlash(host)
	}
}

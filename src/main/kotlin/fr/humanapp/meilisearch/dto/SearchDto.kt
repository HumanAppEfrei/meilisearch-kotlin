package fr.humanapp.meilisearch.dto

/**
 * Meilisearch REST API response for search queries
 *
 * @param T Type of documents the search is being operated on
 */
data class SearchDto<T>(
	/**
	 * Result of the query
	 */
	val hits: List<T>,

	/**
	 * Number of documents skipped
	 */
	val offset: Int = 0,

	/**
	 * Number of documents to take
	 */
	val limit: Int = 20,

	/**
	 * Total number of matches
	 */
	val nbHits: Int,

	/**
	 * Whether **nbHits** is exhaustive
	 */
	val exhaustiveNbHits: Boolean,

	/**
	 * Distribution of the given facets
	 */
	val facetDistribution: Any,

	/**
	 * Whether **facetDistribution** is exhaustive
	 */
	val exhaustiveFacetsCount: Boolean,

	/**
	 * Processing time of the query
	 */
	val processingTimeMs: Long,

	/**
	 * Query originating the response
	 */
	val query: String
)

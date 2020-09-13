package fr.humanapp.meilisearch

import fr.humanapp.meilisearch.exception.IndexNotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class MeilisearchClientTest {
	private val msc = MeilisearchClient("http://localhost:7700")

	@Test
	@Order(1)
	fun `createIndex() Can create index without custom primary key`() {
		msc.createIndex("__test_index_1")
	}

	@Test
	@Order(2)
	fun `createIndex() Can create index with custom primary key`() {
		msc.createIndex("__test_index_2", "custom_id")
	}

	@Test
	@Order(3)
	fun `getIndex() Can retrieve existing index`() {
		assertNotNull(msc.getIndex("__test_index_1"))
	}

	@Test
	@Order(4)
	fun `getIndex() Cannot retrieve non-existing index`() {
		assertThrows(IndexNotFoundException::class.java) {
			msc.getIndex("__no_existing_index")
		}
	}

	@Test
	@Order(5)
	fun `index() Can dynamically get or create index`() {
		// With existing index
		assertNotNull(msc.index("__test_index_1"))

		// With non-existing index
		assertNotNull(msc.index("__test_index_3"))
	}
}

package fr.humanapp.meilisearch

import org.junit.AfterClass
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MeilisearchIndexTest {
	private val name = "__test_index"
	private val msc = MeilisearchClient("http://localhost:7700")
	private val index = msc.createIndex(name)

	@Test
	@Order(1)
	fun `Index should be empty at first`() {
		TODO("Empty index test not implemented")
	}

	@Test
	@Order(2)
	fun `Deleting documents should work`() {
		TODO("Documents deletion test not implemented")
	}


	@AfterClass
	fun `Delete index`() {
		TODO("Index deletion test not implemented")
	}
}

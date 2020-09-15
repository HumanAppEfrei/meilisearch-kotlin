package fr.humanapp.meilisearch

import org.junit.jupiter.api.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MeilisearchIndexTest {
	private companion object {
		const val uid = "__test_index"
		val msc = MeilisearchClient("http://localhost:7700")
		val index = msc.createIndex(uid)

		@AfterAll @JvmStatic
		fun `Delete index`() {
			msc.deleteIndex(uid)
		}
	}

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
}

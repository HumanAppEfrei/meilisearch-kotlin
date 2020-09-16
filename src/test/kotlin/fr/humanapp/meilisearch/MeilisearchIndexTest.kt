package fr.humanapp.meilisearch

import fr.humanapp.meilisearch.model.TestBook
import org.junit.jupiter.api.*
import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers.*
import org.hamcrest.CoreMatchers.`is` as _is
import org.hamcrest.CoreMatchers.`not` as _not

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MeilisearchIndexTest {
	private companion object {
		private const val uid = "__test_books"
		private val msc = MeilisearchClient("http://localhost:7700")
		private val index = msc.createIndex(uid)

		private val book1 = TestBook(1, "1984", "George Orwell", "Big Brother is watching you")
		private val book2 = TestBook(2, "Animal Farm", "George Orwell", "The pigs take over")
		private val book3 = TestBook(3, "The Hitchhiker's guide to the galaxy", "Douglas Adams", "42")
		private val book4 = TestBook(4, "The C Programming Language", "Khais Klai")

		@AfterAll @JvmStatic
		fun `Delete index`() {
			msc.deleteIndex(uid)
		}
	}

	@Test
	@Order(1)
	fun `Index should be empty at first`() {
		assertThat(index.getDocuments<Any>(), _is(empty()))
	}

	@Test
	@Order(2)
	fun `Adding documents should work`() {
		index.insert(book1)  // Single insertion
		index.insert(book2, book3)  // Multiple insertion
	}

	@Test
	@Order(3)
	fun `Getting documents should work`() {
		val documents = index.getDocuments<TestBook>()
		assertThat(documents, containsInAnyOrder(book1, book2, book3))
		assertThat(documents, _not(contains(book4)))
	}

	@Test
	@Order(4)
	fun `Deleting documents should work`() {
		TODO("Documents deletion test not implemented")
	}
}

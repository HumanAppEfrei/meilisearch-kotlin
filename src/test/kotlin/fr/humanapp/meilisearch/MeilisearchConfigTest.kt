package fr.humanapp.meilisearch

import org.junit.Assert.*
import org.junit.jupiter.api.Test
import org.hamcrest.CoreMatchers.*
import org.junit.jupiter.api.assertThrows
import java.net.UnknownHostException

internal class MeilisearchConfigTest {
	@Test
	fun `Should accept valid host without formatting`() {
		val host = "http://127.0.0.1"
		val conf = MeilisearchConfig(host)
		assertThat(host, equalTo(conf.host))
	}

	@Test
	fun `Should throw for invalid host`() {
		assertThrows<UnknownHostException> {
			val conf = MeilisearchConfig("this_is_an_invalid_host")
		}
	}

	@Test
	fun `Should remove trailing slashes at end of valid host`() {
		val expectedHost = "http://127.0.0.1"
		val conf = MeilisearchConfig("$expectedHost/")
		val conf2 = MeilisearchConfig("$expectedHost/////")

		assertThat("With 1 trailing /", expectedHost, equalTo(conf.host))
		assertThat("With 5 trailing /", expectedHost, equalTo(conf2.host))
	}
}

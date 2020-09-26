package fr.humanapp.meilisearch.container

import org.testcontainers.containers.GenericContainer

class KGenericContainer(image: String) : GenericContainer<KGenericContainer>(image)

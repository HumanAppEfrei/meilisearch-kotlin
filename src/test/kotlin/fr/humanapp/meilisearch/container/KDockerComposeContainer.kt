package fr.humanapp.meilisearch.container

import java.io.File
import org.testcontainers.containers.DockerComposeContainer

class KDockerComposeContainer(path: File) : DockerComposeContainer<KDockerComposeContainer>(path)

plugins {
	kotlin("jvm") version "1.4.10"
}

group = "fr.humanapp"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))

	// Gson
	implementation("com.google.code.gson:gson:2.8.6")

	// Fuel (HTTP)
	implementation("com.github.kittinunf.fuel:fuel:2.2.3")

	// Junit
	implementation(kotlin("test"))
	implementation(kotlin("test-junit"))
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
	testRuntimeOnly("org.junit.platform:junit-platform-console:1.7.0")

	// Hamcrest
	testImplementation("org.hamcrest:hamcrest:2.2")
}


tasks {
	compileKotlin {
		kotlinOptions.jvmTarget = "1.8"
	}
	compileTestKotlin {
		kotlinOptions.jvmTarget = "1.8"
	}

	test {
		useJUnitPlatform()
		testLogging {
			events("passed", "skipped", "failed")
		}
	}
}

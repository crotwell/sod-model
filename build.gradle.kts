
group = "edu.sc.seis"
version = "4.0.0-SNAPSHOT"

plugins {
    // Apply the java-library plugin to add support for Java Library
    `java-library`
    eclipse
}
dependencies {
  implementation("org.slf4j:slf4j-api:1.7.30")
  implementation("edu.sc.seis:seedCodec:1.0.11")
  implementation("edu.sc.seis:seisFile:1.7.4")
  implementation("org.json:json:20140107")
  // Use JUnit Jupiter API for testing.
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.1")

  // Use JUnit Jupiter Engine for testing.
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.1")
}


configurations.all {
  resolutionStrategy.dependencySubstitution {
    substitute(module("edu.sc.seis:seedCodec")).with(project(":seedCodec"))
    substitute(module("edu.sc.seis:seisFile")).with(project(":seisFile"))
  }
}

repositories {
  mavenCentral()
}

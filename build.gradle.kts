
group = "edu.sc.seis"
version = "4.0.0-SNAPSHOT"

plugins {
    // Apply the java-library plugin to add support for Java Library
    `java-library`
}
dependencies {
  implementation("edu.sc.seis:seedCodec:1.0.11")
  implementation("edu.sc.seis:seisFile:1.7.4")
  implementation("org.json:json:20140107")
  //testCompile group: "junit", name: "junit", version: "4.12+"
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

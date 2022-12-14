/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("com.hazelcast:hazelcast:5.1.3")
    implementation("org.springframework.boot:spring-boot-starter:2.7.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.2")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.3")
    implementation(project(":api"))
    implementation("org.modelmapper:modelmapper:3.1.0")
    implementation("com.h2database:h2:2.1.214")
}

tasks.register("prepareKotlinBuildScriptModel"){}

group = "com.consdata"
version = "0.1.0"
description = "hazelcast-server"
java.sourceCompatibility = JavaVersion.VERSION_17


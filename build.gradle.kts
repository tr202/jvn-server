import org.gradle.api.tasks.bundling.War

plugins {
    `java-library`
    `war`
    `idea`
}

group = "ru.jvn"
version = "v1"


val myWar by tasks.registering(War::class) {}

val copyDist by tasks.registering(Copy::class) {
    dependsOn(myWar)
    from("build/libs/jvn-server-v1.war") {rename {"server.war"}}
    into("deploy/bin/")
}

repositories {
    mavenCentral()
}

dependencies {
    api("org.springframework:spring-core:5.3.23")
    api("org.springframework:spring-web:5.3.23")
    api("org.springframework:spring-webmvc:5.3.23")
    api("com.fasterxml.jackson.core:jackson-core:2.13.3")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.3")
    api("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    api("com.fasterxml.jackson.core:jackson-annotations:2.13.3")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
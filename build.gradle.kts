import org.gradle.api.tasks.bundling.War
import sun.tools.jar.resources.jar

plugins {
    `java-library`
    `war`
    `idea`
    id ("java")
    id ("project-report")
    //id("com.github.bjornvester.wsdl2java") version "1.2"
    id("org.hibernate.build.xjc") version "2.2.0"
}

group = "ru.jvn"
version = "v1"



val myWar by tasks.registering(War::class) {}

val copyDist by tasks.registering(Copy::class) {
    dependsOn(myWar)



    from("build/libs/jvn-server-v1.war") {rename {"server.war"}}
    into("deploy/bin/")

}

val myJar by tasks.registering(Jar::class) {

        manifest

}

repositories {
    mavenCentral()
}




//wsdl2java {
//    cxfVersion.set("3.4.5")
//    options.addAll("-b", "/home/marid/jtest/jvn1/jvn-server/src/main/resources/temp/XMLSchema.xsd","-b","/home/marid/jtest/jvn1/jvn-server/src/main/resources/temp/XMLSchema.dtd","-b","/home/marid/jtest/jvn1/jvn-server/src/main/resources/temp/datatypes.dtd")
//    //options.addAll("-b", "http://www.w3.org/2001/XMLSchema.xsd")
//
//    //markGenerated.set("true")
//    //markGenerated.set("yes-jdk8")
//}


dependencies {
    api("org.springframework:spring-core:5.3.23")
    api("org.springframework:spring-web:5.3.23")
    api("org.springframework:spring-webmvc:5.3.23")
    api("com.fasterxml.jackson.core:jackson-core:2.13.3")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.3")
    api("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    api("com.fasterxml.jackson.core:jackson-annotations:2.13.3")
    api("org.postgresql:postgresql:42.5.0")
    api("org.springframework:spring-orm:5.3.23")
    api("org.springframework:spring-jdbc:5.3.23")
    api("org.eclipse.persistence:org.eclipse.persistence.jpa:3.0.2")
    api("org.hibernate:hibernate-core:5.2.18.Final")
    api("org.springframework.data:spring-data-jpa:2.7.3")
    api("org.springframework.data:spring-data-commons:2.7.3")

// https://mvnrepository.com/artifact/com.sun.xml.ws/jaxws-maven-plugin
   // implementation("com.sun.xml.ws:jaxws-maven-plugin:4.0.0")

    // https://mvnrepository.com/artifact/com.sun.xml.ws/jaxws-maven-plugin
    //implementation("com.sun.xml.ws:jaxws-maven-plugin:2.3.1")

    //implementation ("com.sun.xml.ws:jaxws:2.0.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5:2.14.2")
    //implementation("wsdl4j:wsdl4j:1.6.2")
// https://mvnrepository.com/artifact/jakarta.xml.bind/jakarta.xml.bind-api
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:2.3.2")

    //implementation("org.jvnet.jaxb2_commons:jaxb2-basics-runtime:0.13.1")
    //xjcPlugins("org.jvnet.jaxb2_commons:jaxb2-basics:0.13.1")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("org.projectlombok:lombok:1.18.26")


    // https://mvnrepository.com/artifact/org.codehaus.mojo/jaxb2-maven-plugin
    implementation("org.codehaus.mojo:jaxb2-maven-plugin:3.2.0")

    implementation("org.springframework:spring-oxm:5.3.28")
    implementation("org.springframework.ws:spring-ws-core:3.0.1.RELEASE")
    implementation("org.slf4j:slf4j-api:2.0.2")
    testImplementation("org.slf4j:slf4j-simple:2.0.13")
    //implementation("org.springframework.ws:spring-ws-security:3.1.0")
    implementation("org.springframework.ws:spring-ws-security:3.1.8")

}

tasks.test {
    useJUnitPlatform()
}

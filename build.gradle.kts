import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.utils.addExtendsFromRelation

plugins {
    kotlin("plugin.jpa") version "1.2.71"
    id("org.springframework.boot") version "2.1.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    kotlin("jvm") version "1.2.71"
    kotlin("plugin.spring") version "1.2.71"
}

group = "umcs.testcraftmanshipt"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.0.1")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testCompile("io.cucumber:cucumber-junit:2.3.1")
    testCompile("io.cucumber:cucumber-java8:2.3.1")
    testImplementation("io.cucumber:cucumber-java8:2.3.1")
    testImplementation("io.cucumber:cucumber-junit:2.3.1")
}

val cucumberRuntime by configurations.creating {
    extendsFrom(configurations.testImplementation.get())
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.register("cucumber") {
    dependsOn("assemble")
    doLast {
        javaexec {
            main = "cucumber.api.cli.Main"
            classpath = cucumberRuntime + sourceSets["main"].output + sourceSets["test"].output
            args = listOf("--glue", "gradle.cucumber", "src/test/resources")
        }
    }
}

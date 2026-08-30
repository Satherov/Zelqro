plugins {
    java
    idea
    id("maven-publish")
    alias(libs.plugins.lombok)
}

group = "dev.satherov"
version = "1.0.3"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jspecify)
    implementation(libs.google.errorprone)

    compileOnly(libs.jetbrains.annotations)

    testImplementation("org.junit.jupiter:junit-jupiter:5.14.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
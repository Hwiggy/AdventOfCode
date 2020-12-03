plugins {
    java
    kotlin("jvm") version "1.4.10"
}

group = "me.hwiggy.aoc"
version = "2020"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

tasks {
    compileJava {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}

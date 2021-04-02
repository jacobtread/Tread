import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
    id("fabric-loom") version "0.6-SNAPSHOT"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

apply(plugin = "com.github.johnrengelman.shadow")

group = "me.jacobtread.tread"
version = "1.0.0"

minecraft {

}

repositories {
}

dependencies {
    minecraft(group = "com.mojang", name = "minecraft", version = "1.16.5")
    mappings(group = "net.fabricmc", name = "yarn", version = "1.16.5+build.5")
    modCompile(group = "net.fabricmc", name = "fabric-loader", version = "0.11.3")
    modCompile(group = "net.fabricmc.fabric-api", name = "fabric-api", version = "0.32.5+1.16")
    modImplementation(group = "net.fabricmc", name = "fabric-language-kotlin", version = "1.5.0+kotlin.1.4.31")
    modImplementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib", version = "1.4.21")
}


tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
val shadowJar: ShadowJar by tasks
shadowJar.apply {
    archiveClassifier.set("")
    archiveBaseName.set("tread")
    mergeServiceFiles()
    dependencies {
//        include(dependency("org.jetbrains.kotlin:kotlin-stdlib:1.4.21"))
    }
}


tasks {
    build {
        dependsOn(shadowJar)
    }
}


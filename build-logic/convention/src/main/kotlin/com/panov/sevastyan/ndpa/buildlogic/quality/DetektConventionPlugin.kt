package com.panov.sevastyan.ndpa.buildlogic.quality

import com.panov.sevastyan.ndpa.buildlogic.detektPlugins
import com.panov.sevastyan.ndpa.buildlogic.gitStagedKotlinFiles
import com.panov.sevastyan.ndpa.buildlogic.library
import com.panov.sevastyan.ndpa.buildlogic.libs
import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.DetektExtension
import dev.detekt.gradle.extensions.FailOnSeverity
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

private const val PRECOMMIT_PROPERTY = "precommit"

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("dev.detekt")

        val configDir = rootProject.layout.projectDirectory.dir("config/detekt")

        extensions.configure<DetektExtension> {
            source.setFrom(layout.projectDirectory.dir("src"))
            config.setFrom(configDir.file("detekt.yml"))
            buildUponDefaultConfig.set(true)
            parallel.set(true)
            failOnSeverity.set(FailOnSeverity.Warning)
            basePath.set(rootProject.layout.projectDirectory)
        }

        if (providers.gradleProperty(PRECOMMIT_PROPERTY).isPresent) {
            val ownDirectory = projectDir

            val ownStagedFiles = gitStagedKotlinFiles().filter { stagedFile ->
                stagedFile.startsWith(ownDirectory)
            }

            tasks.withType(Detekt::class.java).configureEach {
                setSource(ownStagedFiles)
            }
        }

        dependencies {
            detektPlugins(libs.library("detekt-rules-ktlintWrapper"))
            detektPlugins(libs.library("detekt-rules-compose"))
        }
    }
}

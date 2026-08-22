check(project == rootProject) {
    "Plugin com.panov.sevastyan.ndpa.git.hooks must be applied to the root project only"
}

val installGitHooks by tasks.registering(Exec::class) {
    group = "git"
    description = "Points git to the versioned hooks in .githooks"
    val gitDir = layout.projectDirectory.dir(".git").asFile
    onlyIf { gitDir.exists() }
    workingDir = layout.projectDirectory.asFile
    commandLine("git", "config", "core.hooksPath", ".githooks")
}

tasks.matching { it.name == "prepareKotlinBuildScriptModel" }.configureEach {
    dependsOn(installGitHooks)
}

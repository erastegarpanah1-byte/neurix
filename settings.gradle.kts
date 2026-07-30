pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Neurix"

include(":app")
include(":core")
include(":core-common")
include(":core-design")
include(":core-ui")
include(":core-navigation")
include(":feature-home")
include(":feature-chat")
include(":feature-settings")
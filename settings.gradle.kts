fun Settings.getExtraPropertyOrDefault(propertyName: String, defaultValue: () -> String): String {
    return if (extra.has(propertyName)) {
        extra[propertyName] as String
    } else {
        defaultValue()
    }
}

fun MavenArtifactRepository.headerAuthentication(token: String) {
    credentials(HttpHeaderCredentials::class) {
        name = "Authorization"
        value = "Bearer $token"
    }
    authentication {
        create<HttpHeaderAuthentication>("header")
    }
}

fun RepositoryHandler.addGitHubRepository(path: String) {
    val gitHubToken = (settings.extra["com.moveagency.github.token"] as? String).orEmpty()
    maven {
        name = path
        url = uri("https://maven.pkg.github.com/$path")
        headerAuthentication(gitHubToken)
    }
}

fun RepositoryHandler.m2utility() = addGitHubRepository(path = "move-android/m2utility")
fun RepositoryHandler.m2ciPlugin() = addGitHubRepository(path = "move-android/m2ci")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        //Localazy
        maven {
            url = uri("https://maven.localazy.com/repository/release/")
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.localazy.com/repository/release/") }
        maven {
            val oneginiArtifactoryUser = settings.getExtraPropertyOrDefault(
                propertyName = "onegini.artifactory.user",
                defaultValue = { System.getenv("ONEGINI_ARTIFACTORY_USER") }
            )
            val oneginiArtifactoryPassword = settings.getExtraPropertyOrDefault(
                propertyName = "onegini.artifactory.password",
                defaultValue = { System.getenv("ONEGINI_ARTIFACTORY_PASSWORD") }
            )
            url = uri("https://repo.onewelcome.com/artifactory/onegini-sdk")
            credentials {
                username = oneginiArtifactoryUser
                password = oneginiArtifactoryPassword
            }
        }
        m2utility()
    }
}

pluginManagement.repositories.m2ciPlugin()

rootProject.name = "MyTerminal-Shared"
include(":app")

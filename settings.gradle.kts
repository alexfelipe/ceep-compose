pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        val lifecycleVersion = "2.6.1"
        val roomVersion = "2.5.1"
        val navVersion = "2.5.3"
        val hiltVersion = "2.44"
        create("libs") {
            library(
                "coil-compose",
                "io.coil-kt:coil-compose:2.4.0"
            )
            library(
                "compose-bom",
                "androidx.compose:compose-bom:2023.04.01"
            )
            library(
                "hilt-android",
                "com.google.dagger:hilt-android:$hiltVersion"
            )
            library(
                "hilt-compiler",
                "com.google.dagger:hilt-compiler:$hiltVersion"
            )
            library(
                "room-runtime",
                "androidx.room:room-runtime:$roomVersion"
            )
            library(
                "room-ktx",
                "androidx.room:room-ktx:$roomVersion"
            )
            library(
                "room-compiler",
                "androidx.room:room-compiler:$roomVersion"
            )
            library(
                "lifecycle-viewmodel-compose",
                "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"
            )
            library(
                "navigation-compose",
                "androidx.navigation:navigation-compose:$navVersion"
            )
            library(
                "hilt-navigation-compose",
                "androidx.hilt:hilt-navigation-compose:1.0.0"
            )
            library(
                "core-ktx",
                "androidx.core:core-ktx:1.10.1"
            )
            library(
                "material-icons-extended",
                "androidx.compose.material",
                "material-icons-extended"
            ).withoutVersion()
            library(
                "ui",
                "androidx.compose.ui",
                "ui"
            ).withoutVersion()
            library(
                "material",
                "androidx.compose.material",
                "material"
            ).withoutVersion()
            library(
                "ui-tooling-preview",
                "androidx.compose.ui",
                "ui-tooling-preview"
            ).withoutVersion()
            library(
                "lifecycle-runtime-ktx",
                "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
            )
            library(
                "activity-compose",
                "androidx.activity:activity-compose:1.7.2"
            )
            library(
                "junit",
                "junit:junit:4.13.2"
            )
            library(
                "junit-ext",
                "androidx.test.ext:junit:1.1.5"
            )
            library(
                "espresso-core",
                "androidx.test.espresso:espresso-core:3.5.1"
            )
            library(
                "ui-test-junit4",
                "androidx.compose.ui",
                "ui-test-junit4"
            ).withoutVersion()
            library(
                "ui-tooling",
                "androidx.compose.ui",
                "ui-tooling"
            ).withoutVersion()
            library(
                "accompanist-systemuicontroller",
                "com.google.accompanist:accompanist-systemuicontroller:0.25.1"
            )
        }
    }

}
rootProject.name = "Ceep"
include(":app")

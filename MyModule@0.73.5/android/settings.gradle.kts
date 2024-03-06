import groovy.lang.Closure

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyAndroid"
apply(from = "../node_modules/@react-native-community/cli-platform-android/native_modules.gradle"); (extra["applyNativeModulesSettingsGradle"] as Closure<*>).call(settings)
include(":app")
includeBuild("../node_modules/@react-native/gradle-plugin")
 
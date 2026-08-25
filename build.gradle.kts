// 1. Сначала подключаем плагины (ТОЛЬКО id и версии)
plugins {
    application
    java
    id("com.diffplug.spotless") version "6.25.0"
    id("com.github.ben-manes.versions") version "0.54.0"
}

// 2. ОТДЕЛЬНО указываем, где Gradle должен искать эти плагины и библиотеки
repositories {
    mavenCentral()
    gradlePluginPortal()
}

// 3. Настройки приложения
application {
    mainClass.set("hexlet.code.App")
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// 4. Настройки Spotless
spotless {
    java {
        importOrder()
        removeUnusedImports()
        googleJavaFormat().aosp()
        formatAnnotations()
        // Строку leadingTabsToSpaces(4) я убрал, так как в версии 6.x она часто вызывает ошибки.
        // Google Java Format по умолчанию использует пробелы, этого достаточно.
    }
}

// 5. Зависимости
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1")) // Чуть снизил версию JUnit, чтобы не было конфликтов
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

package com.pddstudio.highlightjs.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class LanguageTest {

    @Test
    fun `AUTO_DETECT has null className`() {
        assertNull(Language.AutoDetect.className)
    }

    @Test
    fun `DISABLE_HIGHLIGHT has nohighlight className`() {
        assertEquals("nohighlight", Language.DisableHighlight.className)
    }

    @Test
    fun `companion AUTO_DETECT has null className`() {
        assertNull(Language.AUTO_DETECT.className)
    }

    @Test
    fun `companion DISABLE_HIGHLIGHT has nohighlight className`() {
        assertEquals("nohighlight", Language.DISABLE_HIGHLIGHT.className)
    }

    @Test
    fun `common language class names are correct`() {
        assertEquals("kt", Language.Kotlin.className)
        assertEquals("java", Language.Java.className)
        assertEquals("python", Language.Python.className)
        assertEquals("javascript", Language.JavaScript.className)
        assertEquals("typescript", Language.TypeScript.className)
        assertEquals("go", Language.Go.className)
        assertEquals("rust", Language.Rust.className)
        assertEquals("cpp", Language.CPlusPlus.className)
        assertEquals("csharp", Language.CSharp.className)
        assertEquals("ruby", Language.Ruby.className)
        assertEquals("swift", Language.Swift.className)
        assertEquals("scala", Language.Scala.className)
        assertEquals("dart", Language.Dart.className)
        assertEquals("html", Language.Html.className)
        assertEquals("xml", Language.Xml.className)
        assertEquals("json", Language.Json.className)
        assertEquals("css", Language.Css.className)
        assertEquals("sql", Language.Sql.className)
        assertEquals("bash", Language.Bash.className)
        assertEquals("md", Language.Markdown.className)
        assertEquals("php", Language.Php.className)
        assertEquals("gradle", Language.Gradle.className)
    }

    @Test
    fun `getName() backward compat returns className`() {
        assertEquals(Language.Kotlin.className, Language.Kotlin.getName())
        assertNull(Language.AutoDetect.getName())
    }

    @Test
    fun `Custom language carries arbitrary class name`() {
        val custom = Language.Custom("my-custom-lang")
        assertEquals("my-custom-lang", custom.className)
        assertEquals("my-custom-lang", custom.getName())
    }

    @Test
    fun `companion @JvmField constants match their data objects`() {
        assertEquals(Language.AutoDetect, Language.AUTO_DETECT)
        assertEquals(Language.DisableHighlight, Language.DISABLE_HIGHLIGHT)
        assertEquals(Language.Kotlin, Language.KOTLIN)
        assertEquals(Language.Java, Language.JAVA)
        assertEquals(Language.Python, Language.PYTHON)
        assertEquals(Language.Go, Language.GO)
    }

    @Test
    fun `sealed interface has expected well-known entries`() {
        val knownLanguages: List<Language> = listOf(
            Language.AutoDetect, Language.DisableHighlight,
            Language.Kotlin, Language.Java, Language.Python,
            Language.JavaScript, Language.TypeScript, Language.Go,
            Language.Rust, Language.CPlusPlus, Language.CSharp,
            Language.Ruby, Language.Swift, Language.Scala,
            Language.Dart, Language.Html, Language.Xml,
            Language.Json, Language.Css, Language.Sql,
            Language.Bash, Language.Markdown, Language.Php,
            Language.Gradle
        )
        knownLanguages
            .filter { it != Language.AutoDetect }
            .forEach { lang ->
                assertNotNull(lang.className, "Expected non-null className for $lang")
            }
    }
}

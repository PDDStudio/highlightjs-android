package com.pddstudio.highlightjs.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class LanguageTest {

    @Test
    fun `AUTO_DETECT has null name`() {
        assertNull(Language.AUTO_DETECT.name2())
    }

    @Test
    fun `DISABLE_HIGHLIGHT has nohighlight name`() {
        assertEquals("nohighlight", Language.DISABLE_HIGHLIGHT.name2())
    }

    @Test
    fun `all languages except AUTO_DETECT have non-null names`() {
        Language.values()
            .filter { it != Language.AUTO_DETECT }
            .forEach { language ->
                assertNotNull(language.name2(), "Expected non-null name for ${language.name}")
            }
    }

    @Test
    fun `common language names are correct`() {
        assertEquals("kt", Language.KOTLIN.name2())
        assertEquals("java", Language.JAVA.name2())
        assertEquals("python", Language.PYTHON.name2())
        assertEquals("javascript", Language.JAVA_SCRIPT.name2())
        assertEquals("typescript", Language.TYPE_SCRIPT.name2())
        assertEquals("go", Language.GO.name2())
        assertEquals("rust", Language.RUST.name2())
        assertEquals("cpp", Language.C_PLUS_PLUS.name2())
        assertEquals("csharp", Language.C_SHARP.name2())
        assertEquals("ruby", Language.RUBY.name2())
        assertEquals("swift", Language.SWIFT.name2())
        assertEquals("scala", Language.SCALA.name2())
        assertEquals("dart", Language.DART.name2())
        assertEquals("html", Language.HTML.name2())
        assertEquals("xml", Language.XML.name2())
        assertEquals("json", Language.JSON.name2())
        assertEquals("css", Language.CSS.name2())
        assertEquals("sql", Language.SQL.name2())
        assertEquals("bash", Language.BASH.name2())
        assertEquals("md", Language.MARKDOWN.name2())
        assertEquals("php", Language.PHP.name2())
        assertEquals("gradle", Language.GRADLE.name2())
    }

    @Test
    fun `all enum values are accessible`() {
        val values = Language.values()
        assert(values.isNotEmpty())
        assert(values.size > 100) { "Expected Language enum to have more than 100 values, got ${values.size}" }
    }

    // Alias to avoid shadowing the Kotlin enum `name` property
    private fun Language.name2(): String? = this.getName()
}

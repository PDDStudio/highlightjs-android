package com.pddstudio.highlightjs.utils

import com.pddstudio.highlightjs.models.Language
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ExtensionUtilTest {

    @Test
    fun `unknown extension returns AUTO_DETECT`() {
        assertEquals(Language.AUTO_DETECT, ExtensionUtil.getLanguageByExtension("xyz_unknown"))
    }

    @Test
    fun `empty string returns AUTO_DETECT`() {
        assertEquals(Language.AUTO_DETECT, ExtensionUtil.getLanguageByExtension(""))
    }

    @Test
    fun `uppercase extension returns AUTO_DETECT due to case sensitivity`() {
        assertEquals(Language.AUTO_DETECT, ExtensionUtil.getLanguageByExtension("KT"))
        assertEquals(Language.AUTO_DETECT, ExtensionUtil.getLanguageByExtension("Java"))
        assertEquals(Language.AUTO_DETECT, ExtensionUtil.getLanguageByExtension("PY"))
    }

    @ParameterizedTest(name = "extension \"{0}\" maps to {1}")
    @CsvSource(
        "kt,       KOTLIN",
        "kts,      KOTLIN",
        "java,     JAVA",
        "py,       PYTHON",
        "js,       JAVA_SCRIPT",
        "ts,       TYPE_SCRIPT",
        "go,       GO",
        "rs,       RUST",
        "cpp,      C_PLUS_PLUS",
        "cs,       C_SHARP",
        "rb,       RUBY",
        "swift,    SWIFT",
        "dart,     DART",
        "scala,    SCALA",
        "html,     HTML",
        "xml,      XML",
        "json,     JSON",
        "css,      CSS",
        "sql,      SQL",
        "md,       MARKDOWN",
        "sh,       SHELL",
        "bash,     BASH",
        "php,      PHP",
        "c,        C_PLUS_PLUS",
        "h,        C_PLUS_PLUS",
        "hpp,      C_PLUS_PLUS",
        "gradle,   GRADLE",
        "groovy,   GROOVY",
        "yaml,     AUTO_DETECT",
        "txt,      PLAIN_TEXT"
    )
    fun `known extensions map to expected language`(extension: String, expectedName: String) {
        val expected = Language.valueOf(expectedName)
        assertEquals(expected, ExtensionUtil.getLanguageByExtension(extension))
    }

    @Test
    fun `multiple extensions for same language all resolve correctly`() {
        // C++ family
        assertEquals(Language.C_PLUS_PLUS, ExtensionUtil.getLanguageByExtension("cpp"))
        assertEquals(Language.C_PLUS_PLUS, ExtensionUtil.getLanguageByExtension("c"))
        assertEquals(Language.C_PLUS_PLUS, ExtensionUtil.getLanguageByExtension("h"))
        assertEquals(Language.C_PLUS_PLUS, ExtensionUtil.getLanguageByExtension("hpp"))

        // PHP versions
        assertEquals(Language.PHP, ExtensionUtil.getLanguageByExtension("php"))
        assertEquals(Language.PHP, ExtensionUtil.getLanguageByExtension("php3"))
        assertEquals(Language.PHP, ExtensionUtil.getLanguageByExtension("php5"))
        assertEquals(Language.PHP, ExtensionUtil.getLanguageByExtension("php7"))

        // Ruby
        assertEquals(Language.RUBY, ExtensionUtil.getLanguageByExtension("ruby"))
        assertEquals(Language.RUBY, ExtensionUtil.getLanguageByExtension("rb"))
        assertEquals(Language.RUBY, ExtensionUtil.getLanguageByExtension("gemspec"))

        // Kotlin
        assertEquals(Language.KOTLIN, ExtensionUtil.getLanguageByExtension("kt"))
        assertEquals(Language.KOTLIN, ExtensionUtil.getLanguageByExtension("kts"))
    }
}

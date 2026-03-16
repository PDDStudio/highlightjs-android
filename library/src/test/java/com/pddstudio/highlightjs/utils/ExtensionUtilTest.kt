package com.pddstudio.highlightjs.utils

import com.pddstudio.highlightjs.models.Language
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ExtensionUtilTest {

    @Test
    fun `unknown extension returns AUTO_DETECT`() {
        assertEquals(Language.AutoDetect, ExtensionUtil.getLanguageByExtension("xyz_unknown"))
    }

    @Test
    fun `empty string returns AUTO_DETECT`() {
        assertEquals(Language.AutoDetect, ExtensionUtil.getLanguageByExtension(""))
    }

    @Test
    fun `uppercase extension returns AUTO_DETECT due to case sensitivity`() {
        assertEquals(Language.AutoDetect, ExtensionUtil.getLanguageByExtension("KT"))
        assertEquals(Language.AutoDetect, ExtensionUtil.getLanguageByExtension("Java"))
        assertEquals(Language.AutoDetect, ExtensionUtil.getLanguageByExtension("PY"))
    }

    @ParameterizedTest(name = "extension \"{0}\" maps to expected language")
    @MethodSource("extensionMappings")
    fun `known extensions map to expected language`(pair: Pair<String, Language>) {
        val (extension, expected) = pair
        assertEquals(expected, ExtensionUtil.getLanguageByExtension(extension))
    }

    @Test
    fun `multiple extensions for same language all resolve correctly`() {
        // C++ family
        assertEquals(Language.CPlusPlus, ExtensionUtil.getLanguageByExtension("cpp"))
        assertEquals(Language.CPlusPlus, ExtensionUtil.getLanguageByExtension("c"))
        assertEquals(Language.CPlusPlus, ExtensionUtil.getLanguageByExtension("h"))
        assertEquals(Language.CPlusPlus, ExtensionUtil.getLanguageByExtension("hpp"))

        // PHP versions
        assertEquals(Language.Php, ExtensionUtil.getLanguageByExtension("php"))
        assertEquals(Language.Php, ExtensionUtil.getLanguageByExtension("php3"))
        assertEquals(Language.Php, ExtensionUtil.getLanguageByExtension("php5"))
        assertEquals(Language.Php, ExtensionUtil.getLanguageByExtension("php7"))

        // Ruby
        assertEquals(Language.Ruby, ExtensionUtil.getLanguageByExtension("ruby"))
        assertEquals(Language.Ruby, ExtensionUtil.getLanguageByExtension("rb"))
        assertEquals(Language.Ruby, ExtensionUtil.getLanguageByExtension("gemspec"))

        // Kotlin
        assertEquals(Language.Kotlin, ExtensionUtil.getLanguageByExtension("kt"))
        assertEquals(Language.Kotlin, ExtensionUtil.getLanguageByExtension("kts"))
    }

    companion object {
        @JvmStatic
        fun extensionMappings(): Stream<Pair<String, Language>> = Stream.of(
            "kt"      to Language.Kotlin,
            "kts"     to Language.Kotlin,
            "java"    to Language.Java,
            "py"      to Language.Python,
            "js"      to Language.JavaScript,
            "ts"      to Language.TypeScript,
            "go"      to Language.Go,
            "rs"      to Language.Rust,
            "cpp"     to Language.CPlusPlus,
            "cs"      to Language.CSharp,
            "rb"      to Language.Ruby,
            "swift"   to Language.Swift,
            "dart"    to Language.Dart,
            "scala"   to Language.Scala,
            "html"    to Language.Html,
            "xml"     to Language.Xml,
            "json"    to Language.Json,
            "css"     to Language.Css,
            "sql"     to Language.Sql,
            "md"      to Language.Markdown,
            "sh"      to Language.Shell,
            "bash"    to Language.Bash,
            "php"     to Language.Php,
            "c"       to Language.CPlusPlus,
            "h"       to Language.CPlusPlus,
            "hpp"     to Language.CPlusPlus,
            "gradle"  to Language.Gradle,
            "groovy"  to Language.Groovy,
            "yaml"    to Language.AutoDetect,
            "txt"     to Language.PlainText
        )
    }
}

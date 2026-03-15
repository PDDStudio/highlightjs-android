package com.pddstudio.highlightjs

import com.pddstudio.highlightjs.models.Language
import com.pddstudio.highlightjs.models.Theme
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HighlightJsDslTest {

    @Test
    fun `highlightJs DSL with empty block creates default state`() {
        val state = highlightJs { }
        assertEquals("", state.code)
        assertEquals(Language.AutoDetect, state.language)
        assertEquals(Theme.Default, state.theme)
        assertFalse(state.showLineNumbers)
    }

    @Test
    fun `highlightJs DSL sets code`() {
        val state = highlightJs {
            code = "fun main() {}"
        }
        assertEquals("fun main() {}", state.code)
    }

    @Test
    fun `highlightJs DSL sets language`() {
        val state = highlightJs {
            language = Language.Kotlin
        }
        assertEquals(Language.Kotlin, state.language)
    }

    @Test
    fun `highlightJs DSL sets theme`() {
        val state = highlightJs {
            theme = Theme.Monokai
        }
        assertEquals(Theme.Monokai, state.theme)
    }

    @Test
    fun `highlightJs DSL sets showLineNumbers`() {
        val state = highlightJs {
            showLineNumbers = true
        }
        assertTrue(state.showLineNumbers)
    }

    @Test
    fun `highlightJs DSL sets all fields together`() {
        val state = highlightJs {
            code = "println(\"hello\")"
            language = Language.Kotlin
            theme = Theme.AtomOneDark
            showLineNumbers = true
            zoomEnabled = true
        }
        assertEquals("println(\"hello\")", state.code)
        assertEquals(Language.Kotlin, state.language)
        assertEquals(Theme.AtomOneDark, state.theme)
        assertTrue(state.showLineNumbers)
        assertTrue(state.zoomEnabled)
    }

    @Test
    fun `highlightJs DSL accepts Custom language`() {
        val state = highlightJs {
            language = Language.Custom("my-lang")
        }
        assertEquals("my-lang", state.language.className)
        assertEquals("my-lang", state.language.getName())
    }

    @Test
    fun `highlightJs DSL accepts Custom theme`() {
        val state = highlightJs {
            theme = Theme.Custom("my-theme")
        }
        assertEquals("my-theme", state.theme.themeName)
    }
}

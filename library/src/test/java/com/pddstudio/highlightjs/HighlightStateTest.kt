package com.pddstudio.highlightjs

import com.pddstudio.highlightjs.models.Language
import com.pddstudio.highlightjs.models.Theme
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HighlightStateTest {

    @Test
    fun `default HighlightState has empty code`() {
        val state = HighlightState()
        assertEquals("", state.code)
    }

    @Test
    fun `default HighlightState uses AutoDetect language`() {
        val state = HighlightState()
        assertEquals(Language.AutoDetect, state.language)
    }

    @Test
    fun `default HighlightState uses Default theme`() {
        val state = HighlightState()
        assertEquals(Theme.Default, state.theme)
    }

    @Test
    fun `default HighlightState has showLineNumbers false`() {
        val state = HighlightState()
        assertFalse(state.showLineNumbers)
    }

    @Test
    fun `default HighlightState has zoomEnabled false`() {
        val state = HighlightState()
        assertFalse(state.zoomEnabled)
    }

    @Test
    fun `HighlightState stores provided code`() {
        val state = HighlightState(code = "fun main() {}")
        assertEquals("fun main() {}", state.code)
    }

    @Test
    fun `HighlightState stores provided language`() {
        val state = HighlightState(language = Language.Kotlin)
        assertEquals(Language.Kotlin, state.language)
    }

    @Test
    fun `HighlightState stores provided theme`() {
        val state = HighlightState(theme = Theme.AtomOneDark)
        assertEquals(Theme.AtomOneDark, state.theme)
    }

    @Test
    fun `HighlightState stores showLineNumbers true`() {
        val state = HighlightState(showLineNumbers = true)
        assertTrue(state.showLineNumbers)
    }

    @Test
    fun `HighlightState stores zoomEnabled true`() {
        val state = HighlightState(zoomEnabled = true)
        assertTrue(state.zoomEnabled)
    }

    @Test
    fun `HighlightState fields are mutable`() {
        val state = HighlightState(code = "hello")
        state.code = "world"
        assertEquals("world", state.code)
    }

    @Test
    fun `two HighlightState instances are distinct objects`() {
        val a = HighlightState(code = "x", language = Language.Kotlin, theme = Theme.Default)
        val b = HighlightState(code = "x", language = Language.Kotlin, theme = Theme.Default)
        assertNotSame(a, b)
        // Both carry the same field values
        assertEquals(a.code, b.code)
        assertEquals(a.language, b.language)
        assertEquals(a.theme, b.theme)
    }
}

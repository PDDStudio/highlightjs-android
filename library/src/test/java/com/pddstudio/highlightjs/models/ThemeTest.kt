package com.pddstudio.highlightjs.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ThemeTest {

    @Test
    fun `all themes have non-null names`() {
        Theme.values().forEach { theme ->
            assertNotNull(theme.getName(), "Expected non-null name for ${theme.name}")
        }
    }

    @Test
    fun `all theme names are non-blank`() {
        Theme.values().forEach { theme ->
            assertFalse(theme.getName().isBlank(), "Expected non-blank name for ${theme.name}")
        }
    }

    @Test
    fun `common theme names are correct`() {
        assertEquals("atom-one-dark", Theme.ATOM_ONE_DARK.getName())
        assertEquals("atom-one-light", Theme.ATOM_ONE_LIGHT.getName())
        assertEquals("default", Theme.DEFAULT.getName())
        assertEquals("dark", Theme.DARK.getName())
        assertEquals("monokai", Theme.MONOKAI.getName())
        assertEquals("github", Theme.GITHUB.getName())
        assertEquals("vs", Theme.VS.getName())
        assertEquals("vs2015", Theme.VS2015.getName())
        assertEquals("xcode", Theme.X_CODE.getName())
        assertEquals("androidstudio", Theme.ANDROID_STUDIO.getName())
        assertEquals("nord", Theme.NORD.getName())
        assertEquals("github-dark", Theme.GITHUB_DARK.getName())
    }

    @Test
    fun `all enum values are accessible`() {
        val values = Theme.values()
        assert(values.isNotEmpty())
        assert(values.size > 50) { "Expected Theme enum to have more than 50 values, got ${values.size}" }
    }

    @Test
    fun `theme name can be used as CSS file reference`() {
        // Theme names are used as CSS file prefixes, e.g. styles/dracula.css
        Theme.values().forEach { theme ->
            val cssRef = "styles/${theme.getName()}.css"
            assertFalse(cssRef.contains(" "), "CSS ref for ${theme.name} must not contain spaces: $cssRef")
        }
    }
}

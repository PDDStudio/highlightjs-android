package com.pddstudio.highlightjs.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ThemeTest {

    @Test
    fun `common theme names are correct`() {
        assertEquals("atom-one-dark", Theme.AtomOneDark.themeName)
        assertEquals("atom-one-light", Theme.AtomOneLight.themeName)
        assertEquals("default", Theme.Default.themeName)
        assertEquals("dark", Theme.Dark.themeName)
        assertEquals("monokai", Theme.Monokai.themeName)
        assertEquals("github", Theme.Github.themeName)
        assertEquals("vs", Theme.Vs.themeName)
        assertEquals("vs2015", Theme.Vs2015.themeName)
        assertEquals("xcode", Theme.XCode.themeName)
        assertEquals("androidstudio", Theme.AndroidStudio.themeName)
        assertEquals("nord", Theme.Nord.themeName)
        assertEquals("github-dark", Theme.GithubDark.themeName)
    }

    @Test
    fun `getName() backward compat returns themeName`() {
        assertEquals(Theme.Monokai.themeName, Theme.Monokai.getName())
        assertEquals("default", Theme.Default.getName())
    }

    @Test
    fun `Custom theme carries arbitrary theme name`() {
        val custom = Theme.Custom("my-custom-theme")
        assertEquals("my-custom-theme", custom.themeName)
        assertEquals("my-custom-theme", custom.getName())
    }

    @Test
    fun `companion @JvmField constants match their data objects`() {
        assertEquals(Theme.Default, Theme.DEFAULT)
        assertEquals(Theme.AtomOneDark, Theme.ATOM_ONE_DARK)
        assertEquals(Theme.Monokai, Theme.MONOKAI)
        assertEquals(Theme.Github, Theme.GITHUB)
        assertEquals(Theme.Nord, Theme.NORD)
        assertEquals(Theme.GithubDark, Theme.GITHUB_DARK)
    }

    @Test
    fun `theme names are non-blank`() {
        val knownThemes: List<Theme> = listOf(
            Theme.AtomOneDark, Theme.AtomOneLight, Theme.Default,
            Theme.Dark, Theme.Monokai, Theme.MonokaiSublime,
            Theme.Github, Theme.GithubDark, Theme.GithubDarkDimmed,
            Theme.Nord, Theme.Obsidian, Theme.NightOwl,
            Theme.Vs, Theme.Vs2015, Theme.XCode,
            Theme.AndroidStudio, Theme.IntellijLight, Theme.Idea
        )
        knownThemes.forEach { theme ->
            assertFalse(theme.themeName.isBlank(), "Expected non-blank themeName for $theme")
            assertNotNull(theme.themeName, "Expected non-null themeName for $theme")
        }
    }

    @Test
    fun `theme name can be used as CSS file reference`() {
        val knownThemes: List<Theme> = listOf(
            Theme.AtomOneDark, Theme.Default, Theme.Monokai,
            Theme.Github, Theme.Nord, Theme.NightOwl, Theme.Vs2015
        )
        knownThemes.forEach { theme ->
            val cssRef = "styles/${theme.themeName}.css"
            assertFalse(cssRef.contains(" "), "CSS ref must not contain spaces: $cssRef")
        }
    }
}

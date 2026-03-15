package com.pddstudio.highlightjs.utils

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SourceUtilsTest {

    @Test
    fun `generated content contains DOCTYPE and html tags`() {
        val result = SourceUtils.generateContent("val x = 1", "atom-one-dark", "kotlin", false, false)

        assertTrue(result.contains("<!DOCTYPE html>"))
        assertTrue(result.contains("<html>"))
        assertTrue(result.contains("</html>"))
        assertTrue(result.contains("<head>"))
        assertTrue(result.contains("</head>"))
        assertTrue(result.contains("<body"))
        assertTrue(result.contains("</body>"))
    }

    @Test
    fun `style link uses provided theme name`() {
        val result = SourceUtils.generateContent("code", "dracula", "kotlin", false, false)

        assertTrue(result.contains("styles/dracula.css"))
    }

    @Test
    fun `language class is applied to code block when language is set`() {
        val result = SourceUtils.generateContent("val x = 1", "default", "kotlin", false, false)

        assertTrue(result.contains("<code class=\"kotlin\">"))
    }

    @Test
    fun `no language class when language is null`() {
        val result = SourceUtils.generateContent("plain code", "default", null, false, false)

        assertTrue(result.contains("<pre><code>"))
        assertFalse(result.contains("<code class="))
    }

    @Test
    fun `zoom disabled adds viewport meta tag`() {
        val resultNoZoom = SourceUtils.generateContent("code", "default", null, false, false)
        val resultZoom = SourceUtils.generateContent("code", "default", null, true, false)

        assertTrue(resultNoZoom.contains("maximum-scale=1.0"))
        assertFalse(resultZoom.contains("maximum-scale=1.0"))
    }

    @Test
    fun `line numbers enabled adds line number scripts and styling`() {
        val result = SourceUtils.generateContent("code", "default", null, false, true)

        assertTrue(result.contains("highlightjs-line-numbers.min.js"))
        assertTrue(result.contains("hljs.initLineNumbersOnLoad()"))
        assertTrue(result.contains(".hljs-line-numbers"))
    }

    @Test
    fun `line numbers disabled omits line number scripts`() {
        val result = SourceUtils.generateContent("code", "default", null, false, false)

        assertFalse(result.contains("highlightjs-line-numbers.min.js"))
        assertFalse(result.contains("initLineNumbersOnLoad"))
    }

    @Test
    fun `less-than sign is HTML-escaped in source`() {
        val result = SourceUtils.generateContent("<input>", "default", null, false, false)

        assertTrue(result.contains("&lt;input&gt;"))
        // The template itself has structural HTML tags; verify the source-specific tag is escaped
        assertFalse(result.contains("<input>"))
    }

    @Test
    fun `greater-than sign is HTML-escaped in source`() {
        val result = SourceUtils.generateContent("a > b && b < c", "default", null, false, false)

        assertTrue(result.contains("&gt;"))
        assertTrue(result.contains("&lt;"))
    }

    @Test
    fun `source without angle brackets is not altered`() {
        val source = "val x = listOf(1, 2, 3)"
        val result = SourceUtils.generateContent(source, "default", null, false, false)

        assertTrue(result.contains(source))
    }

    @Test
    fun `highlight pack script is always included`() {
        val result = SourceUtils.generateContent("code", "default", null, false, false)

        assertTrue(result.contains("highlight.pack.js"))
        assertTrue(result.contains("hljs.initHighlightingOnLoad()"))
    }
}

package com.pddstudio.highlightjs.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class FileUtilsTest {

    @TempDir
    lateinit var tempDir: File

    @Test
    fun `loadSourceFromFile throws NullPointerException for null input`() {
        // Bug: function signature accepts nullable File but implementation does not guard against null.
        // This test documents current behavior; the function should ideally return null for null input.
        assertThrows(NullPointerException::class.java) {
            FileUtils.loadSourceFromFile(null)
        }
    }

    @Test
    fun `loadSourceFromFile returns null for nonexistent file`() {
        val nonExistent = File(tempDir, "does_not_exist.txt")
        val result = FileUtils.loadSourceFromFile(nonExistent)
        assertNull(result)
    }

    @Test
    fun `loadSourceFromFile reads single-line file content`() {
        val file = File(tempDir, "single.txt").apply { writeText("hello world") }
        val result = FileUtils.loadSourceFromFile(file)
        assertNotNull(result)
        assertEquals("hello world\n", result)
    }

    @Test
    fun `loadSourceFromFile reads multi-line file content`() {
        val content = "line one\nline two\nline three"
        val file = File(tempDir, "multi.txt").apply { writeText(content) }
        val result = FileUtils.loadSourceFromFile(file)
        assertNotNull(result)
        assertEquals("line one\nline two\nline three\n", result)
    }

    @Test
    fun `loadSourceFromFile preserves code content`() {
        val code = """
            fun main() {
                println("Hello, World!")
            }
        """.trimIndent()
        val file = File(tempDir, "Main.kt").apply { writeText(code) }
        val result = FileUtils.loadSourceFromFile(file)
        assertNotNull(result)
        assert(result!!.contains("fun main()"))
        assert(result.contains("println"))
    }

    @Test
    fun `loadSourceFromFile reads empty file as empty string`() {
        val file = File(tempDir, "empty.txt").apply { createNewFile() }
        val result = FileUtils.loadSourceFromFile(file)
        assertNotNull(result)
        assertEquals("", result)
    }
}

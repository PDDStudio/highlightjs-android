package com.pddstudio.highlightjs.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class LanguageSealedTest {

    @Test
    fun `Custom language returns provided name`() {
        val custom = Language.Custom("my-lang")
        assertEquals("my-lang", custom.getName())
    }

    @Test
    fun `Custom languages with same name are equal`() {
        assertEquals(Language.Custom("kotlin"), Language.Custom("kotlin"))
    }

    @Test
    fun `Custom languages with different names are not equal`() {
        assertNotEquals(Language.Custom("kotlin"), Language.Custom("java"))
    }

    @Test
    fun `AUTO_DETECT is accessible as Language type`() {
        val lang: Language = Language.AUTO_DETECT
        assertNull(lang.getName())
    }

    @Test
    fun `KOTLIN companion entry matches Kotlin language`() {
        assertEquals("kt", Language.KOTLIN.getName())
    }

    @Test
    fun `Language instances can be compared by identity`() {
        assertEquals(Language.AUTO_DETECT, Language.AUTO_DETECT)
        assertEquals(Language.KOTLIN, Language.KOTLIN)
    }

    @Test
    fun `Custom is a distinct Language from all predefined entries`() {
        val custom = Language.Custom("kt")
        // Custom("kt") is a different type than Language.KOTLIN even with same class name
        assertNotEquals(custom, Language.KOTLIN)
    }
}

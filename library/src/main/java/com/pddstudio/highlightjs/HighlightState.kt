package com.pddstudio.highlightjs

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.pddstudio.highlightjs.models.Language
import com.pddstudio.highlightjs.models.Theme

/**
 * Stable, observable state holder for [HighlightJsComposable].
 *
 * All properties are backed by [mutableStateOf], so mutations automatically
 * trigger recomposition of any composable reading them.
 *
 * Create with [rememberHighlightState] inside a composable, or with the
 * [highlightJs] DSL builder outside of composition.
 *
 * @param code        Source code to display.
 * @param language    Syntax language; defaults to [Language.AutoDetect].
 * @param theme       Visual theme; defaults to [Theme.Default].
 * @param showLineNumbers Whether to render line numbers.
 * @param zoomEnabled    Whether to support pinch-zoom in the underlying WebView.
 */
@Stable
class HighlightState(
    code: String = "",
    language: Language = Language.AutoDetect,
    theme: Theme = Theme.Default,
    showLineNumbers: Boolean = false,
    zoomEnabled: Boolean = false
) {
    var code: String by mutableStateOf(code)
    var language: Language by mutableStateOf(language)
    var theme: Theme by mutableStateOf(theme)
    var showLineNumbers: Boolean by mutableStateOf(showLineNumbers)
    var zoomEnabled: Boolean by mutableStateOf(zoomEnabled)
}

/**
 * Kotlin DSL builder for [HighlightState].
 *
 * Usage:
 * ```kotlin
 * val state = highlightJs {
 *     code = "fun main() = println(\"Hello\")"
 *     language = Language.Kotlin
 *     theme = Theme.AtomOneDark
 *     showLineNumbers = true
 * }
 * ```
 */
fun highlightJs(block: HighlightState.() -> Unit): HighlightState =
    HighlightState().apply(block)

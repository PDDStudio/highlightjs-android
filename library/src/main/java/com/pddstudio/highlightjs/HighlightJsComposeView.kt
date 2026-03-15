package com.pddstudio.highlightjs

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import com.pddstudio.highlightjs.models.Language
import com.pddstudio.highlightjs.models.Theme

/**
 * A traditional [android.view.View] subclass that hosts the [HighlightJsView] Compose composable.
 *
 * Use this class when you need to embed the highlight.js viewer in an XML layout or
 * a View-based project that cannot yet adopt full Jetpack Compose navigation. Under the
 * hood it extends [AbstractComposeView], which means no extra boilerplate is required to
 * initialise Compose.
 *
 * ```xml
 * <com.pddstudio.highlightjs.HighlightJsComposeView
 *     android:id="@+id/highlightView"
 *     android:layout_width="match_parent"
 *     android:layout_height="match_parent" />
 * ```
 *
 * ```kotlin
 * binding.highlightView.state.apply {
 *     code     = sourceCode
 *     language = Language.Kotlin
 *     theme    = Theme.AtomOneDark
 * }
 * ```
 */
class HighlightJsComposeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    /**
     * Observable state that drives the rendered output.
     *
     * Mutating any property on this object triggers an automatic recomposition.
     */
    val state: HighlightState = HighlightState()

    // ── Convenience setters for Java / XML-attribute driven usage ─────────────

    /** Sets the source code to display. */
    fun setCode(code: String) { state.code = code }

    /** Sets the highlight.js language for syntax colouring. */
    fun setLanguage(language: Language) { state.language = language }

    /** Sets the visual theme. */
    fun setTheme(theme: Theme) { state.theme = theme }

    /** Enables or disables line number rendering. */
    fun setShowLineNumbers(show: Boolean) { state.showLineNumbers = show }

    /** Enables or disables pinch-zoom support in the underlying WebView. */
    fun setZoomEnabled(enabled: Boolean) { state.zoomEnabled = enabled }

    @Composable
    override fun Content() {
        HighlightJsView(state = state)
    }
}

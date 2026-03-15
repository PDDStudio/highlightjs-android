package com.pddstudio.highlightjs

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.pddstudio.highlightjs.utils.SourceUtils

/**
 * Composable that renders syntax-highlighted code using highlight.js in a [WebView].
 *
 * Reacts to changes in [state] and updates the WebView content automatically via
 * [LaunchedEffect]. The WebView is cleaned up via [DisposableEffect] when this
 * composable leaves the composition.
 *
 * Example:
 * ```kotlin
 * val state = rememberHighlightState(
 *     code = "fun main() = println(\"Hello\")",
 *     language = Language.Kotlin,
 *     theme = Theme.AtomOneDark
 * )
 * HighlightJsView(state = state, modifier = Modifier.fillMaxSize())
 * ```
 *
 * @param state   Observable state describing what to render.
 * @param modifier Compose [Modifier] applied to the underlying [AndroidView].
 */
@Composable
fun HighlightJsView(
    state: HighlightState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val webView = remember(context) { createHighlightWebView(context) }

    // Re-render whenever any state field changes
    LaunchedEffect(state.code, state.language, state.theme, state.showLineNumbers, state.zoomEnabled) {
        renderContent(webView, state)
    }

    AndroidView(
        factory = { webView },
        modifier = modifier
    )

    DisposableEffect(webView) {
        onDispose {
            webView.stopLoading()
            webView.destroy()
        }
    }
}

/**
 * Creates and remembers a [HighlightState] that survives recompositions.
 *
 * @param code            Initial source code.
 * @param language        Initial language for highlighting.
 * @param theme           Initial visual theme.
 * @param showLineNumbers Whether to display line numbers.
 * @param zoomEnabled     Whether to enable pinch-zoom.
 */
@Composable
fun rememberHighlightState(
    code: String = "",
    language: com.pddstudio.highlightjs.models.Language = com.pddstudio.highlightjs.models.Language.AutoDetect,
    theme: com.pddstudio.highlightjs.models.Theme = com.pddstudio.highlightjs.models.Theme.Default,
    showLineNumbers: Boolean = false,
    zoomEnabled: Boolean = false
): HighlightState = remember {
    HighlightState(
        code = code,
        language = language,
        theme = theme,
        showLineNumbers = showLineNumbers,
        zoomEnabled = zoomEnabled
    )
}

// ── Internal helpers ─────────────────────────────────────────────────────────

@SuppressLint("SetJavaScriptEnabled")
private fun createHighlightWebView(context: android.content.Context): WebView {
    return WebView(context).apply {
        loadUrl("about:blank")
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
        isScrollbarFadingEnabled = true

        val ws = settings
        ws.javaScriptEnabled = true
        ws.loadWithOverviewMode = true
        ws.useWideViewPort = true
        ws.setSupportZoom(true)
        ws.builtInZoomControls = true
        ws.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        ws.cacheMode = WebSettings.LOAD_NO_CACHE
        ws.domStorageEnabled = true
        ws.displayZoomControls = false

        setLayerType(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) View.LAYER_TYPE_HARDWARE
            else View.LAYER_TYPE_SOFTWARE,
            null
        )
    }
}

private fun renderContent(webView: WebView, state: HighlightState) {
    val code = state.code
    if (code.isBlank()) {
        Log.w("HighlightJsView", "Source code is empty; nothing to render.")
        return
    }
    webView.settings.setSupportZoom(state.zoomEnabled)
    val page = SourceUtils.generateContent(
        source = code,
        style = state.theme.themeName,
        language = state.language.className,
        supportZoom = state.zoomEnabled,
        showLineNumbers = state.showLineNumbers
    )
    webView.loadDataWithBaseURL("file:///android_asset/", page, "text/html", "utf-8", null)
}

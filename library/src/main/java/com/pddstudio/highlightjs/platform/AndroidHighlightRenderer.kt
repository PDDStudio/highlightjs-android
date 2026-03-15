package com.pddstudio.highlightjs.platform

import android.annotation.SuppressLint
import android.webkit.WebView
import com.pddstudio.highlightjs.utils.SourceUtils

/**
 * Android `actual` implementation of [PlatformHighlightRenderer].
 *
 * ## Compose Multiplatform Preparation
 *
 * When migrated to KMP, move this file to `androidMain` and annotate with `@Suppress("ACTUAL_WITHOUT_EXPECT")`.
 * The class declaration becomes:
 *
 * ```kotlin
 * // androidMain
 * actual class AndroidHighlightRenderer(private val webView: WebView) : PlatformHighlightRenderer { ... }
 * ```
 *
 * Platform-specific assets (highlight.pack.js, CSS files) live in `src/androidMain/assets/`
 * and are loaded via `file:///android_asset/` — mark that path as `actual` when splitting
 * source sets.
 */
@SuppressLint("SetJavaScriptEnabled")
class AndroidHighlightRenderer(private val webView: WebView) : PlatformHighlightRenderer {

    override fun render(
        source: String,
        style: String,
        language: String?,
        supportZoom: Boolean,
        showLineNumbers: Boolean
    ) {
        val page = SourceUtils.generateContent(
            source = source,
            style = style,
            language = language,
            supportZoom = supportZoom,
            showLineNumbers = showLineNumbers
        )
        webView.loadDataWithBaseURL("file:///android_asset/", page, "text/html", "utf-8", null)
    }

    override fun destroy() {
        webView.stopLoading()
        webView.destroy()
    }
}

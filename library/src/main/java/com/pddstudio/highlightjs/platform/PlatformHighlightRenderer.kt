package com.pddstudio.highlightjs.platform

/**
 * Platform abstraction for rendering highlight.js content.
 *
 * ## Compose Multiplatform Preparation
 *
 * This interface represents the `expect` boundary for platform-specific rendering.
 * When the library is migrated to the Kotlin Multiplatform plugin, this should become:
 *
 * ```kotlin
 * // commonMain
 * expect interface PlatformHighlightRenderer {
 *     fun render(source: String, style: String, language: String?, supportZoom: Boolean, showLineNumbers: Boolean)
 *     fun destroy()
 * }
 * ```
 *
 * The Android `actual` implementation wraps a [android.webkit.WebView].
 *
 * Non-Android targets (desktop, iOS) would provide their own `actual` that uses
 * a different rendering backend (e.g. KCEF on desktop, WKWebView on iOS).
 */
interface PlatformHighlightRenderer {
    /**
     * Render the given [source] code with [style] theme and optional [language] hint.
     *
     * [actual on androidMain]: delegates to `WebView.loadDataWithBaseURL`.
     */
    fun render(
        source: String,
        style: String,
        language: String?,
        supportZoom: Boolean,
        showLineNumbers: Boolean
    )

    /**
     * Release any resources held by the renderer.
     *
     * [actual on androidMain]: calls `WebView.destroy()`.
     */
    fun destroy()
}

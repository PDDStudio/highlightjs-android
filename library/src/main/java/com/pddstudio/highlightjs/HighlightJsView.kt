package com.pddstudio.highlightjs

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import com.pddstudio.highlightjs.models.Language
import com.pddstudio.highlightjs.models.Theme
import com.pddstudio.highlightjs.utils.ExtensionUtil
import com.pddstudio.highlightjs.utils.FileUtils
import com.pddstudio.highlightjs.utils.SourceUtils
import java.io.File
import java.net.URL

/**
 * Legacy View-based highlight.js component.
 *
 * This class is retained for backward compatibility with XML layouts that embed it directly.
 * For new code, prefer [HighlightJsComposeView] (View-based host wrapping the Compose
 * component) or the [HighlightJsView] Compose composable together with [rememberHighlightState].
 *
 * The Java Builder API that previously lived alongside this class has been removed — use
 * [HighlightState] and the [highlightJs] DSL builder instead.
 */
class HighlightJsView : WebView, FileUtils.Callback {

    private var language: Language = Language.AutoDetect
    private var theme: Theme = Theme.Default
    private var content: String? = null
    private var zoomSupport: Boolean = false
    private var showLineNumbers: Boolean = false

    private var onLanguageChangedListener: OnLanguageChangedListener? = null
    private var onThemeChangedListener: OnThemeChangedListener? = null
    private var onContentChangedListener: OnContentChangedListener? = null

    interface OnLanguageChangedListener {
        fun onLanguageChanged(language: Language)
    }

    interface OnThemeChangedListener {
        fun onThemeChanged(theme: Theme)
    }

    interface OnContentChangedListener {
        fun onContentChanged()
    }

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initView(context)
    }

    override fun onDataLoaded(success: Boolean, source: String?) {
        if (success) setSource(source!!)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView(context: Context) {
        loadUrl("about:blank")

        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
        isScrollbarFadingEnabled = true

        val settings = this.settings
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.domStorageEnabled = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setLayerType(View.LAYER_TYPE_HARDWARE, null)
        } else {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }

        settings.displayZoomControls = false

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        }
    }

    private fun changeZoomSettings(enable: Boolean) {
        this.zoomSupport = enable
        settings.setSupportZoom(enable)
    }

    fun setOnLanguageChangedListener(onLanguageChangedListener: OnLanguageChangedListener) {
        this.onLanguageChangedListener = onLanguageChangedListener
    }

    fun setOnThemeChangedListener(onThemeChangedListener: OnThemeChangedListener) {
        this.onThemeChangedListener = onThemeChangedListener
    }

    fun setOnContentChangedListener(onContentChangedListener: OnContentChangedListener) {
        this.onContentChangedListener = onContentChangedListener
    }

    fun setHighlightLanguage(language: Language) {
        this.language = language
        onLanguageChangedListener?.onLanguageChanged(language)
    }

    fun setLanguageByFileExtension(extension: String) {
        setHighlightLanguage(ExtensionUtil.getLanguageByExtension(extension.lowercase()))
    }

    fun setTheme(theme: Theme) {
        this.theme = theme
        onThemeChangedListener?.onThemeChanged(theme)
    }

    fun getHighlightLanguage(): Language = language

    fun getTheme(): Theme = theme

    fun setSource(source: String) {
        if (source.isNotEmpty()) {
            this.content = source
            val page = SourceUtils.generateContent(
                source = source,
                style = theme.themeName,
                language = language.className,
                supportZoom = zoomSupport,
                showLineNumbers = showLineNumbers
            )

            val start = System.currentTimeMillis()
            try {
                loadDataWithBaseURL("file:///android_asset/", page, "text/html", "utf-8", null)
            } finally {
                println("로딩 : " + (System.currentTimeMillis() - start))
            }
            onContentChangedListener?.onContentChanged()
        } else {
            Log.e(javaClass.simpleName, "Source can't be null or empty.")
        }
    }

    fun setSource(source: File) {
        val encSource = FileUtils.loadSourceFromFile(source)
        if (encSource == null) {
            Log.e(javaClass.simpleName, "Unable to encode file: " + source.absolutePath)
        } else {
            setSource(encSource)
        }
    }

    fun setSource(url: URL) {
        FileUtils.loadSourceFromUrl(this, url)
    }

    fun refresh() {
        content?.let {
            loadUrl("about:blank")
            setSource(it)
        }
    }

    fun setZoomSupportEnabled(supportZoom: Boolean) {
        changeZoomSettings(supportZoom)
    }

    fun setShowLineNumbers(showLineNumbers: Boolean) {
        this.showLineNumbers = showLineNumbers
    }
}

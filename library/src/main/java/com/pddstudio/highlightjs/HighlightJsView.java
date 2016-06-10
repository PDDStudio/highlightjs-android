package com.pddstudio.highlightjs;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;
import com.pddstudio.highlightjs.utils.FileUtils;
import com.pddstudio.highlightjs.utils.SourceUtils;

import java.io.File;
import java.net.URL;

/**
 * This Class was created by Patrick J
 * on 09.06.16. For more Details and Licensing
 * have a look at the README.md
 */

public class HighlightJsView extends WebView implements FileUtils.Callback {

    //local variables to store language and theme
    private Language language = Language.AUTO_DETECT;
    private Theme theme = Theme.DEFAULT;
    private String content;

    //local variables to register callbacks
    private OnLanguageChangedListener onLanguageChangedListener;
    private OnThemeChangedListener onThemeChangedListener;
    private OnContentChangedListener onContentChangedListener;

    @Override
    public void onDataLoaded(boolean success, String source) {
        if(success) setSource(source);
    }

    public interface OnLanguageChangedListener {
        void onLanguageChanged(@NonNull Language language);
    }

    public interface OnThemeChangedListener {
        void onThemeChanged(@NonNull Theme theme);
    }

    public interface OnContentChangedListener {
        void onContentChanged();
    }

    public HighlightJsView(Context context) {
        super(context);
        initView(context);
    }

    public HighlightJsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HighlightJsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HighlightJsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        //make sure the view is blank
        loadUrl("about:blank");
        //set the settings for the view
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        //disable zoom controls on +Honeycomb devices
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) settings.setDisplayZoomControls(false);
        //to remove padding and margin
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    }

    /**
     * Attach a callback to receive calls when the Highlight-Language has changed
     * @param onLanguageChangedListener
     */
    public void setOnLanguageChangedListener(OnLanguageChangedListener onLanguageChangedListener) {
        this.onLanguageChangedListener = onLanguageChangedListener;
    }

    /**
     * Attach a callback to receive calls when the Theme has changed
     * @param onThemeChangedListener
     */
    public void setOnThemeChangedListener(OnThemeChangedListener onThemeChangedListener) {
        this.onThemeChangedListener = onThemeChangedListener;
    }

    /**
     * Attach a callback to receive calls when the content has changed
     * @param onContentChangedListener
     */
    public void setOnContentChangedListener(OnContentChangedListener onContentChangedListener) {
        this.onContentChangedListener = onContentChangedListener;
    }

    /**
     * Set the desired language to highlight the given source.
     * Default: {@link Language#AUTO_DETECT}
     * @param language
     */
    public void setHighlightLanguage(Language language) {
        this.language = language;
        //notify the callback (if set)
        if(onLanguageChangedListener != null) onLanguageChangedListener.onLanguageChanged(language);
    }

    /**
     * Set the desired theme to highlight the given source.
     * Default: {@link Theme#DEFAULT}
     * @param theme
     */
    public void setTheme(Theme theme) {
        this.theme = theme;
        //notify the callback (if set)
        if(onThemeChangedListener != null) onThemeChangedListener.onThemeChanged(theme);
    }

    /**
     * Receive the {@link Language} which is currently highlighted.
     * @return The {@link Language} which is currently highlighted.
     */
    public Language getHighlightLanguage() {
        return language;
    }

    /**
     * Receive the {@link Theme} which is currently set.
     * @return The {@link Theme} which is currently set.
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * Set the source to highlight from a String
     * @param source - The source as {@linkplain String}
     */
    public void setSource(String source) {
        if(source != null && !source.isEmpty()) {
            //generate and load the content
            this.content = source;
            String page = SourceUtils.generateContent(source, theme.getName(), language.getName());
            loadDataWithBaseURL("file:///android_asset/", page, "text/html", "utf-8", null);
            //notify the callback (if set)
            if(onContentChangedListener != null) onContentChangedListener.onContentChanged();
        } else Log.e(getClass().getSimpleName(), "Source can't be null or empty.");
    }

    /**
     * Set the source to highlight from a File
     * @param source - The source as {@linkplain File}
     */
    public void setSource(File source) {
        //try to encode and set the source
        String encSource = FileUtils.loadSourceFromFile(source);
        if(encSource == null) {
            Log.e(getClass().getSimpleName(), "Unable to encode file: " + source.getAbsolutePath());
        } else setSource(encSource);
    }

    /**
     * Set the source to highlight from a remote URL
     * @param url - The source as {@linkplain URL}
     */
    public void setSource(URL url) {
        //try to encode and set the source
        FileUtils.loadSourceFromUrl(this, url);
    }

    /**
     * Refresh the View.
     * Needs to be called when setting a new language, theme or source.
     */
    public void refresh() {
        if(content != null) {
            loadUrl("about:blank");
            setSource(content);
        }
    }


}

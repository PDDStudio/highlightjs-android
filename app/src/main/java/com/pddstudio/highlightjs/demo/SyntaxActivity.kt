package com.pddstudio.highlightjs.demo

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pddstudio.highlightjs.HighlightJsView
import com.pddstudio.highlightjs.demo.utils.FileObject
import com.pddstudio.highlightjs.demo.utils.ThemeChangerDialog
import com.pddstudio.highlightjs.models.Language
import com.pddstudio.highlightjs.models.Theme
import java.util.Random

class SyntaxActivity : AppCompatActivity(),
    SwipeRefreshLayout.OnRefreshListener,
    HighlightJsView.OnThemeChangedListener,
    ThemeChangerDialog.ThemeChangeListener,
    HighlightJsView.OnLanguageChangedListener {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var highlightJsView: HighlightJsView

    private lateinit var themeChangerDialog: ThemeChangerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syntax)
        val fileObject = intent.extras?.getSerializable("fileObject") as? FileObject
        actionBar?.let {
            it.title = fileObject?.getFileName()
            it.setDisplayHomeAsUpEnabled(true)
        }
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = fileObject?.getFileName()
        }
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener(this)
        highlightJsView = findViewById(R.id.highlight_view)
        highlightJsView.setOnThemeChangedListener(this)
        highlightJsView.setTheme(Theme.ANDROID_STUDIO)
        val name = fileObject?.getFileName()?.split(".")
        highlightJsView.setOnLanguageChangedListener(this)
        if (!name.isNullOrEmpty()) {
            highlightJsView.setLanguageByFileExtension(name[name.size - 1])
        }
        fileObject?.getUrl()?.let { highlightJsView.setSource(it) }
    }

    override fun onResume() {
        super.onResume()
        themeChangerDialog = ThemeChangerDialog(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MenuInflater(this).inflate(R.menu.menu_theme_switch, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> onBackPressed()
            R.id.menu_switch_theme -> themeChangerDialog.show(this)
            R.id.menu_check_line_numbers -> {
                item.isChecked = !item.isChecked
                onShowLineNumbersToggled(item.isChecked)
            }
            R.id.menu_check_zoom -> {
                item.isChecked = !item.isChecked
                onZoomSupportToggled(item.isChecked)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onShowLineNumbersToggled(enableLineNumbers: Boolean) {
        highlightJsView.setShowLineNumbers(enableLineNumbers)
        highlightJsView.refresh()
    }

    private fun onZoomSupportToggled(enableZooming: Boolean) {
        highlightJsView.setZoomSupportEnabled(enableZooming)
        highlightJsView.refresh()
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing = true
        highlightJsView.setTheme(getRandom(*Theme.values()))
        highlightJsView.refresh()
    }

    private fun <T> getRandom(vararg items: T): T {
        return items[Random().nextInt(items.size)]
    }

    override fun onThemeChanged(theme: Theme) {
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(this, "Theme: " + theme.themeName, Toast.LENGTH_SHORT).show()
    }

    override fun onChangeTheme(theme: Theme) {
        highlightJsView.setTheme(theme)
        highlightJsView.refresh()
    }

    override fun onLanguageChanged(language: Language) {
        Toast.makeText(this, language.getName(), Toast.LENGTH_LONG).show()
    }
}

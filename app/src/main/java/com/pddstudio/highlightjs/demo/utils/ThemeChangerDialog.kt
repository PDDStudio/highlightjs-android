package com.pddstudio.highlightjs.demo.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.pddstudio.highlightjs.demo.R
import com.pddstudio.highlightjs.models.Theme

/**
 * Created by pddstudio on 09/01/2017.
 */

class ThemeChangerDialog(private val changeListener: ThemeChangeListener) : DialogInterface.OnClickListener {

    interface ThemeChangeListener {
        fun onChangeTheme(theme: Theme)
    }

    private val styleMap: MutableMap<CharSequence, Theme> = HashMap()
    private var dialog: AlertDialog? = null

    init {
        initStyles()
    }

    private fun initStyles() {
        for (style in Theme.values()) {
            styleMap[style.getName()] = style
        }
    }

    fun show(context: Context) {
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
        dialog = null
        buildDialog(context)
        dialog?.show()
    }

    private fun buildDialog(context: Context) {
        dialog = AlertDialog.Builder(context)
            .setSingleChoiceItems(buildThemeList(), -1, this)
            .setTitle(R.string.dialog_theme_selection_title)
            .create()
    }

    private fun buildThemeList(): Array<CharSequence> {
        val themeNames = styleMap.keys
        return themeNames.toTypedArray()
    }

    private fun findThemeWithMatchingName(themeName: String?): Theme {
        if (themeName.isNullOrEmpty()) return Theme.DEFAULT
        for (theme in Theme.values()) {
            if (theme.getName().equals(themeName, ignoreCase = true)) return theme
        }
        return Theme.DEFAULT
    }

    private fun getSelectedTheme(selectedIndex: Int): Theme {
        val themes = buildThemeList()
        val themeName = if (selectedIndex > 0 && selectedIndex < themes.size) {
            themes[selectedIndex].toString()
        } else null
        return findThemeWithMatchingName(themeName)
    }

    override fun onClick(dialogInterface: DialogInterface, i: Int) {
        dialogInterface.dismiss()
        val newTheme = getSelectedTheme(i)
        changeListener.onChangeTheme(newTheme)
    }
}

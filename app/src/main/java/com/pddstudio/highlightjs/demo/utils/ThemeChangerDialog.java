package com.pddstudio.highlightjs.demo.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.pddstudio.highlightjs.demo.R;
import com.pddstudio.highlightjs.models.Theme;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by pddstudio on 09/01/2017.
 */

public class ThemeChangerDialog implements DialogInterface.OnClickListener {

	public interface ThemeChangeListener {
		void onChangeTheme(@NonNull Theme theme);
	}

	private final ThemeChangeListener changeListener;
	private final Map<CharSequence, Theme> styleMap;
	private AlertDialog dialog;

	public ThemeChangerDialog(@NonNull ThemeChangeListener themeChangeListener) {
		this.changeListener = themeChangeListener;
		this.styleMap = new HashMap<>();
		initStyles();
	}

	private void initStyles() {
		for(Theme style : Theme.values()) {
			styleMap.put(style.getName(), style);
		}
	}

	public void show(@NonNull Context context) {
		if(dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
		dialog = null;
		buildDialog(context);
		dialog.show();
	}

	private void buildDialog(@NonNull Context context) {
		dialog = new AlertDialog.Builder(context)
				.setSingleChoiceItems(buildThemeList(), -1, this)
				.setTitle(R.string.dialog_theme_selection_title)
				.create();
	}

	private CharSequence[] buildThemeList() {
		Set<CharSequence> themeNames = styleMap.keySet();
		return themeNames.toArray(new CharSequence[themeNames.size()]);
	}

	private Theme findThemeWithMatchingName(String themeName) {

		if(themeName == null || themeName.isEmpty()) {
			return Theme.DEFAULT;
		}

		for(Theme theme : Theme.values()) {
			if(theme.getName().equalsIgnoreCase(themeName)) {
				return theme;
			}
		}

		return Theme.DEFAULT;
	}

	private Theme getSelectedTheme(int selectedIndex) {

		CharSequence[] themes = buildThemeList();
		String themeName = null;

		if(selectedIndex > 0 && selectedIndex < themes.length) {
			themeName = themes[selectedIndex].toString();
		}

		return findThemeWithMatchingName(themeName);

	}

	@Override
	public void onClick(DialogInterface dialogInterface, int i) {
		dialogInterface.dismiss();
		Theme newTheme = getSelectedTheme(i);
		changeListener.onChangeTheme(newTheme);
	}
}

package com.pddstudio.highlightjs.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.pddstudio.highlightjs.HighlightJsView;
import com.pddstudio.highlightjs.demo.utils.FileObject;
import com.pddstudio.highlightjs.demo.utils.ThemeChangerDialog;
import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;

import java.util.Random;

public class SyntaxActivity extends AppCompatActivity implements
                                                      SwipeRefreshLayout.OnRefreshListener,
                                                      HighlightJsView.OnThemeChangedListener,
                                                      ThemeChangerDialog.ThemeChangeListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private HighlightJsView highlightJsView;
    private FileObject fileObject;

    private ThemeChangerDialog themeChangerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syntax);
        fileObject = (FileObject) getIntent().getExtras().getSerializable("fileObject");
        if(getActionBar() != null) {
            getActionBar().setTitle(fileObject.getAbsoluteFilePath());
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(fileObject.getAbsoluteFilePath());
        }
        //set and assign swipe refresh listener
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        //find and instantiate the view
        highlightJsView = (HighlightJsView) findViewById(R.id.highlight_view);
        //register theme change listener
        highlightJsView.setOnThemeChangedListener(this);
        //change theme and set language to auto detect
        highlightJsView.setTheme(Theme.ANDROID_STUDIO);
        highlightJsView.setHighlightLanguage(Language.AUTO_DETECT);
        //load the source
        highlightJsView.setSource(fileObject.getUrl());
    }

    @Override
    protected void onResume() {
        super.onResume();
        themeChangerDialog = new ThemeChangerDialog(this);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.menu_theme_switch, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				break;
			case R.id.menu_switch_theme:
				themeChangerDialog.show(this);
				break;
			case R.id.menu_check_line_numbers:
				item.setChecked(!item.isChecked());
				onShowLineNumbersToggled(item.isChecked());
				break;
			case R.id.menu_check_zoom:
				item.setChecked(!item.isChecked());
				onZoomSupportToggled(item.isChecked());
				break;
			default:
				break;
		}
        return super.onOptionsItemSelected(item);
    }

    private void onShowLineNumbersToggled(boolean enableLineNumbers) {
		highlightJsView.setShowLineNumbers(enableLineNumbers);
		highlightJsView.refresh();
	}

	private void onZoomSupportToggled(boolean enableZooming) {
		highlightJsView.setZoomSupportEnabled(enableZooming);
		highlightJsView.refresh();
	}

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        highlightJsView.setTheme(getRandom(Theme.values()));
        highlightJsView.refresh();
    }

    private <T> T getRandom(T... items) {
        return items[new Random().nextInt(items.length)];
    }

    @Override
    public void onThemeChanged(@NonNull Theme theme) {
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(this, "Theme: " + theme.name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChangeTheme(@NonNull Theme theme) {
        highlightJsView.setTheme(theme);
        highlightJsView.refresh();
    }

}

package com.pddstudio.highlightjs.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.pddstudio.highlightjs.HighlightJsView;
import com.pddstudio.highlightjs.demo.utils.FileObject;
import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;

import java.util.Random;

public class SyntaxActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, HighlightJsView.OnThemeChangedListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private HighlightJsView highlightJsView;
    private FileObject fileObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syntax);
        fileObject = (FileObject) getIntent().getExtras().getSerializable("fileObject");
        if(getActionBar() != null) {
            getActionBar().setTitle(fileObject.getName());
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(fileObject.getName());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
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

}

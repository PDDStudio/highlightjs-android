package com.pddstudio.highlightjs.demo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.pddstudio.highlightjs.HighlightJsView;
import com.pddstudio.highlightjs.models.Language;
import com.pddstudio.highlightjs.models.Theme;

import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private HighlightJsView highlightJsView;
    private URL markdownUrl, javaUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createUrls();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        highlightJsView = (HighlightJsView) findViewById(R.id.highlight_js_view);
        highlightJsView.setTheme(Theme.DEFAULT);
        highlightJsView.setHighlightLanguage(Language.AUTO_DETECT);
        highlightJsView.setSource(javaUrl);
    }

    private void createUrls() {
        try {
            markdownUrl = new URL("https://raw.githubusercontent.com/PDDStudio/earthview-android/master/README.md");
            javaUrl = new URL("https://raw.githubusercontent.com/PDDStudio/earthview-android/master/ps-app/src/main/java/com/pddstudio/earthviewer/DemoActivity.java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> T getRandom(T... items) {
        return items[new Random().nextInt(items.length)];
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        highlightJsView.setTheme(getRandom(Theme.values()));
        highlightJsView.refresh();
        Toast.makeText(this, "New Theme: " + highlightJsView.getTheme().name(), Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }
}

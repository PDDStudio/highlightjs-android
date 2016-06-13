package com.pddstudio.highlightjs.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pddstudio.highlightjs.demo.fragments.FilesListFragment;

public class MainActivity extends AppCompatActivity {

    //private SwipeRefreshLayout swipeRefreshLayout;
    //private HighlightJsView highlightJsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, FilesListFragment.newInstance())
                .commit();
        /*createUrls();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        highlightJsView = (HighlightJsView) findViewById(R.id.highlight_js_view);
        highlightJsView.setTheme(Theme.DEFAULT);
        highlightJsView.setHighlightLanguage(Language.AUTO_DETECT);
        highlightJsView.setSource(javaUrl);*/
        //RepositoryLoader.get().loadFiles(null);

    }

}

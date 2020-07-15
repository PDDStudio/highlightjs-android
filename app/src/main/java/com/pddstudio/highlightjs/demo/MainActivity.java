package com.pddstudio.highlightjs.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pddstudio.highlightjs.demo.fragments.FilesListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, FilesListFragment.newInstance())
                .commit();
    }

}

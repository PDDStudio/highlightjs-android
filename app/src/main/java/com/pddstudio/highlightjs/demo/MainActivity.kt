package com.pddstudio.highlightjs.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pddstudio.highlightjs.demo.fragments.FilesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_main, FilesListFragment.newInstance())
            .commit()
    }
}

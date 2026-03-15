package com.pddstudio.highlightjs.demo.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pddstudio.highlightjs.demo.R
import com.pddstudio.highlightjs.demo.SyntaxActivity
import com.pddstudio.highlightjs.demo.adapters.FilesAdapter
import com.pddstudio.highlightjs.demo.utils.FileObject
import com.pddstudio.highlightjs.demo.utils.RepositoryLoader
import java.util.LinkedList

/**
 * This Class was created by Patrick J
 * on 13.06.16. For more Details and Licensing
 * have a look at the README.md
 */

class FilesListFragment : Fragment(), RepositoryLoader.Callback, FilesAdapter.OnItemSelectedListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var filesAdapter: FilesAdapter

    companion object {
        fun newInstance(): FilesListFragment = FilesListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_file_list, container, false)
        recyclerView = root.findViewById(R.id.files_recycler_view)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        filesAdapter = FilesAdapter(LinkedList(), this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = filesAdapter
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RepositoryLoader.get().loadFiles(this)
    }

    override fun onItemLoaded(fileObject: FileObject) {
        filesAdapter.addItem(fileObject)
    }

    override fun onFilesLoaded(fileObjects: List<FileObject>) {}

    override fun onItemSelected(position: Int) {
        Log.d(javaClass.simpleName, "Position: $position URL: ${filesAdapter.getItem(position).getUrl()}")
        val i = Intent(context, SyntaxActivity::class.java)
        i.putExtra("fileObject", filesAdapter.getItem(position))
        startActivity(i)
    }
}

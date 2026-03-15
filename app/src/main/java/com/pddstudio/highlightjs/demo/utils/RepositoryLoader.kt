package com.pddstudio.highlightjs.demo.utils

import android.os.AsyncTask
import android.util.Log
import org.kohsuke.github.GHCommit
import org.kohsuke.github.GHRepository
import org.kohsuke.github.GitHub
import java.io.IOException
import java.util.LinkedList

/**
 * This Class was created by Patrick J
 * on 13.06.16. For more Details and Licensing
 * have a look at the README.md
 */

class RepositoryLoader private constructor() {

    interface Callback {
        fun onItemLoaded(fileObject: FileObject)
        fun onFilesLoaded(fileObjects: List<FileObject>)
    }

    private var gitHub: GitHub? = null
    private val fileObjectList: MutableList<FileObject> = LinkedList()
    private var callback: Callback? = null

    init {
        try {
            gitHub = GitHub.connectAnonymously()
        } catch (io: IOException) {
            io.printStackTrace()
        }
    }

    fun loadFiles(callback: Callback) {
        this.callback = callback
        RepoLoader().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private inner class RepoLoader : AsyncTask<Void, FileObject, Void>() {

        override fun onPreExecute() {
            Log.d(javaClass.simpleName, "loadFiles() - starting async call")
        }

        override fun doInBackground(vararg params: Void): Void? {
            try {
                Log.d(javaClass.simpleName, "entering try-catch block / Anonymous: " + gitHub?.isAnonymous)
                val ghRepository: GHRepository = gitHub!!.getRepository("PDDStudio/highlightjs-android")
                Log.d(javaClass.simpleName, "GHURepository null: " + (ghRepository == null))
                val commitSet = ghRepository.listCommits().asSet()
                Log.d(javaClass.simpleName, "GHCommit size: " + commitSet.size)
                for (commit in commitSet) {
                    val files: List<GHCommit.File> = commit.files
                    for (file in files) {
                        fileObjectList.add(FileObject(file.fileName, file.rawUrl))
                        publishProgress(FileObject(file.fileName, file.rawUrl))
                    }
                }
                Log.d(javaClass.simpleName, "Loaded Items: " + fileObjectList.size)
            } catch (io: IOException) {
                io.printStackTrace()
            } finally {
                Log.d(javaClass.simpleName, "Leaving AsyncTask")
            }
            return null
        }

        override fun onProgressUpdate(vararg values: FileObject) {
            Log.d(javaClass.simpleName, "onProgressUpdate() called.")
            callback?.onItemLoaded(values[0])
        }

        override fun onPostExecute(aVoid: Void?) {
            callback?.onFilesLoaded(fileObjectList)
        }
    }

    companion object {
        private var repositoryLoader: RepositoryLoader? = null

        fun get(): RepositoryLoader {
            if (repositoryLoader == null) {
                repositoryLoader = RepositoryLoader()
            }
            return repositoryLoader!!
        }
    }
}

package com.pddstudio.highlightjs.demo.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.kohsuke.github.GitHub
import java.io.IOException

class RepositoryLoader private constructor() {

    private var gitHub: GitHub? = null

    init {
        try {
            gitHub = GitHub.connectAnonymously()
        } catch (io: IOException) {
            Log.e(TAG, "Failed to connect to GitHub anonymously", io)
        }
    }

    fun loadFilesFlow(): Flow<FileObject> = flow {
        val gh = gitHub ?: run {
            Log.e(TAG, "GitHub client not initialized")
            return@flow
        }
        try {
            val repo = gh.getRepository("PDDStudio/highlightjs-android")
            for (commit in repo.listCommits().asSet()) {
                for (file in commit.files) {
                    emit(FileObject(file.fileName, file.rawUrl))
                }
            }
        } catch (io: IOException) {
            Log.e(TAG, "Error loading repository files", io)
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val TAG = "RepositoryLoader"
        private var instance: RepositoryLoader? = null

        fun get(): RepositoryLoader {
            if (instance == null) instance = RepositoryLoader()
            return instance!!
        }
    }
}

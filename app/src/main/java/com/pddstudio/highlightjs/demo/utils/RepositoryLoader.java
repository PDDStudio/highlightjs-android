package com.pddstudio.highlightjs.demo.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This Class was created by Patrick J
 * on 13.06.16. For more Details and Licensing
 * have a look at the README.md
 */

public class RepositoryLoader {

    public interface Callback {
        void onItemLoaded(FileObject fileObject);
        void onFilesLoaded(List<FileObject> fileObjects);
    }

    private static RepositoryLoader repositoryLoader;

    private GitHub gitHub;
    private List<FileObject> fileObjectList = new LinkedList<>();
    private Callback callback;

    private RepositoryLoader() {
        try {
            gitHub = GitHub.connectAnonymously();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static RepositoryLoader get() {
        if(repositoryLoader == null) repositoryLoader = new RepositoryLoader();
        return repositoryLoader;
    }

    public void loadFiles(Callback callback) {
        this.callback = callback;
        new RepoLoader().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class RepoLoader extends AsyncTask<Void, FileObject, Void> {

        @Override
        protected void onPreExecute() {
            Log.d(getClass().getSimpleName(), "loadFiles() - starting async call");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Log.d(getClass().getSimpleName(), "entering try-catch block / Anonymous: " + gitHub.isAnonymous());
                GHRepository ghRepository = gitHub.getRepository("PDDStudio/highlightjs-android");
                Log.d(getClass().getSimpleName(), "GHURepository null: " + (ghRepository == null));
                Set<GHCommit> commitSet = ghRepository.listCommits().asSet();
                Log.d(getClass().getSimpleName(), "GHCommit size: " + commitSet.size());
                for(GHCommit commit : commitSet) {
                    List<GHCommit.File> files = commit.getFiles();
                    for(GHCommit.File file : files) {
                        fileObjectList.add(new FileObject(file.getFileName(), file.getRawUrl()));
                        publishProgress(new FileObject(file.getFileName(), file.getRawUrl()));
                    }
                }
                Log.d(getClass().getSimpleName(), "Loaded Items: " + fileObjectList.size());
            } catch (IOException io) {
                io.printStackTrace();
            } finally {
                Log.d(getClass().getSimpleName(), "Leaving AsyncTask");
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(FileObject... values) {
            Log.d(getClass().getSimpleName(), "onProgressUpdate() called.");
            if(callback != null && isFileType(values[0])) callback.onItemLoaded(values[0]);
        }

        private boolean isFileType(FileObject fileObject) {
            if(fileObject.getName().endsWith(".md") || fileObject.getName().endsWith(".java") || fileObject.getName().endsWith(".gradle")) return true;
            return false;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(callback != null) callback.onFilesLoaded(fileObjectList);
        }
    }

}

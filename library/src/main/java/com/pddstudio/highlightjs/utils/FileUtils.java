package com.pddstudio.highlightjs.utils;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * This Class was created by Patrick J
 * on 09.06.16. For more Details and Licensing
 * have a look at the README.md
 */

public class FileUtils {

    public interface Callback {
        void onDataLoaded(boolean success, String source);
    }

    public static String loadSourceFromFile(File file) {
	    BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file), 16384);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null) {
            	stringBuilder.append(line).append("\n");
            }

            return stringBuilder.toString();
        } catch (IOException io) {
            io.printStackTrace();
            return null;
        } finally {
        	if(bufferedReader != null) {
		        try {
			        bufferedReader.close();
		        } catch (IOException e) {
			        // ignore
		        }
	        }
        }
    }

    public static void loadSourceFromUrl(Callback callback, URL url) {
        new NetworkLoader(callback, url).execute();
    }

    private static class NetworkLoader extends AsyncTask<Void, Void, String> {

        private final Callback callback;
        private final URL url;

        private NetworkLoader(Callback callback, URL url) {
            this.callback = callback;
            this.url = url;
        }

        @Override
        protected String doInBackground(Void... params) {
	        BufferedReader bufferedReader = null;
            try {
                URLConnection urlConnection = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()), 16384);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while((line = bufferedReader.readLine()) != null) stringBuilder.append(line).append("\n");
                bufferedReader.close();
                return stringBuilder.toString();
            } catch (IOException io) {
                io.printStackTrace();
                return null;
            } finally {
	            if(bufferedReader != null) {
		            try {
			            bufferedReader.close();
		            } catch (IOException e) {
			            // ignore
		            }
	            }
            }
        }

        @Override
        protected void onCancelled() {
            callback.onDataLoaded(false, null);
        }

        @Override
        protected void onPostExecute(String s) {
            callback.onDataLoaded(s != null, s);
        }
    }

}

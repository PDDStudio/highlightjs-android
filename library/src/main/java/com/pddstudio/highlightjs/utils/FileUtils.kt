package com.pddstudio.highlightjs.utils

import android.os.AsyncTask
import java.io.*
import java.net.URL

/**
 * This Class was created by Patrick J
 * on 09.06.16. For more Details and Licensing
 * have a look at the README.md
 */
object FileUtils {
    @JvmStatic
    fun loadSourceFromFile(file: File?): String? {
        var bufferedReader: BufferedReader? = null
        return try {
            bufferedReader = BufferedReader(FileReader(file), 16384)
            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }
            stringBuilder.toString()
        } catch (io: IOException) {
            io.printStackTrace()
            null
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (e: IOException) {
                    // ignore
                }
            }
        }
    }

    @JvmStatic
    fun loadSourceFromUrl(callback: Callback, url: URL) {
        NetworkLoader(callback, url).execute()
    }

    interface Callback {
        fun onDataLoaded(success: Boolean, source: String?)
    }

    private class NetworkLoader constructor(private val callback: Callback, private val url: URL) : AsyncTask<Void?, Void?, String?>() {
        override fun doInBackground(vararg params: Void?): String? {
            var bufferedReader: BufferedReader? = null
            return try {
                val urlConnection = url.openConnection()
                bufferedReader = BufferedReader(InputStreamReader(urlConnection.getInputStream()), 16384)
                val stringBuilder = StringBuilder()
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) stringBuilder.append(line).append("\n")
                bufferedReader.close()
                stringBuilder.toString()
            } catch (io: IOException) {
                io.printStackTrace()
                null
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close()
                    } catch (e: IOException) {
                        // ignore
                    }
                }
            }
        }

        override fun onCancelled() {
            callback.onDataLoaded(false, null)
        }

        override fun onPostExecute(s: String?) {
            callback.onDataLoaded(s != null, s)
        }

    }
}
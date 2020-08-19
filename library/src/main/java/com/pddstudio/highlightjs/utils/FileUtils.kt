package com.pddstudio.highlightjs.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.*
import java.net.URL

/**
 * This Class was created by Patrick J
 * on 09.06.16. For more Details and Licensing
 * have a look at the README.md
 */
object FileUtils {
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

    fun loadSourceFromUrl(callback: Callback, url: URL) {
        CoroutineScope(Dispatchers.Main).launch {
            var result: String? = null
            var success = false
            withContext(Dispatchers.IO) {
                var bufferedReader: BufferedReader? = null
                try {
                    val urlConnection = url.openConnection()
                    bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream), 16384)
                    val stringBuilder = StringBuilder()
                    var line: String?
                    while (bufferedReader.readLine().also { line = it } != null) stringBuilder.append(line).append("\n")
                    bufferedReader.close()

                    result = stringBuilder.toString()
                    success = true
                } catch (io: IOException) {
                    io.printStackTrace()
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close()
                        } catch (e: IOException) {
                        }
                    }
                }
            }
            callback.onDataLoaded(success, result)
        }
    }

    interface Callback {
        fun onDataLoaded(success: Boolean, source: String?)
    }
}

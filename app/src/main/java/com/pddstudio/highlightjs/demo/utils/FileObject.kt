package com.pddstudio.highlightjs.demo.utils

import java.io.File
import java.io.Serializable
import java.net.URL

/**
 * This Class was created by Patrick J
 * on 13.06.16. For more Details and Licensing
 * have a look at the README.md
 */

class FileObject internal constructor(private val name: String, private val url: URL) : Serializable {

    fun getAbsoluteFilePath(): String = name

    fun getUrl(): URL = url

    fun getFileName(): String = File(name).name
}

package com.pddstudio.highlightjs.demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pddstudio.highlightjs.demo.utils.FileObject
import com.pddstudio.highlightjs.demo.utils.RepositoryLoader
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DemoViewModel : ViewModel() {

    private val _files = MutableStateFlow<List<FileObject>>(emptyList())
    val files: StateFlow<List<FileObject>> = _files.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _selectedFile = MutableStateFlow<FileObject?>(null)
    val selectedFile: StateFlow<FileObject?> = _selectedFile.asStateFlow()

    init {
        loadFiles()
    }

    private fun loadFiles() {
        viewModelScope.launch {
            _isLoading.value = true
            RepositoryLoader.get().loadFilesFlow().collect { file ->
                _files.value = _files.value + file
            }
            _isLoading.value = false
        }
    }

    fun selectFile(file: FileObject) {
        _selectedFile.value = file
    }
}

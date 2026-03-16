package com.pddstudio.highlightjs.demo.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pddstudio.highlightjs.HighlightJsView
import com.pddstudio.highlightjs.rememberHighlightState
import com.pddstudio.highlightjs.models.Theme
import com.pddstudio.highlightjs.utils.ExtensionUtil
import com.pddstudio.highlightjs.demo.utils.FileObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "SyntaxScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyntaxScreen(
    fileObject: FileObject,
    onNavigateUp: () -> Unit
) {
    val highlightState = rememberHighlightState(theme = Theme.AndroidStudio)
    var isLoading by remember { mutableStateOf(true) }
    var showLineNumbers by remember { mutableStateOf(false) }
    var showThemePicker by remember { mutableStateOf(false) }

    LaunchedEffect(fileObject) {
        isLoading = true
        withContext(Dispatchers.IO) {
            try {
                val content = fileObject.getUrl().readText()
                val ext = fileObject.getFileName().substringAfterLast(".", "")
                highlightState.language = ExtensionUtil.getLanguageByExtension(ext)
                highlightState.code = content
            } catch (e: Exception) {
                Log.e(TAG, "Failed to load file: ${fileObject.getUrl()}", e)
            }
        }
        isLoading = false
    }

    val bottomSheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(fileObject.getFileName()) },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    FilterChip(
                        selected = showLineNumbers,
                        onClick = {
                            showLineNumbers = !showLineNumbers
                            highlightState.showLineNumbers = showLineNumbers
                        },
                        label = { Text("Lines") },
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    IconButton(onClick = { showThemePicker = true }) {
                        Icon(Icons.Default.Settings, contentDescription = "Pick theme")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HighlightJsView(
                state = highlightState,
                modifier = Modifier.fillMaxSize()
            )
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

    if (showThemePicker) {
        ModalBottomSheet(
            onDismissRequest = { showThemePicker = false },
            sheetState = bottomSheetState
        ) {
            ThemePickerContent(
                currentTheme = highlightState.theme,
                onThemeSelected = { theme ->
                    highlightState.theme = theme
                    showThemePicker = false
                }
            )
        }
    }
}

@Composable
private fun ThemePickerContent(
    currentTheme: Theme,
    onThemeSelected: (Theme) -> Unit
) {
    val themes = remember { Theme.values().toList() }

    Column {
        Text(
            text = "Select Theme",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(themes) { theme ->
                ListItem(
                    headlineContent = { Text(theme.getName()) },
                    trailingContent = if (theme == currentTheme) {
                        { Text("✓") }
                    } else null,
                    modifier = Modifier.clickable { onThemeSelected(theme) }
                )
            }
        }
    }
}

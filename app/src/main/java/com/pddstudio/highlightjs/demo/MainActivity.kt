package com.pddstudio.highlightjs.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pddstudio.highlightjs.demo.ui.screens.FilesScreen
import com.pddstudio.highlightjs.demo.ui.screens.SyntaxScreen
import com.pddstudio.highlightjs.demo.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                val viewModel: DemoViewModel = viewModel()

                NavHost(navController = navController, startDestination = "files") {
                    composable("files") {
                        FilesScreen(
                            viewModel = viewModel,
                            onFileSelected = { file ->
                                viewModel.selectFile(file)
                                navController.navigate("syntax")
                            }
                        )
                    }
                    composable("syntax") {
                        val selectedFile by viewModel.selectedFile.collectAsStateWithLifecycle()
                        selectedFile?.let { file ->
                            SyntaxScreen(
                                fileObject = file,
                                onNavigateUp = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

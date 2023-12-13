package com.securenaut.securenet

import StaticAnalysisAppList
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.securenaut.securenet.pages.HomeActivity
import com.securenaut.securenet.pages.SettingsScreen
import com.securenaut.securenet.pages.StaticAnalysisScreen
import com.securenaut.securenet.ui.theme.SecureNetTheme

class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            SecureNetTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home"){
                    composable("home"){
                        HomeActivity(navController)
                    }
                    composable("staticAnalysisAppList"){
                        StaticAnalysisAppList(navController)
                    }
                    composable("settings"){
                        SettingsScreen(navController)
                    }
                    composable("staticAnalysis/{app}") { backStackEntry ->
                        backStackEntry.arguments?.getString("app")
                            ?.let { StaticAnalysisScreen(navController, it) }
                    }

                }
            }
        }
    }
}

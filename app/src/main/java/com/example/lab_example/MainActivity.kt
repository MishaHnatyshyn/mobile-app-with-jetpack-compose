package com.example.lab_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lab_example.ui.theme.LabexampleTheme
import androidx.navigation.compose.rememberNavController
import com.example.lab_example.services.CalculatorService
import com.example.lab_example.ui.calculator1.Calculator1Screen
import com.example.lab_example.ui.calculator2.Calculator2Screen
import com.example.lab_example.ui.entry.EntryScreen

enum class Routes {
    MAIN_SCREEN,
    CALCULATOR_1,
    CALCULATOR_2
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabexampleTheme {
                val navController = rememberNavController()
                val calculatorService = CalculatorService()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.MAIN_SCREEN.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Routes.MAIN_SCREEN.name) {
                            EntryScreen(
                                onCalculator1Navigate = { navController.navigate(route = Routes.CALCULATOR_1.name) },
                                onCalculator2Navigate = { navController.navigate(route = Routes.CALCULATOR_2.name) },
                            )
                        }
                        composable(route = Routes.CALCULATOR_1.name) {
                            Calculator1Screen(
                                goBack = { navController.navigate(route = Routes.MAIN_SCREEN.name )},
                                calculatorService = calculatorService
                            )
                        }
                        composable(route = Routes.CALCULATOR_2.name) {
                            Calculator2Screen(goBack = { navController.navigate(route = Routes.MAIN_SCREEN.name )},
                                calculatorService = calculatorService
                            )
                        }
                    }
                }
            }
        }
    }
}

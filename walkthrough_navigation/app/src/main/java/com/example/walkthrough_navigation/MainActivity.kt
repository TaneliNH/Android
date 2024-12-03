package com.example.walkthrough_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationExampleApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationExampleApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ){ 
        composable(route = "home") {
            HomeScreen(navController)
        }
        composable(route = "second/{parameter}",
            arguments = listOf(
                navArgument("parameter"){
                    type = NavType.StringType
                }
            )
        ) {
            SecondScreen(navController,
                it.arguments?.getString("parameter")
            )
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text(text = "This is home screen")
        OutlinedTextField(
            value = text,
            onValueChange = {text = it}
        )
        Button (
            onClick = { navController.navigate("second/$text") },
            enabled = text.isNotEmpty()
        ) {
            Text("To second")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController,parameter: String?) {
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text(text = "This is Second screen")
        Text(text = "Parameter from home is $parameter")
        Button (
            onClick = { navController.navigateUp() }
        ) {
            Text("Back home")
        }
    }
}
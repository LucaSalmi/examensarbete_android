package com.example.login_app_exarbete

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login_app_exarbete.views.HomePage
import com.example.login_app_exarbete.views.LoginScreen

@Composable
fun ScreenMain(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route){
        composable(Routes.Login.route){
            LoginScreen(navController)
        }
        composable(Routes.HomePage.route){
            HomePage(navController)
        }
    }
}
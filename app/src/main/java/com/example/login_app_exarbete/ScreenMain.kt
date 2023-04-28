package com.example.login_app_exarbete

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login_app_exarbete.views.CreatePostList
import com.example.login_app_exarbete.views.CreatePostPage
import com.example.login_app_exarbete.views.HomePage
import com.example.login_app_exarbete.views.LoginScreen
import com.example.login_app_exarbete.views.RegisterScreen

@Composable
fun ScreenMain(){
    val navController = rememberNavController()
    val authModel: AuthModel = viewModel()
    val hasUser = authModel.getUser() != null
    NavHost(navController = navController, startDestination = getStartDestination(hasUser)){
        composable(Routes.Login.route){
            LoginScreen(navController)
        }
        composable(Routes.Register.route){
            RegisterScreen(navController)
        }
        composable(Routes.HomePage.route){
            HomePage(navController)
        }
        composable(Routes.CreatePost.route){
            CreatePostPage(navController)
        }
    }
}

fun getStartDestination(hasUser: Boolean): String {
    return if(hasUser){
        Routes.HomePage.route
    }else{
        Routes.Login.route
    }
}
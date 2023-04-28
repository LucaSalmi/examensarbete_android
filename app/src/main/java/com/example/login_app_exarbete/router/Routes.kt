package com.example.login_app_exarbete.router

sealed class Routes(val route: String){
    object Login: Routes("Login")
    object Register: Routes("Register")
    object HomePage: Routes("HomePage")
    object CreatePost: Routes("CreatePost")

}

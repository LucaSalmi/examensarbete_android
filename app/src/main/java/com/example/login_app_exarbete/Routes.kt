package com.example.login_app_exarbete

sealed class Routes(val route: String){
    object Login: Routes("Login")
    object HomePage: Routes("HomePage")

}

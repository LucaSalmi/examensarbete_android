package com.example.login_app_exarbete.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.login_app_exarbete.Routes
import com.example.login_app_exarbete.widgets.AppTextField
import com.example.login_app_exarbete.widgets.CustomTopAppBar

@Composable
fun LoginScreen(navController: NavHostController){
    Scaffold(
        topBar = {
        CustomTopAppBar(navController = navController, title = "Login", showBackIcon = false)
    }) {
        Column(modifier= Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally,) {
            Spacer(modifier = Modifier.height(150.dp))
            AppTextField("E-mail")
            Spacer(modifier = Modifier.height(25.dp))
            AppTextField("Password")
            Spacer(modifier = Modifier.height(25.dp))
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                Button(
                    onClick = { navController.navigate(Routes.HomePage.route) },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Login")
                }
            }
        }
    }
}







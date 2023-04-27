package com.example.login_app_exarbete.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.login_app_exarbete.AuthModel
import com.example.login_app_exarbete.Routes
import com.example.login_app_exarbete.widgets.AppPasswordField
import com.example.login_app_exarbete.widgets.AppTextField
import com.example.login_app_exarbete.widgets.CustomTopAppBar
import com.example.login_app_exarbete.widgets.DialogBoxLoading

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {
    val authModel: AuthModel = viewModel()
    val openDialog by authModel.open.observeAsState(false)
    val context = LocalContext.current
    var mail by rememberSaveable { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(topBar = {
        CustomTopAppBar(navController = navController, title = "Login", showBackIcon = false)
    }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(150.dp))
            AppTextField(label = "E-Mail", value = mail, onInputChanged = { mail = it })
            Spacer(modifier = Modifier.height(25.dp))
            AppPasswordField(
                label = "Password",
                value = password,
                onInputChanged = { password = it })
            Spacer(modifier = Modifier.height(25.dp))
            TextButton(onClick = {
                navController.navigate(Routes.Register.route)
            }) {
                Text("Register Here")
            }
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                Button(
                    onClick = {
                        authModel.open.value = true
                        authModel.loginUser(mail, password, context, navController)
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Login")
                }
            }
            if (openDialog) {
                DialogBoxLoading()
            }
        }
    }
}







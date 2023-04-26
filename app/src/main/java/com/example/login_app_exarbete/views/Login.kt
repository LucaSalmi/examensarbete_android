package com.example.login_app_exarbete.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.login_app_exarbete.AuthModel
import com.example.login_app_exarbete.Routes
import com.example.login_app_exarbete.widgets.AppPasswordField
import com.example.login_app_exarbete.widgets.AppTextField
import com.example.login_app_exarbete.widgets.CustomTopAppBar
import com.example.login_app_exarbete.widgets.DialogBoxLoading
import com.example.login_app_exarbete.widgets.HorizontalCenteredRow
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel: AuthModel = viewModel()
    val openDialog by viewModel.open.observeAsState(false)
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
                        viewModel.open.value = true
                        viewModel.loginUser(mail, password, context, navController)
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







package com.example.login_app_exarbete.views

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavHostController) {
    val viewModel: AuthModel = viewModel()
    val openDialog by viewModel.open.observeAsState(false)
    val context = LocalContext.current
    var mail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }

    Scaffold(topBar = {
        CustomTopAppBar(navController = navController, title = "Login", showBackIcon = false)
    }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(150.dp))
            AppTextField(label = "UserName", value = userName, onInputChanged = { userName = it })
            Spacer(modifier = Modifier.height(25.dp))
            AppTextField(label = "E-Mail", value = mail, onInputChanged = { mail = it })
            Spacer(modifier = Modifier.height(25.dp))
            AppPasswordField(label = "Password",
                value = password,
                onInputChanged = { password = it })
            Spacer(modifier = Modifier.height(25.dp))
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                Button(
                    onClick = {
                        viewModel.open.value = true
                        viewModel.registerUser(mail, password, context, navController)
                    },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Register")
                }
            }
            if (openDialog) {
                DialogBoxLoading()
            }
        }
    }
}


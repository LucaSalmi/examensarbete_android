package com.example.login_app_exarbete.widgets

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.login_app_exarbete.views.TextFieldState


@Composable
fun AppTextField(label: String, value: String, onInputChanged: (String) -> Unit) {

    HorizontalCenteredRow(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            shape = RoundedCornerShape(percent = 20),
            value = value,
            onValueChange = onInputChanged,
            label = { Text(text = label) },
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )
    }
}

@Composable
fun AppPasswordField(label: String, value: String, onInputChanged: (String) -> Unit) {
    var showPassword by remember { mutableStateOf(value = false) }
    HorizontalCenteredRow(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            shape = RoundedCornerShape(percent = 20),
            value = value,
            onValueChange = onInputChanged,
            label = { Text(text = label) },
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 20.dp),
            visualTransformation = if (showPassword) {

                VisualTransformation.None

            } else {

                PasswordVisualTransformation()

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            }
        )
    }
}
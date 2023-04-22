package com.example.login_app_exarbete.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun AppTextField(label: String){
    var text by remember { mutableStateOf(TextFieldValue("")) }
    HorizontalCenteredRow(modifier = Modifier.fillMaxWidth()){
        TextField(
            value = text,
            onValueChange = {text = it},
            label = { Text(text = label) },
            modifier = Modifier.weight(1F).padding(horizontal = 20.dp)
        )
    }

}
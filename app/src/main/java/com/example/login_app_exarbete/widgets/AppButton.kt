package com.example.login_app_exarbete.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
@Composable
fun AppButton( label : String, horizontalSpacer: Dp = 25.dp ,onClick: () -> Unit){
    Column {
        Spacer(modifier = Modifier.height(horizontalSpacer))
        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
            Button(
                onClick = onClick,
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = label)
            }
        }
    }
}

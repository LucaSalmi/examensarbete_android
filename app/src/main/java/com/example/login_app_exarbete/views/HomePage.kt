package com.example.login_app_exarbete.views

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.login_app_exarbete.models.UserPost
import com.example.login_app_exarbete.widgets.CustomTopAppBar
import com.example.login_app_exarbete.widgets.UserPostcard
import java.util.Date
import androidx.compose.foundation.lazy.items
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavHostController) {
    val posts = remember {
        mutableListOf(UserPost(
            title = "Title", body = "Body", userName = "UserName", id = "123456", createdAt = Date.from(
            Instant.now())))
    }
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Home Page",
                showBackIcon = false
            )
        }
    ) {

        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 6.dp,
            )
        ) {
            items(
                items = posts
            ) {
                UserPostcard(item = it)
            }
        }
    }
}
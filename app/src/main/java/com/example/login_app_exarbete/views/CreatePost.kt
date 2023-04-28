package com.example.login_app_exarbete.views

import android.annotation.SuppressLint
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.login_app_exarbete.FirestoreViewModel
import com.example.login_app_exarbete.Routes
import com.example.login_app_exarbete.widgets.CustomTopAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreatePostPage(navController: NavHostController){
    val firestoreViewModel: FirestoreViewModel = viewModel()
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Create New Post",
                showBackIcon = true
            )
        }
    ) {
    Text(text = "A")
    }
}
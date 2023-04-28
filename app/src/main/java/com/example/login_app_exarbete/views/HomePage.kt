package com.example.login_app_exarbete.views

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.login_app_exarbete.AuthModel
import com.example.login_app_exarbete.FirestoreViewModel
import com.example.login_app_exarbete.Routes
import com.example.login_app_exarbete.widgets.ImageLoadingAnimation
import java.time.Instant

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavHostController) {
    val firestoreViewModel: FirestoreViewModel = viewModel()
    firestoreViewModel.streamUserPost()



    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Routes.CreatePost.route) }) {
                Icon(Icons.Filled.Create, contentDescription = null)
            }
        },
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Home Page",
                showBackIcon = false
            )
        }
    ) {
        CreatePostList()
    }
}

@Composable
fun CreatePostList() {
    val firestoreViewModel: FirestoreViewModel = viewModel()
    val content by firestoreViewModel.postList.observeAsState(initial = emptyList())
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 6.dp,
        )
    ) {
        items(content) { item ->
            UserPostcard(item = item)
        }
    }
}
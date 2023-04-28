package com.example.login_app_exarbete.views

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.login_app_exarbete.widgets.CustomTopAppBar
import com.example.login_app_exarbete.widgets.UserPostcard
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.login_app_exarbete.extensions.isScrollingUp
import com.example.login_app_exarbete.router.Routes
import com.example.login_app_exarbete.view_models.FirestoreViewModel
import com.example.login_app_exarbete.widgets.HorizontalCenteredRow
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavHostController) {
    val firestoreViewModel: FirestoreViewModel = viewModel()
    firestoreViewModel.streamUserPost()
    val content by firestoreViewModel.postList.observeAsState(initial = emptyList())
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()



    Scaffold(
        floatingActionButton = {
            Row(Modifier.fillMaxWidth().padding(bottom = 24.dp), horizontalArrangement = Arrangement.End) {
                AnimatedVisibility(
                    visible = !listState.isScrollingUp(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    GoToTop {
                        scope.launch {
                            listState.scrollToItem(0)
                        }
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                FloatingActionButton(onClick = { navController.navigate(Routes.CreatePost.route) }) {
                    Icon(Icons.Filled.Create, contentDescription = null)
                }
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
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 6.dp,
            ),
            state = listState,
        ) {
            itemsIndexed(content) { index, item ->
                UserPostcard(item = item)
            }
        }
    }
}

@Composable
fun GoToTop(goToTop: () -> Unit) {
    FloatingActionButton(
        onClick = goToTop,
        ) {
        HorizontalCenteredRow(Modifier.padding(horizontal = 16.dp)) {
            Icon(
                Icons.Filled.ArrowUpward,
                contentDescription = "go to top"
            )
            Text(text = "Back To Top")
        }
    }

}


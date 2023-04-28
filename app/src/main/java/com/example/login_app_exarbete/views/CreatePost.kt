package com.example.login_app_exarbete.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.login_app_exarbete.view_models.AuthViewModel
import com.example.login_app_exarbete.view_models.FirestoreViewModel
import com.example.login_app_exarbete.models.UserPost
import com.example.login_app_exarbete.widgets.AppButton
import com.example.login_app_exarbete.widgets.AppTextField
import com.example.login_app_exarbete.widgets.CustomTopAppBar
import com.example.login_app_exarbete.widgets.DialogBoxLoading
import com.example.login_app_exarbete.widgets.HorizontalCenteredRow
import java.time.LocalDateTime


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreatePostPage(navController: NavHostController) {
    val firestoreViewModel: FirestoreViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current
    var newPost by remember {
        mutableStateOf(UserPost("", "", "", "", ""))
    }
    val openDialog by firestoreViewModel.open.observeAsState(false)


    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Create New Post",
                showBackIcon = true
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                AppTextField(
                    label = "Title",
                    value = newPost.title,
                    onInputChanged = { newPost = newPost.copy(title = it) })
                BodyTextField(
                    label = "Body",
                    value = newPost.body,
                    onInputChanged = { newPost = newPost.copy(body = it) })
            }

            Row(Modifier.fillMaxWidth().padding(bottom = 40.dp)) {
                AppButton("Publish Post") {
                    firestoreViewModel.open.value = true
                    newPost = newPost.copy(
                        userName = authViewModel.getUser()?.email ?: "Anonymous",
                        createdAt = LocalDateTime.now().toString(),
                    )
                    firestoreViewModel.savePostToFirestore(newPost, context, navController)
                }
            }
            if (openDialog) {
                DialogBoxLoading()
            }
        }


    }
}


@Composable
fun BodyTextField(label: String, value: String, onInputChanged: (String) -> Unit) {

    HorizontalCenteredRow(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            shape = RoundedCornerShape(percent = 20),
            value = value,
            onValueChange = onInputChanged,
            label = { androidx.compose.material.Text(text = label) },
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 20.dp)
                .widthIn(4.dp, Dp.Infinity),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )
    }
}
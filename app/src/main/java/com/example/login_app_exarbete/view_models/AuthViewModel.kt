package com.example.login_app_exarbete.view_models

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.login_app_exarbete.router.Routes
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel : ViewModel() {
    // Dialog box
    var open = MutableLiveData<Boolean>()
    private val auth = Firebase.auth

    fun getUser(): FirebaseUser? {
       return auth.currentUser
    }

    fun loginUser(
        email: String,
        password: String,
        context: Context,
        navController: NavHostController,
    ) {

        if (email == "" || password == "") {
            Toast.makeText(
                context, "check the fields", Toast.LENGTH_SHORT
            ).show()
            return
        }
        viewModelScope.launch {

            withContext(Dispatchers.Default) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        navController.navigate(Routes.HomePage.route)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            context, "Authentication failed.", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            closeDialog()
        }
    }

    fun registerUser(
        email: String,
        password: String,
        context: Context,
        navController: NavHostController,
    ) {

        if (email == "" || password == "") {
            Toast.makeText(
                context, "check the fields", Toast.LENGTH_SHORT
            ).show()
            return
        }

        viewModelScope.launch {

            withContext(Dispatchers.Default) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            navController.navigate(Routes.HomePage.route)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                context, "registration failed.", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
            closeDialog()
        }
    }

    private fun closeDialog() {
        open.value = false
    }
}
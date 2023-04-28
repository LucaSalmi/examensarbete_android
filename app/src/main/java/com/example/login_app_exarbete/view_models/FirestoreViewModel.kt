package com.example.login_app_exarbete.view_models

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.login_app_exarbete.constants.DatabaseConstants
import com.example.login_app_exarbete.models.UserPost
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class FirestoreViewModel : ViewModel() {


    private val db = Firebase.firestore
    var postList = MutableLiveData<List<UserPost>>()
    var open = MutableLiveData<Boolean>()


    fun savePostToFirestore(
        newPost: UserPost,
        context: Context,
        navController: NavHostController,
    ) {
        viewModelScope.launch {

            withContext(Dispatchers.Default) {
                val docRef = db.collection(DatabaseConstants.POSTS)
                val newId = docRef.document().id
                val doc = newPost.toJson(newId)
                docRef.document(newId).set(doc).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Saving success, pop back to home page
                        navController.popBackStack()
                    } else {
                        // If saving fails, display a message to the user.
                        Toast.makeText(
                            context, "Saving failed.", Toast.LENGTH_SHORT
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


    fun streamUserPost() {
        val docRef = db.collection(DatabaseConstants.POSTS)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
            }
            if (snapshot != null && !snapshot.isEmpty) {
                postList.value = UserPost.fromJson(snapshot.documents)
            }
        }
    }

    fun loadMoreItems() {
        val extraItemsList = mutableListOf<UserPost>()
        repeat(2) { postList.value?.let { extraItemsList.addAll(it) } }
        postList.value = extraItemsList
    }


}


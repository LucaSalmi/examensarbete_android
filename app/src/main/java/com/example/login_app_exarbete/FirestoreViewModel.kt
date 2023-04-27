package com.example.login_app_exarbete

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login_app_exarbete.models.UserPost
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Date

class FirestoreViewModel : ViewModel() {


    private val db = Firebase.firestore
    private val POSTS = "posts"
    var postList = MutableLiveData<List<UserPost>>()


     fun streamUserPost() {
        val docRef = db.collection(POSTS)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
            }
            if (snapshot != null && !snapshot.isEmpty) {
                fromJson(snapshot.documents)
                Log.d(TAG, "Current data: ${postList}")
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    private fun fromJson(snapshot: List<DocumentSnapshot>){
        var list = mutableListOf<UserPost>()

        for (doc in snapshot) {
            val data = doc.data
            if (data != null) {
                val item =
                    UserPost(
                        title = data["title"] as String,
                        body = data["body"] as String,
                        id = data["id"] as String,
                        userName = data["userName"] as String,
                        createdAt = data["createdAt"] as String,
                    )

                if (item != null) {
                    list.add(item)
                }
            }
        }
        postList.value = list
    }
}


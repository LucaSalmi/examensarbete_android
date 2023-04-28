package com.example.login_app_exarbete.models

import com.example.login_app_exarbete.constants.DatabaseConstants
import com.google.firebase.firestore.DocumentSnapshot
import java.util.Date

data class UserPost(
    val title: String,
    val body: String,
    val userName: String,
    val id: String,
    val createdAt: String,
) {
    fun copy(
        title: String = this.title,
        body: String = this.body,
        userName: String = this.userName,
        createdAt: String = this.createdAt,
    ) = UserPost(title, body, userName, id, createdAt)

    companion object{
        fun fromJson(snapshot: List<DocumentSnapshot>): MutableList<UserPost> {
            var list = mutableListOf<UserPost>()

            for (doc in snapshot) {
                val data = doc.data
                if (data != null) {
                    val item = UserPost(
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
            return list
        }
    }

     fun toJson( newId: String): HashMap<String, String> {
        return hashMapOf(
            DatabaseConstants.TITLE to this.title,
            DatabaseConstants.BODY to this.body,
            DatabaseConstants.CREATEDAT to this.createdAt,
            DatabaseConstants.ID to newId,
            DatabaseConstants.USERNAME to this.userName,
        )
    }

}


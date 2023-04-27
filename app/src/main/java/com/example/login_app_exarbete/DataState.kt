package com.example.login_app_exarbete

import com.example.login_app_exarbete.models.UserPost

sealed class DataState{
    class Success(val data: MutableList<UserPost>): DataState()
    class Failure(val message: String): DataState()
    object Loading: DataState()
    object Empty : DataState()
}

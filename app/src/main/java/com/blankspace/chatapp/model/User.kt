package com.blankspace.chatapp.model

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
data class User (
    val uID: String,
    val name: String)
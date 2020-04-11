package com.blankspace.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ChatActivity : AppCompatActivity() {

    lateinit var uID: String;
    lateinit var name: String;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        name = intent.getStringExtra("name")
        uID = intent.getStringExtra("uID")

        title = name
    }
}

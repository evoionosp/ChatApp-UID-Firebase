package com.blankspace.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : MyBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnNewUser.setOnClickListener {
            startActivity(Intent(this, RegisterUserActivity::class.java))
        }

        btnExisting.setOnClickListener {
            showSnack("Not Implemented yet", btnExisting)
        }

    }
}

package com.blankspace.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pixplicity.easyprefs.library.Prefs

class SplashActivity : MyBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

       if( Prefs.getString("UID", null) == null){
           startActivity(Intent(this, LoginActivity::class.java))
       } else {
           startActivity(Intent(this, MainActivity::class.java))
       }

    }
}

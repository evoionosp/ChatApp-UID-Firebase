package com.blankspace.chatapp;

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.blankspace.chatapp.model.User
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*


 class RegisterUserActivity : MyBaseActivity() {

        private lateinit var _uId: String
        private lateinit var uId: String
        override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
                generateUID()
                tvPublicKey.text = uId
                btnRegister.setOnClickListener {
                        var name = etDisplayName.text.toString()

                        if(name.isBlank()){
                                etDisplayName.error = "Please enter Display Name, so other can know its you."
                                return@setOnClickListener
                        } else {
                                createUser(uId, name)
                        }
                }
        }


        private fun generateUID(){
                _uId = UUID.randomUUID().toString()
                uId = _uId.replace("-","");
                if(uId.length != 32){
                        showToast("Something went wrong !")
                        finish()
                }
        }



        private fun createUser(uID: String, name: String){

                showProgress("Creating your UID ...")
                val user = User(uID, name)

                db.collection("Users")
                        .document(uID)
                        .set(user)
                        .addOnSuccessListener {
                                Prefs.putString("UID", uID)
                                Prefs.putString("_UID", _uId)
                                Prefs.putString("DisplayName", name)
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                        }.addOnFailureListener { e ->
                                Log.e(TAG, "Error creating user.", e)
                                showSnack("Error while creating data. Please try again.", tvPublicKey)
                        }.addOnCompleteListener {
                                dismissProgress()
                        }
        }

        companion object {
                val TAG = "RegisterUserActivity"
        }



}
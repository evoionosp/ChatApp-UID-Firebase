package com.blankspace.chatapp;

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.blankspace.chatapp.model.User
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*


public class RegisterUserActivity : MyBaseActivity() {

        lateinit var _uId: String
        lateinit var uId: String
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


        fun generateUID(){
                _uId = UUID.randomUUID().toString()
                uId = _uId.replace("-","");
                if(uId.length != 32){
                        showToast("Something went wrong !")
                        finish()
                }
        }



        fun createUser(uID: String, name: String){

                showProgress("Creating your UID ...")
                val user: User = User(uID, name)

                db.collection("users")
                        .document(uID)
                        .set(user)
                        .addOnSuccessListener {

                        }
                        .addOnFailureListener { e ->
                                Log.e(TAG, "Error adding document", e)

                        }.addOnCompleteListener {
                                dismissProgress()
                        }
        }



        companion object {
                val TAG = "RegisterUserActivity"
        }



}
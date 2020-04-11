package com.blankspace.chatapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.WhichButton
import com.afollestad.materialdialogs.actions.setActionButtonEnabled
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.blankspace.chatapp.adapter.MainAdapter
import com.blankspace.chatapp.model.User
import com.blankspace.chatapp.utils.UID_LENGTH
import com.blankspace.chatapp.utils.toast
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.inputManager


class MainActivity : MyBaseActivity() {

    lateinit var addDialog: MaterialDialog;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView?.layoutManager = LinearLayoutManager(this)

         addDialog = MaterialDialog(this)
             .title(R.string.add_chat)
             .message(R.string.enter_uid_to_chat)
             .input(waitForPositiveButton = false) { dialog, text ->
                val inputField = dialog.getInputField()
                inputField.hint = getString(R.string.uid_unique_id)
                val isValid = text.length == UID_LENGTH
                inputField.error = if (isValid) null else "UID length must be exactly $UID_LENGTH"
                dialog.setActionButtonEnabled(WhichButton.POSITIVE, isValid)
            }
             .positiveButton(R.string.add) { dialog ->
                 addChat(dialog.getInputField().text.toString())
            }
             .negativeButton(R.string.cancel)

        val users = ArrayList<User> ()
        users.add(User("sfsdfdfsdfsjjghj", "Foram"))
        users.add(User("uoluilyjtyjewae", "Shubh"))
        users.add(User("ilukuyjrhy", "Yash"))


        recyclerView?.adapter = MainAdapter(users, this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun addChat(uID: String) {

        uID.toast(this)

        if(true) {

        } else {
            MaterialDialog(this)
                .show {
                    title(R.string.not_found)
                    message(R.string.msg_user_not_found)
                positiveButton(R.string.close)
            }
        }
    }

    fun logout() {
        Prefs.clear();
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_add -> addDialog.show()
            R.id.menu_logout -> logout()
            else -> return false
        }
        return super.onOptionsItemSelected(item)
    }


}

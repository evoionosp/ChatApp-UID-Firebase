package com.blankspace.chatapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blankspace.chatapp.ChatActivity
import com.blankspace.chatapp.R
import com.blankspace.chatapp.model.User
import com.blankspace.chatapp.utils.listen
import com.blankspace.chatapp.utils.toast
import com.l4digital.fastscroll.FastScroller
import kotlinx.android.synthetic.main.person_item_view.view.*

class MainAdapter(val users : ArrayList<User>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {



    override fun getItemCount(): Int {
        return users.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.person_item_view, parent, false)


        return ViewHolder(view).listen { pos, type ->
            val item = users[pos]

            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("uID", item.uID)
            intent.putExtra("name", item.name)
           context.startActivity(intent);

        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDisplayName.text = users[position].name
        holder.tvMessage.text = users[position].uID
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val ivProfile = view.ivProfile;
    val tvDisplayName = view.tvDisplayName;
    val tvMessage = view.tvMessage;
}
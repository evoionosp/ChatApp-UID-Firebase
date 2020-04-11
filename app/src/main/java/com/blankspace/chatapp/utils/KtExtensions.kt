package com.blankspace.chatapp.utils

import android.app.ProgressDialog.show
import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView




fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}

fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this.toString(), duration).apply { show() }
}
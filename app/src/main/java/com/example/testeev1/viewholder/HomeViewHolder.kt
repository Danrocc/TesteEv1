package com.example.testeev1.viewholder

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.testeev1.GuestModel
import com.example.testeev1.databinding.RowGuestBinding
import com.example.testeev1.listener.OnGuestListener

class HomeViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Confirma remoção?")
                .setPositiveButton("Sim") { dialog, which -> listener.onDelete(guest.id) }
                .setNegativeButton("Não", null)
                .create()
                .show()

            true
        }
    }
}
package com.example.testeev1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeev1.GuestModel
import com.example.testeev1.databinding.RowGuestBinding
import com.example.testeev1.listener.OnGuestListener
import com.example.testeev1.viewholder.HomeViewHolder

class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>() {

    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position:Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuests(list: List<GuestModel>){
        guestList = list
        notifyDataSetChanged()
    }

    fun attachListener(guestListener: OnGuestListener) {
        listener = guestListener
    }

}
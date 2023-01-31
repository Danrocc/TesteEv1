package com.example.testeev1.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testeev1.GuestModel
import com.example.testeev1.adapter.HomeAdapter
import com.example.testeev1.constants.DataBaseConstants
import com.example.testeev1.databinding.FragmentHomeBinding
import com.example.testeev1.listener.OnGuestListener
import com.example.testeev1.ui.guestform.GuestFormActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private val adapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //Layout
        binding.recyclerHome.layoutManager = LinearLayoutManager(context)

        //Adapter
        binding.recyclerHome.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.home()
            }

        }

        adapter.attachListener(listener)



        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.home()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        viewModel.home.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}
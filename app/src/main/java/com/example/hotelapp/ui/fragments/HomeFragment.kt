package com.example.hotelapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hotelapp.R
import com.example.hotelapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.cardRooms.setOnClickListener {
            findNavController().navigate(R.id.roomListFragment)
        }
        
        binding.cardCustomers.setOnClickListener {
            findNavController().navigate(R.id.customerListFragment)
        }
        
        binding.cardReservations.setOnClickListener {
            findNavController().navigate(R.id.reservationListFragment)
        }
        
        binding.cardCalendar.setOnClickListener {
            findNavController().navigate(R.id.calendarFragment)
        }
        
        binding.cardServices.setOnClickListener {
            findNavController().navigate(R.id.serviceListFragment)
        }
        
        binding.cardInvoices.setOnClickListener {
            findNavController().navigate(R.id.invoiceListFragment)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 
package com.example.hotelapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelapp.R
import com.example.hotelapp.data.model.RoomStatus
import com.example.hotelapp.databinding.FragmentRoomListBinding
import com.example.hotelapp.ui.adapters.RoomAdapter
import com.example.hotelapp.ui.viewmodels.RoomViewModel
import com.example.hotelapp.utils.SampleDataProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RoomListFragment : Fragment() {
    
    private var _binding: FragmentRoomListBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: RoomViewModel by viewModels()
    private lateinit var roomAdapter: RoomAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater, 
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomListBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupClickListeners()
        observeViewModel()
    }
    
    private fun setupRecyclerView() {
        roomAdapter = RoomAdapter(
            onRoomClicked = { room ->
                val bundle = Bundle().apply {
                    putLong("roomId", room.id)
                }
                findNavController().navigate(
                    R.id.action_roomListFragment_to_roomDetailFragment,
                    bundle
                )
            },
            onReserveClicked = { room ->
                val bundle = Bundle().apply {
                    putLong("roomId", room.id)
                }
                findNavController().navigate(
                    R.id.action_roomListFragment_to_createReservationFragment,
                    bundle
                )
            }
        )
        
        binding.recyclerViewRooms.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = roomAdapter
        }
        
        // Cargar datos de muestra inicialmente
        roomAdapter.submitList(SampleDataProvider.getSampleRooms())
    }
    
    private fun setupClickListeners() {
        binding.fabAddRoom.setOnClickListener {
            findNavController().navigate(R.id.action_roomListFragment_to_addEditRoomFragment)
        }
        
        binding.btnFilterRooms.setOnClickListener {
            showFilterDialog()
        }
    }
    
    private fun observeViewModel() {
        viewModel.rooms.observe(viewLifecycleOwner) { rooms ->
            if (rooms != null && rooms.isNotEmpty()) {
                roomAdapter.submitList(rooms)
                binding.recyclerViewRooms.visibility = View.VISIBLE
                binding.tvNoRooms.visibility = View.GONE
            } else {
                // Verificar si estamos usando datos de muestra
                val sampleRooms = SampleDataProvider.getSampleRooms()
                if (sampleRooms.isNotEmpty()) {
                    roomAdapter.submitList(sampleRooms)
                    binding.recyclerViewRooms.visibility = View.VISIBLE
                    binding.tvNoRooms.visibility = View.GONE
                } else {
                    binding.recyclerViewRooms.visibility = View.GONE
                    binding.tvNoRooms.visibility = View.VISIBLE
                }
            }
        }
    }
    
    private fun showFilterDialog() {
        val items = arrayOf("Todas", "Disponibles", "Ocupadas", "En mantenimiento")
        var checkedItem = 0
        
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Filtrar por estado")
            .setSingleChoiceItems(items, checkedItem) { _, which ->
                checkedItem = which
            }
            .setPositiveButton("Aplicar") { _, _ ->
                applyFilter(checkedItem)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
    
    private fun applyFilter(filterOption: Int) {
        val allRooms = SampleDataProvider.getSampleRooms()
        
        val filteredRooms = when (filterOption) {
            0 -> allRooms
            1 -> allRooms.filter { it.status == RoomStatus.AVAILABLE }
            2 -> allRooms.filter { it.status == RoomStatus.OCCUPIED }
            3 -> allRooms.filter { it.status == RoomStatus.MAINTENANCE }
            else -> allRooms
        }
        
        roomAdapter.submitList(filteredRooms)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
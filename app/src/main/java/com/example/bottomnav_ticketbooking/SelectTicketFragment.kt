package com.example.bottomnav_ticketbooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bottomnav_ticketbooking.databinding.FragmentSelectTicketBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectTicketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectTicketFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSelectTicketBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         _binding = FragmentSelectTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            val ticketType =
                resources.getStringArray(R.array.jenis_tiket)
            val adapterTicketType = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, ticketType)
            adapterTicketType.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            spinnerJenisTiket.adapter = adapterTicketType

            btnOrder.setOnClickListener {
                val selectedTicketType = spinnerJenisTiket.selectedItem.toString()
                if (selectedTicketType == ticketType[0]) {
                    Toast.makeText(requireContext(), "Please select your ticket type!", Toast.LENGTH_SHORT).show()
                } else {
                    val currentDate = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(
                        Date()
                    )
                    Toast.makeText(requireContext(), "Ticket with type $selectedTicketType has been successfully booked on $currentDate", Toast.LENGTH_SHORT).show()
                    findNavController().apply {
                        previousBackStackEntry?.savedStateHandle?.set("ticketType", spinnerJenisTiket.selectedItem.toString())
                    }.navigateUp()
                }
            }
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SelectTicketFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectTicketFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
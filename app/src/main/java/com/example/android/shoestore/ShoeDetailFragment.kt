package com.example.android.shoestore

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.android.shoestore.databinding.ActivityMainBinding
import com.example.android.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {

    private  val viewModel:ShoeData by activityViewModels()
//    private lateinit var item:ShoeItem


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(
            inflater,R.layout.fragment_shoe_detail, container, false)

        binding.cancelButton.setOnClickListener { view:View ->
            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        // SAVE SHOE DATA as a list in viewModel then navigate
        binding.saveButton.setOnClickListener { view: View ->

            viewModel.item.name = binding.nameEditText.text.toString()
            viewModel.item.company = binding.companyEditText.text.toString()
            viewModel.item.size = binding.sizeEditText.text.toString()
            viewModel.item.description = binding.descriptionEditText.text.toString()

            viewModel.addData()

            view.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())


        }


        return binding.root
    }


}
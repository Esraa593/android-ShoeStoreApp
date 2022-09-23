package com.example.android.shoestore

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.shoestore.databinding.FragmentShoeListBinding
import com.example.android.shoestore.databinding.ListItemBinding

class ShoeListFragment : Fragment(), MenuProvider {

    private  val viewModel:ShoeData by activityViewModels()


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater,R.layout.fragment_shoe_list, container, false)

        binding.addFloatingButton.setOnClickListener { view:View ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        // GET DATA FROM VIEW MODEL
        viewModel.shoeList.observe(viewLifecycleOwner) {shoeList ->
            for(shoe in shoeList){
//                println("inside for loop, ${shoe.name}")
                addLayout(binding.shoeListLinearLayout,shoe,inflater,container)


//                val textView: TextView = binding.shoeItemView
//                textView.apply {
//                    this.text = shoe.name
//                    this.visibility = View.VISIBLE
//                }
//                binding.shoeListLinearLayout.addView(textView)
            }


        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }


    override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
    }
    override fun onMenuItemSelected(Item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(Item, requireView().findNavController())
    }


    private fun addLayout(layout: LinearLayout, shoe:ShoeItem,
                  inflater: LayoutInflater, container: ViewGroup?){

        val binding = DataBindingUtil.inflate<ListItemBinding>(
            inflater,R.layout.list_item, container, false)
        binding.apply {
            nameView.text = shoe.name
            companyView.text = shoe.company
            sizeView.text = shoe.size
            descriptionView.text = shoe.description
        }

        layout.addView(binding.root)
    }

}

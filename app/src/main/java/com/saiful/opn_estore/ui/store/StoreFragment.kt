package com.saiful.opn_estore.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.saiful.opn_estore.databinding.FragmentStoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : Fragment() {

    private val viewModel: StoreViewModel by viewModels()

    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStoreBinding.inflate(inflater, container, false)

//        viewModel.text.observe(viewLifecycleOwner, Observer {
//            binding.textHome.text = it
//        })

        binding.button.setOnClickListener {
            it.findNavController().navigate(StoreFragmentDirections.actionStoreScreenToOrderSummaryScreen())
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
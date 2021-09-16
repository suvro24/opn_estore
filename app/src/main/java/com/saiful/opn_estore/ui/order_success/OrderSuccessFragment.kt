package com.saiful.opn_estore.ui.order_success

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.saiful.opn_estore.R
import com.saiful.opn_estore.databinding.FragmentOrderSuccessBinding
import com.saiful.opn_estore.databinding.FragmentOrderSummaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderSuccessFragment : Fragment() {

    private val viewModel: OrderSuccessViewModel by viewModels()

    private lateinit var binding: FragmentOrderSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOrderSuccessBinding.inflate(inflater, container, false)

        binding.buttonDismiss.setOnClickListener {
            it.findNavController().navigate(OrderSuccessFragmentDirections.actionOrderSuccessScreenToStoreScreen())
        }

        return binding.root
    }

}
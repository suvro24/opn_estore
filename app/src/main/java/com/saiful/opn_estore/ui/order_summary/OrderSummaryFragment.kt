package com.saiful.opn_estore.ui.order_summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import com.saiful.opn_estore.databinding.FragmentOrderSummaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderSummaryFragment : Fragment() {

    private val viewModel: OrderSummaryViewModel by viewModels()

    private var _binding: FragmentOrderSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderSummaryBinding.inflate(inflater, container, false)


        binding.button2.setOnClickListener {
            //it.findNavController().navigate(OrderSummaryFragmentDirections.actionOrderSummaryScreenToOrderSuccessScreen())
            it.findNavController().navigate(OrderSummaryFragmentDirections.actionOrderSummaryScreenToOrderSuccessScreen())
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
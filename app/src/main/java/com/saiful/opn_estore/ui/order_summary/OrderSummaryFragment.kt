package com.saiful.opn_estore.ui.order_summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saiful.opn_estore.adapter.CartListAdapter
import com.saiful.opn_estore.adapter.ParentListAdapter
import com.saiful.opn_estore.databinding.FragmentOrderSummaryBinding
import com.saiful.opn_estore.ui.store.StoreFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderSummaryFragment : Fragment() {

    private val viewModel: OrderSummaryViewModel by viewModels()

    private lateinit var binding: FragmentOrderSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderSummaryBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@OrderSummaryFragment.viewLifecycleOwner
            vm = viewModel
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
    }

    private fun setUpUI() {
        val adapter = CartListAdapter()
        binding.cartList.adapter = adapter
        binding.goToOrder.setOnClickListener {
            findNavController().navigate(StoreFragmentDirections.actionStoreScreenToOrderSummaryScreen())
        }
    }
}
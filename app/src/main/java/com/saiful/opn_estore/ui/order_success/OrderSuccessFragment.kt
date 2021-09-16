package com.saiful.opn_estore.ui.order_success

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.saiful.opn_estore.R
import com.saiful.opn_estore.adapter.CartListAdapter
import com.saiful.opn_estore.databinding.FragmentOrderSuccessBinding
import com.saiful.opn_estore.databinding.FragmentOrderSummaryBinding
import com.saiful.opn_estore.ui.order_summary.OrderSummaryFragmentDirections
import com.saiful.opn_estore.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderSuccessFragment : Fragment() {

    private val args:OrderSuccessFragmentArgs by navArgs()

    private val viewModel: OrderSuccessViewModel by viewModels()
    private lateinit var binding: FragmentOrderSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderSuccessBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@OrderSuccessFragment.viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        viewModel.placeOrder(args.address)
    }

    private fun setUpUI() {
        viewModel.successEvent.observe(viewLifecycleOwner, EventObserver{
            binding.placeOrderTxt.text = getText(R.string.order_success)
            binding.placeOrderTxt.visibility = View.VISIBLE
            binding.dismissButton.visibility = View.GONE
        })

        viewModel.errorEvent.observe(viewLifecycleOwner, EventObserver{
            binding.placeOrderTxt.text = getText(R.string.order_failed)
            binding.placeOrderTxt.visibility = View.VISIBLE
            binding.dismissButton.visibility = View.GONE
        })

        binding.dismissButton.setOnClickListener {
            navigateTo(OrderSuccessFragmentDirections.actionOrderSuccessScreenToStoreScreen())
        }

    }

    private fun navigateTo(action: NavDirections){
        findNavController().navigate(action)
    }

}
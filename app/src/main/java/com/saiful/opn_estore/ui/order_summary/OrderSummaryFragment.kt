package com.saiful.opn_estore.ui.order_summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.saiful.opn_estore.R
import com.saiful.opn_estore.adapter.CartListAdapter
import com.saiful.opn_estore.adapter.ParentListAdapter
import com.saiful.opn_estore.databinding.FragmentOrderSummaryBinding
import com.saiful.opn_estore.ui.store.StoreFragmentDirections
import com.saiful.opn_estore.utils.EventObserver
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
        viewModel.fetchCart()
    }

    private fun setUpUI() {
        binding.dismissButton.visibility = View.GONE
        binding.emptyCart.visibility = View.GONE
        val adapter = CartListAdapter()
        binding.cartList.adapter = adapter

        viewModel.addressErrorEvent.observe(viewLifecycleOwner, EventObserver {
            showSnackBar(R.string.error_enter_address)
        })

        viewModel.navigationEvent.observe(viewLifecycleOwner, EventObserver {
            navigateTo(OrderSummaryFragmentDirections.actionOrderSummaryScreenToOrderSuccessScreen(viewModel.address.value!!))
        })

        binding.dismissButton.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun showSnackBar(msg:Int){
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).apply {
            animationMode = Snackbar.ANIMATION_MODE_SLIDE
        }.show()
    }

    private fun navigateTo(action: NavDirections){
        findNavController().navigate(action)
    }
}
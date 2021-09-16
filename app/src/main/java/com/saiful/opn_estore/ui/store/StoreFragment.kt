package com.saiful.opn_estore.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.saiful.opn_estore.adapter.ParentListAdapter
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.databinding.FragmentStoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : Fragment(), ParentListAdapter.OnAddRemoveProductClickListener {

    private val viewModel: StoreViewModel by viewModels()

    private lateinit var binding: FragmentStoreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@StoreFragment.viewLifecycleOwner
            vm = viewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        viewModel.fetchStoreAndProduct()
    }

    private fun setUpUI() {
        val adapter = ParentListAdapter(this)
        binding.parentList.adapter = adapter
        binding.goToOrder.setOnClickListener {
            navigateTo(StoreFragmentDirections.actionStoreScreenToOrderSummaryScreen())
        }
    }

    override fun onAddProduct(item: Product) {
        viewModel.addItem(item)
    }

    override fun onRemoveProduct(item: Product) {
        viewModel.removeItem(item)
    }

    private fun navigateTo(action: NavDirections) {
        findNavController().navigate(action)
    }
}
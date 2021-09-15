package com.saiful.opn_estore.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.saiful.opn_estore.adapter.ParentListAdapter
import com.saiful.opn_estore.data.model.ParentListItemModel
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.databinding.FragmentStoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : Fragment(), ParentListAdapter.OnAddProductClickListener {

    private val viewModel: StoreViewModel by viewModels()

    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStoreBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ParentListAdapter(this)
        binding.parentList.adapter = adapter

        viewModel.parentListItems.observe(viewLifecycleOwner) {
            it.forEach { item ->
                println(item)
            }
        }

        viewModel.fetchStoreAndProduct()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAddProduct(item: Product) {
        Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT).show()
    }
}
package com.saiful.opn_estore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.saiful.opn_estore.R
import com.saiful.opn_estore.data.model.ParentListItemModel
import com.saiful.opn_estore.data.model.Product
import com.saiful.opn_estore.data.model.Store
import com.saiful.opn_estore.databinding.ListItemProductBinding
import com.saiful.opn_estore.databinding.ListItemStoreBinding

class ParentListAdapter(private val addRemoveItemListener: OnAddRemoveProductClickListener) :
    ListAdapter<Product, ParentListAdapter.ProductViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ListItemProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(
            getItem(position) as Product,
            addRemoveItemListener
        )

    }

    class ProductViewHolder(private val binding: ListItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product, addRemoveItemListener: OnAddRemoveProductClickListener) {
            binding.apply {
                product = item
                addRemoveListener = addRemoveItemListener
                executePendingBindings()
            }
        }
    }


    interface OnAddRemoveProductClickListener {
        fun onAddProduct(item: Product)
        fun onRemoveProduct(item: Product)
    }

    private class DiffCallback : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem


        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {

            return oldItem == newItem
        }
    }
}


//class DiffCallback : DiffUtil.ItemCallback<Product>() {
//
//    override fun areItemsTheSame(
//        oldItem: Product,
//        newItem: Product
//    ): Boolean {
//        return oldItem == newItem
//
//
//    }
//
//    override fun areContentsTheSame(
//        oldItem: Product,
//        newItem: Product
//    ): Boolean {
//
//        return oldItem == newItem
//    }
//}

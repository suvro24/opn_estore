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
    ListAdapter<ParentListItemModel, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.list_item_store -> ParentViewHolder.StoreViewHolder(
                ListItemStoreBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            R.layout.list_item_product -> ParentViewHolder.ProductViewHolder(
                ListItemProductBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> throw IllegalArgumentException("Invalid ViewHolder Type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is ParentViewHolder.StoreViewHolder -> holder.bind(getItem(position) as Store)

            is ParentViewHolder.ProductViewHolder -> holder.bind(getItem(position) as Product, addRemoveItemListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Store -> R.layout.list_item_store
            is Product -> R.layout.list_item_product
            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }


    sealed class ParentViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        class StoreViewHolder(private val binding: ListItemStoreBinding) :
            ParentViewHolder(binding) {

            fun bind(item: Store) {
                binding.apply {
                    store = item
                    executePendingBindings()
                }
            }
        }

        class ProductViewHolder(private val binding: ListItemProductBinding) :
            ParentViewHolder(binding) {
            fun bind(item: Product, addRemoveItemListener: OnAddRemoveProductClickListener) {
                binding.apply {
                    product = item
                    addRemoveListener = addRemoveItemListener
                    executePendingBindings()
                }
            }
        }

    }

    interface OnAddRemoveProductClickListener{
        fun onAddProduct(item: Product)
        fun onRemoveProduct(item: Product)
    }
}


private class DiffCallback : DiffUtil.ItemCallback<ParentListItemModel>() {

    override fun areItemsTheSame(
        oldItem: ParentListItemModel,
        newItem: ParentListItemModel
    ): Boolean {
        return when {
            oldItem is Store && newItem is Store -> oldItem == newItem

            oldItem is Product && newItem is Product -> oldItem == newItem

            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: ParentListItemModel,
        newItem: ParentListItemModel
    ): Boolean {
        return when {
            oldItem is Store && newItem is Store -> oldItem == newItem

            oldItem is Product && newItem is Product -> oldItem == newItem

            else -> false
        }
    }
}

package com.example.myapplication

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.LayoutItemProductBinding

class MainAdapter(var mContext: Context) :
    ListAdapter<Product, MainAdapter.ProductViewHolder>(TaskDiffCallback()) {

    var listener: ListenerProduct? = null

    inner class ProductViewHolder(private val productItemBinding: LayoutItemProductBinding) :
        RecyclerView.ViewHolder(productItemBinding.root) {

        fun onBind(product: Product) {
            productItemBinding.product = product;
            Glide.with(productItemBinding.imgProduct.context)
                .load(productItemBinding.product?.url)
                .into(productItemBinding.imgProduct)
            productItemBinding.edtAmount.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    text: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    if (!TextUtils.isEmpty(text)) {
                        productItemBinding.product?.amount = Integer.parseInt(text.toString())
                    } else {
                        productItemBinding.product?.amount = 0
                    }

                }

                override fun afterTextChanged(s: Editable?) {}
            })
            productItemBinding.cbSelectProduct.setOnCheckedChangeListener { _, isChecked ->
                run {
                    productItemBinding.product?.isSelect = isChecked
                    listener?.onProductChecked()
                }
            }
            productItemBinding.btnCong.setOnClickListener {
                var amount = Integer.parseInt(productItemBinding.edtAmount.text.toString())
                amount++
                productItemBinding.product?.amount = amount
                productItemBinding.edtAmount.setText(product.amount.toString())
            }
            productItemBinding.btnTru.setOnClickListener {

            }
            productItemBinding.executePendingBindings()
        }
    }

    interface ListenerProduct {
        fun onProductChecked()
    }

    fun setOnChangeListener(listenerProduct: ListenerProduct) {
        this.listener = listenerProduct
    }


    class TaskDiffCallback : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemBinding =
            LayoutItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


}





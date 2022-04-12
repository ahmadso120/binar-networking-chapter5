package com.sopian.binarnetworking.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopian.binarnetworking.databinding.ItemCarBinding
import com.sopian.binarnetworking.model.Car

class CarAdapter(
    private var onDetail: (Car) -> Unit
) : ListAdapter<Car, CarAdapter.CarViewHolder>(CarDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder =
        CarViewHolder(
            ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            nameTv.text = item.name
            categoryTv.text = item.category
            priceTv.text = item.price.toString()
            Glide.with(image.context)
                .load(item.image)
                .into(image)
            root.setOnClickListener {
                onDetail(item)
            }
        }
    }

    class CarViewHolder(
        val binding: ItemCarBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

class CarDiffCallBack : DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean =
        oldItem == newItem
}
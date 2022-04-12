package com.sopian.binarnetworking.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sopian.binarnetworking.databinding.FragmentDetailBinding
import com.sopian.binarnetworking.model.Car

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDetail(args.car)
    }

    private fun showDetail(item: Car) {
        with(binding) {
            nameTv.text = item.name
            categoryTv.text = item.category
            priceTv.text = item.price.toString()
            Glide.with(image.context)
                .load(item.image)
                .into(image)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.sopian.binarnetworking.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sopian.binarnetworking.Injection
import com.sopian.binarnetworking.R
import com.sopian.binarnetworking.data.Result
import com.sopian.binarnetworking.databinding.FragmentHomeBinding
import com.sopian.binarnetworking.model.Car

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Injection.provideRepository()

        repository.getAllCar {
            when(it) {
                is Result.Success -> {
                    binding.progressBar.stopShimmer()
                    binding.progressBar.visibility = View.GONE
                    showData(it.data)
                }
                is Result.Error -> {
                    Log.d("HomeFragment", it.error)
                    binding.progressBar.stopShimmer()
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(binding.root,
                        resources.getString(R.string.error_message),
                        Snackbar.LENGTH_SHORT).show()
                }
                else -> {
                    binding.progressBar.stopShimmer()
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(binding.root,
                        resources.getString(R.string.error_message),
                        Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showData(listCar: List<Car>?) {
        val adapter = CarAdapter(
            onDetail = {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                findNavController().navigate(action)
            }
        )
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.list.layoutManager = layoutManager
        binding.list.adapter = adapter
        adapter.submitList(listCar)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
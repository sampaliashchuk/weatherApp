package com.example.interviewapp.view.fragments.capitals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewapp.databinding.FragmentCapitalBinding
import com.example.interviewapp.viewmodel.CapitalViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CapitalFragment : Fragment() {

    private var _binding: FragmentCapitalBinding? = null
    private val binding: FragmentCapitalBinding get() = _binding!!

    private val capitalViewModel by activityViewModels<CapitalViewModel>()

    private val capitalAdapter by lazy {
        CapitalAdapter(this::onCitySelected)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCapitalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            citiesRV.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = capitalAdapter
            }
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                capitalViewModel.state.collect { state ->
                    foreverSpinner.isVisible = state.isLoading
                    capitalAdapter.submitList(state.forecastToday)
                }
            }
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                capitalViewModel.error.collect { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun onCitySelected(city: String) {
        val action = CapitalFragmentDirections.actionMainscreenFragmentToDeatailsFragment(city)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
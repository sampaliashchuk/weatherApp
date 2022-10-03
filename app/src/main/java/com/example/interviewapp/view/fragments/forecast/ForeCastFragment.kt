package com.example.interviewapp.view.fragments.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewapp.R
import com.example.interviewapp.databinding.FragmentForecastBinding
import com.example.interviewapp.viewmodel.ForeCastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForeCastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null
    private val binding: FragmentForecastBinding get() = _binding!!
    private val foreCastViewModel by activityViewModels<ForeCastViewModel>()

    private val detailsAdapter by lazy { ForeCastAdapter() }
    private val args: ForeCastFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foreCastViewModel.getFiveDayForecast(args.name)
        with(binding) {
            state.text = getString(R.string.city).format(args.name)
            daysRV.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = detailsAdapter
            }
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                foreCastViewModel.weather.collect { weather ->
                    detailsAdapter.submitList(weather)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.interviewapp.view.fragments.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewapp.R
import com.example.interviewapp.databinding.ForecastItemBinding
import com.example.interviewapp.model.local.models.Weather

class ForeCastAdapter : RecyclerView.Adapter<ForeCastAdapter.DetailsViewHolder>() {

    private val weather = mutableListOf<Weather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(
            ForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(weather[position])
    }

    override fun getItemCount(): Int = weather.size

    fun submitList(weather: List<Weather>) {
        this.weather.clear()
        this.weather.addAll(weather)
        notifyDataSetChanged()
    }

    class DetailsViewHolder(
        private val binding: ForecastItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) = with(binding) {
            binding.curTemp.text = weather.currentTemp
            binding.highLow.text = root.context.getString(R.string.high_low_temp)
                .format(weather.lowToday, weather.highToday)
            precipitation.isVisible = weather.precipitation.isNotBlank()
            precipitation.text = weather.precipitation
            binding.date.text = weather.date
        }
    }
}
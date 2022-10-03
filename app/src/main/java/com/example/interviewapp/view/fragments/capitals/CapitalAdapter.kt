package com.example.interviewapp.view.fragments.capitals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewapp.R
import com.example.interviewapp.databinding.CapitalItemBinding

import com.example.interviewapp.model.local.models.Weather

class CapitalAdapter(
    private val listener: (city: String) -> Unit
) : RecyclerView.Adapter<CapitalAdapter.CapitalViewHolder>() {

    private val weather = mutableListOf<Weather>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CapitalViewHolder {
        return CapitalViewHolder(
            CapitalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).also { viewHolder ->
            viewHolder.itemView.setOnClickListener {
                listener(weather[viewHolder.adapterPosition].cityName)
            }
        }
    }

    override fun onBindViewHolder(holder: CapitalViewHolder, position: Int) {
        holder.bind(weather[position])
    }

    override fun getItemCount(): Int = weather.size

    fun submitList(weather: List<Weather>) {
        this.weather.clear()
        this.weather.addAll(weather)
        notifyDataSetChanged()
    }

    class CapitalViewHolder(
        private val binding: CapitalItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) = with(binding) {
            cityName.text = weather.cityName
            state.text = weather.state
            highLow.text = root.context.getString(R.string.high_low_temp)
                .format(weather.lowToday, weather.highToday)
            curTemp.text = weather.currentTemp
            precipitation.isVisible = weather.precipitation.isNotBlank()
            precipitation.text = weather.precipitation
            timeWhenUpdated.text = weather.lasUpdated
        }
    }
}
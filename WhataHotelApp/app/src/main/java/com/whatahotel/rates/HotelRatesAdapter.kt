package com.whatahotel.rates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HotelRatesAdapter : ListAdapter<HotelRate, HotelRatesAdapter.HotelViewHolder>(HotelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hotel_rate, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameText: TextView = itemView.findViewById(R.id.hotelName)
        private val locationText: TextView = itemView.findViewById(R.id.hotelLocation)
        private val starsText: TextView = itemView.findViewById(R.id.hotelStars)
        private val currentRateText: TextView = itemView.findViewById(R.id.currentRate)
        private val originalRateText: TextView = itemView.findViewById(R.id.originalRate)
        private val discountBadge: TextView = itemView.findViewById(R.id.discountBadge)
        private val availabilityText: TextView = itemView.findViewById(R.id.availability)
        private val perksText: TextView = itemView.findViewById(R.id.perks)

        fun bind(hotel: HotelRate) {
            nameText.text = hotel.name
            locationText.text = hotel.location
            starsText.text = "★".repeat(hotel.stars)
            currentRateText.text = "$${String.format("%.0f", hotel.currentRate)}"

            if (hotel.hasDiscount) {
                originalRateText.text = "$${String.format("%.0f", hotel.originalRate)}"
                originalRateText.visibility = View.VISIBLE
                discountBadge.text = "${hotel.discountPercentage}% OFF"
                discountBadge.visibility = View.VISIBLE
            } else {
                originalRateText.visibility = View.GONE
                discountBadge.visibility = View.GONE
            }

            availabilityText.text = hotel.availability

            if (hotel.perks.isNotEmpty()) {
                perksText.text = "✓ " + hotel.perks.joinToString(" • ")
                perksText.visibility = View.VISIBLE
            } else {
                perksText.visibility = View.GONE
            }
        }
    }

    class HotelDiffCallback : DiffUtil.ItemCallback<HotelRate>() {
        override fun areItemsTheSame(oldItem: HotelRate, newItem: HotelRate): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HotelRate, newItem: HotelRate): Boolean {
            return oldItem == newItem
        }
    }
}

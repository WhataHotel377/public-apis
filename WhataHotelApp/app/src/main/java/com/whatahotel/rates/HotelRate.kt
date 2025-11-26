package com.whatahotel.rates

data class HotelRate(
    val id: Int,
    val name: String,
    val location: String,
    val stars: Int,
    val currentRate: Double,
    val originalRate: Double,
    val currency: String = "USD",
    val availability: String,
    val perks: List<String> = emptyList(),
    val lastUpdated: Long = System.currentTimeMillis()
) {
    val discountPercentage: Int
        get() = if (originalRate > 0) {
            ((originalRate - currentRate) / originalRate * 100).toInt()
        } else 0

    val hasDiscount: Boolean
        get() = currentRate < originalRate
}

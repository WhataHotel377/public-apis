package com.whatahotel.rates

import kotlinx.coroutines.delay
import kotlin.random.Random

class HotelRepository {

    private val baseHotels = listOf(
        HotelRate(
            id = 1,
            name = "Four Seasons Resort",
            location = "Maui, Hawaii",
            stars = 5,
            currentRate = 850.0,
            originalRate = 1200.0,
            availability = "3 rooms left",
            perks = listOf("Free Breakfast", "Spa Credit $100", "Room Upgrade")
        ),
        HotelRate(
            id = 2,
            name = "Ritz-Carlton",
            location = "New York, NY",
            stars = 5,
            currentRate = 675.0,
            originalRate = 950.0,
            availability = "Available",
            perks = listOf("Free Breakfast", "Late Checkout")
        ),
        HotelRate(
            id = 3,
            name = "Mandarin Oriental",
            location = "Paris, France",
            stars = 5,
            currentRate = 920.0,
            originalRate = 1100.0,
            availability = "2 rooms left",
            perks = listOf("Free Breakfast", "Airport Transfer", "$150 Dining Credit")
        ),
        HotelRate(
            id = 4,
            name = "St. Regis Resort",
            location = "Bora Bora",
            stars = 5,
            currentRate = 1450.0,
            originalRate = 1850.0,
            availability = "Limited",
            perks = listOf("Free Breakfast", "Water Sports", "Spa Credit $200")
        ),
        HotelRate(
            id = 5,
            name = "The Peninsula",
            location = "Hong Kong",
            stars = 5,
            currentRate = 580.0,
            originalRate = 750.0,
            availability = "Available",
            perks = listOf("Free Breakfast", "Harbor View Upgrade")
        ),
        HotelRate(
            id = 6,
            name = "Belmond Hotel",
            location = "Venice, Italy",
            stars = 5,
            currentRate = 720.0,
            originalRate = 890.0,
            availability = "5 rooms left",
            perks = listOf("Free Breakfast", "Gondola Ride", "Welcome Prosecco")
        ),
        HotelRate(
            id = 7,
            name = "Park Hyatt",
            location = "Tokyo, Japan",
            stars = 5,
            currentRate = 650.0,
            originalRate = 820.0,
            availability = "Available",
            perks = listOf("Free Breakfast", "City View", "Evening Cocktails")
        ),
        HotelRate(
            id = 8,
            name = "Aman Resort",
            location = "Phuket, Thailand",
            stars = 5,
            currentRate = 890.0,
            originalRate = 1150.0,
            availability = "4 rooms left",
            perks = listOf("Free Breakfast", "Spa Treatment", "Sunset Cruise")
        )
    )

    suspend fun fetchHotelRates(): List<HotelRate> {
        // Simulate network delay
        delay(1500)

        // Simulate live rate updates with small random variations
        return baseHotels.map { hotel ->
            val variation = Random.nextDouble(-50.0, 30.0)
            val newRate = (hotel.currentRate + variation).coerceAtLeast(hotel.currentRate * 0.85)

            hotel.copy(
                currentRate = String.format("%.2f", newRate).toDouble(),
                lastUpdated = System.currentTimeMillis()
            )
        }
    }

    suspend fun refreshRates(currentRates: List<HotelRate>): List<HotelRate> {
        delay(1000)

        return currentRates.map { hotel ->
            // Smaller variations for refresh
            val variation = Random.nextDouble(-20.0, 15.0)
            val newRate = (hotel.currentRate + variation).coerceIn(
                hotel.currentRate * 0.90,
                hotel.originalRate * 0.95
            )

            hotel.copy(
                currentRate = String.format("%.2f", newRate).toDouble(),
                lastUpdated = System.currentTimeMillis()
            )
        }
    }
}

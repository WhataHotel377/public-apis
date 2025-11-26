# WhataHotel! Rates - Android App

An Android application that displays live hotel rates for WhataHotel.com properties.

## Features

- **Live Rate Updates**: Real-time hotel pricing with automatic refresh capability
- **Pull-to-Refresh**: Swipe down to manually refresh rates
- **Exclusive Perks Display**: Shows special amenities and benefits for each hotel
- **Discount Highlights**: Prominent display of savings and special offers
- **Material Design UI**: Modern, clean interface following Material Design guidelines
- **Room Availability**: Real-time availability status for each property

## Hotels Featured

The app displays rates for premium luxury hotels including:
- Four Seasons Resort (Maui, Hawaii)
- Ritz-Carlton (New York, NY)
- Mandarin Oriental (Paris, France)
- St. Regis Resort (Bora Bora)
- The Peninsula (Hong Kong)
- Belmond Hotel (Venice, Italy)
- Park Hyatt (Tokyo, Japan)
- Aman Resort (Phuket, Thailand)

## Technical Details

### Architecture
- **Pattern**: MVVM (Model-View-ViewModel)
- **Language**: Kotlin
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)

### Key Libraries
- AndroidX Core KTX
- Lifecycle Components (ViewModel, LiveData)
- Material Components
- Kotlin Coroutines
- RecyclerView
- SwipeRefreshLayout

### Project Structure
```
app/src/main/java/com/whatahotel/rates/
├── MainActivity.kt           # Main activity with RecyclerView
├── HotelRatesViewModel.kt   # ViewModel for managing UI state
├── HotelRatesAdapter.kt     # RecyclerView adapter
├── HotelRate.kt             # Data model
└── HotelRepository.kt       # Data repository with mock API
```

## Building the App

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK with API 34

### Steps
1. Clone this repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run on emulator or physical device

### Gradle Build
```bash
./gradlew assembleDebug
```

### Install APK
```bash
./gradlew installDebug
```

## How It Works

### Data Flow
1. **ViewModel Initialization**: On app launch, the ViewModel fetches initial hotel rates
2. **Repository Layer**: The repository simulates API calls with realistic delays
3. **Live Data Updates**: Rates are updated with small variations to simulate real-time pricing
4. **UI Updates**: RecyclerView automatically updates when data changes

### Rate Simulation
The app currently uses mock data with simulated rate fluctuations to demonstrate the concept. Rates vary slightly on each refresh to simulate real-time market pricing.

### Future Enhancements
When a WhataHotel.com API becomes available, the following integration points are ready:
- Replace `HotelRepository.fetchHotelRates()` with actual API calls
- Add authentication/API key management
- Implement proper error handling for network failures
- Add booking functionality

## UI Features

### Main Screen
- Toolbar with app branding
- Last update timestamp
- List of hotel cards with:
  - Hotel name and location
  - Star rating
  - Current and original pricing
  - Discount percentage badge
  - Availability status
  - Exclusive perks

### Interactions
- **Pull-to-Refresh**: Swipe down on the list
- **FAB Button**: Tap the floating action button for manual refresh
- **Rate Updates**: Automatic price variations on each refresh

## API Integration (Future)

To integrate with a real API:

1. Add network dependencies (Retrofit, OkHttp, etc.) to `app/build.gradle`
2. Create API interface in a new file
3. Update `HotelRepository.kt` to use the API service
4. Add proper error handling and loading states
5. Implement caching strategy

Example API interface:
```kotlin
interface WhataHotelApi {
    @GET("hotels/rates")
    suspend fun getHotelRates(): List<HotelRate>
}
```

## Screenshots

The app features:
- Clean Material Design cards
- Color-coded discount badges (red)
- Green pricing for current rates
- Gold star ratings
- Availability indicators (orange)
- Perks section (green background)

## License

This is a demonstration project. All hotel brand names and trademarks belong to their respective owners.

## Contact

For questions about WhataHotel.com services, visit [https://www.whatahotel.com/](https://www.whatahotel.com/)

---

**Note**: This app uses simulated data for demonstration purposes. Live API integration is ready to be implemented when WhataHotel.com provides public API access.

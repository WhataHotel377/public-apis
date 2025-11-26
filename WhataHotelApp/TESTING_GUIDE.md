# WhataHotel App - Testing Guide

## Prerequisites

### Required Software
- **Java Development Kit (JDK) 17 or later**
  - Check version: `java -version`
  - Download: https://www.oracle.com/java/technologies/downloads/

- **Android SDK** (comes with Android Studio)
  - Or install command-line tools: https://developer.android.com/studio#command-tools

- **ADB (Android Debug Bridge)** (comes with Android SDK)
  - Check if available: `adb version`

### Optional but Recommended
- **Android Studio** (easiest method)
  - Download: https://developer.android.com/studio

## Testing Methods

### Method 1: Android Studio (Easiest)

1. **Install Android Studio**
   ```bash
   # Download from https://developer.android.com/studio
   ```

2. **Open Project**
   - File → Open → Select `WhataHotelApp` folder
   - Wait for Gradle sync to complete

3. **Setup Emulator**
   - Tools → Device Manager
   - Create Device → Select Pixel 6
   - Download system image (API 34 recommended)

4. **Run App**
   - Click green Run button (▶️)
   - Select emulator
   - Wait for build and installation

### Method 2: Command Line with Emulator

1. **Start Emulator**
   ```bash
   # List available emulators
   emulator -list-avds

   # Start an emulator
   emulator -avd <emulator_name> &
   ```

2. **Build and Install**
   ```bash
   cd WhataHotelApp
   ./gradlew installDebug
   ```

3. **Launch App**
   ```bash
   adb shell am start -n com.whatahotel.rates/.MainActivity
   ```

### Method 3: Physical Android Device

1. **Enable Developer Mode**
   - Settings → About Phone
   - Tap "Build Number" 7 times

2. **Enable USB Debugging**
   - Settings → Developer Options
   - Enable "USB Debugging"

3. **Connect Device**
   ```bash
   # Verify connection
   adb devices

   # You should see:
   # List of devices attached
   # XXXXXXXXXX    device
   ```

4. **Install App**
   ```bash
   cd WhataHotelApp
   ./gradlew installDebug
   ```

5. **Launch**
   - Open app from device menu
   - Or: `adb shell am start -n com.whatahotel.rates/.MainActivity`

### Method 4: Build APK for Manual Installation

1. **Build APK**
   ```bash
   cd WhataHotelApp
   ./gradlew assembleDebug
   ```

2. **Locate APK**
   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```

3. **Transfer to Device**
   - Copy APK to device via USB, email, or cloud storage
   - Open APK on device to install
   - Enable "Install from Unknown Sources" if prompted

## What to Test

### 1. Initial Load
- ✅ App launches successfully
- ✅ Hotel list loads (may take 1-2 seconds)
- ✅ 8 hotels are displayed
- ✅ Last update time appears at top

### 2. UI Elements
Check each hotel card shows:
- ✅ Hotel name and location
- ✅ Star rating (★★★★★)
- ✅ Current price (green, large)
- ✅ Original price (crossed out, if discounted)
- ✅ Discount badge (red, showing percentage)
- ✅ Availability status (orange background)
- ✅ Perks list (green background)

### 3. Refresh Functionality
- ✅ Pull down to refresh (swipe down on list)
- ✅ Tap FAB button (floating button, bottom-right)
- ✅ Rates update with slight variations
- ✅ Last update time changes
- ✅ Loading indicator appears during refresh

### 4. Rate Changes
After refreshing:
- ✅ Prices change slightly (±$15-50)
- ✅ Discount percentages may update
- ✅ Rates stay within realistic range

### 5. Scrolling
- ✅ List scrolls smoothly
- ✅ All 8 hotels are visible when scrolling
- ✅ No UI glitches or overlaps

## Expected Behavior

### Hotel List
You should see 8 luxury hotels:
1. Four Seasons Resort - Maui, Hawaii (~$850/night)
2. Ritz-Carlton - New York, NY (~$675/night)
3. Mandarin Oriental - Paris, France (~$920/night)
4. St. Regis Resort - Bora Bora (~$1450/night)
5. The Peninsula - Hong Kong (~$580/night)
6. Belmond Hotel - Venice, Italy (~$720/night)
7. Park Hyatt - Tokyo, Japan (~$650/night)
8. Aman Resort - Phuket, Thailand (~$890/night)

### Live Updates
- Rates fluctuate ±$15-50 on each refresh
- Updates simulate real-time pricing changes
- Refresh takes ~1 second

### Perks Examples
- "Free Breakfast"
- "Spa Credit $100-200"
- "Room Upgrade"
- "Late Checkout"
- "Airport Transfer"

## Troubleshooting

### Build Fails
```bash
# Clear Gradle cache
cd WhataHotelApp
./gradlew clean
./gradlew build --refresh-dependencies
```

### App Won't Install
```bash
# Uninstall existing version
adb uninstall com.whatahotel.rates

# Reinstall
./gradlew installDebug
```

### Device Not Detected
```bash
# Restart ADB server
adb kill-server
adb start-server
adb devices
```

### Emulator Won't Start
```bash
# Check available emulators
emulator -list-avds

# If none exist, create one in Android Studio:
# Tools → Device Manager → Create Device
```

### Gradle Wrapper Missing
```bash
cd WhataHotelApp
gradle wrapper
chmod +x gradlew
```

## Performance Notes

- **Initial Load:** 1-2 seconds
- **Refresh Time:** ~1 second
- **Memory Usage:** ~50-80 MB
- **APK Size:** ~5-8 MB (debug build)

## Next Steps

After testing the basic functionality:

1. **Test on Different Devices**
   - Different screen sizes
   - Different Android versions (API 24-34)

2. **Check Rotation**
   - Rotate device to landscape
   - Verify data persists

3. **Background/Foreground**
   - Send app to background
   - Return to app
   - Verify state is maintained

4. **Network Scenarios**
   - App works offline (uses mock data)
   - No network calls required currently

## Automated Testing (Future)

To run unit tests (when added):
```bash
./gradlew test
```

To run instrumented tests (when added):
```bash
./gradlew connectedAndroidTest
```

## Logging

View app logs in real-time:
```bash
# View all logs
adb logcat

# Filter for app only
adb logcat | grep "WhataHotel"

# Clear logs
adb logcat -c
```

## Questions?

- Check README.md for architecture details
- Check APP_ICONS_NOTE.md for icon customization
- Review source code in app/src/main/java/com/whatahotel/rates/

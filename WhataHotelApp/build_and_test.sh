#!/bin/bash
# WhataHotel App - Build and Test Script

echo "======================================"
echo "WhataHotel App - Build & Test"
echo "======================================"
echo ""

# Navigate to project directory
cd "$(dirname "$0")"

echo "1. Checking Gradle wrapper..."
if [ ! -f "./gradlew" ]; then
    echo "❌ Gradle wrapper not found. Creating..."
    gradle wrapper
fi

chmod +x ./gradlew

echo ""
echo "2. Cleaning previous builds..."
./gradlew clean

echo ""
echo "3. Building debug APK..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Build successful!"
    echo ""
    echo "APK location:"
    echo "  app/build/outputs/apk/debug/app-debug.apk"
    echo ""

    # Check for connected devices
    echo "4. Checking for connected devices..."
    adb devices

    echo ""
    read -p "Install on connected device? (y/n) " -n 1 -r
    echo ""

    if [[ $REPLY =~ ^[Yy]$ ]]; then
        echo "Installing app..."
        ./gradlew installDebug

        if [ $? -eq 0 ]; then
            echo ""
            echo "✅ App installed successfully!"
            echo ""
            echo "To launch the app:"
            echo "  adb shell am start -n com.whatahotel.rates/.MainActivity"
        else
            echo "❌ Installation failed"
        fi
    fi
else
    echo ""
    echo "❌ Build failed. Check errors above."
fi

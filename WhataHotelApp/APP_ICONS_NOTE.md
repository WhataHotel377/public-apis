# App Icons Setup

## Default Icons

The app currently references the default Android launcher icons:
- `@mipmap/ic_launcher` - Main app icon
- `@mipmap/ic_launcher_round` - Round app icon variant

## Adding Custom Icons

To add custom WhataHotel branding icons:

1. **Using Android Studio**:
   - Right-click on `app/src/main/res` folder
   - Select `New > Image Asset`
   - Choose `Launcher Icons (Adaptive and Legacy)`
   - Upload your icon image
   - Configure foreground and background layers
   - Click `Next` and `Finish`

2. **Manual Setup**:
   Place icon files in the following directories:
   ```
   app/src/main/res/
   ├── mipmap-mdpi/ic_launcher.png (48x48)
   ├── mipmap-hdpi/ic_launcher.png (72x72)
   ├── mipmap-xhdpi/ic_launcher.png (96x96)
   ├── mipmap-xxhdpi/ic_launcher.png (144x144)
   └── mipmap-xxxhdpi/ic_launcher.png (192x192)
   ```

3. **Using Online Tools**:
   - Visit https://romannurik.github.io/AndroidAssetStudio/
   - Select "Launcher icon generator"
   - Upload your icon image
   - Download the generated zip file
   - Extract to `app/src/main/res/`

## Recommended Icon Design

For WhataHotel branding:
- Use hotel/luxury themed imagery
- Primary color: Purple (#6200EE)
- Include subtle "WH" monogram or hotel icon
- Ensure good contrast for visibility
- Follow Material Design icon guidelines

## Adaptive Icons (Android 8.0+)

For adaptive icons, create:
- `ic_launcher_foreground.xml` - Foreground layer
- `ic_launcher_background.xml` - Background layer

Adaptive icons allow the system to display the icon in different shapes across different devices.

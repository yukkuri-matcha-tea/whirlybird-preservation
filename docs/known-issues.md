# Known issues and verification status

## Passed

- Gradle debug build succeeds.
- APK is a valid ZIP.
- `apksigner verify`: v1 and v2 signatures valid.
- `zipalign -c -P 16 -v 4`: success.
- Package is `com.google.android.play.games.whirlybird`.
- Launcher is standalone `BoingoGameActivity`.
- Required runtime DEX classes/assets/resources are packaged.
- All five SoundPool OGG entries are STORE.
- No Google Play Games activity/service/provider is declared.
- No INTERNET permission or native library is packaged.
- Android 13 x86_64 emulator: install, cold launch, START, platform generation,
  player motion, score increase to 33, 15+ seconds of continuous game-loop
  execution, HOME/background and hot resume all passed without runtime errors.

## Open runtime gates

- The POCO was visible to Windows only as a WPD/MTP device; its USB ADB
  interface was not enumerated during this run. Physical-device installation
  and accelerometer control therefore remain open.
- Accelerometer movement, start/restart, scoring, collision, audio, background
  resume and recent-app icon/label require device verification.
- Side-by-side visual/physics comparison with the original installed split set
  remains open.
- The requested `split_gpdeku.config.arm64_v8a.apk` was missing, although static
  graph analysis shows it is unrelated to Whirlybird.
- Artifact is debug-signed for local use.

Do not report the project as fully preservation-validated until the device test
matrix above passes. On a crash, capture `adb logcat` and classify the first
Whirlybird-side frame before changing code.

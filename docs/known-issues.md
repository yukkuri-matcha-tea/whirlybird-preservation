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

## Physical-device validation

User-confirmed on 2026-08-18 with no reported problems:

- USB ADB installation on a physical device.
- Accelerometer-based horizontal control.
- Audible sound effects.
- Death, collision and restart flow.
- Recent-app task label and icon.
- Side-by-side visual and physics comparison with the original Google Play
  Games version.

These checks were performed and reported by the user rather than observed
directly by the preservation automation. Together with the automated APK checks
and Android 13 emulator run, the planned runtime validation matrix is complete.

## Remaining distribution limitations

- The requested `split_gpdeku.config.arm64_v8a.apk` was missing, although static
  graph analysis shows it is unrelated to Whirlybird.
- Artifact is debug-signed for local use.

On a future crash, capture `adb logcat` and classify the first Whirlybird-side
frame before changing code.

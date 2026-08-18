# Changelog

## preservation-4

- Changed the standalone application ID to the original-APK-derived `com.google.android.play.games.whirlybird` identifier.
- Retained the standalone launcher and all preservation compatibility fixes.
- Updated build, verification and ADB smoke-test scripts for the new package.
- Verified the APK package, launcher, signing, alignment, DEX/resources/assets and uncompressed SoundPool OGG entries.

## preservation-3

- Fixed the START-screen freeze caused by a JADX control-flow loss in the reverse platform-removal loop.
- Restored the loop exit from smali, preventing index underflow/overflow and `IndexOutOfBoundsException`.
- Prevented the game loop from joining itself on the exceptional stop path.
- Corrected the falling-animation control flow from smali.
- Added an automated ADB smoke test.
- Passed Android 13 emulator install, launch, START, platform generation, movement, scoring, continuous execution, background and resume checks.

## Initial preservation build

- Extracted the Whirlybird/Boingo game graph from Google Play Games `2025.09.66390`.
- Replaced Google Play Games launcher, dependency-injection and telemetry entry glue with a standalone Activity path.
- Preserved the original game loop, physics, collision, rendering, platform generation, scoring, animation, sensor and SoundPool logic.
- Stored all SoundPool OGG resources uncompressed to restore FileDescriptor-based loading on current Android.
- Removed network, Google account, Google Play services and native-library requirements from the packaged graph.

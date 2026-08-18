# Whirlybird Preservation

This is a preservation-oriented extraction of the original Whirlybird/Boingo
mini-game from Google Play Games `2025.09.66390`. It is not a Flappy Bird-style
reimplementation. The original game loop, rendering, physics, collision,
platform generation, scoring, animation, sensor input and sound classes are
retained from the DEX decompilation. Only the Play Games launcher, dependency
injection and telemetry entry glue were replaced for standalone startup.

## Current result

- Application ID: `com.google.android.play.games.whirlybird`
- App/task label: `Whirlybird`
- Launcher: original `BoingoGameActivity`, adapted to extend framework `Activity`
- Icon: original `@drawable/ic_whirlybird` traced from the source manifest
- Network permission: absent
- Google account, Play Games and Play Services: not required by the packaged graph
- SoundPool OGG files: packaged uncompressed (`STORE`)
- Native libraries: none required by the Whirlybird graph

The APK is statically verified, but physical-device gameplay and visual
comparison are still open because no ADB device was connected during this run.
See `docs/known-issues.md`.

## Build on Windows

Requirements:

- Android Studio JBR 21 (or another compatible JDK)
- Android SDK platform 36 and build-tools 36.0.0
- Internet access on the first Gradle dependency resolution

```powershell
$env:JAVA_HOME = 'C:\Program Files\Android\Android Studio\jbr'
$env:ANDROID_HOME = 'C:\Users\kaito\AppData\Local\Android\Sdk'
.\scripts\build.ps1
```

The result is `dist/Whirlybird.apk`. The script runs the automated verifier.
The produced artifact is debug-signed for local preservation/testing, not for
store publication.

## Input provenance

The analyzed input directory contained five APKs. The requested sixth split,
`split_gpdeku.config.arm64_v8a.apk`, was absent. Its absence does not block this
build: Whirlybird code/resources are in `base.apk`, and the game graph has no
JNI or gpdeku reference.

| APK | SHA-256 |
|---|---|
| `base.apk` | `477758E1C4F91A5826321A3457ED86F7BA7417919C659AFE02E8CBF48BC0910E` |
| `split_config.arm64_v8a.apk` | `194D58009BA252EBB917C30F78F94CFC367D4E163302ACC10948F936671E992C` |
| `split_config.ja.apk` | `E90BDBBCB7FF03C31B93D526FD2BF7C3345505BC092C988785C2E6F76E37A8F7` |
| `split_config.xxhdpi.apk` | `1234995B6A3D252E5CE6FA67A9A92097ADF53623684E80656290D5614E3295B3` |
| `split_gpdeku.apk` | `D5A18A87AC234042D9507000244918218912E8837CDBF9A265532567D4CFC22B` |

## Publication warning

This local working tree contains extracted Google code, art, audio, font and
metadata. Do not publish it as-is. A public version should distribute only an
extractor/patcher and require each user to supply their own matching APKs.

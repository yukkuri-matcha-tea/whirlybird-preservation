# Manifest analysis

## Original base APK

`WhirlybirdActivity` is a Play Games trampoline, not the game renderer.

| Attribute | WhirlybirdActivity | BoingoGameActivity |
|---|---|---|
| name | `...builtingames.trampoline.WhirlybirdActivity` | `...eastereggs.boingo.BoingoGameActivity` |
| process | not set; application process | not set; application process |
| taskAffinity | not set | not set |
| label | `@string/game_info_boingo_name` = Whirlybird | inherited application label |
| icon | `@drawable/ic_whirlybird` | inherited application icon |
| theme | `Theme.Games.App.Activity.DayNight` | `Theme.Replay.Games.Light` |
| launchMode | standard | `singleTop` |
| documentLaunchMode | not set | not set |
| exported | false | false |
| excludeFromRecents | not set | not set |
| noHistory | true | not set |
| intent filter | none | none |

The exported alias `com.google.android.play.games.whirlybird` targets
`WhirlybirdActivity` and handles the private action
`...builtingames.LAUNCH_BUILT_IN`. The trampoline resolves the game metadata
and launches `BoingoGameActivity` with title/icon/background extras.

`BoingoGameActivity.onResume()` calls `frp.m8651b()`, which is exactly a wrapper
around `Activity.setTaskDescription(label, bitmap, color)`. Therefore the
separate recent-app presentation was a separate task/launch flow, not a
separate process.

## Standalone manifest

The standalone APK makes `BoingoGameActivity` the exported MAIN/LAUNCHER
activity, retains `singleTop`, portrait and original icon/label, and uses the
affinity `com.google.android.play.games.whirlybird`. There is no separate
process and no INTERNET permission. `setTaskDescription()` is called with the
original icon and Whirlybird label.

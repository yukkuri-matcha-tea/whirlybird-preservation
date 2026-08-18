# Google dependencies

## Removed entry dependencies

- Play Games application and dependency injection
- account/profile lookup
- built-in game catalog and launch metadata resolution
- telemetry/protobuf logging in `onStart`
- Play Games internal task-description helper
- internal logger, locale-list and string-split helpers
- Firebase, Analytics, Phenotype and remote configuration
- gpdeku feature module/content provider

The game state and rendering graph itself does not invoke Google Play Services,
network APIs, reflection or JNI. Consequently the standalone manifest declares
no INTERNET permission, Google provider, receiver, service or custom
application class.

## Why Whirlybird disappears after network access

The trampoline obtains a built-in game catalog (`frs`) and searches for the ID
`com.google.android.play.games.whirlybird`. If the remotely/cached catalog no
longer contains that entry, `fsi` logs failure and finishes. This explains the
observed offline-first visibility followed by disappearance without requiring
the game DEX/resources to be deleted. The exact server flag name was not proven
and is not carried into the standalone APK.

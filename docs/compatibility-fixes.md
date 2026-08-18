# Compatibility fixes

## Sound resources

- Original: all five game OGG members (`res/FN.ogg`, `72.ogg`, `Mt.ogg`,
  `RT.ogg`, `ve.ogg`) were DEFLATE-compressed.
- Problem: `SoundPool.load(context, rawResourceId, 1)` calls
  `openRawResourceFd`; compressed members cannot provide an FD.
- Change: Gradle `androidResources.noCompress += "ogg"`.
- Verified result: all five named standalone raw OGG members have ZIP method
  STORE. Audio bytes and SoundPool timing/play calls are unchanged.

## Standalone entry

- Removed `ActivityC0146fg`, Play Games DI and telemetry from the Activity
  lifecycle.
- Retained the original game initialization, sensor filtering, collision
  dispatch, SoundPool setup, pause/resume behavior and task-description intent.
- Replaced `frp.m8651b` with its exact Android framework operation.

## Tiny common-code shims

- Internal logging -> `android.util.Log`.
- Internal newline splitter -> `String.split`/`Arrays.asList`.
- Internal locale-list wrapper -> framework `Configuration` locale access.
- Desugar unmodifiable set -> `Collections.unmodifiableSet`.
- Recovered the `gpi` animated-sprite array initialization order and synthetic
  callback constructor from smali after JADX emitted invalid Java.
- Recovered `gpi.m9091k()` control flow from smali; JADX incorrectly mapped the
  falling state to the death sprite.
- Guarded `gog.m9062f()` against joining its own game-loop thread. The original
  exception path otherwise deadlocks while attempting to report a render/update
  error; normal UI-thread pause/stop joining remains unchanged.
- Restored the branch from the reverse platform-removal pass to smali label
  `:goto_e`. JADX emitted `while (true)` without its exit, causing the index to
  underflow from `Integer.MIN_VALUE` to `Integer.MAX_VALUE`, throw
  `IndexOutOfBoundsException`, and freeze immediately after START.

These changes do not alter game constants or algorithms.

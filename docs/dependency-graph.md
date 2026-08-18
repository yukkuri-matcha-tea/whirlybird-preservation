# Dependency graph

```text
WhirlybirdActivity (C: Play Games trampoline; removed)
  -> fsi (C: account, metadata, Lottie splash, telemetry; removed)
  -> built-in game metadata
  -> BoingoGameActivity (A with entry glue removed)
       -> gog (A: SurfaceView, main loop, touch/state orchestration)
       -> gpi (A: player physics/state/animation)
       -> gpd (A: platform and pickup generation)
       -> gpc + got..gpg (A: platform hierarchy/collisions)
       -> goh + goe/gon/gol (A: entity and sprite rendering)
       -> gor/goq/gop/goo (A: score, high score, game over, start UI)
       -> gpl (A: SoundPool playback)
       -> Android accelerometer and framework graphics/audio APIs (B)
       -> original drawable/raw/string/dimen/color resources (A)
```

## Classification

- A — Whirlybird-specific: `goe` through `gpl`, except the missing obfuscation
  slot `gph` represented by the original animation-completion callback.
- B — portable common code: framework `Activity`, `SurfaceView`, `Canvas`,
  `SoundPool`, `SensorManager`, `SharedPreferences`, `NumberFormat`.
- C — Google/Play Games glue: `WhirlybirdActivity`, `fsi`, DI call
  `yug.m20704a`, telemetry fields/calls, `frp` task helper, internal locale,
  logging and collection wrappers. Removed or replaced with tiny platform shims.
- D — unrelated: every other Play Games activity/service/provider, gpdeku,
  Firebase, accounts, ads, Phenotype and the three native libraries in the ABI
  split.

The packaged minimum is one activity plus 34 small game/support class files,
22 game drawables, five sounds, one font and the text/dimension/color values.
The six `prebundled_games/whirlybird` files are retained as source metadata but
are not called by the runtime game loop.

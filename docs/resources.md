# Resources and assets

Runtime resources were traced from `R.*` references in `BoingoGameActivity` and
`goe`–`gpl`, then checked against `resources.arsc`/`public.xml`.

- Drawables: original player animation sheets, platform/hazard sheets, start/
  restart art and `ic_whirlybird`.
- Raw audio: death, jump, noogler hat, platform break and spring OGG files.
- Values: two colors, twelve dimensions, gameplay labels and one runtime ID.
- Font: `PressStart2P-Regular.ttf`; the original locale behavior uses monospace
  for Japanese and other listed scripts.
- Metadata assets: all six files under `assets/prebundled_games/whirlybird/`.

The source manifest directly identifies `@drawable/ic_whirlybird` as the
Whirlybird activity icon. This is used for the launcher and task; the smaller
metadata `icon.webp` is preserved but is not substituted for it.

`animation.json`, background, banner, preview and metadata icon belong to the
Play Games prebundled-game catalog/presentation. No direct reference from the
Boingo runtime graph was found. They are preserved unchanged as metadata, not
fed into a new renderer.

The ABI split contains three Google/Play Games native libraries, but the
Whirlybird graph has no native method or `System.loadLibrary` call. No `.so` is
packaged in the standalone APK.

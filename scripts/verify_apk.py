#!/usr/bin/env python3
import sys
import zipfile


def fail(message: str) -> None:
    raise SystemExit(f"FAIL: {message}")


apk = sys.argv[1]
expected_assets = {
    "assets/PressStart2P-Regular.ttf",
    "assets/prebundled_games/whirlybird/animation.json",
    "assets/prebundled_games/whirlybird/background.png",
    "assets/prebundled_games/whirlybird/banner.webp",
    "assets/prebundled_games/whirlybird/game_info.xml",
    "assets/prebundled_games/whirlybird/icon.webp",
    "assets/prebundled_games/whirlybird/preview.png",
}
expected_ogg_layouts = (
    {
        "res/raw/boingo_jump_sound.ogg",
        "res/raw/boingo_nooglerhat_sound.ogg",
        "res/raw/boingo_platformbreak_sound.ogg",
        "res/raw/boingo_playerdeath_sound.ogg",
        "res/raw/boingo_spring_sound.ogg",
    },
    # AGP's optimized release resource paths. The resources table retains the
    # public raw resource names and maps them to these original-style members.
    {"res/72.ogg", "res/FN.ogg", "res/Mt.ogg", "res/RT.ogg", "res/ve.ogg"},
)

with zipfile.ZipFile(apk) as archive:
    bad = archive.testzip()
    if bad:
        fail(f"corrupt ZIP member: {bad}")
    names = set(archive.namelist())
    missing = expected_assets - names
    if missing:
        fail(f"missing required entries: {sorted(missing)}")
    expected_oggs = next((layout for layout in expected_ogg_layouts if layout <= names), None)
    if expected_oggs is None:
        fail("required SoundPool OGG layout is missing")
    for name in sorted(expected_oggs):
        if archive.getinfo(name).compress_type != zipfile.ZIP_STORED:
            fail(f"SoundPool resource is compressed: {name}")
    dex_data = b"".join(
        archive.read(name) for name in names if name.startswith("classes") and name.endswith(".dex")
    )
    for descriptor in (
        b"BoingoGameActivity",
        b"Lp000/gog;",
        b"Lp000/gpi;",
        b"Lp000/gpd;",
        b"Lp000/gpl;",
    ):
        if descriptor not in dex_data:
            fail(f"required DEX class missing: {descriptor!r}")
    if any(name.startswith("lib/") and name.endswith(".so") for name in names):
        fail("unexpected native library packaged")

print("ZIP, required assets, DEX classes, native set and OGG STORE checks passed.")

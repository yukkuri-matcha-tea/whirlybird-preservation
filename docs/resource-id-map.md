# Resource ID map

The original DEX used fixed `0x7f...` IDs. Recompiling the extracted Java makes
all references resolve through the generated `R` class, so no manual DEX ID
patch is needed. The generated map is deterministic for this source set.

| Original ID | Type/name | New ID |
|---|---|---|
| `0x7f060026` | color/boingo_grey | `0x7f010000` |
| `0x7f060027` | color/boingo_white | `0x7f010001` |
| `0x7f070078`–`0x7f070083` | dimen/boingo_* in source order | `0x7f020000`–`0x7f02000b` |
| `0x7f0800bc` | drawable/breakable_platform | `0x7f030000` |
| `0x7f0800bd` | drawable/breakable_platform_sheet | `0x7f030001` |
| `0x7f0800cb` | drawable/cloud_platform | `0x7f030002` |
| `0x7f0800cc` | drawable/cloud_platform_sheet | `0x7f030003` |
| `0x7f08011b` | drawable/device_tilt_sheet | `0x7f030004` |
| `0x7f0801f6` | drawable/hider_platform_sheet | `0x7f030005` |
| `0x7f080243` | drawable/ic_restart_pixel | `0x7f030006` |
| `0x7f080253` | drawable/ic_whirlybird | `0x7f030007` |
| `0x7f080257`–`0x7f080258` | drawable/jump_* | `0x7f030008`–`0x7f030009` |
| `0x7f080281` | drawable/moving_platform_sheet | `0x7f03000a` |
| `0x7f0802b0`–`0x7f0802b1` | drawable/noogler_hat_sheet, normal_platform | `0x7f03000b`–`0x7f03000c` |
| `0x7f080378`–`0x7f08037b` | drawable/player* | `0x7f03000d`–`0x7f030010` |
| `0x7f0803fd`–`0x7f080401` | drawable/spikes, spring, stickler* | `0x7f030011`–`0x7f030015` |
| `0x7f0b0227` | id/games__boingo__surface_view | `0x7f040000` |
| `0x7f130000`–`0x7f130004` | raw/boingo_* | `0x7f050000`–`0x7f050004` |
| `0x7f14002b`–`0x7f14002f` | string/boingo_* | `0x7f060001`–`0x7f060005` |
| `0x7f1401d2`–`0x7f1401d3` | game_info_boingo_* | `0x7f060006`–`0x7f060007` |

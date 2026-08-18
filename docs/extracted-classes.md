# Extracted classes

| Class | Role | Preservation status |
|---|---|---|
| `BoingoGameActivity` | lifecycle, sensor, collision dispatch, SoundPool setup | original methods retained; Play Games superclass/DI/telemetry removed |
| `gog` | SurfaceView, frame loop, state transitions, touch input, draw/update | extracted; Google logger/split/color helpers replaced |
| `goh` | base game entity, coordinate/rectangle collision | extracted |
| `goe`, `gon`, `gol`, `gom`, `goi` | animated/static sprites, callback, bitmap transform | extracted |
| `gok` | coordinate scaling/interpolation | extracted |
| `goo` | start button | extracted |
| `gop` | game-over/restart display | extracted |
| `goq`, `gor` | high-score marker and score persistence/rendering | extracted; locale helper replaced |
| `gos` | pixel-font selection by language | extracted; locale collection helper replaced |
| `gpc` | platform base and collision animation | extracted; synthetic constructor restored from smali semantics |
| `got`, `gou`, `gov`, `gox`, `goz`, `gpe`, `gpf`, `gpg` | concrete platform/hazard variants | extracted |
| `goy`, `gow`, `gpa`, `gpb` | platform state/callback helpers | extracted |
| `gpd` | random platform/pickup factory | extracted; desugar collection wrapper replaced by JDK equivalent |
| `gpi` | player motion, gravity/jump/death/animation state | extracted; constructor ordering repaired from smali |
| `gpj`, `gpk` | hat pickup/entity | extracted |
| `gpl` | five SoundPool IDs and playback | extracted |
| `gof` | point-in-rectangle touch hit test | extracted |

No physical constants, collision sizes, generation probabilities, animation
frame durations or audio timing were guessed. The one JADX-broken `gpi`
constructor was reconstructed against `gpi.smali` instructions and original
resource IDs.

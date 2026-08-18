# Whirlybird Preservation

![Whirlybird preview](app/src/main/assets/prebundled_games/whirlybird/preview.png)

旧 Google Play Games に内蔵されていたミニゲーム **Whirlybird** を、本体アプリから分離して単独起動できるAndroid APKとして保存する非公式プロジェクトです。

これはWhirlybird風ゲームの新規制作やリメイクではありません。Google Play Games `2025.09.66390` のDEXから摘出したゲームループ、描画、物理演算、衝突判定、足場生成、スコア、アニメーション、センサー入力、効果音処理を可能な限り維持し、Google Play Games固有の起動・DI・Telemetry部分だけを独立起動に必要な最小範囲で置き換えています。

> 再実装より摘出。改善より保存。新しさより本家の挙動。

## 重要な注意

- Google LLCによる公式アプリ、公式移植、公式配布物ではありません。
- Google、Google Play、Google Play Gamesおよび各名称・素材の権利は、それぞれの権利者に帰属します。
- このリポジトリには解析・保存目的で抽出されたコード、画像、音声、フォント、メタデータが含まれます。
- 現在は権利確認なしに再配布しないため、リポジトリを**非公開**にしています。
- ストア公開や一般配布を目的とした署名・ライセンス状態ではありません。

詳細は [NOTICE.md](NOTICE.md) を参照してください。

## 現在の成果

| 項目 | 内容 |
|---|---|
| アプリ名 | Whirlybird |
| Application ID | `com.google.android.play.games.whirlybird` |
| バージョン | `preservation-4`（versionCode 4） |
| Launcher | `BoingoGameActivity` |
| 対応Android | minSdk 23 / targetSdk 35 |
| ネット接続 | 不要、`INTERNET` permissionなし |
| Googleアカウント | 不要 |
| Google Play Games | 不要 |
| Google Play services | パッケージ済み依存グラフでは不要 |
| Native library | なし |
| 署名 | ローカル検証用debug署名 |

## どこまで元の実装か

以下の主要部分は、逆コンパイル結果とsmaliを照合しながら元DEX由来の処理を維持しています。

- ゲームループとフレーム更新
- プレイヤーの移動、重力、ジャンプ、落下
- 加速度センサー入力とフィルタリング
- 足場の生成、移動、破壊、特殊足場
- 当たり判定、死亡、再スタート
- スコア処理
- Sprite描画とアニメーション
- SoundPoolによる効果音再生
- Activityのpause/resumeとTask表示

独立動作のため、Google Play Games内のActivity基盤、依存性注入、Telemetry、内部ロギングなどの入口部分だけをAndroid標準APIまたは小さなshimへ置換しています。ゲーム定数や物理アルゴリズムを目視で作り直してはいません。

解析の詳細は次の文書にあります。

- [Manifest解析](docs/manifest-analysis.md)
- [依存グラフ](docs/dependency-graph.md)
- [抽出クラス一覧](docs/extracted-classes.md)
- [Google依存の切り離し](docs/google-dependencies.md)
- [Resource解析](docs/resources.md)
- [Resource ID対応](docs/resource-id-map.md)
- [互換性修正](docs/compatibility-fixes.md)
- [検証状況・既知の問題](docs/known-issues.md)

## 修正した互換性問題

### SoundPoolのOGG読み込み

元APKではゲーム用OGGがDEFLATE圧縮されていました。`SoundPool.load()` が内部で `openRawResourceFd()` を使うため、圧縮されたresourceからFileDescriptorを取得できず、現行Androidでは `Resources$NotFoundException` が発生します。

Gradleの `androidResources.noCompress` で全5音源をZIP内の `STORE` に固定しています。音声データと再生タイミングは変更していません。

### START直後に止まる問題

JADXが足場を逆順に削除するループの終了分岐を欠落させていました。その結果、添字が整数オーバーフローして `IndexOutOfBoundsException` が発生し、例外処理からゲームループ自身を `join()` して停止していました。

smaliの分岐先を照合して本来のループ終了を復元し、異常時の自己joinも防止しました。通常のpause/stop時にUIスレッドからゲームスレッドを待つ挙動は維持しています。

## 動作確認状況

### 確認済み

- Gradle debug build
- APK ZIP整合性
- `apksigner verify`（v1/v2）
- `zipalign -c -P 16 -v 4`
- Package・Launcher Activity
- 必須DEXクラス、assets、resources
- 全5件のSoundPool OGGがSTORE
- 不要なGoogle Play Games Activity / Service / Providerがないこと
- `INTERNET` permissionとnative libraryがないこと
- Android 13 x86_64 emulatorへのインストールとcold launch
- START後の足場生成、プレイヤー移動、スコア33到達
- 15秒以上のゲームループ継続
- HOME移行、バックグラウンド、hot resume
- 上記テスト中のruntime errorなし

### 実機確認済み（ユーザー報告）

- USB ADB経由の実端末インストール
- 加速度センサーによる左右操作
- 効果音の実聴
- 死亡、衝突、再スタートの一連操作
- 最近使ったアプリ上のラベル・アイコン
- 元Google Play Games版との画面・物理挙動の並列比較

上記の実機項目は2026-08-18にユーザー本人が確認し、軒並み問題なしと報告しています。自動APK検査、Android 13エミュレーター検証、実端末でのゲームプレイ確認が揃った状態です。

## APKを使う

GitHubの [Releases](../../releases) にある `Whirlybird.apk` をダウンロードしてください。現状のAPKはdebug署名の保存・テスト用ビルドです。

ADBでインストールする場合：

```powershell
adb install -r Whirlybird.apk
```

直接起動する場合：

```powershell
adb shell am start -W -n `
  com.google.android.play.games.whirlybird/com.google.android.apps.play.games.features.eastereggs.boingo.BoingoGameActivity
```

旧パッケージ `com.kaito.whirlybird` を導入済みの場合、この版は別アプリとして共存します。不要なら旧版だけを手動でアンインストールしてください。

## Windowsでビルドする

### 必要環境

- Android Studio JBR 21、または互換JDK
- Android SDK Platform 36
- Android SDK Build-Tools 36.0.0
- 初回のGradle依存解決時のみインターネット接続

### ビルド

```powershell
$env:JAVA_HOME = 'C:\Program Files\Android\Android Studio\jbr'
$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"
.\scripts\build.ps1
```

生成物は `dist\Whirlybird.apk` です。ビルドスクリプトは続けて署名、alignment、package、Launcher、DEX、resource、asset、OGG圧縮方式などを検査します。

## ADBスモークテスト

USBデバッグ端末またはエミュレーターを接続して実行します。

```powershell
$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"
.\scripts\smoke-test-adb.ps1
```

複数端末がある場合：

```powershell
.\scripts\smoke-test-adb.ps1 -Serial '端末シリアル'
```

スクリプトはAPKの導入、cold launch、START操作、プロセス生存、既知の重大エラーを確認します。センサー、音、見た目、本家比較は自動判定の対象外ですが、本版では別途ユーザーによる実機確認も完了しています。

## 入力APKの出典

解析対象はGoogle Play Games `2025.09.66390` のsplit APK一式です。

| 入力 | SHA-256 |
|---|---|
| `base.apk` | `477758E1C4F91A5826321A3457ED86F7BA7417919C659AFE02E8CBF48BC0910E` |
| `split_config.arm64_v8a.apk` | `194D58009BA252EBB917C30F78F94CFC367D4E163302ACC10948F936671E992C` |
| `split_config.ja.apk` | `E90BDBBCB7FF03C31B93D526FD2BF7C3345505BC092C988785C2E6F76E37A8F7` |
| `split_config.xxhdpi.apk` | `1234995B6A3D252E5CE6FA67A9A92097ADF53623684E80656290D5614E3295B3` |
| `split_gpdeku.apk` | `D5A18A87AC234042D9507000244918218912E8837CDBF9A265532567D4CFC22B` |

依頼時に列挙されていた `split_gpdeku.config.arm64_v8a.apk` は入力フォルダに存在しませんでした。静的な依存グラフ解析ではWhirlybirdの実行経路にJNIまたはgpdeku参照はなく、今回の単独ビルドを妨げていません。

## リポジトリ構成

```text
app/                 Androidアプリ本体
  src/main/java/     摘出・互換修正したゲームコード
  src/main/res/      画像、音声、文字列、スタイル
  src/main/assets/   元ゲーム情報・プレビュー等
docs/                解析記録と互換性修正記録
scripts/build.ps1    ビルドと自動検証
scripts/verify-*     APK内部検査
scripts/smoke-*      ADB起動スモークテスト
```

`dist/`、`build/`、`.gradle/`、解析作業用の `work/`、入力APK、署名鍵はGit管理しません。

## 将来の公開形態

一般公開する場合は、抽出済みのGoogle由来コードや素材を直接配布する現在の構造ではなく、利用者が自分で用意した一致するGoogle Play Games APKからローカルで必要部分を抽出・patch・buildする方式へ移行する想定です。

現状の優先順位は、元コードと元データを維持した動作保存、現行Android互換性、再現可能なビルド、そして検証結果の記録です。

## 変更履歴

[CHANGELOG.md](CHANGELOG.md) を参照してください。

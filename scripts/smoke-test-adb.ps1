param(
    [string] $Serial = '',
    [string] $Apk = ''
)

$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $PSScriptRoot
if (-not $Apk) { $Apk = Join-Path $projectRoot 'dist\Whirlybird.apk' }
$sdkRoot = if ($env:ANDROID_HOME) { $env:ANDROID_HOME } else { Join-Path $env:LOCALAPPDATA 'Android\Sdk' }
$adb = Join-Path $sdkRoot 'platform-tools\adb.exe'
$target = @()
if ($Serial) { $target = @('-s', $Serial) }

& $adb @target install -r $Apk
if ($LASTEXITCODE -ne 0) { throw 'APK install failed' }
& $adb @target logcat -c
& $adb @target shell am force-stop com.google.android.play.games.whirlybird
& $adb @target shell am start -W -n 'com.google.android.play.games.whirlybird/com.google.android.apps.play.games.features.eastereggs.boingo.BoingoGameActivity'
Start-Sleep -Seconds 3

$sizeLine = (& $adb @target shell wm size | Select-String -Pattern 'Physical size').Line
if ($sizeLine -notmatch '(\d+)x(\d+)') { throw 'Could not determine display size' }
$x = [int]$Matches[1] / 2
$y = [int]$Matches[2] - 160
& $adb @target shell input tap $x $y
Start-Sleep -Seconds 5

$appPid = (& $adb @target shell pidof com.google.android.play.games.whirlybird).Trim()
if (-not $appPid) { throw 'Whirlybird process is not running' }
$errors = (& $adb @target logcat -d -v brief) -join "`n"
if ($errors -match 'Unable to render or update|FATAL EXCEPTION|Resources\$NotFoundException') {
    throw "Whirlybird runtime failure detected:`n$errors"
}

Write-Host "ADB smoke test passed; process $appPid remains alive after START."

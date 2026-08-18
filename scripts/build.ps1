$ErrorActionPreference = 'Stop'

$projectRoot = Split-Path -Parent $PSScriptRoot
$gradle = Join-Path $projectRoot 'gradlew.bat'
$sourceApk = Join-Path $projectRoot 'app\build\outputs\apk\debug\app-debug.apk'
$distDir = Join-Path $projectRoot 'dist'
$finalApk = Join-Path $distDir 'Whirlybird.apk'

if (-not $env:JAVA_HOME) {
    $studioJbr = 'C:\Program Files\Android\Android Studio\jbr'
    if (Test-Path -LiteralPath $studioJbr) { $env:JAVA_HOME = $studioJbr }
}
if (-not $env:ANDROID_HOME) {
    $userSdk = Join-Path $env:LOCALAPPDATA 'Android\Sdk'
    if (Test-Path -LiteralPath $userSdk) { $env:ANDROID_HOME = $userSdk }
}

& $gradle :app:assembleDebug --console=plain
if ($LASTEXITCODE -ne 0) { throw "Gradle build failed: $LASTEXITCODE" }

New-Item -ItemType Directory -Force -Path $distDir | Out-Null
Copy-Item -LiteralPath $sourceApk -Destination $finalApk -Force

& (Join-Path $PSScriptRoot 'verify-apk.ps1') -Apk $finalApk
Write-Host "Built: $finalApk"

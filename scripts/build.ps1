$ErrorActionPreference = 'Stop'

$projectRoot = Split-Path -Parent $PSScriptRoot
$gradle = Join-Path $projectRoot 'gradlew.bat'
$sourceApk = Join-Path $projectRoot 'app\build\outputs\apk\release\app-release.apk'
$distDir = Join-Path $projectRoot 'dist'
$finalApk = Join-Path $distDir 'Whirlybird.apk'
$signingProperties = Join-Path $projectRoot 'keystore.properties'

if (-not $env:JAVA_HOME) {
    $studioJbr = 'C:\Program Files\Android\Android Studio\jbr'
    if (Test-Path -LiteralPath $studioJbr) { $env:JAVA_HOME = $studioJbr }
}
if (-not $env:ANDROID_HOME) {
    $userSdk = Join-Path $env:LOCALAPPDATA 'Android\Sdk'
    if (Test-Path -LiteralPath $userSdk) { $env:ANDROID_HOME = $userSdk }
}

if (-not (Test-Path -LiteralPath $signingProperties)) {
    throw 'Release signing is not configured. Run .\scripts\setup-release-signing.ps1 first.'
}
$signing = ConvertFrom-StringData (Get-Content -Raw -LiteralPath $signingProperties)
if (-not $signing.signingCertificateSha256) {
    throw 'Release certificate metadata is missing. Run .\scripts\setup-release-signing.ps1 again.'
}

& $gradle :app:assembleRelease --console=plain
if ($LASTEXITCODE -ne 0) { throw "Gradle build failed: $LASTEXITCODE" }

New-Item -ItemType Directory -Force -Path $distDir | Out-Null
Copy-Item -LiteralPath $sourceApk -Destination $finalApk -Force

& (Join-Path $PSScriptRoot 'verify-apk.ps1') -Apk $finalApk -ExpectedSignerSha256 $signing.signingCertificateSha256
Write-Host "Built: $finalApk"

param(
    [Parameter(Mandatory = $true)]
    [string] $Apk,

    [string] $ExpectedSignerSha256 = 'e013a212e3ecce925b8dc852d0c237cd429f6818e93ab9c1f96600be452a6274'
)

$ErrorActionPreference = 'Stop'
$apkPath = (Resolve-Path -LiteralPath $Apk).Path
$sdkRoot = if ($env:ANDROID_HOME) { $env:ANDROID_HOME } else { Join-Path $env:LOCALAPPDATA 'Android\Sdk' }
$buildToolsRoot = Join-Path $sdkRoot 'build-tools'
$buildTools = Get-ChildItem -LiteralPath $buildToolsRoot -Directory |
    Sort-Object { [version]$_.Name } -Descending |
    Select-Object -First 1
if (-not $buildTools) { throw 'Android SDK build-tools not found' }

$aapt = Join-Path $buildTools.FullName 'aapt.exe'
$apksigner = Join-Path $buildTools.FullName 'apksigner.bat'
$zipalign = Join-Path $buildTools.FullName 'zipalign.exe'

& $apksigner verify --verbose $apkPath
if ($LASTEXITCODE -ne 0) { throw 'apksigner verification failed' }
$certificateInfo = (& $apksigner verify --print-certs $apkPath) -join "`n"
if ($LASTEXITCODE -ne 0) { throw 'APK certificate inspection failed' }
Write-Host $certificateInfo
if ($certificateInfo -notmatch [regex]::Escape($ExpectedSignerSha256.ToLowerInvariant())) {
    throw 'APK is not signed by the Whirlybird release certificate'
}
& $zipalign -c -P 16 4 $apkPath
if ($LASTEXITCODE -ne 0) { throw 'zipalign verification failed' }

$badging = (& $aapt dump badging $apkPath) -join "`n"
if ($badging -notmatch "package: name='com\.google\.android\.play\.games\.whirlybird'") { throw 'Unexpected package name' }
if ($badging -notmatch "launchable-activity: name='com\.google\.android\.apps\.play\.games\.features\.eastereggs\.boingo\.BoingoGameActivity'") { throw 'Launcher activity missing' }
if ($badging -match "uses-permission: name='android\.permission\.INTERNET'") { throw 'INTERNET permission must be absent' }

$python = Get-Command python -ErrorAction SilentlyContinue
if (-not $python) { $python = Get-Command py -ErrorAction SilentlyContinue }
if (-not $python) { throw 'Python is required for ZIP/DEX content verification' }
& $python.Source (Join-Path $PSScriptRoot 'verify_apk.py') $apkPath
if ($LASTEXITCODE -ne 0) { throw 'ZIP/DEX content verification failed' }

Get-FileHash -Algorithm SHA256 -LiteralPath $apkPath | Format-List
Write-Host 'APK verification passed.'

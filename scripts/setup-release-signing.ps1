param()

$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $PSScriptRoot
$signingDir = Join-Path $projectRoot 'signing'
$keyPath = Join-Path $signingDir 'whirlybird-release.jks'
$certificatePath = Join-Path $signingDir 'whirlybird-release.cer'
$propertiesPath = Join-Path $projectRoot 'keystore.properties'
$recoveryPath = Join-Path $signingDir 'SIGNING-KEY-RECOVERY.txt'
$keyAlias = 'whirlybird-release'
$utf8 = [Text.UTF8Encoding]::new($false)

$javaHome = if ($env:JAVA_HOME) { $env:JAVA_HOME } else { 'C:\Program Files\Android\Android Studio\jbr' }
$keytool = Join-Path $javaHome 'bin\keytool.exe'
if (-not (Test-Path -LiteralPath $keytool)) { throw "keytool not found: $keytool" }

function Update-CertificateMetadata {
    param([string] $StorePassword)

    & $keytool -exportcert `
        -keystore $keyPath `
        -storepass $StorePassword `
        -alias $keyAlias `
        -file $certificatePath
    if ($LASTEXITCODE -ne 0) { throw "certificate export failed: $LASTEXITCODE" }

    $certificateSha256 = (Get-FileHash -Algorithm SHA256 -LiteralPath $certificatePath).Hash.ToLowerInvariant()
    $propertiesText = Get-Content -Raw -LiteralPath $propertiesPath
    if ($propertiesText -notmatch '(?m)^signingCertificateSha256=') {
        [IO.File]::AppendAllText($propertiesPath, "signingCertificateSha256=$certificateSha256`n", $utf8)
    }
    return $certificateSha256
}

if ((Test-Path -LiteralPath $keyPath) -and (Test-Path -LiteralPath $propertiesPath)) {
    $existing = ConvertFrom-StringData (Get-Content -Raw -LiteralPath $propertiesPath)
    if (-not $existing.signingCertificateSha256) {
        $certificateSha256 = Update-CertificateMetadata -StorePassword $existing.storePassword
        Write-Host "Release certificate metadata added: $certificateSha256"
        exit 0
    }
    throw 'Release signing files already exist. Refusing to replace the update key.'
}
if ((Test-Path -LiteralPath $keyPath) -or (Test-Path -LiteralPath $propertiesPath)) {
    throw 'Incomplete release signing files exist. Restore the matching key and properties before continuing.'
}

New-Item -ItemType Directory -Force -Path $signingDir | Out-Null
$randomBytes = [byte[]]::new(36)
[Security.Cryptography.RandomNumberGenerator]::Fill($randomBytes)
$password = [Convert]::ToBase64String($randomBytes)

& $keytool -genkeypair -v `
    -keystore $keyPath `
    -storepass $password `
    -keypass $password `
    -alias $keyAlias `
    -keyalg RSA `
    -keysize 4096 `
    -validity 10000 `
    -dname 'CN=Whirlybird Preservation, OU=Preservation, O=Kaito, L=Tokyo, ST=Tokyo, C=JP'
if ($LASTEXITCODE -ne 0) { throw "keytool failed: $LASTEXITCODE" }

$properties = @(
    'storeFile=signing/whirlybird-release.jks'
    "storePassword=$password"
    "keyAlias=$keyAlias"
    "keyPassword=$password"
)
[IO.File]::WriteAllLines($propertiesPath, $properties, $utf8)
$certificateSha256 = Update-CertificateMetadata -StorePassword $password

$recovery = @(
    'WHIRLYBIRD RELEASE SIGNING KEY - KEEP PRIVATE AND BACKED UP'
    ''
    "Keystore: $keyPath"
    "Alias: $keyAlias"
    "Store password: $password"
    "Key password: $password"
    "Certificate SHA-256: $certificateSha256"
    ''
    'Losing this keystore or password makes future in-place APK updates impossible.'
    'Never commit or upload this file, the keystore, or keystore.properties.'
)
[IO.File]::WriteAllLines($recoveryPath, $recovery, $utf8)

Write-Host "Release key created: $keyPath"
Write-Host "Certificate SHA-256: $certificateSha256"
Write-Host "Private recovery information: $recoveryPath"
Write-Host 'Back up the entire signing directory to a secure offline location.'

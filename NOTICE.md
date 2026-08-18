# Notice, provenance and distribution status

## Project status

Whirlybird Preservation is an unofficial, preservation-oriented technical project. It is not produced, endorsed, distributed or supported by Google LLC.

## Third-party material

The working tree contains code, artwork, audio, font data and metadata extracted from a Google Play Games APK for analysis and preservation. Google, Google Play, Google Play Games and related names and assets are the property of their respective owners.

No ownership of the extracted material is claimed. No open-source license is granted for third-party material merely because it is present in this repository or publicly accessible on GitHub.

## Repository visibility

This repository is publicly accessible as a preservation and technical-research record. Public availability does not grant permission to reuse, relicense or redistribute third-party code, artwork, audio, fonts or metadata. Anyone reusing material from this repository is responsible for determining whether they have the necessary rights.

For a future public version, the preferred design is an extractor/patcher that requires each user to provide their own matching APK locally. Extracted Google material and generated APKs should remain outside the public Git history.

## APK status

Beginning with `preservation-5`, generated APKs are signed with a dedicated Whirlybird Preservation release certificate. This provides a stable update identity and APK integrity; it does not make the build official, confer Google endorsement or make it a Play Store release. The private signing key is excluded from Git and must never be published.

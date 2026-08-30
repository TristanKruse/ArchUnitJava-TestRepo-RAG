# Repository guidance

This repository is an executable consumer fixture for ArchUnitJava. Keep it independent from the
library implementation: application source must not import ArchUnitJava, and architecture checks
must use only the published public API from test scope.

The two deliberately invalid dependencies are part of the fixture contract. Tests must prove that
ArchUnitJava detects them; do not "fix" them unless the corresponding tests and documentation are
intentionally redesigned.

Run `./mvnw verify` (or `.\mvnw.cmd verify` on Windows) after changes.


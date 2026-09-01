# ArchUnitJava RAG Test Repository

[![Java 25](https://img.shields.io/badge/Java-25-E76F00?logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/25/)
[![ArchUnitJava](https://img.shields.io/badge/checked%20by-ArchUnitJava-5B45D6)](https://github.com/TristanKruse/ArchUnitJava)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.tristankruse/archunitjava.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.tristankruse/archunitjava/0.1.0)

An independent Maven consumer that proves ArchUnitJava can analyze a realistic compiled Java
application from outside the library repository.

The fixture models a small retrieval-augmented-generation service with API, application, domain,
infrastructure, and bootstrap packages. It contains healthy boundaries and two deliberate
violations. The test suite must both accept the healthy rules and reject the known bad dependencies.

## What this proves

| Contract | Expected result |
| --- | --- |
| Application behavior | The RAG application compiles and its functional test passes |
| Domain does not depend on infrastructure | Pass |
| Application uses domain ports rather than adapters | Pass |
| API does not bypass the application layer | Fail with `UnsafeSearchController` evidence |
| Infrastructure does not depend on delivery APIs | Fail with `LeakyAuditAdapter` evidence |
| Mermaid dependency graph generation | Pass and contain real application types |
| Configuration validation | Pass without executing target classes |

The deliberate violations are fixture data, not open defects. Their failure is asserted by JUnit,
so the Maven build itself remains green.

## Package architecture

```text
bootstrap ───────> api ───────> application ───────> domain
    │                                                   ▲
    └────────────────────> infrastructure ──────────────┘

Deliberate violations:
api -------------------> infrastructure
infrastructure --------> api
```

The intended architecture is also captured in [`architecture.puml`](architecture.puml).

## Run locally

ArchUnitJava `0.1.0` is resolved directly from Maven Central:

```shell
git clone https://github.com/TristanKruse/ArchUnitJava-TestRepo-RAG.git
cd ArchUnitJava-TestRepo-RAG
./mvnw verify
```

On Windows, use `mvnw.cmd` instead of `mvnw`.

## Inspect the policies

- [`archunitjava-healthy.properties`](archunitjava-healthy.properties) contains rules that must pass.
- [`archunitjava-violations.properties`](archunitjava-violations.properties) contains rules that
  must detect the two intentional violations.
- [`ArchitectureTest`](src/test/java/dev/archunitjava/example/rag/ArchitectureTest.java) verifies
  stable exit codes, structured evidence, graph generation, and configuration validation.

ArchUnitJava is a test-scoped Maven dependency. Production application code has no dependency on
the architecture-testing library.

## Why a separate repository?

An in-repository example can accidentally rely on reactor state, implementation classes, or local
paths. This repository exercises the same boundary a real adopter sees: a normal Maven artifact,
compiled application bytecode, public APIs, and independently versioned configuration.

## Security

The fixture is compiled normally by Maven. ArchUnitJava then reads the resulting class files as
data; it does not load or initialize the target application classes during architecture analysis.

Licensed under the [Apache License 2.0](LICENSE).

# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Dropwizard 5.0.1 REST API example written in Kotlin 2.3.0, targeting Java 21. Built with Maven (wrapper included). This is also a GitHub template repository with automated initialization via `.github/workflows/template.yml`.

## Build & Run Commands

```bash
# Build (creates target/dropwizard-kotlin-example.jar)
./mvnw clean package

# Build with Makefile (includes build-info generation)
make dist

# Run tests only
./mvnw test

# Run locally (must build first)
java -jar target/dropwizard-kotlin-example.jar server src/main/resources/config.yaml

# Docker build and run
make image run

# Build + Docker image
make all

# Test endpoints
curl http://localhost:8080/          # App info (RootResource)
curl http://localhost:8080/build-info # Build metadata (BuildInfoResource)
curl http://localhost:8081/healthcheck # Health status
```

## Architecture

Standard Dropwizard application under `src/main/kotlin/dropwizard/kotlin/example/`:

- **App.kt**: Entry point extending `Application<AppConfig>`, registers all resources, filters, and health checks. Loads `build-info.json` from resources at startup.
- **AppConfig.kt**: Configuration class with a single `appName` property (validated `@NotEmpty`).
- **resource/RootResource.kt**: `GET /` returns app name and Java runtime info as JSON.
- **resource/BuildInfoResource.kt**: `GET /build-info` returns git branch, commit SHA, and build timestamp from generated `build-info.json`. Uses Gson for parsing, sets no-cache headers.
- **filter/DiagnosticContextFilter.kt**: JAX-RS request/response filter that adds a UUID request ID to SLF4J MDC for log correlation.
- **healthcheck/DefaultHealthCheck.kt**: Always-healthy check registered with Dropwizard.

Server ports: 8080 (application), 8081 (admin/metrics), 8443 (reserved for HTTPS in Dockerfile).

## Testing

Tests use JUnit 5 with `DropwizardAppExtension` (full integration tests that boot the app with random ports). Located in `src/test/kotlin/dropwizard/kotlin/example/`. Tests create Jersey clients with custom timeouts to hit the running app's endpoints.

No linting or static analysis tools are configured.

## Build Details

- **Maven shade plugin** creates a fat JAR with main class `dropwizard.kotlin.example.App`
- **Resource filtering** is enabled for YAML, XML, and JSON files (Maven property substitution)
- **`generate-build-info.sh`** creates `src/main/resources/build-info.json` with git branch, commit SHA, and timestamp — must run before build for BuildInfoResource to work (CI and `make dist` handle this automatically)

## Deployment

- **Dockerfile**: Ubuntu 24.04 + OpenJDK 21 JRE, runs as non-root `app` user, entrypoint via `docker-entrypoint.sh`
- **Helm chart** in `deployment/k8s/helm/chart/`: includes Deployment, Service (ClusterIP 80→8080), Ingress, optional HPA. Helm operations via `deployment/k8s/helm/Makefile` (`make install`, `make upgrade`, `make uninstall`)
- **CI**: GitHub Actions (`.github/workflows/build.yml`) builds on push/PR to main, pushes Docker image to Docker Hub on main branch only

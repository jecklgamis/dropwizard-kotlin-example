# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Dropwizard 5.0.1 REST API example written in Kotlin, targeting Java 21.

## Build & Run Commands

```bash
# Build (creates target/dropwizard-kotlin-example.jar)
./mvnw clean package

# Build with Makefile (includes build-info generation)
make dist

# Run locally
java -jar target/dropwizard-kotlin-example.jar server src/main/resources/config.yaml

# Docker build and run
make image run

# Test endpoints
curl http://localhost:8080/          # App info
curl http://localhost:8080/build-info # Build metadata
curl http://localhost:8081/healthcheck # Health status
```

## Architecture

The application follows standard Dropwizard patterns:

- **App.kt**: Main entry point extending `Application<AppConfig>`, registers resources, filters, and health checks
- **AppConfig.kt**: Configuration class extending Dropwizard's `Configuration`
- **resource/**: JAX-RS REST endpoints (RootResource, BuildInfoResource)
- **healthcheck/**: Dropwizard health check implementations
- **filter/**: Request filters (DiagnosticContextFilter adds request IDs to MDC for logging)

Server runs on port 8080 (main) and 8081 (admin/metrics).

## Key Files

- `src/main/resources/config.yaml`: Dropwizard server and logging configuration
- `pom.xml`: Maven build with dropwizard-bom, kotlin-stdlib, shade plugin for fat JAR
- `Dockerfile`: Ubuntu 24.04 + OpenJDK 21 JRE container
- `deployment/k8s/helm/`: Helm chart for Kubernetes deployment

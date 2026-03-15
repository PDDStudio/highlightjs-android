# highlightjs-android Migration Report

**Migration period:** March 15-16, 2026
**Status:** Complete

## What Changed and Why

The highlightjs-android library was fully modernized from a legacy Java/View-based Android library to a modern Kotlin/Jetpack Compose library, while retaining backward compatibility for View-based projects.

### Summary of Changes

| Area | Before | After |
|------|--------|-------|
| Language | Java/Kotlin mix | 100% Kotlin |
| Build system | Gradle 6.1.1, AGP 4.0.1, Groovy DSL | Gradle 8.12.1, AGP 8.8.1, Kotlin DSL (.kts) |
| Kotlin | 1.3.72 | 2.1.20 |
| highlight.js | v10.1.1 | v11.11.1 |
| UI framework | XML layouts + WebView | Jetpack Compose + AndroidView(WebView) |
| API style | Java Builder pattern | Kotlin DSL with sealed interfaces |
| Repository | jcenter() (defunct) | mavenCentral() |
| Dependency management | Hardcoded versions | Version Catalog (libs.versions.toml) |
| Publishing | maven-push.gradle | maven-publish plugin |
| CI/CD | None | GitHub Actions (4 workflows) |
| Tests | 0 tests, 0% coverage | 94 tests, 77.8% line coverage |
| compileSdk | 30 | 35 |
| targetSdk | 30 | 35 |
| minSdk | 14/16 | 21 |

### Files Changed

182 files changed: 7,148 insertions, 6,764 deletions across source, build configuration, themes, and test files.

---

## Migration Phases

### Phase 1.0 — Build System Modernization
- Migrated all build files from Groovy to Kotlin DSL (`.gradle` → `.gradle.kts`)
- Upgraded Gradle wrapper 6.1.1 → 8.12.1
- Upgraded Android Gradle Plugin 4.0.1 → 8.8.1
- Upgraded Kotlin 1.3.72 → 2.1.20
- Removed `kotlin-android-extensions` plugin (deprecated)
- Replaced `jcenter()` with `mavenCentral()`
- Created `gradle/libs.versions.toml` version catalog
- Replaced `maven-push.gradle` with `maven-publish` plugin in `library/build.gradle.kts`
- Updated SDK versions: compileSdk=35, targetSdk=35, minSdk=21

### Phase 1.1 — Test Infrastructure
- Added JUnit 5 (Jupiter 5.11.4) + MockK 1.13.14
- Added kotlinx-coroutines-test 1.9.0
- Configured Jacoco for code coverage reporting
- Created baseline tests for all utility classes

### Phase 2.0 — Java to Kotlin Conversion
- Converted all Java source files to Kotlin (library and demo app)
- Key conversions: `HighlightJsView.java` → `.kt`, `Language.java` → `.kt`, `Theme.java` → `.kt`, `SourceUtils.java` → `.kt`
- Removed all XML layout files from demo app (replaced by Compose)

### Phase 2.1 — highlight.js v11.11.1 Upgrade
- Replaced `highlight.pack.js` with v11.11.1
- Updated all CSS theme files (added 30+ new themes, removed deprecated ones)
- Updated Language enum to include new v11 languages (190+ languages total)
- Updated Theme enum with new v11 themes (80+ themes total)

### Phase 3.0 — Kotlin DSL API + Jetpack Compose
- Replaced Java enums with Kotlin sealed interfaces (`Language`, `Theme`)
- Each language/theme is a `data object` implementing the sealed interface
- Added `Custom(className)` / `Custom(themeName)` for extensibility
- Added `@JvmField` companion aliases for Java interop
- Created `HighlightState` — Compose-aware state holder with `mutableStateOf`
- Created `HighlightJsView` composable — wraps WebView in `AndroidView`
- Created `rememberHighlightState()` for Compose usage
- Created `HighlightJsComposeView` — `AbstractComposeView` subclass for XML layout integration
- Added `highlightJs { }` DSL builder function
- Created `PlatformHighlightRenderer` interface for future Compose Multiplatform migration
- Created `AndroidHighlightRenderer` implementation

### Phase 3.1 — Demo App Compose Rewrite
- Rewrote demo app entirely in Jetpack Compose with Material 3
- Created `SyntaxScreen`, `FilesScreen` composables
- Added `DemoViewModel` with StateFlow-based state management
- Added `AppTheme` with Material 3 dynamic theming
- Removed all XML layouts, adapters, and fragments

### Phase 4.0 — CI/CD Pipeline
Created 4 GitHub Actions workflows:
- `pr-verify.yml` — Runs tests, lint, and build on every PR
- `copilot-review.yml` — Copilot-assisted code review on PRs
- `release.yml` — Automated release publishing via GitHub Packages
- `instrumented-tests.yml` — Android instrumented tests on emulator

### Phase 5.0 — Final Verification
- QA ran full test suite: 94/94 tests passing
- Build verification: assembleDebug + assembleRelease both succeed
- Lint: no critical issues
- Coverage: 77.8% line, 96.1% class coverage
- Dependency audit: all dependencies current, no vulnerabilities

---

## Problems Encountered and Solutions

| Problem | Solution |
|---------|----------|
| `jcenter()` repository is defunct | Migrated to `mavenCentral()` |
| `kotlin-android-extensions` removed in Kotlin 2.x | Removed plugin, replaced synthetic view access with Compose |
| highlight.js v11 renamed/removed CSS theme files | Mapped old theme names to new filenames, removed deprecated themes |
| highlight.js v11 changed `initHighlightingOnLoad()` API | Updated to `highlightAll()` and new v11 API |
| Sealed interfaces break Java enum `values()` | Added `values()` companion function returning all entries |
| Sealed interface `Custom` class caused circular init | Moved `Custom` outside companion, used lazy initialization |
| App module missing JUnit 5 dependencies | Added `junit-jupiter-api` and `useJUnitPlatform()` to app build |
| Jacoco `executionData` scanning too broadly | Scoped to specific `build/jacoco` and `build/outputs` subdirectories |

---

## Test Results

### Before Migration
- **Tests:** 0
- **Coverage:** 0%
- **Test framework:** None

### After Migration
- **Tests:** 94 total, 0 failures
- **Framework:** JUnit 5 (Jupiter 5.11.4) + MockK 1.13.14

| Suite | Tests | Status |
|-------|-------|--------|
| ExampleUnitTest (app) | 1 | Pass |
| HighlightJsDslTest | 8 | Pass |
| HighlightStateTest | 12 | Pass |
| LanguageTest | 9 | Pass |
| LanguageSealedTest | 7 | Pass |
| ThemeTest | 6 | Pass |
| ExtensionUtilTest | 34 | Pass |
| SourceUtilsTest | 11 | Pass |
| FileUtilsTest | 6 | Pass |

### Coverage (Jacoco)

| Metric | Covered | Total | % |
|--------|---------|-------|---|
| Line | 874 | 1,123 | 77.8% |
| Instruction | 4,960 | 7,671 | 64.7% |
| Branch | 15 | 104 | 14.4% |
| Class | 273 | 284 | 96.1% |

Note: Branch coverage is low due to the WebView bridge layer (`highlightjs` package) which cannot be unit-tested on JVM. The `utils` package (pure logic) achieves 93.1% line coverage and 68.2% branch coverage.

---

## Build Verification

| Check | Status |
|-------|--------|
| `./gradlew test` | Pass (94/94) |
| `./gradlew assembleDebug` | Pass |
| `./gradlew assembleRelease` | Pass |
| `./gradlew lint` | Pass (no critical issues) |
| `./gradlew jacocoTestReport` | Pass |

---

## Manual Testing Guide

### Library Integration (Compose)

```kotlin
// 1. Add dependency
implementation("com.pddstudio:highlightjs-android:2.0.0")

// 2. Use the composable
@Composable
fun CodeViewer() {
    val state = rememberHighlightState {
        code = "fun main() = println(\"Hello\")"
        language = Language.Kotlin
        theme = Theme.AtomOneDark
        showLineNumbers = true
    }
    HighlightJsView(state = state)
}
```

### Library Integration (View/XML backward compat)

```xml
<com.pddstudio.highlightjs.HighlightJsComposeView
    android:id="@+id/highlight_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

```kotlin
val composeView = findViewById<HighlightJsComposeView>(R.id.highlight_view)
composeView.setCode("System.out.println(\"Hello\");")
composeView.setLanguage(Language.Java)
composeView.setTheme(Theme.GithubDark)
```

### Verification Steps

1. Build and run the demo app on an emulator or device (API 21+)
2. Verify repository file list loads on the main screen
3. Tap a file to view syntax-highlighted content
4. Verify theme switching works (menu in SyntaxScreen)
5. Verify zoom gestures work on the highlighted content
6. Verify line numbers display correctly
7. Test with multiple languages (Kotlin, Java, Python, JavaScript)

---

## Dependency Changelog

| Dependency | Old Version | New Version |
|------------|-------------|-------------|
| Gradle | 6.1.1 | 8.12.1 |
| Android Gradle Plugin | 4.0.1 | 8.8.1 |
| Kotlin | 1.3.72 | 2.1.20 |
| highlight.js | 10.1.1 | 11.11.1 |
| compileSdk | 30 | 35 |
| targetSdk | 30 | 35 |
| minSdk | 14/16 | 21 |
| AppCompat | 1.1.0 | 1.7.0 |
| RecyclerView | 1.1.0 | 1.3.2 |
| Material | 1.1.0 | 1.12.0 |
| Coroutines | 1.3.5 | 1.9.0 |
| **New dependencies** | | |
| Compose BOM | — | 2024.12.01 |
| Activity Compose | — | 1.9.3 |
| Navigation Compose | — | 2.8.5 |
| Lifecycle ViewModel Compose | — | 2.8.7 |
| Lifecycle Runtime Compose | — | 2.8.7 |
| JUnit Jupiter | — | 5.11.4 |
| MockK | — | 1.13.14 |
| Coroutines Test | — | 1.9.0 |
| **Removed dependencies** | | |
| kotlin-android-extensions | 1.3.72 | Removed |
| maven-push.gradle | custom | Removed (replaced by maven-publish) |
| jcenter() | — | Removed (replaced by mavenCentral()) |

---

## Breaking Changes for Library Consumers

### API Changes

1. **Language and Theme are now sealed interfaces, not Java enums.**
   - `Language.JAVA` → `Language.Java` (data object)
   - `Theme.ANDROID_STUDIO` → `Theme.AndroidStudio` (data object)
   - Java interop preserved via `@JvmField` companion aliases
   - `values()` still works but returns `List<Language>` / `List<Theme>` instead of `Array`

2. **`HighlightJsView` (legacy WebView) API changes:**
   - Constructor now takes `Context` and optional `AttributeSet` (Kotlin defaults)
   - `setSource(String)` / `setSource(File)` / `setSource(URL)` still work
   - `setHighlightLanguage()` now accepts `Language` sealed type
   - `setTheme()` now accepts `Theme` sealed type

3. **New Compose API (recommended):**
   - `HighlightJsView(state)` composable for Compose projects
   - `rememberHighlightState { }` DSL for state creation
   - `HighlightJsComposeView` for embedding Compose in View hierarchies

4. **Minimum SDK raised from 14/16 to 21.**
   - Affects ~<3% of active Android devices

5. **Package name unchanged:** `com.pddstudio.highlightjs`

6. **Removed themes** (deprecated in highlight.js v11):
   - All `atelier-*` themes (18 themes)
   - `darcula`, `dracula`, `github-gist`, `gruvbox-dark`, `gruvbox-light`
   - `hopscotch`, `ocean`, `railscasts`, `solarized-dark`, `solarized-light`
   - `tomorrow`, `tomorrow-night`, `tomorrow-night-eighties`, `zenburn`

7. **Renamed themes** (highlight.js v11 file renames):
   - `kimbie.dark` → `kimbie-dark`
   - `kimbie.light` → `kimbie-light`
   - `qtcreator_dark` → `qtcreator-dark`
   - `qtcreator_light` → `qtcreator-light`

---

## Rollback Instructions

The migration was performed as sequential git commits. To rollback:

```bash
# View migration commits
git log --oneline

# Rollback to pre-migration state
git revert --no-commit HEAD~6..HEAD
git commit -m "Revert: rollback migration to pre-Phase 1.0 state"

# Or hard reset (destructive)
git reset --hard 9f70603  # "Removed idea folder" — last pre-migration commit
```

Individual phases can be reverted in reverse order:
1. `9cb5cc3` — Phase 5.0: Fix test infrastructure
2. `376bea5` — Phase 3.1: Demo App Compose Rewrite
3. `92dfcce` — Phase 3.0 fixups
4. `f4a04bf` — Phase 3.0: Kotlin DSL API + Compose
5. `1d6b502` — Phase 2.1: highlight.js v11.11.1 Upgrade
6. `0192a20` — Phase 1.1: Test Infrastructure
7. `b2b429d` — Phase 2.0: Java to Kotlin Conversion
8. `63c9599` — Phase 4.0: CI/CD Pipeline
9. `4903210` — Phase 1.0: Build System Modernization

---

## Team Contributions

| Agent | Role | Phases |
|-------|------|--------|
| Migration CEO | Project lead, planning, coordination, reporting | All phases (oversight), Phase 5.0 (report) |
| DevOps Agent | Build & CI/CD engineer | Phase 1.0, Phase 4.0 |
| QA Lead | Quality assurance | Phase 1.1, Phase 5.0 (verification) |
| Engineer Alpha | Senior migration engineer | Phase 2.1, Phase 3.0 |
| Engineer Beta | Migration engineer | Phase 2.0, Phase 3.1 |

---

## Architecture Notes for Future Work

### Compose Multiplatform Readiness

The library is structured for a future Compose Multiplatform migration:

- `PlatformHighlightRenderer` interface in `platform/` package defines the rendering contract
- `AndroidHighlightRenderer` provides the Android-specific WebView implementation
- Future: add `DesktopHighlightRenderer`, `WasmHighlightRenderer` etc.
- `HighlightState` and `HighlightJsComposable` use pure Compose APIs — portable to `commonMain`
- `Language` and `Theme` sealed interfaces are platform-agnostic

### Module Split Path

```
highlightjs-android/
├── shared/          (commonMain: Language, Theme, HighlightState, composables)
│   ├── androidMain/ (AndroidHighlightRenderer, WebView bridge)
│   ├── desktopMain/ (future: CEF or JxBrowser renderer)
│   └── wasmMain/    (future: direct highlight.js interop)
└── demo/            (Android demo app)
```

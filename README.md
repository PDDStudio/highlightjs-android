# highlightjs-android

A syntax highlighting library for Android, powered by **[highlight.js](https://highlightjs.org/)** v11.11.1.

Supports **190+ languages** and **80+ themes** with a modern Kotlin DSL API and first-class Jetpack Compose support.

## Features

- Jetpack Compose composable with reactive state management
- Kotlin DSL builder API (`highlightJs { }`)
- Backward compatibility for View/XML-based projects via `HighlightJsComposeView`
- 190+ programming languages with auto-detection
- 80+ syntax highlighting themes
- Line numbers support
- Pinch-to-zoom support
- File, URL, and String source loading
- Prepared for Compose Multiplatform migration

## Requirements

- **minSdk:** 21 (Android 5.0 Lollipop)
- **compileSdk:** 35
- **Kotlin:** 2.1.20+
- **Jetpack Compose:** BOM 2024.12.01+

## Getting Started

### Add the dependency

```kotlin
dependencies {
    implementation("com.pddstudio:highlightjs-android:2.0.0")
}
```

### Compose Usage (Recommended)

```kotlin
@Composable
fun CodeViewer() {
    val state = rememberHighlightState {
        code = """
            fun main() {
                println("Hello, World!")
            }
        """.trimIndent()
        language = Language.Kotlin
        theme = Theme.AtomOneDark
        showLineNumbers = true
        zoomEnabled = true
    }

    HighlightJsView(state = state)
}
```

#### Updating state reactively

```kotlin
@Composable
fun DynamicCodeViewer() {
    val state = rememberHighlightState {
        code = "print('hello')"
        language = Language.Python
        theme = Theme.GithubDark
    }

    Column {
        // Theme picker
        Button(onClick = { state.theme = Theme.Monokai }) {
            Text("Switch to Monokai")
        }

        // The view automatically re-renders when state changes
        HighlightJsView(state = state)
    }
}
```

### Kotlin DSL (outside Compose)

```kotlin
val state = highlightJs {
    code = readFileContents("Main.java")
    language = Language.Java
    theme = Theme.AndroidStudio
    showLineNumbers = true
}
```

### View/XML Integration (Backward Compatibility)

For projects still using the View system, use `HighlightJsComposeView`:

```xml
<com.pddstudio.highlightjs.HighlightJsComposeView
    android:id="@+id/highlight_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

```kotlin
val highlightView = findViewById<HighlightJsComposeView>(R.id.highlight_view)
highlightView.setCode("System.out.println(\"Hello\");")
highlightView.setLanguage(Language.Java)
highlightView.setTheme(Theme.GithubDark)
highlightView.setShowLineNumbers(true)
```

### Legacy HighlightJsView (WebView)

The original `HighlightJsView` is still available for direct WebView usage:

```kotlin
val highlightJsView = findViewById<HighlightJsView>(R.id.highlight_view)
highlightJsView.setTheme(Theme.AndroidStudio)
highlightJsView.setHighlightLanguage(Language.Kotlin)
highlightJsView.setSource("fun main() = println(\"Hello\")")
```

## Languages

The library supports 190+ languages via the `Language` sealed interface. Some examples:

```kotlin
Language.Kotlin
Language.Java
Language.Python
Language.JavaScript
Language.TypeScript
Language.Rust
Language.Go
Language.Swift
Language.Cpp
Language.CSharp
Language.AutoDetect      // Let highlight.js detect the language
Language.Custom("mylang") // Custom language support
```

Full list: see [highlight.js supported languages](https://highlightjs.org/static/demo/).

## Themes

80+ themes available via the `Theme` sealed interface:

```kotlin
Theme.AtomOneDark
Theme.GithubDark
Theme.GithubDarkDimmed
Theme.Monokai
Theme.AndroidStudio
Theme.Nord
Theme.TokyoNightDark
Theme.RosePine
Theme.StackoverflowDark
Theme.Custom("my-theme") // Custom theme support
```

Browse themes: check the [assets/styles](library/src/main/assets/styles) directory.

## Migration from v1

### API Changes

| v1 (Java enum) | v2 (Sealed interface) |
|---|---|
| `Language.JAVA` | `Language.Java` |
| `Language.KOTLIN` | `Language.Kotlin` |
| `Language.AUTO_DETECT` | `Language.AutoDetect` |
| `Theme.ANDROID_STUDIO` | `Theme.AndroidStudio` |
| `Theme.ATOM_ONE_DARK` | `Theme.AtomOneDark` |
| `Theme.DEFAULT` | `Theme.Default` |

Java interop is preserved — `@JvmField` companion entries allow the old enum-style access:

```java
// Java code still works
Language lang = Language.JAVA;
Theme theme = Theme.ANDROID_STUDIO;
```

### Removed Themes (deprecated in highlight.js v11)

The following themes were removed upstream: all `atelier-*` variants, `darcula`, `dracula`, `github-gist`, `gruvbox-dark`, `gruvbox-light`, `hopscotch`, `ocean`, `railscasts`, `solarized-dark`, `solarized-light`, `tomorrow`, `tomorrow-night`, `tomorrow-night-eighties`, `zenburn`.

Replacements: `dracula` → `panda-syntax-dark`, `solarized-dark` → `rose-pine`, `github-gist` → `github`.

### minSdk Change

minSdk raised from 14/16 to **21**. This affects <3% of active Android devices.

## Demo Application

The demo app shows syntax highlighting of GitHub repository files. Build and run the `app` module:

```bash
./gradlew :app:assembleDebug
```

## Building

```bash
# Build library
./gradlew :library:assembleRelease

# Run tests
./gradlew test

# Generate coverage report
./gradlew jacocoTestReport
```

## About & Contact

- Author: Patrick J (patrick.pddstudio@googlemail.com)
- Issues: [GitHub Issues](https://github.com/PDDStudio/highlightjs-android/issues)

## License

```
    Copyright 2016 Patrick J

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

	Copyright (c) 2006, Ivan Sagalaev
	All rights reserved.
	Redistribution and use in source and binary forms, with or without
	modification, are permitted provided that the following conditions are met:

	    * Redistributions of source code must retain the above copyright
	      notice, this list of conditions and the following disclaimer.
	    * Redistributions in binary form must reproduce the above copyright
	      notice, this list of conditions and the following disclaimer in the
	      documentation and/or other materials provided with the distribution.
	    * Neither the name of highlight.js nor the names of its contributors
	      may be used to endorse or promote products derived from this software
	      without specific prior written permission.

	THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
	EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
	WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
	DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
	DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
	(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
	LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
	ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
	(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
	SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
```

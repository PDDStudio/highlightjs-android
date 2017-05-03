## HighlightJs View - Android
A syntax highlighting view, powered by **[highlight.js](https://highlightjs.org/)**, for Android.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pddstudio/highlightjs-android/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.pddstudio/highlightjs-android)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-highlightjs--android-green.svg?style=true)](https://android-arsenal.com/details/1/3875)

## Demo Application

The demo application shows a simple example usage for this library.
It lists the files of this repository's commits and allows you to browse the changed files.
Selecting a file show's up the syntax highlighted content.

### Screenshot

![](https://raw.githubusercontent.com/PDDStudio/highlightjs-android/master/gfx/demo.png)

### Download

You can download the sample apk *[in the release section](https://github.com/PDDStudio/highlightjs-android/releases)*

## Getting Started

### Add the library as dependency

Add the library as dependency to your `build.gradle` file.

```java
dependencies {
	//other dependencies...
	compile 'com.pddstudio:highlightjs-android:X.X.X'
}
```

Replace X.X.X with the current version - which can be found in the *[release section](https://github.com/PDDStudio/highlightjs-android/releases)*

### Include the View into your Layout

Add the View to your existing layout file.

```xml
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Other views here -->

    <com.pddstudio.highlightjs.HighlightJsView
	android:id="@+id/highlight_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <!-- Other views here -->

</RelativeLayout>
```

### Assign the view and set it's content

In your Activity/Fragment assign the view and set it's content.

```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syntax);
        //other stuff here ....

        //find and instantiate the view
        highlightJsView = (HighlightJsView) findViewById(R.id.highlight_view);

		//optional: register callbacks and style the view

        //register theme change listener
        highlightJsView.setOnThemeChangedListener(this);
        //change theme and set language to auto detect
        highlightJsView.setTheme(Theme.ANDROID_STUDIO);
        highlightJsView.setHighlightLanguage(Language.AUTO_DETECT);
        //load the source (can be loaded by String, File or URL)
        highlightJsView.setSource(fileObject.getUrl());
    }
```


### Customize the View

For a complete overview of all themes and languages you can check out the official highlight.js demo *[here](https://highlightjs.org/static/demo/)*

You can customize the View's **Theme** and **Language**.
By default the Theme is set to *Theme.DEFAULT* and the language is set to *Language.AUTO_DETECT*

**Change the view's theme:**

```java
//change theme
highlightJsView.setTheme(Theme.ANDROID_STUDIO);
```
The library supports all themes that are included in *highlight.js version 9.10.0*

Browse the list of themes *[here](https://github.com/PDDStudio/highlightjs-android/tree/master/library/src/main/assets/styles)*

**Change the view's language:**

```java
//change language
highlightJsView.setHighlightLanguage(Language.JAVA);
```
The library supports all languages that are included in *highlight.js version 9.10.0* (more than +130 languages)

Browse the list of languages *[here](http://highlightjs.readthedocs.io/en/latest/css-classes-reference.html#language-names-and-aliases)*

**Enable or disable support for zooming:**

```java
//enable or disable zooming support for HighlightJsView
//note: in case you change this during runtime don't forget to
//call highlightJsView.reload(); for the changes to apply!
highlightJsView.setZoomSupportEnabled(true);
```

**Enable or disable line numbers:**

```java
//enable or disable line numbers on the left side of the source
//note: in case you change this during runtime don't forget to
//call highlightJsView.reload(); for the changes to apply!
highlightJsView.setShowLineNumbers(enableLineNumbers);
```

By default Highlight.js does not support line numbers. However, thanks to [wcoder](https://github.com/wcoder/) and all the hard work that was put into [highlightjs-line-numbers.js](https://github.com/wcoder/highlightjs-line-numbers.js/). This made it easy for me to port and include this functionality into this library.

### Change the view's source

You can load the source you want to be highlighted by either a String containing the actual source code, a `File` object which represents the file containing the source code or an `URL`  object to load the source code from a remote Url.

```java
try {
	markdownUrl = new URL("https://raw.githubusercontent.com/PDDStudio/highlightjs-android/master/README.md");
} catch (Exception e) {
	e.printStackTrace();
}
//change the displayed source
highlightJsView.setSource(markdownUrl);
//make sure to call reload() when changing content during runtime
highlightJsView.reload();
```


**Notes:**

- Source Code which is loaded via URL requires internet permission (source is loaded asynchronous) `<uses-permission android:name="android.permission.INTERNET" />`
- Source Code which is loaded by a `File` object from the device's internal storage requires  storage permission (especially on +Android 6.0)

## About & Contact
- In case you've a question feel free to hit me up via E-Mail (patrick.pddstudio@googlemail.com)
- or [Google+](http://plus.google.com/+PatrickJung42) / Hangouts

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

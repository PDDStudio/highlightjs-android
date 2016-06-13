##HighlightJs View - Android
A syntax highlighting view, powered by **[highlight.js](https://highlightjs.org/)**, for Android.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pddstudio/highlightjs-android/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.pddstudio/highlightjs-android)

##Demo Application

The demo application shows a simple example usage for this library.
It lists the files of this repository's commits and allows you to browse the changed files.
Selecting a file show's up the syntax highlighted content.

###Screenshot

![](https://raw.githubusercontent.com/PDDStudio/highlightjs-android/master/gfx/demo.png) 

###Download

You can download the sample apk *here*

##Getting Started

###Add the library as dependency

Add the library as dependency to your `build.gradle` file.

```java
dependencies {
	//other dependencies...
	compile 'com.pddstudio:highlightjs-android:X.X.X'
}
```

Replace X.X.X with the current version - which can be found in the *[release section](https://github.com/PDDStudio/highlightjs-android/releases)*

###Include the View into your Layout

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

###Assign the view and set it's content

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


###Customize the View

You can customize the View's **Theme** and **Language**.
By default the Theme is set to *Theme.DEFAULT* and the language is set to *Language.AUTO_DETECT*

**Change the view's theme:**

```java
//change theme
highlightJsView.setTheme(Theme.ANDROID_STUDIO);
```
The library supports all themes that are included in *highlight.js version 9.4.0*

Browse the list of themes *[here](https://github.com/PDDStudio/highlightjs-android/tree/master/library/src/main/assets/styles)*

**Change the view's language:**

```java
//change language
highlightJsView.setHighlightLanguage(Language.JAVA);
```
The library supports all languages that are included in *highlight.js version 9.4.0* (more than +130 languages)

Browse the list of languages *[here](http://highlightjs.readthedocs.io/en/latest/css-classes-reference.html#language-names-and-aliases)*

###Change the view's source

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
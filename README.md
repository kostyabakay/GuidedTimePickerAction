# GuidedTimePickerAction

Extension for Android TV Leanback SDK similar to available [GuidedDatePickerAction](https://developer.android.com/reference/android/support/v17/leanback/widget/GuidedDatePickerAction).

![Alt text](/screenshots/timepicker-screenshot-1.jpeg?raw=true)

![Alt text](/screenshots/timepicker-screenshot-2.jpeg?raw=true)

![Alt text](/screenshots/timepicker-screenshot-3.jpeg?raw=true)

Do not use this repository as dependency in your production! Instead of this just copy code from library module. If you want to check this library quickly just add dependency to Gradle.

Add this in your root build.gradle at the end of repositories
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency
```
dependencies {
    implementation 'com.github.kostyabakay:guidedtimepickeraction:1.0'
}
```

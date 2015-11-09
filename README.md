IntegrationPreference
=====================

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-IntegrationPreference-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2741)
[![Build Status](https://travis-ci.org/tasomaniac/IntegrationPreference.png?branch=master)](https://travis-ci.org/tasomaniac/IntegrationPreference)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This library is especially for extension apps developers. Some applications like (Muzei, Series Guide)
has extension capabilities. You can use their SDK to integrate your app into theirs. Or your app may
 need another app to be installed on the device.

 Those can be rare cases but this library is just for you.

You put `IntegrationPreference` into your preference XML file and it checks the given `Intent1 inside its XML tags.
If it cannot find the application to handle that `Intent`, it opens Play Store on click to install the
required application.

![](screenshot1.png)

Usage
-----

Detailed usage can be found in the sample project and in the following applications.

- https://github.com/tasomaniac/MuzeiEarthView
- https://github.com/tasomaniac/MuzeiTVShows
- https://github.com/tasomaniac/spaceapi_dashclock
- https://github.com/tasomaniac/MuzeiComicsCovers

For the basic usage, you need to add `IntegrationPreference` into your preference xml like below.

```xml
    <com.tasomaniac.android.widget.IntegrationPreference
      android:key="pref_key"
      android:summaryOff="Description to shown when the Intent is found."
      android:summaryOn="Click here to install the required app.">

      <intent
        android:targetClass="com.google.android.apps.muzei.settings.SettingsActivity"
        android:targetPackage="net.nurik.roman.muzei"/>

    </com.tasomaniac.android.widget.IntegrationPreference>
  </PreferenceCategory>
```

And in your `PreferenceFragment` or `PreferenceActivity` you can add these to functions into you `onResume` and
`onPause` methods to enable automatic detecting of application installs.

```java

    @Override
    protected void onResume() {
        super.onResume();

        integrationPreference.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        integrationPreference.pause();
    }
```

For other configurations and manual Intent handling, please refer to the sample project.

Download
--------

Dependency for native `PreferenceActivity` and `PreferenceFragment`

```groovy
compile 'com.tasomaniac:integrationpreference:0.1'
```

Dependency for support versions.

```groovy
compile 'com.tasomaniac:integrationpreference-support:0.1'
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].

License
-------

    Copyright (C) 2015 Said Tahsin Dane

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



 [snap]: https://oss.sonatype.org/content/repositories/snapshots/

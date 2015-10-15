IntegrationPreference
=====================

<!-- [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ContentLoadingProgressDialog-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/2117) -->
[![Build Status](https://travis-ci.org/tasomaniac/IntegrationPreference.png?branch=master)](https://travis-ci.org/tasomaniac/IntegrationPreference)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This library is especially for extension apps developers. Some applications like (Muzei, Series Guide)
has extension capabilities. You can use their SDK to integrate your app into theirs. Or your app may
 need another app to be installed on the device.

You put IntegrationPreference into your preference XML file and it checks the given intent to it.
If it cannot find the application to handle that Intent, it opens Play Store on click to install the
required application.

Usage
-----

TODO

Download
--------

```groovy
compile 'com.tasomaniac:integrationpreference:0.1-SNAPSHOT'
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

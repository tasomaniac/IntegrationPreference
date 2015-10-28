/*
 * Copyright (c) 2015 Said Tahsin Dane
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tasomaniac.widget.integrationpreference.sample;


import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.StringRes;
import android.text.Html;

import com.tasomaniac.android.widget.IntegrationPreference;

public class PlatformSettingsActivity extends AppCompatPreferenceActivity {

    private IntegrationPreference muzeiPreference;
    private IntegrationPreference dashclockPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);

        fixSpansInPreference(R.string.pref_key_desc_integration_auto_intent);
        fixSpansInPreference(R.string.pref_key_desc_integration_intent_attrs);

        muzeiPreference = (IntegrationPreference) findPreference(R.string.pref_key_muzei_integration);
        dashclockPreference = (IntegrationPreference) findPreference(R.string.pref_key_dashclock_integration);
    }

    private void fixSpansInPreference(@StringRes int key) {
        Preference pref = findPreference(key);
        pref.setSummary(Html.fromHtml(pref.getSummary().toString()));
    }

    @Override
    protected void onResume() {
        super.onResume();

        muzeiPreference.resume();
        dashclockPreference.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        muzeiPreference.pause();
        dashclockPreference.pause();
    }

    @SuppressWarnings("deprecation")
    public Preference findPreference(@StringRes int key) {
        return findPreference(getString(key));
    }
}

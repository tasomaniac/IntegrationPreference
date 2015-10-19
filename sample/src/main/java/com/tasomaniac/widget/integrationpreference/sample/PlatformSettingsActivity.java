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

public class PlatformSettingsActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);

        fixSpansInPreference(R.string.pref_key_desc_integration_auto_intent);
        fixSpansInPreference(R.string.pref_key_desc_integration_intent_attrs);
    }

    private void fixSpansInPreference(@StringRes int key) {
        Preference pref = findPreference(key);
        pref.setSummary(Html.fromHtml(pref.getSummary().toString()));
    }

    public Preference findPreference(@StringRes int key) {
        return findPreference(getString(key));
    }
}

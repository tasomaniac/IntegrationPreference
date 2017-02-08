/*
 * Copyright (c) 2017 Said Tahsin Dane
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

package com.tasomaniac.android.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


final class AppInstallEnabler {

    private final Context context;
    private final IntegrationPreference pref;
    private final IntentFilter intentFilter;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Broadcast receiver is always running on the UI thread here,
            // so we don't need consider thread synchronization.
            handleStateChanged();
        }
    };

    AppInstallEnabler(Context context, IntegrationPreference pref) {
        this.context = context;
        this.pref = pref;

        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
    }

    public void resume() {

        handleStateChanged();
        context.registerReceiver(mReceiver, intentFilter);
    }

    public void pause() {
        context.unregisterReceiver(mReceiver);
    }

    private void handleStateChanged() {
        pref.checkState();
    }

}

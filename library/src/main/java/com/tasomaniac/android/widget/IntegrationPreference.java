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

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

public class IntegrationPreference extends CheckBoxPreference {

    private PackageManager packageManager;
    private AppInstallEnabler appInstallEnabler;

    private Intent originalIntent;
    private Intent integrationIntent;

    public IntegrationPreference(Context context) {
        super(context);
        initialize(context, null);
    }

    public IntegrationPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public IntegrationPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IntegrationPreference(
            Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        packageManager = context.getPackageManager();
        setDefaultValue(false);

        final TypedArray sa = context.obtainStyledAttributes(attrs,
                R.styleable.IntegrationPreference);

        extractIntegrationIntent(sa);

        sa.recycle();

        setDisableDependentsState(true);
        setPersistent(false);
        setWidgetLayoutResource(R.layout.preference_widget_error);
        setSummaryOn(getSummaryOn());

        appInstallEnabler = new AppInstallEnabler(context, this);
    }

    @Override
    public void onAttached() {
        super.onAttached();

        originalIntent = getIntent();
        if (originalIntent != null && !hasIntent(integrationIntent)) {
            integrationIntent = marketIntent(originalIntent);
        }
        checkState();
    }

    public void resume() {
        appInstallEnabler.resume();
    }

    public void pause() {
        appInstallEnabler.pause();
    }

    void checkState() {
        if (hasIntent(originalIntent)) {
            setChecked(false);
        } else {
            setChecked(true);
            if (hasIntent(integrationIntent)) {
                setIntent(integrationIntent);
            }
        }
    }

    private void extractIntegrationIntent(TypedArray sa) {
        integrationIntent = new Intent();

        integrationIntent.setAction(
                sa.getString(R.styleable.IntegrationPreference_integrationIntentAction));

        String data = sa.getString(R.styleable.IntegrationPreference_integrationIntentData);
        String mimeType = sa.getString(R.styleable.IntegrationPreference_integrationIntentMimeType);
        if (data != null) {
            integrationIntent.setData(Uri.parse(data));
        }
        if (mimeType != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                integrationIntent.setTypeAndNormalize(mimeType);
            } else {
                integrationIntent.setType(mimeType);
            }
        }

        String packageName =
                sa.getString(R.styleable.IntegrationPreference_integrationIntentTargetPackage);
        String className =
                sa.getString(R.styleable.IntegrationPreference_integrationIntentTargetClass);
        if (packageName != null && className != null) {
            integrationIntent.setComponent(new ComponentName(packageName, className));
        }
    }

    private Intent marketIntent(Intent originalIntent) {
        ComponentName component = originalIntent.getComponent();
        if (component != null) {
            final String packageName = component.getPackageName();
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + packageName));
            if (!hasIntent(marketIntent)) {
                marketIntent.setData(Uri.parse(
                        "https://play.google.com/store/apps/details?id=" + packageName));
            }
            if (hasIntent(marketIntent)) {
                return marketIntent;
            }
        }
        return null;
    }

    @Override
    public void setSummaryOn(CharSequence summary) {
        if (summary != null) {
            SpannableString summarySpan = getErrorString(summary);
            super.setSummaryOn(summarySpan);
        } else {
            super.setSummaryOn(null);
        }
    }

    private boolean hasIntent(Intent intent) {
        return intent != null
                && packageManager.resolveActivity(intent, 0) != null;
    }

    @Override
    protected void onClick() {
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);

        if (!checked) {
            setTitle(getTitle().toString());
        } else {
            SpannableString titleSpan = getErrorString(getTitle());
            setTitle(titleSpan);
        }
    }

    private SpannableString getErrorString(CharSequence originalString) {
        SpannableString errorSpan = new SpannableString(originalString);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getColor(R.color.error_color));
        errorSpan.setSpan(colorSpan, 0, originalString.length(), 0);
        return errorSpan;
    }

    @SuppressWarnings("deprecation")
    private int getColor(int id) {
        return getContext().getResources().getColor(id);
    }
}

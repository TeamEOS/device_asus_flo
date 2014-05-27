/*
 * Copyright (C) 2011 The CyanogenMod Project
 * Copyright (C) 2014 Team Eos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.eos.settings.device;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.view.MenuItem;

import org.eos.settings.device.R;

public class DevicePreferenceActivity extends PreferenceFragment {

    public static final String KEY_S2W = "s2w";
    public static final String KEY_DT2W = "dt2w";
    
    private Context context;
    private CheckBoxPreference mS2w;
    private CheckBoxPreference mDt2w;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		ActionBar actionBar = getActivity().getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
					  | actionBar.DISPLAY_SHOW_TITLE);
		actionBar.setDisplayHomeAsUpEnabled(true);

        addPreferencesFromResource(R.xml.preferences);
        context = getActivity().getApplication();

        mS2w = (CheckBoxPreference) findPreference(KEY_S2W);
        //mS2w.setChecked(S2w.isEnabled());
        mS2w.setEnabled(S2w.isSupported());
        
        mDt2w = (CheckBoxPreference) findPreference(KEY_DT2W);
        //mDt2w.setChecked(Dt2w.isEnabled());
        mDt2w.setEnabled(Dt2w.isSupported());

    }

    @Override
    public void onStart() {
    	super.onStart();
        mS2w.setChecked(S2w.isEnabled());
        mDt2w.setChecked(Dt2w.isEnabled());
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			getActivity().finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
    if (preference == mS2w) {
        if (mS2w.isChecked())
            S2w.enable(context);
        else
            S2w.disable(context);
        return true;
    }

    if (preference == mDt2w) {
        if (mDt2w.isChecked())
            Dt2w.enable(context);
        else
            Dt2w.disable(context);
        return true;
    }

    return super.onPreferenceTreeClick(preferenceScreen, preference);
}
}
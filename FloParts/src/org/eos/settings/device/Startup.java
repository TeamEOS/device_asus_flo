/*
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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Startup extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, final Intent bootintent) {
		String action = bootintent.getAction();
		if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
			S2w.restore(context);
			Dt2w.restore(context);
		} else if ("org.eos.settings.device.FloParts.feature_changed"
				.equals(action)) {
			if (bootintent.getStringExtra("feature_s2w") != null) {
				SharedPreferences sharedPrefs = PreferenceManager
						.getDefaultSharedPreferences(context);
				SharedPreferences.Editor editor = sharedPrefs.edit();
				editor.putBoolean(DevicePreferenceActivity.KEY_S2W,
						S2w.isEnabled());
				editor.commit();
			}
			if (bootintent.getStringExtra("feature_dt2w") != null) {
				SharedPreferences sharedPrefs = PreferenceManager
						.getDefaultSharedPreferences(context);
				SharedPreferences.Editor editor = sharedPrefs.edit();
				editor.putBoolean(DevicePreferenceActivity.KEY_DT2W,
						Dt2w.isEnabled());
				editor.commit();
			}
		}
	}
}

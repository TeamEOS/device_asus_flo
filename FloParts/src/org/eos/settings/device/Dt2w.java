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

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import java.lang.String;

public class Dt2w {

    private static final String DT2W = "/sys/android_touch/doubletap2wake";

    public static boolean isSupported() {
        return Utils.fileExists(DT2W);
    }

    public static boolean isEnabled() {
        if (!isSupported())
            return false;
        return Utils.readOneLine(DT2W).equals("1");
    }

    public static void restore(Context context) {
        if (!isSupported())
            return;
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (sharedPrefs.getBoolean(DevicePreferenceActivity.KEY_DT2W, false))
            //Utils.writeValue(DT2W, "1");
	    RunRootCommand.cmd("echo 1 > /sys/android_touch/doubletap2wake");
        else
            //Utils.writeValue(DT2W, "0");
	    RunRootCommand.cmd("echo 0 > /sys/android_touch/doubletap2wake");
    }

    public static void enable(Context context) {
        if (!isSupported())
            return;
        //Utils.writeValue(DT2W, "1");
	RunRootCommand.cmd("echo 1 > /sys/android_touch/doubletap2wake");
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(DevicePreferenceActivity.KEY_DT2W, true);
        editor.commit();
    }

    public static void disable(Context context) {
        if (!isSupported())
            return;
        //Utils.writeValue(DT2W, "0");
	RunRootCommand.cmd("echo 0 > /sys/android_touch/doubletap2wake");
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(DevicePreferenceActivity.KEY_DT2W, false);
        editor.commit();
    }
}
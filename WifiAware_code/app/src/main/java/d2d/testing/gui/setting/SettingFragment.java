package d2d.testing.gui.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import d2d.testing.R;
import d2d.testing.gui.main.MainFragment;

public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String TAG = "SettingFragment";

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Unregister the OnSharedPreferenceChangeListener
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals("sharedSecretInput")) {
            // Restart the app
            Intent i = getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        else if(key.equals("sharedSecretSwitch")){
            MainFragment.ssMode = sharedPreferences.getBoolean("sharedSecretSwitch", false);
        }

    }
}

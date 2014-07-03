package adapter;

import com.example.iet.R;
import com.example.iet.R.layout;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Settings  extends PreferenceFragment implements OnSharedPreferenceChangeListener  {
	public static boolean tour=true;
	

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        // Load the preferences from an XML resource
	        addPreferencesFromResource(R.layout.settings);
	        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
	            initSummary(getPreferenceScreen().getPreference(i));
	          }
	        Preference fooBarPref = (Preference) findPreference("screen_preference");
	        fooBarPref.setOnPreferenceClickListener (new OnPreferenceClickListener(){

				@Override
				public boolean onPreferenceClick(Preference arg0) {
					Log.d("yesssssss", "yesssssssss");
					Intent myIntent=new Intent("HOWTOUSEIET");
					tour=false;
			         startActivity(myIntent);
					return false;
				}
	        	
	        });
	    }
		private void initSummary(Preference p) {
			 if (p instanceof PreferenceCategory) {
			        PreferenceCategory cat = (PreferenceCategory) p;
			        for (int i = 0; i < cat.getPreferenceCount(); i++) {
			          initSummary(cat.getPreference(i));
			        }
			      } else {
			        updatePreferences(p);
			      }
			
		}

		private void updatePreferences(Preference p) {
			if (p instanceof EditTextPreference) {
		        EditTextPreference editTextPref = (EditTextPreference) p;
		        p.setSummary(editTextPref.getText());
		      }
			
		}
		@Override
		public void onResume() {
	      super.onResume();
	      getPreferenceScreen().getSharedPreferences()
	          .registerOnSharedPreferenceChangeListener(this);
	    }

	    @Override
		public void onPause() {
	      super.onPause();
	      getPreferenceScreen().getSharedPreferences()
	          .unregisterOnSharedPreferenceChangeListener(this);
	    }

	    @Override
	    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,String key) {
	      updatePreferences(findPreference(key));
	    }
//	@Override
//	public void onPrepareOptionsMenu(Menu menu) {
//		
//		MenuItem item=menu.findItem(R.id.action_settings);
//		item.setEnabled(false).setVisible(false);
//	}
//	

}

package com.example.iet;

import service.IETService;
import service.NewsService;
import service.PlacementNews;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class Splash extends Activity {
	final String prefs="Myprefs";
	private OnSharedPreferenceChangeListener listener;
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);	
		
		SharedPreferences settings=getSharedPreferences(prefs,0);
		if(settings.getBoolean("my_first_time", true)){
			Log.d("first", "time");
			 Intent myIntent=new Intent("HOWTOUSEIET");
				
	         startActivity(myIntent);
	        services();
			settings.edit().putBoolean("my_first_time",false).commit();   //change
		}
		else{
			
			Intent myIntent=new Intent("IetHome");
	        
  			startActivity(myIntent);
  			services();
		}
   			    finish();
      			}


	 public void services() {
		 final Intent service1=new Intent(this,IETService.class);
		 final Intent service3=new Intent(this,NewsService.class);
		final Intent service2=new Intent(this,PlacementNews.class);
		try{
			 SharedPreferences sharedPrefs = PreferenceManager
		                .getDefaultSharedPreferences(this);
			 String url = sharedPrefs.getString("prefHostname","0"); 
			 String port = sharedPrefs.getString("prefPort","0"); 
			 String proxy_username = sharedPrefs.getString("prefUsername","0"); 
			 String proxy_passowrd = sharedPrefs.getString("prefPassword","0"); 
			 Log.d("",url);
			 boolean pref0=sharedPrefs.getBoolean("prefSendReport", false);
			 boolean pref1=sharedPrefs.getBoolean("nsettings1", false);
			// boolean pref2=sharedPrefs.getBoolean("nsettings2", false);
			 boolean pref3=sharedPrefs.getBoolean("nsettings3", false);
			 //Log.d("", "+pref"+pref0+pref1+pref2+pref3);
			 if(pref1==true){ Log.d("", "+pref"+pref1);
  		    startService(service1);
			 }
			 //if(pref2==true){ Log.d("", "+pref"+pref2);
  			//startService(service2);
			 //}
				 if(pref3==true){ Log.d("", "+pref"+pref3);
 			startService(service3);
				 }
		}catch(Exception e)
		{
		e.printStackTrace();
		}
	}
	 
      			
}

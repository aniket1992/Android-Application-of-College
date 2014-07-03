package service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class NetworkReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(final Context context, Intent intent) {
		final Intent service1=new Intent(context,IETService.class);
		final Intent service2=new Intent(context,PlacementNews.class);
		final Intent service3=new Intent(context,NewsService.class);
		try{
        ConnectivityManager connectivityManager = (ConnectivityManager) 
                                     context.getSystemService(Context.CONNECTIVITY_SERVICE );
        NetworkInfo activeNetInfo1 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo activeNetInfo2 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isConnected1 = activeNetInfo1 != null && activeNetInfo1.isConnectedOrConnecting();  
        boolean isConnected2 = activeNetInfo2 != null && activeNetInfo2.isConnectedOrConnecting(); 
       
        if (isConnected1 ||isConnected2 )   
         {   
             SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
			 boolean pref0=sharedPrefs.getBoolean("prefSendReport", false);
			 boolean pref1=sharedPrefs.getBoolean("nsettings1", false);
			// boolean pref2=sharedPrefs.getBoolean("nsettings2", false);
			 boolean pref3=sharedPrefs.getBoolean("nsettings3", false);
			// Log.d("", "+pref"+pref0+pref1+pref2+pref3);
			 if(pref1==true)
		    context.startService(service1);
			 //if(pref2==true)
			 //context.startService(service2);
		    if(pref3==true)
			context.startService(service3);
        	         
        	
        }  
        else {
        	
        		context.stopService(service1);
        		context.stopService(service2);
        		context.stopService(service3);
        		
        	
        	
        }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}

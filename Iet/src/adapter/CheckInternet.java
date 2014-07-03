package adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

public class CheckInternet {
	static boolean flag=false;
	public static boolean dialog(final Context context) {

		AlertDialog.Builder alert=new AlertDialog.Builder(context);
		alert.setTitle("No Internet");
		alert.setMessage("No Internet Connection Available.Do you want to try again?");
		alert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(isNetworkAvaialable(context)){
					flag=true;
				}
				else{
					flag=false;
					dialog(context);
				}
			
				
				
			}
		});
		alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
				flag=true;
			}
		});
		alert.show();
		if(flag==true)
			return true;
		else return false;
		
	}
	public static boolean  isNetworkAvaialable(Context context) {
		
		ConnectivityManager cm=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
		try{if(cm.getActiveNetworkInfo().isConnected()){
			
		
		return (true);
		}
		
		else
			return (false);
		}
         catch(Exception ex){
        	 return false;
		}
		
	}
	

}

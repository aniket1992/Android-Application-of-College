package com.example.iet;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.placement.Events;
import com.example.placement.News;
import com.example.placement.PlacementRegistrationModule;
import com.scringo.Scringo;

@SuppressLint("NewApi")
public class PlacementCell extends Fragment implements OnClickListener{
	
	private ImageButton placement;
	private ImageButton registration;
	private ImageButton newsevents;
	private ImageButton news;
	private ImageButton events;
	private ImageButton registration_login;
	private Scringo scringo;

	public PlacementCell() {}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.placement_pages, container, false);
        placement =(ImageButton) rootView.findViewById(R.id.placement);
        registration =(ImageButton) rootView.findViewById(R.id.registration);
     placement.setOnClickListener(this);
     registration.setOnClickListener(this);
        return rootView;
    }
	
	
	
	
	
	



	@Override
	public void onStop() {
		super.onStop();
		
		
		System.gc();
	}

	@Override
	public void onClick(View v) {
		Intent intent1 = new Intent("placement");
	   
	    
		
		String link;
		boolean check;
	
		switch(v.getId()){
		case R.id.placement:
			link="http://www.ietlucknow.edu/online/Views/User/Login.aspx";
			intent1.putExtra("url",link);
			break;
		case R.id.registration:
		link="http://www.ietlucknow.edu/online/Views/User/Registration.aspx";
		intent1.putExtra("url",link);
			break;
		}
		 check=isNetworkAvaialable();
		 if(check){
			 startActivity(intent1);
				}
				else{
					dialog(intent1);
					
				}
		
		
	}

	private boolean isNetworkAvaialable() {
		
		ConnectivityManager cm=(ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
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
	public void dialog(final Intent intent1) {

									AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
									alert.setTitle("No Internet");
									alert.setMessage("No Internet Connection Available.Do you want to try again?");
									alert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											if(isNetworkAvaialable()){
												 startActivity(intent1);
											}
											else{
												dialog(intent1);
											}
										
											
											
										}
									});
									alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											dialog.cancel();
											
										}
									});
									alert.show();
									
								}
}
package com.example.Institute;

import com.example.iet.R;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

public class Faculty extends Fragment{
	private ImageView load;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.faculty, container, false);
        load=(ImageView)rootView.findViewById(R.id.imageView1111);
        final String link="http://www.ietlucknow.edu/facultyapp/frmFacultySearch.aspx";
       load.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			
			pp(link);
			
		}
    	   
       })  ;
      pp(link);
        
	return rootView;
	}
	private void pp(String link) {
		 boolean check=isNetworkAvailable();
	        if(check){
	        	open(link);
		    }
		   else{
			  dialog(link);
			
		  }
		
	}
	private void open(String link) {
		
		Intent intent1 = new Intent("placement");
		intent1.putExtra("url",link);
		 startActivity(intent1);
		}
		
		


	private boolean isNetworkAvailable() {
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

	private void dialog(final String link2) {
		AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
		alert.setTitle("No Internet");
		alert.setMessage("No Internet Connection Available.Do you want to try again?");
	
		alert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(isNetworkAvailable()){
					open(link2);
				}
				else{
					dialog(link2);
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

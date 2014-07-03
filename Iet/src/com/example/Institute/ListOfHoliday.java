package com.example.Institute;

import java.io.File;
import java.io.IOException;

import adapter.DownloadPdfFile;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.iet.R;

public class ListOfHoliday extends Fragment{
	private SQLiteDatabase helper;

    public Cursor cursor;
	private ImageView load;
	
	public ListOfHoliday (){
		
	}
	
	public ListOfHoliday (SQLiteDatabase helper) {
		this.helper=helper;
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		final String link="http://www.ietlucknow.edu/holiday.pdf";
        View rootView = inflater.inflate(R.layout.listofholidays, container, false);
        load=(ImageView)rootView.findViewById(R.id.imageView1111);
        load.setOnClickListener(new OnClickListener(){

    		@Override
    		public void onClick(View v) {
    			
    			pp(link);
    			
    		}
        	   
           })  ;
          pp(link);
		
      
       
	 return rootView;
}


	private void pp(final String link) {
		 boolean check=isNetworkAvailable();
		 if(check){
	        	open(link);
		    }
		   else{
			  dialog(link);
			
		  }
		
	}

	private void open(String link) {
		final String linknew=link;
		 final String googleDocs="http://docs.google.com/viewer?url=";
	    AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
		
		alert.setTitle("PDF FILE:");
		alert.setPositiveButton("Download Pdf",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			 Intent intent=new Intent("download_pdf");
			intent.putExtra("url", linknew);
			 startActivity(intent);
				
			}
		});
		alert.setNegativeButton("View Pdf",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Intent intent1 = new Intent("placement");
			    intent1.putExtra("url",Uri.parse(googleDocs+linknew).toString());
			    
				 startActivity(intent1);
				
			}
		});
		alert.show();
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
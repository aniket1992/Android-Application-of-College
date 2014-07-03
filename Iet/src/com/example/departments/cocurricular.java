package com.example.departments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.iet.R;

public class cocurricular extends Fragment{
	private ImageButton curricular;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.cocurricular,container,false);
        curricular=(ImageButton) rootView.findViewById(R.id.curricular);
        curricular.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				curricularActivities();
				
			}

			protected void curricularActivities() {
		final CharSequence[] activities={"Student's Societies","Encore","Shouryotsava"};
		AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
		alert.setTitle("Co-curriculars");
		
		alert.setSingleChoiceItems(activities,-1,new DialogInterface.OnClickListener() {
			private Intent fragment;
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch(which){
				case 0:fragment=new Intent("StudentsSocieties");break;
				case 1:fragment=new Intent("Encore");break;
				case 2:fragment=new Intent("Shouryotsava");break;
				
				}
			 startActivity(fragment);
			   dialog.cancel();
				
			}
		}); 
		alert.setCancelable(true);
		alert.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
				
			}
		});
		alert.show();
		
		
	
	
		
	}
        	
        });
        return rootView;
	}

}

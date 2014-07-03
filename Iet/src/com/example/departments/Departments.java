package com.example.departments;

import com.example.iet.R;

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

public class Departments extends Fragment{
	private ImageButton departments;
//	private android.app.Fragment fragment=null;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.departments,container,false);
        departments=(ImageButton) rootView.findViewById(R.id.departments);
        departments.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				openDepartmentsList();
				
			}

			protected void openDepartmentsList() {
		
		final CharSequence[] departments={"Computer Science & Engineering","Civil Engineering","Electrical Engineering","Electronics & Communication","Mechanical Engineering","Chemical Engineering","Biotechnology","Environmental Engineering","M.B.A"};
		AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
		alert.setTitle("Select Department");
		//final android.app.FragmentManager fragmentManager = getFragmentManager();
	
		alert.setSingleChoiceItems(departments,-1,new DialogInterface.OnClickListener() {
		
		

			private Intent fragment;

			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			switch(which){
				case 0:fragment=new Intent("ComputerScience");break;
				case 1:fragment=new Intent("CivilEngineering");break;
				case 2:fragment=new Intent("ElectricalEngineering");break;
				case 3:fragment=new Intent("ElectronicsCommEngineering");break;
				case 4:fragment=new Intent("MechanicalEngineering");break;
				case 5:fragment=new Intent("ChemicalEngineering");break;
				case 6:fragment=new Intent("Biotechnology");break;
				case 7:fragment=new Intent("EnvironmentalEngineering");break;
				case 8:fragment=new Intent("MBA");break;
				}
			 //  fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
				
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

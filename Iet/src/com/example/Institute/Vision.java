package com.example.Institute;

import adapter.TextViewEx;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.iet.R;

public class Vision extends Fragment{
	private SQLiteDatabase helper;

    public Cursor cursor;

	private MenuItem menuItem;
	
	public Vision(){
		
	}
	
	public Vision(SQLiteDatabase helper) {
		this.helper=helper;
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.vision, container, false);
      
        TextViewEx inst=(TextViewEx)rootView.findViewById(R.id.thought);
       inst.setText("\"To effectively contribute the national endeavour of producing world class manpower and to usher in Technology driven econoomic development of the country in order to in rich the quality of life of its citizen and to create and propagate innovative technologiese and optimize the utilization of resources for sustainable development.\"",true);

		

        
        
        

	 return rootView;
}
	
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
		menu.clear();
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.refresh, menu);
		
		
	}
	
	

	
	
	


}
package com.example.contactus;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.iet.R;

public class ContactUs extends Fragment{
	public Cursor cursor;

	public ContactUs(){
		
	}
	
	public ContactUs(SQLiteDatabase helper) {
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.contact_us, container, false);
        
        
      
        

	 return rootView;
}
	


}


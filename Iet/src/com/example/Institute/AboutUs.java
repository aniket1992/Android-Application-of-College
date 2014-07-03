package com.example.Institute;

import adapter.TextViewEx;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iet.R;

public class AboutUs extends Fragment{
	private SQLiteDatabase helper;

    public Cursor cursor;

	private MenuItem menuItem;
	
	public AboutUs(){
		
	}
	
	public AboutUs(SQLiteDatabase helper) {
		this.helper=helper;
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.aboutus, container, false);
      
         TextViewEx inst=(TextViewEx)rootView.findViewById(R.id.institute_aboutus);
         TextViewEx inst1=(TextViewEx)rootView.findViewById(R.id.institute_aboutus1);
         TextViewEx inst2=(TextViewEx)rootView.findViewById(R.id.institute_aboutus2);
         TextViewEx inst3=(TextViewEx)rootView.findViewById(R.id.institute_aboutus3);
        TextViewEx inst4=(TextViewEx)rootView.findViewById(R.id.institute_aboutus4);
        inst.setText("The IET was established by the Government of U.P in 1984, for imparting engineering education and for promoting technological research to generate suitable technical manpower. The institute  is fully financed by U.P Government and is being administered by the IET Society (registered under the Societies Act 1860) through its Board of Governors.\n",true);
       inst1.setText("\nThe IET Lucknow, started in November 1984, constituted the Faculty of Engineering and Technology of Lucknow University.Initially, it offered B.Tech degree in three branches, namely Computer Science, Electrical and Electronics. Within a year, two more branches i.e. Civil and Mechanical were introduced.\n",true);
        inst2.setText("The Campus of institute was constructed by Uttar Pradesh Rajkiya Nirman Nigam Ltd. (UPRNN) on an 100 acre plot purchased from Lucknow Development Authority against a sum of Rs. 2.40 crores. Inspite of initial difficulties, UPRNN constructed substation, Academic Block, two boys’ hostels,one girls’ hostel and nearly 70 residences. LDA provided its support for construction of external roads, electric supply, drainage and sewage, etc.",true);
        inst3.setText("IET fulfilled the long felt need of an Engineering College at Lucknow.It was the first state government Engineering College to start its classes in its own campus from the beginning itself.",true);
        inst4.setText("The founder Director, Prof. Suresh Chandra, joined on 26th June 1984 and the teaching faculty joined their posts from 11th October 1984 onwards. The Director, IET Lucknow, also assumed the office of Dean of Faculty of Engineering and Technology, Lucknow University on 25th April, 1985.",true);
        //inst4.setGravity(Gravity.RIGHT);
	 return rootView;
}
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
		menu.clear();
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.refresh, menu);
		
		
	}
	

	
	
	
	
	
}

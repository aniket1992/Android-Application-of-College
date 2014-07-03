package com.example.iet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TabHost;

import com.example.Institute.AboutUs;
import com.example.Institute.Administration;
import com.example.Institute.Faculty;
import com.example.Institute.ListOfHoliday;
import com.example.Institute.TheInstitute;
import com.example.Institute.Vision;


public class Institute extends Fragment {
	
	public  SQLiteDatabase helper;
    public Cursor cursor;
    public ImageButton theinstitute;
    public ImageButton vision;
    public ImageButton administration;
    public ImageButton aboutus;
    public ImageButton listofholiday;
	private TabHost mytabhost;
	
	
	 TabManager mTabManager;
	  public Institute(){
		
	}
	
	public Institute(SQLiteDatabase helper) {
		this.helper=helper;
	}
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        mTabManager = new TabManager(getActivity(),this.getFragmentManager(),R.id.realtabcontent);
	    }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
      final  View rootView = inflater.inflate(R.layout.institutetabs,container,false);
        final Activity cc=this.getActivity();
      

		try{
						        TabHost host = mTabManager.handleCreateView(rootView);
						        TabHost.TabSpec spec1,spec2,spec3,spec4,spec5,spec6;
						host.setup();
						   spec1=host.newTabSpec("administration").setIndicator("",getResources().getDrawable(R.drawable.ic_administration));
						//spec1=host.newTabSpec("administration").setIndicator("Administration");
						   spec2=host.newTabSpec("vision").setIndicator("",getResources().getDrawable(R.drawable.ic_vision));
						   spec3=host.newTabSpec("institute").setIndicator("",getResources().getDrawable(R.drawable.ic_tabinstitute));
						   spec4=host.newTabSpec("aboutus").setIndicator("",getResources().getDrawable(R.drawable.aboutus));
						   spec6=host.newTabSpec("faculty").setIndicator("",getResources().getDrawable(R.drawable.faculty));
						   spec5=host.newTabSpec("loh").setIndicator("",getResources().getDrawable(R.drawable.ic_holidays));
						        mTabManager.addTab(spec1,
						                Administration.class, null);
						        mTabManager.addTab(spec2,
						               Vision.class, null);
						        mTabManager.addTab(spec3,
						                TheInstitute.class, null);
						        mTabManager.addTab(spec4,
						                AboutUs.class, null);
						        mTabManager.addTab(spec6,
						                Faculty.class, null);
						        mTabManager.addTab(spec5,
						                ListOfHoliday.class, null);
						       
						        }catch(Exception e){
						        	e.printStackTrace();
						        }
						       
					
				
        return rootView;
        }
		

	
	
	
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabManager.handleDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mTabManager.handleSaveInstanceState(outState);
    }
	
}

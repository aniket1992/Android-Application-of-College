package com.example.placement;

import com.example.iet.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class PlacementRegistrationModule extends Fragment implements OnClickListener{

	private ImageButton placement;
	private ImageButton registration;
	public PlacementRegistrationModule(){
		
	}
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
	public void onClick(View v) {
		android.app.Fragment fragment=null;
	switch(v.getId()){
	case R.id.registration:
		Intent intent1 = new Intent("placement");
	    intent1.putExtra("url","http://www.ietlucknow.edu/online/Views/Recruiter/Recruiter.aspx");
	    
		            startActivity(intent1);
		           
		break;
	case R.id.placement:
		
		Intent intent2 = new Intent("placement");
	    intent2.putExtra("url","http://www.ietlucknow.edu/online/Views/User/Login.aspx");
		          startActivity(intent2);
    
		break;
	}
	}
	
}

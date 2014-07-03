package academics;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.iet.R;
import com.example.iet.TabManager;


public class Academics extends Fragment{

	private ImageButton departments;
	private android.app.Fragment fragment=null;
	private ImageButton curricular;
	private ImageButton syllabus;
	TabManager mTabManager;
	public Academics(SQLiteDatabase helper) {
	
	}
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        mTabManager = new TabManager(getActivity(),this.getFragmentManager(),R.id.realtabcontent);
	       
	    }
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.academics, container, false);
      
        departments=(ImageButton) rootView.findViewById(R.id.departments);
        curricular=(ImageButton) rootView.findViewById(R.id.curricular);
        syllabus=(ImageButton) rootView.findViewById(R.id.syllabus);
        syllabus.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				android.app.FragmentManager fragmentManager = getFragmentManager();
				fragment=new Syllabus();
				Log.d("fragmentinfo",""+fragment);
				 fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frame_container, fragment).commit();
				
				
			}
        	
        });
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

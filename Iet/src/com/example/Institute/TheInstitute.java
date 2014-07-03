package com.example.Institute;

import adapter.TextViewEx;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.iet.R;

public class TheInstitute extends Fragment{
	private SQLiteDatabase helper;

    public Cursor cursor;

	private MenuItem menuItem;
	
	public TheInstitute(){
		
	}
	
	public TheInstitute(SQLiteDatabase helper) {
		this.helper=helper;
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.the_institute, container, false);
      
        final TextViewEx inst=(TextViewEx)rootView.findViewById(R.id.institute_inst);
        
inst.setText("Institute of Engineering And Technology, Lucknow (better known as the \"Engineering College\" in Lucknow), is the oldest engineering college of Lucknow. It was inaugurated on November 1984 with the objective of producing quality engineers in every discipline. It includes com.example.departments of Computer Science and Engineering, Electronics Department, Mechanical Engineering Department, Electrical Engineering Department, Chemical Engineering Department, Civil Department, Applied Sciences Department, Bio Technology and Business Administration Department. Since its establishment, this institute has been proving itself to be the best engineering college of Lucknow.\n\nThe institute is situated on the outskirts of Aliganj or on the entrance of Lucknow from Sitapur road. It is well reputed all throughout the state of Uttar Pradesh and has been spreading its fame throughout the country through its professionals. The institute had started with a mere strength of around 200 students. Now the strength has reached up to 1500 students. The institute is run by the government with a highly qualified faculty in every discipline. The institute has been accredited by AICTE.\n\nThe institute is affiliated to the Uttar Pradesh Technical University (U.P.T.U). The institute organizes the selection process for the students in undergraduate and postgraduate programmes every year for the state of Uttar Pradesh. The SEE (State Entrance Examination) is conducted by UPTU and the online  counseling process is also done by UPTU.\n",true);
		
			/*	final InputStream is=getClass().getClassLoader().getResourceAsStream("assets/institute.htm");
					//final String url="http://ietlucknow.edu/institute.htm";
				
				 try {
					
					 Document doc = Jsoup.parse(is,"UTF-8","www.ietlucknow.edu");
					// Document doc=Jsoup.connect(url).get();
						 Element value=doc.getElementById("inner_text_1");
						 final String chars;
							String sql="insert into institute_inst values('"+value.text()+"')";
						
							//String sql="insert into institute_inst values('h')";
							helper.execSQL(sql);
							cursor =helper.rawQuery("SELECT * FROM institute_inst",null);
					        //if(cursor.moveToFirst()){
							cursor.moveToFirst();
					         chars=cursor.getString(cursor.getColumnIndex("theinstitute"));
					        	
					        //}
							 inst.setText(chars);
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}

        
        */
        

	 return rootView;
}
	
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
		menu.clear();
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.refresh, menu);
	
		
		
	}
	

	private void refresh(MenuItem item) {
		menuItem = item;
	      menuItem.setActionView(R.layout.progressbar);
	      menuItem.expandActionView();
	      TestTask task = new TestTask();
	      task.execute("test");
	}
	
	
	
	public class TestTask extends AsyncTask<String, Void, String> {

	    @Override
	    protected String doInBackground(String... params) {
	      // Simulate something long running
	      try {
	        Thread.sleep(5000);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	      return null;
	    }

	    @Override
	    protected void onPostExecute(String result) {
	      menuItem.collapseActionView();
	      menuItem.setActionView(null);
	    }
	  };
		
}
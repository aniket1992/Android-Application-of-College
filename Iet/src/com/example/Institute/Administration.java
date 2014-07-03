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

public class Administration extends Fragment{
	private SQLiteDatabase helper;

    public Cursor cursor;

	private MenuItem menuItem;
	
	public Administration(){
		
	}
	
	public Administration(SQLiteDatabase helper) {
		this.helper=helper;
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.administration, container, false);
       
       /* android.app.Fragment fragment = this;    
        FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, fragment).commit();
*/
	
   
        final TextViewEx inst=(TextViewEx)rootView.findViewById(R.id.institute_administration);
        inst.setText("The institute of engineering and technology is a government institution run by the state government. The administration in the institute includes some key positions :\n\n",true);

		/*StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

				final InputStream is=getClass().getClassLoader().getResourceAsStream("assets/administration.htm");
					//final String url="http://ietlucknow.edu/institute.htm";
				
				 try {
					
					 Document doc = Jsoup.parse(is,"UTF-8","www.ietlucknow.edu");
					// Document doc=Jsoup.connect(url).get();
						 Element value=doc.getElementById("inner_text_1");
						 //Elements value=table.getElementsByTag("td");

						 final String chars;
							String sql="insert into institute_administration values('"+value.text()+"')";
						
							//String sql="insert into institute_inst values('h')";
							helper.execSQL(sql);
							cursor =helper.rawQuery("SELECT * FROM institute_administration",null);
					        //if(cursor.moveToFirst()){
							cursor.moveToFirst();
					         chars=cursor.getString(cursor.getColumnIndex("administration"));
					        	
					        //}
							 //inst.setText(chars);
						
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
	
	
	
	private class TestTask extends AsyncTask<String, Void, String> {

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
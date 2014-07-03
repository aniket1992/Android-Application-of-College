package com.example.placement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.iet.R;

import database.IetDatabase;

public class Events extends Fragment{
	private SQLiteDatabase helper;
	 int count=0;
    public Cursor cursor;

	private MenuItem menuItem;
	String chars1;
	private String[] eventsC=new String[500];

	private ListView newslist;

	private ArrayAdapter<String> listAdapter;
	private String[] events=new String[500];
	
	public Events(){
		
	}
	
	public Events(SQLiteDatabase helper) {
		this.helper=helper;
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.placement_news_events, container, false);
        newslist=(ListView)rootView.findViewById(R.id.news_events_list);
      helper=new IetDatabase(getActivity()).getWritableDatabase();
   		
					//final String url="http://ietlucknow.edu/institute.htm";
				
				 try {
					 /*final InputStream is=getClass().getClassLoader().getResourceAsStream("assets/placementcellnews/placementcell_events.htm");
					 Document doc = Jsoup.parse(is,"UTF-8","www.ietlucknow.edu");
					// Document doc=Jsoup.connect(url).get();
					 Element marquee=doc.getElementById("middle");
						Elements marq=marquee.getElementsByTag("p");
						for(Element mar:marq){
						Elements p=mar.getElementsByTag("strong");
						
						//System.out.println(mar.text()+p.text());

						String sql="insert into placement_events values('"+p.text().replaceAll("'", "")+"','"+mar.text().replaceAll("'", "")+"')";
					 
					
					      //Log.d(".","."+mar.text()+". "+p.text());
					      //System.out.println(m1.text()+". "+p1.text());
					   helper.execSQL(sql);
							
						}*/
							cursor =helper.rawQuery("SELECT * FROM placement_events",null);
							 cursor.moveToFirst();
						       
						        do{
						         chars1=cursor.getString(cursor.getColumnIndex("events"));
	                             //chars2=cursor.getString(cursor.getColumnIndex("url"));
						        //System.out.println(chars1+"  "+chars2);
						
						         events[count]=chars1;
						         count++;
						        // System.out.print(resultname);
						        }while(cursor.moveToNext());
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				 String[] eventsC=new String[count];
	                for(int i=0;i<count;i++)
	                	eventsC[i]=events[i];
                 
				 ArrayList<String> rlist=new ArrayList<String>();
				 rlist.addAll(Arrays.asList(eventsC));
				 listAdapter=new ArrayAdapter<String>(getActivity(),R.layout.simplerow,R.id.rowTextView,rlist);
				 try{
				 newslist.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
				 }catch(Exception e){
			    		System.err.print(e);
			    	}
				 newslist.setAdapter(listAdapter);
        
        

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


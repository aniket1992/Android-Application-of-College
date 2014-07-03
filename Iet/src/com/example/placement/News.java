package com.example.placement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import service.PlacementNews;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

import com.example.iet.MainActivity;
import com.example.iet.Notificator;
import com.example.iet.R;
import com.example.result.Result.TestTask;

import database.IetDatabase;

public class News extends Fragment{
	protected static final float THRESHOLD = 130;
	private SQLiteDatabase helper;
	 int count=0;
	 boolean touch =true;
    public Cursor cursor;

	private MenuItem menuItem;
	String chars1,chars2;
	private MenuInflater menuflater;
	private Menu menuNew;
	private String[] news=new String[500];
	private boolean STATE_REFRESH_ENABLED;
	private boolean STATE_REFRESHING;
	private ListView newslist;

	private ArrayAdapter<String> listAdapter;
	private ArrayList<String> rlist;
	
	public News(){
		
	}
	
	public News(SQLiteDatabase helper) {
		this.helper=helper;
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        final View rootView = inflater.inflate(R.layout.placement_news_events, container, false);
        this.setHasOptionsMenu(true);
        newslist=(ListView)rootView.findViewById(R.id.news_events_list);
      helper=new IetDatabase(getActivity()).getWritableDatabase();
   		
					//final String url="http://ietlucknow.edu/institute.htm";
				
				 try {
					/* final InputStream is=getClass().getClassLoader().getResourceAsStream("assets/placementcellnews/placementcell_news.htm");
					 Document doc = Jsoup.parse(is,"UTF-8","www.ietlucknow.edu");
					// Document doc=Jsoup.connect(url).get();
						 Element value=doc.getElementById("iet_news_data");
						 Elements marq=value.getElementsByTag("div");
						    int i=0;
						    
							for(Element mar:marq )
								
								 {
								
								if(i==0){
									i++;
									 continue;
								 }
								else{
									Elements m1=mar.getElementsByTag("h4");
									Elements p1=mar.getElementsByTag("p");
									String m11=m1.text();
									String p11=p1.text();
								
									String sql="insert into placement_news values('"+m11.replaceAll("'", "")+"','"+p11.replaceAll("'", "")+"')";
								 
								
								Log.d(".","."+m11+". "+p11);
								//System.out.println(m1.text()+". "+p1.text());
								helper.execSQL(sql);
								}
								 }
						 
							*/
							
							cursor =helper.rawQuery("SELECT * FROM placement_news",null);
							 cursor.moveToFirst();
						       
						        do{
						         chars1=cursor.getString(cursor.getColumnIndex("news"));
	                             chars2=cursor.getString(cursor.getColumnIndex("heading"));
						        //System.out.println(chars1+"  "+chars2);
						         chars2=chars2.toUpperCase();
						         news[count]=chars2+'\n'+chars1;
						         count++;
						       
						        }while(cursor.moveToNext());
						      /*  cursor1=helper.rawQuery("SELECT * FROM placement_new_temp",null);
								cursor1.moveToFirst();
								do{
									try{
									newNews=cursor1.getString(cursor1.getColumnIndex("newnews"));
									}catch(Exception e){
										break;
									}
									 newNewsA[counter]=newNews;
									 counter++;
								}while(cursor1.moveToNext());*/
					} catch (Exception e) {
						
						e.printStackTrace();
					}
                String[] newsC=new String[count];
                for(int i=0;i<count;i++)
                	newsC[i]=news[i];
				 rlist=new ArrayList<String>();
				 rlist.addAll(Arrays.asList(newsC));
				 listAdapter=new ArrayAdapter<String>(getActivity(),R.layout.simplerow,R.id.rowTextView,rlist);
				 try{
				 newslist.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
				 }catch(Exception e){
			    		System.err.print(e);
			    	}
				 newslist.setAdapter(listAdapter);
				 final ActionBar actionBar = getActivity().getActionBar();
				 if(Notificator.touch_notification==true){
				 newslist.setOnTouchListener(new OnTouchListener(){

					
						private float startY;
					

						@Override
						public boolean onTouch(View v, MotionEvent event) {
							float y=event.getY();
							switch(event.getAction()){
							case MotionEvent.ACTION_MOVE:{
								if((y-startY)>20 && !STATE_REFRESHING && STATE_REFRESH_ENABLED && touch){
									Log.d("", "activated");
									inflate();
									actionBar.setCustomView(R.layout.actionbar);
									actionBar.setDisplayShowCustomEnabled(true);
									if((y-startY)>THRESHOLD){
								if(isNetworkAvaialable()){
								 TestTask task = new TestTask(rootView,newslist,getActivity());
							      task.execute("test");
										load();}
								
								}
								else
								{
									final Toast toast=Toast.makeText(getActivity(), "No Internet Connection..retry",Toast.LENGTH_SHORT);
									toast.show();
									STATE_REFRESHING=false;
									Handler handler=new Handler();
									handler.postDelayed(new Runnable(){
										public void run(){
											toast.cancel();
										}
									},500);
								}}
							}
								break;
							case MotionEvent.ACTION_DOWN:{
								startY=y;
								STATE_REFRESH_ENABLED=newslist.getFirstVisiblePosition()==0;
								touch=true;
							}
							break;
							case MotionEvent.ACTION_UP:{
								STATE_REFRESHING=false;
								deInflate();
								actionBar.setDisplayShowCustomEnabled(false);
							}
							break;
							}
							return false;
							
						}

						private void load() {
							touch=false;
							 String chars11,chars12;
								int count=0;
								rlist.clear();
								cursor =helper.rawQuery("SELECT * FROM placement_news",null);
								 cursor.moveToFirst();
							       
								   do{
								         chars11=cursor.getString(cursor.getColumnIndex("news"));
			                             chars12=cursor.getString(cursor.getColumnIndex("heading"));
								        //System.out.println(chars1+"  "+chars2);
								         chars12=chars12.toUpperCase();
								         news[count]=chars12+'\n'+chars11;
								         count++;
								       
								        }while(cursor.moveToNext());
							         String[] newsC=new String[count];
						                for(int i=0;i<count;i++)
						                	newsC[i]=news[i];
								rlist.addAll(Arrays.asList(newsC));
								Log.d("aaaa","aaa");
								listAdapter.notifyDataSetChanged();
								
						} 
			 });
				 }

	 return rootView;
}
	
private boolean isNetworkAvaialable() {
		
		ConnectivityManager cm=(ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
		try{if(cm.getActiveNetworkInfo().isConnected()){
			
		
		return (true);
		}
		
		else
			return (false);
		}
         catch(Exception ex){
        	 return false;
		}
		
	}

	
	
	private void refresh(MenuItem item) {
		menuItem = item;
	      menuItem.setActionView(R.layout.progressbar);
	      menuItem.expandActionView();
	      //TestTask task = new TestTask();
	      //task.execute("test");
	}
	
	
	
	public class TestTask extends AsyncTask<String, Void, String> {

	    private Context context;
		private View rootView;
		private ListView newslist;
		private LinearLayout linlaHeaderProgress;
	   
		public TestTask(View rootView, ListView newslist,Context applicationContext) {
			this.context=applicationContext;
			this.rootView=rootView;
			this.newslist=newslist;
			
			 linlaHeaderProgress = (LinearLayout)rootView.findViewById(R.id.linlaHeaderProgress);
		}
		@Override
	    protected String doInBackground(String... params) {
	      // Simulate something long running
	      try {
	        new PlacementNews().checkPlacement(context);
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      return null;
	    }

		  @Override
		    protected void onPreExecute() {    
		        // SHOW THE SPINNER WHILE LOADING FEEDS
		    	newslist.setVisibility(View.GONE);
		    	try{
		    	newslist.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
		    	linlaHeaderProgress.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
		       linlaHeaderProgress.setVisibility(View.VISIBLE);
		    	}catch(Exception e){
		    		System.err.print(e);
		    	}
		       
		    }
		    @Override
		    protected void onPostExecute(String result) {
		    	STATE_REFRESHING=false;
		    	try{
		    	linlaHeaderProgress.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
		    	linlaHeaderProgress.setVisibility(View.GONE);
		    	newslist.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
		    	newslist.setVisibility(View.VISIBLE);
		    	}catch(Exception e){
		    		System.err.print(e);
		    	}
		    }
	  };
	  public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
			super.onCreateOptionsMenu(menu, inflater);
			Log.d("call","lmkl");
			this.setHasOptionsMenu(true);
			//menu.findItem(R.id.downloads).setVisible(false);
			//inflater.inflate(R.menu.contextual, menu);
			menuflater=inflater;
			menuNew=menu;
		}
	  protected void deInflate() {
			menuNew.findItem(R.id.downloads).setVisible(true);
			menuNew.findItem(R.id.action_back).setVisible(true);
			menuNew.findItem(R.id.action_settings).setVisible(true);
			MainActivity.mDrawerToggle.setDrawerIndicatorEnabled(true);
			
		}

		protected void inflate() {
			menuNew.findItem(R.id.downloads).setVisible(false);
			menuNew.findItem(R.id.action_back).setVisible(false);
			menuNew.findItem(R.id.action_settings).setVisible(false);
			MainActivity.mDrawerToggle.setDrawerIndicatorEnabled(false);
		
			
		}

}

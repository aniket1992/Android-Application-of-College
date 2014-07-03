
package com.example.result;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import service.IETService;
import android.widget.AdapterView.OnItemClickListener;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iet.MainActivity;
import com.example.iet.Notificator;
import com.example.iet.R;

public class Result extends Fragment{
	private static final float THRESHOLD = 300;
	 boolean touch =true;
	 boolean flag =false;
	private SQLiteDatabase helper;
	private String[] newNewsA=new String[500];
    public Cursor cursor,cursor1;

	private MenuItem menuItem;
	String[] resultname=new String[500];
	int count=0,counter=0;
	private ListView resultlist;
	 List<HashMap<String,String>> aList;
	 private ArrayAdapter<String> listAdapter;

	private ArrayList<String> rlist;

	private ImageButton refresh;

	private float startY;

	private boolean STATE_REFRESH_ENABLED;

	private boolean STATE_REFRESHING;

	private TextView swipe;
	private MenuInflater menuflater;
	private Menu menuNew;
	
	public Result(){
		
	}
	
	public Result(SQLiteDatabase helper) {
		this.helper=helper;
	}
	@Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		 /* =*/ 
       final  View rootView = inflater.inflate(R.layout.result, container, false);
                this.setHasOptionsMenu(true);
        		resultlist=(ListView)rootView.findViewById(R.id.resultlist);
        		swipe=(TextView)rootView.findViewById(R.id.swipeToRefreshText);
                
       		 final InputStream is=getClass().getClassLoader().getResourceAsStream("assets/result/result.htm");
				
				
						try {
							 String chars1,chars2;
							 String sql,link;
							 String newNews;
							
								cursor =helper.rawQuery("SELECT * FROM result",null);
							       
							    	   cursor.moveToFirst();
							       
							        
							        do{
							         chars1=cursor.getString(cursor.getColumnIndex("resultname"));
		                            //chars2=cursor.getString(cursor.getColumnIndex("url"));
							        //System.out.println(chars1+"  "+chars2);
							         resultname[count]=chars1;
							         count++;
							        // System.out.print(resultname);
							        }while(cursor.moveToNext());
							        cursor1=helper.rawQuery("SELECT * FROM results_new",null);
							      
									cursor1.moveToFirst();
									Log.d("1","1");
							        do{
							        	
							        	Log.d("2","2");
										try{newNews=cursor1.getString(cursor1.getColumnIndex("newnews"));
										}catch(Exception e){
											break;
										}
										 newNewsA[counter]=newNews;
										 counter++;
									}while(cursor1.moveToNext());
							       
							} catch (Exception e) {
								
								e.printStackTrace();
							}	
					
				
				
                
						 String[] newsC=new String[count];
						    int[] flags = new int[count];
			                for(int i=0;i<count;i++)
			                	{
			                	try{
			                	if(newNewsA[i].contains("true") ){
			                		newsC[i]=resultname[i]+"";
			                		flags[i]=R.drawable.newgh;
			                	}
			                	else{
			                	newsC[i]=resultname[i];
			                	flags[i]=R.drawable.white;
			                	}
			                	}
			                	catch(Exception e){
			                		newsC[i]=resultname[i];
			                		flags[i]=R.drawable.white;
			                	}
			                	}
			                
			           
			                
			                aList = new ArrayList<HashMap<String,String>>();
			                for(int i=0;i<count;i++){
			                    HashMap<String, String> hm = new HashMap<String,String>();
			                    hm.put("txt",newsC[i]);
			                    hm.put("flag", Integer.toString(flags[i]) );
			                    aList.add(hm);
			                }
			                String[] from = { "txt","flag"};
			                
			                // Ids of views in listview_layout
			                int[] to = { R.id.rowTextView,R.id.newgh};
			                final SimpleAdapter adapter = new SimpleAdapter(getActivity(), aList, R.layout.simplerow, from, to);
			                
			                // Getting a reference to listview of main.xml layout file
			               
			         
			                // Setting the adapter to the listView
			                resultlist.setAdapter(adapter);
			                try{
			                resultlist.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
			                }catch(Exception e){
			    	    		System.err.print(e);
			    	    	}		
			                resultlist.setOnItemClickListener(new OnItemClickListener(){
								 
								@Override
								public void onItemClick(AdapterView<?> arg0, View arg1,
										int position, long id) {
									// TODO Auto-generated method stub
									
									cursor.moveToPosition(position);
									String link=cursor.getString(cursor.getColumnIndex("url"));
									if(link.contains("http://www.ietlucknow.edu")||link.contains("http://ietlucknow.edu")){
										
									}
									else{
										link="http://ietlucknow.edu/"+link;
										
									}
									Log.d("df", "fg"+link);
									boolean check=isNetworkAvaialable();
									if(check){
									open(link);
									}
									else{
										dialog(link);
										
									}
								}

								public void dialog(final String link) {

									AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
									alert.setTitle("No Internet");
									alert.setMessage("No Internet Connection Available.Do you want to try again?");
									alert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											if(isNetworkAvaialable()){
												open(link);
											}
											else{
												dialog(link);
											}
										
											
											
										}
									});
									alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											dialog.cancel();
											
										}
									});
									alert.show();
									
								}
								

								private void open(String link) {
									Intent intent1 = new Intent("placement");
								    intent1.putExtra("url",link);
								    
									startActivity(intent1);
									
								}

								
								
							});
			                final ActionBar actionBar=getActivity().getActionBar();
							 if(Notificator.touch_notification==true){
			                resultlist.setOnTouchListener(new OnTouchListener(){
								
								@Override
								public boolean onTouch(View v, MotionEvent event) {
									float y=event.getY();
									float x;
									
									switch(event.getAction()){
									case MotionEvent.ACTION_MOVE:{
									// x = 
											
									//	actionBar.setTitle("Swipe");
										//	
										Log.d("","21"+touch);
										
											if((y-startY)>20 && !STATE_REFRESHING && STATE_REFRESH_ENABLED && touch){
												Log.d("", "activated");
												
											//	actionBar.setBackgroundDrawable(new ColorDrawable(Color.RED));
												//inflater.inflate(R.menu.webview,null);
												inflate();
												
												  actionBar.setCustomView(R.layout.actionbar);
												actionBar.setDisplayShowCustomEnabled(true);
												
												if((y-startY)>THRESHOLD){
											
												if(isNetworkAvaialable()){
													final TestTask task = new TestTask(rootView,resultlist);
													task.execute("test");
													
																	load();
														
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
												}
												
												}
												}
											}
												break;
									case MotionEvent.ACTION_DOWN:{
										startY=y;
										
										STATE_REFRESH_ENABLED=resultlist.getFirstVisiblePosition()==0;
									}
									break;
									case MotionEvent.ACTION_UP:{
										//Log.d("leave","leave");
										STATE_REFRESHING=false;
										touch=true;
										deInflate();
										
										actionBar.setDisplayShowCustomEnabled(false);
										
										
									}
									break;
									}
									
									return false;
									
								}

								private void load() {
									touch=false;
									String chars12,newNews;
									int count=0;
									//rlist.clear();
									aList.clear();
									cursor =helper.rawQuery("SELECT * FROM result",null);
							        cursor.moveToFirst();
							        do{
							         chars12=cursor.getString(cursor.getColumnIndex("resultname"));
			                        //chars2=cursor.getString(cursor.getColumnIndex("url"));
							        //System.out.println(chars1+"  "+chars2);
							         resultname[count]=chars12;
							         count++;
							        // System.out.print(resultname);
							        }while(cursor.moveToNext());
							        
									
									
									
									 cursor1=helper.rawQuery("SELECT * FROM results_new",null);
								      
										cursor1.moveToFirst();
										Log.d("1","1");
								        do{
								        	
								        	Log.d("2","2");
											try{
												newNews=cursor1.getString(cursor1.getColumnIndex("newnews"));
											}catch(Exception e){
												break;
											}
											 newNewsA[counter]=newNews;
											 counter++;
										}while(cursor1.moveToNext());
								        

										 String[] newsC=new String[count];
										 int[] flags = new int[count];
							                for(int i=0;i<count;i++)
							                	{
							                	try{
							                	if(newNewsA[i].contains("true") ){
							                		newsC[i]=resultname[i]+"";
							                		flags[i]=R.drawable.newgh;
							                	}
							                	else{
							                	newsC[i]=resultname[i];
							                	flags[i]=R.drawable.white;
							                	
							                	}}
							                	catch(Exception e){
							                		newsC[i]=resultname[i];
							                		flags[i]=R.drawable.white;
							                	}
							                	}
							               
							               
							                for(int i=0;i<count;i++){
							                    HashMap<String, String> hm = new HashMap<String,String>();
							                    hm.put("txt",newsC[i]);
							                    hm.put("flag", Integer.toString(flags[i]) );
							                    aList.add(hm);
							                }
								
								 adapter.notifyDataSetChanged();
								 MainActivity.queries(helper);
									
								}
								 
							 });
							
 
	}

	 return rootView;
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
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
		super.onCreateOptionsMenu(menu, inflater);
		Log.d("call","lmkl");
		this.setHasOptionsMenu(true);
		//menu.findItem(R.id.downloads).setVisible(false);
		//inflater.inflate(R.menu.contextual, menu);
		menuflater=inflater;
		menuNew=menu;
	}
	/*public void onPrepareOptionsMenu(Menu menu){
		super.onPrepareOptionsMenu(menu);
		menu.findItem(R.id.downloads).setVisible(false);
	}*/
	
	private void refresh(MenuItem item) {
		menuItem = item;
	      menuItem.setActionView(R.layout.progressbar);
	      menuItem.expandActionView();
	   //   TestTask task = new TestTask();
	  //    task.execute("test");
	}
	
	
	
	public class TestTask extends AsyncTask<String, Void, String> {
		
		private View rootView;
		 LinearLayout linlaHeaderProgress ;
		private ListView resultlist;
		public TestTask(View rootView, ListView resultlist) {
			this.rootView=rootView;
			this.resultlist=resultlist;
			
			 linlaHeaderProgress = (LinearLayout)rootView.findViewById(R.id.linlaHeaderProgress);
			 
		}
	
		
	    @Override
	    protected String doInBackground(String... params) {
	      // Simulate something long running
	     try {
	    	 /* if(isNetworkAvaialable()){
	    		  
	    	  }*/
	    	  new IETService().checkResult(getActivity());
	    	 
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      
	      return null;
	    }
	   
	    @Override
	    protected void onPreExecute() {    
	        // SHOW THE SPINNER WHILE LOADING FEEDS
	    	Log.d("","pre");
	    	try{
	    	resultlist.setVisibility(View.GONE);
	    	resultlist.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
	    	linlaHeaderProgress.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
	       linlaHeaderProgress.setVisibility(View.VISIBLE);
	    	}catch(Exception e){
	    		System.err.print(e);
	    	}
	       
	    }
	    @Override
	    protected void onPostExecute(String result) {
	    	Log.d("", ""+touch);
	    	STATE_REFRESHING=false;
	    	flag=true;
	    	try{
	    	linlaHeaderProgress.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
	    	linlaHeaderProgress.setVisibility(View.GONE);
	    	resultlist.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
	    	resultlist.setVisibility(View.VISIBLE);
	    	}catch(Exception e){
	    		System.err.print(e);
	    	}
	    }
	  }



	





	
	
}

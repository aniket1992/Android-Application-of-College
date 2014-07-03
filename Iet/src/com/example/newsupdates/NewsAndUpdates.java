package com.example.newsupdates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import service.NewsService;

import adapter.CheckInternet;
import adapter.Download;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.iet.MainActivity;
import com.example.iet.Notificator;
import com.example.iet.R;
import com.example.result.Result;
import com.example.result.Result.TestTask;

public class NewsAndUpdates extends Fragment{
	protected static final int THRESHOLD = 300;
	 boolean touch =true;
	private SQLiteDatabase helper;
	 List<HashMap<String,String>> aList;
    public Cursor cursor=null;
    public Cursor cursor1=null;

	private MenuItem menuItem;

	private String[] news=new String[500];
	private String[] newNewsA=new String[500];


	private ListView listView;

	private ArrayAdapter<String> listAdapter;

	private boolean STATE_REFRESH_ENABLED;
	private boolean STATE_REFRESHING;
	private Dialog dialog;

	private ArrayList<String> lists;
	private Menu menuNew;
	
	public NewsAndUpdates(){
		
	}
	
	public NewsAndUpdates(SQLiteDatabase helper) {
		this.helper=helper;
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		int count=0,counter=0;
        final View rootView = inflater.inflate(R.layout.news_updates, container, false);
      this.setHasOptionsMenu(true);
       // final TextView inst=(TextView)rootView.findViewById(R.id.news_updates);
        listView =(ListView)rootView.findViewById(R.id.newslist);
       
			
				 try {
					 String chars1;
						String chars2;
						String newNews;
					/*	final InputStream is=getClass().getClassLoader().getResourceAsStream("assets/newsupdates/news.js");
						//final String url="http://ietlucknow.edu/institute.htm";
					boolean newnews=false;
					 String sql,link="";
				 
					
				
					// Document doc=Jsoup.connect(url).get();
					 Document doc = Jsoup.parse(is,"UTF-8","www.ietlucknow.edu");
					 Elements table=doc.getElementsByTag("a");
					 Elements img=doc.getElementsByTag("img");
						int i=1,k=1;
						String l;
						   for(Element p:table){
							i++;
							k++;
							   if(i==k)
							   { 
								   if(i==2){
									   continue;
								   }
								   
								   
								   for(Element o:img){
									   if(o.previousElementSibling().text().equals(p.text())){
										   
										 newnews=true;
										   break;
									   }
									   else{
										   newnews=false;
										   
									   }
									   
								   }
							   
							   System.err.print(p.text()+"  "+newnews);
							   link=p.attr("href");
							  // System.out.println(link);
							   sql="insert into news_updates values('"+p.text()+"','"+link+"','"+newnews+"')";
						       helper.execSQL(sql);
							   
							   }
							   
								  
						  
						   }
						*/
						cursor=helper.rawQuery("SELECT * FROM news_updates",null);
					        cursor.moveToFirst();
					        
					       do{
					         chars1=cursor.getString(cursor.getColumnIndex("news"));
					        // newNews=cursor.getString(cursor.getColumnIndex("newnews"));
                             //chars2=cursor.getString(cursor.getColumnIndex("url"));
					        //System.out.println(chars1+"  "+chars2);
					         news[count]=chars1;
					        
					         count++;
					        // System.out.print(resultname);
					       //  inst.setText(news);
					        }while(cursor.moveToNext());
							
					       cursor1=helper.rawQuery("SELECT * FROM news_updates_new",null);
							cursor1.moveToFirst();
						do{
							try{
							newNews=cursor1.getString(cursor1.getColumnIndex("newnews"));
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
	                	{try{
	                	if(newNewsA[i].contains("true")){
	                		newsC[i]=news[i]+"";
	                		flags[i]=R.drawable.newgh;
	                	}
	                	else{
	                	newsC[i]=news[i];
	                	flags[i]=R.drawable.white;
	                	}
	                	}
	                	catch(Exception e){
	                		newsC[i]=news[i];
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
	                int[] to = { R.id.rowTextView,R.id.newgh};
	                final SimpleAdapter adapter = new SimpleAdapter(getActivity(), aList, R.layout.simplerow, from, to);
	                
	                // Getting a reference to listview of main.xml layout file
	               
	         
	                // Setting the adapter to the listView
	               listView.setAdapter(adapter);
	               try{listView.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
	               }catch(Exception e){
	   	    		System.err.print(e);
	   	    	}
				 final ActionBar actionBar = getActivity().getActionBar();
				 if(Notificator.touch_notification==true){
				listView.setOnTouchListener(new OnTouchListener(){
					

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
									if(isNetworkAvailable()){
									 TestTask task = new TestTask(rootView,listView,getActivity());
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
									}}
								}
									break;
								case MotionEvent.ACTION_DOWN:{
									startY=y;
									STATE_REFRESH_ENABLED=listView.getFirstVisiblePosition()==0;
								}
								break;
								case MotionEvent.ACTION_UP:{
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
								 String chars12,newNews12;
									int count=0,counter=0;
									aList.clear();
								cursor=helper.rawQuery("SELECT * FROM news_updates",null);
						        cursor.moveToFirst();
						        
						       do{
						          chars12 = cursor.getString(cursor.getColumnIndex("news"));
						          
	                             //chars2=cursor.getString(cursor.getColumnIndex("url"));
						        //System.out.println(chars1+"  "+chars2);
						         news[count]=chars12;
						        
						         count++;
						        // System.out.print(resultname);
						       //  inst.setText(news);
						        }while(cursor.moveToNext());
						       cursor1=helper.rawQuery("SELECT * FROM news_updates_new",null);
								cursor1.moveToFirst();
							do{try{
								newNews12=cursor1.getString(cursor1.getColumnIndex("newnews"));
							}catch(Exception e){
								break;
							}
								 newNewsA[counter]=newNews12;
								 counter++;
							
							}while(cursor1.moveToNext());
							 String[] newsC=new String[count];
							  int[] flags = new int[count];
				                for(int i=0;i<count;i++)
				                	{try{
				                	if(newNewsA[i].contains("true")){
				                		newsC[i]=news[i]+"";
				                		flags[i]=R.drawable.newgh;
				                	}
				                	else{
				                	newsC[i]=news[i];
				                	flags[i]=R.drawable.white;
				                	}
				                	}
				                	catch(Exception e){
				                		newsC[i]=news[i];
				                		flags[i]=R.drawable.white;
				                	}
				}
				              
				                for(int i=0;i<count;i++){
				                    HashMap<String, String> hm = new HashMap<String,String>();
				                    hm.put("txt",newsC[i]);
				                    hm.put("flag", Integer.toString(flags[i]) );
				                    aList.add(hm);
				                }
								Log.d("aaaa","aaa");
								adapter.notifyDataSetChanged();
								MainActivity.queries(helper);
							
								
							} 
				 });
				
				 }
				 final String googleDocs="http://docs.google.com/viewer?url=";
				 
                   listView.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
					
						cursor.moveToPosition(position);
						String link=cursor.getString(cursor.getColumnIndex("url"));
						if(link.contains("http://ietlucknow.edu")||link.contains("http://www.ietlucknow.edu")){
							
							
						}
						else{
							link="http://ietlucknow.edu/"+link;
							
						}
						boolean check=isNetworkAvailable();
						if(check){
						view(link);
						}
						else{
							dialog(link);
							
						}
					
						
						Log.d("df", "fg"+googleDocs+link);
					}

					
						public void dialog(final String link) {

							AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
							alert.setTitle("No Internet");
							alert.setMessage("No Internet Connection Available.Do you want to try again?");
							alert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									if(isNetworkAvailable()){
										view(link);
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

					private void view(String link) {
						if(link.contains(".htm")||link.contains(".html")||link.contains(".aspx")||link.contains(".asp")){
							if(link.contains("result.htm")|| link.contains("result.html")){
								android.app.Fragment fragment = new Result(helper);
								Log.d("h", "yippi");
								android.app.FragmentManager fragmentManager = getFragmentManager();
								fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frame_container, fragment).commit();
								
							}
							else{
							Intent intent1 = new Intent("placement");
						    intent1.putExtra("url",link);
						   
						    	startActivity(intent1);
						    	
							}
							}
						else{
						
						    final String linknew=link;
						    AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
							
							alert.setTitle("PDF FILE:");
							alert.setPositiveButton("Download Pdf",new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									
								 Intent intent=new Intent("download_pdf");
								intent.putExtra("url", linknew);
								 startActivity(intent);
									/* Download down=new Download();
									Boolean result=down.isDownloadManagerAvailabl(getActivity().getApplicationContext());
									 if (result)
							              down.downloadFile();
				
									
								*/
								}
							});
							alert.setNegativeButton("View Pdf",new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									
									Intent intent1 = new Intent("placement");
								    intent1.putExtra("url",Uri.parse(googleDocs+linknew).toString());
								    
									 startActivity(intent1);
									
								}
							});
							alert.show();
							
							
													}
						
					}

					
	             });			
        
        
        

	 return rootView;
}
	protected void deInflate() {
		menuNew.findItem(R.id.downloads).setVisible(true);
		menuNew.findItem(R.id.action_back).setVisible(true);
		menuNew.findItem(R.id.action_settings).setVisible(true);
		
	}

	protected void inflate() {
		menuNew.findItem(R.id.downloads).setVisible(false);
		menuNew.findItem(R.id.action_back).setVisible(false);
		menuNew.findItem(R.id.action_settings).setVisible(false);
		
	}
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
		super.onCreateOptionsMenu(menu, inflater);
		Log.d("call","lmkl");
		this.setHasOptionsMenu(true);
		//menu.findItem(R.id.downloads).setVisible(false);
		menuNew=menu;
		//inflater.inflate(R.menu.contextual, menu);
		
		
	}
	private boolean isNetworkAvailable() {
		
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
	
	
	
	private class TestTask extends AsyncTask<String, Void, String> {

		private Context context;
		private View rootView;
		private ListView listView;
		private LinearLayout linlaHeaderProgress;

		
	    public TestTask(View rootView, ListView listView,Context context) {
	    	this.rootView=rootView;
			this.listView=listView;
			this.context=context;
			 linlaHeaderProgress = (LinearLayout)rootView.findViewById(R.id.linlaHeaderProgress);
		}
		@Override
	    protected String doInBackground(String... params) {
	      // Simulate something long running
	      try {
	       new NewsService().checkNews(context);
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      return null;
	    }
	    protected void onPreExecute() {    
	        // SHOW THE SPINNER WHILE LOADING FEEDS
	    	try{
	    	listView.setVisibility(View.GONE);
	    	listView.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
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
	    	listView.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
	    	listView.setVisibility(View.VISIBLE);
	    	}catch(Exception e){
	    		System.err.print(e);
	    	}
	    }
	  };
}
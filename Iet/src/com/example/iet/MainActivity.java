package com.example.iet;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import model.NavDrawerItem;
import academics.Academics;
import adapter.NavDrawerListAdapter;
import adapter.Settings;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.contactus.ContactUs;
import com.example.faq.FAQ;
import com.example.newsupdates.NewsAndUpdates;
import com.example.placement.placement_web;
import com.example.result.Result;
import com.scringo.Scringo;
import com.scringo.Scringo.ScringoFeature;
import com.scringo.Scringo.ScringoIcon;
import com.scringo.ScringoActivationButton;

import database.IetDatabase;


public class MainActivity extends Activity implements OnClickListener{
	
	
	public  DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	public SQLiteDatabase helper;
	static boolean popback=false;
	static final String net_scringo="true";
	private Fragment mDrawer;
	 boolean checkFinish=false;
	
     static  public ActionBarDrawerToggle mDrawerToggle,mDrawerToggleNew;
	static int settings=0;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	private MenuItem menuItem;
	private Cursor cursor;
	public static Cursor cursor1;
	String counter="9";
	public static Integer result_counter=0,news_counter=0;
	static int i=1;
	NotificationManager notificationManager;
	private String open_fragment;
	SharedPreferences net_scringo_shared;
	private boolean open_scringo=true;
	private Scringo scringo=new Scringo(this);
	private ScringoFeature feature;
	private boolean drawerOpen;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// this.requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY  );
		
		//scringo= new Scringo(this);
		
		//getActionBar().setDisplayShowCustomEnabled(false);
		//getActionBar().setBackgroundDrawable(new ColorDrawable(-10036244));
		
		net_scringo_shared=getSharedPreferences("net_scringo",0);
		
		try{
			
			
			//Scringo.setLogLevel(ScringoLogLevel.SCRINGO_LOG_LEVEL_DEBUG);
			scringo.setIcon(ScringoIcon.PERSON);
			scringo.init();
			scringo.addSidebar();
			
			//Scringo.setDebugMode(true);
			// scringo.disableSwipeToOpenSidebar();
			
			Log.d("","button pressed");
		}catch(Exception e){
			Log.d("",""+e);
		}
		 
		//Scringo.setAppId("N36b3Zx7kbtqLaUrCtRcFn599hoLS3Vf");
		 helper=new IetDatabase(this).getWritableDatabase();//creating database instance
		queries(helper);
		//database working
		//notification work
		 
		
		 ActionBar actionBar = getActionBar();
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
			        | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
	     

		mTitle = mDrawerTitle = getTitle();

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		//mDrawer=(fragment) findViewById(R.id.action_back);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// institute
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// news and updates
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1),true, news_counter.toString()));
		// Academics
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		// Placementcell
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		// resources
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
		//results
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1), true,result_counter.toString()));
		//faq
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
		//contact us
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
		//chat
		//navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons.getResourceId(9, -1)));
		

		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		
		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				scringo.enableSwipeToOpenSidebar();
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				scringo.closeSidebar();
				scringo.disableSwipeToOpenSidebar();
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		 
		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	
		
	}

	

	public static void queries(SQLiteDatabase helper) {
	result_counter=0;
	news_counter=0;
	Log.d("","sdfesgeg"+Environment.getRootDirectory());
		  cursor1=helper.rawQuery("SELECT * FROM results_new",null);
		  cursor1.moveToFirst();
		 try{ do{
		  if(cursor1.getString(cursor1.getColumnIndex("newnews")).contains("true")){
			  result_counter++;
			  Log.d("",""+cursor1.getString(cursor1.getColumnIndex("newnews")));
		  }
		  }
		  while(cursor1.moveToNext());
		 }catch(Exception e){
			 
		 }
		 cursor1=helper.rawQuery("SELECT * FROM news_updates_new",null);
		 cursor1.moveToFirst();
		 try{
			 do{
				 if(cursor1.getString(cursor1.getColumnIndex("newnews")).contains("true")){
					 news_counter++;
				 }
			 }while(cursor1.moveToNext());
			 
		 }
			 catch(Exception e){
		 }
	}

	/**
	 * Slide menu item click listener
	 * */
	     private class SlideMenuClickListener implements
	     	ListView.OnItemClickListener {
	    	 	@Override
	    	 		public void onItemClick(AdapterView<?> parent, View view, int position,
	    	 					long id) {
	    	 		// display view for selected nav drawer item
	    	 			displayView(position);
	    	 	}
	     }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.refresh, menu);
		((ScringoActivationButton) findViewById(R.id.activationLeft)).setScringo(scringo);
		
		return super.onCreateOptionsMenu(menu);
		//return true;
	}

	

	private void refresh(MenuItem item) {
		boolean check=isNetworkAvailable();
		if(check){
			load(item);
		}
		else{
			dialog(item);
		}
	}
	
	private void dialog(final MenuItem item) {
		AlertDialog.Builder alert=new AlertDialog.Builder(this);
		alert.setTitle("No Internet");
		alert.setMessage("No Internet Connection Available.Do you want to try again?");
		alert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(isNetworkAvailable()){
					load(item);
				}
				else{
					dialog(item);
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



	private void load(MenuItem item) {
		menuItem = item;
	      menuItem.setActionView(R.layout.progressbar);
	      menuItem.expandActionView();
	      TestTask task = new TestTask(this,helper);
	      task.execute("test");
		
	}



	private boolean isNetworkAvailable() {
		ConnectivityManager cm=(ConnectivityManager)this.getSystemService(this.CONNECTIVITY_SERVICE);
		try{if(cm.getActiveNetworkInfo().isConnected()){
			
		
		return (true);
		}
		
		else
			return (false);
		}
         catch(Exception ex){
        	 ex.printStackTrace();
        	 return false;
		}
		
	}
	
	/***
	 * Called when invalidateOptionsMenu() is triggered
	 */

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	public void displayView(int position) {
		// update the main content by replacing fragments
		android.app.Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment(helper);
			break;
		case 1:
			fragment = new Institute(helper);
			break;
		case 2:
			fragment = new NewsAndUpdates(helper);
			break;
		case 3:
			fragment = new Academics(helper);
			break;
		case 4:
		fragment = new PlacementCell();
			break;
		case 5:
		fragment = new IETResources();
			break;
		case 6:
			fragment = new Result(helper);
			break;
		case 7:
		    fragment = new FAQ(helper);
			break;
		case 8:
			fragment = new ContactUs(helper);
			break;
		default:
			break;
		}

		if (fragment != null) {
			android.app.FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frame_container, fragment).commit();
			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		android.app.Fragment setting = null;
		android.app.FragmentManager fragmentManager = getFragmentManager();
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
	
	
		case R.id.action_back:
			//finish();
			
			 if (drawerOpen){
	                mDrawerLayout.closeDrawers();
			 }
			if(scringo.isSidebarOpen()){
				scringo.closeSidebar();
			}
			 if(checkFinish==true)
				 this.finish();
		     if (getFragmentManager().getBackStackEntryCount() == 1) {
		        // this.finish();
		    	 checkFinish=true;
		    	 Toast.makeText(getApplicationContext(),"Press Again To Exit",1).show();
		    	
		    	 new Handler().postDelayed(new Runnable(){
		    		 public void run(){
		    			 checkFinish=false;
		    		 }
		    	 },2000);
		     } else {
		         getFragmentManager().popBackStack();
		         Log.d("",""+getFragmentManager().getBackStackEntryCount()+Toast.LENGTH_SHORT);
		        
		     }
			break;
		case R.id.action_settings:
			 if (drawerOpen){
	                mDrawerLayout.closeDrawers();
			 }
			setting=new Settings();
			if(scringo.isSidebarOpen())
				scringo.closeSidebar();
			if(settings==0){
			fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.frame_container, setting).commit();
			settings=1;
			popback=false;
			}
			else{
				if(popback==false){
				fragmentManager.popBackStack();
				popback=true;
				settings=0;
				}
				else{
					popback=true;
					
				}
				Log.d("detach","detach");
				
				
			}
			
			break;
		case R.id.downloads: 
			 if (drawerOpen){
	                mDrawerLayout.closeDrawers();
			 }
			if(scringo.isSidebarOpen())
				scringo.closeSidebar();
			Intent intent1 = new Intent("Downloads");
        startActivity(intent1);
	/*		Bitmap bitmap=takeScreenshot();
			saveBitmap(bitmap);*/
			
        break;
		
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	 private void open_scringo() {
		 if(scringo.isSidebarOpen())
			{
			scringo.closeSidebar();
			
			}
			else{
			
						scringo.openSidebar();	
						//scringo.setActivationButton(findViewById(R.id.open_scringo));
//						String userId = Scringo.getUserId();
//						Scringo.openChat(this,userId);
						//Scringo.openLogin(this);
				Log.d("time1","time1");
				
			//Scringo.openMenu(this);
			}
		
	}



	private void saveBitmap(Bitmap bitmap) {
		// TODO Auto-generated method stub
		 
    	File imagePath=new File(Environment.getExternalStorageDirectory()+"/IET/"+"Download"+i+".png");
    	FileOutputStream fos;
    	try{
    		fos=new FileOutputStream(imagePath);
    		bitmap.compress(CompressFormat.JPEG,100, fos);
    		fos.flush();
    		fos.close();
    	}catch(FileNotFoundException e){
    		Log.e("", e.getMessage());
    	}catch(IOException e)
    	{
    		Log.e("",e.getMessage());
    	}
    	finally{
    	i++;
    	}
	}


	private Bitmap takeScreenshot() {
		getActionBar().hide();
		placement_web.zoomout();
		View rootView=findViewById(android.R.id.content).getRootView();
		rootView.setDrawingCacheEnabled(true);
		
		return rootView.getDrawingCache();
	}




	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	
	
	
	 @Override
	 public void onBackPressed() {
		 Log.d("",scringo.onBackPressed()+"");
		 
		 if (scringo.onBackPressed()) {
	            //super.onBackPressed();
	            Log.d("exe","exe");
	            return;
	           
	        }
		 if(settings==1){
			 settings=0;
		 }
		 if(checkFinish){
			 
			 super.onBackPressed();
			 result_counter=0;
			 news_counter=0;
			 this.finish();
			 return;
			 }
	     if (getFragmentManager().getBackStackEntryCount() == 1) {
	        // this.finish();
	    	 checkFinish=true;
	    	 Toast.makeText(getApplicationContext(),"Press Again To Exit",1).show();
	    	 new Handler().postDelayed(new Runnable(){
	    		 public void run(){
	    			 checkFinish=false;
	    		 }
	    	 },2000);
	    	
	     } else {
	         getFragmentManager().popBackStack();
	         Log.d("",""+getFragmentManager().getBackStackEntryCount()+Toast.LENGTH_SHORT);
	        
	     }
	 }
	
	
	public class TestTask extends AsyncTask<String, Void, String> {

	    private Context context;
		private SQLiteDatabase helper;

		public TestTask(Context applicationContext, SQLiteDatabase helper) {
			this.context=applicationContext;
			this.helper=helper;
		}

		@Override
	    protected String doInBackground(String... params) {
	      // Simulate something long running
	      try {
	      // new IETService().checkResult(context);
	      
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      return null;
	    }

	    @Override
	    protected void onPostExecute(String result) {
	      menuItem.collapseActionView();
	      menuItem.setActionView(null);
	     //new Result().refresh(helper,context);
	      
	        Log.d("","receiving");
	    }
	  }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	};
	  
	@Override
	public void onStart() {
		super.onStart();
		try{
			scringo.onStart();
		}catch(Exception e){
			
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		try{
			scringo.onStop();
		}catch(Exception e){
			
		}
	}
	
	
}

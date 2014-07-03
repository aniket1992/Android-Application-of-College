package com.example.iet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.result.Result;

public class HomeFragment extends Fragment {
	
	private SQLiteDatabase helper;
	private Cursor cursor,cursor1,cursor2,cursor3;
	private String[] HomeNewsA=new String[500];
	private String[] HomeNewsB=new String[500];
	
	private String HomeNews;

	private int counter=0,counter1=0;
	private ListView homelist;
	private ArrayList<String> rlist;
	private ArrayAdapter<String> listAdapter;
	private ImageView image;
	Timer timer;
	private TextView no_news;

	public HomeFragment(){}
	
	public HomeFragment(SQLiteDatabase helper) {
	
		this.helper=helper;
	}
	public void onStop(){
		super.onStop();
		Log.d("finished this fragment", "finsied fragments");
		timer.cancel();
		image.destroyDrawingCache();
		System.gc();
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        try{
        	final Activity ccs = this.getActivity();
        	 final int photoAry[] = { R.drawable.ones, R.drawable.twos, R.drawable.threes,
                     R.drawable.fours, R.drawable.fives, R.drawable.sixs,R.drawable.seves };
        	 timer = new Timer("TweetCollectorTimer");
        		image=(ImageView)rootView.findViewById(R.id.home_splash);
        		
             TimerTask updateTask = new TimerTask() {
            	 int i=0;
                 @Override
                 public void run() {

                	

                       
                	 ccs.runOnUiThread(new Runnable() {

                         @Override
                         public void run() {
                        	 try{
                        	 image.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out));
							image.setImageResource(photoAry[i]);
							
							image.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
                        	 }
                        	 catch(Exception e){
                        		 e.printStackTrace();
                        	 }
                             i++;
                             if (i > 6)
                             {
                                 i = 0;
                                                 
                         

                             }
                         }
                         });
                 }
             };
             timer.schedule(updateTask, 1L, 4000L);
	
		
		
	
		}catch(Exception e){
			e.printStackTrace();
		}
        //((ScringoActivationButton)rootView.findViewById(R.id.activationLeft)).setScringo(scringo);
        homelist=(ListView)rootView.findViewById(R.id.homelist);
        no_news=(TextView)rootView.findViewById(R.id.textView90);
        
       
                	cursor=helper.rawQuery("SELECT * FROM results_new",null);
        	cursor1 =helper.rawQuery("SELECT * FROM result",null);  
        	cursor2 =helper.rawQuery("SELECT * FROM news_updates",null);
        	 cursor3=helper.rawQuery("SELECT * FROM news_updates_new",null);
			cursor.moveToFirst();
			cursor1.moveToFirst();
			cursor2.moveToFirst();
			cursor3.moveToFirst();
			Log.d("1","1");
	        do{
	        	
	        	Log.d("2","2");
				try{
					HomeNews=cursor.getString(cursor.getColumnIndex("newnews"));
				}catch(Exception e){
					break;
				}
				 HomeNewsA[counter]=HomeNews;
				 counter++;
			}while(cursor.moveToNext());
	        do{
				try{
				HomeNews=cursor3.getString(cursor3.getColumnIndex("newnews"));
				}catch(Exception e){
					break;
				}
				 HomeNewsB[counter1]=HomeNews;
				 counter1++;
			}while(cursor3.moveToNext());
	        
	        String[] newsHomeA=new String[counter];
	        String[] newsHomeB=new String[counter1];
	        final String[] urlHomeA=new String[counter];
	       final  String[] urlHomeB=new String[counter1];
	       int j=0,k=0;
	        for(int i=0;i<counter;i++)
        	{
	        	try{
                	if(HomeNewsA[i].contains("true") ){
                		cursor1.moveToPosition(i);
                		newsHomeA[j]=cursor1.getString(cursor1.getColumnIndex("resultname"));
                		urlHomeA[j]=cursor1.getString(cursor1.getColumnIndex("url"));
                		
                		j++;
                		//Log.d("home",newsHomeA[i]);
                	}
                	else
                	{
                		//newsC[i]=resultname[i];
                	}
	        	}
                	catch(Exception e){
                		//newsC[i]=resultname[i];
                		Log.d("error1","error1");
                		break;
                	}
                	}
	        for(int i=0;i<counter1;i++)
        	{
	        	try{
                	if(HomeNewsB[i].contains("true") ){
                		cursor2.moveToPosition(i);
                		newsHomeB[k]=cursor2.getString(cursor2.getColumnIndex("news"));
                		urlHomeB[k]=cursor2.getString(cursor2.getColumnIndex("url"));
                		k++;
                		//Log.d("home",newsHomeA[i]);
                	}
                	else
                	{
                		//newsC[i]=resultname[i];
                	}
	        	}
                	catch(Exception e){
                		//newsC[i]=resultname[i];
                		Log.d("error","error");
                		break;
                	}
                	}
	        String[] newsHome=new String[j+k];
	       int l=0;
	        for	(int i=0;i<j;i++){
	        	newsHome[i]=newsHomeA[i];
	        }
	        l=j;
	        for	(int i=0;i<k;i++){
	        	newsHome[l]=newsHomeB[i];
	        	l++;
	        }
	 try{  	 rlist=new ArrayList<String>();
			
		 rlist.addAll(Arrays.asList(newsHome));
		
		 
		 listAdapter=new ArrayAdapter<String>(getActivity(),R.layout.simplerowhome,R.id.rowTextView1,rlist);
		 homelist.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
		
		 homelist.setAdapter(listAdapter);
		 if(rlist.isEmpty()){
			 homelist.setVisibility(View.GONE);
			 homelist.destroyDrawingCache();
			 
			 no_news.setVisibility(View.VISIBLE);
			 
		 }
		 final int j1=j,k1=k;
		 homelist.setOnItemClickListener(new OnItemClickListener(){
			 final String googleDocs="http://docs.google.com/viewer?url=";
			
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long id) {
					// TODO Auto-generated method stub
					
					if(position<j1){
					
					
					
					
					String link=urlHomeA[position];
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
					else{
						
							int index=position-j1;
							
						
						
						String link=urlHomeB[index];
						if(link.contains("http://ietlucknow.edu")||link.contains("http://www.ietlucknow.edu")){
							
							
						}
						else{
							link="http://ietlucknow.edu/"+link;
							
						}
						
						boolean check=isNetworkAvaialable();
						if(check){
						view(link);
						}
						else{
							dialog1(link);
							
						}
						
						Log.d("df", "fg"+googleDocs+link);
					}
						
					
						
					
				}

				private void dialog1(final String link) {
					AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
					alert.setTitle("No Internet");
					alert.setMessage("No Internet Connection Available.Do you want to try again?");
					alert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if(isNetworkAvaialable()){
								view(link);
							}
							else{
								dialog1(link);
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
	 }catch(Exception e){
		 System.err.print(e);
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
}


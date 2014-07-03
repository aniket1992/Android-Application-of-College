package service;

import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.iet.MainActivity;
import com.example.iet.Notificator;
import com.example.iet.R;

import database.IetDatabase;

public class PlacementNews extends Service {
	Thread placementThread;
	SQLiteDatabase helper,helper1;//new IetDatabase(getApplicationContext()).getReadableDatabase();
	private Cursor cursor;
	String[] heading=new String[500];
	String[] news=new String[500];
	String[] cheading=new String[500];
	String[] cnews=new String[500];
	static int randomValue1=345436;
	boolean delete=false;
	private Context context;
	
	
	@Override
    public void onCreate() {
	
      //  Toast.makeText(this, "The placement Service was Created", Toast.LENGTH_SHORT).show();
    }
	public void show(String message){
		 
    	NotificationCompat.Builder builder=new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_notification).
    			setContentTitle("Placement News").setContentText(message).setAutoCancel(true);
    	builder.setTicker(message);
    	
    	 builder.setDefaults(-1);
    	 //builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        //builder.setVibrate(new long[]{1000,1000});
    	Intent notificationIntents=new Intent(getApplicationContext(),Notificator.class);
    	notificationIntents.putExtra("notification","placementnews");
    	PendingIntent pIntent=PendingIntent.getActivity(getApplicationContext(),0,notificationIntents,1);
    	builder.setContentIntent(pIntent);
    	
        int NOTIFICATION_ID =randomValue1++;
         NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
         nManager.notify(NOTIFICATION_ID, builder.build());
         
    	
    }
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
	  super.onStartCommand(intent, flags, startId);
	  placementThread=new Thread(new Runnable(){

			@Override
			public void run() {
				try{
						 checkPlacement(getApplicationContext());
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}	      
	       });
	  
			placementThread.start();
			
	//onStartCommand(new Intent(),9,0);
      return Service.START_STICKY;
    }
	public void checkPlacement(Context context) throws Exception {
		helper1=new IetDatabase(context).getWritableDatabase();
		this.context=context;
		int count=0,counter=0;
		
		helper=new IetDatabase(context).getReadableDatabase();
		cursor =helper.rawQuery("SELECT * FROM placement_news",null);
        cursor.moveToFirst();
        do{
	         heading[count]=cursor.getString(cursor.getColumnIndex("heading"));   
	         news[count]=cursor.getString(cursor.getColumnIndex("news"));
	         Log.d("","1");
	       count=count+1;
	        }while(cursor.moveToNext());
        int l=0;
        while(l<count){
        	//Log.d("",heading[l]+""+news[l++]);
        	l++;
        }
    	final InputStream is=getClass().getClassLoader().getResourceAsStream("assets/placementcellnews/placementcell_news2.htm");
    	String url="http://www.ietplacementcell.net";
    	helper1.execSQL("drop table IF EXISTS placement_news_temp");
    	helper1.execSQL("drop table IF EXISTS placement_new_temp");
        	Log.d("","2");
    		//Document doc = Jsoup.parse(is,"UTF-8","www.ietlucknow.edu");
        	Document doc = Jsoup.connect(url).get();
        	Log.d("","3");
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
					
					counter++;
					Elements m1=mar.getElementsByTag("h4");
					Elements p1=mar.getElementsByTag("p");
					cheading[i]=m1.text().replaceAll("'", "");
					cnews[i]=p1.text().replaceAll("'", "");
					Log.d("","4"+cnews[i]+cheading[i]);
					Log.d(""+i,"");
					saveResult(cheading[i],cnews[i]);
					
					i++;
				}
				 }
          
    	
    	 boolean flag=false;
    	  boolean notifications_placement[]=new boolean[500];
          int ncount=0;
    	 for(int i1=1;i1<counter;i1=i1+2){
    		 for(int j=0;j<count;j++){
    			 if(heading[j].contains(cheading[i1])){
    				 if(news[j].contains(cnews[i1])){
    				 flag=true;
    				
    				 Log.d("",""+cnews[i1]);
    				 break;
    				 }
    				 else{
    						flag=false;
    						Log.d("found","found"+j+i1);
    						//saveResult(cheading[i1],cnews[i1]);
    						//notifications_placement[i1]=true;
    						delete=true;
    						Log.d("",cheading[i1]);	
    						
    					
    			  }
    			 }
    			 else{
    				 flag=false;
    			 }
    		 }
    		 if(flag==false){
 				System.out.println("found");
 				notifications_placement[i1]=true;
 				delete=true;
 				latest_news(true);
 				Log.d("",cheading[i1]);	
 				//saveResult(cheading[i1],cnews[i1]);
 				//show(cheading[i1]);
 				
 			}
    		 else{
 				latest_news(false);
 				}
    	 }
    		
			if(delete==true){
    		helper1.execSQL("drop table placement_news");
			helper1.execSQL("ALTER TABLE placement_news_temp RENAME TO placement_news");
			helper1.execSQL("drop table placement_new");
			helper1.execSQL("ALTER TABLE placement_new_temp RENAME TO placement_new");
			delete=false;
			}
			
			boolean flag1=true;
			for(int n=0;n<500;n++){
				
				if(notifications_placement[n]==true){
					if(flag1==true){
						//helper.execSQL("drop table iet_home2");	
						flag1=false;
					}
					//PostToHome(cheading[n]);
					show(cheading[n]);
					//Log.d("",""+cresult[i]+"check");
				}
				else{
				//	Log.d("","problem");
				}
				//Log.d("",notifications_result[i]+"");
				
			}
    	 
			helper.close();
			helper1.close();
 
    	 
         
	}
	private void latest_news(boolean b) {
		
		 String sql;
	        helper=new IetDatabase(context).getWritableDatabase();
	        helper.execSQL("CREATE TABLE IF NOT EXISTS placement_new_temp(newnews varchar(500))");
			  sql="insert into placement_new_temp values('"+b+"')";
			 
				//helper.execSQL("delete from news_updates");
				helper.execSQL(sql);
				helper.close();
	}
	
	private void saveResult(String po11,String p11) throws Exception{
		String sql;
		helper=new IetDatabase(context).getWritableDatabase();
		//helper.execSQL("delete from placement_news");
		helper.execSQL("CREATE TABLE IF NOT EXISTS placement_news_temp(heading varchar(500),news varchar(1000000))");
	 sql="insert into placement_news_temp values('"+po11+"','"+p11+"')";
	 
	 Log.d("","5"+po11+p11);
		helper.execSQL(sql);
		helper.close();
		
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public void onDestroy() {
       // Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}

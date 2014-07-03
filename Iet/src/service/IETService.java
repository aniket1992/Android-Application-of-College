package service;

import java.io.File;
import java.io.InputStream;
import java.util.Timer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.iet.MainActivity;
import com.example.iet.Notificator;
import com.example.iet.R;
import com.scringo.Scringo;
import com.scringo.Scringo.ScringoIcon;

import database.IetDatabase;

public class IETService extends Service{
private Timer timer;
boolean network=true;
NotificationCompat n;
Thread resultThread;
SQLiteDatabase helper,helper1;//=new IetDatabase(this).getWritableDatabase();
private Cursor cursor;
static int randomvalue=45324;
private String[] cresult=new String[500];
private String[] clink=new String[500];
private String[] link=new String[500];
boolean delete=false;

private Context context;
	@Override
    public void onCreate() {
	
      //  Toast.makeText(this, "The new Service was Created", Toast.LENGTH_LONG).show();
        
       
    }
    
	public void checkResult(Context context) {
		helper1=new IetDatabase(context).getWritableDatabase();
		this.context=context;
		int count=2,numbers=1;
		helper=new IetDatabase(context).getReadableDatabase();
		cursor =helper.rawQuery("SELECT * FROM result",null);
        cursor.moveToFirst();
        
        do{
	         link[count]=cursor.getString(cursor.getColumnIndex("url"));   
	       count=count+1;
	        }while(cursor.moveToNext());
        
		final String url="http://www.ietlucknow.edu/result.htm";
       // final String url="http://www.ietlucknow.site11.com/";
       final InputStream is=getClass().getClassLoader().getResourceAsStream("assets/result/result1.htm");
		
		Document doc;
		helper1.execSQL("drop table IF EXISTS result_temp");
		helper1.execSQL("drop table IF EXISTS results_new_temp");
		
		try {
			doc = Jsoup.connect(url).get();
			//doc = Jsoup.parse(is,"UTF-8","www.ietlucknow.edu");
			 Element e=doc.getElementById("inner_text_1");
				Elements table=e.getElementsByTag("a");
				int i=1,k=1;
				   for(Element p:table){
					i++;
					k++;
					   if(i==k)
					   { numbers++;
					   System.out.print(i+"=");
						   clink[i]=p.attr("href");
						   cresult[i]=p.text().replaceAll("'", "");
						   System.out.println("..aaaaaaaaaaaaaaaaaaaa"+clink[i]);
						   saveResult(cresult[i],clink[i]);
						   
						   
					   }
				   else{
					   
				   }
				   }
				   
		}
		 catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		boolean flag=false;
           boolean notifications_result[]=new boolean[500];
          
           int ncount=0;
			for(int i1=2;i1<=numbers;i1++){
				for(int j=2;j<count;j++){
				if(link[j].contains(clink[i1])){
					flag=true;
					System.out.println(link[j]+"no matches");
					
					break;
				}
				else{
					flag=false;
				}
			}
			if(flag==false){
				System.out.println("found");
				//saveResult(cresult[i1],clink[i1]);
				//show(cresult[i1]);
				notifications_result[i1]=true;
				latest_news(true);
				ncount=i1;
				delete=true;
			}
			else{
				System.out.println("not found");
				latest_news(false);
				}
		}
			
			
			if(delete==true){
				helper1.execSQL("drop table result");	
				helper1.execSQL("drop table results_new");
				helper1.execSQL("ALTER TABLE result_temp RENAME TO result");
				helper1.execSQL("ALTER TABLE results_new_temp RENAME TO results_new");
				delete=false;
				
			}
			
			boolean flag1=true;
			
			for(int i=0;i<500;i++){
				
				if(notifications_result[i]==true){
					if(flag1==true){
						//helper.execSQL("drop table iet_home4");	
						flag1=false;
					}
					//PostToHome(cresult[i]);
					show(cresult[i]);
					Log.d("",""+cresult[i]+"check");
				}
				else{
				//	Log.d("","problem");
				}
				//Log.d("",notifications_result[i]+"");
				
			}
			//onCreate();
			helper.close();
			helper1.close();
			
	}
	private void latest_news(boolean b) {
		
		 String sql;
	        helper=new IetDatabase(context).getWritableDatabase();
	        helper.execSQL("CREATE TABLE IF NOT EXISTS results_new_temp(newnews varchar(500))");
			  sql="insert into results_new_temp values('"+b+"')";
			
				//helper.execSQL("delete from news_updates");
				helper.execSQL(sql);
				helper.close();
	}
	

	private void saveResult(String clink1, String clink2) {
    	String sql;
    	helper=new IetDatabase(context).getWritableDatabase();
    	helper.execSQL("CREATE TABLE IF NOT EXISTS result_temp(resultname varchar(1000000),url varchar(500))");
    	sql="insert into result_temp values('"+clink1+"','"+clink2+"')";
    	
	      helper.execSQL(sql);
		  helper.close();
    	Log.d("",""+clink1);
	}

	public void show(String message){
 
    	NotificationCompat.Builder builder=new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_notification).
    			setContentTitle("Result").setContentText(message).setAutoCancel(true);
    	builder.setTicker(message);
    	
        builder.setDefaults(-1);
      // builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
       
     // builder.setVibrate(new long[]{1000,1000});
    	Intent notificationIntent=new Intent(context,Notificator.class);
    	notificationIntent.putExtra("notification","result");
    	PendingIntent pIntent=PendingIntent.getActivity(context, 2, notificationIntent,3);
    	builder.setContentIntent(pIntent);
         int NOTIFICATION_ID = randomvalue++;
         NotificationManager nManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
         nManager.notify(NOTIFICATION_ID, builder.build());
         
    	
    }
  

    @Override
    public void onDestroy() {
     //   Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        
        

    }
  @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
	  super.onStartCommand(intent, flags, startId);
	  
	  try{
	  resultThread=new Thread(new Runnable(){

			

			@Override
			public void run() {
						 checkResult(getApplicationContext());
					   Log.d("","service finished its working");
					  while(isNetworkAvailable()){
					   startAgain();
					   try {
						resultThread.sleep(300000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  }
					    	 
				
		
			}

			private boolean isNetworkAvailable() {
		
		ConnectivityManager cm=(ConnectivityManager) getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
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

			private void startAgain() {
				
				 checkResult(getApplicationContext());
			}
	    	    
	    	  
	       });
	  
			resultThread.start();
	  }catch(Exception e){
		  System.out.print(e);
	  }
	 //scringo= new Scringo((Activity) getApplicationContext());
      //scringo.setIcon(ScringoIcon.PERSON);
   
    // scringo.init();
  	 //Log.d("time","time");
     /// Scringo.setAppId("N36b3Zx7kbtqLaUrCtRcFn599hoLS3Vf");
     // scringo.addSidebar();
	//onStartCommand(new Intent(),9,0);
      return Service.START_STICKY;
    }
  
  
  
@Override
public IBinder onBind(Intent intent) {
	// TODO Auto-generated method stub
	return null;
}
	

}

package service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

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
import android.net.ConnectivityManager;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.iet.MainActivity;
import com.example.iet.Notificator;
import com.example.iet.R;

import database.IetDatabase;

public class NewsService extends Service {
	private static String[] link=new String[500];
	private static String[] clink=new String[500];
	private static String[] cnews=new String[500];
	
	Thread newsThread;
	SQLiteDatabase helper,helper1;//new IetDatabase(getApplicationContext()).getReadableDatabase();
	private Cursor cursor;
	static int randomValue2=4346672;
	boolean delete=false;
	private Context context;
	private boolean newnews=false;
	@Override
    public void onCreate() {
	
       // Toast.makeText(this, "The news Service was Created", Toast.LENGTH_LONG).show();
        
       
    }
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
	  super.onStartCommand(intent, flags, startId);
	  newsThread=new Thread(new Runnable(){

			@Override
			public void run() {
				try{
						 checkNews(getApplicationContext());
						 while(isNetworkAvailable()){
							   startAgain();
							   try {
								newsThread.sleep(300000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							  }
				}catch(Exception e)
				{
					System.err.print(e);
				}
			}

			private void startAgain() {
				 checkNews(getApplicationContext());
				
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
	       });
	  
			newsThread.start();
      return Service.START_STICKY;
    }
  

	public  void checkNews(Context context) {
		this.context=context;
		helper1=new IetDatabase(context).getWritableDatabase();
		int count=0,numbers=1,counter=0;
		helper=new IetDatabase(context).getReadableDatabase();
		cursor =helper.rawQuery("SELECT * FROM news_updates",null);
        cursor.moveToFirst();
        
        do{
	         link[count]=cursor.getString(cursor.getColumnIndex("url"));   
	       count=count+1;
	        }while(cursor.moveToNext());
	
 //fetching page
        try {
   		URL url = new URL("http://www.ietlucknow.edu/news1.js");
        	//URL url = new URL("http://www.ietlucknow.site11.com/news1.js");
   		 URLConnection ucon= url.openConnection();
   		 InputStream ise = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(ise);
            OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory()+"/news1.js");
            byte data[] = new byte[1024];

			int length = 0;

				while ((length = bis.read(data)) != -1) {
					
					output.write(data, 0, length);
				}

				output.flush();
				output.close();
				bis.close();
		   
	 }	  
      catch (Exception e) {
			
			e.printStackTrace();
		}
        
        
        
        final File is=new File(Environment.getExternalStorageDirectory()+"/news1.js");
        helper1.execSQL("drop table IF EXISTS news_updates_temp");
        helper1.execSQL("drop table IF EXISTS news_updates_new_temp");
		 try {
			 Document doc = Jsoup.parse(is,"UTF-8","www.ietlucknow.edu");
			 Elements table=doc.getElementsByTag("a");
			// Elements img=doc.getElementsByTag("img");
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
						   
						   
						
					   
				   clink[counter]=p.attr("href");
				   cnews[counter]=p.text().replaceAll("'", "");
				   saveNews(cnews[counter],clink[counter],newnews);
				   counter++;
				  
				  }
			   }
	 
         
		 }
			catch (Exception e) {
				
				e.printStackTrace();
			}
		 for( int p=0;p<counter;p++){
				System.out.println(clink[p]+cnews[p]+counter); 
			 }
		 boolean flag=false;
		 boolean notifications_news[]=new boolean[500];
			for(int i1=0;i1<counter;i1++){
				for(int j=0;j<count;j++){
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
				System.out.println("found"+cnews[i1]);
				notifications_news[i1]=true;
				delete=true;
				latest_news(true);
				
				
			}
			else{
			latest_news(false);
			}
		 
			}
			
			
			if(delete==true){
			helper1.execSQL("drop table news_updates");
			helper1.execSQL("drop table news_updates_new");
			helper1.execSQL("ALTER TABLE news_updates_temp RENAME TO news_updates");
			helper1.execSQL("ALTER TABLE news_updates_new_temp RENAME TO news_updates_new");
			delete=false;
			}
			boolean flag1=true;
			for(int i=0;i<500;i++){
				
				if(notifications_news[i]==true){
					if(flag1==true){
						//helper.execSQL("drop table iet_home1");	
						flag1=false;
					}
					//PostToHome(cnews[i]);
					show(cnews[i]);
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
	        helper.execSQL("CREATE TABLE IF NOT EXISTS news_updates_new_temp(newnews varchar(500))");
			  sql="insert into news_updates_new_temp values('"+b+"')";
			  Log.d("updates", ""+b);
				//helper.execSQL("delete from news_updates");
				helper.execSQL(sql);
				helper.close();
	}
	
	private void saveNews(String p, String newnews,boolean newnews1) {
        String sql;
        helper=new IetDatabase(context).getWritableDatabase();
        helper.execSQL("CREATE TABLE IF NOT EXISTS news_updates_temp(news varchar(1000000),url varchar(500),newnews varchar(500))");
		  sql="insert into news_updates_temp values('"+p+"','"+newnews+"','"+newnews1+"')";
		 
			//helper.execSQL("delete from news_updates");
			helper.execSQL(sql);
			helper.close();
		
	}
	public void show(String message){
		 
    	NotificationCompat.Builder builder=new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_notification).
    			setContentTitle("News and Updates").setContentText(message).setAutoCancel(true);
    	builder.setTicker(message);
    	
    	 builder.setDefaults(-1);
    	 //builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        //builder.setVibrate(new long[]{1000,1000});
    	Intent notificationIntent=new Intent(context,Notificator.class);
    	notificationIntent.putExtra("notification","newsupdates");
    	PendingIntent pIntent=PendingIntent.getActivity(context, 4, notificationIntent,5);
    	builder.setContentIntent(pIntent);
         int NOTIFICATION_ID = randomValue2++;
         NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
         nManager.notify(NOTIFICATION_ID, builder.build());
         
    	
    }
	
	
	@Override
    public void onDestroy() {
      //  Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        
        

    }
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}

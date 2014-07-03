package introduction;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.iet.R;

import database.IetDatabase;

import adapter.Settings;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class HowTouse extends FragmentActivity implements OnPageChangeListener{
	ViewPager viewPager;
	public static int progress=0;
	private PagerAdapter mPagerAdapter;
	private ImageView imageview;
	//private  SQLiteDatabase helper=new IetDatabase(this).getWritableDatabase();
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
	}
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howtouse);
        viewPager = (ViewPager) findViewById(R.id.pager);
        Log.d("df", "yup");
        List <Fragment> fragments =new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, FirstSlide.class.getName()));
        fragments.add(Fragment.instantiate(this, SecondSlide.class.getName()));
        fragments.add(Fragment.instantiate(this, ThirdSlide.class.getName()));
        fragments.add(Fragment.instantiate(this, FourthSlide.class.getName()));
        
        mPagerAdapter=new PagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setOnPageChangeListener(this);
         if(Settings.tour==false){
        	 
         }else
        prepare();  //most important method
        
	}
	public void prepare() {
		final SQLiteDatabase helper=new IetDatabase(HowTouse.this).getWritableDatabase();
		Thread result_thread=new Thread(new Runnable(){
			

			public void run(){
			prepareResult();
			}
	

			private void prepareResult() {
				 final InputStream is=getClass().getClassLoader().getResourceAsStream("assets/result/result.htm");
					
					
					try {
						 String chars1,chars2;
						 String sql,link;
						
						 Document doc = Jsoup.parse(is,"UTF-8","www.ietlucknow.edu");
					//	Document doc=Jsoup.connect(url).get();
						 Element e=doc.getElementById("inner_text_1");
							Elements table=e.getElementsByTag("a");
						int i=1,k=1;
						   for(Element p:table){
							i++;
							k++;
							   if(i==k)
							   { 
								   System.out.print(p.text()+"  ");
								   link=p.attr("href");
								   sql="insert into result values('"+p.text().replaceAll("'", "")+"','"+link+"')";
							       helper.execSQL(sql);
							   }
						   else{
							   
						   }
						   }
							
				
					
			}catch(Exception e){
				e.printStackTrace();
			}
					finally{
						helper.close();
					}
			}	
		});
		Thread NewsThread=new Thread(new Runnable(){
			public void run(){
				prepareNewsUpdates();
			}
			

			private void prepareNewsUpdates() {
				final String url="";
				final InputStream is=getClass().getClassLoader().getResourceAsStream("assets/newsupdates/news.js");
				//final String url="http://ietlucknow.edu/institute.htm";
			boolean newnews=false;
			 String sql,link="";
		 
			 String chars1;
			String chars2;
			try {
			//Document doc=Jsoup.connect(url).get();
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
						   
						   
//						   for(Element o:img){
//							   if(o.previousElementSibling().text().equals(p.text())){
//								   
//								 newnews=true;
//								   break;
//							   }
//							   else{
//								   newnews=false;
//								   
//							   }
//							   
//						   }
//					   
					   System.err.print(p.text()+"  "+newnews);
					   link=p.attr("href");
					  // System.out.println(link);
					   sql="insert into news_updates values('"+p.text()+"','"+link+"','"+newnews+"')";
				       helper.execSQL(sql);
					   
					   }
					   
						  
				  
				   }
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
					finally{
						helper.close();
					}
			}	
		})	;

		Thread syllabus=new Thread(new Runnable(){
			public void run(){
				add_syllabus();
			}

			private void add_syllabus() {
				 final InputStream is=getClass().getClassLoader().getResourceAsStream("assets/syllabus/syllabus.htm");
				 final SQLiteDatabase helper1=new IetDatabase(HowTouse.this).getWritableDatabase();
					//StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
					//StrictMode.setThreadPolicy(policy);
					String[] clink=new String[500];
					try {
						String url="http://www.uptu.ac.in/academics/syllabus_archive.htm";
						//doc = Jsoup.connect(url).get();
						Document doc = Jsoup.parse(is,"UTF-8","www.uptu.ac.in");
						 Element e=doc.getElementById("content_area_inner");
						String sql1,link1;
							Elements table=e.getElementsByTag("a");
							int i=1,k=1;
							
							   for(Element p1:table){
								
								   //System.out.print(i);
								   link1=p1.attr("href");
									   sql1="insert into syllabus values('"+p1.text()+"','"+link1+"')";
								      helper1.execSQL(sql1);
									   System.err.println(p1.text()+p1.attr("href"));
									  
									  
									   
									   
								   
							   }
							   
						
					}
					 catch (Exception e1) {
							// TODO Auto-generated catch block
						e1.printStackTrace();
						}
				
			}
			
		});
		
		result_thread.start();
		NewsThread.start();
		syllabus.start();
		//placementNews.start();
		//placementEvents.start();
		 Log.d("", "yoyoyo");
		 
		
		
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	
	}
	@Override
	public void onPageSelected(int arg0) {
		
		
		    	 
	}

}
class PagerAdapter extends FragmentPagerAdapter{
	private List<Fragment> fragments;
	public PagerAdapter(FragmentManager fm,List<Fragment> fragments){
	super(fm);
	this.fragments=fragments;
	}
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
	
		return this.fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return this.fragments.size();
	}
	
}
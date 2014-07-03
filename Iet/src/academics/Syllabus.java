package academics;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.iet.R;
import com.example.result.Result;

import database.IetDatabase;

public class Syllabus extends Fragment {
	private ListView syllabuslist;
	private String[] clink=new String[500];
	private int counter=0;

	private String[] csyllabus;
	
	//private 
	private Cursor cursor;
	private String chars1;
	private String syllabusurl;
	private String[] sUrlA;
	private int count=0;
	private String[] syllabusname=new String[1000];
	private ArrayAdapter<String> listAdapter;
	private ArrayList<String> rlist;
	private SQLiteDatabase helper1;
	Document doc;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		final SQLiteDatabase helper=new IetDatabase(this.getActivity()).getReadableDatabase();;
        final View rootView = inflater.inflate(R.layout.syllabus, container, false);
        syllabuslist =(ListView)rootView.findViewById(R.id.syllabuslist);
		       
       
			
		
      try {
			 String chars1,chars2;
			 String sql,link;
			 String newNews;
			
				cursor =helper.rawQuery("SELECT * FROM syllabus",null);
			       
			    	   cursor.moveToFirst();
			       
			        
			        do{
			         chars1=cursor.getString(cursor.getColumnIndex("Syllabus"));
                  //chars2=cursor.getString(cursor.getColumnIndex("url"));
			        //System.out.println(chars1+"  "+chars2);
			       syllabusname[counter]=chars1;
			         counter++;
			        // System.out.print(resultname);
			        }while(cursor.moveToNext());
			      
					
			       
			} catch (Exception e) {
				
				e.printStackTrace();
			}	
	



		
          
       String[] newsC=new String[counter];
       for(int i=0;i<counter;i++)
       	newsC[i]=syllabusname[i];
		 rlist=new ArrayList<String>();
		 rlist.addAll(Arrays.asList(newsC));
		 listAdapter=new ArrayAdapter<String>(getActivity(),R.layout.simplerowh,R.id.rowTextView,rlist);
		 syllabuslist.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
          // Getting a reference to listview of main.xml layout file
         
   
          // Setting the adapter to the listView
		 syllabuslist.setAdapter(listAdapter);
		 syllabuslist.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
		 syllabuslist.setOnItemClickListener(new OnItemClickListener(){
				 
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long id) {
					// TODO Auto-generated method stub
					
					cursor.moveToPosition(position);
					String link=cursor.getString(cursor.getColumnIndex("url"));
					if(link.contains("http://uptu.ac.in/academics/")||link.contains("http://www.uptu.ac.in/academics/")){
						
					}
					else{
						link="http://uptu.ac.in/academics/"+link;
						
					}
					Log.d("df", "fg"+link);
					boolean check=isNetworkAvaialable();
					if(check){
					view(link);
					}
					else{
						dialog(link);
						
					}
				}
				private void view(String link) {
							
							
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
									
									 final String googleDocs="http://docs.google.com/viewer?url=";

									@Override
									public void onClick(DialogInterface dialog, int which) {
										
										Intent intent1 = new Intent("placement");
									    intent1.putExtra("url",Uri.parse(googleDocs+linknew).toString());
									    
										 startActivity(intent1);
										
									}
								});
								alert.show();
								
								
														}
				private boolean isNetworkAvaialable() {
					ConnectivityManager  cm=(ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
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

	

				public void dialog(final String link) {
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
				

				
				
		});

				helper.close();
        return rootView;
		
		
}

	
}
package com.example.iet;

import com.example.newsupdates.NewsAndUpdates;
import com.example.placement.News;
import com.example.result.Result;

import database.IetDatabase;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Notificator extends Activity {
	private SQLiteDatabase helper;
	public static boolean touch_notification=true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 helper=new IetDatabase(this).getWritableDatabase();
		android.app.Fragment fragment=null;
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		String fragment_open=intent.getStringExtra("notification");
		touch_notification=false;
	//	Toast.makeText(getApplicationContext(), fragment_open, Toast.LENGTH_SHORT).show();
		android.app.FragmentManager fragmentManager = getFragmentManager();
		if(fragment_open.contains("result")){
			fragment=new Result(helper);
			
			Log.d("", ""+fragment_open);
	}
		else if(fragment_open.contains("placementnews")){
			fragment=new News();
			Log.d("","news");
		}
		else if(fragment_open.contains("newsupdates")){
			fragment=new NewsAndUpdates(helper);
		}
		fragmentManager.beginTransaction().add(android.R.id.content,fragment).commit();
		
	}
	@Override
	 public void onBackPressed() {
		super.onBackPressed();
		touch_notification=true;
		finish();
	}

}

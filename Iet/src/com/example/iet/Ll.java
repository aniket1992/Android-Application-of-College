package com.example.iet;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Ll extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ll, menu);
		return true;
	}

}

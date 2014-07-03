package com.example.iet;

import com.scringo.utils.ScringoLogger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub if (intent.getAction().equals("com.scringo.LoginBroadcast")) {
        String userId = intent.getExtras().getString("userId");
        String accountId = intent.getExtras().getString("accountId");
        ScringoLogger.e("Got receiver: " + accountId + ", " + userId);      
    }
		
	
}
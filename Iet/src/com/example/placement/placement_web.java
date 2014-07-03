package com.example.placement;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.iet.R;

import adapter.Settings;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.widget.Toast;

public class placement_web extends Activity {
	String url1;
	String url2;
	

    private WebView webView;
	private String url;
	private Object progressDialog;
	public static WebView temp_web;
	private static int i=1;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.webview, menu);
		return super.onCreateOptionsMenu(menu);
		//return true;
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		if(url.contains(".html")|| url.contains(".html")|| url.contains(".aspx") || url.contains(".php"))
		menu.findItem(R.id.save).setVisible(true);
		else
			menu.findItem(R.id.save).setVisible(false);
		return super.onPrepareOptionsMenu(menu);
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		
		case R.id.save:
			Bitmap bitmap=takeScreenshot();
			saveBitmap(bitmap);
			Toast.makeText(getApplication(),"Successfully saved in downloads directory",Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.cancel:webView.stopLoading();
			break;
		case R.id.exit:finish();
		break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
		
	}
    private void saveBitmap(Bitmap bitmap) {
		// TODO Auto-generated method stub
    	File sdcard=new File(Environment.getExternalStorageDirectory()+"/IET");
    	sdcard.mkdir();
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
    	}finally{
    		i++;
    		
    	}
	}


	private Bitmap takeScreenshot() {
		
		View rootView=findViewById(android.R.id.content).getRootView();
		
		
		//webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		webView.zoomOut();
		webView.zoomOut();
		webView.zoomOut();
		webView.zoomOut();
		webView.zoomOut();
		webView.zoomOut();
		webView.zoomOut();
		webView.zoomOut();
		webView.zoomOut();
		webView.zoomOut();
		webView.zoomOut();
		
		//webView.setInitialScale(1);
		//webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		//webView.getSettings().setUseWideViewPort(true);
		//webView.getSettings().setLoadWithOverviewMode(true);   
		rootView.setDrawingCacheEnabled(true);
		
		return rootView.getDrawingCache();
	}


	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        url=intent.getStringExtra("url"); 
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        
        setContentView(R.layout.placement_web);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS,Window.PROGRESS_VISIBILITY_ON);
        
     
        webView = (WebView) findViewById(R.id.placement_web);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginsEnabled(false);
        webView.getSettings().setAllowFileAccess(true);
        webView.zoomOut();
        temp_web=webView;
       // webView.setPadding(0, 0, 0, 0);
       //webView.setInitialScale(0);
       webView.getSettings().setLoadWithOverviewMode(true);
    
        webView.getSettings().setLoadsImagesAutomatically(true);
       webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(true);
        //webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //webView.getSettings().setAppCacheMaxSize(1024*1024*8);
        webView.getSettings().setAllowFileAccess(true);
        //webView.getSettings().setAppCacheEnabled(true);
        
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        
        //progressDialog = ProgressDialog.show(placement_web.this, "", "Loading...\nDepends upon your Internet Connection and IET server speed");
        final Activity myActivity=this;
        webView.setWebChromeClient(new WebChromeClient(){
        	public void onProgressChanged(WebView view,int progress){
        		myActivity.setTitle("Loading...");
        		myActivity.setProgress(progress*100);
        	
        		
        		if(progress==100){
        			myActivity.setTitle(R.string.app_name);
        			
        		}
        		if(progress==70){
        		//	((Dialog) progressDialog).dismiss();	
        		}
        	}
        	
        });
        
        webView.setWebViewClient(new WebViewClient(){
        	public void onReceivedError(WebView view,int errorcode,String description,String failingurl){
        		
        		Toast.makeText(getApplicationContext(), ""+description,Toast.LENGTH_SHORT).show();
        		finish();
        	}
        	
        });
        

		
	}
	public static void zoomout(){
		temp_web.zoomOut();
		Log.d("zooming","zooming");
	}
	private int getScale() {
	Display display=((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
	int width=display.getWidth();
	Double val =new Double(width)/new Double(100);
	val=val*100d;
	
		return val.intValue();
	}


	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		webView.saveState(outState);
	}
	protected void onRestoreInstanceState(Bundle outState){
		super.onRestoreInstanceState(outState);
		webView.restoreState(outState);
	}
    public void onBackPressed(){
    	if(webView.canGoBack()){
    		webView.goBack();
    	}
    	else{
    		super.onBackPressed();
    	}
    }
    
    public File getCacheDir(){
		return getApplicationContext().getCacheDir();
    	
    }
	
	

}

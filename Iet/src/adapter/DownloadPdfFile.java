package adapter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.example.iet.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class DownloadPdfFile extends Activity {
   
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private Button startBtn;
    String url;
    int l;
    String filename;
    private ProgressDialog mProgressDialog;
   
    /** Called when the activity is first created. */
    @Override
	 public void onBackPressed() {
    	this.finish();
    	
    	
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.download_progress);
       
        Intent intent=getIntent();
        url=intent.getStringExtra("url"); 
        l=url.lastIndexOf("/");
        filename=url.substring(l);
        try{
               startDownload();
        }catch(Exception e){
        	e.printStackTrace();
        }
          
    }

    public void openpdf(String filename) {
    	
    	
    	File file = new File(Environment.getExternalStorageDirectory()+"/IET"+filename);
        PackageManager packageManager =this.getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        //List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        startActivity(intent);
        this.finish();
		
	}

	private void startDownload() {
        
        new DownloadFileAsync(filename).execute(url);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
		case DIALOG_DOWNLOAD_PROGRESS:
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage("Downloading file..");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(true);
			mProgressDialog.setCanceledOnTouchOutside(false);
			mProgressDialog.show();
			
			
			
			return mProgressDialog;
		default:
			return null;
        }
    }

class DownloadFileAsync extends AsyncTask<String, String, String> {
   
	private String filename;

	public DownloadFileAsync(String filename) {
	this.filename=filename;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		try{
		showDialog(DIALOG_DOWNLOAD_PROGRESS);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected String doInBackground(String... aurl) {
		int count;

	try {

	URL url = new URL(aurl[0]);
	URLConnection conexion = url.openConnection();
	conexion.connect();

	int lenghtOfFile = conexion.getContentLength();
	Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

	InputStream input = new BufferedInputStream(url.openStream());
	File sdcard=new File(Environment.getExternalStorageDirectory()+"/IET");
	sdcard.mkdir();
	
	OutputStream output = new FileOutputStream(sdcard+filename);

	byte data[] = new byte[1024];

	long total = 0;

		while ((count = input.read(data)) != -1) {
			total += count;
			publishProgress(""+(int)((total*100)/lenghtOfFile));
			output.write(data, 0, count);
		}

		output.flush();
		output.close();
		input.close();
	} catch (Exception e) {}
	return null;

	}
	protected void onProgressUpdate(String... progress) {
		 Log.d("ANDRO_ASYNC",progress[0]);
		 try{
		 mProgressDialog.setProgress(Integer.parseInt(progress[0]));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	} 
	
	

	@Override
	protected void onPostExecute(String unused) {
		Toast.makeText(DownloadPdfFile.this,"Downloaded in:"+Environment.getExternalStorageDirectory()+"/IET",Toast.LENGTH_LONG);
//	try {
//		this.wait(100);
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		try{
		dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
		openpdf(filename);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
}
}
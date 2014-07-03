package adapter;

import java.util.List;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

public class Download extends Activity{
	public void downloadFile() {
		  String DownloadUrl = "http://ietlucknow.edu/tenders/tender_21022014.pdf";
		  Log.d("","aa1");
		    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DownloadUrl));
		    Log.d("","aa");
		    request.setDescription("sample pdf file for testing");   //appears the same in Notification bar while downloading
		    request.setTitle("Sample.pdf");    
		  
		    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		        request.allowScanningByMediaScanner();
		        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		        Log.d("","a2");
		    }
		    request.setDestinationInExternalFilesDir(this.getApplicationContext(),null, "sample.pdf");
		 
		    // get download service and enqueue file
		    DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
		    Log.d("","aa5");
		    manager.enqueue(request);
	
		
	}

	public  Boolean isDownloadManagerAvailabl(Context context) {
		 try {
		        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
		            return false;
		        }
		        Intent intent = new Intent(Intent.ACTION_MAIN);
		        intent.addCategory(Intent.CATEGORY_LAUNCHER);
		        intent.setClassName("com.android.providers.downloads.ui","com.android.providers.downloads.ui.DownloadList");
		        List <ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
		                PackageManager.MATCH_DEFAULT_ONLY);
		        return list.size() > 0;
		    } catch (Exception e) {
		        return false;
		    }
	
	}

}

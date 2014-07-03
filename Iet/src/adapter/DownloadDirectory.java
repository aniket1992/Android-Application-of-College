package adapter;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.iet.R;

public class DownloadDirectory extends ListActivity{
	private File currentDir;
    private FileArrayAdapter adapter;
    List<Item>fls ;
    int i=0,count=0,p=0;
    boolean flag=true;
    Intent intent;
    private ShareActionProvider mshareNew;
    ArrayList<Uri>names =new ArrayList<Uri>();
    boolean delete[]=new boolean[300];
    
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        currentDir = new File(Environment.getExternalStorageDirectory()+"/IET");
        fill(currentDir); 
    }
    private void fill(File f)
    {
    	File[]dirs = f.listFiles(); 
		 this.setTitle("Current Dir: "+f.getName());
		 List<Item>dir = new ArrayList<Item>();
		 List<Item>fls = new ArrayList<Item>();
		 try{
			 for(File ff: dirs)
			 { 
				Date lastModDate = new Date(ff.lastModified()); 
				DateFormat formater = DateFormat.getDateTimeInstance();
				String date_modify = formater.format(lastModDate);
				
				if(ff.isDirectory()){
					
					
					File[] fbuf = ff.listFiles(); 
					int buf = 0;
					
					if(fbuf != null){ 
						buf = fbuf.length;
						
					} 
					else buf = 0; 
					String num_item = String.valueOf(buf);
					if(buf == 0) num_item = num_item + " item";
				
					else num_item = num_item + " items";
					
					//String formated = lastModDate.toString();
					dir.add(new Item(ff.getName(),num_item,date_modify,ff.getAbsolutePath(),"directory_icon")); 
				}
				else
				{
					
					fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"file_icon"));
				}
			 }
		 }catch(Exception e)
		 {    
			 
		 }
		 Collections.sort(dir);
		 Collections.sort(fls);
		 dir.addAll(fls);
		 
		/* if(!f.getName().equalsIgnoreCase("sdcard"))
			 dir.add(0,new Item("..","Parent Directory","",f.getParent(),"directory_up"));*/
		 adapter = new FileArrayAdapter(DownloadDirectory.this,R.layout.file_view,dir);
		 this.setListAdapter(adapter); 
		 getListView().setBackgroundColor(Color.WHITE);
		 this.registerForContextMenu(getListView());
		 getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		
		 getListView().setMultiChoiceModeListener(new MultiChoiceModeListener(){

			private int check;
			

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				
				switch(item.getItemId()){
				case R.id.delete:
					
					
					for(int i=delete.length-1;i>=0;i--){
						if(delete[i]==true){
							Log.d("",""+i+adapter.getItem(i).getName());
							new File(Environment.getExternalStorageDirectory()+"/IET/"+adapter.getItem(i).getName()).delete();
					adapter.remove(adapter.getItem(i));
					
					
					//delete[i]=false;
					}}
					//Log.d("",""+adapter.currentSelection);
					Toast.makeText(getApplication(),"Successfully Deleted", Toast.LENGTH_SHORT).show();
					mode.finish();
					
					break;
			
					default:{
						Log.d("","called");
						return false;
					}
				}
				return false;
			}

			
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				MenuInflater inflater=mode.getMenuInflater();
				inflater.inflate(R.menu.contextual, menu);
			ShareActionProvider mshare = (ShareActionProvider) menu.findItem(R.id.share).getActionProvider();
			mshare.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
			intent=new Intent(Intent.ACTION_SEND_MULTIPLE);
			intent.setType("text/plain");
			mshareNew=mshare;
			//mshare.setShareIntent(intent);
			Log.d("","only once");
			
				return true;
			}
		

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				// TODO Auto-generated method stub
				//getListView().setBackgroundResource(R.drawable.list_item_bg_pressed);
				//mode.finish();
                  for(int i=delete.length-1;i>=0;i--){
					
					if(delete[i]==true){
						delete[i]=false;
						getListView().getChildAt(i).setBackgroundResource(android.R.color.white);
						
					}
                  }
				Log.d("", "ll");
			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				
				//Log.d("cc","cc");
				return false;
			}

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {
				// TODO Auto-generated method stub
				
				Log.d("",""+delete[position]+position);
				delete[position]=true;
				
				Log.d("",""+	getListView().getChildAt(position).isActivated());
				if(getListView().getChildAt(position).isActivated()){
					getListView().getChildAt(position).setBackgroundResource(android.R.color.white);
					delete[position]=false;
					
					
				}
				else{
			
					getListView().getChildAt(position).setBackgroundResource(android.R.color.holo_blue_light);
					delete[position]=true;
				//	getListView().getChildAt(position).setActivated(false);
					Uri pp=Uri.fromFile(new File(Environment.getExternalStorageDirectory()+"/IET/"+adapter.getItem(position).getName()));
					names.add(pp);
					Log.d("kk",""+p+" "+names.size());
					//intent.putExtra(Intent.EXTRA_STREAM,ppp);
					intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,names);
					mshareNew.setShareIntent(intent);
					
					
				}
			   
			 	
			
			}
			 
		 });
		/* getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		 getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Log.d("long1","long");
				startActionMode(mActionMode);
				arg1.setSelected(true);
				adapter.currentSelection=arg2;
				Log.d("",""+arg2+FileArrayAdapter.getsItem(arg2).getName());
				return true;
			}
			
			
		});
	*/
    }
    
    

    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Item o = adapter.getItem(position);
		
		if(o.getImage().equalsIgnoreCase("directory_icon")||o.getImage().equalsIgnoreCase("directory_up")){
				currentDir = new File(o.getPath());
				fill(currentDir);
		}
		else
		{
			onFileClick(o);
		}
	}
    
    private void onFileClick(Item o)
    {
    	//Toast.makeText(this, "Folder Clicked: "+ currentDir, Toast.LENGTH_SHORT).show();
    	
    	if(o.getName().contains(".pdf"))
    	{
    		
    		
    			String filen=o.getPath().substring(o.getPath().lastIndexOf("/"));
    		
    		Log.d(o.getName(),""+o.getName()+filen.toString());
    		File file = new File(Environment.getExternalStorageDirectory()+"/IET"+filen);
    		try{ Intent intent = new Intent();
    	        intent.setAction(Intent.ACTION_VIEW);
    	       Uri uri = Uri.fromFile(file);
    	        intent.setDataAndType(uri, "application/pdf");
    	        startActivity(intent);
    		}catch(Exception e){
    			Toast.makeText(getApplication(),"No application found to open this file",Toast.LENGTH_SHORT).show();
    		}
    		finally{
    			
    		}
    		
    	}
    	if(o.getName().contains(".png")){
            String filen=o.getPath().substring(o.getPath().lastIndexOf("/"));
    		
    		Log.d(o.getName(),""+o.getName()+filen.toString());
    		File file = new File(Environment.getExternalStorageDirectory()+"/IET"+filen);
    		try{ Intent intent = new Intent();
    	        intent.setAction(Intent.ACTION_VIEW);
    	       Uri uri = Uri.fromFile(file);
    	        intent.setDataAndType(uri, "image/*");
    	        startActivity(intent);
    		}catch(Exception e){
    			Toast.makeText(getApplication(),"No application found to open this file",Toast.LENGTH_SHORT).show();
    		}
    		finally{
    			
    		}
    	}
    }
   

}

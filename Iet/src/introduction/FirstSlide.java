package introduction;

import java.util.Timer;
import java.util.TimerTask;

import com.example.iet.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstSlide extends Fragment{
	private ImageView iet_image;
	Timer timer;
	private TextView iet_text;
	private ImageView iet_image2;
	private TextView iet_text1;
	private ImageView iet_image3;
	public View onCreateView(LayoutInflater inflator,ViewGroup container,Bundle b){
		
		View rootView=inflator.inflate(R.layout.first_slide,container,false);
		 timer = new Timer("TweetCollectorTimer");
		iet_image=(ImageView)rootView.findViewById(R.id.iet_image);
		iet_image2=(ImageView)rootView.findViewById(R.id.iet_image2);
		iet_image3=(ImageView)rootView.findViewById(R.id.imageView1);
		iet_text=(TextView)rootView.findViewById(R.id.textView1);
		iet_text1=(TextView)rootView.findViewById(R.id.TextView01);
		final Activity ccs = this.getActivity();
		 TimerTask updateTask1 = new TimerTask() {
        	 int i=0;
             @Override
             public void run() {

            	

                   
            	 ccs.runOnUiThread(new Runnable() {

                     @Override
                     public void run() {
                    	 try{

           iet_image.setVisibility(View.VISIBLE);
			iet_image.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
                     }
            	 });
             }
		 } ;
		  timer.schedule(updateTask1, 2000L, 100000000L);
		//  timer.cancel();
		  
		  TimerTask updateTask2 = new TimerTask() {
	        	 int i=0;
	             @Override
	             public void run() {

	            	

	                   
	            	 ccs.runOnUiThread(new Runnable() {

	                     @Override
	                     public void run() {
	                    	 try{

	                    		 iet_text.setVisibility(View.VISIBLE);
	             				iet_text.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
	             				iet_image2.setVisibility(View.VISIBLE);
	             				iet_image2.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.scringo_slide_in_from_bottom));
	             				 iet_text1.setVisibility(View.VISIBLE);
		             				iet_text1.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
		             				 iet_image3.setVisibility(View.VISIBLE);
			             				iet_image3.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
	                     }
	            	 });
	             }
			 } ;
			  timer.schedule(updateTask2, 3000L, 100000000L);
		// timer.cancel();
			  
		
		return rootView;
		
	}

}

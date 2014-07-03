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
public class SecondSlide extends Fragment{
	private TextView iet_textt;
	private TextView iet_text1;
	private ImageView iet_image3;
	private ImageView iet_image1;
    Timer timer1;
	public View onCreateView(LayoutInflater inflator,ViewGroup container,Bundle s){
	
		View rootView =inflator.inflate(R.layout.second_slide, container, false);
		iet_textt=(TextView)rootView.findViewById(R.id.textView111);
		 timer1 = new Timer("TweetCollectorTimers");
		 
		final Activity ccs=this.getActivity();
		//Log.d("hhhhhhhhhhhhhhhhhhhhhhhhhhh",""+ccs);
		try{
		 TimerTask updateTask111 = new TimerTask() {
        	 int i=0;
             @Override
             public void run() {
            	ccs.runOnUiThread(new Runnable() {

                     @Override
                     public void run() {
                    	 try{

           iet_textt.setVisibility(View.VISIBLE);
			iet_textt.startAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
                     }
            	 });
                 		
             }
		 };
		timer1.schedule(updateTask111, 4000L, 100000000L);
	
		}
		catch(Exception e){
			e.printStackTrace();
		}
return rootView	;

		}

}

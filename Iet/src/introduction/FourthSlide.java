package introduction;

import com.example.iet.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FourthSlide extends Fragment{
	public View onCreateView(LayoutInflater inflator,ViewGroup container,Bundle b){
		
		View rootView=inflator.inflate(R.layout.fourth_slide,container,false);
final ImageButton start=(ImageButton)rootView.findViewById(R.id.start);
		final Activity cc=this.getActivity();
		start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				start.setImageResource(R.drawable.continue_button1);
				Intent myIntent=new Intent("IetHome");
		        
				startActivity(myIntent);
				cc.finish();
				
			}
			
		});
		return rootView;
		
		
	}

	
	

}

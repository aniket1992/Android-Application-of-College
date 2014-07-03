package adapter;

import service.IETService;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.iet.R;
import com.example.result.Result.TestTask;
import com.scringo.Scringo;
import com.scringo.ScringoActivationButton;
import com.scringo.Scringo.ScringoIcon;
import com.scringo.ScringoLeftRibbonButton;
import com.scringo.ScringoRightRibbonButton;

public class Chat extends Fragment{
	
	private Scringo scringo;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.chat, container, false);
        
        scringo= new Scringo(getActivity());
        scringo.setIcon(ScringoIcon.PERSON);
        scringo.init();
        Scringo.setAppId("N36b3Zx7kbtqLaUrCtRcFn599hoLS3Vf");
        scringo.addSidebar();
        scringo.disableSwipeToOpenSidebar();
        
		
        
        ((ScringoActivationButton)rootView.findViewById(R.id.activationLeft)).setScringo(scringo);
 		
 		
 		
 		 
 		
 		
 			//Scringo.openMenu(getActivity());	
 	 		
 		
 		//Scringo.openMyProfile(getActivity());
 		//Scringo.openLogin(getActivity());
 		//Scringo.loginWithFacebook(getActivity());
 		Log.d("yessss",""+Scringo.isLoggedIn());
 		//scringo.openSidebar();
 		//Scringo.openSignup(getActivity())
        return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		scringo.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
		scringo.onStop();
	}

	
	
	

}

package com.example.departments;

import com.example.iet.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import adapter.TextViewEx;

public class EnvironmentalEngineering extends Activity{
	private TextViewEx mission;
	private TextViewEx details;
	private TextViewEx details1;
	private TextViewEx d_news;
	private TextViewEx physical_infra;
	private TextViewEx q2;
	private TextViewEx q3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enen);
       mission=(TextViewEx) findViewById(R.id.mission);
      mission.setText("The P.G. programme offers M.Tech. in Environmental Engineering. The programme Coordinator is Prof. N. B. Singh.\n\n1.The Environmental engineering programme has been recognized as a promising discipline of engineering and technology.\n\n2.The programme is formulated so as to meet the reqiurements and demands of the environmental problem globally.\n\n3.The highly qualified experienced faculty associated strengthens the programme.\n\n4.The students get necessary technical support from various CSIR Research centres in Lucknow.\n\n Environmental engineering has been considered as a promising discipline of engineering and technology. All developmental projects, now mandatory, require on Environmental Impact Assessment (EIA) and Environmental Management plan (EMP), before implementation. It has also been assessed as an area of prime importance an drelevance by the state /central government.\n\n The associated faculty comprises of highly qualified and experienced professionals. It also avails the advantages of infrastructure and professional facilities avilable at ITRC, CDRI, CIMAP, ISCRI and UPPCB situated in lucknow in terms of facility sharing, training and expert lectures in different fields of Environmental Engineering. ",true);
	 
}
}

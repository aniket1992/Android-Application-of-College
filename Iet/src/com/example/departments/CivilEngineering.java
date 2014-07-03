package com.example.departments;

import com.example.iet.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import adapter.TextViewEx;

public class CivilEngineering extends Activity{
	private TextViewEx mission;
	private TextViewEx details;
	private TextViewEx details1;
	private ImageView d_news;
	private TextViewEx physical_infra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.civil);
       mission=(TextViewEx) findViewById(R.id.mission);
      // details=(TextViewEx) findViewById(R.id.details);
       d_news=(ImageView) findViewById(R.id.department_news);
       physical_infra=(TextViewEx) findViewById(R.id.physical_infra);
      
        mission.setText("The Department of Civil Engineering was established in the year 1985. In the ever changing scenario of modern day industry, the civil engineers are the ones who are bridging the gap between the normal civil engineers and the need of the modernization.\n\nThe Civil Department has a highly qualified faculty. The department has developed an excellence in various fields especially in the area of Transportation Engineering. Presently the Transportation Engineering Section is carrying out two AICTE, New Delhi sponsored Projects under the major scheme (Nationally coordinated Projects) in the area of \"Urban Transport Environment Interaction\" & \"Road Traffic Safety\" jointly with IIT Roorkee and University of Bangalore respectively. Earlier in the year 1990 AICTE had sanctioned Rs.17.5 Lacs for modernization of Traffic and Transportation Engineering Laboratory, & it is the only Laboratory in the UP Government State Colleges which has got Central assistance for the modernization. Dept. Headed by Dr. S. P. Sharma.\n\nThe Traffic and Transportation Engineering Lab is having latest equipments for research and development activities and this is the only State Government Engineering College which has got faculty members with specialization in Transportation Engineering at Master's level and the Transportation Engineering Section is the best amongst all the UP State Government Engineering Colleges.\n\nThe Department of Civil Engineering is approved as STATE TECHNICAL AGENCY for Pradhan Mantri Gramian Sadak Yojana, by the Central Government and UP Government as well . Prof. J.B. Srivastava is the Coordinator & Er. A.K. Shukla is the Deputy Coordinator of the PMGSY Scheme. ",true);
        //details.setText("The Computer Science and Engineering Department, IET is a premier and dynamic center for imparting quality education in the field of Computer Science.\nThe Department has been the pioneer department since the college was established and has developed itself in leaps and bounds.\nThe Department is a center for advanced studies, keeping pace with the latest in the rapidly changing environment of information technology.\nIt has been consistent endeavor of the department to adapt itself and its programmers to mirror the requirements of the constantly evolving IT environment in India and abroad.");
        //details1.setText("1.B.Tech in Computer Science and Engineering(since 1984)\nTotal intake-60\nB.Tech in Information Technology(since 1998) \nTotal intake-60\nMaster of Computer Applications(since 1988)\nTotal intake-60");
	//d_news.setText("1.B.Tech in Computer Science and Engineering (since 1984) \nTotal intake-60\n 2.B.Tech in Information Technology (since 1998)\nTotal intake-60\n 3.Master of Computer Applications(since 1988)\nTotal intake-60\n\nEvery year it produces talented breed of computer professionals keeping in mind the requirements of the dynamic and highly competitive environment of the present era. ");
   physical_infra.setText("The department has got well equipped laboratories with latest equipments required to run UG & PG programme and support the research works. Presently4 (part time) research scholars are working in the department .\n1.Soil mechanics lab\n2.Structural Analysis lab\n3.Concrete lab\n4.Traffic engineering lab with computerised DAT testing machine\n5.Environmental engineering lab\n6.Surveying and Remote Sensing and Photogrammetry lab\n7.Transportation engineering lab\n8.Fluid mechanics lab\n9.CAD lab\n10.Building Material\n\n Every year one student gets an opportunity to attend a fully funded month long summer camp at IIT Kanpur, which is an all India level camp where only a single student from each reputed institution gets entry.\n\n",true);
	 
}
}

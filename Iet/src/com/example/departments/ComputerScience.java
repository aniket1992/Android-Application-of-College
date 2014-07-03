package com.example.departments;

import com.example.iet.R;
import com.example.iet.R.id;
import com.example.iet.R.layout;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import adapter.TextViewEx;

public class ComputerScience extends Activity {
	private TextViewEx mission;
	private TextViewEx details;
	private TextViewEx details1;
	private TextViewEx d_news;
	private TextViewEx physical_infra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cse);
       mission=(TextViewEx)findViewById(R.id.mission);
       details=(TextViewEx)findViewById(R.id.details);
       d_news=(TextViewEx)findViewById(R.id.department_news);
       physical_infra=(TextViewEx)findViewById(R.id.physical_infra);
      
        mission.setText("The computer science department is committed towards achieving high academic standards and values to prepare Computer Science and Information Technology graduates and MCA post graduates to meet international standards ",true);
        details.setText("The Computer Science and Engineering Department, IET is a premier and dynamic center for imparting quality education in the field of Computer Science.\nThe Department has been the pioneer department since the college was established and has developed itself in leaps and bounds.\nThe Department is a center for advanced studies, keeping pace with the latest in the rapidly changing environment of information technology.\nIt has been consistent endeavor of the department to adapt itself and its programmers to mirror the requirements of the constantly evolving IT environment in India and abroad.",true);
        //details1.setText("1.B.Tech in Computer Science and Engineering(since 1984)\nTotal intake-60\nB.Tech in Information Technology(since 1998) \nTotal intake-60\nMaster of Computer Applications(since 1988)\nTotal intake-60");
	d_news.setText("1.B.Tech in Computer Science and Engineering (since 1984)\nTotal intake-60\n2.B.Tech in Information Technology (since 1998)\nTotal intake-60\n3.Master of Computer Applications(since 1988)\nTotal intake-60\n\nEvery year it produces talented breed of computer professionals keeping in mind the requirements of the dynamic and highly competitive environment of the present era. ",true);
   physical_infra.setText("Total Area : App. 10000 Sq ft .It has a three floor building containing :\n1.Computing Lab I\n2.Computing Lab II\n3.Internet Computing Lab\n4.Digital Lab\n5.Two Departmental Class Rooms\n6.Seminar Room\n7.Meeting Room\n8.Department Office\n\n",true);
	
}
}

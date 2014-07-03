package com.example.departments;

import com.example.iet.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import adapter.TextViewEx;

public class ElectricalEngineering extends Activity{
	private TextViewEx mission;
	private TextViewEx details;
	private TextViewEx details1;
	private TextViewEx d_news;
	private TextViewEx physical_infra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ee);
       mission=(TextViewEx) findViewById(R.id.mission);
       details=(TextViewEx) findViewById(R.id.details);
       d_news=(TextViewEx) findViewById(R.id.department_news);
       physical_infra=(TextViewEx) findViewById(R.id.physical_infra);
      
        mission.setText("The department of electrical engineering is one of the most electrifying com.example.departments, producing engineers with the power of knowledge and experience. Created in 1984, it is one of the leading and oldest com.example.departments in IET, Lucknow. The engineers graduating from the department spend their four-year graduation period under the influence of some of the most experienced faculty members. Dept. headed by Dr. Kuldeep Sahai",true);
        
        //details1.setText("1.B.Tech in Computer Science and Engineering(since 1984)\nTotal intake-60\nB.Tech in Information Technology(since 1998) \nTotal intake-60\nMaster of Computer Applications(since 1988)\nTotal intake-60");
	d_news.setText("The syllabus is well up to date and be spoke for the needs of the industry and follows the guidelines laid down by AICTE, New Delhi.\n\nIt includes Elements of Power System, Power Electronics, Electric Devices, Power Quality Control System, High Voltage Engineering, Quality System and Management, Non-conventional Energy Sources, Simulation of Electrical Machines & Bio-instrumentation. ",true);
   physical_infra.setText("The department has over eleven well-equipped laboratories.\nThese are as follows:\n1.Machine lab\n2.Power Electronics lab\n3.Computers lab\n4.Digital Electronics lab\n5.Mroprocessor lab\n6.Control lab Measurement lab\n7.Network lab\n8.Power System lab\n9.Instrumentation lab\n\nIn addition to a central institute library, the department offers the facility of books on all areas of Electrical engineering through its own library. \n\n",true);
	 
}
}

package com.example.departments;

import com.example.iet.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import adapter.TextViewEx;

public class ElectronicsCommEngineering extends Activity{
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
		setContentView(R.layout.ece);
       mission=(TextViewEx) findViewById(R.id.mission);
       details=(TextViewEx) findViewById(R.id.details);
       d_news=(TextViewEx) findViewById(R.id.department_news);
       physical_infra=(TextViewEx) findViewById(R.id.physical_infra);
       q2=(TextViewEx) findViewById(R.id.q2);
       q3=(TextViewEx) findViewById(R.id.q3);
      
        mission.setText("The Department of Electronics Engineering under the able guidance of Dr. Neelam Chandra, Head of the Department, and the consistent efforts of its highly qualified and esteemed faculty members, is a proof to the aforesaid lines. ",true);
        details.setText("In tune with the latest developments and keeping pace with the rapidly evolving technology, specialized laboratories have been established with a view to strengthen research and development. Equipped with state-of-the-art equipments and instruments for experimentation in different subjects, these assist students in exhaustive study and help them in learning various concepts through practical experience.\n\nThe department offers two academic programmes:\n1. B.Tech. in Electronics and Communication Engineering (EC)\n2.B.Tech. in Electronics and Instrumentation Engineering (EI)",true);
        //details1.setText("1.B.Tech in Computer Science and Engineering(since 1984)\nTotal intake-60\nB.Tech in Information Technology(since 1998) \nTotal intake-60\nMaster of Computer Applications(since 1988)\nTotal intake-60");
	d_news.setText("The department has a well supplemented set of laboratories. These labs are as follows: \n1.Basic Electronics lab\n2.PCB lab\n3.Communication Systems (Optical Fiber) lab\n4.Digital Electronics lab\n5.Analog Integrated Circuits lab\n6.Computer Simulation and Microprocessor lab\n7.Power Electronics lab\n8.Microwave Engineering lab\n",true);
   physical_infra.setText("The core curriculum lay emphasis on understanding the basic electronics principles, semiconductor technology, power electronics, microwave engineering, microprocessor, analog communication systems and integrated circuits, VLSI technology, Satellite communication and signal processing. ",true);
   q2.setText("The electronics and instrumentation engineer is concerned with the design of an over-all process control system that will provide the regulation specified by the manufacturing process experts. This involves a good design-level understanding of the measurement, electronic and pneumatic features inherent in instrumentation-control. ",true);
   q3.setText("Modern Control Systems Control System Components Bio-medical Electronics Intelligent Instrumentation Consumer Electronics Industrial Instrumentation Digital Control Digital Measurement Techniques.\n\nThe students work under the guidance of the faculty on several projects based on Optical fiber communication, Computer simulation. Bio-medical engineering and Micro Controller applications in the field of power electronics.\n\n ",true);
	 
}
}

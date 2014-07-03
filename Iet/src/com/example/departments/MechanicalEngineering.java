package com.example.departments;

import com.example.iet.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import adapter.TextViewEx;

public class MechanicalEngineering  extends Activity{
	private TextViewEx mission;
	private TextViewEx details;
	private TextViewEx details1;
	private TextViewEx d_news;
	private TextViewEx physical_infra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me);
       mission=(TextViewEx) findViewById(R.id.mission);
       details=(TextViewEx) findViewById(R.id.details);
       d_news=(TextViewEx) findViewById(R.id.department_news);
       physical_infra=(TextViewEx) findViewById(R.id.physical_infra);
      
       mission.setText("The Mechanical Engineering department is considered as the heart of the institute. The strength of the department is the highly qualified faculty which guides the students during the programme. A mechanical engineer designs, develops, operates and maintains systems required for all what happens in industry whether in a power plant, space & nuclear establishment, steel plant, military establishments or automobile industry, etc. The Department conduct the Program \"B.Tech in Mechanical Engineering , Total intake=60\" ",true);
        
        //details1.setText("1.B.Tech in Computer Science and Engineering(since 1984)\nTotal intake-60\nB.Tech in Information Technology(since 1998) \nTotal intake-60\nMaster of Computer Applications(since 1988)\nTotal intake-60");
	d_news.setText("The syllabus is fully updated and made according to the present day need of the industry. The department of Mechanical Engineering is embellishing the following subjects that fulfill the technology needs.\nApplied Thermodynamics, Product Development and Design, Machine Design, Strength of Materials, Courses in Microprocessor Application, CAD/CAM, Industrial Engineering and Management, Entrepreneurship and Project Planning, Value Engineering, Organizational Behaviours, Condition Monitoring and Diagnostic & Mechanical Vibration, etc. ",true);
   physical_infra.setText("The department provides the following labs:\n1.Material Science lab\n2.Measurements and Metrology lab\n3.Material Testing lab\n4.Machine lab\n5.Thermal lab Vibration lab\n6.I.C. Engine lab\n7.Heat Transfer lab\n8.Mechanical Workshop\n9.Kinematics and Dynamics of Machine lab\n10.CAD lab\n\nIt's the only department which which provides exposure to it's students to softwares like MATLAB, and now is in the process of procuring Pro-Engineer & Finite Element Analysis software to further horn their skills.\n\n",true);
	 
}
}

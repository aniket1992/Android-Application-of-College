package com.example.departments;

import com.example.iet.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import adapter.TextViewEx;

public class MBA extends Activity{
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
		setContentView(R.layout.mba);
       mission=(TextViewEx) findViewById(R.id.mission);
       details=(TextViewEx) findViewById(R.id.details);
       d_news=(TextViewEx) findViewById(R.id.department_news);
       physical_infra=(TextViewEx) findViewById(R.id.physical_infra);
       q2=(TextViewEx) findViewById(R.id.q2);
       q3=(TextViewEx) findViewById(R.id.q3);
      
        mission.setText("The Master of Business Administration (MBA) is a two-year full time course spread over four semesters. The programme is duly approved by AICTE, New Delhi and affiliated to the U.P. Technical University, Lucknow. Coordinator of this programe is Dr. D.N. Kakkar.",true);
        details.setText("Besides and IET faculty a team of highly specialized resource person are associated with the programme. The programme aims at bringing together the students from diverse geographical and educational background like Science, Arts, Commerce & engineering and groom their conceptual and analytical skills so as to streamline the talents to adapt to the ever demanding requirements of the modern business world.\n\nThe objective is being achieved by various activities like case studies, debate, group discussion, students' workshop & mock-interviews.\nApart from these, students publish their own monthly newsletter \"Management Interface\", which updates them with recent trends and developments in the department as well as in the macro environment. ",true);
        //details1.setText("1.B.Tech in Computer Science and Engineering(since 1984)\nTotal intake-60\nB.Tech in Information Technology(since 1998) \nTotal intake-60\nMaster of Computer Applications(since 1988)\nTotal intake-60");
	d_news.setText("The institute has proved its academic excellence as MBA first semester students secured the top first,sixth, and tenth positions in UPTU. MBA third semester students secured the top fourth and seventh positions in UPTU.",true);
   physical_infra.setText("Students undergo a two year rigorous programme at IET including two months summer training in various reputed organizations like PEPSI , COKE, GODREJ, BLUE STAR, LML, HAL, HDFC, BHEL, BAJAJ, ICICI etc, and are ready to face the challenges.",true);
   q2.setText("This year reputed companies like TELCO, HDFC, HCL, YAMAHA, KOTAK MAHINDRA, CIPLA, ICICI, etc. recruited many final year students. They have been placed highly in these reputed organizations.\nIET MBA programme has earned a reputation for its discipline, regular sessions, continuous evaluation system, emphasis on development of dynamic personality and outlook of its students and highly qualified and motivated faculty.\n\n ",true);
  // q3.setText("Modern Control Systems Control System Components Bio-medical Electronics Intelligent Instrumentation Consumer Electronics Industrial Instrumentation Digital Control Digital Measurement Techniques.\n\nThe students work under the guidance of the faculty on several projects based on Optical fiber communication, Computer simulation. Bio-medical engineering and Micro Controller applications in the field of power electronics.\n\n ");
	 
}
}

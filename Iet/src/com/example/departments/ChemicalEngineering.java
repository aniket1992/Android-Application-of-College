package com.example.departments;

import com.example.iet.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import adapter.TextViewEx;

public class ChemicalEngineering extends Activity{
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
		setContentView(R.layout.chemical);
       mission=(TextViewEx) findViewById(R.id.mission);
       details=(TextViewEx) findViewById(R.id.details);
       d_news=(TextViewEx) findViewById(R.id.department_news);
       physical_infra=(TextViewEx) findViewById(R.id.physical_infra);
       q2=(TextViewEx) findViewById(R.id.q2);
       q3=(TextViewEx) findViewById(R.id.q3);
      
        mission.setText("The department offers an academic programme leading to B.Tech degree in chemical engineering. The department has a highly qualified faculty to guide the students at every step. The department has strength of highly qualified faculty, which give their valuable guidance to the students. Dept. headed by Shri Ram Pravesh.",true);
        details.setText("The curriculum is fully updated and meets the industry requirements and trends in the industry. The electives provide an opportunity to the students to hone their practical skills and strengthen their practical and theoretical base.\n\nThe students who engage themselves in project work, seminars and have periodic visits at various research institutes. ",true);
        //details1.setText("1.B.Tech in Computer Science and Engineering(since 1984)\nTotal intake-60\nB.Tech in Information Technology(since 1998) \nTotal intake-60\nMaster of Computer Applications(since 1988)\nTotal intake-60");
	d_news.setText("It offers a well-planned and balanced curriculum to enable students to cope up with the latest knowledge and rigorous industrial trends. ",true);
   physical_infra.setText("Along with inter-disciplinary core courses, the department offers some specialized courses that include Petroleum Refining Engineering, Fertilizer Technology, Hazard and safety analysis, design catalytic reactors, advanced Separation technique, high polymer technology, Surface coating And technology, oil technology, Biochemical Engineering. ",true);
   q2.setText("The department has many laboratories that are fully-equipped and well maintained. These laboratories not only enhance the practical skills, but also strengthen the theoretical concepts. The various laboratories in the department include:\n\n1.Fluid mechanics and particle laboratory\n2.Heat transfer lab\n3.Mass transfer laboratory having computerized distilling column\n4.Chemical reaction engineering laboratory having plug flow reactor\n5.CSTR along with Batch Reactor\n6.Process instrumentation lab with experiments on proportional controller, PI controller, PID controller etc.\n7.CAD laboratory\n\nFor project reports, seminar works or for knowledge of latest developments, the students continuously visit CDRI, CMAP and CSIR. Every year a good percentage of students employed in reputed companies and pursue higher studies. With hard and proper guidance of faculty members, the students make their way to IISC, IITs and BARC.\n\nIn short these dynamic engineers, by their sheer labour and work to win attitude can stabilize the chequered markets and also can turn the stumbling blocks into the stepping stones to success.\n\n ",true);
   //q3.setText("Modern Control Systems Control System Components Bio-medical Electronics Intelligent Instrumentation Consumer Electronics Industrial Instrumentation Digital Control Digital Measurement Techniques.\n\nThe students work under the guidance of the faculty on several projects based on Optical fiber communication, Computer simulation. Bio-medical engineering and Micro Controller applications in the field of power electronics.\n\n ");
	 
}
}

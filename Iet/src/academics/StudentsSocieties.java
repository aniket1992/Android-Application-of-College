package academics;

import com.example.iet.R;

import adapter.TextViewEx;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StudentsSocieties extends Activity {
	private TextViewEx mission;
	private TextViewEx details;
	private TextViewEx details1;
	private TextViewEx d_news;
	private TextViewEx physical_infra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studentsocieties);
       mission=(TextViewEx)findViewById(R.id.mission);
      
       d_news=(TextViewEx)findViewById(R.id.department_news);
       physical_infra=(TextViewEx)findViewById(R.id.physical_infra);
      
        mission.setText("The institute has various student societies run by the students of various com.example.departments to carry out activities that are most essentuial for the development of the individual. The societies active in the institute are :\n\n1. KRITI - The Creative Vision (CSE )\n2. Electrical Engineering Society",true);
        
        //details1.setText("1.B.Tech in Computer Science and Engineering(since 1984)\nTotal intake-60\nB.Tech in Information Technology(since 1998) \nTotal intake-60\nMaster of Computer Applications(since 1988)\nTotal intake-60");
	d_news.setText("The Computer Science and Engineering Department of the institute works continuously to keep the students aware of the latest technology and thus produce young budding technocrats who can bring laurels to the institute, and this was the cause of the launch of Computer Science and Engineering Department Students' society named \"KRITI - the creative vision\".\n\nThe society works for the all-round development of the students. It also aims at keeping the students aware of the latest market demands and happenings as well as the technological trends. The society mainly works in the following fields-\n1. Wall Magazine\n2. Personality Development\n3. Quizzes/Competitions/Seminars\n4. Database Maintenance\n5. Paper Presentation\n6. Technical Events\n7. Departmental Library\n8. Software Development\n9. Web Designing\n10.Student Welfare\n\nThe society was launched on the eve of the National Science Day, i.e. 28th February 2004.The society is continuously progressing under the proper guidance of Mr.D.S.Yadav, the Head Of the Department and the executive committee of the society consists of students from third year and final year. Till date the society has successfully achieved the following-\n(a). Debate on the topic \"IT slump-a fact or a myth?\"\n(b). General Computer Quiz\n(c). Quizzing in C\n(d). Puzzles\n(e). Wall magazine setup\n(f). Departmental Library Hall\n\nThe society shall continuously strive towards the betterment of the students in all the fields. ",true);
   physical_infra.setText("The society is run by the students and for the students.The basic aim of EES is :\n\"To inculcate extra and co-curriculur activities as well as to procure advanced technical knowledge.\"\n\nThe various activities of EES are :\n1.Debate on the topic \"IT slump-a fact or a myth?\"\n2.General Computer Quiz\n3.Quizzing in C\n4.Puzzles\n5.Wall magazine setup\n6. Departmental Library Hall\n\n",true);
	
}
}

package academics;

import com.example.iet.R;

import adapter.TextViewEx;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Shouryotsava extends Activity {
	private TextViewEx mission;
	private TextViewEx details;
	private TextViewEx details1;
	private TextViewEx d_news;
	private TextViewEx physical_infra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shouryotsava);
       mission=(TextViewEx)findViewById(R.id.mission);
      
       d_news=(TextViewEx)findViewById(R.id.department_news);
       physical_infra=(TextViewEx)findViewById(R.id.physical_infra);
      
        mission.setText("It is not \"all work no play\" in IET. Every year ISSACC organizes a 3 days sports meet where the students show their stamina and will power. Different kind of sports and games make the technocrats strong & perfect. IET believes in exploring the students' ability to the fullest.\n\nOn the whole, the institute strives to develop the overall personality of its students through encouraging the above mentioned activities. All the events are completely managed by students themselves from the planning the resource generation to the final execution. ",true);
        
        //details1.setText("1.B.Tech in Computer Science and Engineering(since 1984)\nTotal intake-60\nB.Tech in Information Technology(since 1998) \nTotal intake-60\nMaster of Computer Applications(since 1988)\nTotal intake-60");
	d_news.setText("(a). Outdoor Activities\n\n1. Athletics\n2. Races\n3. High Jump and Long Jump\n4. Javelin Throw\n5. Discuss Throw\n6. Hammer Throw\n7. Cricket\n8. Kabbadi\n9. Football\n10. Volleyball\n11. Tae-kwan-do\n12. Lawn Tennis\n\n(b). Indoor Activities\n\n1. Table Tennis\n2. Badminton\n3. Chess\n4. Carrom\n",true);
   //physical_infra.setText("The society is run by the students and for the students.The basic aim of EES is : \n\"To inculcate extra and co-curriculur activities as well as to procure advanced technical knowledge.\"\n\n The various activities of EES are :\n1. Debate on the topic \"IT slump-a fact or a myth?\"\n2. General Computer Quiz\n3. Quizzing in C\n4. Puzzles\n5. Wall magazine setup\n6. Departmental Library Hall\n\n");

}
}

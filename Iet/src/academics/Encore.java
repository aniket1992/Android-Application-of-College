package academics;

import com.example.iet.R;

import adapter.TextViewEx;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Encore extends Activity {
	private TextViewEx mission;
	private TextViewEx details;
	private TextViewEx details1;
	private TextViewEx d_news;
	private TextViewEx physical_infra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.encore);
       mission=(TextViewEx)findViewById(R.id.mission);
      
       d_news=(TextViewEx)findViewById(R.id.department_news);
       physical_infra=(TextViewEx)findViewById(R.id.physical_infra);
      
        mission.setText("The motto of our institute is \"Gyan Bharam Kriyam Binam\" which means that knowledge is a load without application. The students of this institute are not only rich in technical and academic skills but they also excel culturally. To celebrate all these skills of our students, our cultural fest 'ENCORE' is organized every year. It is our institute's cultural and technical fest. Other colleges from throughout the country are invited to participate in the fest.\n\nEncore stands for Endeavour, Nous, Creativity, Opera, Rollick and Enthusiasm.This is the theme of Encore with which we budding engineers bring out the very best in ourselves. It provides a platform for self-analysis and also enhances managing,organizing and many more hidden capabilties among the students. ",true);
        
        //details1.setText("1.B.Tech in Computer Science and Engineering(since 1984)\nTotal intake-60\nB.Tech in Information Technology(since 1998) \nTotal intake-60\nMaster of Computer Applications(since 1988)\nTotal intake-60");
	d_news.setText("1. Stage Events\n\n(i). Musical Night :\n\n(a) Eastern Slot : Group songs, Filmi Solo, Non-filmi solo, Duet songs, Folk Songs.\n(b) Western Slot : Solo, Group Songs, Instrumental, Classical Round.\n\n(ii). Choreography : Western Group , Western Solo , Folk Group, Folk Solo, Clasical Solo, Choreography.\n\n(iii). Dramatics : Street Play, Skit, Stage Play, Mono-acting, Mimicry.\n\n2. Non-Stage Events\n\n(i) Fine arts : Sketching, Spray Painting, 3-D drawing, Mehndi, Ad-making, Rangoli, Collage Making, Cartooning and Clay modelling.\n\n(ii) Quiz events : Computer Quiz, sports Quiz, Business Quiz, History Quiz, Cine quiz, Trivia.\n\n(iii) Technical events : Seminar, Technical Quiz, Puzzlika, Mock GRE, Creative Writing, Mathematics Olympiad.\n\n(iv) English Literary Events : Debate, Group Discussion, Elocution, Hard Talk, Jam, Reason It Out, Mock CAT, Volte-Face.\n\n(v) Hindi Literary Events : Hindi Saras Antakshari, Srijanatmak Lekh, Anargal Pralap, Hindi Sahitya Prashonotri, Ashu Bhashan, Hindi Debate, Kavyanjali, Chhintakashi, Manthan (volteface), Mantrana(GD).\n\n(vi) Project/ Model Presentation\n\n",true);
   //physical_infra.setText("The society is run by the students and for the students.The basic aim of EES is : \n\"To inculcate extra and co-curriculur activities as well as to procure advanced technical knowledge.\"\n\n The various activities of EES are :\n1. Debate on the topic \"IT slump-a fact or a myth?\"\n2. General Computer Quiz\n3. Quizzing in C\n4. Puzzles\n5. Wall magazine setup\n6. Departmental Library Hall\n\n");
	
}
}

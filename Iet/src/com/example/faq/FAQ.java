package com.example.faq;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import adapter.ExpandableListAdapter;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.example.iet.R;

public class FAQ extends Fragment{
	private SQLiteDatabase helper;

    public Cursor cursor;

	private MenuItem menuItem;

	private ExpandableListView expList;
	List<String> groupList;

	private LinkedHashMap<String, List<String>> laptopCollection;

	private List<String> childList;
	String[] faq=new String[50];
	
	public  FAQ(){
		
	}
	
	public  FAQ(SQLiteDatabase helper) {
		this.helper=helper;
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.faq, container, false);
        //TextView inst=(TextView)rootView.findViewById(R.id.faq1);
       
        //inst.setText(faqs1);
      createGroupList();
      createCollection();
       expList=(ExpandableListView)rootView.findViewById(R.id.expandableListView1);
       final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(getActivity(), groupList, laptopCollection);
       expList.setAdapter(expListAdapter);
       
       
       expList.setOnChildClickListener(new OnChildClickListener() {
    	   
           public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
               final String selected = (String) expListAdapter.getChild(groupPosition, childPosition);
               
               Log.d("ff", "pp"+groupPosition);

               return true;
           }
       });
   
       
       
       
       
       
       
       
       
       
       
       
       return rootView;
       
}

	private void createCollection() {
		 String[] ans0 = { "The Institute is the fastest upcoming Institute. It is ranked in the category of most premium Institutes of India. It has most advanced infrastructure for students. The Institute also has the best faculty having both academic and industry background. Faculty members do research and develop instructional materials on a continuous basis. Most importantly, the Institute cares for its students through counseling, mentoring and involvement in various institutional activities." };
 String[] ans1 = { "Computer Science & Engineering", "Information Technology", "Civil Engineering","Electrical Engineering","Mechanical Engineering","Electronics & Communications","Electronics & Instrumentation","Chemical Engineering","Master of Computer Applications","M.Tech. (Environmental Engineering)","M.Tech. (Biotechnology)","Master of Business Administration"};
 String[] ans2 = { "The BTech programme is a rigorous 4 yrs programme which prepares students to take on to the corporate world. The course is designed to meet the latest industry requirement in consultation with eminent Industry people. The students are trained to work under pressure and deadlines. The projects in the programm gives exposure to the corporate world and brings the team work in the students."};
 String[] ans3 ={"The Institute has hostel facilities for boys and girls separately. There are six boys' and two girls' hostels along with 24-hour backup power supply. They accommodate around 1500 undergraduate and postgraduate students. Committees consisting of elected student representatives manage them. All the hostels are located on the campus. The hostel rooms are spacious and well-furnished.Each hostel has its own mess. The hostel mess is operated by elected student representative. Spacious and modern kitchens with steam cooking cater to healthy and nutritious food. Strict hygienic conditions are maintained.Each hostel is provided with a television, telephone, water-purifier, water cooler and kit for indoor games and outdoor games. The hostels, also include a shop within its boundary for basic amenities. The hostel residents are expected to adhere to a set of hostel rules and regulations."};
 String[] ans4 ={"ADMISSION TO FIRST YEAR :\nThe Entrance Examination for admission to First Year of Four Year B.Tech. / B.Tech.(Ag. Engg.) / B.Pharm / B.HMCT. and 5 Year of B.Arch. courses offered at the Institutions affiliated to U.P. Technical University Lucknow is conducted by U.P.Technical University, Lucknow as per Govt. Order 4514/2003 - Solah - 1- 10(1) / 2001 dated 03.12.2003 and will be known as State Entrance Examination, Uttar Pradesh Technical University (SEE-UPTU) (Formerly known as UPSEAT www.uptu.nic.in ).\n\nDIRECT ADMISSION TO SECOND YEAR (For Diploma holder) :\nThe eligible candidates (Refer section 3.2) seeking direct admission to IInd year of B.Tech./ B.Tech.(Ag. Engg.) / B.Pharm and B.HMCT in institutions affiliated to UPTU are required to submit the application forms to Registrar, U.P. Technical University, SEE-UPTU:2004, Lucknow by the last date. Such candidates need not appear in the entrance examination. The merit list for admission will be prepared on the basis of the total marks obtained by them in the Diploma examination.. Direct admission to Second year (for diploma holders ) shall not be made in B.Arch. course. \nSuch lateral entry for diploma holders will be allowed in the second year third semester level. Seats in addition to the sanctioned intake at first year level are limited to maximum of 10% and will be reserved for such candidates.\n\nADMISSION TO NRI CANDIDATES :\n\nNRI candidates, who possess prescribed qualifications, may get direct admission on 5 percent seats of total intake in State funded / Private Engineering Colleges / Institutions. Such candidates should submit the application forms to U.P. Technical University, Lucknow but need not appear in the entrance examination. Separate counseling of such candidates will be done by U.P. Technical University.\n\n\nAdmission in an institution affiliated to UPTU will be on the basis of merit, eligibility of the candidate & seat option given by him/her at the time of counselling, No transfer from one institution to another is permissible on any ground whatsoever after allotment of seat/branch "};
 String[] ans5 ={"Master of Technology :\nThe Admission to the M.Tech. course can be obtained after qualifying GATE (Graduate Aptitude Test in Engineering). The institute offers two M.Tech. programmes viz. Environmental Engineering and Biotechnology.\nThe admission is entirely based upon merit and depends on the percentile achieved in the examination. The addmission process is done by UPTU. For more information www.uptu.ac.in"};
 String[] ans6 ={"MCA / MBA :\nThe State Entrance Exam, Uttar Pradesh Technical University is conducted by Uttar Pradesh Technical University, Lucknow (UPTU) for admission to first year of (A) three year full time MCA course and (B) Two year full time Management Courses offered by the Institute. This entrance examination is conducted for admission to UP Quota seats only.\n\nFor further details, log on to www.uptu.ac.in"};
 String[] ans7 ={"ELIGIBILITY CRITERIA :\n\nAdmission to First Year :\nThe minimum academic qualification to appear in State Entrance Examination - 2004, Uttar Pradesh Technical University (SEE-UPTU: 2004) is a pass in the final examination of 10+2 standard or equivalent. Those candidates appearing in 10+2 final examination may also appear in SEE-UPTU:2004 subject to the condition that he / she will pass 10+2 examination before counseling for admission takes place. The group and subject combinations required in the qualifying examination for admission to different courses shall be as under:-"};
 String[] ans8 ={"ADMISSION TO NRI CANDIDATES :\n\nNRI candidates, who possess prescribed qualifications, may get direct admission on 5 percent seats of total intake in State funded / Private Engineering Colleges / Institutions. Such candidates should submit the application forms to U.P. Technical University, Lucknow but need not appear in the entrance examination. Separate counseling of such candidates will be done by U.P. Technical University."};
 String[] ans10 ={"Refer to Placment Module"};
 String[] ans9 ={"The teaching methodology incorporates:-Lectures regularly delivered on each subject.Assignments to be submitted within the specified time limit.Quizzes enable the students to remain regular in their studies.Tutorials evaluates the student's regularity and hard-work.Laboratories provides the environment to implement their theoretical concepts."}; 
 String[] ans11 ={"The Institute was granted Industry Institute Partnership Cell in 1999 by Ministry of the Human Resource, Govt. of India.\n\n\nThe broad objectives of this Cell are:\n\nTo develop strategies for the promotion of technology.\n\nTo facilitate industries with know-how in emerging areas of technology.\n\nTo collaborate with industry for joint research projects.\n\nInviting industry personnel to institute to share their experience with the students.\n\nTo conduct short-term courses for persons working in industry.\n\nTo develop curriculum to suit the needs of the industry.\n\nTo arrange vocational training for the students in industries.\n\nTo take R& D projects of the industries and provide the solutions to them.\n\nTo arrange the campus interviews and placement facilities to the students.\n"}; 
 String[] ans12 ={"From the railway station (Charbagh) one can hire an auto rickshaw (three-wheeler) or the maxi cabs (Tata Sumos or Marshall's, painted  with a yellow strip on the body) or the city-buses (Route Number-5), which are readily available. It takes between 30 minutes to 45 minutes to reach the campus.Please Note: Institute of Engineering and Technology, Lucknow is locally spoken as "+"Engineering College."}; 
 String[] ans13 ={"Please follow the following hyperlink to find contacts of the alumni. Our extensive database contains details only of the registered alumni"};
 String[] ans14 ={"Refer to Co-Curriculars section in Academics Module"};

 laptopCollection = new LinkedHashMap<String, List<String>>();

 for (String laptop : groupList) {
     if (laptop.equals(faq[0])) {
         loadChild(ans0);
     } else if (laptop.equals(faq[1]))
         loadChild(ans1);
     else if (laptop.equals(faq[2]))
         loadChild(ans2);
     else if (laptop.equals(faq[3]))
         loadChild(ans3);
     else if (laptop.equals(faq[4]))
         loadChild(ans4);
     else if (laptop.equals(faq[5]))
         loadChild(ans5);
     else if (laptop.equals(faq[6]))
         loadChild(ans6);
     else if (laptop.equals(faq[7]))
         loadChild(ans7);
     else if (laptop.equals(faq[8]))
         loadChild(ans8);
     else if (laptop.equals(faq[9]))
         loadChild(ans9);
     else if (laptop.equals(faq[10]))
         loadChild(ans10);
     else if (laptop.equals(faq[11]))
         loadChild(ans11);
     else if (laptop.equals(faq[12]))
         loadChild(ans12);
     else if (laptop.equals(faq[13]))
         loadChild(ans13);
     else 
         loadChild(ans14);

     laptopCollection.put(laptop, childList);
 }
		
	}

	private void loadChild(String[] hpModels) {
		childList = new ArrayList<String>();
        for (String model : hpModels)
            childList.add(model);
		
	}
	

	private void createGroupList() {
		// TODO Auto-generated method stub
		 faq[0]="Why should I join Institute of Engineering & Technology? ";
		 faq[1]="Which are the programmes offered by Institute of Engineering & Technology?";
		 faq[2]="What are the unique features of B.Tech. programme?";
		 faq[3]="Does Institute have the hostel facilities?";
	     faq[4]="How can I get admission to B.Tech. programme?";
	     faq[5]="How can I get admission to M.Tech. programme?";
	     faq[6]="How can I get admission to MCA programme?";
	 	 faq[7]="What are the eligibility criteria for B.Tech.?";
	   	 faq[8]="Does Institute have seats for Non Resident Indians?";
		 faq[9]="What is the pre-dominant teaching methodology?";
	   	 faq[10]="What are the placement activities?";
	     faq[11]="How well is the Institute connected with the Industry?";
	     faq[12]="What are the transport facilities to reach the campus?";
	     faq[13]="How do I contact Institute Alumni?";
	     faq[14]=" How is the cultural life on campus?";
		  groupList = new ArrayList<String>();
	        groupList.add(faq[0]);
	        groupList.add(faq[1]);
	        groupList.add(faq[2]);
	        groupList.add(faq[3]);
	        groupList.add(faq[4]);
	        groupList.add(faq[5]);
	        groupList.add(faq[6]);
	        groupList.add(faq[7]);
	        groupList.add(faq[8]);
	        groupList.add(faq[9]);
	        groupList.add(faq[10]);
	        groupList.add(faq[11]);
	        groupList.add(faq[12]);
	        groupList.add(faq[13]);
	        groupList.add(faq[14]);
	        
	        
	       
	}
	
}

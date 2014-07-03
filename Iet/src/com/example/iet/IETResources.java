package com.example.iet;

import adapter.TextViewEx;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IETResources extends Fragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
     final    View rootView = inflater.inflate(R.layout.resources, container, false);
        
        
      
        	TextViewEx inst=(TextViewEx)rootView.findViewById(R.id.institute_resources1);
            //TextViewEx inst1=(TextViewEx)rootView.findViewById(R.id.resources2);
            TextViewEx inst2=(TextViewEx)rootView.findViewById(R.id.resources3);
            TextViewEx inst3=(TextViewEx)rootView.findViewById(R.id.resources5);
            TextViewEx inst4=(TextViewEx)rootView.findViewById(R.id.resources6);
            TextViewEx inst5=(TextViewEx)rootView.findViewById(R.id.resources9);
            TextViewEx inst6=(TextViewEx)rootView.findViewById(R.id.resources10);
            TextViewEx inst7=(TextViewEx)rootView.findViewById(R.id.resources11);
            TextViewEx inst8=(TextViewEx)rootView.findViewById(R.id.resources12);
			
						 inst.setText("At the IET, we believe in excellence, which originates in creativity, enterprise and industry. This philosophy drives our pedagogy and curriculum. Our alumni have proved their mettle in the tough grind of the corporate world. Their success could be attributed to the rigour and grit at the IET. With emphasis given to not only the fundamentals of academics, but also all-round development of the individual, the student when he walks out of these doors is no longer a student, but an executive toughened to take on the battles of the corporate world.\n\nLiving in a new environment can be a challenging and sometimes daunting experience, especially for young students who are entering the undergraduate college for the first time. Keeping these things in mind, the Institute of Engineering and Technology, Lucknow provides all the basic amenities and support facilities on the campus. In addition to the basic amenities like telephones, Internet, and banking services, the institute provides various support facilities like the library, computer centre, gymnasium, dispensary etc. to help students pursue their studies and research without any hindrance and give them an all-around development. These facilities are centrally located and are open beyond the normal working hours. Services of the central library and computer center are some of the services that are heavily used by the students.\n\nAll these central facilities are well equipped with the necessary infrastructure and headed by a faculty member and/or a senior administrative staff.\n",true);
      //inst1.setText("As books are best friends, philosopher and guide, so the Institute has an excellent library adjacent to academic block. Apart from books, students have access to the latest magazines, international journals, newspapers and research papers. The library also stocks a",true);
        inst2.setText("As books are best friends, philosopher and guide, so the Institute has an excellent library adjacent to academic block. Apart from books, students have access to the latest magazines, international journals, newspapers and research papers. The library also stocks a huge collection of educational cd-roms and old copiesof important periodicals.\n\nIt aims to facilitate production & dissemination of knowledge, information, insights & intellectual contribution in all areas of education among the academic and business community.\n\nYou can borrow upto three books for a period of fifteen days. Some books (called study room books) are issued after 4 p. m. for one night only and that too only against the I - card. All this, all the year round, without any but three holidays.\n\nThe Book Bank within the Library provides a facility to, SC/ST students of issuing up to six books and general students one book for a semester. The library also has a photocopying facility.",true);
inst3.setText("The institute has well-equipped air-conditioned computer laboratories with the latest in both hardware and software. All the computers are part of an intranet. The institute has high bandwidth Internet connectivity round the clock. Students and researchers have 24 hour-access to the computational facilities.\n\n",true);
inst4.setText("The Institute has hostel facilities for boys and girls separately. There are six boys' and two girls' hostels along with 24-hour backup power supply. They accommodate around 1500 undergraduate and postgraduate students. Committees consisting of elected student representatives manage them. All the hostels are located on the campus. The hostel rooms are spacious and well-furnished.\n\nEach hostel has its own mess. The hostel mess is operated by elected student representative. Spacious and modern kitchens with steam cooking cater to healthy and nutritious food. Strict hygienic conditions are maintained.\n\nEach hostel is provided with a television, telephone, water-purifier, water-cooler and kit for indoor games and outdoor games. The hostels, also include a shop within its boundary for basic amenities. The hostel residents are expected to adhere to a set of hostel rules and regulations.\n",true);
	 inst5.setText("A state of art gymnasium has been established to aid the physical fitness maintenance of the students. The gymnasium is equipped with all modern equipment and is open for both boys and girls at separate times during mornings and evenings.\n",true);
inst6.setText("As a healthy body leads to a healthy mind, so the Institute provides a dispensary which is a well-organized entity catering to around 2000 people (including 1700 students and 300 staff members).It provides all medical facilities and medicines regularly to the patients of the Institute during OPD hours (11:00am-2:00pm and 4:00pm-5:00pm) by a dispensary team comprising of Medical Officer incharge, Dr. L.K Sharma, MD (Medicine), a compounder, Shri Chandrakant and a hospital attendant Shri Sunder Lal Yadav. If an emergency occurs, it is treated immediately by the qualified medical officer, whenever being called upon. \n",true);
inst7.setText("IET, Lucknow has a large and efficient security force, offering its services to all the buildings, including the residential areas, the gates, etal. Security kiosks are present at accessible distance from all points in the campus. \n",true);
inst8.setText("The students have the facility of an on-campus bank, viz. Vijaya Bank Branch, which is a well- equipped, computerized branch, assuring instant deposits and withdrawls- saving a lot of precious time of the students.\n",true);
				
return rootView;

}
}
package com.example.departments;

import com.example.iet.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import adapter.TextViewEx;

public class Biotechnology extends Activity {
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
		setContentView(R.layout.biotech);
       mission=(TextViewEx)findViewById(R.id.mission);
       details=(TextViewEx)findViewById(R.id.details);
       d_news=(TextViewEx)findViewById(R.id.department_news);
       physical_infra=(TextViewEx)findViewById(R.id.physical_infra);
       q2=(TextViewEx)findViewById(R.id.q2);
       q3=(TextViewEx)findViewById(R.id.q3);
      
        mission.setText("Biotechnology is one of the most promising disciplines of engineering and technology that has been assessed as an area of prime importance and relevance by the government. The institute offers M.Tech. as well as Ph.D. programmes in Biotechnology. The Head of Biotechnology Department is Dr. B. N. Mishra.\n\n1.The M.Tech. in Biotechnology is full time two year course."+"\n\n2.The institute also offers Ph.D. programmes in Biotechnology.\n\n3.The students avail laboratory facilities of various renowned CSIR, ICAR and Universities research institutions in lucknow.\n\n4.Lectures of guest faculty and expert scientists are arranged regularly.\n\n5.The students also assist the faculty members in high quality research and development activities.",true);
        details.setText("The M.Tech Biotechnology programme is sanctioned and sponsored by All India Council for Technical Education, New Delhi. This is the only M.Tech. Biotechnology programme in entire U.P. state. The students with diverse subject background such as modern biology, engineering and technology are admitted to the course after a rigorous selection procedure at all India level. M.Tech. as well as Ph.D. Biotechnology programme of the institute is designed with a technological bias to produce dynamic biotechnologists who can meet the ever growing demands of the biotechnological industries. ",true);
        //details1.setText("1.B.Tech in Computer Science and Engineering(since 1984)\nTotal intake-60\nB.Tech in Information Technology(since 1998) \nTotal intake-60\nMaster of Computer Applications(since 1988)\nTotal intake-60");
	d_news.setText("Institute offers M. Tech. as well as Ph.D. Programmes in Biotechnology. Besides, Institute is also actively engaged in high quality research and development activities in the area of Biotechnology, Bioinformatics & Nanobiotechnology. The MHRD, Govt. of India under TEQIP scheme has recently given Centre of Excellence in Biotechnology. The Biotechnology research activities at Institute include site targeted drug designing, vaccine designing, gene therapy, secondary metabolites production using hairy root lines of medicinal plants & Bioreactor modeling simulation, control and optimization. Bioinformatics facility helps Biotechnology Department to horn the skills of researcher in developing novel procedures and protocols in the area of computational molecular biology and databases/ softwares development. Beside, Institute also provide general purpose information retrieval services to scientific community in the area of genomics, proteomics & Biotechnology. Such efforts are useful in developing strong networking with the institutions and industries. Institute is also rendering services to community & economy in the area of Biofertilizer, Sericulture and Aquaculture. ",true);
   physical_infra.setText("1. Equipments :\nUV-VIS Spectrophotometer, Centrifuge, Microscope, Laminar Flow Chamber, Autoclave, Incubator, Oven, Fume Chamber, Electronic Balance, Shakers, Deep Freezer, Muffle furnace, BOD Incubator, Colorimeter, Rotary Water Bath, Water Purification System. Besides, a number of sophisticated equipments worth Rs.75 Lacs are being procured under TEQIP/ AICTE project, Govt. of India.\n\n2. Databases/ Tools :\nThe following databases/ software tools have been developed by the Department of Biotechnology:\n(a).TepiPred: A novel software tool to predict conserved and promiscuous HLA-DR binding sites\n(b). PMVD: A database on plant mosaic viruses\n(c). CCPD: A database on promoters of cereal crops\n(d). PlantProteaseDB: A database on plant derived proteases\n(e). VTFBSS: A novel software to predict vertebrate encoding transcription factors\n(f). PromScanE: A software to extract promoters from eukaryotic systems\n\n3. Educational Software's :\nMATLAB, Statistical Softwares, Biochemistry Lectures Modules, Biotechnology Lectures Modules, Bioinformatics packages (Protein explorer, Bioedit, Treeview, all 3D viewers, Drug Designing softwares e.g. Gaussian, Hex, Autodoc & Argus Lab etc.).",true);
   q2.setText("Besides, the following R & D projects are underway:\n1. Quantum Pharmacological Studies of DHFR & TS inhibitors on Cancer chemotherapy.\n\nThe ongoing work deals with the identification of Quantum Pharmacological Studies of DHFR & TS inhibitors on Cancer chemotherapy and also to identify potential drug targets using machine-learning approaches for cancer chemotherapy.\n\n2. Analysis and development of in-silico tools for genome wide identification of vaccine candidates:\nThe proposed research work deals with the analysis and development of in-silico tool for genome wide identification of vaccine candidates against human pathogens.\n\n3.In-Silico Genome comparisons and Cis-Element/ Transcription Factor Statistical Analysis in Eco-friendly Bacteria:\n\nThe ongoing research work deals with the identification of known transcription factors and their DNA binding sites/ cis-elements in eco-friendly bacteria e.g. Nitrogen fixing bacteria (Rhizobia family), Biopolymer (polyhydroxyalkanoates; PHA) producing bacteria (Biodegradable Plastic or Green Plastic Biopolymer family) and radiation digestive bacteria e.g. Dienococcus radiodurons by using different algorithms & statistical approaches over >190 completely sequenced microbial genomes. ",true);
   q3.setText("The Centre of Excellence in Biotechnology at Department of Biotechnology of the Institute is actively engaged in providing training/ consultancy services in the area of Bioinformatics/ Biotechnology. Besides, number of R & D activities has been under taken and few software/ databases have already been developed. \n\n",true);
	
}
}


/**************************************
* http://vertical-scroller.vbarsan.com/
*    This notice may not be removed 
**************************************/

//-- Begin Scroller's Parameters and messages -->
//scroller's width
var swidth=280;

//scroller's height
var sheight=290;


//scroller's speed 
var sspeed=2;
var restart=sspeed;
var rspeed=sspeed;

//scroller's pause 
var spause=2000;

//scroller's background
var sbcolor="";

//messages: set your own; use as many as you'd like; set up Hyperlinks to
//URLs as you normally do: <a target=... href="... URL ...">..message..</a>;
var singletext=new Array();
singletext[0]='<table CELLSPACING="0" CELLPADDING="0" align="center"><tr><td valign="middle"><table align="center" width="300px"><tr><td><ul class="new_a" style="margin-top: 10px; text-align: left; margin: 0px ;padding: 20px;"><li><a target="_blank" href="pdf/iet_schedule_for_odd_sem_2013_14.pdf">Schedule for Theory Examination <br>(ODD Semester) 2013-14.</a><img src="images/new.gif" width="33" height="18" /></li><li><a href="result.htm">All Results are available in Result Link</a><img src="images/new.gif" width="33" height="18" /></li><li><a href="pdf/btech_passout_25oct13.pdf" target="_blank">Special Carry Over Exam 2013 Schedule Pass out Batches</a></li><li><a href="pdf/subjects_14oct13.pdf" target="_blank">Special Carry Over Exam 2013 Schedule First Year Subjects</a></li><li><a href="pdf/notice_08oct13.pdf" target="_blank">Scrutiny Challenge evaluation notice</a></li><li><a href="pdf/out_08oct13.pdf" target="_blank">Special carry over form pass out</a></li><li><a href="pdf/pass_08oct13.pdf" target="_blank">Special carry over notice pass out batch</a></li><li><a href="pdf/Special_carry_over_form_pass_out_8oct13.doc" target="_blank">Application Form For Special Carry Over Examination 2013 For Student Of Pass-Out Batches.</a></li><li><a href="pdf/special_carry_over_notice_8oct13.pdf" target="_blank">Regarding Notice for Special Carry Over Examination For Students Of Pass Out Batches Having Carry Over Paper </a></li><li><a href="pdf/notice_08102013.pdf" target="_blank">Notice regarding discrepancy in records submitted by Students for Scholarship. Part-III</a></li><li><a href="pdf/scholership_notice_25092013.pdf" target="_blank">Notice regarding discrepancy in records submitted by Students for Scholarship (Part-II).</a></li><li><a href="pdf/scholership_notice_07092013.pdf" target="_blank">Notice regarding discrepancy in records submitted by Students for Scholarship (Part-I).</a></li><li><a href="pdf/notice_24aug2013.pdf" target="_blank">Notice for Old students admit in the year 2009-10 regarding returning of fee</a> </li><li><a href="http://ietlucknow.edu/recruitment/frmregistration.aspx" target="_blank">Click here to apply online for Faculty position on contract basis</a> </li><li><a href="notice_6aug2013.pdf" target="_blank">Online Application for Walk in Interview for Faculty Position on Contract basis</a> </li><li><a href="notice_06aug2013.pdf" target="_blank">Walk in interview re-advertisment</a> </li><li><a href="notice_02082013.pdf" target="_blank">Registration of Students admitted through Counseling SEE 2013-14</a></li><li><a href="http://ietlucknow.edu/pdciet/login.asp" target="_blank">Download Provisional Degree Certificate</a></li><li><a href="pdf/office_memo_13072013.pdf" target="_blank">Schedule for Provisional Registration of B.Tech, MCA and MBA IIIrd (except B.Tech. direct IInd year admission done through UPSEE-2013), Vth & VIIth Semester.</a></li><li><a href="pdf/fee_structure2013_14_13072013.pdf" target="_blank">Fee Structure for the Session 2013-2014</a></li><li><a href="pdf/letter_iet_06072013.pdf" target="_blank">Regarding application for scholarship to SC/ST students, 2013-14.</a></li><li><a href="pdf/notice_06062013.pdf" target="_blank">Regarding postponement of interview for the post of director, I.E.T. Lucknow.</a></li><li><a href="pdf/bye_laws_04april13.pdf" target="_blank"><b>Bye Laws</b></a> </li><li><a href="pdf/service_rules_04april13.pdf" target="_blank"><b>Service Rules</b></a></li><li><a href="antiragging.htm"> Anti-Ragging Initiatives </a></li><li><a href="pdf/anti_ragging_affidavits.pdf" target="_blank"> Anti Ragging Affidavit</a></li><li><a href="pdf/aicte_antiragging_notice.pdf" target="_blank">AICTE NOTICE</a> </li><li><a href="pdf/dsw.pdf" target="_blank">Notice and Affidavit from DSW<li><a href="pdf/go_9june10.pdf" target="_blank"> Government Order for the U.P. Prohibition of Ragging in Educational Institutions Act,2010</a></li><li><a target="_blank" href="pdf/aicte_antiragging_notification.pdf"> AICTE NOTIFICATION</a></li><li><a href="pdf/anti-rag.pdf" target="_blank">UGC Regulations on curbing the meance of ragging in higher educational institutions.</a></li><li><a href="pdf/iet_profile.pdf" target="_blank">Institute Profile as Prescribed by MHRD</a></li><li><a href="rti.htm" target="_self">Right To Information</a></li><!--li><a href="pdf/feestructure08dec.pdf" target="_blank">Notice Regarding Fee for Social Welfare,SC,ST Students.</a></li--></ul></td></tr></table>';
//singletext[...]='...';
//-- end Parameters and message -->

//-- begin: Scroller's Algorithm -->
var ii=0;
function goup(){if(sspeed!=rspeed*16){sspeed=sspeed*2;restart=sspeed;}}

function start(){
if(document.getElementById){ns6div=document.getElementById('iens6div');ns6div.style.top=sheight+"px";ns6div.innerHTML=singletext[0];sizeup=ns6div.offsetHeight;ns6scroll();}
else 
if(document.layers){ns4layer=document.ns4div.document.ns4div1;ns4layer.top=sheight;ns4layer.document.write(singletext[0]);ns4layer.document.close();sizeup=ns4layer.document.height;ns4scroll();}
else 
if(document.all){iediv=iens6div;iediv.style.pixelTop=sheight+"px";iediv.innerHTML=singletext[0];sizeup=iediv.offsetHeight;iescroll();}}
function iescroll(){if(iediv.style.pixelTop>0&&iediv.style.pixelTop<=sspeed){iediv.style.pixelTop=0;setTimeout("iescroll()",spause);}else 
if(iediv.style.pixelTop>=sizeup*-1){iediv.style.pixelTop-=sspeed+"px";setTimeout("iescroll()",100);}else{if(ii==singletext.length-1)ii=0;else ii++;iediv.style.pixelTop=sheight+"px";iediv.innerHTML=singletext[ii];sizeup=iediv.offsetHeight;iescroll();}}
function ns4scroll(){if(ns4layer.top>0&&ns4layer.top<=sspeed){ns4layer.top=0;setTimeout("ns4scroll()",spause);}else 
if(ns4layer.top>=sizeup*-1){ns4layer.top-=sspeed;setTimeout("ns4scroll()",100);}else{if(ii==singletext.length-1)ii=0;else ii++;ns4layer.top=sheight;ns4layer.document.write(singletext[ii]);ns4layer.document.close();sizeup=ns4layer.document.height;ns4scroll();}}
function ns6scroll(){if(parseInt(ns6div.style.top)>0&&parseInt(ns6div.style.top)<=sspeed){ns6div.style.top=0;setTimeout("ns6scroll()",spause);}else 
if(parseInt(ns6div.style.top)>=sizeup*-1){ns6div.style.top=parseInt(ns6div.style.top)-sspeed+"px";setTimeout("ns6scroll()",100);}
else{if(ii==singletext.length-1)ii=0;else ii++;
ns6div.style.top=sheight+"px";ns6div.innerHTML=singletext[ii];sizeup=ns6div.offsetHeight;ns6scroll();}}
//-- end Algorithm -->

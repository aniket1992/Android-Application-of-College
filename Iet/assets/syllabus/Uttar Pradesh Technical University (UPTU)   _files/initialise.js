ddsmoothmenu.init({
	mainmenuid: "menu_area", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'menu_area', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})

function gototarget(target_url)
	{
		if(target_url != '#'){
	if(target_url.search('.asp') != -1 || target_url.search('.pdf') != -1)
		window.open(target_url);
	else
		window.location.href = target_url;
		
	}
	}
	function mtuMenu()
{
document.write('<table border="0" cellspacing="10" cellpadding="0" align="center"><tr><td><strong>Search Results</strong>:</td><td><select name="year" class="select_box" onChange="gototarget(this.value);"><option value="#">Select Results..</option><optgroup label="2013-2014"><option value="mtu_result.htm">Odd Semester Results 2013-14</option><option value="http://uptu.ac.in/results/gbturesult_11_12/MTU_result/Mtu_result.aspx">MTU Result Session (2010-11, 2011-12, 2012-13)</option></optgroup></option></select></td></tr></table>');
}
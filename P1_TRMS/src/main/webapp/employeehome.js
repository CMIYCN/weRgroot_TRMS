//name of the user is displayed in the title as well as in the header
function showName(empData){
	var webHeader = document.getElementById("empPageTitle");
	var welcome = document.getElementById("empTitle");
	webHeader.innerHTML = empData.name;
	welcome.innerHTML = "Welcome, "+empData.name+"!";
}
window.onload = function (){
	console.log("inEmployeeHome");
	
	var xhr = new XMLHttpRequest();
	//Step 2 function to handle onreadystatechange of response
	xhr.onreadystatechange=function(){
		//Need both conditionals!
		if(xhr.readyState == 4 && xhr.status == 200){
			var empData = JSON.parse(xhr.responseText);
			showName(empData);
		}
	}
	xhr.open("GET", "get-type", true);
	//Step 4 - Send request
	xhr.send();
}

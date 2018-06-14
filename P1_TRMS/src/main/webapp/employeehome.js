function showName(empData){
	var welcome = document.getElementById("empTitle");
	welcome.text = "Welcome, "+empData.name+"!";
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

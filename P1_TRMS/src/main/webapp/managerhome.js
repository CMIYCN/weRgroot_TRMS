function showName(empData){
	var managerHeader = document.getElementById("manPageTitle");
	var welcome = document.getElementById("managerTitle");
	managerHeader.innerHTML = empData.name;
	welcome.innerHTML = "Welcome, "+empData.name+"!";
}
window.onload = function (){
	console.log("inManagerHome");
	
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
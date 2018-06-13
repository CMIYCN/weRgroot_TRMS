var servletName = "getForms";

function displayData(data,url){
	var formID = parseInt(url.split("=")[1]);
	var row = document.getElementById("rmRow");
	var list = ["formID","eventID","empID","eventTime", "eventDate","location","description","cost","projectedReimbursement","urgent"];
	for(d in data){
		console.log(data[d]);
		if(data[d].formID === formID){
			list.forEach(function(field){
				var cell = document.createElement("td");
				var text = document.createTextNode(data[d][field]);
				cell.appendChild(text);
				row.appendChild(cell);
			})
		}
	}
}

	
window.onload = function (){
	var xhr = new XMLHttpRequest();
	//Step 2 function to handle onreadystatechange of response
	xhr.onreadystatechange=function(){
		//Need both conditionals!
		if(xhr.readyState == 4 && xhr.status == 200){
			var url = window.location.href;
			var data = JSON.parse(xhr.responseText);
			displayData(data, url);
		}
	}
	
	xhr.open("GET", servletName, true);
	//Step 4 - Send request
	xhr.send();
};
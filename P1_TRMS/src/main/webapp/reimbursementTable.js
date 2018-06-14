var servletName = "getForms";

function displayData(data,url){
	var formID = parseInt(url.split("=")[1]);
	var row = document.getElementById("rmRow");
	var list = ["formID","eventType","empID","eventTime", "eventDate","location","description","cost","projectedReimbursement","urgent"];
	var tblData;
	for(d in data){
		if(data[d].formID === formID){
			list.forEach(function(field){
				if(field === "cost" || field === "projectedReimbursement"){
					tblData = "$"+parseFloat(data[d][field]).toFixed(2);
				}else if(field === "eventType"){
					tblData = getEventType(data[d][field]);
				}
				else{
					tblData = data[d][field];
				}
				var cell = document.createElement("td");
				var text = document.createTextNode(tblData);
				cell.appendChild(text);
				row.appendChild(cell);
			})
		}
	}
	
	//add formID to form 
	var form = document.getElementById("form");
	var cell = document.createElement("input");
	cell.style.display = "none";
	cell.setAttribute("name", "formID");
	cell.setAttribute("value", data[0].formID);
	form.appendChild(cell);
}

function getEventType(eventid){
	switch(eventid){
	case 0:
		eventtype = "University Courses";
		break;
	case 1:
		eventtype = "Seminars";
		break;
	case 2:
		eventtype = "Certification Preparation Classes";
		break;
	case 3:
		eventtype = "Certification";
		break;
	case 4:
		eventtype = "Technical Training";
		break;
	case 5:
		eventtype = "Other";
		break;
	}
	return eventtype;
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
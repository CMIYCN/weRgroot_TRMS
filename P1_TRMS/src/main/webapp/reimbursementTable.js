var servletName = "getForms"

function displayData(data,url){
	var formID = parseInt(url.split("=")[1]);
	console.log(formID);
	var row = document.getElementById("rmRow");
	var list = ["formID","eventID","empID","eventTime", "eventDate","location","description","cost","projectedReimbursement","urgent"];
//	var buttons = ["Approve", "Deny"];
	for(d in data){
		if(data[d].formID === formID){
			list.forEach(function(field){
				var cell = document.createElement("td");
				var text = document.createTextNode(data[d][field]);
				cell.appendChild(text);
				row.appendChild(cell);
			})
		}
//		buttons.forEach(function(btn){
//			var btnCell = document.createElement("td");
//			var btn = document.createElement("input");
//			btn.setAttribute("type", "submit");
//			btn.setAttribute("value", btn);
//			btn.setAttribute("class", "btn btn-primary");
//			btnCell.appendChild(btn);
//			row.appendChild(btnCell);
//		})
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
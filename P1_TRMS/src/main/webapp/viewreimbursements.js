//
//function sendToSelectedForm() {
//	var formID = this.name;
//	//send ajax call with formID
//	var xhr = new XMLHttpRequest();
//	//Step 2 function to handle onreadystatechange of response
//	
//	xhr.onreadystatechange=function(){
//		if(xhr.readyState == 4 && xhr.status == 200){
//			var data = xhr.responseText;
//			console.log(data);
//		}
//	}
//	
//	xhr.open("POST", "get-form", true);
//	//xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
//	//Step 4 - Send request
//	xhr.send('formID='+formID);
//}
//	
function displayData(data){
	var tbl = document.getElementById("reimburse-tbl");
	var list = ["formID","eventID","empID","cost"];
	for(d in data){
		var row = document.createElement("tr");
		list.forEach(function(field){
			var cell = document.createElement("td");
			var text = document.createTextNode(data[d][field]);
			cell.appendChild(text);
			row.appendChild(cell);
		})
		var btnCell = document.createElement("td");
		
		//create form
		var form = document.createElement("form");
		form.setAttribute("method", "POST");
		form.setAttribute("action", "get-form?formID=" + data[d].formID);
		//create button
		var btn = document.createElement("input");
		btn.setAttribute("type", "submit");
		btn.setAttribute("value", "View");
		btn.setAttribute("class", "btn btn-primary");

		form.appendChild(btn);
		btnCell.appendChild(form);

		row.appendChild(btnCell);
		tbl.appendChild(row);
	}
}

	
window.onload = function (){
	var xhr = new XMLHttpRequest();
	//Step 2 function to handle onreadystatechange of response
	xhr.onreadystatechange=function(){
		//Need both conditionals!
		if(xhr.readyState == 4 && xhr.status == 200){
			var data = JSON.parse(xhr.responseText);
			displayData(data);
		}
	}
	xhr.open("GET", "getForms", true);
	//Step 4 - Send request
	xhr.send();
};
var servletName = "getForms"

function displayData(data){
	var tbl = document.getElementById("rmTable");
	var list = ["formID","eventID","emp_ID","cost"];
	for(d in data){
		var row = document.createElement("tr");
		list.forEach(function(field){
			var cell = document.createElement("td");
			var text = document.createTextNode(data[d][field]);
			cell.appendChild(text);
			row.appendChild(cell);
		})
		var btnCell = document.createElement("td");
		var btn = document.createElement("input");
		btn.setAttribute("type", "submit");
		btn.setAttribute("value", "View");
		btn.setAttribute("class", "btn btn-primary");
		btnCell.appendChild(btn);
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
	
	xhr.open("GET", servletName, true);
	//Step 4 - Send request
	xhr.send();
};
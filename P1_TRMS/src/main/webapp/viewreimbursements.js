var servletName = "getForms"

//function getRegError(){
//	var xhr = new XMLHttpRequest();
//		//Step 2 function to handle onreadystatechange of response
//	xhr.onreadystatechange=function(){
//		console.log("Roll Tide1");
//		//Need both conditionals!
//		if(xhr.readyState == 4 && xhr.status == 200){
//			var data = JSON.parse(xhr.responseText);
//		}
//	}
//	
//	xhr.open("GET", servletName, true);
//	//Step 4 - Send request
//	xhr.send();
//};
function displayData(data){
	var tbl = document.getElementById("reimburse-tbl");
	var list = ["formID","eventID","emp_ID","cost"];
	for(d in data){
		var row = document.createElement("tr");
		list.forEach(function(field){
			console.log(field);
			var cell = document.createElement("td");
			var text = document.createTextNode(data[d][field]);
			cell.appendChild(text);
			row.appendChild(cell);
		})
		tbl.appendChild(row);
	}
}

	
window.onload = function (){
	console.log("in onLoad");
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
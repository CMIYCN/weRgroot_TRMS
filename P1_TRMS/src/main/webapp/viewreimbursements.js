var count = 0;
var tableData;
var positionType;

function displayData(){
	var tbl = document.getElementById("reimburse-tbl");
	var list = ["formID","eventType","empID","cost", "projectedReimbursement"];//,"supervisorApproval", "departmentApproval", "bencoApproval"];
	for(d in tableData){
		switch(positionType){
			case 0:
				row = addRow(d, list);
				tbl.appendChild(row);
				break;
			case 1:
				if(!tableData[d].supervisorApproval){
					row = addRow(d, list);
					tbl.appendChild(row);
				}
				break;
			case 2:
				if(!tableData[d].departmentApproval){
					row = addRow(d, list);
					tbl.appendChild(row);
				}
				break;
			case 3:
				if(!tableData[d].bencoApproval){
					row = addRow(d, list);
					tbl.appendChild(row);
				}
				break;
			case 4:
				row = addRow(d, list);
				tbl.appendChild(row);
				break;
		}
	}
}

function addRow(index, list){
	var row = document.createElement("tr");
	list.forEach(function(field){
		var cell = document.createElement("td");
		var text = document.createTextNode(tableData[index][field]);
		cell.appendChild(text);
		row.appendChild(cell);
	})
	
	
	superApprove = tableData[index].supervisorApproval;
	headApprove = tableData[index].departmentApproval;
	bencoApprove = tableData[index].bencoApproval;
	if(positionType === 1 || positionType===2 || positionType===3 ||positionType===4){
		var btnCell = document.createElement("td");
		
		//create form
		var form = document.createElement("form");
		form.setAttribute("method", "POST");
		form.setAttribute("action", "get-form?formID=" + tableData[d].formID);
		//create button
		var btn = document.createElement("input");
		btn.setAttribute("type", "submit");
		btn.setAttribute("value", "View");
		btn.setAttribute("class", "btn btn-primary");
	
		form.appendChild(btn);
		btnCell.appendChild(form);
	
		row.appendChild(btnCell);
	}else{
		document.getElementById("emptyHeader").innerHTML = "Status";
		if(superApprove && headApprove && bencoApprove){
			var cell = document.createElement("td");
			var text = document.createTextNode("Approved");
			cell.appendChild(text);
			row.appendChild(cell);
		}
		else if(superApprove==-1 || headApprove==-1 || bencoApprove==-1){
			var cell = document.createElement("td");
			var text = document.createTextNode("Denied");
			cell.appendChild(text);
			row.appendChild(cell);
		}
		else{
			var cell = document.createElement("td");
			var text = document.createTextNode("Pending");
			cell.appendChild(text);
			row.appendChild(cell);
		}
	}
	return row;
}

function getPosition(){
	var xhrPosition = new XMLHttpRequest();
	xhrPosition.onreadystatechange=function(){
		if(xhrPosition.readyState == 4 && xhrPosition.status == 200){
			count++;
			positionType = JSON.parse(xhrPosition.responseText);
			if(count===2){
				displayData();
			}
		}
	}
	xhrPosition.open("GET", "get-type", true);
	//Step 4 - Send request
	xhrPosition.send();
}
	
window.onload = function (){
	console.log("woo");
	getPosition();
	var xhr = new XMLHttpRequest();
	//Step 2 function to handle onreadystatechange of response
	xhr.onreadystatechange=function(){
		//Need both conditionals!
		if(xhr.readyState == 4 && xhr.status == 200){
			count++;
			tableData = JSON.parse(xhr.responseText);
			if(count===2){
				displayData();
			}
		}
	}
	xhr.open("GET", "getForms", true);
	//Step 4 - Send request
	xhr.send();
};
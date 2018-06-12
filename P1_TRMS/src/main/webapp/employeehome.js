var servletName = "view-reimbursements";
function getReimbursements(){
	var xhr = new XMLHttpRequest();
	//Step 2 function to handle onreadystatechange of response
	xhr.onreadystatechange=function(){
	console.log("Roll Tide1");
	//Need both conditionals! Or not because it doesn't work with servlets T_T
	if(xhr.readyState == 4 && xhr.status==200){
		var data = xhr.response;
	}
}

xhr.open("GET", servletName, true);
//Step 4 - Send request
xhr.send();
}

window.onload = function (){
	console.log("in onLoad");
	//document.getElementById("view-btn").addEventListener("click", getReimbursements, false);
}

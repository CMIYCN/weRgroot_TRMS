var errorPage = "error";
var errorField = "registerError";
var servletName = "error";

function getLoginError(){
	var xhr = new XMLHttpRequest();
		//Step 2 function to handle onreadystatechange of response
	xhr.onreadystatechange=function(){
		console.log("Roll Tide1");
		//Need both conditionals! Or not because it doesn't work with servlets T_T
		if(xhr.readyState == 4){
			console.log(xhr.status);
			var errorFlag = JSON.parse(xhr.responseText);
			console.log(errorFlag);
			addErrorMessage(errorFlag);
		}
	}
	
	xhr.open("GET", servletName, true);
	//Step 4 - Send request
	xhr.send();
};

function addErrorMessage(errorFlag){
	switch(errorFlag){
		case 0:
			var errorElement = document.getElementById(errorField);
			errorElement.innerHTML="Please try again.";
			break;
	}
};

window.onload = function (){
	console.log("in onLoad");
	document.getElementsByName("login-btn")[0].addEventListener("click", getLoginError, false);
}
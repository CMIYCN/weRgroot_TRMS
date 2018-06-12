var errorPage = "error";
var errorField = "registerError";

function getRegError(){
	var xhr = new XMLHttpRequest();
		//Step 2 function to handle onreadystatechange of response
	xhr.onreadystatechange=function(){
		console.log("Roll Tide1");
		//Need both conditionals!
		if(xhr.readyState == 4 && xhr.status == 100){
			var errorFlag = JSON.parse(xhr.responseText);
			alert(errorFlag);
			addErrorMessage(errorFlag);
		}
	}
	
	xhr.open("GET", "error", true);
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

//function getJSON(){
//	console.log("in getJSON");
//	//var pokemonId1 = pokemonId;
//	//Step 1: 
//	var xhr = new XMLHttpRequest();
//	//Step 2 function to handle onreadystatechange of response
//	xhr.onreadystatechange=function(){
//		console.log("Roll Tide1");
//		//Need both conditionals!
//		if(xhr.readyState == 4 && xhr.status == 200){
//			console.log(xhr.responseText);
//			console.log(JSON.parse(xhr.responseText));
//		}
//	}
//	xhr.open("GET", "error",true);
//	//Step 4 - Send request
//	xhr.send();
//}
window.onload = function (){
	console.log("in onLoad");
	document.getElementsByName("register-btn")[0].addEventListener("click", getRegError, false);
}
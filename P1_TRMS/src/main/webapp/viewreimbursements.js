var servletName = "error"

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

window.onload = function (){
	console.log("in onLoad");
	var xhr = new XMLHttpRequest();
	//Step 2 function to handle onreadystatechange of response
	xhr.onreadystatechange=function(){
		//Need both conditionals!
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("Happy times")
			var data = xhr.responseText;
			console.log(data);
		}
	}
	
	xhr.open("GET", servletName, true);
	//Step 4 - Send request
	xhr.send();
};
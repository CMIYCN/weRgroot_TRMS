function register() {
	//convert form fields to json and send post connection
	//Step 1: Create/Open
	var xhr = new XMLHttpRequest();
	//Step 2: Define onreadystatechange function for response
	xhr.onreadystatechange = function() {
		//the response is in the ready state of 4 and the status of the response is a 200"all good"
		if(xhr.readyState == 4 && xhr.status == 200) {
			quotes = JSON.parse(xhr.responseText);
			displayQuotes(quotes);
		}
	}
	//Step 3: Open request/connection
	xhr.open("POST", "http://ron-swanson-quotes.herokuapp.com/v2/quotes", true);
	//Step 4: Send the request
	xhr.send();
}

window.onload = function() {
	document.getElementById("btn-register").addEventListener("click", register, true);
}
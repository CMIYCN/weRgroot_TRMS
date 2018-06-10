//function firstStep(){
//	var pokemonId1 = document.getElementById("pokeId1").value;
//	var poke1 = getPokemon(pokemonId1);
//	//loadPokemon(poke1,poke2);
//}
//
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
//window.onload = function (){
//	console.log("in onLoad");
//	document.getElementsByName("register-btn")[0].addEventListener("click", getJSON, false);
//}
window.onload = () =>{
	let employee = JSON.parse(sessionStorage.getItem("employee"));
	console.log(employee.firstname);
	
	 document.getElementById("a1").innerHTML = employee.firstName;
	 document.getElementById("a2").innerHTML = employee.lastName;
	 document.getElementById("a3").innerHTML = employee.email;
	 

	
	 
}
window.onload = () =>{
	 employee = JSON.parse(sessionStorage.getItem("employee"));
	 eid = employee.id;
	 console.log('Employe id is eid');

	 document.getElementById("a1").innerHTML = employee.firstName;
	 document.getElementById("a2").innerHTML = employee.lastName;
	 document.getElementById("a3").innerHTML = employee.email;
	 document.getElementById("a4").innerHTML =employee.firstName;
	
	 
	    document.getElementById("pending").addEventListener("click", () => {
sessionStorage.setItem(eid);
window.location ="multipleRequests.do?x="  + eid;
	    });
	}


	
	 

	
	 

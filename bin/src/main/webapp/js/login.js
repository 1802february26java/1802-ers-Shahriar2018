window.onload = () =>{

    //redirect user to the right URL if he comes from somewhere else

    if(window.location.pathname !== '/ERS/login.do'){
       window.location.replace('login.do');
    }

    /** **/
    //Login event listener
    document.getElementById("login").addEventListener("click", ()=>{
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;

        //AJAX Logic
        let xhr = new XMLHttpRequest();

        xhr.onreadystatechange = () => {
            if(xhr.readyState === XMLHttpRequest.DONE && xhr.status ===200){
                //Getting JSON from response body
                let data = JSON.parse(xhr.responseText);
                //console.log(data);   

                //Call login response processing
                login(data);
            }
        };
          //Doing a HTTP to a specifc endpoint
          xhr.open("POST",`login.do?username=${username}&password=${password}`);
   
     //Sending our request
     xhr.send();

    })
}

function login(data) {
     
    //If message is a member of the JSON, it was AUTHENTICATION FAILED
      if(data.message){
          document.getElementById("loginMessage").innerHTML = '<span class="label label-danger label-center">Wrong credentials.</span>';
      }
      else{
          //Using sessionStorage of Javascript
          sessionStorage.setItem("id", data.id);
          sessionStorage.setItem("firstName", data.firstName);
          sessionStorage.setItem("lastName",data.lastName);
          sessionStorage.setItem("username", data.username);
          sessionStorage.setItem("password",data.password);
          sessionStorage.setItem("email",data.email);
          sessionStorage.setItem("employeeRole",JSON.stringify(data.employeeRole));

          //check if I can retrive employee role correctly or not
        //   let temp = sessionStorage.getItem('employeeRole');
        //   let viewEmployeeRole = JSON.parse(temp);
        //   console.log(viewEmployeeRole.id);
        //   console.log(viewEmployeeRole.type);
         
          
          window.location.replace("home.do");
      }
}
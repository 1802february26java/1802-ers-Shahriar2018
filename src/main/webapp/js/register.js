window.onload = () => {
    //Register Event Listener
    document.getElementById("submit").addEventListener("click", () => {
        //Check passwords are the same
        let password = document.getElementById("password").value;
        let repeatPassword = document.getElementById("repeatPassword").value;
        if(password !== repeatPassword) {
            document.getElementById("registrationMessage").innerHTML = '<span class="label label-danger label-center">Password mismatch.</span>';
            return;
        }
        
        //Get the rest fields
        let firstName = document.getElementById("firstName").value;
        let lastName = document.getElementById("lastName").value;
        let username = document.getElementById("username").value;
        
        //AJAX Logic
        let xhr = new XMLHttpRequest();
        
        xhr.onreadystatechange = () => {
            //If the request is DONE (4), and everything is OK
            if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                //Getting JSON from response body
                let data = JSON.parse(xhr.responseText);
                console.log(data);

                //Call registration response processing
                register(data);
            }
        };

        //Doing a HTTP to a specific endpoint
        xhr.open("POST",`register.do?firstName=${firstName}&lastName=${lastName}&username=${username}&password=${password}`);

        //Sending our request
        xhr.send();
    });
}

function disableAllComponents() {
    document.getElementById("firstName").setAttribute("disabled","disabled");
    document.getElementById("lastName").setAttribute("disabled","disabled");
    document.getElementById("username").setAttribute("disabled","disabled");
    document.getElementById("password").setAttribute("disabled","disabled");
    document.getElementById("repeatPassword").setAttribute("disabled","disabled");
    document.getElementById("submit").setAttribute("disabled","disabled");
}

function register(data) {
    //If message is a member of the JSON, something went wrong
    if(data.message === "REGISTRATION SUCCESSFUL") {
        //Confirm registration and redirect to login
        disableAllComponents();
        document.getElementById("registrationMessage").innerHTML = '<span class="label label-success label-center">Registration successful.</span>';
        setTimeout(() => { window.location.replace("login.do"); }, 3000);
    }
    //Something went wrong
    else {
        document.getElementById("registrationMessage").innerHTML = '<span class="label label-danger label-center">Something went wrong.</span>';
    }
}
window.onload = () => {
    //Get Event Listener
    document.getElementById("getCustomers").addEventListener("click", getAllCustomers);

    //Get all customers as soon as the page loads
    getAllCustomers();
}

function getAllCustomers() {
    //AJAX Logic
    let xhr = new XMLHttpRequest();
        
    xhr.onreadystatechange = () => {
        //If the request is DONE (4), and everything is OK
        if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            //Getting JSON from response body
            let data = JSON.parse(xhr.responseText);
            console.log(data);

            //Present the data to the user
            presentCustomers(data);
        }
    };

    //Doing a HTTP to a specific endpoint
    xhr.open("GET",`getAll.do?fetch=LIST`);

    //Sending our request
    xhr.send();
}

function presentCustomers(data) {
    //If message is a member of the JSON, something went wrong
    if(data.message) {
        document.getElementById("listMessage").innerHTML = '<span class="label label-danger label-center">Something went wrong.</span>';
    }
    //We present all the customers to the user
    else {
        //Get customer list node
        let customerList = document.getElementById("customerList");

        //Clean customer list
        customerList.innerHTML = "";

        //Iterate over all customers
        data.forEach((customer) => {
            //Creating node of <li>
            let customerNode = document.createElement("li");

            //Add class for styling <li class="something">
            //You can access any HTML fields (id might be useful)
            customerNode.className = "list-group-item";

            //Creating innerHTML object, adding customer name to it.
            //<li class="something">[creating this object]</li>
            let customerNodeText = document.createTextNode(`${customer.lastName}, ${customer.firstName}`);

            //Append innerHTML to the customerNode
            //<li class="something">Perez, Julio</li>
            customerNode.appendChild(customerNodeText);

            //Finally, we append the new customerNode to the customerList
            //<ul id="customerList">
            //<li class="something">Perez, Julio</li>
            //</ul>
            customerList.appendChild(customerNode);
        });
    }
}
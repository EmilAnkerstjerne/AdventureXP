function postItem (name, price){
    document.getElementById("namereq").innerText = "";
    document.getElementById("pricereq").innerText = "";
    document.getElementById("price").placeholder="";

    if(document.getElementById("name").value!=="" && document.getElementById("price").value!==""){
        let xhr = new XMLHttpRequest();
        let url = "http://localhost:8080/newStoreItem";
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        let data = JSON.stringify({"name": name,"price": price});
        xhr.send(data);
        setTimeout(() => {window.location.reload();}, 50);
    } else if(document.getElementById("name").value==="" && document.getElementById("price").value===""){
        //document.getElementById("namereq").innerText = "Name required";
        //document.getElementById("pricereq").innerText = "Price required";
        document.getElementById("name").placeholder = "Name required";
        document.getElementById("price").placeholder = "Price required";
    } else if(document.getElementById("name").value===""){
        //document.getElementById("namereq").innerText = "Name required";
        document.getElementById("name").placeholder = "Name required";
    } else if(document.getElementById("price").value===""){
        //document.getElementById("pricereq").innerText = "Price required";
        document.getElementById("price").placeholder = "Price required";
    }
}


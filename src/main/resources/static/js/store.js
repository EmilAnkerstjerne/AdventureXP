
let getUrl = "http://localhost:8080/store/item/all"
let deleteUrl = "http://localhost:8080/store/deleteItem/";
let postUrl = "http://localhost:8080/newStoreItem";
let xhr = new XMLHttpRequest();


let getRequest = {
    method: "GET",
    headers: {
        "content-type": "application/json"
    },
    redirect: "follow"
}

let deleteRequest = {
    method: "DELETE",
    headers: {
        "content-type": "application/json"
    },
    redirect: "follow"
}


let masterDiv = document.getElementById("item-list-div");

function generateHTMLList(item){
    let div = document.createElement("div");
    div.className = "itemListing";

    let name = document.createElement("span");
    name.innerHTML = item.name;

    let hiddenName = document.createElement("input");
    hiddenName.value = item.name;
    hiddenName.className = "edit-name-input";


    let price = document.createElement("span");
    price.innerHTML = "Price: " + item.price + ",-";

    let buttonsDiv = document.createElement("div");
    buttonsDiv.style.display ="inline-block";

    let editButton = document.createElement("button");
    editButton.className = "edit-save-btn"
    editButton.innerHTML = "Edit";
    editButton.id="edit-btn";
    editButton.onclick =
        function editItem(){
        saveButton.style.display = "inline-block";
        editButton.style.display = "none";
        hiddenName.style.display = "inline-block";
        name.style.display = "none";
        };

    let saveButton = document.createElement("button");
    saveButton.className = "edit-save-btn"
    saveButton.innerHTML = "Save";
    saveButton.style.display = "none";
    saveButton.id = "edit-save"
    saveButton.onclick =
        function saveItem(){
        editButton.style.display = "inline-block";
        saveButton.style.display = "none";
            hiddenName.style.display = "none";
            name.style.display = "inline-block";
            xhr.open("POST", postUrl, true);
            xhr.setRequestHeader("Content-Type", "application/json");
            let data = JSON.stringify({"id": item.id,"name": hiddenName.value,"price": item.price});
            xhr.send(data);
            name.innerHTML = hiddenName.value;
        };

    let deleteButton = document.createElement("button");
    deleteButton.innerHTML = "Delete";
    deleteButton.onclick =
        function deleteItem(){
            if(confirm("Are you sure you want to delete " + item.name + "?")){
                fetch(deleteUrl + item.id, deleteRequest).
                catch((error) => console.log("fejl"));
                setTimeout(() => {window.location.reload();}, 50);
            }
        }

    div.appendChild(name);
    div.appendChild(hiddenName);
    div.appendChild(document.createElement("br"));
    div.appendChild(price);
    div.appendChild(document.createElement("br"));
    div.appendChild(buttonsDiv);
    buttonsDiv.appendChild(editButton);
    buttonsDiv.appendChild(saveButton);
    buttonsDiv.appendChild(deleteButton);

    masterDiv.appendChild(div);
}


function loadItems(){
    fetch(getUrl, getRequest)
        .then(response => response.json())
        .then(data => loadedItems(data))
        .catch((error) => console.log("error"));
}

function loadedItems(data){
    data.forEach(item => generateHTMLList(item));
}


// POST ITEM
function postItem (name, price){
    document.getElementById("namereq").innerText = "";
    document.getElementById("pricereq").innerText = "";
    document.getElementById("price").placeholder="";

    if(document.getElementById("name").value!=="" && document.getElementById("price").value!==""){
        xhr.open("POST", postUrl, true);
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

/*
function editItem(){
    document.getElementById("edit-save").style.display = "block";
    document.getElementById("edit-btn").style.display = "none";


    document.getElementById("item-name").style.display = "none";
    document.getElementById("edit-name").style.display = "block";
}
function saveItem(){
    document.getElementById("edit-save").style.display = "none";
    document.getElementById("editBtn").style.display = "block";

    document.getElementById("item-name").style.display = "block";
    document.getElementById("edit-name").style.display = "none";
}


function delItem(id, name){
    if(confirm("Are you sure you want to delete " + name + "?")){
        fetch(deleteUrl + id, deleteRequest).
        catch((error) => console.log("fejl"));
        setTimeout(() => {window.location.reload();}, 50);
    }
}
*/

loadItems();
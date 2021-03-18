let masterDiv = document.getElementById("master-div");
//--Requests--
let deleteRequest = {
    method: "DELETE",
    headers: {
        "content-type": "application/json"
    },
    redirect: "follow"
}
let postRequest = {
    method: "POST",
    headers: {
        "content-type": "application/json"
    },
    redirect: "follow"
}
let getRequest = {
    method: "GET",
    headers: {
        "content-type": "application/json"
    },
    redirect: "follow"
}
//---------
//--JsonObjects--
let object = {
    name: "Gokart",
    duration: 10
}
//---------
//--Urls--
let deleteUrl = "http://localhost:8080/activities/deleteActivity/"
let getUrl = "http://localhost:8080/activities/all"
//---------
//--Buttons--

//---------
//--EventListeners--

//---------
//--Functions--

function deleteAct(id){
    if(confirm("Are you sure you want to delete this activity?")){
        fetch(deleteUrl + id, deleteRequest).
        catch((error) => console.log("fejl"));
        setTimeout(reloadPage, 50);
    }
}

function createHTML(activity){
    let br = document.createElement("br");
    let div = document.createElement("div");
    div.className = "activity-div";

    let name = document.createElement("h2");
    name.innerHTML = activity.name;
    div.appendChild(name);

    let maksPers = document.createElement("span");
    maksPers.innerHTML= "Maks " + activity.maxParticipants + " personer.";
    div.appendChild(maksPers);
    div.appendChild(br);

    let description = document.createElement("span");
    description.innerHTML = activity.description;
    div.appendChild(description);
    div.appendChild(br);

    let editButton = document.createElement("button");
    editButton.className = "btn editActivity";
    editButton.setAttribute("onclick", "");
    editButton.innerHTML = "Edit activity";
    div.appendChild(editButton);

    let deleteButton = document.createElement("button");
    deleteButton.className = "btn deleteActivity";
    deleteButton.onclick = function(){deleteAct(activity.id);};
    deleteButton.innerHTML = "Delete activity";
    div.appendChild(deleteButton);

    masterDiv.appendChild(div);

}

function loadActivities(){
    fetch(getUrl, getRequest)
        .then(response => response.json())
        .then(data => loadedActivities(data))
        .catch((error) => console.log("error"));
}

function loadedActivities(data){
    data.forEach(activity => createHTML(activity));
}

function insertActivities(activity){

}

function reloadPage(){
    location.reload();
}
//---------
loadActivities();

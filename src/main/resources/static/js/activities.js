let masterDiv = document.getElementById("master-div");
//--Requests--

let deleteRequest = {
    method: "DELETE",
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

let activityToSend = {
    "id": "",
    "name": "",
    "maxParticipants": "",
    "description": "",
    "minDurationHours": ""
}

let body1 = JSON.stringify(activityToSend);

let postRequest = {
    method: "POST",
    headers: {
        "content-type": "application/json"
    },
    body: body1
}

//--Urls--
let deleteUrl = "http://localhost:8080/activities/deleteActivity/"
let getUrl = "http://localhost:8080/activities/all"
let postUrl = "http://localhost:8080/activities/create/save"
//---------
//--Buttons--

//---------
//--EventListeners--

//---------
//--Functions--

function deleteAct(id, actName){
    if(confirm("Are you sure you want to delete " + actName + "?")){
        let reload = true;
        fetch(deleteUrl + id, deleteRequest).
        then(response => {
            if(response.status === 500){
            reload = false
            alert("Can't delete " + actName + " as reservations are still attached.")
            }
        }).
        catch((error) => console.log("error: " + error));
        setTimeout(reloadPage, 60)
    }
}

function createHTML(activity){
    let div = document.createElement("div");
    div.className = "activity-div";

    let name = document.createElement("h2");
    name.innerHTML = activity.name;
    let nameInp = document.createElement("input");
    nameInp.style.display = "none";
    nameInp.style.textAlign = "center";
    nameInp.style.margin = "auto";
    nameInp.value = name.innerHTML;
    div.appendChild(name);
    div.appendChild(nameInp);
    //div.appendChild(document.createElement("br"));

    let span1 = document.createElement("span");
    span1.innerHTML = "Maks ";
    let span2 = document.createElement("span")
    span2.innerHTML = " personer.";
    let maksPers = document.createElement("span");
    maksPers.innerHTML= activity.maxParticipants;
    let maksPersInp = document.createElement("input");
    maksPersInp.style.width = "30px";
    maksPersInp.type = "number";
    maksPersInp.style.display = "none";
    maksPersInp.value = maksPers.innerHTML;
    div.appendChild(span1);
    div.appendChild(maksPers);
    div.appendChild(maksPersInp);
    div.appendChild(span2);
    div.appendChild(document.createElement("br"));

    let span3 = document.createElement("span");
    span3.innerHTML = "Minimum spilletid: ";
    let span4 = document.createElement("span")
    span4.innerHTML = " timer.";
    let minDuration = document.createElement("span");
    minDuration.innerHTML = activity.minDurationHours;
    let minDurationInp = document.createElement("input");
    minDurationInp.style.width = "25px";
    minDurationInp.type = "number";
    minDurationInp.style.display = "none";
    minDurationInp.value = minDuration.innerHTML;
    div.appendChild(span3);
    div.appendChild(minDuration);
    div.appendChild(minDurationInp);
    div.appendChild(span4);
    div.appendChild(document.createElement("br"));

    let description = document.createElement("span");
    description.innerHTML = activity.description;
    let descriptionInp = document.createElement("textarea");
    descriptionInp.style.display = "none";
    descriptionInp.style.width = "50%";
    descriptionInp.style.height = "100px";
    descriptionInp.value = description.innerHTML;
    div.appendChild(description);
    div.appendChild(descriptionInp)
    div.appendChild(document.createElement("br"));

    let saveButton = document.createElement("button");
    saveButton.className = "btn saveActivity";
    saveButton.onclick = function(){
        activityToSend.id = activity.id;
        activityToSend.name = nameInp.value;
        activityToSend.maxParticipants = maksPersInp.value;
        activityToSend.minDurationHours = minDurationInp.value;
        activityToSend.description = descriptionInp.value;

        body1 = JSON.stringify(activityToSend);
        postRequest.body = body1;

        fetch(postUrl, postRequest).then(response => console.log(response));

        name.innerHTML = nameInp.value;
        name.style.display = "";
        nameInp.style.display = "none";

        maksPers.innerHTML = maksPersInp.value;
        maksPers.style.display = "inline-block";
        maksPersInp.style.display = "none";

        minDuration.innerHTML = minDurationInp.value;
        minDuration.style.display = "inline-block";
        minDurationInp.style.display = "none";

        description.innerHTML = descriptionInp.value;
        description.style.display = "inline-block";
        descriptionInp.style.display = "none";

        saveButton.style.display = "none";
        editButton.style.display = "inline-block";



    };
    saveButton.innerHTML = "Save activity";
    saveButton.style.display = "none";
    div.appendChild(saveButton);


    let editButton = document.createElement("button");
    editButton.className = "btn editActivity";
    editButton.value = activity.id;
    editButton.onclick = function(){
        name.style.display = "none";
        nameInp.style.display = "block";

        maksPers.style.display = "none";
        maksPersInp.style.display = "inline-block";

        minDuration.style.display = "none";
        minDurationInp.style.display = "inline-block";

        description.style.display = "none";
        descriptionInp.style.display = "inline-block";

        editButton.style.display = "none";
        saveButton.style.display = "inline-block";

    };
    editButton.innerHTML = "Edit activity";
    div.appendChild(editButton);

    let deleteButton = document.createElement("button");
    deleteButton.className = "btn deleteActivity";
    deleteButton.onclick = function(){deleteAct(activity.id, activity.name);};
    deleteButton.innerHTML = "Delete activity";
    div.appendChild(deleteButton);

    masterDiv.appendChild(div);

}


function loadActivities(){
    fetch(getUrl, getRequest)
        .then(response => response.json())
        .then(data => loadedActivities(data))
        .catch((error) => console.log("error: " + error));
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

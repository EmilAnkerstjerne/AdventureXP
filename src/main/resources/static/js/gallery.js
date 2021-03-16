const gridCon = document.querySelector(".grid-container");

const requestObject = {
    method: "GET",
    "content-type": "application/json",
    redirect: "follow"
}

const url = "/activities/all";

function makeGridItem(activity){
    console.log(activity);
    const gridItem = document.createElement("div");
    gridItem.className = "grid-item";

    const name = document.createElement("h2");
    name.className = "activity-name";

    const nameCon = document.createTextNode(activity.name);
    name.appendChild(nameCon);
    gridItem.appendChild(name);

    const desc = document.createElement("p");
    desc.className = "activity-desc";

    const descCon = document.createTextNode(activity.description);
    desc.appendChild(descCon);
    gridItem.appendChild(desc);

    gridCon.appendChild(gridItem);
}

function gotActivityData(data){
    console.log(data);
    data.forEach(activity => makeGridItem(activity));
}

function loadActivities(){
    fetch(url, requestObject)
        .then(response => response.json())
        .then(data => gotActivityData(data))
        .catch((error) => console.log("Ups"));
}

loadActivities();
let billy = "Billy";

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

    const dur = document.createElement("p");
    dur.className = "activity-duration";

    const durCon = document.createTextNode("Min duration: " + activity.minDurationHours + " hour(s)")
    dur.appendChild(durCon);
    gridItem.appendChild(dur);

    const part = document.createElement("p");
    part.className = "activity-participants";

    const partCon = document.createTextNode("Max participants: " + activity.maxParticipants + " alien(s)");
    part.appendChild(partCon);
    gridItem.appendChild(part);

    const bookBtn = document.createElement("a");
    bookBtn.className = "bookingBtn";
    bookBtn.setAttribute("value", activity.id);

    const bookBtnCon = document.createTextNode("Book");
    bookBtn.appendChild(bookBtnCon);
    gridItem.appendChild(bookBtn);

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

function book(ev){
    let el = ev.target;

    if (el.className === "bookingBtn"){
        let id = el.getAttribute("value");

        fetch("/scopedproxy/form/" + id, requestObject)
            .then(() => window.location.href = "/booking")
    }
}

document.addEventListener("click", ev => book(ev));
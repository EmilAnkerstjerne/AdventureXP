let billy = "Billy";

const resCon = document.querySelector(".res-con");
const resItems = () => {return document.querySelectorAll(".res-box")};

const resId = document.querySelector(".form-id");
const resTitle = document.querySelector(".form-name");
const resSTime = document.querySelector(".startTime");
const resETime = document.querySelector(".endTime");
const dateInp = document.querySelector(".date-inp");

const saveBtn = document.querySelector(".saveBtn");
const deleteBtn = document.querySelector(".deleteBtn");
const warn = document.querySelector(".warn");

//---------------------------------------------------------------------------------------------------------------------

const requestObjectGet = {
    method: "GET",
    "content-type": "application/json",
    redirect: "follow"
}

let reservation = {
    id: 0,
    startTime: "",
    endTime: "",
    date: ""
}

let body1 = JSON.stringify(reservation);

let requestOptionsPut = {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'PUT',
    body: body1
};

let requestOptionsDelete = {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'DELETE'
};

const activityUrl = "/activities/"; // "/activities/{id}"
const reservationsUrl = "/booking/myReservations";
const editReservationUrl = "/booking/edit";
const deleteReservationUrl = "/booking/delete/"; // "/booking/delete/{id}"

//---------------------------------------------------------------------------------------------------------------------

function resClick(ev){
    let el = ev.target;
    if (el.className === "res-box"){
        saveBtn.disabled = false;
        deleteBtn.disabled = false;

        resItems().forEach(item => item.style.backgroundColor = "#fed767");
        el.style.backgroundColor = "#92e3ff";

        resId.value = el.getElementsByTagName('input')[0].value;
        resTitle.value = el.getElementsByTagName('h2')[0].innerHTML;
        resSTime.value = el.getElementsByTagName('input')[2].value.split("-")[0];
        resETime.value = el.getElementsByTagName('input')[2].value.split("-")[1];
        dateInp.value = el.getElementsByTagName('input')[1].value;
    }
}

function addReservation(res, act){
    res.getElementsByTagName('h2')[0].innerHTML = act.name;

    resCon.appendChild(res);
}

function createReservation(data){

    const start = data.startTime;
    const end = data.endTime;

    let start1 = start[0].toString();
    let start2 = start[1].toString();
    let end1 = end[0].toString();
    let end2 = end[1].toString();
    if (start1.length === 1){
        start1 = "0" + start1;
    }
    if (start2.length === 1){
        start2 = "0" + start2;
    }
    if (end1.length === 1){
        end1 = "0" + end1;
    }
    if (end2.length === 1){
        end2 = "0" + end2;
    }

    const resBox = document.createElement("div");
    resBox.className = "res-box";

    const resTitle = document.createElement("h2");
    resTitle.className = "res-title";
    resBox.appendChild(resTitle);

    const resDate = document.createElement("p");
    resDate.className = "res-date";
    resDate.innerHTML = "Date: " + data.date;
    resBox.appendChild(resDate);

    const resTime = document.createElement("p");
    resTime.className = "res-time";
    resTime.innerHTML = "Time: " + start1 + ":" + start2 + "-" + end1 + ":" + end2;
    resBox.appendChild(resTime);

    const hiddenId = document.createElement("input");
    hiddenId.className = "hidden-id";
    hiddenId.setAttribute("type", "hidden");
    hiddenId.value = data.id;
    resBox.appendChild(hiddenId);

    const hiddenDate = document.createElement("input");
    hiddenDate.className = "hidden-date";
    hiddenDate.setAttribute("type", "hidden");
    hiddenDate.value = data.date;
    resBox.appendChild(hiddenDate);

    const hiddenTime = document.createElement("input");
    hiddenTime.className = "hidden-time";
    hiddenTime.setAttribute("type", "hidden");
    hiddenTime.value = start1 + ":" + start2 + "-" + end1 + ":" + end2;
    resBox.appendChild(hiddenTime);

    let url = activityUrl + data.activity_id;
    fetch(url, requestObjectGet)
        .then(response => response.json())
        .then(data => addReservation(resBox, data))
}

function setReservations(data){
    data.forEach(res => createReservation(res));
}

function deleteReservation(){
    let url = deleteReservationUrl + resId.value;
    fetch(url, requestOptionsDelete)
        .then(response => response.status)
        .then(status => {
            if (status === 200){
                location.reload();
            } else {
                console.log("Delete failed");
            }
        })
}

function saveReservation(){

    reservation.date = dateInp.value;
    reservation.id = resId.value;
    reservation.startTime = resSTime.value;
    reservation.endTime = resETime.value;

    console.log(reservation);

    body1 = JSON.stringify(reservation);
    requestOptionsPut.body = body1;

    fetch(editReservationUrl, requestOptionsPut)
        .then(response => response.status)
        .then(status => {
            if (status === 200){
                warn.innerHTML = "";
                start();
            }else {
                warn.innerHTML = "*New timeslot might not be available, please check booking calendar";
            }
        })
}

function addDays(dateX, daysX) {
    let copy = new Date(Number(dateX));

    copy.setDate(dateX.getDate() + daysX);
    return copy;
}

function start(){
    saveBtn.disabled = true;
    deleteBtn.disabled = true;
    resId.value = "";
    resTitle.value = "";

    let date = new Date;
    dateInp.setAttribute("min", date.toISOString().split("T")[0]);
    date = addDays(date, 30);
    dateInp.setAttribute("max", date.toISOString().split("T")[0]);

    resItems().forEach(item => item.remove());
    resItems().forEach(item => item.style.backgroundColor = "#fed767");

    fetch(reservationsUrl, requestObjectGet)
        .then(response => response.json())
        .then(data => setReservations(data))
}


document.addEventListener('click', ev => resClick(ev));

saveBtn.addEventListener("click", saveReservation);
deleteBtn.addEventListener("click", deleteReservation);

start();
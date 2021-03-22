let billy = "Billy";

const title = document.querySelector(".act-title");
const dur = document.querySelector(".minDur");
const par = document.querySelector(".maxPar");

const mon = document.querySelector(".mon");
const tue = document.querySelector(".tue");
const wed = document.querySelector(".wed");
const thu = document.querySelector(".thu");
const fri = document.querySelector(".fri");
const sat = document.querySelector(".sat");
const sun = document.querySelector(".sun");

const days = [mon, tue, wed, thu, fri, sat, sun];

const col1 = document.querySelector(".col1");
const col2 = document.querySelector(".col2");
const col3 = document.querySelector(".col3");
const col4 = document.querySelector(".col4");
const col5 = document.querySelector(".col5");
const col6 = document.querySelector(".col6");
const col7 = document.querySelector(".col7");
const columns = document.querySelectorAll(".grid-item");

const cols = [col1, col2, col3, col4, col5, col6, col7];

const sTime = document.querySelector(".startTime");
const eTime = document.querySelector(".endTime");
const dateInp = document.querySelector(".date-inp");
const bookBtn = document.querySelector(".bookingBtn");
const warn = document.querySelector(".warn");

const pastBtn = document.querySelector(".pastBtn");
const weekCount = document.querySelector(".weekCount")
const nextBtn = document.querySelector(".nextBtn");

//---------------------------------------------------------------------------------------------------------------------

const requestObjectGet = {
    method: "GET",
    "content-type": "application/json",
    redirect: "follow"
}

let reservation = {
    startTime: "",
    endTime: "",
    date: ""
}

let body1 = JSON.stringify(reservation);

let requestOptionsPost = {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'POST',
    body: body1
};

const activityUrl = "/activities/"; // "/activities/{id}"
const reservationsUrl = "/booking/reservation/day/"; // "/booking/reservation/day/{date}/{activityId}"
const newReservationUrl = "/booking/reserve/"; // "/booking/reserve/{id}"



let activity = {
    description: "",
    id: 0,
    maxParticipants: 0,
    minDurationHours: 0,
    name: ""
};

//---------------------------------------------------------------------------------------------------------------------

function clearReservations(){
    columns.forEach(column => column.innerHTML = "");
}

let firstMonday = new Date;
let currentWeek = 1;

function getReservations(dayX, date, activityId){

    let newDate = addDays(date, ((currentWeek - 1) * 7) + days.indexOf(dayX));

    dayX.innerHTML = newDate.getDate() + "/" + (newDate.getMonth() + 1);

    const url = reservationsUrl + newDate.toISOString().split("T")[0] + "/" + activityId;

    fetch(url, requestObjectGet)
        .then(response => response.json())
        .then(dataX => setReservations(dataX, cols[days.indexOf(dayX)]))
        .catch((error) => console.log("Error", error))

}

function addReservation(reservation, column){
    const res = document.createElement("div");
    res.className = "reservation";

    const start = reservation.startTime;
    const end = reservation.endTime;

    const cont = document.createElement("p");
    cont.className = "res-content";

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

    cont.innerHTML = start1 + ":" + start2 + "-" + end1 + ":" + end2;
    res.appendChild(cont);

    let top = (Number(start[0]) + (Number(start[1]) /60) - 8) * 40;
    let height = ((Number(end[0]) + (Number(end[1]) /60)) - Number(start[0]) - (Number(start[1]) /60)) * 40;

    res.setAttribute("style", "top: " + top + "px; height: " + height +"px")

    column.appendChild(res);
}

function setReservations(reservations, column){
    reservations.forEach(reservation => addReservation(reservation, column))
}

function addDays(dateX, daysX) {
    let copy = new Date(Number(dateX));

    copy.setDate(dateX.getDate() + daysX);
    return copy;
}

function loadCalendar(){
    clearReservations();

    let date = firstMonday;

    days.forEach((day) => getReservations(day, date, activity.id));
}

function setActivity(activityX){
    activity = activityX;

    title.innerHTML = activity.name;
    dur.innerHTML = activity.minDurationHours;
    par.innerHTML = activity.maxParticipants;
}

function loadActivity(activityId){
    const url = activityUrl + activityId;
    fetch(url, requestObjectGet)
        .then(response => response.json())
        .then(activityX => setActivity(activityX))
        .then(() => loadCalendar());
}

function setFirstMonday(){
    let curr = new Date;
    curr.setDate(curr.getDate() - (curr.getDay()) + 1);
    firstMonday = curr;
}

function start(){
    setFirstMonday();
    loadActivity(1); //TODO: ActivityID
    pastBtn.disabled = true;

    let date = new Date;
    dateInp.setAttribute("min", date.toISOString().split("T")[0]);
    date = addDays(date, 30);
    dateInp.setAttribute("max", date.toISOString().split("T")[0])
}

function nextWeek(){
    if (currentWeek == 5){
        pastBtn.disabled = false;
        nextBtn.disabled = true;
    }else {
        currentWeek++;
        weekCount.innerHTML = currentWeek;
        loadCalendar();

        pastBtn.disabled = false;
        nextBtn.disabled = false;
        if (currentWeek == 5){
            pastBtn.disabled = false;
            nextBtn.disabled = true;
        }
    }
}

function pastWeek(){
    if (currentWeek == 1){
        pastBtn.disabled = true;
        nextBtn.disabled = false;
    }else {
        currentWeek--;
        weekCount.innerHTML = currentWeek;
        loadCalendar();

        pastBtn.disabled = false;
        nextBtn.disabled = false;
        if (currentWeek == 1){
            pastBtn.disabled = true;
            nextBtn.disabled = false;
        }
    }
}

function reserve(){
    warn.innerHTML = "";

    let url = newReservationUrl + activity.id;
    reservation.startTime = sTime.value;
    reservation.endTime = eTime.value;
    reservation.date = dateInp.value;

    body1 = JSON.stringify(reservation);

    requestOptionsPost.body = body1;

    fetch(url, requestOptionsPost)
        .then(response => response.status)
        .then(status => {
            if (status === 201){
                console.log("Success");
            }else {
                console.log("Failed")
                warn.innerHTML = "Booking failed: Please check your input or reload page";
            }

            loadCalendar();
        })
}

bookBtn.addEventListener("click", reserve);

pastBtn.addEventListener("click", pastWeek);
nextBtn.addEventListener("click", nextWeek);

start();
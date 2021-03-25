let monthLabel = document.getElementById("h1");

let weekday = new Array(7);
weekday[0] = "Sunday";
weekday[1] = "Monday";
weekday[2] = "Tuesday";
weekday[3] = "Wednesday";
weekday[4] = "Thursday";
weekday[5] = "Friday";
weekday[6] = "Saturday";


let getRequest = {
    method: "GET",
    headers: {
        "content-type": "application/json"
    },
    redirect: "follow"
}
let fetchData = [];

let getUrl = "http://localhost:8080/reservations/all"
let activityGet = "http://localhost:8080/activities/all"

fetch(getUrl, getRequest)
    .then(response => response.json())
    .then(data => getDataFromFetch(data))


let activities = [];

async function getDataset() {
    await fetch(activityGet, getRequest)
        .then(response => response.json())
        .then(data => data.forEach(act => activities.push(act.name)))

}




function getDataFromFetch(data){

    data.forEach(res => fetchData.push(res));
    let today = new Date();
    createHTMLMonth(today.getMonth(), today.getFullYear());
}

let months =[
    [31, "January"],
    [28, "February"],
    [31, "March"],
    [30, "April"],
    [31, "May"],
    [30, "June"],
    [31, "July"],
    [31, "August"],
    [30, "September"],
    [31, "October"],
    [30, "November"],
    [31, "December"]
];

let masterDiv = document.getElementById("calendar-div");
let buttonDiv = document.getElementById("buttons-div");

async function createHTMLMonth(month, year){


    await getDataset();

    monthLabel.innerHTML = months[month][1];



    let counter = months[month][0];




    for(let i = 1; i<=counter; i++) {
        let addRes = 0;

        let tempRes = [];

        for (let j = 0; j < fetchData.length; j++) {
            if (parseInt(fetchData[j].date.split("-")[2]).toString() === i.toString() && parseInt(fetchData[j].date.split("-")[1]).toString() === (month+1).toString()) {
                addRes += 1;
                tempRes.push(fetchData[j]);
            }
        }
        let day = new Date(year, month, i);
        let div = document.createElement("div");
        div.className = "daysDiv"



        let daySpan = document.createElement("span");
        daySpan.innerHTML = i + ". ";
        div.appendChild(daySpan)

        let weekDaySpan = document.createElement("span");
        weekDaySpan.innerHTML = weekday[day.getDay()];
        div.appendChild(weekDaySpan);

        div.appendChild(document.createElement("br"));

        for (let k = 0; k<addRes; k++) {
            let reservation = document.createElement("span");
            reservation.innerHTML = "" + activities[tempRes[k].activity_id-1] + " " + parseFloat(tempRes[k].startTime.join(".")).toFixed(2) + "-" + parseFloat(tempRes[k].endTime.join(".")).toFixed(2);
            div.appendChild(reservation);
            div.appendChild(document.createElement("br"));
        }


        masterDiv.appendChild(div);
    }
    setTimeout(function(){}, 1000)


    let plusbutton = document.createElement("button");
    plusbutton.type = "button"
    plusbutton.innerHTML = "Next month";

    plusbutton.addEventListener("click", function(){
        while(masterDiv.firstChild){
            masterDiv.removeChild(masterDiv.lastChild);
        }
        plusbutton.remove();
        minusbutton.remove();
        createHTMLMonth(month+1, year);
    })


    let minusbutton = document.createElement("button");
    minusbutton.type = "button"
    minusbutton.innerHTML = "Previous month";

    minusbutton.addEventListener("click", function(){
        while(masterDiv.firstChild){
            masterDiv.removeChild(masterDiv.lastChild);
        }
        plusbutton.remove();
        minusbutton.remove();
        createHTMLMonth(month-1, year);
    })
    buttonDiv.appendChild(minusbutton);
    buttonDiv.appendChild(plusbutton);

}

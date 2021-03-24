let billy = "Billy";

const title = document.querySelector(".title");
const calSearchInp = document.querySelector(".cal-search-inp");
const calSearchBtn = document.querySelector(".cal-search-btn");
const calClearBtn = document.querySelector(".cal-clear-btn");
const calBoxes = document.querySelectorAll(".cal-box");
const assignments = () => {return document.querySelectorAll(".assignment")};
const preBtn = document.querySelector(".preBtn");
const period = document.querySelector(".period");
const nextBtn = document.querySelector(".nextBtn");

const insAddInp = document.querySelector(".ins-add-inp");
const insSearchBtn = document.querySelector(".ins-search-btn");
const insBox = document.querySelector(".ins-box");
const ins = () => document.querySelector(".ins");

//---------------------------------------------------------------------------------------------------------------------

const requestObjectGet = {
    method: "GET",
    "content-type": "application/json",
    redirect: "follow"
}

let assignment = {
    id: 0
}

let instructor = {
    id: 0,
    name: ""
}

let body1 = JSON.stringify(assignment);
let body2 = JSON.stringify(assignment);

let requestOptionsPost = {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'POST',
    body: body1
};

let requestOptionsDelete = {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'DELETE'
};

const instructorGetUrl = "/work-schedule/instructors";
const instructorPostUrl = "/work-schedule/new-instructor";
const instructorDeleteUrl = "/work-schedule/delete-instructor";

const assignmentGetUrl = "/work-schedule/assignments/"; // "/work-schedule/assignments/{year}/{month}"
const assignmentPostUrl = "/work-schedule/new-assignment";
const assignmentDeleteUrl = "/work-schedule/delete-assignment";

//---------------------------------------------------------------------------------------------------------------------

function loadInstructor(data){
    const instructor = document.createElement("p");
    instructor.className = "ins";
    instructor.innerHTML = data.name;
    instructor.setAttribute("value", data.id);

    insBox.appendChild(instructor);
}

function getInstructors(){
    fetch(instructorGetUrl, requestObjectGet)
        .then(response => response.json())
        .then(data => data.forEach(dat => loadInstructor(dat)))
}

const activeDays = [];

let monthVar = 0;

function loadCalendar(){
    activeDays.length = 0;

    document.querySelectorAll(".assignment").forEach(a => a.remove());

    let curr = new Date;
    curr.setDate(1);
    curr.setMonth(curr.getMonth() + monthVar);
    let day = curr.getDay() - 1;

    title.innerHTML = "Planning Calendar: " + (curr.getMonth() + 1) + "/" + curr.getFullYear();
    period.innerHTML = " " + (curr.getMonth() + 1) + "/" + curr.getFullYear() + " "

    if (day === -1){
        day = 6;
    }

    let check = curr.getMonth();
    for (let i = day; curr.getMonth() === check; i++){
        calBoxes[i].getElementsByTagName("span")[0].innerHTML = curr.getDate() + "/" + (curr.getMonth() + 1);
        activeDays.push(calBoxes[i]);
        curr.setDate(curr.getDate() + 1);
    }

    calBoxes.forEach(box => box.hidden = true);

    activeDays.forEach(box => box.hidden = false);
}

function loadAssignments(data){
    let el = document.createElement("p");
    el.className = "assignment";
    el.innerHTML = data.instructor.name;
    el.setAttribute("value", data.id);
    activeDays[data.date[2]].appendChild(el);
}

function getAssignments(){
    let url = assignmentGetUrl + period.innerHTML.trim().split("/")[1] + "/" + period.innerHTML.trim().split("/")[0];
    fetch(url, requestObjectGet)
        .then(response => response.json())
        .then(data => data.forEach(dat => loadAssignments(dat)))
}

function start(){
    getInstructors();
    loadCalendar();
    getAssignments();
}

start();

function previousMonth(){
    monthVar--;
    loadCalendar();
    getAssignments();
}

function nextMonth(){
    monthVar++;
    loadCalendar();
    getAssignments();
}

function clearSearch(){
    assignments().forEach(a => a.hidden = false);
}

function calSearch(){
    const term = calSearchInp.value;
    assignments().forEach(a => {
        if (a.innerHTML === term){
            a.hidden = false;
        } else {
            a.hidden = true;
        }
    });
}

function insAdd(){
    if (insAddInp.value !== null){
        instructor.name = insAddInp.value;
        body1 = JSON.stringify(instructor);
        requestOptionsPost.body = body1;
        fetch(instructorPostUrl, requestOptionsPost)
            .then(response => response.json())
            .then(data => loadInstructor(data))
    }
}

function elementPress(ev){

}

calSearchBtn.addEventListener("click", calSearch);
calClearBtn.addEventListener("click", clearSearch)
preBtn.addEventListener("click", previousMonth);
nextBtn.addEventListener("click", nextMonth);
insSearchBtn.addEventListener("click", insAdd);

document.addEventListener("click", ev => elementPress());
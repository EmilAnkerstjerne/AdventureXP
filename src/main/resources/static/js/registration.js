
const backBtn = document.getElementById("backBtn");

const nameInp = document.getElementById("nameInp");
const emailInp = document.getElementById("emailInp");
const passwordInp = document.getElementById("passwordInp");
const telInp = document.getElementById("telInp");

const inputs = document.querySelector(".inputBox");

const nameWarn = document.getElementById("nameWarning");
const emailWarn = document.getElementById("emailWarning");
const passWarn = document.getElementById("passwordWarning");
const telWarn = document.getElementById("telWarning");

const warnings = document.querySelectorAll(".warning");

const regBtn = document.getElementById("regBtn");

const url = "http://localhost:8080/registration/form"; //Localhost?

let user = {
    "name" : "",
    "username": "",
    "password": "",
    "phoneNumber": ""
};

let body1 = JSON.stringify(user);

let requestOptions = {
    headers: {
        'Content-Type': 'application/json'
    },
    method: 'POST',
    body: body1
};

function clearWarnings(warnings){
    warnings.forEach(warning => warning.innerHTML = "")
}

function addUser(){
    body1 = JSON.stringify(user);
    requestOptions.body = body1;

    console.log(user);

    fetch(url, requestOptions)
        .then(response => response.json())
        .then(data => {
            document.body.innerHTML += "<h1>Success</h1>"; console.log(data)
        })
        .catch((error) => {
            document.body.innerHTML += "<h1>Failure</h1>"; console.log(data)
        });
}

function checkUser(){
    clearWarnings(warnings);
    let clear = true;

    if (nameInp.value == ""){
        clear = false;
        nameWarn.innerHTML = "*Required";
    }else {
        user.name = nameInp.value;
    }

    if (emailInp.value == ""){
        clear = false;
        emailWarn.innerHTML = "*Required";
    }else {
        user.username = emailInp.value;
    }

    if (passwordInp.value == ""){
        clear = false;
        passWarn.innerText = "*Required";
    }else {
        user.password = passwordInp.value;
    }

    if (telInp.value == ""){
        clear = false;
        telWarn.innerHTML = "*Required";
    }else {
        user.phoneNumber = telInp.value;
    }

    if (clear){
        addUser();
    }
}

backBtn.addEventListener("click", () => window.location.href = '/')
regBtn.addEventListener("click", checkUser)
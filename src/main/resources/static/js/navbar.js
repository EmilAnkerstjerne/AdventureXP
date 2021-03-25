
//navbar
let isLoggedIn;
fetch("/logged-in")
    .then(response => response.json())
    .then(data => setIsLoggedIn(data));
function setIsLoggedIn(value){
    isLoggedIn = value;
    if(isLoggedIn===true){
        document.getElementById("login-div").style.display = "none";
        document.getElementById("logout-div").style.display = "inline-block";
        document.getElementById("my-bookings").style.display = "inline-block";
    } else {
        document.getElementById("login-div").style.display = "inline-block";
    }

}

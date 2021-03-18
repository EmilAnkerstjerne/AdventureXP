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
//---------
//--JsonObjects--
let object = {
    name: "Gokart",
    duration: 10
}
//---------
//--Urls--
let deleteUrl = "http://localhost:8080/activities/deleteActivity/"
//---------
//--Buttons--
let deleteButton = document.querySelector(".deleteActivity");
//---------

//--EventListeners--
// deleteButton.addEventListener("click", function(){
//     if(confirm("Are you sure you want to delete this activity?")){
//     fetch(deleteUrl + deleteButton.value, deleteRequest).
//     catch((error) => console.log("fejl"));
//     setTimeout(reloadPage, 50);
//     }}, false);


//---------
//--Functions--

function deleteAct(){
    if(confirm("Are you sure you want to delete this activity?")){
        fetch(deleteUrl + deleteButton.value, deleteRequest).
        catch((error) => console.log("fejl"));
        setTimeout(reloadPage, 50);
    }
}
function insertActivities(activity){

}

function reloadPage(){
    location.reload();
}
//---------


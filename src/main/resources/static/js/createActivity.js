

let postUrl = "http://localhost:8080/activities/create/save"



let activity = {
    "name": "",
    "maxParticipants": "",
    "description": "",
    "minDurationHours": ""
}

let body1 = JSON.stringify(activity);

let postRequest = {
    method: "POST",
    headers: {
        "content-type": "application/json"
    },
    body: body1
}

function save() {

    let name = document.getElementById("name").value;
    let maxParticipant = document.getElementById("max-participants").value;
    let description = document.getElementById("description").value;
    let minDuration = document.getElementById("min-duration").value;

    // let activity = {
    //     "name": document.getElementById("name").value,
    //     "maxParticipants": document.getElementById("max-participants").value,
    //     "description": document.getElementById("description"),
    //     "minDurationHours": document.getElementById("min-duration")
    // }








    if(name === "" || maxParticipant === "" || minDuration === "" || description === "") {
        alert("All fields have to be filled")
    }else{
        console.log("askdjbasd")
        activity.name = name;
        activity.description = description;
        activity.maxParticipants = maxParticipant;
        activity.minDurationHours = minDuration;
        console.log(activity);
        console.log(body1);
        body1 = JSON.stringify(activity);
        postRequest.body = body1;

        fetch(postUrl, postRequest)
            .catch((error) => console.log("error"));

        location.reload();
    }





}
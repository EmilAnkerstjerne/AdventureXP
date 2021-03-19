let getUrl = "http://localhost:8080/store/item/all"

let getRequest = {
    method: "GET",
    headers: {
        "content-type": "application/json"
    },
    redirect: "follow"
}

let listDiv = document.getElementById("list-div")
let basketDiv = document.getElementById("basket-div")
let idList = [];
let totalPrice =0;


function generateHTML(item){
    let hdiv = document.createElement("div");
    hdiv.className = "itemListing";

    let name = document.createElement("span");
    name.innerHTML = item.name;

    let price = document.createElement("span");
    price.innerHTML = "Price: " + item.price + ",-";

    //basket items
    let counter = 1;
    let basketItem = document.createElement("div");
    basketItem.className = "itemListing";

    let nameB = document.createElement("span");
    nameB.innerHTML = item.name;

    let count = document.createElement("span");
    count.innerHTML = " x "+counter;

    let priceB = document.createElement("span");
    priceB.innerHTML = "Price: " + item.price + ",-";

    let priceTotal = document.createElement("span");
    priceTotal.innerHTML = "Total: " + totalPrice + ",-";

    let br = document.createElement("br");

    let addToBasket = document.createElement("button");
    addToBasket.innerHTML = "Add to basket";
    addToBasket.onclick =

        function addToBasket(){

            if(idList.includes(item.id)){
                counter++;
                count.innerHTML = " x "+counter;
                priceB.innerHTML = priceB.innerHTML = "Price: " + item.price*counter + ",-";

            }
            totalPrice = totalPrice + item.price;
            priceTotal.innerHTML = "Total: " + totalPrice + ",-";
            basketItem.appendChild(nameB);
            basketItem.appendChild(count);
            basketItem.appendChild(br);
            basketItem.appendChild(priceB);
            basketDiv.appendChild(basketItem)
            idList.push(item.id);
            document.getElementById("total-price").innerText= "Total: " + totalPrice + ",-";
            document.getElementById("payment").style.visibility = "visible";


        };

    hdiv.appendChild(name);
    hdiv.appendChild(document.createElement("br"));
    hdiv.appendChild(price);
    hdiv.appendChild(document.createElement("br"));
    hdiv.appendChild(addToBasket);


    listDiv.appendChild(hdiv);
}



function loadItems(){
    fetch(getUrl, getRequest)
        .then(response => response.json())
        .then(data => loadedItems(data))
        .catch((error) => console.log("error"));
}

function loadedItems(data){
    data.forEach(item => generateHTML(item));
}

loadItems();

function paymentBtn(){
    console.log(totalPrice);
}

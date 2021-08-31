var submit = document.getElementById("update-btn");
submit.addEventListener("click", updateReq);

function updateReq() {
    let nameEntry = document.querySelector("#update-name").value;
    let artistCard = document.querySelectorAll("#artist-card");
    let id = 0;
    console.log(nameEntry);
    for (card of artistCard) {
        console.log(card.querySelector("h1").textContent);
    if (nameEntry.toLowerCase() == card.querySelector("h1").textContent.toLowerCase()) {
        id = card.querySelector(".centered").getAttribute("id");
    }
}
    const req = new XMLHttpRequest();
    console.log(id);
    req.open("POST", "http://localhost:8082/artists/update/" + id);
    req.onload = () => {
        if (req.status === 202) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }
    var nameVal = $('#new-name').val();

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let data = { id: id, name: nameVal };
    req.send(JSON.stringify(data));
    let modal = document.getElementById("close-modal");
    modal.click();
};

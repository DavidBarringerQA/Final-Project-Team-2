let delReq = () => {
    let nameEntry = document.querySelector("#delete-name").value;
    let albumCard = document.querySelectorAll("#album-card");
    let id = 0;
    console.log(nameEntry);
    for (card of albumCard) {
        console.log(card.querySelector("h1").textContent);
    if (nameEntry.toLowerCase() == card.querySelector("h1").textContent.toLowerCase()) {
        id = card.querySelector(".centered").getAttribute("id").split("(")[1].split(")")[0];
    }
}
    fetch("http://localhost:8082/albums/delete/" + id, {
        method: 'DELETE'
    }).then((data) => {
        if (data.status === 204) {
            console.log("delete success");
        }
    }).catch((error) => {
        console.log("Oops..." + error);
    });
    
    let modal = document.getElementById("close-modal");
    modal.click();
}

var submit = document.getElementById("delete-btn");
submit.addEventListener("click", delReq);

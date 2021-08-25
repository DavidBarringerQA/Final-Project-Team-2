let delReq = () => {
    let nameEntry = document.querySelector("#delete-name").value;
    let genreCard = document.querySelectorAll("#genre-card");
    let id = 0;
    console.log(nameEntry);
    for (card of genreCard) {
        console.log(card.querySelector("h1").textContent);
    if (nameEntry == card.querySelector("h1").textContent) {
        id = card.querySelector(".centered").getAttribute("id").split("(")[1].split(")")[0];
    }
}
    fetch("http://localhost:8082/genres/delete/" + id, {
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

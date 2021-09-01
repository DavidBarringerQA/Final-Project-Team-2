var submit = document.getElementById("update-btn");
submit.addEventListener("click", updateReq);

function updateReq() {
        let nameEntry = document.querySelector("#update-old-name").value;
        let playlistCard = document.querySelectorAll("#playlist-card");
        let id = 0;
        console.log(nameEntry);
        for (card of playlistCard) {
            console.log(card.querySelector("h1").textContent);
        if (nameEntry.toLowerCase() == card.querySelector("h1").textContent.toLowerCase()) {
            id = card.querySelector(".centered").getAttribute("id");
        }
    }
    console.log(id);
    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/playlists/update/" + id);
    req.onload = () => {
        if (req.status === 202) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }
    var nameVal = $('#update-name').val();
    var descriptionVal = $('#update-description').val();

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let data = { id: id, name: nameVal, description: descriptionVal, artwork: nameVal };
    req.send(JSON.stringify(data));
    let modal = document.getElementById("close-modal");
    modal.click();
};

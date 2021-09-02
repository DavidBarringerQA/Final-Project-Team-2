var submit = document.getElementById("update-btn");
submit.addEventListener("click", updateReq);

function updateReq() {
    let nameEntry = document.querySelector("#old-name").value;
    let albumCard = document.querySelectorAll("#album-card");
    let id = 0;
    console.log(nameEntry);
    for (card of albumCard) {
        console.log(card.querySelector("h1").textContent);
    if (nameEntry.toLowerCase() == card.querySelector("h1").textContent.toLowerCase()) {
        id = card.querySelector(".centered").getAttribute("id").split("(")[1].split(")")[0];
    }
}
    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/albums/update/" + id);
    req.onload = () => {
        if (req.status === 202) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }

    var nameVal = $('#update-name').val();
    var artist = document.getElementById("read-artists-update");
    var artistVal = artist.options[artist.selectedIndex].value;
    var genre = document.getElementById("read-genres-update");
    var genreVal = genre.options[genre.selectedIndex].value;

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		req.setRequestHeader("Authorization", `Bearer ${localStorage.getItem("token")}`);
    let data = {
         "cover": nameVal, 
         "name": nameVal, 
         "artist": {
         "id": artistVal, 
         },
         "genre": {
            "id": genreVal, 
            }
        };
    console.log(data);
    req.send(JSON.stringify(data));

    let modal = document.getElementById("close-modal");
    modal.click();
};

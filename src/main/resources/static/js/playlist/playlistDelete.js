let delReq = () => {
    let nameEntry = document.querySelector("#delete-name").value;
    let playlistCard = document.querySelectorAll("#playlist-card");
    let id = 0;
    console.log(nameEntry);
    for (card of playlistCard) {
        console.log(card.querySelector("h1").textContent);
    if (nameEntry.toLowerCase() == card.querySelector("h1").textContent.toLowerCase()) {
        id = card.querySelector(".centered").getAttribute("id");
    }
}
    fetch("http://localhost:8082/playlists/delete/" + id, {
        method: 'DELETE',
				headers: {
						"Authorization": `Bearer ${localStorage.getItem("token")}`
				}
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

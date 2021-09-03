var submit = document.getElementById("update-btn");
submit.addEventListener("click", updateReq);

function updateReq() {
        let nameEntry = document.querySelector("#update-name").value;
        let genreCard = document.querySelectorAll("#genre-card");
        let id = 0;
        console.log(nameEntry);
        for (card of genreCard) {
            console.log(card.querySelector("h1").textContent);
        if (nameEntry.toLowerCase() == card.querySelector("h1").textContent.toLowerCase()) {
            id = card.querySelector(".centered").getAttribute("id").split("(")[1].split(")")[0];
        }
    }
    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/genres/update/" + id);
    req.onload = () => {
        if (req.status === 202) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }
    var nameVal = $('#new-name').val();
    var descriptionVal = $('#update-description').val();

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		req.setRequestHeader("Authorization", `Bearer ${localStorage.getItem("token")}`);
    let data = { id: id, name: nameVal, description: descriptionVal };
    req.send(JSON.stringify(data));
    let modal = document.getElementById("close-modal");
    modal.click();
};

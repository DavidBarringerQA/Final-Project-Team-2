let delReq = () => {
    let nameEntry = document.querySelector("#delete-name").value;
    let trackTable = document.querySelector("#track-table");
    let id = 0;
    console.log(nameEntry);
    let tracks = trackTable.querySelectorAll(".tracks");
    for (t of tracks) {
        console.log(t.textContent);
    if (nameEntry.toLowerCase() == t.textContent.toLowerCase()) {
        id = t.getAttribute("id");
    }
}
    fetch("http://localhost:8082/tracks/delete/" + id, {
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

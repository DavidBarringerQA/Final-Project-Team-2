var submit = document.getElementById("update-btn");
submit.addEventListener("click", updateReq);

function updateReq() {
    let nameEntry = document.querySelector("#old-track").value;
    let trackTable = document.querySelectorAll("#track-elements");
    let id = 0;
    console.log(nameEntry);
    for (tracks of trackTable) {
        console.log(tracks.querySelector("a").textContent);
    if (nameEntry.toLowerCase() == tracks.querySelector("a").textContent.toLowerCase()) {
        id = tracks.querySelector(".tracks").getAttribute("id");
    }
}
    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/tracks/update/" + id);
    req.onload = () => {
        if (req.status === 202) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }

    var nameVal = $('#update-name').val();
    var durationVal = parseInt($('#update-duration').val());
    var lyricsVal = $('#update-lyrics').val();
    var album = document.getElementById("update-album");
    var albumVal = album.options[album.selectedIndex].value;

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		req.setRequestHeader("Authorization", `Bearer ${localStorage.getItem("token")}`);
    let data = {
         "id": id,
         "name": nameVal, 
         "duration": durationVal, 
         "lyrics": lyricsVal, 
         "album": {
         "id": albumVal, 
         }
        };
    console.log(data);
    console.log(id);
    req.send(JSON.stringify(data));

    let modal = document.getElementById("close-modal");
    modal.click();
};

var submit = document.getElementById("add-btn");
submit.addEventListener("click", updateReq);

function updateReq() {
    var track = document.getElementById("read-tracks-playlist");
    var id = track.options[track.selectedIndex].value;
    console.log(id);

    fetch(`http://localhost:8082/tracks/read/` + id) 
    .then((response) => {
        if (response.status !== 200) { 
            console.error(`status: ${reponse.status}`);
            return;
        }
        return response.json()
    }) 
        .then(data =>  {
            console.log(data);
            updateTrack(id, data)
        }
    ).catch((err)=> console.error(`Fetch Error: ${err}`)); 

};

function updateTrack(trackId, trackData) {
const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/tracks/update/" + trackId);
    req.onload = () => {
        if (req.status === 202) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }
    
    let playlistJumbo = document.querySelector("#main-container");
    var playlistVal = playlistJumbo.querySelector("h4").getAttribute("id");
    console.log("playlist id: " + playlistVal);
    console.log(trackData);

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		req.setRequestHeader("Authorization", `Bearer ${localStorage.getItem("token")}`);
    let data = {
         "id": trackId,
         "name": trackData.name, 
         "duration": trackData.duration, 
         "lyrics": trackData.lyrics, 
         "album": {
         "id": Object.keys(trackData.album)[0], 
         },
         "playlist": {
            "id": playlistVal,
            }
        };
    console.log(data);
    console.log("data name" + trackData.name);
    console.log("playlist id: " + playlistVal);
    req.send(JSON.stringify(data));

    let modal = document.getElementById("close-modal");
    modal.click();
};

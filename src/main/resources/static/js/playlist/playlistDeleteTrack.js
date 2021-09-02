var submitDelete = document.getElementById("delete-btn");
submitDelete.addEventListener("click", deleteReq);

function deleteReq() {
    var track = document.getElementById("read-this-playlist");
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
            deleteTrack(id, data)
        }
    ).catch((err)=> console.error(`Fetch Error: ${err}`)); 

};

function deleteTrack(trackId, trackData) {
const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/tracks/update/" + trackId);
    req.onload = () => {
        if (req.status === 202) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		req.setRequestHeader("Authorization", `Bearer ${localStorage.getItem("token")}`);
    let data = {
         "id": trackId,
         "name": trackData.name, 
         "duration": trackData.duration, 
         "lyrics": trackData.lyrics, 
         "album": {
         "id": Object.keys(trackData.album)[0], 
         }
        };
    console.log(data);
    req.send(JSON.stringify(data));

    let modal = document.getElementById("close-modal");
    modal.click();
};

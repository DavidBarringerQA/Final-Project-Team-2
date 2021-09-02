fetch(`http://localhost:8082/tracks/read`) 
    .then((response) => {
        if (response.status !== 200) { 
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() 
        .then(data => {
            readAll(data)
            readAllFromThisPlaylist(data)
        })
}).catch((err)=> console.error(`Fetch Error: ${err}`)); 

function readAll(data) {

    let dropdownTracks = document.getElementById("read-tracks-playlist");
    for (i = 0; i < data.length; i++) {
        let option = document.createElement("option");
        option.setAttribute("value", data[i].id);
        option.textContent = data[i].name;
        dropdownTracks.appendChild(option);
    }

};

function readAllFromThisPlaylist(data) {

    let playlistJumbo = document.querySelector("#main-container");
    var playlistVal = playlistJumbo.querySelector("h4").getAttribute("id");
    let dropdownTracks = document.getElementById("read-this-playlist");
    for (i = 0; i < data.length; i++) {
        if(Object.keys(data[i].playlist) == playlistVal) {
        let option = document.createElement("option");
        option.setAttribute("value", data[i].id);
        option.textContent = data[i].name;
        dropdownTracks.appendChild(option);
        }
    }

};
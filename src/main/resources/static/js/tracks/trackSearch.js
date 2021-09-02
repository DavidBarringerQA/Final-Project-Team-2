var submitSearch = document.getElementById("search-btn");
submitSearch.addEventListener("click", myFunction);

function myFunction() {
    fetch(`http://localhost:8082/tracks/read`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            return response.json()
        })
        .then(data => {
            console.log(data);
            let input = document.getElementById('search-input');
            let filter = input.value.toLowerCase();
            let results = false;
            for (let i = 0; i < data.length; i++) {
                if (data[i].name.toLowerCase() == filter) {
                    readAlbumSearch(data[i], Object.keys(data[i].album)[0]);
                    results = true;
                } 
            }
            if(!results) {
                feedbackToUser();
            }
        }
        ).catch((err) => console.error(`Fetch Error: ${err}`));
}

function readAlbumSearch(track, id) {
    fetch(`http://localhost:8082/albums/read/` + id)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            return response.json()
                .then(data => readFromSearch(track, data))
        }).catch((err) => console.error(`Fetch Error: ${err}`));
}

function readFromSearch(track, album) {
    let searchList = document.getElementById("search-list");
    let trackTable = document.getElementById("track-table");
    let searchBarRow = document.getElementById("no-results");
    searchBarRow.setAttribute("style", "display: none");

    parent = document.getElementById("body-container");
    parent.replaceChild(searchList, trackTable);
    let container = document.getElementById("search-list");

    let body = document.createElement("tbody");
    container.appendChild(body);
    let row = document.createElement("tr");
    body.appendChild(row);

    let header = document.createElement("th");
    row.appendChild(header);
    let image = document.createElement("img");
    console.log(album);
    console.log(track);
    image.setAttribute("src", "../img/albums/" + album.name + ".png");
    image.setAttribute("alt", "...");
    image.setAttribute("class", "img");
    image.setAttribute("width", "50px");
    header.appendChild(image);

    let songName = document.createElement("td");
    songName.setAttribute("id", "track-elements");
    let link = document.createElement("a");
    link.setAttribute("id", track.id);
    link.setAttribute("class", "tracks");
    link.setAttribute("href", "trackChild.html?id=" + track.id);
    link.setAttribute("style", "color: white; font-size: x-large;")
    link.appendChild(songName);
    row.appendChild(songName);
    songName.appendChild(link);


    let songNameText = track.name;
    link.textContent = songNameText;

    let albumName = document.createElement("td");
    let albumLink = document.createElement("a");
    albumLink.setAttribute("href", "albumChild.html");
    albumLink.setAttribute("style", "color: white; font-size: x-large;")
    albumLink.appendChild(albumName);
    row.appendChild(albumName);
    albumName.appendChild(albumLink);

    let albumNameText = album.name;
    albumLink.textContent = albumNameText;
}

function feedbackToUser() {
    let searchBarRow = document.getElementById("no-results");
    searchBarRow.setAttribute("style", "display: inline");
}



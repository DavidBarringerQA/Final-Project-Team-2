const idString = new URLSearchParams(window.location.search)
let playlistId = idString.get('id');

fetch(`http://localhost:8082/playlists/read/` + playlistId)
    .then((response) => {
        if (response.status !== 200) {
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json()
            .then(data => readAllItems(data))
    }).catch((err) => console.error(`Fetch Error: ${err}`));

function readAllItems(data) {

    // Read playlist name to jumbotron
    let mainContainer = document.getElementById("main-container");
    let jumbo = document.createElement("div");
    jumbo.setAttribute("class", "jumbotron jumbotron-fluid");
    mainContainer.appendChild(jumbo);
    let container = document.createElement("div");
    container.setAttribute("class", "container");
    jumbo.appendChild(container);
    let row = document.createElement("div");
    row.setAttribute("class", "row");
    container.appendChild(row);
    let col = document.createElement("div");
    col.setAttribute("class", "col-md-12");
    row.appendChild(col);
    let playlistName = document.createElement("h4");
    playlistName.setAttribute("id", data.id);
    playlistName.setAttribute("class", "display-3");
    col.appendChild(playlistName);
    playlistName.textContent = data.name;

    // image
    let subContainer = document.getElementById("playlist-list");
    let subCol = document.createElement("div");
    subCol.setAttribute("class", "col");
    subContainer.appendChild(subCol);
    let link = document.createElement("a");
    link.setAttribute("href", "#")
    link.setAttribute("class", "playlist");
    subCol.appendChild(link);
    let card = document.createElement("div");
    card.setAttribute("class", "card");
    card.setAttribute("id", "playlist-card");
    link.appendChild(card);
    let image = document.createElement("img");
    image.setAttribute("id", "playlist-image");
    image.setAttribute("src", "../img/playlist/" + data.artwork + ".png")
    image.setAttribute("alt", "...");
    image.setAttribute("class", "card-img-top");
    card.appendChild(image);
    let playlistNameDiv = document.createElement("div");
    playlistNameDiv.setAttribute("class", "centered");
    playlistNameDiv.setAttribute("id", "album(" + data.id + ")");
    let playlistNameCard = document.createElement("h1");
    playlistNameCard.setAttribute("style", "color: black");

    }

    fetch(`http://localhost:8082/tracks/read/`)
    .then((response) => {
        if (response.status !== 200) {
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json()
            .then(data => readTracks(data))
    }).catch((err) => console.error(`Fetch Error: ${err}`));

    function readTracks(data) {
    //read tracks
    for(i = 0; i < data.length; i++) {
    if (Object.keys(data[i].playlist) == playlistId) {
    let songCol = document.getElementById("playlist-songs");
    let list = document.createElement("div");
    list.setAttribute("class", "list-group");
    list.setAttribute("id", "list-tab");
    list.setAttribute("role", "tablist");
    songCol.append(list);

    let song = document.createElement("a");
    song.setAttribute("class", "list-group-item list-group-item-action list-group-item-secondary");
    song.setAttribute("id", "songname");
    song.setAttribute("href", "trackChild.html?id=" + data[i].id);
    song.setAttribute("role", "tab");
    song.textContent = data[i].name;
    list.append(song);
    }
    }
    }
    

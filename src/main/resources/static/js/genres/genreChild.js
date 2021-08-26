const idString = new URLSearchParams(window.location.search)
let genreId = idString.get('id');

fetch(`http://localhost:8082/genres/read/` + genreId)
    .then((response) => {
        if (response.status !== 200) {
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json()
            .then(data => readAllItems(data))
    }).catch((err) => console.error(`Fetch Error: ${err}`));

function readAllItems(data) {

    // Read genre name to jumbotron
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
    col.setAttribute("class", "col-md-6");
    row.appendChild(col);
    let genreName = document.createElement("h4");
    genreName.setAttribute("class", "display-3");
    col.appendChild(genreName);
    genreName.textContent = data.name;
    
    }

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
            for(let i = 0; i < data.length; i++) {
                readAlbum(data[i], Object.keys(data[i].album)[0]);
            }
        }
    ).catch((err)=> console.error(`Fetch Error: ${err}`)); 

function readAlbum(track, id) {
    fetch(`http://localhost:8082/albums/read/` + id) 
    .then((response) => {
        if (response.status !== 200) { 
            console.error(`status: ${reponse.status}`);
            return;
        }
        return response.json() 
        .then(data => readAll(track, data))
    }).catch((err)=> console.error(`Fetch Error: ${err}`)); 
}

function readAll(track, album) {
    if(Object.keys(album.genre)[0] == genreId) {
    let container = document.getElementById("track-table");
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
    let link = document.createElement("a");
    link.setAttribute("id", track.id);
    link.setAttribute("class", "tracks");
    link.setAttribute("href", "trackChild.html");
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
    albumLink.textContent =  albumNameText;
    }
}
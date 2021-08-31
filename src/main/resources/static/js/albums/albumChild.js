const idString = new URLSearchParams(window.location.search)
let albumId = idString.get('id');

fetch(`http://localhost:8082/albums/read/` + albumId)
    .then((response) => {
        if (response.status !== 200) {
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json()
            .then(data => readAllItems(data))
    }).catch((err) => console.error(`Fetch Error: ${err}`));

function readAllItems(data) {

    // Read album name to jumbotron
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
    let albumName = document.createElement("h4");
    albumName.setAttribute("class", "display-3");
    col.appendChild(albumName);
    albumName.textContent = data.name;

    let artistLink = document.createElement("a");
    artistLink.setAttribute("href", "artistChild.html?id=" + Object.keys(data.artist));
    let artistNamePrint = document.createElement("h5");
    artistNamePrint.setAttribute("class", "display-5");
    artistNamePrint.setAttribute("style", "color: gray");
    artistNamePrint.textContent = Object.values(data.artist);
    col.appendChild(artistLink);
    artistLink.appendChild(artistNamePrint);

    // image
    let subContainer = document.getElementById("album-list");
    let subCol = document.createElement("div");
    subCol.setAttribute("class", "col");
    subContainer.appendChild(subCol);
    let link = document.createElement("a");
    link.setAttribute("href", "#")
    link.setAttribute("class", "album");
    subCol.appendChild(link);
    let card = document.createElement("div");
    card.setAttribute("class", "card");
    card.setAttribute("id", "album-card");
    link.appendChild(card);
    let image = document.createElement("img");
    image.setAttribute("id", "album-image");
    image.setAttribute("src", "../img/albums/" + data.name + ".png")
    image.setAttribute("alt", "...");
    image.setAttribute("class", "card-img-top");
    card.appendChild(image);
    let albumNameDiv = document.createElement("div");
    albumNameDiv.setAttribute("class", "centered");
    albumNameDiv.setAttribute("id", "album(" + data.id + ")");
    let albumNameCard = document.createElement("h1");
    albumNameCard.setAttribute("style", "color: black");

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
    if (Object.keys(data[i].album) == albumId) {
    let songCol = document.getElementById("album-songs");
    let list = document.createElement("div");
    list.setAttribute("class", "list-group");
    list.setAttribute("id", "list-tab");
    list.setAttribute("role", "tablist");
    songCol.append(list);

    let song = document.createElement("a");
    song.setAttribute("class", "list-group-item list-group-item-action");
    song.setAttribute("id", "songname");
    song.setAttribute("href", "trackChild.html?id=" + data[i].id);
    song.setAttribute("role", "tab");
    song.textContent = data[i].name;
    list.append(song);
    }
    }
    }
    

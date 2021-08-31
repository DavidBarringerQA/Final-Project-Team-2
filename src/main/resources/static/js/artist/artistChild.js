const idString = new URLSearchParams(window.location.search)
let artistId = idString.get('id');

fetch(`http://localhost:8082/artists/read/` + artistId)
    .then((response) => {
        if (response.status !== 200) {
            console.error(`status: ${reponse.status}`);
            return;
        }
        return response.json()
    })
    .then(data => {
        console.log(data);
        readJumbo(data);
        readAlbums(data);

    }).catch((err) => console.error(`Fetch Error: ${err}`));

function readAlbums(artist) {
    fetch(`http://localhost:8082/albums/read`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            return response.json()
        })
        .then(data => {
            for (let i = 0; i < data.length; i++) {
                if (Object.keys(data[i].artist) == artist.id) {
                    readAllItems(data[i], artist)
                }
            }
        }
        ).catch((err) => console.error(`Fetch Error: ${err}`));
}

function readAllItems(album, artist) {
    
    let container = document.getElementById("album-list");
    let col = document.createElement("div");
    col.setAttribute("class", "col");
    container.appendChild(col);
    let link = document.createElement("a");
    link.setAttribute("href", "albumChild.html?id=" + album.id)
    link.setAttribute("class", "album");

    col.appendChild(link);

    let card = document.createElement("div");
    card.setAttribute("class", "card");
    card.setAttribute("id", "album-card");

    link.appendChild(card);

    let image = document.createElement("img");
    image.setAttribute("id", "album-image");
    image.setAttribute("src", "../img/albums/" + album.name + ".png")
    image.setAttribute("alt", "...");
    image.setAttribute("class", "card-img-top");

    card.appendChild(image);

    let albumNameDiv = document.createElement("div");
    albumNameDiv.setAttribute("class", "centered");
    albumNameDiv.setAttribute("id", "album(" + album.id + ")");

    let albumName = document.createElement("h1");
    albumName.setAttribute("style", "color: black");

    let albumArtist = document.createElement("h5");
    albumArtist.setAttribute("style", "color: black");

    let albumNameText = album.name;
    let albumArtistText = "- " + artist.name + " -";
    card.appendChild(albumNameDiv);
    albumNameDiv.appendChild(albumName);
    albumNameDiv.appendChild(albumArtist);
    albumArtist.textContent = albumArtistText;
    albumName.textContent = albumNameText;
}

function readJumbo(artist) {
    // Read artist name to jumbotron
    let mainContainer = document.getElementById("main-container");
    let jumbo = document.createElement("div");
    jumbo.setAttribute("class", "jumbotron jumbotron-fluid");
    mainContainer.appendChild(jumbo);
    let subContainer = document.createElement("div");
    subContainer.setAttribute("class", "container");
    jumbo.appendChild(subContainer);
    let row = document.createElement("div");
    row.setAttribute("class", "row");
    subContainer.appendChild(row);
    let subCol = document.createElement("div");
    subCol.setAttribute("class", "col-md-6");
    row.appendChild(subCol);
    let artistName = document.createElement("h4");
    artistName.setAttribute("class", "display-3");
    subCol.appendChild(artistName);
    artistName.textContent = artist.name;
}


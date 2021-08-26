const idString = new URLSearchParams(window.location.search)
let trackId = idString.get('id');

fetch(`http://localhost:8082/tracks/read/` + trackId)
    .then((response) => {
        if (response.status !== 200) {
            console.error(`status: ${reponse.status}`);
            return;
        }
        return response.json()
    })
    .then(data => { getAlbumGenre(data) 
    }
    ).catch((err) => console.error(`Fetch Error: ${err}`));

function readAll(data, genreName, artistNameText) {

    // Read track name, genre and album to jumbotron
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
    let trackName = document.createElement("h4");
    trackName.setAttribute("class", "display-3");
    col.appendChild(trackName);
    trackName.textContent = data.name;

    let row2 = document.createElement("div");
    row2.setAttribute("class", "row");
    container.appendChild(row2);
    let col2 = document.createElement("div");
    col2.setAttribute("class", "col-md-6");
    row2.appendChild(col2);
    let albumName = document.createElement("h5");
    albumName.setAttribute("class", "display-5");
    albumName.setAttribute("style", "color: gray")
    albumName.textContent = Object.values(data.album) + " (" + genreName + ")";
    col2.appendChild(albumName);

    let row3 = document.createElement("div");
    row3.setAttribute("class", "row");
    container.appendChild(row3);
    let col3 = document.createElement("div");
    col3.setAttribute("class", "col-md-6");
    row3.appendChild(col3);
    let artistName = document.createElement("h6");
    artistName.setAttribute("class", "display-6");
    artistName.setAttribute("style", "color: gray")
    artistName.textContent = "- " + artistNameText + " -";
    col3.appendChild(artistName);
    //

    //read lyrics
    let trackRow = document.getElementById("track-row");
    let textArea = document.createElement("div");
    textArea.setAttribute("class", "form-group shadow-textarea");
    trackRow.appendChild(textArea);
    let header = document.createElement("h2");
    header.setAttribute("for", "textArea");
    header.textContent = "Lyrics";
    textArea.appendChild(header);
    let lyricsTextArea = document.createElement("textarea");
    lyricsTextArea.setAttribute("class", "form-control z-depth-1");
    lyricsTextArea.setAttribute("id", "textArea");
    lyricsTextArea.setAttribute("rows", "8");
    lyricsTextArea.setAttribute("style", "overflow:scroll");
    lyricsTextArea.setAttribute("placeholder", "lyrics");
    lyricsTextArea.textContent = data.lyrics;
    textArea.appendChild(lyricsTextArea);
    //

}

function getAlbumGenre(track) {
    fetch(`http://localhost:8082/albums/read/` + Object.keys(track.album))
    .then((response) => {
        if (response.status !== 200) {
            console.error(`status: ${reponse.status}`);
            return;
        }
        return response.json()
    })
    .then(data => {
        readAll(track, Object.values(data.genre), Object.values(data.artist));
    }
    ).catch((err) => console.error(`Fetch Error: ${err}`));
}






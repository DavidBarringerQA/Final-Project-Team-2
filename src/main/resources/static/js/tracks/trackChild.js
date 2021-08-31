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

function readAll(data, genreID, genreName, artistNameText, artistID) {

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
    let albumLink = document.createElement("a");
    albumLink.setAttribute("href", "albumChild.html?id=" + Object.keys(data.album));
    let albumName = document.createElement("h5");
    albumName.setAttribute("class", "display-5");
    albumName.setAttribute("style", "color: gray");
    albumName.textContent = Object.values(data.album);
    col2.appendChild(albumLink);
    albumLink.appendChild(albumName);

    let row4 = document.createElement("div");
    row4.setAttribute("class", "row");
    container.appendChild(row4);
    let col4 = document.createElement("div");
    col4.setAttribute("class", "col-md-6");
    row4.appendChild(col4);
    let genreLink = document.createElement("a");
    genreLink.setAttribute("href", "genreChild.html?id=" + genreID);
    let genreNamePrint = document.createElement("h5");
    genreNamePrint.setAttribute("class", "display-5");
    genreNamePrint.setAttribute("style", "color: gray");
    genreNamePrint.textContent = genreName;
    col4.appendChild(genreLink);
    genreLink.appendChild(genreNamePrint);


    let row3 = document.createElement("div");
    row3.setAttribute("class", "row");
    container.appendChild(row3);
    let col3 = document.createElement("div");
    col3.setAttribute("class", "col-md-6");
    row3.appendChild(col3);
    let artistLink = document.createElement("a");
    artistLink.setAttribute("href", "artistChild.html?id=" + artistID);
    let artistNamePrint = document.createElement("h5");
    artistNamePrint.setAttribute("class", "display-5");
    artistNamePrint.setAttribute("style", "color: gray");
    artistNamePrint.textContent = "- " + artistNameText + " -";
    col3.appendChild(artistLink);
    artistLink.appendChild(artistNamePrint);
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
        readAll(track, Object.keys(data.genre), Object.values(data.genre), Object.values(data.artist), Object.keys(data.artist));
    }
    ).catch((err) => console.error(`Fetch Error: ${err}`));
}






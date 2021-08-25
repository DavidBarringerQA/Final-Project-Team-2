fetch(`http://localhost:8082/playlists/read`) 
    .then((response) => {
        if (response.status !== 200) { 
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() 
        .then(data => readAllItems(data))
    }).catch((err)=> console.error(`Fetch Error: ${err}`)); 

function readAllItems(data) {
    let container = document.getElementById("playlist-list");
    for(let i = 0; i < data.length; i++) {
    let col = document.createElement("div");
    col.setAttribute("class", "col");
    container.appendChild(col);

    let link = document.createElement("a");
    link.setAttribute("href", "playlist.html")
    link.setAttribute("class", "playlist");

    col.appendChild(link);

    let card = document.createElement("div");
    card.setAttribute("class", "card");
    card.setAttribute("id", "playlist-card");

    link.appendChild(card);

    let image = document.createElement("img");
    image.setAttribute("id", "playlist-image");
    image.setAttribute("src", "/img/playlist/" + data[i].artwork + ".png")
    image.setAttribute("alt", "...");
    image.setAttribute("class", "card-img-top");

    card.appendChild(image);

    let playlistNameDiv = document.createElement("div");
    playlistNameDiv.setAttribute("class", "centered");
    playlistNameDiv.setAttribute("id", "playlist(" + data[i].id + ")");

    let playlistName = document.createElement("h1");
    playlistName.setAttribute("style", "color: black");

    let playlistDes = document.createElement("h3");
    playlistDes.setAttribute("style", "color: black");

    let playlistNameText = data[i].name;
    let playlistDesText = data[i].description;
    card.appendChild(playlistNameDiv);
    playlistNameDiv.appendChild(playlistName);
    playlistNameDiv.appendChild(playlistDes);
    playlistDes.textContent = playlistDesText;
    playlistName.textContent = playlistNameText; 
    }

}
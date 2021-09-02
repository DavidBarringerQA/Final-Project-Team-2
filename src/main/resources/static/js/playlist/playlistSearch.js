var submitSearch = document.getElementById("search-btn");
submitSearch.addEventListener("click", myFunction);

function myFunction() {
    fetch(`http://localhost:8082/playlists/read`) 
    .then((response) => {
        if (response.status !== 200) { 
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() 
        .then(data => readFromSearch(data))
    }).catch((err)=> console.error(`Fetch Error: ${err}`));
}

function readFromSearch(data) {
    // Declare variables
    var input, filter, parent;
    input = document.getElementById('search-input');
    filter = input.value.toLowerCase();

    searchList = document.getElementById("search-list");
    playlistList = document.getElementById("playlist-list");

    parent = document.getElementById("body-container");
    parent.replaceChild(searchList, playlistList);
    let container = document.getElementById("search-list");

    for(let i = 0; i < data.length; i++) {
    console.log("data i to lower case: " + data[i].name.toLowerCase());
    console.log("filter entry: " + filter)
    if(data[i].name.toLowerCase() == filter) {
    console.log("I met the if statement: " + data[i].name.toLowerCase());
    let col = document.createElement("div");
    col.setAttribute("class", "col");
    container.appendChild(col);

    let link = document.createElement("a");
    link.setAttribute("href", "playlist.html?id=" + data[i].id)
    link.setAttribute("class", "playlist");

    col.appendChild(link);

    let card = document.createElement("div");
    card.setAttribute("class", "card");
    card.setAttribute("id", "playlist-card");

    link.appendChild(card);

    let image = document.createElement("img");
    image.setAttribute("id", "playlist-image");
    image.setAttribute("src", "../img/playlist/" + data[i].artwork + ".png")
    image.setAttribute("alt", "...");
    image.setAttribute("class", "card-img-top");

    card.appendChild(image);

    let playlistNameDiv = document.createElement("div");
    playlistNameDiv.setAttribute("class", "centered");
    playlistNameDiv.setAttribute("id", data[i].id);

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

}
  
  
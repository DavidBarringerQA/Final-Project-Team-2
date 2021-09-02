var submitSearch = document.getElementById("search-btn");
submitSearch.addEventListener("click", myFunction);

function myFunction() {
    fetch(`http://localhost:8082/albums/read`)
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
                let input = document.getElementById('search-input');
                let filter = input.value.toLowerCase();
                console.log("Data i name: " + data[i].name);
                console.log("entry name: " + filter);
                if(filter == data[i].name.toLowerCase()){
                readArtistSearch(data[i], Object.keys(data[i].artist)[0]);
                }
            }
        }
    ).catch((err) => console.error(`Fetch Error: ${err}`));
 }

function readArtistSearch(album, id) {
    fetch(`http://localhost:8082/artists/read/` + id)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            return response.json()
                .then(data => {
                    readAllItemsSearch(album, data)
                    console.log(data)
                })
        }).catch((err) => console.error(`Fetch Error: ${err}`));
}

function readAllItemsSearch(album, artist) {

    let searchList = document.getElementById("search-list");
    let albumList = document.getElementById("album-list")
    let parent = document.getElementById("body-container");

    console.log(parent);
    console.log(searchList);
    console.log(albumList);
    parent.replaceChild(searchList, albumList);

    let container = document.getElementById("search-list");
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



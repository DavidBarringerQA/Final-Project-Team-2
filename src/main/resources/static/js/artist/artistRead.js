fetch(`http://localhost:8082/artists/read`) 
    .then((response) => {
        if (response.status !== 200) { 
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() 
        .then(data => readAllItems(data))
    }).catch((err)=> console.error(`Fetch Error: ${err}`)); 

function readAllItems(data) {
    let container = document.getElementById("artist-list");
    for(let i = 0; i < data.length; i++) {
    let col = document.createElement("div");
    col.setAttribute("class", "col");
    container.appendChild(col);

    let link = document.createElement("a");
    link.setAttribute("href", "artistChild?id=" + data[i].id)
    link.setAttribute("class", "artist");

    col.appendChild(link);

    let card = document.createElement("div");
    card.setAttribute("class", "card");
    card.setAttribute("id", "artist-card");

    link.appendChild(card);

    let image = document.createElement("img");
    image.setAttribute("id", "artist-image");
    image.setAttribute("src", "../img/artist/" + data[i].name + ".png")
    image.setAttribute("alt", "...");
    image.setAttribute("class", "card-img-top");

    card.appendChild(image);

    let artistNameDiv = document.createElement("div");
    artistNameDiv.setAttribute("class", "centered");
    artistNameDiv.setAttribute("id", data[i].id);

    let artistName = document.createElement("h1");
    artistName.setAttribute("style", "color: black");

    let artistDes = document.createElement("h3");
    artistDes.setAttribute("style", "color: black");

    let artistNameText = data[i].name;
    let artistDesText = data[i].description;
    card.appendChild(artistNameDiv);
    artistNameDiv.appendChild(artistName);
    artistNameDiv.appendChild(artistDes);
    artistDes.textContent = artistDesText;
    artistName.textContent = artistNameText; 
    }

}

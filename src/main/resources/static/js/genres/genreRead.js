fetch(`http://localhost:8082/genres/read`) 
    .then((response) => {
        if (response.status !== 200) { 
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() 
        .then(data => readAllItems(data))
    }).catch((err)=> console.error(`Fetch Error: ${err}`)); 

function readAllItems(data) {
    let container = document.getElementById("genre-list");
    for(let i = 0; i < data.length; i++) {
    let col = document.createElement("div");
    col.setAttribute("class", "col");
    container.appendChild(col);

    let link = document.createElement("a");
    link.setAttribute("href", "genreChild.html")
    link.setAttribute("class", "genre");

    col.appendChild(link);

    let card = document.createElement("div");
    card.setAttribute("class", "card");
    card.setAttribute("id", "genre-card");

    link.appendChild(card);

    let image = document.createElement("img");
    image.setAttribute("id", "genre-image");
    image.setAttribute("src", "/img/genres/" + data[i].name + ".png")
    image.setAttribute("alt", "...");
    image.setAttribute("class", "card-img-top");

    card.appendChild(image);

    let genreNameDiv = document.createElement("div");
    genreNameDiv.setAttribute("class", "centered");
    genreNameDiv.setAttribute("id", "genre(" + data[i].id + ")");

    let genreName = document.createElement("h1");
    genreName.setAttribute("style", "color: black");

    let genreDes = document.createElement("h3");
    genreDes.setAttribute("style", "color: black");

    let genreNameText = data[i].name;
    let genreDesText = data[i].description;
    card.appendChild(genreNameDiv);
    genreNameDiv.appendChild(genreName);
    genreNameDiv.appendChild(genreDes);
    genreDes.textContent = genreDesText;
    genreName.textContent = genreNameText; 
    }

}
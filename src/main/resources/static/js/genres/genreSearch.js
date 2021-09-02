var submitSearch = document.getElementById("search-btn");
submitSearch.addEventListener("click", myFunction);

function myFunction() {
    fetch(`http://localhost:8082/genres/read`) 
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
    let results = false;

    for(let i = 0; i < data.length; i++) {
    console.log("data i to lower case: " + data[i].name.toLowerCase());
    console.log("filter entry: " + filter)
    if(data[i].name.toLowerCase() == filter) {
    results = true;
    let searchBarRow = document.getElementById("no-results");
    searchBarRow.setAttribute("style", "display: none");
    searchList = document.getElementById("search-list");
    genreList = document.getElementById("genre-list");
    
    parent = document.getElementById("body-container");
    parent.replaceChild(searchList, genreList);
    let container = document.getElementById("search-list");
    console.log("I met the if statement: " + data[i].name.toLowerCase());
    let col = document.createElement("div");
    col.setAttribute("class", "col");
    container.appendChild(col);

    let link = document.createElement("a");
    link.setAttribute("href", "genreChild.html?id=" + data[i].id)
    link.setAttribute("class", "genre");

    col.appendChild(link);

    let card = document.createElement("div");
    card.setAttribute("class", "card");
    card.setAttribute("id", "genre-card");

    link.appendChild(card);

    let image = document.createElement("img");
    image.setAttribute("id", "genre-image");
    image.setAttribute("src", "../img/genres/" + data[i].name + ".png")
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

if(!results) {
    feedbackToUser();
}

}

function feedbackToUser() {
    let searchBarRow = document.getElementById("no-results");
    searchBarRow.setAttribute("style", "display: inline");
}
  
  
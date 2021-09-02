var submitSearch = document.getElementById("search-btn");
submitSearch.addEventListener("click", myFunction);

function myFunction() {
    fetch(`http://localhost:8082/artists/read`) 
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
    let searchList = document.getElementById("search-list");
    let artistList = document.getElementById("artist-list");
    
    parent = document.getElementById("body-container");
    parent.replaceChild(searchList, artistList);
    let container = document.getElementById("search-list");
    console.log("I met the if statement: " + data[i].name.toLowerCase());
    let col = document.createElement("div");
    col.setAttribute("class", "col");
    container.appendChild(col);

    let link = document.createElement("a");
    link.setAttribute("href", "artistChild.html?id=" + data[i].id)
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

if(!results) {
    feedbackToUser();
}

}

function feedbackToUser() {
    let searchBarRow = document.getElementById("no-results");
    searchBarRow.setAttribute("style", "display: inline");
}

  
  
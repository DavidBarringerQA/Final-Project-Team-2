fetch(`http://localhost:8082/artists/read`) 
    .then((response) => {
        if (response.status !== 200) { 
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() 
        .then(data => readAllArtists(data))
}).catch((err)=> console.error(`Fetch Error: ${err}`)); 

 
    fetch(`http://localhost:8082/genres/read`) 
    .then((response) => {
        if (response.status !== 200) { 
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() 
        .then(data => readAllGenres(data))
    }).catch((err)=> console.error(`Fetch Error: ${err}`)); 


function readAllArtists(data) {

    let dropdownArtist = document.getElementById("read-artists");
    for (i = 0; i < data.length; i++) {
        let select = document.createElement("a");
        select.setAttribute("class", "dropdown-item dropdown-item-secondary");
        select.setAttribute("id", data[i].id);
        select.textContent = data[i].name;
        dropdownArtist.appendChild(select);
    }

};

function readAllGenres(genre_data) {

    let dropdownGenre = document.getElementById("read-genres");
    for (i = 0; i < genre_data.length; i++) {
        let selection = document.createElement("a");
        selection.setAttribute("class", "dropdown-item dropdown-item-secondary");
        selection.setAttribute("id", genre_data[i].id);
        selection.textContent = genre_data[i].name;
        dropdownGenre.appendChild(selection);
    }

};
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

    let dropdownArtist = document.getElementById("read-artists-add");
    for (i = 0; i < data.length; i++) {
        let option = document.createElement("option");
        option.setAttribute("value", data[i].id);
        option.textContent = data[i].name;
        dropdownArtist.appendChild(option);
    }

    let dropdownArtistUpdate = document.getElementById("read-artists-update");
    for (i = 0; i < data.length; i++) {
        let optionUpdate = document.createElement("option");
        optionUpdate.setAttribute("value", data[i].id);
        optionUpdate.textContent = data[i].name;
        dropdownArtistUpdate.appendChild(optionUpdate);
    }

};

function readAllGenres(genre_data) {

    let dropdownGenre = document.getElementById("read-genres-add");
    for (i = 0; i < genre_data.length; i++) {
        let option = document.createElement("option");
        option.setAttribute("value", genre_data[i].id);
        option.textContent = genre_data[i].name;
        dropdownGenre.appendChild(option);
    }

    let dropdownGenreUpdate = document.getElementById("read-genres-update");
    for (i = 0; i < genre_data.length; i++) {
        let optionUpdate = document.createElement("option");
        optionUpdate.setAttribute("value", genre_data[i].id);
        optionUpdate.textContent = genre_data[i].name;
        dropdownGenreUpdate.appendChild(optionUpdate);
    }

};
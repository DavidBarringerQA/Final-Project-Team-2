fetch(`http://localhost:8082/albums/read`) 
    .then((response) => {
        if (response.status !== 200) { 
            console.error(`status: ${reponse.status}`);
            return;
        }
        response.json() 
        .then(data => readAllAlbums(data))
}).catch((err)=> console.error(`Fetch Error: ${err}`)); 

function readAllAlbums(data) {

    let dropdownAlbum = document.getElementById("add-album");
    for (i = 0; i < data.length; i++) {
        let option = document.createElement("option");
        option.setAttribute("value", data[i].id);
        option.textContent = data[i].name;
        dropdownAlbum.appendChild(option);
    }

    let dropdownAlbumUpdate = document.getElementById("update-album");
    for (i = 0; i < data.length; i++) {
        let optionUpdate = document.createElement("option");
        optionUpdate.setAttribute("value", data[i].id);
        optionUpdate.textContent = data[i].name;
        dropdownAlbumUpdate.appendChild(optionUpdate);
    }

};
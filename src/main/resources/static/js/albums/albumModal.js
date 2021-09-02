var submitAdd = document.getElementById("add-inputs");
submitAdd.addEventListener("click", readInputsFromAlbumAdd);
var submitUpdate = document.getElementById("update-inputs");
submitUpdate.addEventListener("click", readInputsFromAlbumUpdate);
var submitUpdate = document.getElementById("delete-inputs");
submitUpdate.addEventListener("click", deleteInputsFromAlbumUpdate);

function readInputsFromAlbumAdd() {
    var nameVal = $('#add-name').val();
    let albumName = document.getElementById("album-name");
    albumName.textContent = "Album Name: " + nameVal;
    console.log(nameVal);
    
    var artist = document.getElementById("read-artists-add");
    var artistVal = artist.options[artist.selectedIndex].text;
    let artistName = document.getElementById("artist-name");
    artistName.textContent = "Artist: " + artistVal;
    console.log(artistVal);

    var genre = document.getElementById("read-genres-add");
    var genreVal = genre.options[genre.selectedIndex].text;
    let genreName = document.getElementById("genre-name");
    genreName.textContent = "Genre: " + genreVal;
    console.log(genreVal);
};

function readInputsFromAlbumUpdate() {
    var nameOldVal = $('#old-name').val();
    let albumOldName = document.getElementById("album-name-old");
    albumOldName.textContent = "Album Name: " + nameOldVal;
    console.log(nameOldVal);

    var nameVal = $('#update-name').val();
    let albumName = document.getElementById("album-name-update");
    albumName.textContent = "Album Name: " + nameVal;
    console.log(nameVal);
    
    var artist = document.getElementById("read-artists-update");
    var artistVal = artist.options[artist.selectedIndex].text;
    let artistName = document.getElementById("artist-name-update");
    artistName.textContent = "Artist: " + artistVal;
    console.log(artistVal);

    var genre = document.getElementById("read-genres-update");
    var genreVal = genre.options[genre.selectedIndex].text;
    let genreName = document.getElementById("genre-name-update");
    genreName.textContent = "Genre: " + genreVal;
    console.log(genreVal);
};

function deleteInputsFromAlbumUpdate() {
    var nameVal = $('#delete-name').val();

    let albumName = document.getElementById("delete-entry");
    albumName.textContent = "Album: " + nameVal;
    
};


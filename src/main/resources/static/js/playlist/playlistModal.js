var submitAdd = document.getElementById("add-inputs");
submitAdd.addEventListener("click", readInputs);
var submitDelete = document.getElementById("delete-inputs");
submitDelete.addEventListener("click", deleteInputs);
var submitUpdate = document.getElementById("update-inputs");
submitUpdate.addEventListener("click", updateInputs);

function readInputs() {
    var nameValAdd = $('#add-name').val();
    var descriptionValAdd = $('#add-description').val();

    let playlistNameAdd = document.getElementById("playlist-name-add");
    playlistNameAdd.textContent = "Name: " + nameValAdd;
    console.log(nameValAdd);

    let albumDesAdd = document.getElementById("playlist-description-add");
    albumDesAdd.textContent = "Description: " + descriptionValAdd;
    console.log(descriptionValAdd);
};

function deleteInputs() {
    var nameValDelete = $('#delete-name').val();

    let playlistNameDelete = document.getElementById("delete-entry");
    playlistNameDelete.textContent = "Playlist: " + nameValDelete;
};

function updateInputs() {
    var oldName = $('#update-old-name').val();
    var nameValUpdate = $('#update-name').val();
    var descriptionValUpdate = $('#update-description').val();

    let playlistNameOld = document.getElementById("old-name");
    playlistNameOld.textContent =  oldName;
    console.log(oldName);

    let playlistNameUpdate = document.getElementById("update-to");
    playlistNameUpdate.textContent = "Name: " + nameValUpdate;
    console.log(nameValUpdate);

    let playlistDesUpdate = document.getElementById("update-des");
    playlistDesUpdate.textContent = "Description: " + descriptionValUpdate;
    console.log(descriptionValUpdate);
};

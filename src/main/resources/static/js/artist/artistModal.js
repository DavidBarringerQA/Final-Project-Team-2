var submitAdd = document.getElementById("add-inputs");
submitAdd.addEventListener("click", readInputs);
var submitDelete = document.getElementById("delete-inputs");
submitDelete.addEventListener("click", deleteInputs);
var submitUpdate = document.getElementById("update-inputs");
submitUpdate.addEventListener("click", updateInputs);

function readInputs() {
    var nameVal = $('#add-name').val();

    let artistName = document.getElementById("artist-name");
    artistName.textContent = "Artist: " + nameVal;
    console.log(artistName);
};

function deleteInputs() {
    var nameVal = $('#delete-name').val();

    let artistName = document.getElementById("delete-entry");
    artistName.textContent = "Artist: " + nameVal;
    
};

function updateInputs() {
    var oldVal = $('#update-name').val();
    var newVal = $('#new-name').val();

    let oldName = document.getElementById("old-name");
    oldName.textContent = oldVal;

    let newName = document.getElementById("update-to");
    newName.textContent = "Artist Name: " + newVal;
    
};
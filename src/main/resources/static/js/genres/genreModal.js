var submitAdd = document.getElementById("add-inputs");
submitAdd.addEventListener("click", readInputs);
var submitDelete = document.getElementById("delete-inputs");
submitDelete.addEventListener("click", deleteInputs);
var submitUpdate = document.getElementById("update-inputs");
submitUpdate.addEventListener("click", updateInputs);

function readInputs() {
    var nameVal = $('#add-name').val();
    var descriptionVal = $('#add-description').val();

    let genreName = document.getElementById("genre-name");
    genreName.textContent = "Genre: " + nameVal;
    console.log(genreName);

    let genreDes = document.getElementById("genre-description");
    genreDes.textContent = "Description: " + descriptionVal;
    console.log(genreDes);
};

function deleteInputs() {
    var nameVal = $('#delete-name').val();

    let genreName = document.getElementById("delete-entry");
    genreName.textContent = "Genre: " + nameVal;
    
};

function updateInputs() {
    var oldVal = $('#update-name').val();
    var newVal = $('#new-name').val();
    var descVal = $('#update-description').val();

    let oldName = document.getElementById("old-name");
    oldName.textContent = oldVal;

    let newName = document.getElementById("update-to");
    newName.textContent = "Genre Name: " + newVal;

    let newDes = document.getElementById("update-des");
    newDes.textContent = "Genre Description: " + descVal;
    
};
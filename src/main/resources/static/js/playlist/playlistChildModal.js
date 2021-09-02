var submitAdd = document.getElementById("add-inputs");
submitAdd.addEventListener("click", readInputs);
var submitDelete = document.getElementById("delete-inputs");
submitDelete.addEventListener("click", deleteInputs);

function readInputs() {
    var selection = document.getElementById("read-tracks-playlist");
    var nameValAdd = selection.options[selection.selectedIndex].text;

    let trackNameAdd = document.getElementById("track-add");
    trackNameAdd.textContent = "Track: " + nameValAdd;
    console.log(nameValAdd);
};

function deleteInputs() {
    var selection = document.getElementById("read-this-playlist");
    var nameValDelete = selection.options[selection.selectedIndex].text;

    let trackNameDelete = document.getElementById("delete-entry");
    trackNameDelete.textContent = "Track: " + nameValDelete;
};


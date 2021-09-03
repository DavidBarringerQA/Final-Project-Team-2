var submitAdd = document.getElementById("add-inputs");
submitAdd.addEventListener("click", readInputsFromTrackAdd);
var submitDelete = document.getElementById("delete-inputs");
submitDelete.addEventListener("click", deleteInputsFromTrack);
var submitUpdate = document.getElementById("update-inputs");
submitUpdate.addEventListener("click", updateInputsFromTrack);

function readInputsFromTrackAdd() {

    var trackNameValue = $('#add-name').val();
    let trackName = document.getElementById("track-name");
    trackName.textContent = "Track Name: " + trackNameValue;
    console.log(trackNameValue);

    var trackDurationValue = $('#add-duration').val();
    let trackDuration = document.getElementById("track-duration");
    trackDuration.textContent = "Track Duration: " + trackDurationValue;
    console.log(trackDurationValue);
    
    var trackLyricValue = $('#add-lyrics').val();
    let trackLyric = document.getElementById("track-lyrics");
    trackLyric.textContent = "Track Lyrics: " + trackLyricValue;
    console.log(trackLyricValue);

    var trackAlbumVal = document.getElementById("add-album");
    var trackVal = trackAlbumVal.options[trackAlbumVal.selectedIndex].text;
    let trackAlbum = document.getElementById("track-album");
    trackAlbum.textContent = "Track Album: " + trackVal;
    console.log(trackVal);
};

function deleteInputsFromTrack() {

    var deleteNameVal = $('#delete-name').val();

    let trackDeleteName = document.getElementById("delete-entry");
    trackDeleteName.textContent = "Track: " + deleteNameVal;

};

function updateInputsFromTrack() {

    var nameOldVal = $('#old-track').val();
    let albumOldName = document.getElementById("track-name-old");
    albumOldName.textContent = nameOldVal;
    console.log(nameOldVal);

    var nameVal = $('#update-name').val();
    let trackName = document.getElementById("track-name-update");
    trackName.textContent = "Track Name: " + nameVal;
    console.log(nameVal);

    var trackDurationValue = $('#update-duration').val();
    let trackDuration = document.getElementById("track-duration-update");
    trackDuration.textContent = "Track Duration: " + trackDurationValue;
    console.log(trackDurationValue);
    
    var trackLyricValue = $('#update-lyrics').val();
    let trackLyric = document.getElementById("track-lyric-update");
    trackLyric.textContent = "Track Lyrics: " + trackLyricValue;
    console.log(trackLyricValue);

    var album = document.getElementById("update-album");
    var albumVal = album.options[album.selectedIndex].text;
    let albumName = document.getElementById("track-album-update");
    albumName.textContent = "Album: " + albumVal;
    console.log(albumVal);

};
var submit = document.getElementById("add-btn");
submit.addEventListener("click", postReq);

function postReq() {
    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/tracks/create");
    req.onload = () => {
        if (req.status === 201) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }
    var nameVal = $('#add-name').val();
    var durationVal = parseInt($('#add-duration').val());
    var lyricsVal = $('#add-lyrics').val();
    var album = document.getElementById("add-album");
    var albumVal = album.options[album.selectedIndex].value;

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let data = {
         "name": nameVal, 
         "duration": durationVal, 
         "lyrics": lyricsVal, 
         "album": {
         "id": albumVal, 
         }
        };
    console.log(data);
    req.send(JSON.stringify(data));

    let modal = document.getElementById("close-modal");
    modal.click();
};

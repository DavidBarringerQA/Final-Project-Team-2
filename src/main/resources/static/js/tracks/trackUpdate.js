var submit = document.getElementById("update-btn");
submit.addEventListener("click", updateReq);

function updateReq() {
    let id = document.querySelector("#update-id").value;
    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/tracks/update/" + id);
    req.onload = () => {
        if (req.status === 202) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }

    var nameVal = $('#update-name').val();
    var durationVal = parseInt($('#update-duration').val());
    var lyricsVal = $('#update-lyrics').val();
    var albumVal = parseInt($('#update-album').val());

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let data = {
         "id": id,
         "name": nameVal, 
         "duration": durationVal, 
         "lyrics": lyricsVal, 
         "album": {
         "id": albumVal, 
         }
        };
    console.log(data);
    req.send(JSON.stringify(data));

    // let modal = document.getElementById("close-modal");
    // modal.click();
};

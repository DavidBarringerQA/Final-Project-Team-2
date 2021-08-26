var submit = document.getElementById("update-btn");
submit.addEventListener("click", updateReq);

function updateReq() {
    let id = document.querySelector("#update-id").value;
    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/albums/update/" + id);
    req.onload = () => {
        if (req.status === 202) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }

    var nameVal = $('#update-name').val();
    var artistVal = parseInt($('#update-artist').val());
    var genreVal = $('#update-genre').val();

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let data = {
         "cover": nameVal, 
         "name": nameVal, 
         "artist": {
         "id": artistVal, 
         },
         "genre": {
            "id": genreVal, 
            }
        };
    console.log(data);
    req.send(JSON.stringify(data));

    let modal = document.getElementById("close-modal");
    modal.click();
};

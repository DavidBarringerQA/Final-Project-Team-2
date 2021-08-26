var submit = document.getElementById("add-btn");
submit.addEventListener("click", postReq);

function postReq() {
    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/albums/create");
    req.onload = () => {
        if (req.status === 201) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }
    var nameVal = $('#add-name').val();
    var artistVal = parseInt($('#add-artist').val());
    var genreVal = $('#add-genre').val();

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

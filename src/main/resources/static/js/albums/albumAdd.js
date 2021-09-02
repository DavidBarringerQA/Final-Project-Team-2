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
    var artist = document.getElementById("read-artists-add");
    var artistVal = artist.options[artist.selectedIndex].value;
    var genre = document.getElementById("read-genres-add");
    var genreVal = genre.options[genre.selectedIndex].value;

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		req.setRequestHeader("Authorization", `Bearer ${localStorage.getItem("token")}`);
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

var submit = document.getElementById("add-btn");
submit.addEventListener("click", postReq);

function postReq() {
    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/playlists/create");
    req.onload = () => {
        if (req.status === 200) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }
    var nameVal = $('#add-name').val();
    var descriptionVal = $('#add-description').val();

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let data = { name: nameVal, description: descriptionVal, artwork: nameVal };
    req.send(JSON.stringify(data));

    let modal = document.getElementById("close-modal");
    modal.click();
};

var submit = document.getElementById("update-btn");
submit.addEventListener("click", updateReq);

function updateReq() {
    let id = document.querySelector("#update-id").value;
    const req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8082/genres/update/" + id);
    req.onload = () => {
        if (req.status === 202) {
            console.log("Server Responded with: " + req.responseText);
        } else {
            console.log("Oops...");
        }
    }
    var nameVal = $('#update-name').val();
    var descriptionVal = $('#update-description').val();

    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let data = { id: id, name: nameVal, description: descriptionVal };
    req.send(JSON.stringify(data));
    let modal = document.getElementById("close-modal");
    modal.click();
};

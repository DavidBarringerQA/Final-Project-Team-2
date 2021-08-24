const delReq = () => {
    let id = document.querySelector("#delete-id").value;
    console.log(id);
    fetch("http://localhost:8082/genres/delete/" + id, {
        method: 'DELETE'
    }).then((data) => {
        console.log(data)
        if(data.status === 204) {
            console.log("delete success");
        }
    }).catch((error) => {
            console.log("Oops..." + error);
        });
    let modal = document.getElementById("close-modal");
    modal.click();
}

var submit = document.getElementById("delete-btn");
submit.addEventListener("click", delReq);

(() => {
		let registerButton = document.querySelector("#register");
		
		registerButton.addEventListener("click", (event) => {
				event.preventDefault();
				let username = document.querySelector("#floatingInput").value;
				let password = document.querySelector("#floatingPassword").value;
				let passwordConf = document.querySelector("#floatingPasswordConf").value;
				let body = {
						"username": username,
						"password": password
				}
				if(password === passwordConf){
						fetch("http://localhost:8082/register", {
								method: "POST",
								headers: {
										"Content-Type": "application/json"
								},
								body: JSON.stringify(body)
						}).then(response => {
								if(response.status !== 200){
										console.error(response.status);
								} else {
										window.location = `http://${location.hostname}:${location.port}/login`;
								}
						}).catch(err => console.error(err));
				}
		});
})();

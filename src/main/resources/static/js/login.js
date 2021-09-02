(() => {
		let loginButton = document.querySelector("#login");

		loginButton.addEventListener("click", (event) => {
				event.preventDefault();
				let username = document.querySelector("#floatingInput").value;
				let password = document.querySelector("#floatingPassword").value;
				let body = {
						"username": username,
						"password": password
				};
				fetch("http://localhost:8082/auth", {
						method: "POST",
						headers: {
								"Content-type": "application/json"
						},
						body: JSON.stringify(body)
				}).then(response => {
						if(response.status !== 200){
								console.error(response.status);
								return new Promise((resolve, reject) => reject(-1));
						}
						else{
								return response.json();
						}
				}).catch(err => {})
						.then(data => {
								localStorage.setItem("token", data.token);
								setTimeout(() => {
										localStorage.removeItem("token");

								},180000);
								window.location = `http://${location.hostname}:${location.port}/`;
						}).catch(err => {
								console.error(err);
						});
		});
})();

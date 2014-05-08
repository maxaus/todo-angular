function handleTheError(e) {
	var message = "Error! Please, try again.";
	console.log("e ", e);
	if (e && e.data && e.data.error && e.data.error.message) {
		message += " Error info: " + e.data.error.message;
	}
	alert(message);
}
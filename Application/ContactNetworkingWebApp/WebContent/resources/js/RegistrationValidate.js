function confirmSubmit() {
		alert("You are about to submit!");
	}

function validateName() {
	let name = document.getElementById('name').value;

	if (name == "") {
		document.getElementById('nameerror').innerHTML = " Name is MUST";
		document.getElementById('submitbutton').setAttribute('disabled',
				'disabled');
	} else if (name.length < 2) {
		document.getElementById('nameerror').innerHTML = "Username must be between 6 to 12 characters";
		document.getElementById('submitbutton').setAttribute('disabled',
				'disabled');
	} else
		document.getElementById('submitbutton').removeAttribute('disabled');

}

function validateUserName(){
	let userName = document.getElementById('username').value;
	if (userName == "") {
		document.getElementById('usernameerror').innerHTML = " User Name is MUST";
		document.getElementById('submitbutton').setAttribute('disabled','disabled');
	}
	else
		document.getElementById('submitbutton').removeAttribute('disabled');
}

function validateEmail(){
	let email = document.getElementById('email').value;
	if (email == "") {
		document.getElementById('emailerror').innerHTML = "Email is MUST";
		document.getElementById('submitbutton').setAttribute('disabled','disabled');
	}	
	else
		document.getElementById('submitbutton').removeAttribute('disabled');
}

var check = function() {
	  if (document.getElementById('password').value ==
	    document.getElementById('cpassword').value) {
	    document.getElementById('message').style.color = 'green';
	    document.getElementById('message').innerHTML = 'matching';
	  } else {
	    document.getElementById('message').style.color = 'red';
	    document.getElementById('message').innerHTML = 'not matching';
	  }
	}

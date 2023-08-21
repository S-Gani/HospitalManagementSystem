$(document).ready(function(){
	$('#navbar-con').load('Home.html #mynavv');
	//Login form
	$('#error').hide();
    $("#loginButton").on("click", function(event) {
        event.preventDefault(); 
        var username = $('#username').val().trim();
        var password = $('#password').val().trim();
        var module = $('#module').val();
        if (username && password && module) {
            var formData = {
                username: username,
                password: password,
                module: module
            };
      	console.log(formData);
	  	// Ajax call for Login Authentication
      	$.ajax({
			url: 'http://localhost:8080/Hospital_Management_System1/login',
			type: 'POST',
			data: formData,
				success : function(response){
					var data = JSON.parse(response);
					console.log(data["username"]);
					console.log(data["password"]);
					console.log(formData["module"]);
					if(data["username"] === "true" && data["password"] === "true"){
						$("#error").hide();
						if(formData['module']==="User"){
							window.location.href = "UserPage.html";
						}else if(formData['module']==="Admin"){
							window.location.href = "DoctorPage.html";
						}else{
							window.location.href = "AdminNav.html";
						}
						
						return;
					}
					else if(data["username"] === "true" && data["password"] === "false"){
						$("#error").text("Wrong Password");
						$("#error").show();
						return;
					}
					else{
						$("#error").text("Username does not exist");
						$("#error").show();
						return;
					}
			},
				error: function(error) {
				// Handle error response from the server
				console.log(error);
				console.log("Error raised");
				},
	
    	});
	}
	else{
		$("#error").text("Please enter the deatails");
		$("#error").show();
	}
	});
   
	console.log("2");
	//Registration form 
	//Function To validate UserName
    function validateUsername() { 
		var usernameValue = $("#userName").val();
		console.log(usernameValue.length);
      if (usernameValue.length === 0) { 
        $("#userNameError").html("**This field is required"); 
        return false; 
      } else if (usernameValue.length < 3 || usernameValue.length > 20) { 
        $("#userNameError").html("**Length of username must be between 3 and 20"); 
        return false; 
	  } else{
		$("#userNameError").hide()
		return true; 
	  }
     
    }
    // Function to validate phone number 
    function validatePhone() { 
		var phoneNumberValue = $("#phoneNumber").val(); 
      if (phoneNumberValue.length < 10) { 
        $("#phoneNumberError").html("**Phone number must be 10 digits"); 
        return false; 
      } 
      else{
		$("#phoneNumberError").hide()
		return true; 
	  }
    } 
    // Function to validate email 
    function validateEmail() { 
		var email = $('#email').val(); 
      if (email.length === 0) { 
        $("#emailError").html('**This field is required'); 
        return false; 
      } else { 
        var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; 
        var validEmail = regex.test(email); 
      } 
      if (!validEmail) { 
        $('#emailError').html('**Enter a valid email'); 
        return false; 
      } 
      else{
		$("#emailError").hide()
		return true; 
	  }
    } 
   // Function to validate user ID 
   function validateUserId() { 
    var userId = $('#createUserid').val(); 
     if (userId.length < 5) { 
       $("#useridError").html("**User ID must contain at least 5 digits"); 
       return false; 
     } 
     else{
		$("#useridError").hide()
		return true; 
	  } 
   } 
   // Function to validate password 
   function validatePassword() { 
	var password = $('#createPassword').val(); 
     if (password.length < 3 || password.length > 10) { 
       $("#passwordError").html("**Length of your password must be between 3 and 10"); 
       return false; 
     } 
	 else{
		$("#passwordError").hide()
		return true; 
	  } 
   } 
   //onblur event listeners to input fields 
     $("#userName").on('blur', validateUsername); 
     $("#phoneNumber").on('blur', validatePhone); 
     $("#email").on('blur', validateEmail); 
     $("#createUserid").on('blur', validateUserId); 
     $("#createPassword").on('blur', validatePassword); 
     // Form submit  
     $("#registerBtn").click(function(event) { 
       event.preventDefault(); 
	   console.log("hiii");
		var obj={
			"userName":$('#userName').val(),
			"gender": $('#gender').val(),
			"age": $('#age').val(),
			"phoneNumber":$('#phoneNumber').val(),
			"user_id":$('#createUserid').val(),
			"Email" : $('#email').val(),
			"userPassword" : $('#createPassword').val(),
			};
			console.log(obj);
		if(validateUsername() && validatePhone() && validateEmail() && validateUserId() && validatePassword() && obj.age){
			console.log("validated-->>");
				// Ajax call for Registration
				$.ajax({
				  url: "http://localhost:8080/Hospital_Management_System1/com/register",
				  type: "POST",
				  data:obj,	
				  dataType:"json",	
				  success: function(response) {
					// Handle success response from the server
					console.log(response+"Sussfuly connection");
					if(response.sucessmsg){
						alert(response.sucessmsg);
						window.location.href = "Login.html";
					}else{
						alert(response.errormsg);
					}
					
				  },
				  error: function( error) {
					alert("Registration Failed");
					console.log(error);
					console.log("Error raised");
				  }
				});
		}
	});

});
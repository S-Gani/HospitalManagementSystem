
$(document).ready(function(){
  $('#navbar-con').load('UserPage.html.html #mynavv');
// Appointment Booking
$('#BookAppointment').click(function(event){
   console.log("clickedbook");
    event.preventDefault();

    var appointmentDate = $("#appointmentDate").val().trim();
    var problemDescription = $("#problemDescription").val().trim();
    console.log(appointmentDate);
    console.log(problemDescription);

    if(appointmentDate!="" && problemDescription!= ""){
      var formData = {
        appointmentDate: appointmentDate,
        problemDescription: problemDescription
      };
      console.log("came");
      // Sending the form data to the servlet 
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/Hospital_Management_System1/appAppointment",
        data: formData,
        success: function(response){
          $('#card-footer').html(""+response);
          console.log("Success: " + response);
        },
        error: function(xhr, status, error){
          $('#card-footer').html("Something went Wrong");
          console.log("Error: " + error);
        }
      });
    }

    
 
  });
});

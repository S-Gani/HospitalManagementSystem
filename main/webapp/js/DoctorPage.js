$(document).ready(function(){
    console.log("doc");
    $("#pendingAppointments").DataTable ({
        "severside": true,
        "pocessing": true, 
        "ajax" : {"url": "http://localhost:8080/Hospital_Management_System1/pendingAppointment",
                  "type": "GET",
                  "dataSrc":""
                 },
      
       "columns" : [
           { "data" : "UserName" },
           { "data" : "gender" },
           { "data" : "age" },
           { "data" : "PhoneNumber" },
           { "data" : "appointmentDate" },
           { "data" : "problem" }
       ],
   });
   console.log("end");
})
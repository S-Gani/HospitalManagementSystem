$(document).ready(function(){
  $('#navbar-con').load('UserPage.html #mynavv');
        console.log("----------->>>>>>");
        function populateDataTable() { 
          console.log("ajax statrt");
          $.ajax({ 
            url: 'http://localhost:8080/Hospital_Management_System1/viewAppointmet', 
            type: 'GET', 
            dataType: 'json', 
            success: function(response) {
              var dataTable = $('#appointmentTable').DataTable({ 
                data: response, 
                "columns" : [
                           { "data" : "appointmentDate" },
                           { "data" : "problem" },
                           { "data" : "status",
                             render : function(data,type) {
                                switch(data){
                                  case "1": 
                                   return "Completed";
                                  case "0":
                                    return "Pending";
                                  default:
                                    return "";
                                }
                             }
                           },
                           { "data" : "DocName" },
                           { "data" : "specilazationString" },
                           { "data" : "Phone" }
                       ],
              }); 
            }, 
            error: function() { 
              console.log('Error fetching data from the backend.'); 
            }
          }); 
        }
        populateDataTable(); 
        
                  
}); 
     

   
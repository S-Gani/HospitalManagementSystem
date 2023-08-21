$(document).ready(function(){
    $('#navbar-con').load('AdminNav.html #mynavv');
    console.log("get all user");
    $("body").on("click","#getAllUser", function(event){
        event.preventDefault();
        fetchAdminData();
        console.log("clickednn");
        function fetchAdminData(){
            console.log("fetch");
            const url = "http://localhost:8080/Hospital_Management_System1/getAllUser";
            const requestData = {"action":"display"};
            const successCallback = function(response) {
               console.log(response);
               initializeDataTable(response);
            };
            const errorCallback = function(xhr, status, error) {
                console.error("Error fetching data:", error);
            };
            type = "GET";
            fetchData(url, requestData, successCallback, errorCallback);
        }

    });
})
    function fetchData(url, requestData, successCallback, errorCallback,type) {
        $.ajax({
            url: url,
            method: type,
            data: requestData,
            success: successCallback,
            error: errorCallback
        });
    }
    
    function initializeDataTable(data) {
        const columns = [
            { title: "UserId", data: "UserId" },
            { title: "UserName", data: "UserName" },
            { title: "Gender", data: "gender" },
            { title: "Age", data: "age" },
            { title: "PhoneNumber", data: "PhoneNumber" },
            { title: "AppointmentDate", data: "appointmentDate" },
            { title: "DoctorId", data: "doctorId" },
            { title: "DoctorName", data: "doctorName" },
        ];
        // Datatable
        $("#DataTable").DataTable({
            data: data,
            columns: columns,
            destroy: true, 
        });
    }

    //============Home Data Display===========
    function fetchAdminData() {
        const url = "http://localhost:8080/Hospital_Management_System1/getAllUser";
        const requestData = {"action":"adminhome"};
        const successCallback = function(response) {
           console.log(response);
        };
        const errorCallback = function(xhr, status, error) {
            console.error("Error fetching data:", error);
        };
    }

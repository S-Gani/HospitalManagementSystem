$(document).ready(function () {
    console.log("AdminDoc 000000000000000");
 $('#navbar-con').load('AdminNav.html #mynavv');
$("#doctorName").blur(function(){
    console.log("-->>",$("#doctorName").val());
})
//============= ADD Doctor Form submit =================
    $("body").on("click", "#submitFormBtn" ,function(event){
        event.preventDefault();
        console.log("hi");
        console.log($("#doctorID").val());

        var formData = {
            doctorID:$("#doctorID").val(),
            doctorPassword:$("#doctorPassword").val(),
            doctorName:$("#doctorName").val(),
            email:$("#email").val(),
            phone:$("#phone").val(),
            specialization:$("#specialization").val()
        };
            console.log(formData);
        // AJAX Call
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/Hospital_Management_System1/addoctor",
            data: formData,
            dataType: "json",
            success: function (response) {
                console.log(response);
                if(response.successmsg){
                    alert(response.successmsg);
                    console.log("--------->>success");
                }
                else{
                    alert(response.errormsg);
                }
            },
            error: function (error) {
                console.log("Error:", error);
            }
        });
    });
    //============ Home view data ==================
    $("#adminHome").on("click", function() {
        fetchAdminData();
    });
});
console.log("AdminDoc flfjk");
function fetchData(url, requestData, successCallback, errorCallback,type) {
    $.ajax({
        url: url,
        method: type,
        data: requestData,
        success: successCallback,
        error: errorCallback
    });
}

// Example usage for fetching doctor data
function fetchAdminData() {
    const url = "../deletedoc";
    const requestData = {"action":"display"};
    const successCallback = function(response) {
       console.log(response);
    };
    const errorCallback = function(xhr, status, error) {
        console.error("Error fetching data:", error);
    };
}


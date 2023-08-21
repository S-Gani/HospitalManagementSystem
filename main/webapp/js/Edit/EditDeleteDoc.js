$(document).ready(function(){
    // event.preventDefault();
    console.log("edit update 1");
    // Function to initialize DataTable with JSON data
    console.log("edit update 2");
    // Ajax call
    function fetchDataAndPopulateTable() {
        $.ajax({
            url: "http://localhost:8080/Hospital_Management_System1/deletedoc",
            // url: "../../deletedoc",
            type: "GET",
            dataType: "json",
            data: {"action": "datatable"},
            success: function(data) {
                console.log(data);
                initializeDataTable(data);
            },
            error: function(xhr, status, error) {
                console.error("Error fetching data:", error);
            }
        });
    }
    console.log("edit update 3");

    // initializeDataTable(jsonData);
    console.log("edit update 4");

    $("body").on("click", "#nbut",function(event) {
        event.preventDefault();
        console.log("view doctors");
        $('#listcontent').hide();
        fetchDataAndPopulateTable();
        // initializeDataTable(jsonData);
    });
    // Delete 
    $("#dataTable").on("click", ".deleteBtn", function() {
        const docId = $(this).data("id");
        
        if(window.confirm("Are You sure you want to Delete")){
            console.log(docId);
            $.ajax({
                url: "http://localhost:8080/Hospital_Management_System1/deletedoc",
                method: "GET",
                dataType: 'json',
                data: { "docId": docId,"action": "delete" },
                success: function(response) {
                    console.log("Row deleted:", docId);
                    fetchDataAndPopulateTable()
                },
                error: function(xhr, status, error) {
                    console.error("Error deleting row:", error);
                }
            });
        }
    });
    // modal edit
    $(document).ready(function() {
        $("#dataTable").on("click", ".editBtn", function() {
            const docId = $(this).data("id");
            console.log("Edit clicked for docId:", docId);
            const dataTable = $("#dataTable").DataTable();
            const editRowData = dataTable.row($(this).closest("tr")).data();
            console.log(editRowData);
            toFormEdit(editRowData);
            $("#editModal").modal("show");
        });
    });
        $("button.close,button.btn.btn-secondary").click(function(){
            console.log("clicked");
            $("#editModal").modal("hide");
            $("body").removeClass("modal-open");
            $(".modal-backdrop").remove();
            $("body").css("overflow", "auto");
        });
 });

 function createButtons(data) {
    return `<button class="editBtn" data-bs-toggle="modal" data-bs-target="#editModal" data-id="${data.DocId}">Edit</button>
            <button class="deleteBtn" data-id="${data.DocId}">Delete</button>`;
  }
function initializeDataTable(data) {
    const columns = [
        { title: "DocName", data: "DocName" },
        { title: "docEmail", data: "docEmail" },
        { title: "docPhone", data: "docPhone" },
        { title: "Specialization", data: "Specialization" },
        { title: "DocId", data: "DocId" },
        { title: "Password", data: "Password" },
        {
            title: "Actions",
            data: null,
            render: createButtons,
        }
    ];
    // Datatable
    $("#dataTable").DataTable({
        data: data,
        columns: columns,
        destroy: true, 
    });
}

function  toFormEdit(editRowData){
    $('#doctorID').val(editRowData.DocId);
    $('#doctorPassword').val(editRowData.Password);
    $('#doctorName').val(editRowData.DocName);
    $('#email').val(editRowData.docEmail);
    $('#phone').val(editRowData.docPhone);
    $("#specialization").each(function() {
        if ($(this).val() === (editRowData.Specialization)) {
            $(this).prop("selected", true);
        }
    });
}

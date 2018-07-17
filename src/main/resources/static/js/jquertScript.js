$(document).ready(function() {

    // DO GET
    $.ajax({
        type : "GET",
        url : "/search/all",
        success: function(result){
            $.each(result, function(i, teamleader){
                console.log(teamleader)
                var customerRow = '<tr>' +
                    '<td>' + teamleader.id + '</td>' +
                    '<td>' + teamleader.firstName + '</td>' +
                    '<td>' + teamleader.lastName + '</td>' +
                    '<td>' + teamleader.email + '</td>' +
                    '<td>' + teamleader.teamNam + '</td>' +
                    '<td>' + teamleader.phonenumber + '</td>' +
                    '<td>' + teamleader.troops + '</td>' +
                    '<td>' + teamleader.role + '</td>' +
                    '<td>' + teamleader.active + '</td>' +
                    '<td>' + '<a href="/admin/details/"  +  ' + teamleader.id + ' +   "/"  + ' + teamleader.email + '>Szczegóły</a>' + ' </td>'
                    '</tr>';

                $('#customerTable tbody').append(customerRow);

            });

            $( "#customerTable tbody tr:odd" ).addClass("info");
            $( "#customerTable tbody tr:even" ).addClass("success");
        },
        error : function(e) {
            alert("ERROR: ", e);
            console.log("ERROR: ", e);
        }
    });

    // do Filter on View
    $("#inputFilter").on("keyup", function() {
        var inputValue = $(this).val().toLowerCase();
        $("#customerTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
        });
    });
})
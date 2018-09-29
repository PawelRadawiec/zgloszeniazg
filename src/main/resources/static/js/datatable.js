$(document).ready( function () {
	 var table = $('#teammemberTable').DataTable({
			"sAjaxSource": "/teamleader/rest/all",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			    { "mData": "id"},
		        { "mData": "firstName" },
				{ "mData": "lastName" },
				{ "mData": "homeCity" },
				{ "mData": "street" },
				{ "mData": "phoneNumber"},
				{ "mData": "teamLeaderPhone" },
				{ "mData": "mealCategory" },
				{ "mData": "teamName" },
				{ "mData": "data" },
                {
                    "mData": null,
                    "bSortable": false,
                    "mRender": function(data, type, full) {return '<td><button id="btnEdit" class="btn btn-info">Edit</td> <td><button id="btnDelete" class="btn btn-danger">Delete</td>';}
                }

			]
	 });

	 $('body').on('click', '#btnEdit', function(){
       var row  = $(this).parents('tr')[0];
       window.location.href='/teamleader/edit/' + table.row( row ).data().id ;
     });

     $('body').on('click', '#btnDelete', function(){
        var row  = $(this).parents('tr')[0];
        if (window.confirm('Usunąć użytkownika?')){
                 window.location.href='/teamleader/delete/' + table.row( row ).data().id ;
        };

     });

});
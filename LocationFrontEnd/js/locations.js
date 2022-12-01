$(document).ready(function(){
    loadLocations();
    addLocation();
});

function loadLocations(){
    clearLocationTable();
    var contentRows = $('#contentRows');

    $.ajax({
        type: 'GET',
        url: 'inser tURL',
        success: function(locationArray){
            $.each(locationArray, function(index, location){
                var name = location.locationName;
                var address = location.locationAddressLine1 + '\n' + location.locationAddressLine2;
                var description = location.locationDescription;

                var row = '<tr>';
                    row += '<td>' + name + '</td>';
                    row += '<td>' + address + '</td>';
                    row += '<td>' + description + '</td>';
                    row += '<td><button type="button" class="btn btn-info" onclick"showEditForm()">Edit</button></td>';
                    row += '<td><button type="button" class="btn btun-danger" onclick"deleteLocation('+ locationID + ')">Delete</button></td>';
                    row += '</tr>';
                
                contentRows.append(row);
            })

        },
        error: function(){
            $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again later.'));
        }
    
    })
}

function addLocation(){
    $('#addButton').click(function(event) {

        var haveValidationErrors = checkAndDisplayValidationErrors($('#addForm').find('input'));

        if(haveValidationErrors){
            return false;
        }
        
        $.ajax({
            type:'POST',
            url: 'insert url for adding to addresses',
            data: {
                'Address': [{
                    'addressLine1': $('#addAddressLine1').val(),
                    'addressLine2': $('#addAddressLine2').val(),
                    'city': $('#addCity').val(),
                    'stateAbbreviation': $('#addState').val(),
                    'zip': $('#addZip').val()

                }],
                'Location': [{
                    'locationName': $('#addLocationName').val(),
                    'locationDescription': $('#addLocationDescription').val(),
                    'locationLatitude': $('#addLatitude').float(),
                    'locationLongitude': $('#addLongitude').float()
                }]
            },
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function(){
                $('#errorMessages').empty();
                $('#addLocationName').val('');
                $('#addAddressLine1').val('');
                $('#addAddressLine2').val('');
                $('#addCity').val('');
                $('#addState').val('');
                $('#addZip').val('');
                $('#addLocationDescription').val('');
                $('#addLatitude').float(0);
                $('#addLongitude').float(0);
                loadLocations();
            },
            error: function() {
                $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later'));
            }
            
        })
    });
}

function clearLocationTable(){
    $('#contentRows').empty();
}

function hideEditForm(){
    $('#errorMessages').empty();
    $('#editLocationName').val('');
    $('#editAddressLine1').val('');
    $('#editAddressLine2').val('');
    $('#editCity').val('');
    $('#editState').val('');
    $('#editZip').val('');
    $('#editLocationDescription').val('');
    $('#editLatitude').float(0);
    $('#editLongitude').float(0);
    $('#locationTableDiv').show();
    $('#editFormDiv').hide();
}

function showEditForm(locationID){
    $('#errorMessages').empty();

    $.ajax ({
        type: 'GET',
        url: 'insert url for retrieveing location record' + locationID,
        success: function(data, status) {
            $('#editLocationName').val(data.locationName);
            $('#editLocationDescription').val(data.locationDescription);
            $('#editLatitude').val(data.locationLatitude);
            $('#editLongitude').val(data.locationLongitude);
            $('#editAddressLine1').val(data.addressLine1);
            $('#editAddressLine2').val(data.addressLine2);
            $('#editCity').val(data.city);
            $('#editState').val(data.state);
            $('#editZip').val(data.zip);
        },
        error: function() {
            $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again later'));
        }
    })

    $('#locationTableDiv').hide();
    $('#editFormDiv').show();
}

function updateLocation(locationID) {
    $('#updateButton').click(function(event){
        $.ajax({
            type: 'PUT',
            url: 'insert url to update location' + $('#editLocationID').val(),
            data: {
                'Address': [{
                    'addressLine1': $('#addAddressLine1').val(),
                    'addressLine2': $('#addAddressLine2').val(),
                    'city': $('#addCity').val(),
                    'stateAbbreviation': $('#addState').val(),
                    'zip': $('#addZip').val()

                }],
                'Location': [{
                    'locationName': $('#addLocationName').val(),
                    'locationDescription': $('#addLocationDescription').val(),
                    'locationLatitude': $('#addLatitude').float(),
                    'locationLongitude': $('#addLongitude').float()
                }]
            },
            headers: {
                'dataType': 'json',
                'Content-Type': 'application/json'
                
            },
            'dataType': 'json',
            'success': function(){
                $('#errorMessage').empty();
                hideEditForm();
                loadLocations();
            },
            error: function() {
                $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later'));
            }

        })
    })
}


function deleteLocation(locationID){
    $.ajax({
        type: 'DELETE',
        url: 'insert URL' + locationID,
        success: function(){
            loadLocations();
        }
    });
}

function checkAndDisplayValidationErrors(input) {
    $('#errorMessages').empty();

    var errorMessages = [];

    input.each(function(){
        if (!this.validity.valid){
            var errorField = $('label[For=' + this.id + ']').text();
            errorMessages.push(errorField + '' + this.validationMessage);
        }
    });

    if (errorMessages.length > 0){
        $.each(errorMessages, function(index, message){
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
        });
        return true;
    } else {
        return false;
    }
    
}
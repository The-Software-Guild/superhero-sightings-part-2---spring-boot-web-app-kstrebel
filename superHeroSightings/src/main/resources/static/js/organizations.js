$(document).ready(function(){
    loadOrganizations();
    addOrganization();
});

function loadOrganizations(){
    clearLocationTable();
    var contentRows = $('#contentRows');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8083/api/organizations',
        success: function(organizationArray){
            $.each(organizationArray, function(index, organization){
                var name = organization.organizationName;
                var description = organization.organizationDescription;
                var members = organization.Members;
                var organizationID = organization.organizationID;

                var row = '<tr>';
                    row += '<td>' + name + '</td>';
                    row += '<td>' + description + '</td>';
                    row += '<td>' + members + '</td>';
                    row += '<td><button type="button" class="btn btn-info" onclick"showEditForm()">Edit</button></td>';
                    row += '<td><button type="button" class="btn btn-danger" onclick"deleteLocation('+ organizationID + ')">Delete</button></td>';
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

function addOrganization(){
    $('#addButton').click(function(event) {

        var haveValidationErrors = checkAndDisplayValidationErrors($('#addForm').find('input'));

        if(haveValidationErrors){
            return false;
        }
        
        $.ajax({
            type:'POST',
            url: 'http://localhost:8083/api/addOrganization',
            data: {
                'Address': [{
                    'addressLine1': $('#addAddressLine1').val(),
                    'addressLine2': $('#addAddressLine2').val(),
                    'city': $('#addCity').val(),
                    'stateAbbreviation': $('#addState').val(),
                    'zip': $('#addZip').val()

                }],
                'Organization': [{
                    'organizationName': $('#addOrganizationName').val(),
                    'organizationDescription': $('#addOrganizationDescription').val(),
                }]
            },
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function(){
                $('#errorMessages').empty();
                $('#addOrganizationnName').val('');
                $('#addAddressLine1').val('');
                $('#addAddressLine2').val('');
                $('#addCity').val('');
                $('#addState').val('');
                $('#addZip').val('');
                $('#addOrganizationDescription').val('');
                $('#addMembers').val('');
                loadOrganizations();
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
    $('#editOrganizationName').val('');
    $('#editAddressLine1').val('');
    $('#editAddressLine2').val('');
    $('#editCity').val('');
    $('#editState').val('');
    $('#editZip').val('');
    $('#editOrganizationDescription').val('');
    $('#editMembers').val('');
    $('#organizationTableDiv').show();
    $('#editFormDiv').hide();
}

function showEditForm(organizationID){
    $('#errorMessages').empty();

    $.ajax ({
        type: 'GET',
        url: 'http://localhost:8083/api/organizations/' + organizationID,
        success: function(data, status) {
            $('#editOrganizationName').val(data.organizationName);
            $('#editLocationDescription').val(data.organizationDescription);
            $('#editAddressLine1').val(data.addressLine1);
            $('#editAddressLine2').val(data.addressLine2);
            $('#editCity').val(data.city);
            $('#editState').val(data.state);
            $('#editZip').val(data.zip);
            $('#editMembers').val(data.members)
        },
        error: function() {
            $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again later'));
        }
    })

    $('#organizationTableDiv').hide();
    $('#editFormDiv').show();
}

function updateOrganization(organizationID) {
    $('#updateButton').click(function(event){
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8083/api/organizations/'+ $('#editOrganizationID').val(),
            data: {
                'Address': [{
                    'addressLine1': $('#editAddressLine1').val(),
                    'addressLine2': $('#editAddressLine2').val(),
                    'city': $('#editCity').val(),
                    'stateAbbreviation': $('#editState').val(),
                    'zip': $('#editZip').val()

                }],
                'Organization': [{
                    'organizationName': $('#editLocationName').val(),
                    'organizationDescription': $('#editOrganizationDescription').val(),
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
                loadOrganizations();
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


function deleteLocation(organizationID){
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8083/api/organizations/' + organizationID,
        success: function(){
            loadOrganizations();
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
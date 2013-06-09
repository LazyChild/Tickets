$ ->
    $('.time-picker').datetimepicker
        pickSeconds: false
        pickDate: false

    $('.date-picker').datetimepicker
        pickTime: false

    $('#add-passenger').click ->
        n = $('#passenger-table tr').length - 1
        tr = $('#passenger-table tr').eq(1).clone()
        tr.find('input, select').each ->
            name = $(this).attr 'name'
            name = name.replace '0', n
            $(this).attr 'name', name
            if $(this).attr('type') != 'hidden'
                $(this).val null
        $('#passenger-table tbody').append tr

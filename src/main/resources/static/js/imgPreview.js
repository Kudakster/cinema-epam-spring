$(document).ready(function(){
    $('#image').change(function(){
        $("#frames").html('');
        for (var i = 0; i < $(this)[0].files.length; i++) {
            $("#frames").append('<img src="'+window.URL.createObjectURL(this.files[i])+'" width="300px" height="400px" alt=""/>');
        }
    });
});
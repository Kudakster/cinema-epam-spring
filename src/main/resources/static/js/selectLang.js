$(document).ready(function () {
    $("#select-lang").on("change", function () {
        document.getElementById("locale-form").submit();
    });
});
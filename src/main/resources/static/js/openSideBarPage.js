$(document).ready(function () {
    $("#content").load(getCookie("page"));

    openPage("movie", "page=admin/movies", "http://localhost:8080/admin/");
    openPage("auditorium", "page=admin/auditorium", "http://localhost:8080/admin/");
    openPage("a-movie", "page=admin/add-movie", "http://localhost:8080/admin/");
    openPage("u-schedule", "page=admin/update-schedule", "http://localhost:8080/admin/");
});

function openPage(idClick, cookie, URL) {
    $("#" + idClick).on("click", function () {
        document.cookie = cookie;
        location.href = URL;
    });
}

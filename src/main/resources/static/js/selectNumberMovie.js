$(document).ready(function () {
    document.getElementById("select-page").value = getCookie("numberMovies");

    $("#select-page").on("change", function () {
        let value = document.getElementById("select-page").value;
        document.cookie = "numberMovies=" + value;
        document.cookie = "missedPage=0";
        document.location.reload();
    });

    let missedPage = Number(getCookie("missedPage"));
    let numberPages = Number(getCookie("numberMovies"));
    let numberMovies = Number(getCookie("numberAllMovies"));
    let previous = $("#previous");
    let next = $("#next");

    if (missedPage === 0) {
        document.getElementById("previous").disabled = true;
        document.getElementById("next").disabled = false;
        previous.attr("class", "btn-second");
    } else {
        document.getElementById("previous").disabled = false;
        previous.attr("class", "btn-danger");
    }

    if (numberMovies <= missedPage + numberPages) {
        document.getElementById("next").disabled = true;
        next.attr("class", "btn-second");
    } else {
        document.getElementById("next").disabled = false;
        next.attr("class", "btn-danger");
    }

    previous.on("click", function () {
        if (--missedPage < 0) {
            missedPage = 0;
        }
        document.cookie = "missedPage=" + missedPage;
        document.location.reload();
    });

    next.on("click", function () {
        document.cookie = "missedPage=" + ++missedPage;
        document.location.reload();
    });
});
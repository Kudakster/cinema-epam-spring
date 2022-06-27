let movies = document.querySelectorAll(".rectangle");
let moviesArray = Array.from(movies);
let index = 0;

$(document).ready(function () {
    movies = document.querySelectorAll(".rectangle");
    moviesArray = Array.from(movies);
    slide()

    if (index + 3 < moviesArray.length) {
        document.getElementById("next").hidden = false;
    }

    $(".btn-left").click(function () {
        index = index - 3;
        if (index === 0) {
            document.getElementById("previous").hidden = true;
        }
        document.getElementById("next").hidden = false;
        slide();
    })

    $(".btn-right").click(function () {
        index = index + 3;
        if (index + 3 > moviesArray.length) {
            document.getElementById("next").hidden = true;
        }
        document.getElementById("previous").hidden = false;
        slide();
    })
})

function slide() {
    for (let i = 0; i < moviesArray.length; i++) {
        if (moviesArray[i]) {
            moviesArray[i].hidden = true;
        }
    }
    for (let i = index; i < index + 3; i++) {
        if (moviesArray[i]) {
            moviesArray[i].hidden = false;
        }
    }
}


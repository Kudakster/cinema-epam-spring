let suggestions

function validateMovieName() {
    let movieName = document.getElementById("movie-name").value

        $.ajax({
                url: "validateMovieName",
                type: "get",
                async: false,
                data: {"movie-name": movieName},
                dataType: "html",
                success: function (data) {
                    suggestions = data.split(";");
                },
            }
        )
}
$(document).ready(function () {
    const tBodyElem = document.querySelector("tbody");

    $("button[name=\"add-row-button\"]").on("click", function () {
        let element = document.getElementById("auditorium-seats")
        element.innerHTML += '<tr>' +
            '<th><input type="number" class="input-seat" name="seat-number" min="0"></th>' +
            '<th><button type="button" id="button-delete" class="btn-danger">Delete</button> </th>' +
            '</tr>';
    });

    tBodyElem.addEventListener("click", function (e) {
        if (!e.target.classList.contains("btn-danger")) {
            return;
        }

        e.target.closest("tr").remove();
    })
});
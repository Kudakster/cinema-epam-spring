$(document).ready(function () {
    $("button[name=add-screening-form]").on("click", function(e) {
        let parent = document.getElementById("screening-form");
        let main = e.getElementById("day-block");
        let children = parent.cloneNode(true);

        let button = document.createElement("button");
        button.setAttribute("type", "submit");
        button.setAttribute("class", "btn btn-danger");
        button.setAttribute("id", "delete-screening");
        button.setAttribute("onclick", "deleteNode(this)");
        button.innerText = "Delete";

        for (let i = 0; i < children.childNodes.length; i++) {
            if (children.childNodes[i].nodeName === 'FORM') {
                children.childNodes[i].replaceWith(button);
            }
        }

        let array = children.querySelectorAll("input");
        array.forEach(value => value.setAttribute("value", ""));

        main.appendChild(children);
    });
});

function deleteNode(e) {
    e.parentElement.remove();
}


function setInvalid(data, idInput) {
    let idLabel = idInput + "-valid";
    $("#" + idInput).attr("class", "form-control invalid");
    $("#" + idLabel).attr("style", "display: inline-block;");
    document.getElementById(idLabel).innerText = data;
}

function setValid(idInput) {
    let idLabel = idInput + "-valid";
    $("#" + idInput).attr("class", "form-control valid");
    $("#" + idLabel).attr("style", "display: none;");
}

function setDefault(idInput) {
    let idLabel = idInput + "-valid";
    $("#" + idInput).attr("class", "form-control");
    $("#" + idLabel).attr("style", "display: none;");
}

function createElement(forElement) {
    let idLabel = forElement + "-valid";
    let idForm = forElement + "-form";

    if (document.getElementById(idLabel) === null) {
        let label = document.createElement("label");
        label.setAttribute("for", forElement);
        label.setAttribute("id", idLabel);
        label.setAttribute("class", "invalid")
        let element = document.getElementById(idForm);
        element.appendChild(label);
    }
}

function checkInput(value, formName, idForm, minLength, maxLength, regex, regexMessage) {
    const MUST_BE_INPUT = formName + " must be input!";
    let MUST_BE_SHORTER = formName + " must be shorter that " + maxLength + " symbols";
    let MUST_BE_LONGER = formName + " must be longer that " + minLength + " symbols";
    const MUST_CONTAINS = formName + " must contains " + minLength + " symbols";

    if (minLength === maxLength) {
        MUST_BE_SHORTER = MUST_CONTAINS;
        MUST_BE_LONGER = MUST_CONTAINS;
    }

    if (value === "") {
        setInvalid(MUST_BE_INPUT, idForm);
    } else if (value.length < minLength) {
        setInvalid(MUST_BE_LONGER, idForm);
    } else if (value.length > maxLength) {
        setInvalid(MUST_BE_SHORTER, idForm);
    } else if (regex !== undefined && !regex.test(value)) {
        setInvalid(regexMessage, idForm);
    } else {
        setValid(idForm);
    }
}
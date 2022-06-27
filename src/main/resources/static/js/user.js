const MIN_LOGIN_LENGTH = 4;
const MAX_LOGIN_LENGTH = 30;

const MIN_NAME_LENGTH = 2;
const MAX_NAME_LENGTH = 30;

const LOGIN = "Login";
const FIRSTNAME = "Firstname";
const SURNAME = "Surname";

$(document).ready(function () {
    //Login
    requiredLogin("login", LOGIN, MIN_LOGIN_LENGTH, MAX_LOGIN_LENGTH, undefined, undefined, restrictNonEnglishAndNumbers); //Must be first, import!!!

    //Firstname
    requiredFirstname("firstname", FIRSTNAME, MIN_NAME_LENGTH, MAX_NAME_LENGTH, undefined, undefined, restrictNonEnglish);

    //Surname
    requiredSurname("surname", SURNAME, MIN_NAME_LENGTH, MAX_NAME_LENGTH, undefined, undefined, restrictNonEnglish);

    requiredAllFormValid();
});

function required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    $("#" + idElement).on('input', function () {
        let value = document.getElementById(idElement).value
        let value_default = document.getElementById(idElement).defaultValue;

        function_parameter(idElement);
        createElement(idElement); //Create and check if exist (not good)
        checkInput(value, formName, idElement, minLength, maxLength, regex, regexMessage);

        if (value_default === document.getElementById(idElement).value) {
            setDefault(idElement)
        }
    })
}

function requiredLogin(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    $("#" + idElement).on('input', function () {
        let value = document.getElementById(idElement).value
        let LOGIN_OLD = document.getElementById(idElement).defaultValue;

        function_parameter(idElement);
        createElement(idElement); //Create and check if exist (not good)
        checkInput(value, formName, idElement, minLength, maxLength, regex, regexMessage);

        if (LOGIN_OLD === document.getElementById(idElement).value) {
            setDefault(idElement)
        }
    })
}

function requiredFirstname(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredSurname(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredAllFormValid() {
    $("#basic-form").on('change', function () {
        let formArray = [{id: "login"}, {id: "firstname"}, {id: "surname"}];
        let n = 0;

        for (let obj of formArray) {
            let classAttr = document.getElementById(obj.id).getAttribute("class");
            if (classAttr === "form-control invalid") {
                document.getElementById("submit-button").disabled = true;
                return;
            } else if (classAttr === "form-control valid") {
                document.getElementById("submit-button").disabled = false;
            } else {
                n++;
            }
        }

        if (n === 3) {
            document.getElementById("submit-button").disabled = true;
        }
    })
}

function restrictNonEnglishAndNumbers(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^A-Za-z0-9]/g, '');
}

function restrictNonEnglish(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^A-Za-z]/g, '');
}

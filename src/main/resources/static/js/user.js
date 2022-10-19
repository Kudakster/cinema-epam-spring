const MIN_NAME_LENGTH = 2;
const MAX_NAME_LENGTH = 30;

const MIN_EMAIL_LENGTH = 5;
const MAX_EMAIL_LENGTH = 50;

const MIN_PHONE_NUMBER_LENGTH = 13;
const MAX_PHONE_NUMBER_LENGTH = 13;

const FIRSTNAME = "Firstname";
const SURNAME = "Surname";
const EMAIL = "Email";
const PHONE_NUMBER = "Phone number";

const EMAIL_REGEX_NOT_MATCH = "Email not correct (123acv@mail.net)";
const PHONE_NUMBER_REGEX_NOT_MATCH = "Phone number not correct (+380123658456)";

const EMAIL_REGEX_EXP = new RegExp("^\\S+@\\S+\\.\\S+$")
const PHONE_NUMBER_REGEX_EXP = new RegExp("\\+380[0-9]{9}")

$(document).ready(function () {
    //Firstname
    requiredFirstname("firstname", FIRSTNAME, MIN_NAME_LENGTH, MAX_NAME_LENGTH, undefined, undefined, restrictNonEnglish);

    //Surname
    requiredSurname("surname", SURNAME, MIN_NAME_LENGTH, MAX_NAME_LENGTH, undefined, undefined, restrictNonEnglish);

    //Email
    requiredEmail("email", EMAIL, MIN_EMAIL_LENGTH, MAX_EMAIL_LENGTH, EMAIL_REGEX_EXP, EMAIL_REGEX_NOT_MATCH, restrictNonEmail);

    //Phone Number
    requiredPhoneNumber("phone-number", PHONE_NUMBER, MIN_PHONE_NUMBER_LENGTH, MAX_PHONE_NUMBER_LENGTH, PHONE_NUMBER_REGEX_EXP, PHONE_NUMBER_REGEX_NOT_MATCH, restrictNonPhoneNumber);

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

function requiredFirstname(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredSurname(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredEmail(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredPhoneNumber(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredAllFormValid() {
    $("#basic-form").on('change', function () {
        let formArray = [{id: "firstname"}, {id: "surname"}, {id:"email"}, {id:"phone-number"}];
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

        if (n === formArray.length) {
            document.getElementById("submit-button").disabled = true;
        }
    })
}

function restrictNonEnglish(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^A-Za-z]/g, '');
}

function restrictNonEmail(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^A-Z0-9]+^@[^A-Z0-9.]+[^a-z]{2,}/g, '');
}

function restrictNonPhoneNumber(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^+0-9]/g, '');
}

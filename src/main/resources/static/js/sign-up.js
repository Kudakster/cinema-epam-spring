const MIN_LOGIN_LENGTH = 4;
const MAX_LOGIN_LENGTH = 30;

const MIN_PASSWORD_LENGTH = 6;
const MAX_PASSWORD_LENGTH = 30;

const MIN_NAME_LENGTH = 2;
const MAX_NAME_LENGTH = 30;

const MIN_EMAIL_LENGTH = 5;
const MAX_EMAIL_LENGTH = 50;

const MIN_PHONE_NUMBER_LENGTH = 13;
const MAX_PHONE_NUMBER_LENGTH = 13;

const LOGIN = "Login";
const PASSWORD = "Password";
const FIRSTNAME = "Firstname";
const SURNAME = "Surname";
const EMAIL = "Email";
const PHONE_NUMBER = "Phone number";

const PASSWORD_REGEX_NOT_MATCH = "Minimum six characters, at least one uppercase letter,\n " +
    "one lowercase letter, one number and one special character (@$!#^%*?&)";
const EMAIL_REGEX_NOT_MATCH = "Email not correct (123acv@mail.net)";
const PHONE_NUMBER_REGEX_NOT_MATCH = "Phone number not correct (+380123658456)";

const PASSWORD_REGEX_EXP = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!#^%*?&])[A-Za-z\\d@$!#^%*?&]{6,30}$");
const EMAIL_REGEX_EXP = new RegExp("^\\S+@\\S+\\.\\S+$")
const PHONE_NUMBER_REGEX_EXP = new RegExp("\\+380[0-9]{9}")
const CONFIRM_PASSWORDS_DONT_MATCH = "Passwords Don't Match";

$(document).ready(function () {
    //Login
    requiredLogin("login", LOGIN, MIN_LOGIN_LENGTH, MAX_LOGIN_LENGTH, undefined, undefined, restrictNonEnglishAndNumbers); //Must be first, import!!!

    //Password
    requiredPassword("pwd", PASSWORD, MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH, PASSWORD_REGEX_EXP, PASSWORD_REGEX_NOT_MATCH, restrictNonEnglishAndNumbersAndCharacters);

    //Confirm Password
    requiredConfirmPassword("confirm-password");

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

        function_parameter(idElement);
        createElement(idElement); //Create and check if exist (not good)
        checkInput(value, formName, idElement, minLength, maxLength, regex, regexMessage);
    })
}

function requiredLogin(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredPassword(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredConfirmPassword() {
    restrictNonEnglishAndNumbersAndCharacters("confirm-password")

    createElement("confirm-password"); //Create and check if exist (not good)

    let password = document.getElementById("pwd")
        , confirm_password = document.getElementById("confirm-password");

    if (password.value !== confirm_password.value) {
        setInvalid(CONFIRM_PASSWORDS_DONT_MATCH, "confirm-password")
    } else if (password.value !== "") {
        setValid("confirm-password")
    }

    password.onchange = requiredConfirmPassword;
    confirm_password.oninput = requiredConfirmPassword;
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
        let formArray = [{id:"login"}, {id:"pwd"}, {id:"confirm-password"}, {id:"firstname"}, {id:"surname"}, {id:"email"}, {id:"phone-number"}];

        for (let obj of formArray) {
            let classAttr = document.getElementById(obj.id).getAttribute("class");
            if (classAttr !== "form-control valid") {
                document.getElementById("submit-button").disabled = true;
                return;
            } else if (classAttr === "form-control valid") {
                document.getElementById("submit-button").disabled = false;
            }
        }
    })
}

function restrictNonEnglishAndNumbers(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^A-Za-z0-9]/g, '');
}

function restrictNonEnglishAndNumbersAndCharacters(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^A-Za-z0-9@$!#^%*?&]/g, '');
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








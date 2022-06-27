$(document).ready(function () {
    const MIN_MOVIE_NAME_LENGTH = 1,
        MAX_MOVIE_NAME_LENGTH = 30,
        MIN_ACTORS_LENGTH = 2,
        MAX_ACTORS_LENGTH = 30,
        MIN_DIRECTION_LENGTH = 2,
        MAX_DIRECTION_LENGTH = 30,
        MIN_GENRE_LENGTH = 4,
        MAX_GENRE_LENGTH = 30,
        MIN_COUNTRY_LENGTH = 3,
        MAX_COUNTRY_LENGTH = 30,
        MIN_TRAILER_URL_LENGTH = 4,
        MAX_TRAILER_URL_LENGTH = 60,
        MIN_RELEASE_DATE_LENGTH = 10,
        MAX_RELEASE_DATE_LENGTH = 10,
        MIN_DURATION_MIN_LENGTH = 2,
        MAX_DURATION_MIN_LENGTH = 3,
        MIN_DESCRIPTION_LENGTH = 4,
        MAX_DESCRIPTION_LENGTH = 300,

        MOVIE_NAME = "Movie Name",
        ACTORS = "Actors",
        DIRECTION = "Direction",
        GENRE = "Genre",
        COUNTRY = "Country",
        TRAILER_URL = "Trailer URL",
        RELEASE_DATE = "Release Date",
        DURATION_MIN = "Duration Min",
        DESCRIPTION = "Description";

    //Movie Name
    requiredMovieName("movie-name", MOVIE_NAME, MIN_MOVIE_NAME_LENGTH, MAX_MOVIE_NAME_LENGTH, undefined, undefined, restrictNonEnglishAndNumbersAndCharacters); //Must be first, import!!!

    //Password
    requiredActors("actors", ACTORS, MIN_ACTORS_LENGTH, MAX_ACTORS_LENGTH, undefined, undefined, restrictNonEnglishAndCharacters);

    //Direction
    requiredDirection("direction", DIRECTION, MIN_DIRECTION_LENGTH, MAX_DIRECTION_LENGTH, undefined, undefined, restrictNonEnglish);

    //Genre
    requiredGenre("genre", GENRE, MIN_GENRE_LENGTH, MAX_GENRE_LENGTH, undefined, undefined, restrictNonEnglish);

    //Country
    requiredCountry("country", COUNTRY, MIN_COUNTRY_LENGTH, MAX_COUNTRY_LENGTH, undefined, undefined, restrictNonEnglish);

    //Trailer URL
    requiredTrailerURL("trailer-url", TRAILER_URL, MIN_TRAILER_URL_LENGTH, MAX_TRAILER_URL_LENGTH, undefined, undefined, restrictNonEnglishAndNumbersAndCharacters);

    //Release Date
    requiredReleaseDate("release-date", RELEASE_DATE, MIN_RELEASE_DATE_LENGTH, MAX_RELEASE_DATE_LENGTH, undefined, undefined, restrictNonDate);

    //Duration Min
    requiredDurationMin("duration-min", DURATION_MIN, MIN_DURATION_MIN_LENGTH, MAX_DURATION_MIN_LENGTH, undefined, undefined, restrictNonNumbers);

    //Duration Min
    requiredDescription("description", DESCRIPTION, MIN_DESCRIPTION_LENGTH, MAX_DESCRIPTION_LENGTH, undefined, undefined, restrictNonEnglish);

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

function requiredMovieName(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredActors(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredDirection(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredGenre(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredCountry(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredTrailerURL(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredReleaseDate(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredDurationMin(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredDescription(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter) {
    required(idElement, formName, minLength, maxLength, regex, regexMessage, function_parameter);
}

function requiredAllFormValid() {
    $("#basic-form").on('change', function () {
        let formArray = [{id: "movie-name"}, {id: "release-date"}];

        for (let obj of formArray) {
            let classAttr = document.getElementById(obj.id).getAttribute("class");
            if (classAttr === "form-control invalid") {
                document.getElementById("submit-button").disabled = true;
                return;
            } else {
                document.getElementById("submit-button").disabled = false;
            }
        }
    })
}

function restrictNonNumbers(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^0-9]/g, '');
}

function restrictNonEnglishAndNumbersAndCharacters(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^A-Za-z0-9,./;:'"@$!#^%*?& ]/g, '');
}

function restrictNonEnglishAndCharacters(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^A-Za-z,./;:'"@$!#^%*?& ]/g, '');
}

function restrictNonEnglish(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^A-Za-z ]/g, '');
}

function restrictNonDate(id) {
    let element = document.getElementById(id)
    element.value = element.value.replace(/[^0-9-]/g, '');
}

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
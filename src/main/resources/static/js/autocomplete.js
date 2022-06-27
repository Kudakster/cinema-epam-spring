let searchWrapper = document.querySelectorAll(".search-input");
let inputArray = Array.from(searchWrapper);
let inputBox;
let suggBox;

for (let i = 0; i < inputArray; i++) {
        inputBox = inputArray.querySelector("input");
        suggBox = searchWrapper.querySelector(".autocom-box");

        inputBox.onkeyup = (e) => {
            let userData = e.target.value; //user enetered data
            let emptyArray = [];
            if (userData) {
                emptyArray = suggestions.filter((data) => {
                    //filtering array value and user characters to lowercase and return only those words which are start with user enetered chars
                    return data.toLocaleLowerCase().startsWith(userData.toLocaleLowerCase());
                });
                emptyArray = emptyArray.map((data) => {
                    // passing return data inside li tag
                    return data = `<li>${data}</li>`;
                });
                searchWrapper.classList.add("active"); //show autocomplete box
                showSuggestions(emptyArray);
                let allList = suggBox.querySelectorAll("li");
                for (let i = 0; i < allList.length; i++) {
                    //adding onclick attribute in all li tag
                    allList[i].setAttribute("onclick", "select(this)");
                }
            } else {
                searchWrapper.classList.remove("active"); //hide autocomplete box
            }
        }
}

function select(element) {
    inputBox.value = element.textContent;
    searchWrapper.classList.remove("active");
}

function showSuggestions(list) {
    let listData;
    if (!list.length) {
        let userValue = inputBox.value;
        listData = `<li>${userValue}</li>`;
    } else {
        listData = list.join('');
    }
    suggBox.innerHTML = listData;
}

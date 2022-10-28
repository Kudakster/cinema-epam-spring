let map = {}
let parent;
let bookedClass = "booked-seat";
let freeClass = "free-seat";

window.onload = addFunctionToFreeTicketElement;

function addFunctionToFreeTicketElement() {
    let elements = document.getElementsByClassName("free-seat");
    for (let el of elements) {
        el.onclick = bookTicket;
    }
}

function bookTicket() {
    parent = document.getElementById("bucket");
    let select = document.getElementById("seatId");
    let row = this.getAttribute("row");
    let number = this.getAttribute("number");
    let seatId = this.getAttribute("seatId");

    let elementClass = this.getAttribute("class");
    if (elementClass === bookedClass) {
        setClass(this, freeClass);
        removeTicketFromForm(select,seatId);
        removeTicketElementFromBucket(row, number);
    } else {
        setClass(this, bookedClass);
        addTicketElementToBucket(row, number, seatId)
        addTicketToForm(select, seatId);

        map[row + number + seatId] = this;
    }
}

function addTicketToForm(select, seatId) {
    let ticket = createTicket(seatId);
    select.appendChild(ticket);
    map[seatId] = ticket;
}

function removeTicketFromForm(select, seatId) {
    let el = map[seatId];
    select.removeChild(el);
}

function addTicketElementToBucket(row, number, seatId) {
    let ticket = createTicketElement(row, number, seatId);
    parent.appendChild(ticket);
    map[row + number] = ticket;
}

function removeTicketElementFromBucket(row, number) {
    let ticketElement = map[row + number];
    parent.removeChild(ticketElement);
}

function setClass(el,HTMLClass) {
    el.setAttribute("class", HTMLClass);
}

function removeTicket(el) {
    let parent = document.getElementById("bucket");
    let select = document.getElementById("seatId");
    parent.removeChild(el);

    let row = el.getAttribute("row");
    let number = el.getAttribute("number");
    let seatId = el.getAttribute("seatId");
    let ticket = map[row + number + seatId];

    setClass(ticket, freeClass);
    removeTicketFromForm(select,seatId);
}

function createTicketElement(row, number, seatId) {
    return htmlToElement('<li class="ticket" row="'+ row +'" number="'+ number +'" seatId="'+ seatId +'"> <div class="ticket-info">Row: ' + row + '</div> <div class="ticket-info">Number: ' + number + '</div> <div class="ticket-info">Price: Free</div> <button class="btn-close" onclick="removeTicket(this.parentElement)"></button> </li>')
}

function createTicket(seatId) {
    let element = htmlToElement('<option selected></option>');
    element.setAttribute("value", seatId);
    return element;
}

function htmlToElement(html) {
    let template = document.createElement('template');
    html = html.trim();
    template.innerHTML = html;
    return template.content.firstChild;
}
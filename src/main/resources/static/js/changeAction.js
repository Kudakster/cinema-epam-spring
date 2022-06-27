function changeAction() {
    let element = document.getElementById("screening-action");
    element.setAttribute("action", "${pageContext.request.contextPath}/cinema/delete-schedule")
}
document.addEventListener("DOMContentLoaded", function() {
    const userMenu = document.querySelector(".user-name");
    const dropdown = document.querySelector(".dropdown-menu");

    userMenu.addEventListener("click", function(event) {
        dropdown.computedStyleMap.display
    });
});
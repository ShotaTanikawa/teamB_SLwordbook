document.addEventListener("DOMContentLoaded", function () {
    console.log("JavaScript Loaded");

    function openDeleteModal(button) {
        console.log("openDeleteModal Called");

        let wordbookId = button.getAttribute("data-id");
        let wordbookName = button.getAttribute("data-name");

        let deleteTarget = document.getElementById("deleteTargetName");
        let confirmDelete = document.getElementById("confirmDelete");
        let deleteModal = document.getElementById("deleteModal");

        if (!deleteTarget || !confirmDelete || !deleteModal) {
            console.error("Modal elements not found!", deleteTarget, confirmDelete, deleteModal);
            return;
        }

        deleteTarget.innerText = "単語帳「" + wordbookName + "」を削除しますか？";
        confirmDelete.setAttribute("data-id", wordbookId);
        deleteModal.style.display = "block";
    }

    function closeDeleteModal() {
        console.log("closeDeleteModal Called");
        let deleteModal = document.getElementById("deleteModal");
        if (deleteModal) {
            deleteModal.style.display = "none";
        } else {
            console.error("Modal not found!");
        }
    }

    let confirmDelete = document.getElementById("confirmDelete");
    if (confirmDelete) {
        confirmDelete.addEventListener("click", function () {
            console.log("Confirm Delete Clicked");
            let wordbookId = this.getAttribute("data-id");
            window.location.href = "/user/wordbooks/delete/" + wordbookId;
        });
    } else {
        console.error("confirmDelete button not found!");
    }

    // グローバルスコープに関数を追加（HTMLのonclick対応）
    window.openDeleteModal = openDeleteModal;
    window.closeDeleteModal = closeDeleteModal;
});

document.addEventListener("DOMContentLoaded", function() {
    const modal = document.getElementById("foodModal");
    const modalImage = document.getElementById("modalImage");
    const modalTitle = document.getElementById("modalTitle");
    const modalPrice = document.getElementById("modalPrice");
    const closeModal = document.querySelector(".close");
    const sizeSelect = document.getElementById("size");

    document.querySelectorAll(".menu-item img").forEach(img => {
        img.addEventListener("click", function() {
            const menuItem = this.closest(".menu-item");
            modalImage.src = this.src;
            modalTitle.textContent = menuItem.querySelector("h3").textContent;
            
            // Cập nhật giá ban đầu theo kích cỡ nhỏ
            modalPrice.textContent = parseInt(modalPrice.getAttribute("data-price-small")).toLocaleString("vi-VN") + "đ";
            
            // Hiển thị modal
            modal.style.display = "flex";
        });
    });

    // Xử lý thay đổi giá khi chọn kích cỡ
    sizeSelect.addEventListener("change", function() {
        let selectedSize = sizeSelect.value;
        let newPrice = modalPrice.getAttribute(`data-price-${selectedSize}`);
        modalPrice.textContent = parseInt(newPrice).toLocaleString("vi-VN") + "đ";
    });

    // Đóng modal khi nhấn vào dấu X
    closeModal.addEventListener("click", function() {
        modal.style.display = "none";
    });

    // Đóng modal khi nhấn ra ngoài
    window.addEventListener("click", function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});

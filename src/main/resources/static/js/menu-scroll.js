document.addEventListener("DOMContentLoaded", function () {
    // Tìm tất cả các danh sách món ăn
    const menus = document.querySelectorAll(".menu-wrapper");

    menus.forEach((menuWrapper) => {
        const prevBtn = menuWrapper.querySelector(".prev");
        const nextBtn = menuWrapper.querySelector(".next");
        const menuItems = menuWrapper.querySelector(".menu-items");

        // Khi bấm nút trái
        prevBtn.addEventListener("click", function () {
            menuItems.scrollBy({ left: -500, behavior: "smooth" });
        });

        // Khi bấm nút phải
        nextBtn.addEventListener("click", function () {
            menuItems.scrollBy({ left: 500, behavior: "smooth" });
        });
    });
});

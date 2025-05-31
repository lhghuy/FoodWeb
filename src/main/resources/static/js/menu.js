document.addEventListener("DOMContentLoaded", function () {
  // Xử lý dropdown menu
  const menuToggle = document.getElementById("menu-toggle");
  const dropdownMenu = document.getElementById("dropdown-menu");

  menuToggle?.addEventListener("click", function (e) {
    e.preventDefault();
    dropdownMenu.classList.toggle("show");
  });

  document.addEventListener("click", function (e) {
    if (!menuToggle.contains(e.target) && !dropdownMenu.contains(e.target)) {
      dropdownMenu.classList.remove("show");
    }
  });
  document.querySelectorAll(".dropdown-menu li a").forEach((link) => {
    link.addEventListener("click", function () {
      dropdownMenu.classList.remove("show");
    });
  });
  // Xử lý cuộn đến danh mục khi nhấn vào mục menu
  // Xử lý cuộn đến danh mục khi nhấn vào mục menu
  document.querySelectorAll(".dropdown-menu li a").forEach((link) => {
    link.addEventListener("click", function (event) {
      event.preventDefault();
      let targetText = this.textContent.toUpperCase().trim();
      let targetSections = document.querySelectorAll("h2");

      let targetSection = Array.from(targetSections).find(
        (h2) => h2.textContent.toUpperCase().trim() === targetText
      );

      if (targetSection) {
        targetSection.scrollIntoView({ behavior: "smooth", block: "start" });
        event.stopImmediatePropagation();
      } else {
        console.error("Không tìm thấy danh mục:", targetText);
      }
    });
  });

  // Xử lý nút "Đặt món ngay" -> Cuộn đến danh mục đầu tiên
  let btnDatMon = document.querySelector(".btn-primary");
  let danhMucMonAn = document.querySelector("h2");

  if (btnDatMon && danhMucMonAn) {
    btnDatMon.addEventListener("click", function (event) {
      event.preventDefault();
      danhMucMonAn.scrollIntoView({ behavior: "smooth", block: "start" });
    });
  }

  // Xử lý sự kiện click cho nút "XEM THÊM"
  let viewMoreButtons = document.querySelectorAll(".view-more");

  viewMoreButtons.forEach((button) => {
    button.addEventListener("click", function (event) {
      event.preventDefault();
      event.stopPropagation(); // Chặn sự kiện lan ra ngoài

      let targetText = this.getAttribute("data-target").toUpperCase().trim();
      let targetSections = document.querySelectorAll("h2");

      let targetSection = Array.from(targetSections).find(
        (h2) => h2.textContent.toUpperCase().trim() === targetText
      );

      if (targetSection) {
        targetSection.scrollIntoView({ behavior: "smooth", block: "start" });
        event.stopImmediatePropagation();
      } else {
        console.error("Không tìm thấy danh mục:", targetText);
      }
    });
  });

  function filterItems(categoryID) {
    // Đổi trạng thái nút được chọn
    document
      .querySelectorAll(".sidebar li")
      .forEach((btn) => btn.classList.remove("active"));
    const activeBtn = document.querySelector(
      `.sidebar li[data-cat="${categoryID}"]`
    );
    if (activeBtn) activeBtn.classList.add("active");

    // Hiển thị các món ăn theo danh mục
    document.querySelectorAll(".menu-item").forEach((item) => {
      const cat = item.getAttribute("data-cat");
      if (categoryID === "all" || cat === categoryID) {
        item.style.display = "block";
      } else {
        item.style.display = "none";
      }
    });
  }

  // Enhanced smooth scroll navigation for menu categories
  document.addEventListener("DOMContentLoaded", function () {
    // Cache DOM elements
    const categoryButtons = document.querySelectorAll(".sidebar li");
    const menuSections = document.querySelectorAll(".menu-section");
    const scrollToTopBtn = document.getElementById("scrollToTop");

    // Category mapping - thay đổi để khớp với ID sections thực tế
    const categoryMapping = {
      all: "all",
      1: "Món chính",
      2: "Lẩu",
      3: "Đồ uống",
      4: "Tráng miệng",
      5: "Món thêm",
    };

    // Reverse mapping để tìm category từ section ID
    const sectionToCategory = {};
    Object.keys(categoryMapping).forEach((key) => {
      sectionToCategory[categoryMapping[key]] = key;
    });

    // Smooth scroll configuration
    const scrollOptions = {
      behavior: "smooth",
      block: "start",
      inline: "nearest",
    };

    // Handle category button clicks
    categoryButtons.forEach((button) => {
      button.addEventListener("click", function (e) {
        e.preventDefault();

        // Remove active class from all buttons
        categoryButtons.forEach((btn) => btn.classList.remove("active"));

        // Add active class to clicked button
        this.classList.add("active");

        // Get category ID from data attribute
        const categoryId = this.getAttribute("data-cat");

        // Find target section
        const targetSectionId = categoryMapping[categoryId] || "all";
        const targetSection = document.getElementById(targetSectionId);

        if (targetSection) {
          // Add loading state
          this.classList.add("loading");

          // Scroll to section
          targetSection.scrollIntoView(scrollOptions);

          // Remove loading state after scroll
          setTimeout(() => {
            this.classList.remove("loading");
          }, 600);

          // Trigger animation for menu items in the section
          animateMenuItems(targetSection);
        }
      });
    });

    // Animate menu items when section comes into view
    function animateMenuItems(section) {
      const menuItems = section.querySelectorAll(".menu-item");
      menuItems.forEach((item, index) => {
        item.style.animation = "none";
        item.style.opacity = "0";
        item.style.transform = "translateY(20px)";

        setTimeout(() => {
          item.style.animation = `fadeInUp 0.6s ease forwards`;
          item.style.animationDelay = `${index * 0.1}s`;
        }, 100);
      });
    }

    // Intersection Observer for auto-updating active states
    const observerOptions = {
      root: null,
      rootMargin: "-20% 0px -60% 0px",
      threshold: 0.1,
    };

    const sectionObserver = new IntersectionObserver((entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          const sectionId = entry.target.id;
          const correspondingCategoryId = sectionToCategory[sectionId] || "all";

          // Update active state for corresponding button
          categoryButtons.forEach((btn) => {
            btn.classList.remove("active");
            if (btn.getAttribute("data-cat") === correspondingCategoryId) {
              btn.classList.add("active");
            }
          });

          // Animate items when they come into view
          if (entry.intersectionRatio > 0.1) {
            animateMenuItems(entry.target);
          }
        }
      });
    }, observerOptions);

    // Observe all menu sections
    menuSections.forEach((section) => {
      sectionObserver.observe(section);
    });

    // Scroll to top functionality
    function toggleScrollToTopButton() {
      if (scrollToTopBtn) {
        if (window.pageYOffset > 300) {
          scrollToTopBtn.style.display = "block";
          scrollToTopBtn.style.animation = "fadeInUp 0.3s ease forwards";
        } else {
          scrollToTopBtn.style.display = "none";
        }
      }
    }

    // Handle scroll events
    let ticking = false;
    function handleScroll() {
      if (!ticking) {
        requestAnimationFrame(() => {
          toggleScrollToTopButton();
          ticking = false;
        });
        ticking = true;
      }
    }

    window.addEventListener("scroll", handleScroll);

    // Scroll to top button click handler
    if (scrollToTopBtn) {
      scrollToTopBtn.addEventListener("click", function () {
        window.scrollTo({
          top: 0,
          behavior: "smooth",
        });
      });
    }

    // Enhanced scroll to category function
    window.scrollToCategory = function (categoryId) {
      const targetSectionId = categoryMapping[categoryId] || "all";
      const targetSection = document.getElementById(targetSectionId);
      const correspondingButton = document.querySelector(
        `[data-cat="${categoryId}"]`
      );

      if (targetSection) {
        // Update active state
        categoryButtons.forEach((btn) => btn.classList.remove("active"));
        if (correspondingButton) {
          correspondingButton.classList.add("active");
        }

        // Scroll to section
        targetSection.scrollIntoView(scrollOptions);

        // Animate items
        setTimeout(() => {
          animateMenuItems(targetSection);
        }, 300);
      }
    };

    // Keyboard navigation support
    document.addEventListener("keydown", function (e) {
      if (e.key === "Home") {
        e.preventDefault();
        window.scrollTo({ top: 0, behavior: "smooth" });
      } else if (e.key === "End") {
        e.preventDefault();
        window.scrollTo({
          top: document.body.scrollHeight,
          behavior: "smooth",
        });
      }
    });

    // Add focus styles for accessibility
    categoryButtons.forEach((button) => {
      button.setAttribute("tabindex", "0");
      button.addEventListener("keypress", function (e) {
        if (e.key === "Enter" || e.key === " ") {
          e.preventDefault();
          this.click();
        }
      });
    });

    // Preload images for better performance
    function preloadImages() {
      const images = document.querySelectorAll(".menu-item img");
      images.forEach((img) => {
        const imageLoader = new Image();
        imageLoader.src = img.src;
      });
    }

    // Initialize
    setTimeout(preloadImages, 1000);

    // Handle resize events
    let resizeTimer;
    window.addEventListener("resize", function () {
      clearTimeout(resizeTimer);
      resizeTimer = setTimeout(() => {
        // Recalculate positions if needed
        toggleScrollToTopButton();
      }, 250);
    });

    // Add smooth scroll behavior to any anchor links
    document.querySelectorAll('a[href^="#"]').forEach((anchor) => {
      anchor.addEventListener("click", function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute("href"));
        if (target) {
          target.scrollIntoView(scrollOptions);
        }
      });
    });

    console.log("Menu navigation initialized successfully");
  });

  document.querySelectorAll(".scroll-btn").forEach((button) => {
    button.addEventListener("click", function () {
      const targetId = this.getAttribute("data-target");
      const targetSection = document.getElementById(targetId);
      if (targetSection) {
        targetSection.scrollIntoView({ behavior: "smooth" });
      }
    });
  });

  // Xử lý slider trượt menu
  let sliders = document.querySelectorAll(".menu-wrapper");

  sliders.forEach((slider) => {
    let nextBtn = slider.querySelector(".next");
    let prevBtn = slider.querySelector(".prev");
    let menuItems = slider.querySelector(".menu-items");

    let itemWidth = menuItems.querySelector(".menu-item").offsetWidth;
    let scrollAmount = itemWidth * 3;

    nextBtn.addEventListener("click", function () {
      menuItems.scrollBy({ left: scrollAmount, behavior: "smooth" });
    });

    prevBtn.addEventListener("click", function () {
      menuItems.scrollBy({ left: -scrollAmount, behavior: "smooth" });
    });
  });
});

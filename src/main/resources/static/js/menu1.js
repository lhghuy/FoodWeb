document.addEventListener("DOMContentLoaded", function () {
    let cartCount = document.getElementById("cart-count");
    let buttons = document.querySelectorAll(".menu-item button");
    

    let count = 0;

    buttons.forEach(button => {
        button.addEventListener("click", function (event) {
            count++;
            cartCount.textContent = count;

            // Hiệu ứng sản phẩm bay vào giỏ hàng
            let productImage = this.parentElement.querySelector("img");
            let flyingImage = productImage.cloneNode(true);
            let cart = document.getElementById("cart");

            flyingImage.style.position = "fixed";
            flyingImage.style.width = "70px";
            flyingImage.style.height = "70px";
            flyingImage.style.zIndex = "1000";
            flyingImage.style.transition = "all 1s ease-in-out";

            let rect = productImage.getBoundingClientRect();
            flyingImage.style.left = rect.left + "px";
            flyingImage.style.top = rect.top + "px";
            
            document.body.appendChild(flyingImage);

            let cartRect = cart.getBoundingClientRect();
            setTimeout(() => {
                flyingImage.style.left = cartRect.left + "px";
                flyingImage.style.top = cartRect.top + "px";
                flyingImage.style.opacity = "0";
            }, 100);

            setTimeout(() => {
                document.body.removeChild(flyingImage);
            }, 1000);
        });
    });
   // Mảng chứa sản phẩm trong giỏ hàng
let cart = JSON.parse(localStorage.getItem("cart")) || [];

// Hàm thêm sản phẩm vào giỏ hàng
function addToCart(productName, productPrice, productImage) {
    let existingProduct = cart.find(item => item.name === productName);
    if (existingProduct) {
        existingProduct.quantity++;
    } else {
        cart.push({
            name: productName,
            price: productPrice,
            image: productImage,
            quantity: 1
        });
    }
    updateCart();
}

// Hàm cập nhật giỏ hàng
function updateCart() {
    localStorage.setItem("cart", JSON.stringify(cart));
    renderCart();
}

// Hàm hiển thị giỏ hàng trên trang giohang.html
function renderCart() {
    let cartContainer = document.getElementById("cart-items");
    let totalPrice = 0;
    cartContainer.innerHTML = "";
    cart.forEach((item, index) => {
        totalPrice += item.price * item.quantity;
        cartContainer.innerHTML += `
            <div class='cart-item'>
                <img src="${item.image}" alt="${item.name}" class="cart-image">
                <p>${item.name}</p>
                <p>${item.price} đ</p>
                <input type="number" value="${item.quantity}" min="1" onchange="updateQuantity(${index}, this.value)">
                <button onclick="removeFromCart(${index})">Xóa</button>
            </div>
        `;
    });
    document.getElementById("total-price").innerText = totalPrice + " đ";
}

// Cập nhật số lượng sản phẩm
function updateQuantity(index, newQuantity) {
    cart[index].quantity = parseInt(newQuantity);
    updateCart();
}

// Xóa sản phẩm khỏi giỏ hàng
function removeFromCart(index) {
    cart.splice(index, 1);
    updateCart();
}

// Load giỏ hàng khi vào trang giohang.html
document.addEventListener("DOMContentLoaded", () => {
    if (document.getElementById("cart-items")) {
        renderCart();
    }
});
document.getElementById("open-cart").addEventListener("click", function() {
    window.location.href = "giohang.html";
});

function openMap(index) {
    console.log("Đã click vào cửa hàng:", index);
    window.location.href = `../hethongcuahang/bando.html?store=${index}`;
}





    });

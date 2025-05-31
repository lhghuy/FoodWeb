document.addEventListener("DOMContentLoaded", function () {
    // Kiểm tra div có tồn tại không
    if (!document.getElementById('map')) {
        console.error("Không tìm thấy phần tử #map, kiểm tra lại bando.html!");
        return;
    }

    // Khởi tạo bản đồ
    var map = L.map('map').setView([10.7769, 106.7009], 13);

    // Thêm layer bản đồ từ OpenStreetMap
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);

    // Danh sách cửa hàng
    var stores = [
        { name: "Cửa hàng 1", lat: 10.7769, lng: 106.7009, address: "Quận 1, TP.HCM" },
        { name: "Cửa hàng 2", lat: 10.7625, lng: 106.6820, address: "Quận 3, TP.HCM" },
        { name: "Cửa hàng 3", lat: 10.7621, lng: 106.6597, address: "Quận 5, TP.HCM" }
    ];

    // Hiển thị marker cho từng cửa hàng
    stores.forEach((store, index) => {
        var marker = L.marker([store.lat, store.lng]).addTo(map)
            .bindPopup(`<b>${store.name}</b><br>${store.address}`);
        store.marker = marker;
    });

    // Lấy chỉ mục cửa hàng từ URL
    const params = new URLSearchParams(window.location.search);
    const storeIndex = params.get("store");

    if (storeIndex !== null && stores[storeIndex]) {
        goToStore(parseInt(storeIndex));
    }

    // Hàm di chuyển đến cửa hàng
    function goToStore(index) {
        var store = stores[index];
        map.setView([store.lat, store.lng], 15);
        store.marker.openPopup();
    }
});

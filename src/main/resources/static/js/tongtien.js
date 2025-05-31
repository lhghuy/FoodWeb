/*<script>
  let total = 0;

  function formatCurrency(amount) {
    return amount.toLocaleString("vi-VN") + "đ";
  }

  document.querySelectorAll(".menu-item button").forEach((btn) => {
    btn.addEventListener("click", function () {
      const priceText = this.parentElement.querySelector(".price").innerText;
      const price = parseInt(priceText.replace(/[^\d]/g, ""));
      total += price;
      document.getElementById("total").innerText = formatCurrency(total);
    });
  });
</script>*/

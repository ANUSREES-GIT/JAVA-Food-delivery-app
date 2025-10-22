document.addEventListener('DOMContentLoaded', () => {
  const cartCount = document.getElementById('cart-count');
  const cart = JSON.parse(localStorage.getItem('cart')) || {};

  function updateCartCount() {
    const count = Object.values(cart).reduce((sum, item) => sum + item.quantity, 0);
    cartCount.textContent = count;
  }

  document.querySelectorAll('.menu-item').forEach(item => {
    const name = item.querySelector('h3').textContent;
    const price = parseInt(item.getAttribute('data-price'), 10);
    const countEl = item.querySelector('.item-count');
    const increaseBtn = item.querySelector('.increase');
    const decreaseBtn = item.querySelector('.decrease');

    if (cart[name]) {
      countEl.textContent = cart[name].quantity;
    }

    increaseBtn.addEventListener('click', () => {
      if (!cart[name]) {
        cart[name] = { price, quantity: 1 };
      } else {
        cart[name].quantity += 1;
      }
      countEl.textContent = cart[name].quantity;
      localStorage.setItem('cart', JSON.stringify(cart));
      updateCartCount();
    });

    decreaseBtn.addEventListener('click', () => {
      if (cart[name] && cart[name].quantity > 0) {
        cart[name].quantity -= 1;
        if (cart[name].quantity === 0) {
          delete cart[name];
        }
        countEl.textContent = cart[name] ? cart[name].quantity : 0;
        localStorage.setItem('cart', JSON.stringify(cart));
        updateCartCount();
      }
    });
  });

  updateCartCount();
});

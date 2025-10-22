document.addEventListener('DOMContentLoaded', () => {
  const cart = JSON.parse(localStorage.getItem('cart')) || {};
  const cartItems = document.getElementById('cart-items');
  const totalEl = document.getElementById('total');
  const proceedBtn = document.getElementById('proceed-btn');

  cartItems.innerHTML = '';
  let total = 0;
  let itemCount = 0;

  for (const [name, details] of Object.entries(cart)) {
    const li = document.createElement('li');
    li.innerHTML = `
      <span><strong>${name}</strong> (₹${details.price})</span>
      <span>Qty: ${details.quantity}</span>
      <span>Total: ₹${details.price * details.quantity}</span>
    `;
    cartItems.appendChild(li);

    total += details.price * details.quantity;
    itemCount += details.quantity;
  }

  totalEl.textContent = `Total: ₹${total}`;
  proceedBtn.disabled = itemCount === 0;
});

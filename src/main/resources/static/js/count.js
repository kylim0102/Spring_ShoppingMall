const decreaseButton = document.getElementById('decrease');
const increaseButton = document.getElementById('increase');
const numberInput = document.getElementById('number');
const quantitySpan = document.getElementById('quantity');
const totalPriceSpan = document.getElementById('totalPrice');

decreaseButton.addEventListener('click', function() {
    const currentValue = parseInt(numberInput.value);
    if(currentValue > 1) {
        numberInput.value = parseInt(numberInput.value) - 1;
        updateQuantity();
    } else {
        alert("최소 주문수량은 1개 입니다.");
    }
});

increaseButton.addEventListener('click', function() {
    numberInput.value = parseInt(numberInput.value) + 1;
    updateQuantity();
});

numberInput.addEventListener('input', function() {
    const currentValue = parseInt(numberInput.value);
    if (currentValue < 1) {
        alert("최소 주문수량은 1개 입니다.");
        numberInput.value = 1;
    }
    updateQuantity();
});

function updateQuantity() {
    const quantity = parseInt(numberInput.value);
    const pricePerItem = parseInt(totalPriceSpan.getAttribute('data-price'));
    const totalPrice = pricePerItem * quantity;
    totalPriceSpan.textContent = totalPrice.toLocaleString() + '원';
    quantitySpan.textContent = quantity.toLocaleString();
    document.getElementById('hiddenQuantity').value = quantity; // hidden input 업데이트
}
const filas = document.querySelectorAll('.fila');
const precioTotal = document.querySelector('.precio-total');
const subTotales = document.querySelectorAll('.precio-sub-total');
const homeBtn = document.getElementById('home');

actualizarPrecioTotal();

filas.forEach((fila) => {
  fila.addEventListener('click', (e) => {
    // Actualiza la cantidad
    if (e.target.classList.contains('btn-mas')) {
      const cantInput = e.currentTarget.querySelector('.cantidad-inp');
      let cant = parseInt(cantInput.value, 10);
      if (cant < 999) {
        cant++;
      }
      cantInput.value = cant;

      // Actualiza los precios
      actualizarPrecio(e, cant);
      actualizarPrecioTotal();
    } else if (e.target.classList.contains('btn-menos')) {
      // Actualiza la cantidad
      const cantInput = e.currentTarget.querySelector('.cantidad-inp');
      let cant = parseInt(cantInput.value, 10);
      if (cant > 0) {
        cant--;
      }
      cantInput.value = cant;

      // Actualiza los precios
      actualizarPrecio(e, cant);
      actualizarPrecioTotal();
    }
  });
});

function actualizarPrecio(e, cant) {
  const precio = e.currentTarget.querySelector('.precio');
  const precioSubTotal = e.currentTarget.querySelector('.precio-sub-total');
  const precioUnid = parseFloat(precio.textContent.substring(3), 10);
  let precioSubTotalActual = 'S/ ' + (precioUnid * cant).toFixed(2);
  precioSubTotal.textContent = precioSubTotalActual;
}

function actualizarPrecioTotal() {
  let total = 0;
  subTotales.forEach((subTotal) => {
    total += parseFloat(subTotal.textContent.substring(3));
  });
  precioTotal.textContent = 'S/ ' + total.toFixed(2);
}

homeBtn.addEventListener('click', () => {
  document.location = 'Productos?accion=obtenerproductos&target=tienda';
});

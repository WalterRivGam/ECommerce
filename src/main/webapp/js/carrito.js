const filas = document.querySelectorAll('.fila');
const precioTotal = document.querySelector('.precio-total');
const subTotales = document.querySelectorAll('.precio-sub-total');
const homeBtn = document.getElementById('home');

actualizarPrecioTotal();

filas.forEach((fila) => {
  fila.addEventListener('click', (e) => {
    if (e.target.classList.contains('btn-mas')) {
      const codigo = e.currentTarget.querySelector('.codigo').textContent;
      const cantInput = e.currentTarget.querySelector('.cantidad-inp');
      let cant = parseInt(cantInput.value, 10);
      if (cant < 999) {
        $.ajax({
          url: 'Carrito',
          data: {
            accion: 'aumentar',
            codigo: codigo,
            success: function () {
              cantInput.value = cant + 1;
              actualizarPrecio(e, cant + 1);
              actualizarPrecioTotal();
            }
          },
          type: 'POST'
        });
      }
    } else if (e.target.classList.contains('btn-menos')) {
      const codigo = e.currentTarget.querySelector('.codigo').textContent;
      const cantInput = e.currentTarget.querySelector('.cantidad-inp');
      let cant = parseInt(cantInput.value, 10);
      if (cant > 0) {
        $.ajax({
          url: 'Carrito',
          data: {
            target: 'carrito',
            accion: 'disminuir',
            codigo: codigo,
            success: function () {
              cantInput.value = cant - 1;
              actualizarPrecio(e, cant - 1);
              actualizarPrecioTotal();
            }
          },
          type: 'POST'
        });
      }
    }
  });
});
//
function actualizarPrecio(e, cant) {
  const precio = e.currentTarget.querySelector('.precio');
  const precioSubTotal = e.currentTarget.querySelector('.precio-sub-total');
  const precioUnid = parseFloat(precio.textContent.substring(3), 10);
  let precioSubTotalActual = 'S/ ' + (precioUnid * cant).toFixed(2);
  precioSubTotal.textContent = precioSubTotalActual;
}
//
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

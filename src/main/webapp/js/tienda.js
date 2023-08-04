const articulos = document.querySelectorAll('.articulo');
const btnsAniadir = document.querySelectorAll('.btn-aniadir');
const btnCarrito = document.querySelector('.btn-carrito');

let estadoCarrito = document.querySelector('.estado-carrito').textContent;

btnsAniadir.forEach(btnAniadir => {
  btnAniadir.addEventListener('click', (e) => {
    const codigo = parseInt(e.target.id);

    // Mostrar mensaje:
    const descripcion = e.target.parentElement.querySelector('.descripcion').textContent;
    const precio = e.target.parentElement.querySelector('.precio').textContent;
    alert('Estás agregando: ' + descripcion + ' a ' + precio);
    
    estadoCarrito = "novacio";

    $.ajax({
      url: 'Carrito',
      data: {
        accion: 'aumentar',
        codigo: codigo
      },
      type: 'POST'
    });
  });
});

btnCarrito.addEventListener('click', () => {
  if (estadoCarrito === "vacio") {
    alert("Carrito está vacío!")
  } else {
    window.location = "carrito.jsp";
  }
});
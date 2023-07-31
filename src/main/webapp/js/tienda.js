const articulos = document.querySelectorAll('.articulo');
const btnsAniadir = document.querySelectorAll('.btn-accion');
const btnCarrito = document.querySelector('.btn-carrito');

const codigos = new Set();

btnsAniadir.forEach(btnAniadir => {
  btnAniadir.addEventListener('click', (e) => {
    const codigo = parseInt(e.target.id);
    if (codigos.has(codigo)) {
      alert('Ya estaba en el carrito');
    } else {
      codigos.add(codigo);
      const descripcion = e.target.parentElement.querySelector('.descripcion').textContent;
      alert('Se agregó al carrito: ' + descripcion);
    }
  });
});

btnCarrito.addEventListener('click', () => {
  const cadena = formarCadena(codigos);
  if(cadena.length > 0) {
    window.location = 'VerCarrito?codigos=' + cadena;
  } else {
    alert('Carrito vacío!');
  }
});

function formarCadena(codigos) {
  let cadena = ""
  codigos.forEach(codigo => {
    cadena += (codigo + ',');
  });
  if(cadena.length > 0) {
    cadena = cadena.substring(0, cadena.length - 1);
  }
  return cadena;
}
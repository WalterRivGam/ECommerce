const articulos = document.querySelectorAll('.articulo');
const btnsAniadir = document.querySelectorAll('.btn-accion');
const btnCarrito = document.querySelector('.btn-carrito');

let codigos = [];
let cantidades = [];

btnsAniadir.forEach(btnAniadir => {
  btnAniadir.addEventListener('click', (e) => {
    const codigo = parseInt(e.target.id);
    if (codigos.includes(codigo)) {
      const index = codigos.indexOf(codigo);
      cantidades[index]++;
    } else {
      codigos.push(codigo);
      cantidades.push(1);
    }
    const descripcion = e.target.parentElement.querySelector('.descripcion').textContent;
    const precio = e.target.parentElement.querySelector('.precio').textContent;
    alert('Estás agregando: ' + descripcion + ' a ' + precio);

  });
});

btnCarrito.addEventListener('click', () => {
  const cadenaDeCodigos = formarCadenaDeCodigos(codigos);
  const cadenaDeCantidades = formarCadenaDeCantidades(cantidades);
  console.log("Cadena de cantidades: " + cadenaDeCantidades);
  if (codigos.length > 0) {
    window.location = 'Carrito?codigos=' + cadenaDeCodigos + '&cantidades=' + cadenaDeCantidades;
  } else {
    alert('Carrito vacío!');
  }
});

function formarCadenaDeCodigos(codigos) {
  let cadena = ""
  codigos.forEach(codigo => {
    cadena += (codigo + ',');
  });
  if (cadena.length > 0) {
    cadena = cadena.substring(0, cadena.length - 1);
  }
  return cadena;
}

function formarCadenaDeCantidades(cantidades) {
  let cadena = ""
  cantidades.forEach(cantidad => {
    cadena += (cantidad + ',');
  });
  if (cadena.length > 0) {
    cadena = cadena.substring(0, cadena.length - 1);
  }
  return cadena;
}
const btnIns = document.querySelector('.btn-ins');
const btnDel = document.querySelector('.btn-del');
const btnUpd = document.querySelector('.btn-upd');

btnIns.addEventListener('click', () => {
  if (confirm('¿Desea añadir un registro?')) {
    window.location = 'productoIns.jsp';
  }
});

btnDel.addEventListener('click', () => {
  const codigos = getCodigosDel();
  if (codigos === null) {
    alert('Debe seleccionar al menos un producto!');
  } else {
    window.location = 'Productos?accion=eliminar&codigos=' + codigos;
  }
});

btnUpd.addEventListener('click', () => {
  const codigo = getCodigoUpd();
  if (codigo === null) {
    alert('Debe seleccionar un producto!');
  } else if (confirm('¿Desea actualizar el registro?')) {
    window.location = 'VerProducto?codigo=' + codigo;
  }
});

function getCodigoUpd() {
  const prodsUpd = document.getElementsByName('productoUpd');
  let codigo = null;
  prodsUpd.forEach((prodUpd) => {
    if (prodUpd.checked) {
      codigo = prodUpd.value;
    }
  });
  return codigo;
}

function getCodigosDel() {
  const prodsDel = document.getElementsByName('productoDel');
  let codigos = "";
  prodsDel.forEach((prodDel) => {
    if(prodDel.checked) {
      codigos += (prodDel.value + ",");
    }
  });
  if(codigos.length > 0) {
    return codigos.substring(0, codigos.length - 1);
  } else {
    return null;
  }
}
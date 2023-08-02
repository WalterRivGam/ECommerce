<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertar Producto</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

    <link rel="stylesheet" href="css/productoForm.css">

  </head>

  <body>
    <h1>Nuevo Producto</h1>
    <div class="form-cont">
      <form action="Productos" method="post" enctype="multipart/form-data">
        <input type="hidden" name="accion" value="insertar" autocomplete="off">
        <div class="mb-3">
          <label for="codigo" class="form-label">Código</label>
          <input type="text" name="codigo" id="codigo" class="form-control" autocomplete="off">
        </div>
        <div class="mb-3">
          <label for="descripcion" class="form-label">Descripción</label>
          <input type="text" name="descripcion" id="descripcion" class="form-control" autocomplete="off">
        </div>
        <div class="mb-3">
          <label for="marca" class="form-label">Marca</label>
          <input type="text" name="marca" id="marca" class="form-control" autocomplete="off">
        </div>
        <div class="mb-3">
          <label for="precio" class="form-label">Precio</label>
          <input type="text" name="precio" id="precio" class="form-control" autocomplete="off">
        </div>
        <div class="mb-3">
          <label for="imagen" class="form-label">Imagen</label>
          <input type="file" name="imagen" id="imagen" class="form-control">
        </div>
        <div class="btn-cont">
          <button type="submit" class="btn btn-primary">Enviar Datos</button>
        </div>
      </form>
    </div>
    <div class="cancelar">
      <a href="Productos?accion=obtenerproductos&target=productos">Cancelar</a>
    </div>

    <!-- Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"></script>

  </body>

</html>

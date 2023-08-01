<%@page import="com.proyectos.ecommerce.dto.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mantenimiento Productos</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

    <link rel="stylesheet" href="css/productos.css">
  </head>

  <body>
    <h1>Lista de Productos</h1>
    <div>
      <table class="table table-hover">
        <thead>
          <tr>
            <th>Código</th>
            <th>Descripcion</th>
            <th>Marca</th>
            <th>Precio</th>
            <th>Imagen</th>
            <th class="btn-ins"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="#37b24d"
                                     viewBox="0 0 256 256">
              <path
                d="M128,24A104,104,0,1,0,232,128,104.11,104.11,0,0,0,128,24Zm0,192a88,88,0,1,1,88-88A88.1,88.1,0,0,1,128,216Zm48-88a8,8,0,0,1-8,8H136v32a8,8,0,0,1-16,0V136H88a8,8,0,0,1,0-16h32V88a8,8,0,0,1,16,0v32h32A8,8,0,0,1,176,128Z">
              </path>
              </svg></th>
            <th class="btn-del"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="#f03e3e"
                                     viewBox="0 0 256 256">
              <path
                d="M176,128a8,8,0,0,1-8,8H88a8,8,0,0,1,0-16h80A8,8,0,0,1,176,128Zm56,0A104,104,0,1,1,128,24,104.11,104.11,0,0,1,232,128Zm-16,0a88,88,0,1,0-88,88A88.1,88.1,0,0,0,216,128Z">
              </path>
              </svg></th>
            <th class="btn-upd"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="#1c7ed6"
                                     viewBox="0 0 256 256">
              <path
                d="M197.67,186.37a8,8,0,0,1,0,11.29C196.58,198.73,170.82,224,128,224c-37.39,0-64.53-22.4-80-39.85V208a8,8,0,0,1-16,0V160a8,8,0,0,1,8-8H88a8,8,0,0,1,0,16H55.44C67.76,183.35,93,208,128,208c36,0,58.14-21.46,58.36-21.68A8,8,0,0,1,197.67,186.37ZM216,40a8,8,0,0,0-8,8V71.85C192.53,54.4,165.39,32,128,32,85.18,32,59.42,57.27,58.34,58.34a8,8,0,0,0,11.3,11.34C69.86,69.46,92,48,128,48c35,0,60.24,24.65,72.56,40H168a8,8,0,0,0,0,16h48a8,8,0,0,0,8-8V48A8,8,0,0,0,216,40Z">
              </path>
              </svg></th>
          </tr>
        </thead>
        <tbody>
          <% List<Producto> productos = (List<Producto>) request.getAttribute("productos");
            for (Producto producto : productos) {%>
          <tr>
            <td><%= producto.getCodigo() + ""%></td>
            <td><%= producto.getDescripcion()%></td>
            <td><%= producto.getMarca()%></td>
            <td>S/ <%= String.format("%.2f", producto.getPrecio())%></td>
            <td><img src="VerImagen?codigo=<%= producto.getCodigo()%>" alt="" class="img-prod"></td>
            <td></td>
            <td class="checkbox">
              <input type="checkbox" name="productoDel" value="<%= producto.getCodigo()%>">
            </td>
            <td class="radio">
              <input type="radio" name="productoUpd" value="<%= producto.getCodigo()%>">
            </td>
          </tr>
          <% }%>
        </tbody>
      </table>
      <div class="home">
        <a href="index.html">Menú</a>
      </div>

    </div>

    <!-- Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"></script>

    <script src="js/productos.js"></script>
  </body>

</html>

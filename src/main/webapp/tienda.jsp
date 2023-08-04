<%@page import="java.util.Map"%>
<%@page import="com.proyectos.ecommerce.dto.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tienda Online</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

    <link rel="stylesheet" href="css/tienda.css">
  </head>

  <body>
    <nav>
      <h1>Tienda Online</h1>
      <button type="button" class="btn-carrito">
        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="#edf2ff" viewBox="0 0 256 256">
        <path
          d="M222.14,58.87A8,8,0,0,0,216,56H54.68L49.79,29.14A16,16,0,0,0,34.05,16H16a8,8,0,0,0,0,16h18L59.56,172.29a24,24,0,0,0,5.33,11.27,28,28,0,1,0,44.4,8.44h45.42A27.75,27.75,0,0,0,152,204a28,28,0,1,0,28-28H83.17a8,8,0,0,1-7.87-6.57L72.13,152h116a24,24,0,0,0,23.61-19.71l12.16-66.86A8,8,0,0,0,222.14,58.87ZM96,204a12,12,0,1,1-12-12A12,12,0,0,1,96,204Zm96,0a12,12,0,1,1-12-12A12,12,0,0,1,192,204Zm4-74.57A8,8,0,0,1,188.1,136H69.22L57.59,72H206.41Z">
        </path>
        </svg>
      </button>
    </nav>
    
    <% Object obj = request.getSession().getAttribute("prodsCarrito"); %>
    <div hidden class="estado-carrito"><%= obj == null ? "vacio" : "novacio" %></div>

    <main>
      <div class="container">
        <div class="row">
          <%
            List<Producto> productos = (List<Producto>) request.getAttribute("productos");
            for (Producto producto : productos) {
          %>
          <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 articulo">
            <img src="VerImagen?codigo=<%= producto.getCodigo()%>" alt="">
            <h3 class="marca"><%= producto.getMarca()%></h3>
            <p class="descripcion"><%= producto.getDescripcion()%></p>
            <p class="codigo">Código <%= producto.getCodigo()%></p>
            <p class="precio">S/ <%= String.format("%.2f", producto.getPrecio())%></p>
            <button class="btn-aniadir" id="<%= producto.getCodigo()%>">AÑADIR AL CARRO</button>
          </div>
          <% }%>
        </div>
      </div>
    </main>


    <!-- Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"></script>

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    
    <script src="js/tienda.js"></script>
  </body>

</html>

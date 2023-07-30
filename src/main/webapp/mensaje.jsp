<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mensaje</title>
    <style>
      body {
        text-align: center
      }
    </style>
  </head>
  <body>
    <h1>Mensaje</h1>
    <p><%= (String)request.getAttribute("msg") %></p>
    <a href="VerProductos">Home</a>
  </body>
</html>

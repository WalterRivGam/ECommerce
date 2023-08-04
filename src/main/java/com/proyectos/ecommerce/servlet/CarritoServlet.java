package com.proyectos.ecommerce.servlet;

import com.proyectos.ecommerce.dao.ProductoDAO;
import com.proyectos.ecommerce.dao.impl.ProductoDAOImpl;
import com.proyectos.ecommerce.dto.Producto;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CarritoServlet", urlPatterns = {"/Carrito"})
public class CarritoServlet extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    ProductoDAO productoDAO = new ProductoDAOImpl();
    Map<Producto, Integer> prodsCarrito = getProdsExistentes(request);

    int codigo = Integer.parseInt(request.getParameter("codigo"));
    String accion = request.getParameter("accion");

    if (prodsCarrito == null && accion.equals("aumentar")) {
      prodsCarrito = new HashMap<>();
      Producto producto = productoDAO.obtenerProducto(codigo);
      prodsCarrito.put(producto, 1);
    } else if (prodsCarrito != null && accion.equals("aumentar")) {
      boolean productoNuevo = true;
      for (Map.Entry<Producto, Integer> prodEntry : prodsCarrito.entrySet()) {
        if (prodEntry.getKey().getCodigo() == codigo) {
          int nuevaCant = prodEntry.getValue() + 1;
          prodEntry.setValue(nuevaCant);
          productoNuevo = false;
        }
      }
      if (productoNuevo) {
        Producto producto = productoDAO.obtenerProducto(codigo);
        prodsCarrito.put(producto, 1);
      }
    } else if (accion.equals("disminuir")) {
      Producto prodBorrar = null;
      for (Map.Entry<Producto, Integer> prodEntry : prodsCarrito.entrySet()) {
        if (prodEntry.getKey().getCodigo() == codigo) {
          int nuevaCant = prodEntry.getValue() - 1;
          prodEntry.setValue(nuevaCant);
          if (nuevaCant == 0) {
            prodBorrar = prodEntry.getKey();
          }
        }
      }
      if (prodBorrar != null) {
        prodsCarrito.remove(prodBorrar);
      }
    }

    request.getSession().setAttribute("prodsCarrito", prodsCarrito);
  }

  Map<Producto, Integer> getProdsExistentes(HttpServletRequest request) {
    Map<Producto, Integer> prodsExistentes = null;
    Object prodsCarr = request.getSession().getAttribute("prodsCarrito");
    if (prodsCarr != null) {
      prodsExistentes = (Map<Producto, Integer>) prodsCarr;
    }
    return prodsExistentes;
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}

package com.proyectos.ecommerce.servlet;

import com.proyectos.ecommerce.dao.ProductoDAO;
import com.proyectos.ecommerce.dao.impl.ProductoDAOImpl;
import com.proyectos.ecommerce.dto.Producto;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/Productos"})
public class ProductoServlet extends HttpServlet {

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
    String accion = null;
    String msg = null;
    String target = "/mensaje.jsp";;

    Producto producto = new Producto();
    ProductoDAO productoDAO = new ProductoDAOImpl();
    boolean esMultipart = ServletFileUpload.isMultipartContent(request);

    // para INS y UPD
    if (esMultipart) {
      List<FileItem> list = recogeParam(request);
      Iterator<FileItem> iter = list.iterator();
      while (iter.hasNext()) {
        FileItem item = iter.next();
        String name = item.getFieldName();

        switch (name) {
          case "accion":
            accion = item.getString();
            break;
          case "codigo":
            int codigoProducto = Integer.parseInt(item.getString());
            producto.setCodigo(codigoProducto);
            break;
          case "descripcion":
            String descripcion = item.getString();
            producto.setDescripcion(descripcion);
            break;
          case "marca":
            String marca = item.getString();
            producto.setMarca(marca);
            break;
          case "precio":
            double precio = Double.parseDouble(item.getString());
            producto.setPrecio(precio);
            break;
          case "imagen":
            byte[] imagen = item.get();
            if (imagen.length > 0) {
              producto.setImagen(imagen);
            } else {
              int cod = producto.getCodigo();
              producto.setImagen(productoDAO.obtenerProducto(cod).getImagen());
            }
            break;
        }
      }
    } else {
      accion = request.getParameter("accion");
    }

    if (accion == null) {
      msg = "Ingreso no autorizado";
    } else {
      switch (accion) {
        case "insertar":
          msg = productoDAO.insertarProducto(producto);
          break;
        case "eliminar":
          String codigos = request.getParameter("codigos");
          List<Integer> codigosProductos = getCodigos(codigos);
          msg = productoDAO.eliminarProductos(codigosProductos);
          break;
        case "actualizar":
          msg = productoDAO.actualizarProducto(producto);
          break;
        case "obtenerproductos":    
          target = "/" + request.getParameter("target") + ".jsp";
          
          List<Producto> productos = productoDAO.obtenerProductos();
          request.setAttribute("productos", productos);
          break;
        case "obtenerproducto":
          int codigo = Integer.parseInt(request.getParameter("codigo"));
          producto = productoDAO.obtenerProducto(codigo);
          request.setAttribute("producto", producto);
          target = "/productoUpd.jsp";
          break;
        default:
          msg = "Solicitud no reconocida";
      }
    }

    if (msg == null) {
      msg = "Solicitud atendida";
    }
    request.setAttribute("msg", msg);
    RequestDispatcher rp = request.getRequestDispatcher(target);
    rp.forward(request, response);
  }

  private List<FileItem> recogeParam(HttpServletRequest request) {
    List<FileItem> list = null;
    try {
      FileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload upload = new ServletFileUpload(factory);
      list = upload.parseRequest(request);
    } catch (FileUploadException ex) {
    }
    return list;
  }

  private List<Integer> getCodigos(String codigosProductos) {
    List<Integer> list = null;
    if ((codigosProductos != null) && (codigosProductos.trim().length() > 0)) {
      String[] id = codigosProductos.split(",");
      list = new LinkedList<>();
      for (String ix : id) {
        Integer x = Integer.parseInt(ix);
        if (x != null) {
          list.add(x);
        } else {
          list = null;
          break;
        }
      }
    }
    return list;
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

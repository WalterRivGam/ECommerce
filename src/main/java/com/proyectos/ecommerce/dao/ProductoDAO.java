package com.proyectos.ecommerce.dao;

import com.proyectos.ecommerce.dto.Producto;
import java.util.List;

public interface ProductoDAO {

  public String insertarProducto(Producto producto);

  public List<Producto> obtenerProductos();

  public Producto obtenerProducto(int codigoProducto);

  public String actualizarProducto(Producto producto);

  public String eliminarProducto(List<Integer> codigosProductos);

}

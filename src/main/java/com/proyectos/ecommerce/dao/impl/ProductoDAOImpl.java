package com.proyectos.ecommerce.dao.impl;

import com.proyectos.ecommerce.dao.ProductoDAO;
import com.proyectos.ecommerce.dao.SqlConecta;
import com.proyectos.ecommerce.dto.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

  private final SqlConecta conecta;

  public ProductoDAOImpl() {
    this.conecta = new SqlConecta();
  }

  @Override
  public String insertarProducto(Producto producto) {
    String msgError = null;
    String sql = "INSERT INTO producto(codigoproducto, descripcion, marca, precio, imagen) "
            + "VALUES(?, ?, ?, ?, ?)";

    try (Connection cn = conecta.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setInt(1, producto.getCodigoProducto());
      ps.setString(2, producto.getDescripcion());
      ps.setString(3, producto.getMarca());
      ps.setDouble(4, producto.getPrecio());
      ps.setBytes(5, producto.getImagen());

      int filasAfectadas = ps.executeUpdate();
      if (filasAfectadas == 0) {
        throw new SQLException("0 filas afectadas");
      }
    } catch (SQLException e) {
      msgError = e.getMessage();
    }

    return msgError;
  }

  @Override
  public List<Producto> obtenerProductos() {
    List<Producto> list = null;
    String sql = "SELECT (codigoproducto, descripcion, marca, precio, imagen) FROM producto";

    try (Connection cn = conecta.obtenerConexion(); Statement ps = cn.createStatement()) {
      ResultSet rs = ps.executeQuery(sql);
      list = new ArrayList<>();
      while (rs.next()) {
        Producto p = new Producto();
        p.setCodigoProducto(rs.getInt(1));
        p.setDescripcion(rs.getString(2));
        p.setMarca(rs.getString(3));
        p.setPrecio(rs.getDouble(4));
        p.setImagen(rs.getBytes(5));

        list.add(p);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  @Override
  public Producto obtenerProducto(int codigoProducto) {
    Producto producto = null;
    String sql = "SELECT (codigoproducto, descripcion, marca, precio, imagen) FROM producto "
            + "WHERE codigoproducto = ?";

    try (Connection cn = conecta.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setInt(1, codigoProducto);
      ResultSet rs = ps.executeQuery();
      rs.next();
      producto = new Producto();
      producto.setCodigoProducto(rs.getInt(1));
      producto.setDescripcion(rs.getString(2));
      producto.setMarca(rs.getString(3));
      producto.setPrecio(rs.getDouble(4));
      producto.setImagen(rs.getBytes(5));

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return producto;
  }

  @Override
  public String actualizarProducto(Producto producto) {
    String resultado = null;
    String sql = "UPDATE producto SET descripcion = ?, marca = ?, precio = ?, imagen = ? "
            + "WHERE codigoproducto = ?";

    try (Connection cn = conecta.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
      ps.setString(1, producto.getDescripcion());
      ps.setString(2, producto.getMarca());
      ps.setDouble(3, producto.getPrecio());
      ps.setBytes(4, producto.getImagen());
      int filasAfectadas = ps.executeUpdate();
      if (filasAfectadas == 0) {
        throw new SQLException("0 filas afectadas");
      }
    } catch (SQLException e) {
      resultado = e.getMessage();
    }

    return resultado;
  }

  @Override
  public String eliminarProductos(List<Integer> codigosProductos) {
    String result = null;
    String sql = "DELETE FROM producto WHERE codigoproducto=?";

    try (Connection cn = conecta.obtenerConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
      for (Integer cod : codigosProductos) {
        ps.setInt(1, cod);
        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas == 0) {
          throw new SQLException("Código " + cod + " es incorrecto");
        }
      }
    } catch (SQLException e) {
      result = e.getMessage();
    }

    return result;
  }

}

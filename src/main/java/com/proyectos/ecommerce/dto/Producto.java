package com.proyectos.ecommerce.dto;

public class Producto {

  private int codigoProducto;
  private String descripcion;
  private String marca;
  private double precio;
  private byte[] imagen;

  public int getCodigoProducto() {
    return codigoProducto;
  }

  public void setCodigoProducto(int codigoProducto) {
    this.codigoProducto = codigoProducto;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public byte[] getImagen() {
    return imagen;
  }

  public void setImagen(byte[] imagen) {
    this.imagen = imagen;
  }

}

package com.proyectos.ecommerce.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConecta {

  public Connection obtenerConexion() {
    Connection cn = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      cn = DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/bdecommerce?serverTimezone=UTC", "root", "WalSql579");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return cn;
  }
}

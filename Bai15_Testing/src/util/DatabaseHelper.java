package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {

  private static final String CONNECTION_URL = "jdbc:sqlite:Bai15_Testing/database/student_management.db";

  private static volatile DatabaseHelper instance = null;

  private static volatile Connection conn = null;

  private DatabaseHelper() {
  }

  public Connection getConnection() {
    return conn;
  }

  public static DatabaseHelper getInstance() {
    if (instance == null) {
      synchronized (DatabaseHelper.class) {
        if (instance == null) {
          instance = new DatabaseHelper();
        }
      }
    }

    return instance;
  }


  public Connection connect() {
    try {
      conn = DriverManager.getConnection(CONNECTION_URL);
      System.out.println("Connection to SQLite has been established.");
      return conn;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }


  public void closeConnection(Connection con) throws SQLException {
    if (con != null) {
      con.close();
    }
  }

  public void closePreparedStatement(PreparedStatement stmt) throws SQLException {
    if (stmt != null) {
      stmt.close();
    }
  }

  public void closeResultSet(ResultSet rs) throws SQLException {
    if (rs != null) {
      rs.close();
    }
  }




}

package repository;

import entity.StudentEntity;
import enumeration.DepartmentType;
import enumeration.StudentType;
import exception.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import util.DatabaseHelper;

public class StudentRepository {

  public static final String INSERT_QUERY = "INSERT INTO student(student_id, student_type, name, entry_score, entry_year, department_type, in_service_place_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
  public static final String UPDATE_BY_ID_QUERY = "UPDATE student SET student_type=? name=?, entry_score=?, entry_year=?, department_type=?, in_service_place_id=? WHERE student_id=?";
  public static final String SELECT_BY_ID_QUERY = "SELECT * WHERE student_id=?";
  public static final String SELECT_ALL_QUERY = "SELECT * from student_id";

  public StudentRepository() {
  }

  public StudentEntity getStudentById(String studentId) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StudentEntity student = new StudentEntity();
    try {
      connection = DatabaseHelper.getInstance().connect();
      if (connection == null || studentId.isEmpty()) {
        throw new DatabaseException("getStudentById-Connection is null");
      }
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(SELECT_BY_ID_QUERY);
      ps.setString(1, studentId);
      rs = ps.executeQuery();
      System.out.println("retrivePerson => " + ps.toString());
      while (rs.next()) {
        student.setStudentId(rs.getString("student_id"));
        student.setStudentType(StudentType.valueOf(rs.getString("student_type")));
        student.setName(rs.getString("name"));
        student.setEntryScore(
            rs.getFloat("entry_score")); // this is to show that we can retrive using index of the column
        student.setDepartmentType(DepartmentType.valueOf(rs.getString("department_type")));
        student.setInServicePlaceId(rs.getString("in_service_place_id"));
        student.setEntryYear(Year.of(rs.getInt("year")));
      }

    } catch (SQLException e) {
      throw e;

    } finally {
      try {
        DatabaseHelper.getInstance().closeResultSet(rs);
        DatabaseHelper.getInstance().closePreparedStatement(ps);
        DatabaseHelper.getInstance().closeConnection(connection);
      } catch (SQLException e) {
        throw e;
      }
    }
    return student;
  }

  public void insertStudent(StudentEntity entity) throws SQLException {

    PreparedStatement ps = null;
    Connection connection = null;
    try {
      connection = DatabaseHelper.getInstance().connect();
      if (connection == null || entity == null) {
        throw new DatabaseException("insertStudent-Connection is null");
      }
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(INSERT_QUERY);
      ps.setString(1, entity.getStudentId());
      ps.setString(2, entity.getStudentType());
      ps.setString(3, entity.getName());
      ps.setDouble(4, entity.getEntryScore());
      ps.setInt(5, entity.getEntryYear());
      ps.setString(6, entity.getDepartmentType());
      ps.setString(7, entity.getInServicePlaceId());

      ps.execute();
      System.out.println("insertStudent => " + ps.toString());
      connection.commit();

    } catch (SQLException e) {
      try {
        if (connection != null) {
          connection.rollback();
        }
      } catch (SQLException e1) {
        throw e1;
      }
      throw e;
    } finally {
      try {
        DatabaseHelper.getInstance().closePreparedStatement(ps);
        DatabaseHelper.getInstance().closeConnection(connection);
      } catch (SQLException e) {
        throw e;
      }
    }

  }

}

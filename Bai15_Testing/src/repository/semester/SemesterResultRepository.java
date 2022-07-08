package repository.semester;

import entity.SemesterResultEntity;
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

public class SemesterResultRepository {

    public static final String INSERT_QUERY = "INSERT INTO semester_result(student_id, semester_type, year, average_score) VALUES(?, ?, ?, ?)";
    public static final String SELECT_BY_ID_QUERY = "SELECT * FROM semester_result AS seme, student AS stu WHERE (seme.student_id=stu.student_id) AND (semester_type=?) AND (year=?) AND (stu.student_id=?)";
//    public static final String SELECT_ALL_QUERY = "SELECT * from student";
    public SemesterResultRepository() {
    }

  public SemesterResultEntity getSemesterResultById(String student_id, String semester_type, int year) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    SemesterResultEntity semesterResult = null;

    try {
      connection = DatabaseHelper.getInstance().connect();
      if (connection == null || semester_type.isEmpty() || year == 0 || student_id == null) {
        throw new DatabaseException("getSemesterResultById-Connection is null");
      }
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(SELECT_BY_ID_QUERY);
      ps.setString(1, semester_type);
      ps.setInt(2, year);
      ps.setString(3, student_id);
      rs = ps.executeQuery();
      System.out.println("retrieveSemesterResult => " + ps.toString());
      if (!rs.isBeforeFirst()) {
        return null;
      }
      semesterResult = new SemesterResultEntity();
      while (rs.next()) {
        semesterResult.setStudent_id(rs.getString("student_id"));
        semesterResult.setSemester_type(rs.getString("semester_type"));
        semesterResult.setYear(rs.getInt("year"));
        semesterResult.setAverage_score(rs.getDouble("average_score"));
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
    return semesterResult;
  }

    public void insertSemesterResult(SemesterResultEntity entity) throws SQLException {
      PreparedStatement ps = null;
      Connection connection = null;
      try {
        connection = DatabaseHelper.getInstance().connect();
        if (connection == null || entity == null) {
          throw new DatabaseException("insertSemesterResult-Connection is null");
        }
        connection.setAutoCommit(false);
        ps = connection.prepareStatement(INSERT_QUERY);
        ps.setString(1, entity.getStudent_id());
        ps.setString(2, entity.getSemester_type());
        ps.setInt(3, entity.getYear());
        ps.setDouble(4, entity.getAverage_score());
        ps.execute();
        System.out.println("insertResult => " + ps.toString());
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

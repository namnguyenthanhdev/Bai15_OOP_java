package repository.student;

import entity.InServicePlaceEntity;
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
import java.util.ArrayList;
import java.util.List;
import util.DatabaseHelper;

public class StudentRepository {

  public static final String INSERT_QUERY = "INSERT INTO student(student_id, student_type, name, entry_score, entry_year, department_type, in_service_place_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
  public static final String UPDATE_BY_ID_QUERY = "UPDATE student SET student_type=?, name=?, entry_score=?, entry_year=?, department_type=?, in_service_place_id=? WHERE student_id=?";
  public static final String SELECT_BY_ID_QUERY = "SELECT DISTINCT * FROM student WHERE student_id= ?";
  public static final String SELECT_STU_BY_STUDENT_TYPE_DEPARTMENT_PLACE_QUERY = "SELECT DISTINCT stu.name FROM student AS stu LEFT JOIN inservice_place AS plc ON stu.in_service_place_id=plc.place_id WHERE stu.student_type=?  AND stu.department_type=? AND plc.address=?";
  public static final String SELECT_AVG_BY_ID_QUERY = "SELECT DISTINCT res.average_score FROM semester_result AS res, student AS stu WHERE stu.student_id=res.student_id AND stu.student_id=? AND res.semester_type=? AND res.year=?";
//  public static final String SELECT_AVG_OVER_8_BY_DEPARTMENT_QUERY = "SELECT res.average_score FROM student AS stu, semester_result AS res WHERE stu.student_id=res.student_id AND res.average_score>=8 AND stu.department_type=? GROUP BY stu.student_id ORDER BY res.semester_type DESC, res.year DESC LIMIT 5";
  public static final String SELECT_AVG_OVER_8_BY_DEPARTMENT_QUERY = "SELECT DISTINCT name, average_score, semester_type, year FROM student AS stu, semester_result AS res WHERE stu.student_id=res.student_id AND res.average_score>=8 AND stu.department_type=? "
    + "GROUP BY stu.name ORDER BY res.semester_type DESC , res.year DESC LIMIT (SELECT COUNT(name) FROM student)";

  public static final String SELECT_MAX_AVG_BY_ALL_QUERY = "SELECT MAX(res.average_score) AS max_avg_score, name FROM semester_result AS res, student AS stu"
      + " WHERE stu.student_id=res.student_id AND department_type=? GROUP BY name ";
  public static final String SELECT_STUDENT_ACS_BY_ENTRYSCORE_AND_DESC_BY_ENTRYYEAR = "SELECT DISTINCT name FROM student WHERE department_type=? ORDER BY entry_score ASC, entry_year DESC";
//  sortStudentAscendingByScoreAndDescendingByYear
  public static final String DELETE_BY_ID = "DELETE FROM student WHERE student_id=?";
  public StudentRepository() {
  }
  public List<String> sortStudentAscendingByScoreAndDescendingByYear(DepartmentType departmentType)
      throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StudentEntity studentEntity = null;
    List<String> nameSortingByScoreAndYearList = new ArrayList<>();

    try{
      connection = DatabaseHelper.getInstance().connect();
      if (connection == null){
        throw new DatabaseException("sortStudentAscendingByScoreAndDescendingByYear-Connection is null.");
      }
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(SELECT_STUDENT_ACS_BY_ENTRYSCORE_AND_DESC_BY_ENTRYYEAR);
      ps.setString(1, String.valueOf(departmentType));
      rs = ps.executeQuery();
      System.out.println("retrieveStudentAscendingByScoreAndDescendingByYear -> " + ps);

      if(!rs.isBeforeFirst()){
        return null;
      }
      studentEntity = new StudentEntity();
      while(rs.next()){
        studentEntity.setName(rs.getString("name"));
        nameSortingByScoreAndYearList.add(String.valueOf(studentEntity.getName()));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally{
      try{
        DatabaseHelper.getInstance().closeResultSet(rs);
        DatabaseHelper.getInstance().closePreparedStatement(ps);
        DatabaseHelper.getInstance().closeConnection(connection);
      } catch (SQLException e){
        throw e;
      }
    }
    return nameSortingByScoreAndYearList;
  }
  public List<String> getMaxAverageScoreStudentFromAllSemester(DepartmentType departmentType)
      throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    SemesterResultEntity semesterResult = null;
    List<String> maxAverageScoreList = new ArrayList<>();

    try{
      connection = DatabaseHelper.getInstance().connect();
      if (connection == null){
        throw new DatabaseException("getMaxAverageScoreStudentFromAllSemester-Connection is null.");
      }
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(SELECT_MAX_AVG_BY_ALL_QUERY);
      ps.setString(1, String.valueOf(departmentType));
      rs = ps.executeQuery();
      System.out.println("retrieveMaxAverageScore -> " + ps);

      if(!rs.isBeforeFirst()){
        return null;
      }
      semesterResult = new SemesterResultEntity();
      while(rs.next()){
        semesterResult.setAverage_score(rs.getDouble("max_avg_score"));
        maxAverageScoreList.add(String.valueOf(semesterResult.getAverage_score()));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally{
      try{
        DatabaseHelper.getInstance().closeResultSet(rs);
        DatabaseHelper.getInstance().closePreparedStatement(ps);
        DatabaseHelper.getInstance().closeConnection(connection);
      } catch (SQLException e){
        throw e;
      }
    }
    return maxAverageScoreList;
  }
  public List<String> getAverageScoreHigherThanEightInRecentSemesterByDepartmentType(DepartmentType departmentType)
      throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    SemesterResultEntity semesterResult = null;
    List<String> averageScoreOver8List = new ArrayList<>();

    try{
      connection = DatabaseHelper.getInstance().connect();
      if (connection == null){
        throw new DatabaseException("getAverageScoreHigherThanEightInRecentSemesterByDepartmentType-Connection is null.");
      }
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(SELECT_AVG_OVER_8_BY_DEPARTMENT_QUERY);
      ps.setString(1, String.valueOf(departmentType));
      rs = ps.executeQuery();
      System.out.println("retrieveAverageScoreOverThan8 -> " + ps);

      if(!rs.isBeforeFirst()){
        return null;
      }
      semesterResult = new SemesterResultEntity();
      while(rs.next()){
        semesterResult.setAverage_score(rs.getDouble("average_score"));
        averageScoreOver8List.add(String.valueOf(semesterResult.getAverage_score()));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally{
      try{
        DatabaseHelper.getInstance().closeResultSet(rs);
        DatabaseHelper.getInstance().closePreparedStatement(ps);
        DatabaseHelper.getInstance().closeConnection(connection);
      } catch (SQLException e){
        throw e;
      }
    }
    return averageScoreOver8List;
  }

  public StudentEntity getStudentById(String studentId) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    StudentEntity student = null;
    try {
      connection = DatabaseHelper.getInstance().connect();
      if (connection == null || studentId.isEmpty()) {
        throw new DatabaseException("getStudentById-Connection is null");
      }
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(SELECT_BY_ID_QUERY);
      ps.setString(1, studentId);
      rs = ps.executeQuery();
      System.out.println("retrievePerson => " + ps.toString());
      if (!rs.isBeforeFirst()) {
        return null;
      }
      student = new StudentEntity();
      while (rs.next()) {
        student.setStudentId(rs.getString("student_id"));
        student.setStudentType(StudentType.valueOf(rs.getString("student_type")));
        student.setName(rs.getString("name"));
        student.setEntryScore(
            rs.getFloat("entry_score")); // this is to show that we can retrieve using index of the column
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

  public double getAverageScoreById(String studentId, String semester_type, int year) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    SemesterResultEntity semester_result = null;
    try {
      connection = DatabaseHelper.getInstance().connect();
      if (connection == null || studentId.isEmpty()) {
        throw new DatabaseException("getScoreById-Connection is null");
      }
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(SELECT_AVG_BY_ID_QUERY);
      ps.setString(1, studentId);
      ps.setString(2, semester_type);
      ps.setInt(3, year);
      rs = ps.executeQuery();
      System.out.println("retrieveAverageScore => " + ps.toString());
      if (!rs.isBeforeFirst()) {
        return 0;
      }
      semester_result = new SemesterResultEntity();
      while (rs.next()) {
//        semester_result.setStudent_id(rs.getString("student_id"));
//       semester_result.setSemester_type(rs.getString("semester_type"));
//       semester_result.setYear(rs.getInt("year"));
       semester_result.setAverage_score(rs.getDouble("average_score"));
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
    return semester_result.getAverage_score();
  }

  public List<String> getStudentByDepartmentTypeAndStudentTypeAndAddress(String studentType, String department, String address)
      throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    InServicePlaceEntity inServicePlaceEntity = null;
    StudentEntity studentEntity = null;
    List<String> students = new ArrayList<>();

    try{
      connection = DatabaseHelper.getInstance().connect();
      if (connection == null || department == null || studentType == null || address == null){
        throw new DatabaseException("getStudentByDepartmentTypeAndStudentTypeAndAddress-Connection is null.");
      }
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(SELECT_STU_BY_STUDENT_TYPE_DEPARTMENT_PLACE_QUERY);
      ps.setString(1, studentType);
      ps.setString(2, department);
      ps.setString(3, address);
      rs = ps.executeQuery();
      System.out.println("retrieveIdNameType -> " + ps);
      if (!rs.isBeforeFirst()){
        return null;
      }

      studentEntity = new StudentEntity();
      while(rs.next()){
        studentEntity.setName(rs.getString("name"));
        students.add(studentEntity.getName());
      }
    } catch (SQLException e){
      throw e;
    } finally{
      try{
        DatabaseHelper.getInstance().closeResultSet(rs);
        DatabaseHelper.getInstance().closePreparedStatement(ps);
        DatabaseHelper.getInstance().closeConnection(connection);
      } catch (SQLException e){
        throw e;
      }
    }
    return students;
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

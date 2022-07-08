package repository.inServicePlace;

import dto.InServicePlace;
import entity.InServicePlaceEntity;
import exception.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DatabaseHelper;

public class InServicePlaceRepository {
  public static final String SELECT_PLACE_BY_ID = "SELECT * FROM inservice_place WHERE address=? ";
  //why we can not use private static final
  public static final String INSERT_PLACE_BY_ID = "INSERT INTO inservice_place (place_id, address, contact_number) VALUES (?, ?, ?)";

  public InServicePlaceRepository() {
  }

  public InServicePlaceEntity getInServicePlaceById(String address){
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    InServicePlaceEntity placeEntity = null;

    try{
      connection = DatabaseHelper.getInstance().connect();
      if (connection == null || address == null){
        throw new DatabaseException("getInServicePlaceById-Connection is null.");
      }
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(SELECT_PLACE_BY_ID);
      ps.setString(1, address);
      rs = ps.executeQuery();
      System.out.println("retrieveInServicePlace => " + ps);
      if (!rs.isBeforeFirst()){
        return null;
      }
      placeEntity = new InServicePlaceEntity();
      while(rs.next()){
        placeEntity.setPlaceId(rs.getString("place_id"));
        placeEntity.setAddress(rs.getString("address"));
        placeEntity.setContact_number(rs.getString("contact_number"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try{
        DatabaseHelper.getInstance().closeResultSet(rs);
        DatabaseHelper.getInstance().closePreparedStatement(ps);
        DatabaseHelper.getInstance().closeConnection(connection);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return placeEntity;
  }

  public void insertInServicePlace(InServicePlaceEntity entity) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;

    try{
      connection = DatabaseHelper.getInstance().connect();
      if (connection == null || entity == null){
        throw new DatabaseException("insertInServicePlace-Connection is null");
      }
      connection.setAutoCommit(false);
      ps = connection.prepareStatement(INSERT_PLACE_BY_ID);
      ps.setString(1, entity.getPlaceId());
      ps.setString(2, entity.getAddress());
      ps.setString(3, entity.getContact_number());
      ps.execute();
      System.out.println("insertPlace => " + ps);
      connection.commit();


    } catch (SQLException e) {
      try{
        if(connection != null){
          connection.rollback();
        }
      } catch (SQLException ex) {
        throw ex;
      }
      throw e;
    } finally {
        try{
          DatabaseHelper.getInstance().closePreparedStatement(ps);
          DatabaseHelper.getInstance().closeConnection(connection);
        } catch (SQLException e){
          throw e;
        }
      }
  }
}

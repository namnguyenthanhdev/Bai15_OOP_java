package service.inServicePlace;

import dto.InServicePlace;
import entity.InServicePlaceEntity;
import java.sql.SQLException;

public interface InServicePlaceAdvanceInterface {
  InServicePlace createNewPlace(InServicePlace place) throws SQLException;

  InServicePlaceEntity getPlace(String address);

}

package service.inServicePlace;

import dto.InServicePlace;
import entity.InServicePlaceEntity;
import exception.AlreadyExistException;
import exception.InServicePlaceNotFoundException;
import java.sql.SQLException;
import repository.inServicePlace.InServicePlaceRepository;

public class InServicePlaceAdvanceImpl implements InServicePlaceAdvanceInterface{

  private InServicePlaceRepository inServicePlaceRepository;

  public InServicePlaceAdvanceImpl(InServicePlaceRepository inServicePlaceRepository) {
    this.inServicePlaceRepository = inServicePlaceRepository;
  }

  public InServicePlaceAdvanceImpl() {
//    createNewPlace(InServicePlace.createNewInServicePlace("TP.HCM", "0902297861"));
//    createNewPlace(InServicePlace.createNewInServicePlace("Binh Duong", "0938948764"));

  }

  @Override
  public InServicePlace createNewPlace(InServicePlace place) throws SQLException {
//    InServicePlace newPlace = InServicePlace.createNewInServicePlace(place);
//    if (inServicePlaceRepository.getInServicePlaceById(newPlace.getId()) != null) {
//      throw new AlreadyExistException(newPlace.getId());
//    }
    inServicePlaceRepository.insertInServicePlace(new InServicePlaceEntity(place));
    return place;
  }
  @Override
  public InServicePlaceEntity getPlace(String address) {
    if (inServicePlaceRepository.getInServicePlaceById(address) == null) {
      throw new InServicePlaceNotFoundException();
    }
    return inServicePlaceRepository.getInServicePlaceById(address);  }
}

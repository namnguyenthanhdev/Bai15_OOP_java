package service.inServicePlace;

import dto.InServicePlace;
import exception.AlreadyExistException;
import exception.InServicePlaceNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class InServicePlaceImplements implements InServicePlaceInterface {

  private Map<String, InServicePlace> placeMap = new HashMap<>();

  public InServicePlaceImplements() {
    createNewPlace(InServicePlace.createNewInServicePlace("TP.HCM", "0902297861"));
    createNewPlace(InServicePlace.createNewInServicePlace("Binh Duong", "0938948764"));
  }

  @Override
  public InServicePlace createNewPlace(InServicePlace place) {
    InServicePlace newPlace = InServicePlace.createNewInServicePlace(place);
    if (placeMap.containsKey(newPlace.getId())) {
      throw new AlreadyExistException(newPlace.getId());
    }
    placeMap.put(newPlace.getId(), newPlace);
    return newPlace;
  }

  @Override
  public InServicePlace getPlace(String placeId) {
    if (placeMap.isEmpty() || !placeMap.containsKey(placeId)) {
      throw new InServicePlaceNotFoundException();
    }
    return placeMap.get(placeId);
  }
}

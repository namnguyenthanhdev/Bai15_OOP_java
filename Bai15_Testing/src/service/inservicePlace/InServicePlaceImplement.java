package service.inservicePlace;

import dto.InServicePlace;
import exception.AlreadyExistException;
import exception.InServicePlaceNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class InServicePlaceImplement implements InServicePlaceInterface {

  private Map<String, InServicePlace> placeMap = new HashMap<>();

  @Override
  public InServicePlace createNewPlace(InServicePlace place) {
    InServicePlace newPlace = InServicePlace.createNewPlace(place);
    if (placeMap.containsKey(newPlace.getPlaceId())) {
      throw new AlreadyExistException(newPlace.getPlaceId());
    }
    placeMap.put(newPlace.getPlaceId(), newPlace);
    return newPlace;
  }

  @Override
  public InServicePlace getPlace(String placeId) {
    if (placeMap.isEmpty() || !placeMap.containsKey(placeId)) {
      throw new InServicePlaceNotFoundException(placeId);
    }
    return placeMap.get(placeId);
  }
}

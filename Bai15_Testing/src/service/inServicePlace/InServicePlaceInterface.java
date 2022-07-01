package service.inServicePlace;

import dto.InServicePlace;

public interface InServicePlaceInterface {

  InServicePlace createNewPlace(InServicePlace place);

  InServicePlace getPlace(String placeId);

}

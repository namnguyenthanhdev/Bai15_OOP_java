package dto;

import exception.InvalidPlaceIdException;
import util.ValidatorUtil;

public class InServicePlace {

  private static final String PLACE_ID_PREFIX = "PLACE_";
  private static int currentPlaceIdGenerator = 0;
  private String id;
  private String address;
  private String contactNumber;

  public InServicePlace(String id, String address, String contactNumber) {
    this.id = id;
    setAddress(address);
    setContactNumber(contactNumber);
  }

  protected static String generateNewPlaceId() {
    currentPlaceIdGenerator += 1;
    return PLACE_ID_PREFIX + currentPlaceIdGenerator;
  }

  public static void checkValidId(String id) {
    if (id == null || !id.startsWith(PLACE_ID_PREFIX)) {
      throw new InvalidPlaceIdException(id);
    }
  }

  public static InServicePlace createNewInServicePlace(String address, String contactNumber) {
    return new InServicePlace(" ", address, contactNumber);
  }

  public static InServicePlace createNewInServicePlace(InServicePlace place) {
    return new InServicePlace(generateNewPlaceId(), place.getAddress(), place.getContactNumber());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    if (ValidatorUtil.checkValidContactNumber(contactNumber)) {
      this.contactNumber = contactNumber;
    }
  }

  @Override
  public String toString() {
    return "InServicePlace{" +
        "id='" + getId() + '\'' +
        ", address='" + getAddress() + '\'' +
        ", contactNumber='" + getContactNumber() + '\'' +
        '}';
  }

}
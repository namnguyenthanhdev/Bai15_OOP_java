package dto;

public class InServicePlace {

  private static int generatorId = 0;

  private static final String ID_PREFIX = "PLACE_";


  protected static String generateId() {
    generatorId += 1;
    return ID_PREFIX + generatorId;
  }


  private String placeId;
  private String address;

  private String contactNumber;

  public static InServicePlace createNewPlace(String address, String contactNumber) {
    return new InServicePlace(generateId(), address, contactNumber);
  }

  public static InServicePlace createNewPlace(InServicePlace dto) {
    return new InServicePlace(generateId(), dto.getAddress(), dto.getContactNumber());
  }


  public InServicePlace(String placeId, String address, String contactNumber) {
    setPlaceId(placeId);
    setAddress(address);
    setContactNumber(contactNumber);
  }


  public String getPlaceId() {
    return placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
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
    this.contactNumber = contactNumber;
  }
}

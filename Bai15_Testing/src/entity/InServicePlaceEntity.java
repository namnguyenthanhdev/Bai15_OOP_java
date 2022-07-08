package entity;

import dto.InServicePlace;

public class InServicePlaceEntity {
  private String placeId;
  private String address;
  private String contact_number;

  public InServicePlaceEntity(InServicePlace dto) {
    setPlaceId(dto.getId());
    setAddress(dto.getAddress());
    setContact_number(dto.getContactNumber());
  }

  public InServicePlaceEntity() {
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

  public String getContact_number() {
    return contact_number;
  }

  public void setContact_number(String contact_number) {
    this.contact_number = contact_number;
  }
}

package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.skillbox.socialnetwork.model.enumeration.MessagesPermissionPerson;

public class PersonApi extends AbstractResponse {

  private int id;
  @JsonProperty("first_name")
  private String firstName;
  @JsonProperty("last_name")
  private String lastName;
  @JsonProperty("reg_date")
  private long regDate;
  @JsonProperty("birth_date")
  private long birthDate;
  private String email;
  private String phone;
  private String photo;
  private String about;
  private String city;
  private String country;
  @JsonProperty("messages_permission")
  private MessagesPermissionPerson messagesPermission;

  @JsonProperty("last_online_time")
  private long lastOnlineTime;
  @JsonProperty("is_blocked")
  private boolean isBlocked;
  @JsonProperty("is_friend")
  private boolean isFriend;

  private String token;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirst_name() {
    return firstName;
  }

  public void setFirst_name(String first_name) {
    this.firstName = first_name;
  }

  public String getLast_name() {
    return lastName;
  }

  public void setLast_name(String last_name) {
    this.lastName = last_name;
  }

  public long getReg_date() {
    return regDate;
  }

  public void setReg_date(long reg_date) {
    this.regDate = reg_date;
  }

  public long getBirth_date() {
    return birthDate;
  }

  public void setBirth_date(long birth_date) {
    this.birthDate = birth_date;
  }

  public long getLast_online_time() {
    return lastOnlineTime;
  }

  public void setLast_online_time(long last_online_time) {
    this.lastOnlineTime = last_online_time;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public String getCity() {
      return city;
  }

  public void setCity(String city) {
      this.city = city;
  }

  public String getCountry() {
      return country;
  }

  public void setCountry(String country) {
      this.country = country;
  }

  public MessagesPermissionPerson getMessages_permission() {
    return messagesPermission;
  }

  public void setMessages_permission(MessagesPermissionPerson messages_permission) {
    this.messagesPermission = messages_permission;
  }

  public boolean isIs_blocked() {
    return isBlocked;
  }

  public void setIs_blocked(boolean is_blocked) {
    this.isBlocked = is_blocked;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public boolean isFriend() {
    return isFriend;
  }

  public void setFriend(boolean friend) {
    isFriend = friend;
  }
}

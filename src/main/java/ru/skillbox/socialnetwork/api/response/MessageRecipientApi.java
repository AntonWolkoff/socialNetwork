package ru.skillbox.socialnetwork.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRecipientApi extends AbstractResponse {

   private int id;
   @JsonProperty("first_name")
   private String firstName;
   @JsonProperty("last_name")
   private String lastName;
   @JsonProperty("photo")
   private String photo;
   @JsonProperty("last_online_time")
   private Long lastOnlineTime;

   public MessageRecipientApi() {
   }

   public MessageRecipientApi(int id, String firstName, String lastName, String photo,
       Long lastOnlineTime) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.photo = photo;
      this.lastOnlineTime = lastOnlineTime;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getPhoto() {
      return photo;
   }

   public void setPhoto(String photo) {
      this.photo = photo;
   }

   public Long getLastOnlineTime() {
      return lastOnlineTime;
   }

   public void setLastOnlineTime(Long lastOnlineTime) {
      this.lastOnlineTime = lastOnlineTime;
   }
}

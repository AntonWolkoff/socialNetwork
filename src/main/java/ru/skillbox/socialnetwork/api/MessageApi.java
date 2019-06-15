package ru.skillbox.socialnetwork.api;

import java.util.Date;

public class MessageApi {

  private int id;
  private Date time;
  private int author_id;
  private int recipient_id;
  private String message_text;
  private statuses read_status;

  private enum statuses {SENT, READ}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public int getAuthor_id() {
    return author_id;
  }

  public void setAuthor_id(int author_id) {
    this.author_id = author_id;
  }

  public int getRecipient_id() {
    return recipient_id;
  }

  public void setRecipient_id(int recipient_id) {
    this.recipient_id = recipient_id;
  }

  public String getMessage_text() {
    return message_text;
  }

  public void setMessage_text(String message_text) {
    this.message_text = message_text;
  }

  public statuses getRead_status() {
    return read_status;
  }

  public void setRead_status(statuses read_status) {
    this.read_status = read_status;
  }
}
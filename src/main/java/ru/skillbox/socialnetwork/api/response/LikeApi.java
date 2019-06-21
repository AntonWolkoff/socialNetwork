package ru.skillbox.socialnetwork.api.response;

public class LikeApi extends AbstractResponse{

  private int id;
  private long time;
  private int person_id;
  private int post_id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public int getPerson_id() {
    return person_id;
  }

  public void setPerson_id(int person_id) {
    this.person_id = person_id;
  }

  public int getPost_id() {
    return post_id;
  }

  public void setPost_id(int post_id) {
    this.post_id = post_id;
  }
}

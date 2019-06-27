package ru.skillbox.socialnetwork.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.model.Post;
import ru.skillbox.socialnetwork.model.PostComment;

@Repository
@Transactional
public class PostDAO {

  @Autowired
  private SessionFactory sessionFactory;

  public void addPost(Post post) {
    getCurrentSession().save(post);
  }

  public List<Post> getAllPosts() {
    return getCurrentSession().createQuery("from Post p").list();
  }

  public Post getPostById(int id) {
    return getCurrentSession().get(Post.class, id);
  }

  public void updatePost(Post post) {
    getCurrentSession().update(post);
  }

  public void deletePost(Post post) {
    getCurrentSession().delete(post);
  }


  public void addComment(PostComment comment) {
    getCurrentSession().save(comment);
  }

  public List<PostComment> getComments() {
    return getCurrentSession().createQuery("from Post_comment p").list();
  }

  public PostComment getCommentById(int id) {
    return getCurrentSession().get(PostComment.class, id);
  }

  public void updateComment(PostComment comment) {
    getCurrentSession().update(comment);
  }

  public void deleteComment(PostComment comment) {
    getCurrentSession().delete(comment);
  }

  private Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

}
package ru.skillbox.socialnetwork.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.socialnetwork.api.dto.FriendsParameters;
import ru.skillbox.socialnetwork.model.Friendship;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.enumeration.CodeFriendshipStatus;

@Repository
@Transactional
public class FriendsDAO {
//TODO Перенести логику в Service

  @Autowired
  private SessionFactory sessionFactory;

  @Autowired
  private PersonDAO personDAO;

  @Autowired
  private NotificationDAO notificationDAO;

  public List<Friendship> searchFriend(FriendsParameters parameters) {
    String query = "from Friendship f where src_person_id = " + parameters.getId() + " AND code = " + (CodeFriendshipStatus.FRIEND.ordinal() + 1);
    Query q = getCurrentSession().createQuery(query);
    ArrayList<Friendship> all = (ArrayList<Friendship>)q.list();
    ArrayList<Friendship> select = new ArrayList<>();
    for(Friendship f : all){
      if (f.getDstPerson().getFirstName().contains(parameters.getName()) || f.getDstPerson().getLastName().contains(parameters.getName())){
        select.add(f);
      }
    }
    int offset = select.size() >= parameters.getOffset() ? parameters.getOffset() : select.size();
    int itemPerPage = select.size() >= offset + parameters.getItemPerPage() ? offset + parameters.getItemPerPage() : select.size();
//при превышении длины списка возвращает последнее значение
    return select.subList(offset, itemPerPage);
  }

  public boolean deleteFriendById(FriendsParameters parameters) {
    Friendship source = getFriendshipByID(parameters);
    Friendship target = getFriendshipByTargetID(parameters);
    if(source.getCode().equals(CodeFriendshipStatus.SUBSCRIBED)){
        getCurrentSession().delete(source);
        getCurrentSession().delete(target);
    } else if (source.getCode().equals(CodeFriendshipStatus.FRIEND)){
        source.setCode(CodeFriendshipStatus.DECLINED);
        getCurrentSession().save(source);
        target.setCode(CodeFriendshipStatus.SUBSCRIBED);
        getCurrentSession().save(target);
    } else {
        return false;
    }
    return true;
  }

  public List<Friendship> getRequestsByName(FriendsParameters parameters) {
    String query = "FROM Friendship f WHERE dst_person_id = " + parameters.getId()
        + " AND code = " + (CodeFriendshipStatus.REQUEST.ordinal() + 1);
    Query q = getCurrentSession().createQuery(query);
    q.setFirstResult(parameters.getOffset());
    q.setMaxResults(parameters.getItemPerPage());
    return q.list();
  }

  public boolean addPersonAsFriendById(FriendsParameters parameters) {
    //самого себя в друзья в друзья не добавляем
    if (parameters.getId() == parameters.getTargetID()) return false;

    Friendship source = getFriendshipByID(parameters);
    Friendship target = getFriendshipByTargetID(parameters);
    //нет записей
    if (source == null){
      Friendship newFriend = new Friendship();
      newFriend.setCode(CodeFriendshipStatus.SUBSCRIBED);
      newFriend.setSrcPerson(parameters.getPerson());
      newFriend.setDstPerson(parameters.getTarget());
      getCurrentSession().save(newFriend);
      Friendship dstFriend = new Friendship();
      dstFriend.setCode(CodeFriendshipStatus.REQUEST);
      dstFriend.setSrcPerson(parameters.getTarget());
      dstFriend.setDstPerson(parameters.getPerson());
      getCurrentSession().save(dstFriend);
    } //есть реквест или отказ
      else if (source.getCode().equals(CodeFriendshipStatus.DECLINED) || source.getCode().equals(CodeFriendshipStatus.REQUEST)){
        source.setCode(CodeFriendshipStatus.FRIEND);
        getCurrentSession().save(source);
        target.setCode(CodeFriendshipStatus.FRIEND);
        getCurrentSession().save(target);
    } //остальные варианты
      else {
      return false;
    }
    return true;
  }

  public List<Friendship> getRecommendation(FriendsParameters parameters) {
    List<Friendship> listMyFriend = searchAllFriendForPerson(parameters.getPerson());//getCurrentSession().createQuery(queryRequest).list();
    List<Integer> listIdMyFriends = new ArrayList<>();

    for (Friendship friendship : listMyFriend) {
      if (friendship.getCode().equals(CodeFriendshipStatus.FRIEND)) {
        listIdMyFriends.add(friendship.getDstPerson().getId());
      }
    }

    Query query = getCurrentSession().createQuery ("from Friendship f where src_person_id in (:friendsId) AND dst_person_id != " + parameters.getId());
    query.setParameterList("friendsId", listIdMyFriends);
    List<Friendship>  list = query.list();

    return new ArrayList<>(list
        .stream()
        .collect(Collectors.toMap(f -> f.getDstPerson().getId(), Function.identity(), (o1, o2) -> o1))
        .values());
  }

  public List<Friendship> isAFriendOfUsers(int[] idsFriend, int currentId) {
    List<Friendship> idStatus = new ArrayList<>();
    for (int id : idsFriend) {
      String query = "from Friendship f where src_person_id = " + currentId + " AND dst_person_id = " + id;
      List<Friendship> listMyFriend = getCurrentSession().createQuery(query).list();
      if (listMyFriend.size() > 0) {
        idStatus.add(listMyFriend.get(0));
      }
    }
    return idStatus;
  }

  private Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  private List<Friendship> searchAllFriendForPerson(Person person) {
    String query = "from Friendship f where src_person_id = " + person.getId();
    List<Friendship> list = getCurrentSession().createQuery(query).list();
    return list;
  }

  private Friendship getFriendshipByTargetID(FriendsParameters parameters){
    String query = "from Friendship f where src_person_id = " + parameters.getTargetID()
            + " and dst_person_id = " + parameters.getId();
    List<Friendship> list = getCurrentSession().createQuery(query).list();
    return list.size() > 0 ? list.get(0) : null;
  }

  private Friendship getFriendshipByID(FriendsParameters parameters){
    String query = "from Friendship f where src_person_id = " + parameters.getId()
            + " and dst_person_id = " + parameters.getTargetID();
    List<Friendship> list = getCurrentSession().createQuery(query).list();
    return list.size() > 0 ? list.get(0) : null;
  }
}

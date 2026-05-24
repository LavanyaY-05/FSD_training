//package com.service;
//
//import com.ResourseNotFoundException;
//import com.model.User;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.List;
//
//public class UserService {
//    Session session;
//    public UserService(Session session){
//        this.session = session;
//    }
//
//    public void insertUser(User user) {
//        Transaction tx = session.beginTransaction();
//        session.persist(user);
//        tx.commit();
//    }
//
//    public List<User> getAllUsers() {
//        Transaction tx = session.beginTransaction();
//        List list = session.createQuery("from User", User.class).list();
//        tx.commit();
//        if(list == null){
//            throw new ResourseNotFoundException("Invalid ID given.");
//        }
//        return list;
//    }
//
//    public void deleteUser(int id) {
//        Transaction tx = session.beginTransaction();
//        User user = session.find(User.class,id);
//        if(user == null){
//            throw new ResourseNotFoundException("Invalid ID given.");
//        }
//        session.createMutationQuery("delete from User where id=:id")
//                        .setParameter("id",id)
//                                .executeUpdate();
//        tx.commit();
//    }
//
//    public User getUserById(int id) {
//        Transaction tx = session.beginTransaction();
//        User user = session.find(User.class,id);
//        tx.commit();
//        if(user== null){
//            throw new ResourseNotFoundException("Invalid ID given.");
//        }
//        return user;
//    }
//
////    public User getUserByUsername(String username) {
////        Transaction tx = session.beginTransaction();
////        User user =
////        tx.commit();
////    }
//}

//package com.project.cookguide.Cook.guide.project.dao;
//
//import com.project.cookguide.Cook.guide.project.dto.BookmarkDto;
//import com.project.cookguide.Cook.guide.project.entities.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//
//import javax.management.Query;
//import java.util.List;
//
//@Repository
//public class UserDAO {
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @SuppressWarnings({"unchecked","rawtypes"})
//    public List<BookmarkDto> getBookmarkByUserId(Long id){
//        Session session = this.sessionFactory.getCurrentSession();
//        String hql = "FROM bookmark WHERE id = :id";
//        //Query query = (Query) session.createQuery(hql);
//        org.hibernate.Query query = session.createQuery(hql);
//        query.setParameter("id",id);
//
//        List<BookmarkDto> bookmarkDtos = query.list();
//
//        if(bookmarkDtos != null && bookmarkDtos.size() > 0) {
//            return bookmarkDtos;
//        }else{
//            return null;
//        }
//    }
//    @SuppressWarnings({"unchecked","rawtypes"})
//    public User loadUserByUsername(final String username){
//        Session session = this.sessionFactory.getCurrentSession();
//        String hql = "from User where username = : username";
//        //Query query = (Query) session.createQuery(hql);
//        org.hibernate.Query query = session.createQuery(hql);
//        query.setParameter("username",username);
//
//        List<User> users = query.list();
//
//        if(users != null && users.size() > 0) {
//            return users.get(0);
//        }else{
//            return null;
//        }
//
//    }
//
//    @SuppressWarnings({"unchecked","rawtypes"})
//    public boolean checkLogin(User userForm){
//        Session session = this.sessionFactory.getCurrentSession();
//
//        String hql = "from User where username = :username and password = :password";
//        org.hibernate.Query query = session.createQuery(hql);
//        query.setParameter("username",userForm.getUsername());
//        query.setParameter("password",userForm.getPassword());
//
//        List<User> users = query.list();
//
//        if(users != null && users.size() > 0) {
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//}

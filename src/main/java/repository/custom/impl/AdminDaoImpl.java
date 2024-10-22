package repository.custom.impl;

import dto.Admin;
import entity.AdminEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.AdminDao;
import utill.CrudUtil;
import utill.HibernateUtil;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    //--------Singleton----------//
    private static AdminDaoImpl instance;

    private AdminDaoImpl(){}

    public static AdminDaoImpl getInstance(){
        return null==instance?instance = new AdminDaoImpl():instance;
    }
    //-----------------------------//

    @Override
    public void save(AdminEntity admin) {
        Transaction transaction = null;
        Session session = null;

        try{
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            session.persist(admin);
            session.getTransaction().commit();
        }catch (Exception ex) {
            if (transaction!= null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
}

    @Override
    public List<AdminEntity> getAll() {

        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            String SQL = "FROM admin";

            return session.createQuery(SQL, AdminEntity.class).getResultList();
        } catch (Exception ex) {
            if (transaction!= null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return null;
    }
}

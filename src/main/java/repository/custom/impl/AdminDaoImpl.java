package repository.custom.impl;


import entity.AdminEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.AdminDao;
import utill.HibernateUtil;
import utill.HibernateUtilType;

import java.util.List;

public class AdminDaoImpl implements AdminDao {

    //--------Singleton----------//
    private static AdminDaoImpl instance;

    private AdminDaoImpl() {
    }

    public static AdminDaoImpl getInstance() {
        return null == instance ? instance = new AdminDaoImpl() : instance;
    }
    //-----------------------------//

    @Override
    public void save(AdminEntity admin) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSession(HibernateUtilType.ADMIN);
            transaction = session.beginTransaction();
            session.persist(admin);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<AdminEntity> getAll() {

        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSession(HibernateUtilType.ADMIN);
            transaction = session.beginTransaction();

            String SQL = "FROM admin";

            return session.createQuery(SQL, AdminEntity.class).getResultList();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}

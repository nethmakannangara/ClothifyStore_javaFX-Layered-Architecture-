package repository.custom.impl;

import entity.ItemEntity;
import entity.SupplierEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.SupplierDao;
import utill.HibernateUtil;
import utill.HibernateUtilType;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {

    //--------Singleton---------//
    private static SupplierDaoImpl instance;

    private SupplierDaoImpl() {
    }

    public static SupplierDaoImpl getInstance() {
        return instance == null ? instance = new SupplierDaoImpl() : instance;
    }
    //-------------------------//

    @Override
    public void save(SupplierEntity supplierEntity) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSession(HibernateUtilType.SUPPLIER);
            transaction = session.beginTransaction();
            session.persist(supplierEntity);
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
    public List<SupplierEntity> getAll() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSession(HibernateUtilType.SUPPLIER);
            transaction = session.beginTransaction();

            String SQL = "FROM supplier";

            return session.createQuery(SQL, SupplierEntity.class).getResultList();
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

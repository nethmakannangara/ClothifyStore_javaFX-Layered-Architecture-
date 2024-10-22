package repository.custom.impl;


import entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.EmployeeDao;
import utill.HibernateUtil;
import utill.HibernateUtilType;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    //--------Singleton----------//
    private static EmployeeDaoImpl instance;

    private EmployeeDaoImpl() {
    }

    public static EmployeeDaoImpl getInstance() {
        return null == instance ? instance = new EmployeeDaoImpl() : instance;
    }
    //-----------------------------//

    @Override
    public void save(EmployeeEntity employee) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSession(HibernateUtilType.EMPLOYEE);
            transaction = session.beginTransaction();
            session.persist(employee);
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
    public List<EmployeeEntity> getAll() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSession(HibernateUtilType.EMPLOYEE);
            transaction = session.beginTransaction();

            String SQL = "FROM employee";

            return session.createQuery(SQL, EmployeeEntity.class).getResultList();
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

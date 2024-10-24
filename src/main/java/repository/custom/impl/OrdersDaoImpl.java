package repository.custom.impl;

import entity.ItemEntity;
import entity.OrdersEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.OrdersDao;
import utill.HibernateUtil;
import utill.HibernateUtilType;

import java.util.List;

public class OrdersDaoImpl implements OrdersDao {

    //--------Singleton---------//
    private static OrdersDaoImpl instance;

    private OrdersDaoImpl() {
    }

    public static OrdersDaoImpl getInstance() {
        return instance == null ? instance = new OrdersDaoImpl() : instance;
    }
    //-------------------------//

    @Override
    public void save(OrdersEntity ordersEntity) {

    }

    @Override
    public List<OrdersEntity> getAll() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSession(HibernateUtilType.ORDERS);
            transaction = session.beginTransaction();

            String SQL = "FROM orders";

            return session.createQuery(SQL, OrdersEntity.class).getResultList();
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

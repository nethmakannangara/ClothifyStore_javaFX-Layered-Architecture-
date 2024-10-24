package repository.custom.impl;

import dto.Item;
import entity.ItemEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.ItemDao;
import utill.HibernateUtil;
import utill.HibernateUtilType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    //--------Singleton---------//
    private static ItemDaoImpl instance;

    private ItemDaoImpl() {
    }

    public static ItemDaoImpl getInstance() {
        return instance == null ? instance = new ItemDaoImpl() : instance;
    }
    //-------------------------//

    @Override
    public void save(ItemEntity item) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSession(HibernateUtilType.ITEM);
            transaction = session.beginTransaction();
            session.persist(item);
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
    public void delete(String itemCode) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSession(HibernateUtilType.ITEM);
            transaction = session.beginTransaction();

            String SQL = "DELETE FROM item WHERE itemCode = :itemCode";

            session.createQuery(SQL).setParameter("itemCode",itemCode).executeUpdate();
            transaction.commit();

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
    public int update(ItemEntity updateItem) {
        Transaction transaction = null;
        Session session = null;
        int isUpdate = 0;
        try {
            session = HibernateUtil.getSession(HibernateUtilType.ITEM);
            transaction = session.beginTransaction();

            String SQL = "UPDATE item SET description = :description ,size = :size ,unitPrice = :unitPrice ,qtyOnHand = :qtyOnHand  WHERE itemCode = :itemCode";

            isUpdate = session.createQuery(SQL)
                    .setParameter("description",updateItem.getDescription())
                    .setParameter("size",updateItem.getSize())
                    .setParameter("unitPrice",updateItem.getUnitPrice())
                    .setParameter("qtyOnHand",updateItem.getQtyOnHand())
                    .setParameter("itemCode",updateItem.getItemCode())
                            .executeUpdate();

            transaction.commit();

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
        return isUpdate;
    }

    public Item search(String itemCode){
//        String SQL = "SELECT * FROM item WHERE itemCode = ?";
//        Item searchItem = null ;
//        try {
//            ResultSet resultSet = CrudUtil.execute(SQL, itemCode);
//            while (resultSet.next()){
//                searchItem = new Item(
//                        resultSet.getString("itemCode"),
//                        resultSet.getString("description"),
//                        resultSet.getDouble("unitPrice"),
//                        resultSet.getString("size"),
//                        resultSet.getInt("qtyOnHand")
//                );
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return searchItem;
        return null;
    }

    @Override
    public List<ItemEntity> getAll() {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSession(HibernateUtilType.ITEM);
            transaction = session.beginTransaction();

            String SQL = "FROM item";

            return session.createQuery(SQL,ItemEntity.class).getResultList();
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

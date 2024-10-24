package repository;

import repository.custom.impl.*;
import utill.DaoType;

public class DaoFactory {

    private static DaoFactory instance;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return null == instance ? instance = new DaoFactory() : instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case ADMIN : return (T) AdminDaoImpl.getInstance();
            case EMPLOYEE:return (T) EmployeeDaoImpl.getInstance();
            case ITEM:return (T) ItemDaoImpl.getInstance();
            case SUPPLIER:return (T) SupplierDaoImpl.getInstance();
            case ORDERS:return (T) OrdersDaoImpl.getInstance();
            case PLACEORDER:return (T) PlaceOrderDaoImpl.getInstance();
        }
        return null;
    }
}

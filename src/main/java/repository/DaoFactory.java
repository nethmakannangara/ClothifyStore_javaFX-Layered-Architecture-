package repository;

import repository.custom.impl.AdminDaoImpl;
import repository.custom.impl.EmployeeDaoImpl;
import repository.custom.impl.ItemDaoImpl;
import repository.custom.impl.SupplierDaoImpl;
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
        }
        return null;
    }
}

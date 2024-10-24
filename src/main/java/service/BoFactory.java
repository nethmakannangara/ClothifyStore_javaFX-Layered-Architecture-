package service;


import service.custom.AdminService;
import service.custom.impl.*;
import utill.ServiceType;

public class BoFactory {

    private static BoFactory instance;

    private BoFactory(){}

    public static BoFactory getInstance(){
        return null==instance?instance = new BoFactory():instance;
    }

    public <T extends SuperFactory>T getServiceType(ServiceType type){
        switch (type){
            case ADMIN : return (T) AdminServiceImpl.getInstance();
            case EMPLOYEE:return (T) EmployeeServiceImpl.getInstance();
            case ITEM:return (T) ItemServiceImpl.getInstance();
            case SUPPLIER:return (T) SupplierSeriviceImpl.getInstance();
            case PLACEORDER:return (T) PlaceOrderServiceImpl.getInstance();
        }
        return null;
    }
}

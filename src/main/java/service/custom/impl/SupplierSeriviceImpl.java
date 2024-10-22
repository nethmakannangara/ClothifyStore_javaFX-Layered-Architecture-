package service.custom.impl;


import dto.Admin;
import dto.Item;
import dto.Supplier;
import entity.EmployeeEntity;
import entity.ItemEntity;
import entity.SupplierEntity;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ItemDao;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import utill.DaoType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SupplierSeriviceImpl implements SupplierService {

    //--------Singleton--------//
    private static SupplierSeriviceImpl instance;

    private SupplierSeriviceImpl(){}

    public static SupplierSeriviceImpl getInstance(){
        return instance==null?instance=new SupplierSeriviceImpl():instance;
    }
    //--------------------------//

    SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);

    @Override
    public void save(Supplier supplier) {
        supplierDao.save(new ModelMapper().map(supplier,SupplierEntity.class));
    }

    @Override
    public boolean checkDuplicateEmail(String email) {
        List<SupplierEntity> resultSet = supplierDao.getAll();
        List<Supplier> supplierList = new ArrayList<>();

        resultSet.forEach(supplierEntity->{
            supplierList.add(new ModelMapper().map(supplierEntity,Supplier.class));
        });

        for (int i = 0; i < supplierList.size(); i++) {
            if(supplierList.get(i).getEmail().equals(email)){
                new Alert(Alert.AlertType.ERROR,"This email already added..").show();
                return false;
            }
        }
        return true;
    }

    public String checkLastId() {
        String lastId = null;
        List<SupplierEntity> resultSet = supplierDao.getAll();

        if(!resultSet.isEmpty()){
            lastId = new ModelMapper().map(resultSet.getLast(), Supplier.class).getSupplierId();
        }

        return lastId;
    }

    public String generateId() {
        String supplierId = "1";
        if(checkLastId()!=null){
            Scanner id = new Scanner(checkLastId());
            id.useDelimiter("[A-Z]");
            while (id.hasNext()){
                supplierId = id.next();
            }
            int supplierCount = Integer.parseInt(supplierId);
            return String.format("SUP%04d", ++supplierCount);
        }else {
            int supplierCount = Integer.parseInt(supplierId);
            return String.format("SUP%04d", supplierCount);
        }
    }
}

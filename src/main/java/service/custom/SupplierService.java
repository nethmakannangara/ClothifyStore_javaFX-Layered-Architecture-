package service.custom;

import dto.Supplier;
import repository.custom.SupplierDao;
import service.SuperFactory;

public interface SupplierService extends SuperFactory {

    void save(Supplier supplier);

    boolean checkDuplicateEmail(String email);

    String generateId();

    String checkLastId();
}

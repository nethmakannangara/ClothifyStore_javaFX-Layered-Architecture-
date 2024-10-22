package service.custom;

import dto.Admin;
import service.SuperFactory;

public interface AdminService extends SuperFactory {

    String checkLastId();

    boolean checkPasswords(String password, String confirmationPassword);

    boolean checkDuplicateEmail(String email);

    void save(Admin admin);

    String generateId();
}

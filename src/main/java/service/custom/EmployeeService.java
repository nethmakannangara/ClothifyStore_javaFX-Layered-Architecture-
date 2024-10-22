package service.custom;

import dto.Employee;
import service.SuperFactory;

public interface EmployeeService extends SuperFactory {

    void save(Employee employee);

    boolean checkDuplicateEmail(String text);

    boolean checkPasswords(String text, String text1);

    String generateId();
}

package service.custom.impl;

import dto.Admin;
import dto.Employee;
import entity.EmployeeEntity;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import utill.DaoType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeServiceImpl implements EmployeeService {

    //----------Singleton----------//
    private static EmployeeServiceImpl instance;

    private EmployeeServiceImpl(){}

    public static EmployeeServiceImpl getInstance(){
        return null==instance?instance = new EmployeeServiceImpl():instance;
    }
    //------------------------------//

    EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

    @Override
    public void save(Employee employee) {
        employeeDao.save(new ModelMapper().map(employee, EmployeeEntity.class));
    }

    public String checkLastId() {
        String lastId = null;
        List<EmployeeEntity> resultSet = employeeDao.getAll();

        if(!resultSet.isEmpty()){

            lastId = new ModelMapper().map(resultSet.getLast(), Employee.class).getEmployeeId();
        }
        return lastId;
    }

    public String generateId() {
        String employeeId = "1";
        if(checkLastId()!=null){
            Scanner id = new Scanner(checkLastId());
            id.useDelimiter("[A-Z]");
            while (id.hasNext()){
                employeeId = id.next();
            }
            int employeeCount = Integer.parseInt(employeeId);
            return String.format("EMP%04d", ++employeeCount);
        }else {
            int employeeCount = Integer.parseInt(employeeId);
            return String.format("EMP%04d", employeeCount);
        }
    }

    @Override
    public boolean checkPasswords(String password, String confirmationPassword) {
        if (password.equals(confirmationPassword)) {
            return true;
        }else{
            new Alert(Alert.AlertType.ERROR,"Please check your password").show();
        }
        return false;
    }

    @Override
    public boolean checkDuplicateEmail(String email) {
        List<EmployeeEntity> resultSet = employeeDao.getAll();
        List<Employee> employeeList = new ArrayList<>();

        resultSet.forEach(employeeEntity->{
            employeeList.add(new ModelMapper().map(employeeEntity,Employee.class));
        });

        for (int i = 0; i < employeeList.size(); i++) {
            if(employeeList.get(i).getEmail().equals(email)){
                new Alert(Alert.AlertType.ERROR,"This email already added..").show();
                return false;
            }
        }
        return true;
    }
}

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
            lastId = new ModelMapper().map(resultSet.getLast(), Admin.class).getAdminId();
        }

        return lastId;
    }

    public String generateId() {
        String adminId = "1";
        if(checkLastId()!=null){
            Scanner id = new Scanner(checkLastId());
            id.useDelimiter("[A-Z]");
            while (id.hasNext()){
                adminId = id.next();
            }
            int adminCount = Integer.parseInt(adminId);
            return String.format("E%04d", ++adminCount);
        }else {
            int adminCount = Integer.parseInt(adminId);
            return String.format("E%04d", adminCount);
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
        List<Admin> adminList = new ArrayList<>();

        resultSet.forEach(adminEntity->{
            adminList.add(new ModelMapper().map(adminEntity,Admin.class));
        });

        for (int i = 0; i < adminList.size(); i++) {
            if(adminList.get(i).getEmail().equals(email)){
                new Alert(Alert.AlertType.ERROR,"This email already added..").show();
                return false;
            }
        }
        return true;
    }
}

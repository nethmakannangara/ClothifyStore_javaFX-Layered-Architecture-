package service.custom.impl;

import dto.Admin;
import entity.AdminEntity;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.AdminDao;
import service.custom.AdminService;
import utill.CrudUtil;
import utill.DaoType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminServiceImpl implements AdminService {

    private static AdminServiceImpl instance;

    private AdminServiceImpl() {
    }

    public static AdminServiceImpl getInstance() {
        return null == instance ? instance = new AdminServiceImpl() : instance;
    }

    //---------LAYERED ARCHITECTURE-------------//

    AdminDao adminDao = DaoFactory.getInstance().getDaoType(DaoType.ADMIN);

    @Override
    public void save(Admin admin) {
        AdminEntity adminEntity = new ModelMapper().map(admin,AdminEntity.class);
        adminDao.save(adminEntity);
    }

    public String checkLastId() {
        String lastId = null;
        List<AdminEntity> resultSet = adminDao.getAll();

        lastId = new ModelMapper().map(resultSet.getLast(),Admin.class).getAdminId();

        return lastId;
    }

    public String generateId() {
        String adminId = "1";
        Scanner id = new Scanner(checkLastId());
        id.useDelimiter("[A-Z]");
        while (id.hasNext()){
            adminId = id.next();
        }
        int adminCount = Integer.parseInt(adminId);
        return String.format("A%04d", ++adminCount);
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
        List<AdminEntity> resultSet = adminDao.getAll();
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

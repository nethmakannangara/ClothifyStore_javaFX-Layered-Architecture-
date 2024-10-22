package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import service.BoFactory;
import service.custom.AdminService;
import service.custom.ItemService;
import utill.ServiceType;

import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

public class AddAdminFormController implements Initializable {

    AdminService adminService = BoFactory.getInstance().getServiceType(ServiceType.ADMIN);

    @FXML
    public Label lblAdminID;

    @FXML
    public JFXTextField txtAdminId;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXTextField txtAdminConfirmPassword;

    @FXML
    private JFXTextField txtAdminEmail;

    @FXML
    private JFXTextField txtAdminName;

    @FXML
    private JFXTextField txtAdminPassword;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        if (adminService.checkPasswords(txtAdminPassword.getText(), txtAdminConfirmPassword.getText()) && adminService.checkDuplicateEmail(txtAdminEmail.getText())) {
            adminService.save(new Admin(
                    lblAdminID.getText(),
                    txtAdminName.getText(),
                    txtAdminEmail.getText(),
                    Base64.getEncoder().encodeToString(txtAdminPassword.getText().getBytes())
            ));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       lblAdminID.setText(adminService.generateId());
    }
}

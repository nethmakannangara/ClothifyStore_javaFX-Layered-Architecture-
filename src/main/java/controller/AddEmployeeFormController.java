package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dto.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import service.BoFactory;
import service.custom.EmployeeService;
import utill.ServiceType;

import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

public class AddEmployeeFormController implements Initializable {

    EmployeeService employeeService = BoFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    @FXML
    public JFXRadioButton btnRadioFemale;

    @FXML
    public JFXRadioButton btnRadioMale;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private JFXTextField txtEmployeeAddress;

    @FXML
    private JFXTextField txtEmployeeConfirmPassword;

    @FXML
    private JFXTextField txtEmployeeContactNo;

    @FXML
    private JFXTextField txtEmployeeEmail;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtEmployeePassword;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String gender = null;
        if (btnRadioMale.isSelected()){
            gender = btnRadioMale.getText();
        }else {
            gender = btnRadioFemale.getText();
        }
        if (employeeService.checkPasswords(txtEmployeePassword.getText(), txtEmployeeConfirmPassword.getText()) && employeeService.checkDuplicateEmail(txtEmployeeEmail.getText()) && btnRadioMale.isSelected()) {
            employeeService.save(new Employee(
                    lblEmployeeId.getText(),
                    txtEmployeeName.getText(),
                    txtEmployeeAddress.getText(),
                    txtEmployeeContactNo.getText(),
                    txtEmployeeEmail.getText(),
                    Base64.getEncoder().encodeToString(txtEmployeePassword.getText().getBytes()),
                    gender
            ));
        } else if (btnRadioMale.isSelected()==false && btnRadioFemale.isSelected()==false) {
            new Alert(Alert.AlertType.ERROR,"Please select gender").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblEmployeeId.setText(employeeService.generateId());

        ToggleGroup genderGroup = new ToggleGroup();
        btnRadioFemale.setToggleGroup(genderGroup);
        btnRadioMale.setToggleGroup(genderGroup);
    }
}

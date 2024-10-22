package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import service.BoFactory;
import service.custom.SupplierService;
import utill.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    SupplierService supplierService = BoFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    @FXML
    private JFXButton btnRegister;

    @FXML
    private Label lblSupplierID;

    @FXML
    private JFXTextField txtSupplierAddress;

    @FXML
    private JFXTextField txtSupplierCompany;

    @FXML
    private JFXTextField txtSupplierContactNo;

    @FXML
    private JFXTextField txtSupplierEmail;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        if (supplierService.checkDuplicateEmail(txtSupplierEmail.getText())) {
            supplierService.save(new Supplier(
                    lblSupplierID.getText(),
                    txtSupplierName.getText(),
                    txtSupplierAddress.getText(),
                    txtSupplierEmail.getText(),
                    txtSupplierCompany.getText(),
                    txtSupplierContactNo.getText()
            ));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblSupplierID.setText(supplierService.generateId());
    }

}

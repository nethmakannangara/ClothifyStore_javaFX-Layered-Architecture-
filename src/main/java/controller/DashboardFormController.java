package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private JFXButton btnAddAdmin;

    @FXML
    private JFXButton btnAddItem;

    @FXML
    private JFXButton btnAddSupplier;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    void btnAddAdminOnAction(ActionEvent event) {
        Stage stage;
        try {
            stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/add_admin_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
//        Stage itemStage;
//        try {
//            itemStage = new Stage();
//            itemStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/item_form.fxml"))));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        itemStage.show();

    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
//        Stage supplierForm;
//        try {
//            supplierForm = new Stage();
//            supplierForm.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/supplier_form.fxml"))));
//            supplierForm.show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
//        Stage placeOrderStage;
//        try {
//            placeOrderStage = new Stage();
//            placeOrderStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/place_order_form.fxml"))));
//            placeOrderStage.show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) {
//        Stage employeeFromStage;
//        try {
//            employeeFromStage = new Stage();
//            employeeFromStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/add_employee_from.fxml"))));
//            employeeFromStage.show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
